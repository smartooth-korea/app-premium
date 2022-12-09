package co.smartooth.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hpsf.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.smartooth.app.service.DiagnosisService;
import co.smartooth.app.service.TeethService;
import co.smartooth.app.utils.JwtTokenUtil;
import co.smartooth.app.vo.DiagnosisVO;
import co.smartooth.app.vo.TeethInfoVO;
import co.smartooth.app.vo.TeethMeasureVO;
import co.smartooth.app.vo.ToothMeasureVO;
import co.smartooth.app.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 07. 14
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Slf4j
@RestController
public class TeethController {
    
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired(required = false)
	private TeethService teethService;
	
	@Autowired(required = false)
	private DiagnosisService diagnosisService;
	
	// 인증 패스
	private static boolean tokenValidation = false; 
	private static boolean tokenValidation1 = true; 
	
	
	/**
	 * 기능   : 회원의 치아 상태 값 등록
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 05. 20
	 * 수정일 : 2022. 07. 14
	 */
//	@PostMapping(value = {"/app/user/insertUserTeethInfo.do"})
	@PostMapping(value = {"/premium/user/insertUserTeethInfo.do"})
	@ResponseBody
	public HashMap<String,Object> insertUserTeethInfo(@RequestBody HashMap<String, Object> paramMap) {
		
		logger.debug("========== TeethController ========== insertUserTeethInfo.do ==========");
		
		String userId = null;
		String userNo = null;
		String teethStatus = null;
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		TeethInfoVO teethInfoVO = new TeethInfoVO();
		
		userId = (String)paramMap.get("userId");
		userNo = (String)paramMap.get("userNo");
		teethStatus = (String)paramMap.get("teethStatus");
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		if(userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}
		
		try {
			teethInfoVO.setUserId(userId);
			teethInfoVO.setUserNo(userNo);
			teethInfoVO.setTeethStatus(teethStatus);
			// 회원 치아 상태 INSERT
			teethService.insertUserTeethInfo(teethInfoVO);
			hm.put("code", "000");
			hm.put("msg", "Success");
		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "Server Error");
			e.printStackTrace();
		}
		return hm;
	}
	
	
	
	
	/**
	 * 기능   : 회원의 치아 측정 값을 등록 또는 업데이트
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 05. 25
	 * 	수정일 : 2022. 08. 26
	 * @throws Exception 
	 */
