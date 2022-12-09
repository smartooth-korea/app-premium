package co.smartooth.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import co.smartooth.app.utils.AES256Util;
import co.smartooth.web.vo.WebLocationVO;
import co.smartooth.web.vo.WebSeqNoVO;
import co.smartooth.web.vo.WebTeethMeasureVO;
import co.smartooth.web.service.WebRegistService;
import co.smartooth.web.service.WebTeethService;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class UtilsController {

	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired(required = false)
	private WebTeethService WebTeethService;
	
	
	@Autowired(required = false)
	private WebRegistService webRegistService;
	
	
	
	
	

	/**
	 * 기능   : 비밀번호 암호화 및 복호화
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 08. 24
	 * 			 APP에서 JSON형식으로 전달 받은 회원의 ID와 비밀번호를 확인 후 JSON으로 반환
	 */
	@PostMapping(value = {"/premium/utils/passwordCrypto.do"})
	@ResponseBody
	public HashMap<String,Object> passwordCrypto(@RequestBody HashMap<String, Object> paramMap) {
       
	    logger.debug("========== UtilController ========== passwordCrypto.do ==========");
	    
	    HashMap<String, Object> hm = new HashMap<String, Object>();
	    
		String userPwd = null;
		String encodePwd = null;
		String decodePwd = null;
		
		// 비밀번호 암호화 
		AES256Util aes256Util = new AES256Util();
		userPwd = (String)paramMap.get("userPwd");
		encodePwd = aes256Util.aesEncode(userPwd);
		decodePwd = aes256Util.aesDecode(encodePwd);
		
		System.out.println("USER_PWD Encoding >>>>>>>>>>>>>>>> "+ encodePwd);
		System.out.println("USER_PWD Decoding >>>>>>>>>>>>>>>> "+ decodePwd);
		
		hm.put("encodePwd", encodePwd);
		hm.put("decodePwd", decodePwd);
		
		return hm;
	}
	
	
	 /**
     * 기능   : 학생 회원 충치 개수 업데이트 (수동)
     * 작성자 : 정주현 
     * 작성일 : 2022. 08. 26
     * 수정일 : 
     */
	@PostMapping(value = {"/premium/utils/updateCavityCntKids.do"})
	@ResponseBody
	public void updateCavityCntKids(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
		logger.debug("========== UtilController ========== updateCavityCnt.do ==========");
		
		String userId = null;
		String startDt = null;
		String endDt = null;

		// 20개 치아 측정 값 
		int[] teethValue = new int[20];
		
		Integer cavityCaution = 0;
		Integer cavityDanger = 0;
			    
		// 정상 수치 0~12 이하 갯수
		int cavityNormalCnt = 0;
		// 주의 수치 13~24 이하 갯수
		int cavityCautionCnt = 0;
	    // 위험 수치 25이상 갯수
	    int cavityDangerCnt = 0;
		
		
		List<WebTeethMeasureVO> userTeethValues = new ArrayList<WebTeethMeasureVO>();
		
		WebTeethMeasureVO WebTeethMeasureVO = new WebTeethMeasureVO();

		HashMap<String,Integer> cavityLevel = new HashMap<String,Integer>();
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId= (String)paramMap.get("userId");
		startDt= (String)paramMap.get("startDt");
		endDt= (String)paramMap.get("endDt");
		
		WebTeethMeasureVO.setUserId(userId);
		WebTeethMeasureVO.setStartDt(startDt);
		WebTeethMeasureVO.setEndDt(endDt);
		
			
		try {
			
			// 모든 치아에 대한 조회 값 (오늘의 값이 없을 경우 최근 값으로 불러와야함)
			userTeethValues = WebTeethService.selectUserTeethMeasureValue(WebTeethMeasureVO);
			
			// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
			cavityLevel = WebTeethService.selectCavityLevel();
			
			teethValue[0] = userTeethValues.get(0).getT04();
			teethValue[1] = userTeethValues.get(0).getT05();
			teethValue[2] = userTeethValues.get(0).getT06();
			teethValue[3] = userTeethValues.get(0).getT07();
			teethValue[4] = userTeethValues.get(0).getT08();
			teethValue[5] = userTeethValues.get(0).getT09();
			teethValue[6] = userTeethValues.get(0).getT10();
			teethValue[7] = userTeethValues.get(0).getT11();
			teethValue[8] = userTeethValues.get(0).getT12();
			teethValue[9] = userTeethValues.get(0).getT13();
			teethValue[10] = userTeethValues.get(0).getT20();
			teethValue[11] = userTeethValues.get(0).getT21();
			teethValue[12] = userTeethValues.get(0).getT22();
			teethValue[13] = userTeethValues.get(0).getT23();
			teethValue[14] = userTeethValues.get(0).getT24();
			teethValue[15] = userTeethValues.get(0).getT25();
			teethValue[16] = userTeethValues.get(0).getT26();
			teethValue[17] = userTeethValues.get(0).getT27();
			teethValue[18] = userTeethValues.get(0).getT28();
			teethValue[19] = userTeethValues.get(0).getT29();
			
			
			cavityCaution = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
			cavityDanger = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

			
			for (int i = 0; i < teethValue.length; i++) {
				
				System.out.println("teethValue[i] >> " + teethValue[i]);
				if (teethValue[i] < cavityCaution) { 														// 0~12 정상 치아
					cavityNormalCnt++;
				} else if (teethValue[i] >= cavityCaution && teethValue[i] < cavityDanger) {	// 13~24 주의
					cavityCautionCnt++;
				} else if (teethValue[i] >= cavityDanger) {	// 25~ 충치
					cavityDangerCnt++;
				}
			}
			
			WebTeethMeasureVO.setCavityNormal(cavityNormalCnt);
			WebTeethMeasureVO.setCavityCaution(cavityCautionCnt);
			WebTeethMeasureVO.setCavityDanger(cavityDangerCnt);
			
			userTeethValues.get(0).setCavityDanger(cavityDangerCnt);
			
			// ST_STUDENT_USER_DETAIL 테이블에 CavityCnt 업데이트
			WebTeethMeasureVO.setMeasureDt(startDt);
			WebTeethService.updateUserCavityCntByMeasureDt(WebTeethMeasureVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
     * 기능   : 학생 회원 충치 개수 업데이트 (수동)
     * 작성자 : 정주현 
     * 작성일 : 2022. 08. 26
     * 수정일 : 
     */
	@PostMapping(value = {"/premium/utils/updateCavityCntNormal.do"})
	@ResponseBody
	public void updateCavityCntNormal(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
		logger.debug("========== UtilController ========== updateCavityCnt.do ==========");
		
		String userId = null;
		String startDt = null;
		String endDt = null;

		// 32개 치아 측정 값 
		int[] teethValue = new int[32];
		
		Integer cavityCaution = 0;
		Integer cavityDanger = 0;
			    
		// 정상 수치 0~12 이하 갯수
		int cavityNormalCnt = 0;
		// 주의 수치 13~24 이하 갯수
		int cavityCautionCnt = 0;
	    // 위험 수치 25이상 갯수
	    int cavityDangerCnt = 0;
		
		
		List<WebTeethMeasureVO> userTeethValues = new ArrayList<WebTeethMeasureVO>();
		
		WebTeethMeasureVO WebTeethMeasureVO = new WebTeethMeasureVO();

		HashMap<String,Integer> cavityLevel = new HashMap<String,Integer>();
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId= (String)paramMap.get("userId");
		startDt= (String)paramMap.get("startDt");
		endDt= (String)paramMap.get("endDt");
		
		WebTeethMeasureVO.setUserId(userId);
		WebTeethMeasureVO.setStartDt(startDt);
		WebTeethMeasureVO.setEndDt(endDt);
		WebTeethMeasureVO.setMeasureDt(endDt);
		
			
		try {
			
			// 모든 치아에 대한 조회 값 (오늘의 값이 없을 경우 최근 값으로 불러와야함)
			userTeethValues = WebTeethService.selectUserTeethMeasureValue(WebTeethMeasureVO);
			
			// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
			cavityLevel = WebTeethService.selectCavityLevel();
			
			teethValue[0] = userTeethValues.get(0).getT01();
			teethValue[1] = userTeethValues.get(0).getT02();
			teethValue[2] = userTeethValues.get(0).getT03();
			teethValue[3] = userTeethValues.get(0).getT04();
			teethValue[4] = userTeethValues.get(0).getT05();
			teethValue[5] = userTeethValues.get(0).getT06();
			teethValue[6] = userTeethValues.get(0).getT07();
			teethValue[7] = userTeethValues.get(0).getT08();
			teethValue[8] = userTeethValues.get(0).getT09();
			teethValue[9] = userTeethValues.get(0).getT10();
			teethValue[10] = userTeethValues.get(0).getT11();
			teethValue[11] = userTeethValues.get(0).getT12();
			teethValue[12] = userTeethValues.get(0).getT13();
			teethValue[13] = userTeethValues.get(0).getT14();
			teethValue[14] = userTeethValues.get(0).getT15();
			teethValue[15] = userTeethValues.get(0).getT16();
			teethValue[16] = userTeethValues.get(0).getT17();
			teethValue[17] = userTeethValues.get(0).getT18();
			teethValue[18] = userTeethValues.get(0).getT19();
			teethValue[19] = userTeethValues.get(0).getT20();
			teethValue[20] = userTeethValues.get(0).getT21();
			teethValue[21] = userTeethValues.get(0).getT22();
			teethValue[22] = userTeethValues.get(0).getT23();
			teethValue[23] = userTeethValues.get(0).getT24();
			teethValue[24] = userTeethValues.get(0).getT25();
			teethValue[25] = userTeethValues.get(0).getT26();
			teethValue[26] = userTeethValues.get(0).getT27();
			teethValue[27] = userTeethValues.get(0).getT28();
			teethValue[28] = userTeethValues.get(0).getT29();
			teethValue[29] = userTeethValues.get(0).getT30();
			teethValue[30] = userTeethValues.get(0).getT31();
			teethValue[31] = userTeethValues.get(0).getT32();
			
			
			cavityCaution = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
			cavityDanger = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

			
			for (int i = 0; i < teethValue.length; i++) {
				
				System.out.println("teethValue[i] >> " + teethValue[i]);
				if (teethValue[i] < cavityCaution) { 														// 0~12 정상 치아
					cavityNormalCnt++;
				} else if (teethValue[i] >= cavityCaution && teethValue[i] < cavityDanger) {	// 13~24 주의
					cavityCautionCnt++;
				} else if (teethValue[i] > cavityDanger) {	// 25~ 충치
					cavityDangerCnt++;
				}
			}

			WebTeethMeasureVO.setCavityNormal(cavityNormalCnt);
			WebTeethMeasureVO.setCavityCaution(cavityCautionCnt);
			WebTeethMeasureVO.setCavityDanger(cavityDangerCnt);
			
			userTeethValues.get(0).setCavityDanger(cavityDangerCnt);
			
			// ST_STUDENT_USER_DETAIL 테이블에 CavityCnt 업데이트
			WebTeethService.updateUserCavityCnt(WebTeethMeasureVO);
			WebTeethService.updateUserCavityCntByMeasureDt(WebTeethMeasureVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 기능   : 회원 일괄 등록
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 10. 05
	 * 비고 : 추 후 미국이나 해외에 관련 되어서 STATE에 대한 정보도 같이 넣을 수 있도록 변경해야함
	 * 
	 * 수정일 : 
	 */
	@PostMapping(value = {"/premium/utils/registUsers.do"})
	@ResponseBody
	public void registUsers(@RequestParam("file") MultipartFile file) throws Exception {
		
		
		logger.debug("========== UtilController ========== registUsers.do ==========");
		
		
		int classSeqNo = 0;
		int tcSeqNo = 0;
		int stSeqNo = 0;
		int paSeqNo = 0;
		
		String countryCd = null;
		String sidoCd = null;
		String ggemdCd = null;
		
		
		
		
		// 파일 이름
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		
		// xlsx, xls 엑셀 확장자만 업로드 할 수 있도록 설정
		if (!extension.equals("xlsx") && !extension.equals("xls")) {
		      throw new IOException("엑셀파일만 업로드 해주세요.");
	    } 
		
		Workbook workbook = null;
		if (extension.equals("xlsx")) {
	      workbook = new XSSFWorkbook(file.getInputStream());
	    } else if (extension.equals("xls")) {
	      workbook = new HSSFWorkbook(file.getInputStream());
	    }
		
		
		/**
		 ** 첫번째 시트 (학교정보)
		 **/ 
		
		Sheet schInfoworksheet = workbook.getSheetAt(0);
		
		// 학교 정보 입력
		// i = 1 부터 인것은 맨 윗줄을 읽지 않는다.
		for (int i = 1; i < schInfoworksheet.getPhysicalNumberOfRows(); i++) {

			// 엑셀에서 ROW가 여러 줄(반으로 인해서) 일 경우 첫번째만 DB에 입력되도록 if문 사용
			if(i == 1) {
				String schNm = null;
				String schType = null;
				String classNm = null;
				String countryNm = null;
				String siooNm = null;
				String sigunguNm = null;
				String eupMyeonDongNm = null;
				String schCd = null;
				int schSeqNo = 0;
				
				
				WebLocationVO webLocationVO = new WebLocationVO();
				WebSeqNoVO webSeqNoVO = new WebSeqNoVO();
	
				Row row = schInfoworksheet.getRow(i);
				
				schNm = row.getCell(0).getStringCellValue();
				schType = row.getCell(1).getStringCellValue();
				classNm = row.getCell(2).getStringCellValue();
				countryNm = row.getCell(3).getStringCellValue();
				siooNm = row.getCell(4).getStringCellValue();
				sigunguNm = row.getCell(5).getStringCellValue();
	
				
				if(schType.contains("어린") || schType.contains("유아") || schType.contains("유치")) {
					schType = "KG";
				}else if(schType.contains("초등")) {
					schType = "EL";
				}else if(schType.contains("중")) {
					schType = "MD";
				}else if(schType.contains("고등")) {
					schType = "HI";
				}
				
				// 읍면동에서 예를들어 신정동 하나로 입력하는 경우가 발생, 그럴 경우 동을 제외하고 신정만 읽어서 조회해야함
				eupMyeonDongNm = row.getCell(6).getStringCellValue();
				
				// 학교 코드를 생성하기 위해 나라, 시도, 시군구, 읍면동 코드 조회
				countryCd = webRegistService.selectCountryCd(countryNm);
				sidoCd = webRegistService.selectSiDoCd(siooNm);
				
				
				webLocationVO.setSidoCd(sidoCd);
				webLocationVO.setSigunguNm(sigunguNm);
				if(eupMyeonDongNm.matches(".*\\d.*")) {
					webLocationVO.setEupmyeondongNm(eupMyeonDongNm);
				}else {
					webLocationVO.setEupmyeondongNm(eupMyeonDongNm.replaceAll("동", ""));
				}
				
				
				// 지역 정보
				webLocationVO = webRegistService.selectLocationInfo(webLocationVO);
				
				// 시군구읍면동 코드
				ggemdCd = webLocationVO.getSggemdCd();
				
				// 시퀀스 조회
				webSeqNoVO = webRegistService.selectSeqNoAll();
				
				// 학교 코드 시퀀스
				schSeqNo = webSeqNoVO.getSchSeqNo();
				// 학교 코드 생성 :: 나라코드+학교타입+시도코드+시군구읍동면코드+0001
				schCd = countryCd+schType+sidoCd+ggemdCd+String.format("%02d", schSeqNo);
				// 반 코드 시퀀스
				classSeqNo = webSeqNoVO.getClassSeqNo();
				
				// 학교 코드와 학교 이름 INSERT
//				webRegistService.insertSchInfo(schCd, schNm);
				// 학교 코드 시퀀스 증가
//				webRegistService.updateSeqNo("SCH_SEQ_NO" , Integer.toString(schSeqNo+1));
				
				// 반 이름 등록
//				webRegistService.insertClassInfo(String.format("%07d", classSeqNo), classNm);
				// 반 코드 시퀀스 증가
//				webRegistService.updateSeqNo("CLASS_SEQ_NO" , Integer.toString(classSeqNo++));
				
				
				
				// 조회해온 시퀀스를 변수에 담아 저장
				stSeqNo = webSeqNoVO.getStSeqNo();
				tcSeqNo = webSeqNoVO.getTcSeqNo();
				paSeqNo = webSeqNoVO.getPaSeqNo();
				
				
				
			}else {
				
				String classNm = null;
				// 반 이름이 여러개인 경우가 많으므로 반이름만 넣는 작업을 진행
				Row row = schInfoworksheet.getRow(i);
				// 반 이름
				classNm = row.getCell(2).getStringCellValue();
				// 조회를 해서 동일한 값이 있을 경우 그걸로 만든다 (나중에 고려할 문제)
//				webRegistService.insertClassInfo(String.format("%08d", classSeqNo), classNm);
				// 반 코드 시퀀스 증가
				classSeqNo++;
			}
			
//			webRegistService.updateSeqNo("CLASS_SEQ_NO" , Integer.toString(classSeqNo));
			
		}
		
		
		
		
		
		
		
		
		
		// i = 1 부터 인것은 맨 윗줄을 읽지 않는다.
		for (int i = 1; i < schInfoworksheet.getPhysicalNumberOfRows(); i++) {

			String userId = null;
			String userPwd = null;
			String userType = null;
			String userName = null;
			String userCountry = null;
			String userStatus = null;
			String userAddress = null;
			
			Row row = schInfoworksheet.getRow(i);
			
			// 선생님 ID
			userId = sidoCd+ggemdCd+String.format("%04d", tcSeqNo);
			// 선생님 회원 등록 시퀀스 증가
			tcSeqNo++;
			// 선생님 비밀번호 : 1234
			userPwd = "wAeBLxGzduQlEshoYpgzlg==";
			// 회원 종류 : TC 선생님
			userType = "TC";
			// 선생님 회원 이름
			userName = row.getCell(2).getStringCellValue()+" 선생님";
			// 생일 : NULL
			// 회원 거주 국
			userCountry = countryCd;
			// 회원 거주 주
			// userStatus = statusCd;
//			userAddress = siDoNm+siGunGuNm+eupMyeonDongNm;
			
			
			
			
			
			
			

			

			System.out.println("row.getCell(0).getNumericCellValue() >>> " + row.getCell(0).getStringCellValue());
//			System.out.println("row.getCell(1).getNumericCellValue() >>> "+row.getCell(1).getStringCellValue());
//			System.out.println("row.getCell(2).getNumericCellValue() >>> "+row.getCell(2).getStringCellValue());
//			dataList.add(data);
//			
		}

//				    model.addAttribute("datas", dataList); // 5

//				    return "excelList";
		}
		
		
//	}
	
}