//	@PostMapping(value = {"/app/user/insertUserMeasureValue.do"})
	@PostMapping(value = {"/premium/user/insertUserMeasureValue.do"})
	@ResponseBody
	public HashMap<String,Object> insertUserMeasureValue(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {		
		logger.debug("========== TeethController ========== insertUserMeasureValue.do ==========");
		
		String measurerId = null;
		String userId = null;
		String userNo = null;
		String userAuthToken = null;

		// 32개 치아 측정 값 
		int[] teethValue = new int[32];
		
		int cavityCaution = 0;
	    int cavityDanger = 0;
			    
		// 정상 수치 개수
		int cavityNormalCnt = 0;
		// 주의 수치 개수
		int cavityCautionCnt = 0;
	    // 충치 수치 개수
	    int cavityDangerCnt = 0;
		
	    // 데이터 존재 유무 
		int isExistRow = 0;
		
		HashMap<String,Object> hm = new HashMap<String,Object>();

		HashMap<String,Integer> cavityLevel = new HashMap<String,Integer>();

		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		// 오늘 날짜 구하기 (SYSDATE)
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String sysDate = now.format(formatter);
		
		// 측정인 아이디
		measurerId= (String)paramMap.get("measurerId");
		userId= (String)paramMap.get("userId");
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		if(userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}
		
		
		for (int i = 0; i < teethValue.length + 1; i++) {
			if (i == 0) {
				teethValue[i] = Integer.parseInt((String) paramMap.get("t0" + (i + 1)));
				i++;
			} else if (i < 10) {
				teethValue[i - 1] = Integer.parseInt((String) paramMap.get("t0" + i));
			} else {
				teethValue[i - 1] = Integer.parseInt((String) paramMap.get("t" + i));
			}
		}
		
		
		// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
		cavityLevel = teethService.selectCavityLevel();
		
		cavityCaution = cavityLevel.get("cavityCaution");
		cavityDanger = cavityLevel.get("cavityDanger");
		
		for (int i = 0; i < teethValue.length; i++) {
			if (teethValue[i] < cavityCaution) { // 12 이하일 경우 정상 치아
				cavityNormalCnt++;
			} else if (teethValue[i] >= cavityCaution || teethValue[i] < cavityDanger) { // 13이상 19이하일 경우 충치 상태 주의
				cavityCautionCnt++;
			} else {
				cavityDangerCnt++;
			}
		}
		
		userNo = (String)paramMap.get("userNo");
		
		userAuthToken = request.getHeader("Authorization");
		// TOKEN 검증
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if(tokenValidation1) {
			try {
				
				teethMeasureVO.setT01(teethValue[0]); teethMeasureVO.setT02(teethValue[1]); teethMeasureVO.setT03(teethValue[2]); 
				teethMeasureVO.setT04(teethValue[3]); teethMeasureVO.setT05(teethValue[4]); teethMeasureVO.setT06(teethValue[5]); 
				teethMeasureVO.setT07(teethValue[6]); teethMeasureVO.setT08(teethValue[7]); teethMeasureVO.setT09(teethValue[8]);
				teethMeasureVO.setT10(teethValue[9]); teethMeasureVO.setT11(teethValue[10]); teethMeasureVO.setT12(teethValue[11]);
				teethMeasureVO.setT13(teethValue[12]); teethMeasureVO.setT14(teethValue[13]); teethMeasureVO.setT15(teethValue[14]);
				teethMeasureVO.setT16(teethValue[15]); teethMeasureVO.setT17(teethValue[16]); teethMeasureVO.setT18(teethValue[17]); 
				teethMeasureVO.setT19(teethValue[18]); teethMeasureVO.setT20(teethValue[29]); teethMeasureVO.setT21(teethValue[20]);
				teethMeasureVO.setT22(teethValue[21]); teethMeasureVO.setT23(teethValue[22]); teethMeasureVO.setT24(teethValue[23]); 
				teethMeasureVO.setT25(teethValue[24]); teethMeasureVO.setT26(teethValue[25]); teethMeasureVO.setT27(teethValue[26]);
				teethMeasureVO.setT28(teethValue[27]); teethMeasureVO.setT29(teethValue[28]); teethMeasureVO.setT30(teethValue[29]);
				teethMeasureVO.setT31(teethValue[30]); teethMeasureVO.setT32(teethValue[31]);

				teethMeasureVO.setUserId(userId);
				teethMeasureVO.setUserNo(userNo);
				teethMeasureVO.setMeasureDt(sysDate);
				teethMeasureVO.setMeasurerId(measurerId);
				teethMeasureVO.setCavityNormal(cavityNormalCnt);
				teethMeasureVO.setCavityCaution(cavityCautionCnt);
				teethMeasureVO.setCavityDanger(cavityDangerCnt);
				
				
				// 회원 치아 측정 값을 저장하기 위해 현재 회원이 측정한 측정 값이 오늘 데이터인지 확인 후 값 반환(0 : 오늘X / 1: 오늘)
				isExistRow = teethService.isExistSysDateRow(teethMeasureVO);
				if(isExistRow == 0){ // 0일 경우는 Database에 값이 없는 경우 ::: INSERT
					teethService.insertUserTeethMeasureValue(teethMeasureVO);
				}else { // 1이상일 경우 Database에 값이 있는 경우 ::: UPDATE
					teethService.updateUserTeethMeasureValue(teethMeasureVO);
				}
				
				hm.put("code", "000");
				hm.put("msg", "Success");
				
				
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "Server Error");
				e.printStackTrace();
			}
		}else {
			hm.put("code", "400");
			hm.put("msg", "The token is not valid.");
		}
		return hm;
	}
	
	
	
	
	/**
	 * 기능   : 회원의 치아 상태 값 조회
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 5. 20
	 * 수정일 : 2022-07-14
	 */
//	@PostMapping(value = {"/app/user/selectUserTeethInfo.do"})
	@PostMapping(value = {"/premium/user/selectUserTeethInfo.do"})
	@ResponseBody
	public HashMap<String,Object> selectUserTeethInfo(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) {
		
		logger.debug("========== TeethController ========== selectUserTeethInfo.do ==========");
		
		String userId = null;
		String userNo = null;
		String userAuthToken = null;
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		List<TeethInfoVO> userTeethInfo = new ArrayList<TeethInfoVO>();
		UserVO userVO = new UserVO();
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId= (String)paramMap.get("userId");
		if(userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}
		
		userNo = (String)paramMap.get("userNo");
		userAuthToken = request.getHeader("Authorization");
		// JWT TOKEN 검증
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if(tokenValidation1) {

			try {
				userVO.setUserId((String)paramMap.get("userId"));
				userVO.setUserNo((String)paramMap.get("userNo"));
				// 회원의 치아 상태 값 조회
				userTeethInfo = teethService.selectUserTeethInfo(userVO);
				
				hm.put("userTeethInfo", userTeethInfo);
				hm.put("code", "000");
				hm.put("msg", "Success");
				
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "Server Error");
				e.printStackTrace();
			}
			
		}else {
			hm.put("code", "400");
			hm.put("msg", "The token is not valid.");
		}
		return hm;
	}
	
	
	
	
	/**
	 * 기능   : 회원의 치아 측정 값 조회
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 5. 16
	 *       	 기간  조회 (startDt, endDt)
	 *       
	 *       
	 *       
	 *       여기서부터 봐야함
	 *       
	 */
//	@PostMapping(value = {"/app/user/selectUserTeethMeasureValue.do"})
	@PostMapping(value = {"/premium/user/selectUserTeethMeasureValue.do"})
	@ResponseBody
	public HashMap<String,Object> selectUserMeasureValue(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
		logger.debug("========== TeethController ========== selectUserTeethMeasureValue.do ==========");
		
		String userNo = null;
		String userId = null;
		String userAuthToken = null;
		String startDt = null;
		String endDt = null;
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		List<TeethMeasureVO> userTeethValues = new ArrayList<TeethMeasureVO>();
		TeethMeasureVO teethInfoVO = new TeethMeasureVO();

		userNo = (String)paramMap.get("userNo");		
		userId = (String)paramMap.get("userId");
		startDt = (String)paramMap.get("startDt");
		endDt = (String)paramMap.get("endDt");

		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId= (String)paramMap.get("userId");
		if(userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}
		
		
		userAuthToken = request.getHeader("Authorization");
		// TOKEN 검증
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if(tokenValidation1) {
			try {
				
				teethInfoVO.setUserNo(userNo);
				teethInfoVO.setUserId(userId);
				teethInfoVO.setStartDt(startDt);
				teethInfoVO.setEndDt(endDt);
				
				// 회원의 치아 측정 값 조회 (기간)
				userTeethValues = teethService.selectUserTeethMeasureValue(teethInfoVO);
				
				hm.put("userTeethValues", userTeethValues);
				hm.put("code", "000");
				hm.put("msg", "Success");
				
			} catch (Exception e) {
				
				hm.put("code", "500");
				hm.put("msg", "Server Error");
				e.printStackTrace();
				
			}
		}else {
			
			hm.put("code", "400");
			hm.put("msg", "The token is not valid.");
			
		}
		
		return hm;
	}
	
	
	
	/**
	 * 기능   : 회원 치아 개별 측정 값 조회 (기간)
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 5. 26
 	 *		      기간  조회 (startDt, endDt)
	 */
	@PostMapping(value = {"/premium/user/selectUserToothMeasureValue.do"})
	@ResponseBody
	public HashMap<String,Object> selectUserMeasureToothValue(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
		logger.debug("========== TeethController ========== selectUserToothMeasureValue.do ==========");
		
		int isExistRow = 0;
		String userId = null;
		String userNo = null;
		String userAuthToken = null;
		String toothNo = null;
		String toothValue = null;
		String startDt = null;
		String endDt = null;

		// 32개 치아 측정 값 
		int[] teethValue = new int[32];
		
		Integer cavityCaution = 0;
		Integer cavityDanger = 0;
			    
		// 정상 수치 0~12 이하 갯수
		int cavityNormalCnt = 0;
		// 주의 수치 13~29 이하 갯수
		int cavityCautionCnt = 0;
	    // 위험 수치 30이상 갯수
	    int cavityDangerCnt = 0;
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		List<ToothMeasureVO> userToothValues = new ArrayList<ToothMeasureVO>();
		List<TeethMeasureVO> userTeethValues = new ArrayList<TeethMeasureVO>();
		
		ToothMeasureVO toothMeasureVO = new ToothMeasureVO();
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		HashMap<String,Integer> cavityLevel = new HashMap<String,Integer>();
		
		// 오늘 날짜 구하기 (SYSDATE)
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String sysDate = now.format(formatter);
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId= (String)paramMap.get("userId");
		if(userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}
		
		userAuthToken = request.getHeader("Authorization");
		
		// TOKEN 검증
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if(tokenValidation1) {
			
			try {
				userNo = (String)paramMap.get("userNo");
				// 치아 번호
				toothNo = (String)paramMap.get("toothNo");
				// 치아 측정 값
				toothValue = (String)paramMap.get("toothValue");
				// 검색기간 (시작일)
				startDt = (String)paramMap.get("startDt");
				// 검색기간 (종료일)
				endDt = (String)paramMap.get("endDt");
				
				
				toothMeasureVO.setUserId(userId);
				toothMeasureVO.setUserNo(userNo);
				toothMeasureVO.setStartDt(startDt);
				toothMeasureVO.setEndDt(endDt);
				toothMeasureVO.setToothNo(toothNo);
				toothMeasureVO.setMeasureDt(sysDate);
				
				
				// 회원의 32개 치아 즉정 데이터 조회(기간)
				teethMeasureVO.setUserNo(userNo);
				teethMeasureVO.setUserId(userId);
				teethMeasureVO.setStartDt(startDt);
				teethMeasureVO.setEndDt(endDt);
				teethMeasureVO.setMeasureDt(sysDate);
				
			
				isExistRow = teethService.isExistSysDateRow(teethMeasureVO);
				
				if(toothValue != null && !toothValue.equals("") && !toothValue.equals(" ")) { // 값이 있으면 업데이트 

					// 회원의 치아 측정 값 확인 >>> 오늘 날짜(측정날짜)의 값이 없을 경우 INSERT
					toothMeasureVO.setToothValue(Integer.parseInt(toothValue));
					if(isExistRow == 0) {
						teethService.insertUserToothMeasureValue(toothMeasureVO);
					}else {
						teethService.updateUserToothMeasureValue(toothMeasureVO);
					}
				}else {
					if(isExistRow == 0){ // 0일 경우는 Database에 값이 없는 경우 ::: INSERT
						
						// 메모 UPDATE
						List<DiagnosisVO> diagList = diagnosisService.selectDiagDept2List();
						String diagCd = "";
						for(int i=0; i<diagList.size(); i++) {
							if(i == diagList.size()-1) {
								diagCd += diagList.get(i).getDiagCd()+":"+diagList.get(i).getDiagNo()+":0";
							}else {
								diagCd += diagList.get(i).getDiagCd()+":"+diagList.get(i).getDiagNo()+":0|";
							}
						}
						teethMeasureVO.setDiagCd(diagCd);
						// 치아 측정 값 INSERT
						teethService.insertUserTeethMeasureValue(teethMeasureVO);
					}
				}
				
				// 회원 치아 개별 측정 값 조회 (기간) :: 오늘 날짜로 검색 했을 때 0이 나올 경우, 지난 값을 가져와야됨
				userToothValues = teethService.selectUserToothMeasureValue(toothMeasureVO);
				
				// 모든 치아에 대한 조회 값 (오늘의 값이 없을 경우 최근 값으로 불러와야함)
				userTeethValues = teethService.selectUserTeethMeasureValue(teethMeasureVO);
				
				// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
				cavityLevel = teethService.selectCavityLevel();
				
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
					if (teethValue[i] < cavityCaution) { 														// 12이하일 경우 정상 치아
						cavityNormalCnt++;
					} else if (teethValue[i] >= cavityCaution && teethValue[i] < cavityDanger) {	// 13이상 19이하일 경우 충치 상태 주의
						cavityCaution++;
					} else if (teethValue[i] > cavityDanger) {
						cavityDanger++;
					}
				}

				teethMeasureVO.setCavityNormal(cavityNormalCnt);
				teethMeasureVO.setCavityCaution(cavityCautionCnt);
				teethMeasureVO.setCavityDanger(cavityDangerCnt);
				
				userTeethValues.get(0).setCavityDanger(cavityDangerCnt);
				
				// ST_STUDENT_USER_DETAIL 테이블에 CavityCnt 업데이트 (최근)
				teethService.updateUserCavityCnt(teethMeasureVO);
				
				// 측정 날짜 별 충치 갯수 업데이트 -- 메모 추가
				teethService.updateUserCavityCntByMeasureDt(teethMeasureVO);
				
				hm.put("userToothValues", userToothValues);
				hm.put("userTeethValues", userTeethValues);
				hm.put("code", "000");
				hm.put("msg", "Success");
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "Server Error");
				e.printStackTrace();
			}
		}else {
			hm.put("code", "400");
			hm.put("msg", "The token is not valid.");
		}
		return hm;
	}
	
	
	
	/**
     * 기능   : 회원의 진단 정보 업데이트
     * 작성자 : 정주현 
     * 작성일 : 2022. 11. 25
     * 수정일 : 2022. 11. 29
     */
	@PostMapping(value = {"/premium/user/updateDiagCd.do"})
	@ResponseBody
	public HashMap<String, Object> updateDiagCd(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
	
	    String userId = (String)paramMap.get("userId");
	    String measureDt = (String)paramMap.get("measureDt");
	    String diagCd = (String)paramMap.get("diagCd");
	    String diagCd2 = null;
	    String[] diagArray = null;
	    String tmpDiagCd = null;
	    
	    //A:01:1|A:02:1
	    if("".equals(measureDt) || measureDt == null) {
			// 오늘 날짜 구하기 (SYSDATE)
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			measureDt = now.format(formatter);
	    }
	    
	    HashMap<String, Object> hm = new HashMap<String, Object>();
	    TeethMeasureVO teethMeasureVO = new TeethMeasureVO();
	    
        try {

        	if(diagCd.contains("|")) { // 두 개 이상의 파라미터 처리
        		String[] tmpDiagArray = diagCd.split("\\|");
        		for(int i=0; i < tmpDiagArray.length; i++) {
        			diagArray = tmpDiagArray[i].split(":");
    	        	diagCd2 = diagArray[0]+":"+diagArray[1]+":";
    	        	// 아이디와 측정일 기준으로 메모를 조회한다
    	        	teethMeasureVO = teethService.selectDiagCd(userId, measureDt);
    	        	 tmpDiagCd = teethMeasureVO.getDiagCd();
//    	        	tmpDiagCd = "A:01:0|A:02:0|B:01:0|B:02:0|B:03:0|B:04:0|B:05:0|B:06:0|C:01:0|C:01:0|C:01:0|C:01:0|D:01:0|D:02:0|D:03:0|D:04:0|D:05:0|D:06:0|D:07:0|D:08:0|D:09:0|D:10:0|D:11:0|D:12:0|D:13:0|D:14:0|D:15:0|D:16:0|D:17:0|D:18:0|E:01:0|E:02:0|E:03:0";
    	        	// 받은 메모를 업데이트 해준다.
    	        	
    	        	StringBuffer sb = new StringBuffer();
    	        	sb.append(tmpDiagCd);
    	        	
    	        	tmpDiagCd = sb.replace(tmpDiagCd.indexOf(diagCd2), tmpDiagCd.indexOf(diagCd2)+6, tmpDiagArray[i]).toString();
    	        	teethService.updateDiagCd(userId, tmpDiagCd, measureDt);
        		}
        	}else {
	        	// 파라미터로 받은 메모 (A:01:1)
	        	diagArray = diagCd.split(":");
	        	diagCd2 = diagArray[0]+":"+diagArray[1]+":";
	        	// 아이디와 측정일 기준으로 메모를 조회한다
	        	teethMeasureVO = teethService.selectDiagCd(userId, measureDt);
	        	tmpDiagCd = teethMeasureVO.getDiagCd();
	        	// 받은 메모를 업데이트 해준다.
	        	
	        	StringBuffer sb = new StringBuffer();
	        	sb.append(tmpDiagCd);
	        	
	        	tmpDiagCd = sb.replace(tmpDiagCd.indexOf(diagCd2), tmpDiagCd.indexOf(diagCd2)+6, diagCd).toString();
	        	teethService.updateDiagCd(userId, tmpDiagCd, measureDt);
        	}
        	
        }catch (Exception e) {
            hm.put("code", "500");
            hm.put("msg", "Server error");
            e.printStackTrace();
            
        }
	    hm.put("code", "000");
	    hm.put("msg", "Success");
	    hm.put("diagInfo", teethMeasureVO);
	    return hm;
	
	}
	
	
	
	
	/**
     * 기능   : 회원의 메모(비고) 내용 업데이트
     * 작성자 : 정주현 
     * 작성일 : 2022. 11. 25
     * 수정일 : 2022. 11. 25
     */
	@PostMapping(value = {"/premium/user/updateMemo.do"})
	@ResponseBody
	public HashMap<String, Object> updateMemo(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
	
	    String userId = (String)paramMap.get("userId");
	    String memo = (String)paramMap.get("memo");
	    
		// 오늘 날짜 구하기 (SYSDATE)
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String measureDt = now.format(formatter);
	    
	    HashMap<String, Object> hm = new HashMap<String, Object>();
	    TeethMeasureVO teethMeasureVO = new TeethMeasureVO();
	    
        try {
        	
        	if(!"ÿ".equals(memo)) {
        		// 파라미터로 받은 MEMO 정보를 업데이트
            	teethService.updateMemo(userId, memo, measureDt);
        	}

        	teethMeasureVO = teethService.selectMemo(userId, measureDt);
        	
        	
        	
        }catch (Exception e) {
            hm.put("code", "500");
            hm.put("msg", "Server error");
            e.printStackTrace();
            
        }
	    hm.put("code", "000");
	    hm.put("msg", "Success");
	    hm.put("memoInfo", teethMeasureVO);
	    return hm;
	
	}
	
	
	
	
	
	/**
     * 기능   : 진단 정보 조회
     * 작성자 : 정주현 
     * 작성일 : 2022. 11. 25
     * 수정일 : 2022. 11. 25
     */
	@PostMapping(value = {"/premium/user/selectDiagnosisInfo.do"})
	@ResponseBody
	public HashMap<String, Object> selectDiagnosisInfo(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
	
		List<DiagnosisVO> diagDept1List = new ArrayList<DiagnosisVO>();
		List<DiagnosisVO> diagDept2List = new ArrayList<DiagnosisVO>();
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		
		try {
			// ST_DIAG_DEPT1 리스트 조회 (최상위 진단 정보 : ex 양치불량)
			diagDept1List = diagnosisService.selectDiagDept1List();
			// ST_DIAG_DEPT2 리스트 조회 (중위 진단 정보 : ex 치태)
			diagDept2List = diagnosisService.selectDiagDept2List();
		} catch (Exception e) {
			hm.put("code", "500");
            hm.put("msg", "Server error");
            e.printStackTrace();
		}
		
	    hm.put("code", "000");
	    hm.put("msg", "Success");
	    
	    hm.put("DiagDept1List", diagDept1List);
	    hm.put("DiagDept2List", diagDept2List);
		
		return hm;
	
	}
	
	
	
	
	 /**
     * 기능   : 회원의 측정 상태(IS_MEASURING) 목록 조회
     * 작성자 : 정주현 
     * 작성일 : 2022. 06. 30
     * 수정일 : 2022. 08. 04
     */
	@PostMapping(value = {"/premium/user/selectUserIsMeasuringValue.do"})
	@ResponseBody
	public HashMap<String, Object> selectUserIsMeasuringList(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
	
	    String userId = (String)paramMap.get("userId");
	    String isMeasuring = (String)paramMap.get("isMeasuring");
	    
	    HashMap<String, Object> hm = new HashMap<String, Object>();
	    List<UserVO> userList = null;
	    UserVO userVO = new UserVO();
	    
	    if(userId != null && !userId.equals("") && isMeasuring != null && !isMeasuring.equals("")) {
	        userVO.setUserId(userId);
	        userList = teethService.selectStUserIsMeasuring(userVO);
	        
	        //동일한 값이 들어오면 db업데이트 X
	        if(!isMeasuring.equals(userVO.getIsMeasuring())) {
	            userVO.setIsMeasuring(isMeasuring);
	            teethService.updateStUserIsMeasuring(userVO);
	        }
	    }else {
	        try {
	            userList = teethService.selectStUserIsMeasuring(userVO);
	        }catch (Exception e) {
	            hm.put("code", "500");
	            hm.put("msg", "Server error");
	            e.printStackTrace();
	            
	        }
	    }
	    hm.put("code", "000");
	    hm.put("msg", "Success");
	    hm.put("isMeasuring", isMeasuring);
	    hm.put("userList", userList);
	
	    return hm;
	
	}
}
