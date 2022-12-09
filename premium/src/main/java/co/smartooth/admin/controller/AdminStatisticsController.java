package co.smartooth.admin.controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.smartooth.admin.service.AdminMemoService;
import co.smartooth.admin.service.AdminTeethService;
import co.smartooth.admin.service.AdminUserService;
import co.smartooth.admin.service.impl.AdminDiagnosisService;
import co.smartooth.admin.vo.AdminLocationVO;
import co.smartooth.admin.vo.AdminMemoVO;
import co.smartooth.admin.vo.AdminTeethMeasureVO;
import co.smartooth.admin.vo.AdminUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 08. 03
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class AdminStatisticsController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	

	@Autowired(required = false)
	private AdminUserService adminUserService;

	
	@Autowired(required = false)
	private AdminTeethService adminTeethService;

	
	@Autowired(required = false)
	private AdminDiagnosisService adminDiagnosisService; 
	
	
	@Autowired(required = false)
	private AdminMemoService adminMemoService;
	
	

	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 10. 27
	 * 기능   : 치아 모니터링 페이지 return
	 */
	@RequestMapping(value = {"/premium/admin/statistics/selectUserMeasureList"})
	public String selectUserMeasureList(HttpServletRequest request, HttpSession session, Model model) {
		
		@SuppressWarnings("unchecked")
		AdminUserVO sessionList = (AdminUserVO)session.getAttribute("userInfo");
		if(sessionList == null ) {
			return "redirect:/premium/admin/login";
		}
		
		String userId = sessionList.getUserId();
		
		model.addAttribute("userInfo", sessionList);
		
		return "/premium/admin/statistics/selectUserMeasureList";
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/premium/admin/user/selectUserMeasureList.do"})
	public @ResponseBody String AjaxSelectUserListforSheet(HttpServletRequest request, HttpSession session, Model model, @RequestParam Map<String, String> paramMap) throws Exception {
		
		@SuppressWarnings("unchecked")
		AdminUserVO sessionList = (AdminUserVO)session.getAttribute("userInfo");
		if(sessionList == null ) {
			return "redirect:/premium/admin/login";
		}
		
		List<HashMap<String,Object>> userList = new ArrayList<HashMap<String,Object>>();

		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		String searchData = paramMap.get("searchData");
		String searchType = paramMap.get("searchType");
		
		
		// 사용자 목록 조회 (관리자 제외) - 반 아이디 제외 
		userList = adminUserService.selectUserMeasureList(searchType, searchData);
		
		for(int i=0; i< userList.size(); i++) {
			
			JSONObject tmpObj = new JSONObject();
			
			tmpObj.put("userName", userList.get(i).get("USER_NAME"));
			tmpObj.put("userId", userList.get(i).get("USER_ID"));
			tmpObj.put("userType", userList.get(i).get("USER_TYPE"));
			tmpObj.put("schoolName", userList.get(i).get("SCHOOL_NAME"));
			tmpObj.put("className", userList.get(i).get("CLASS_NAME"));
			tmpObj.put("measureDt", userList.get(i).get("MEASURE_DT"));
			tmpObj.put("schoolCode", userList.get(i).get("SCHOOL_CODE"));
			tmpObj.put("classCode", userList.get(i).get("CLASS_CODE"));
			jsonArray.add(tmpObj);
			
		}
		
		jsonObject.put("rows", jsonArray);
		
		return jsonObject.toJSONString(); 
		
	}
	
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 28
	 * 기능   : 관리자페이지 - 치아 모니터링 - 결과지 화면 조회
	 */
	@RequestMapping(value = {"/premium/admin/statistics/diagnosisSheetPopup"})
	public String diagnosisSheetPopup(HttpServletRequest request, HttpSession session, Model model) {
		
		@SuppressWarnings("unchecked")
		AdminUserVO sessionList = (AdminUserVO)session.getAttribute("userInfo");
		if(sessionList == null ) {
			// 세션 만료 시 창닫기 위한 파라미터를 세션에 담아서 전송
			model.addAttribute("session", "expired");
			return "/premium/admin/statistics/diagnosisSheet";
		}
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");;
		String measureDt = request.getParameter("measureDt");
		String schoolName = request.getParameter("schoolName");
		String className = request.getParameter("className");
		String tmpDiagCd = null;
		String diagCd = null;
		String[] diagCdArray;
		String[] diagCdTitleArray;
		String diagDescript = "";
		String tmpDiagDescript = null; 
		String tmpMemo = null;
		
		int[] teethValue = new int[20];
		Integer cavityCaution = 0;
		Integer cavityDanger = 0;
		

		HashMap<String,Integer> cavityLevel = new HashMap<String,Integer>();
		AdminTeethMeasureVO adminTeethMeasureVO = new AdminTeethMeasureVO();
		List<AdminTeethMeasureVO> userTeethValues = new ArrayList<AdminTeethMeasureVO>();
		
		
		// 회원의 치아 정보 및 기타 필요한 정보들 가져와야함
		try {
			
			// 회원 치아 측정 값 목록 조회
			adminTeethMeasureVO = adminTeethService.selectUserMeasureValueList(userId, measureDt);
			// 파라미터로 받은 이름을 VO에 입력
			adminTeethMeasureVO.setUserName(userName);
			
			tmpDiagDescript = adminTeethMeasureVO.getDiagDescript();
			tmpDiagCd = adminTeethMeasureVO.getDiagCd();
			
			if(tmpDiagDescript == null || "".equals(tmpDiagDescript)) {
				if(tmpDiagCd != null && !"".equals(tmpDiagCd)) {
					diagCdArray = tmpDiagCd.split("\\|");
					diagDescript = "";
					
					for(int i=0; i < diagCdArray.length; i++) {
						
						String[] diagSplit = diagCdArray[i].split(":");
						if("1".equals(diagSplit[2])) { // 진단 값이 1일 경우 조회해서 문자열을 붙여준다.
							diagCd = diagSplit[0]+diagSplit[1];
							// DIAG_DESCRIPT 테이블의 값을 조회해서 붙여넣기
							if(i >= 0) {
								diagDescript += adminDiagnosisService.selectDiagDescript(diagCd)+"<br/>";
							}else {
								diagDescript += adminDiagnosisService.selectDiagDescript(diagCd);
							}
						}
					}
					
					diagDescript = diagDescript.replaceAll("			", "");
				}
				adminTeethService.updateDiagDescript(userId, measureDt, diagDescript);
				adminTeethMeasureVO.setDiagDescript(diagDescript);
			}else {
				adminTeethService.updateDiagDescript(userId, measureDt, tmpDiagDescript);
				adminTeethMeasureVO.setDiagDescript(tmpDiagDescript);
			}
			
			// 진단 제목
			if(tmpDiagCd != null && !"".equals(tmpDiagCd)) {
				diagCdTitleArray = tmpDiagCd.split("\\|");
				for(int i=0; i < diagCdTitleArray.length; i++) {
					// A:01:01
					String diagTitle = adminTeethService.selectDiagTitle(diagCdTitleArray[i]);
					if(diagTitle !=null) {
						adminTeethMeasureVO.setDiagTitle(diagTitle);
						break;
					}
				}
			}
			//diagTitle
			
			if(adminTeethMeasureVO.getMemo() != null && !adminTeethMeasureVO.getMemo().equals("")) {
				tmpMemo = adminTeethMeasureVO.getMemo();
				// 메모에 개행문자가 있을 경우 <br/> 치환
				if(tmpMemo.endsWith("\n")) {
					adminTeethMeasureVO.setMemo(tmpMemo.substring(0, tmpMemo.length()-2).replace("\n", "<br/>"));
				}
			}else {
				adminTeethMeasureVO.setMemo("");
			}
			
			// 정상 수치 0~9 사이의 치아 개수
			int cavityNormalCnt = 0;
			// 주의 수치 10~19 사이의 치아 개수
			int cavityCautionCnt = 0;
		    // 위험 수치 20이상의 치아 개수
		    int cavityDangerCnt = 0;
				
		    // userMeasureData
		    
			// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
			cavityLevel = adminTeethService.selectCavityLevel();
			
			teethValue[0] = adminTeethMeasureVO.getT04();
			teethValue[1]  = adminTeethMeasureVO.getT05();
			teethValue[2]  = adminTeethMeasureVO.getT06();
			teethValue[3]  = adminTeethMeasureVO.getT07();
			teethValue[4]  = adminTeethMeasureVO.getT08();
			teethValue[5]  = adminTeethMeasureVO.getT09();
			teethValue[6]  = adminTeethMeasureVO.getT10();
			teethValue[7]  = adminTeethMeasureVO.getT11();
			teethValue[8]  = adminTeethMeasureVO.getT12();
			teethValue[9]  = adminTeethMeasureVO.getT13();
			teethValue[10] = adminTeethMeasureVO.getT20();
			teethValue[11] = adminTeethMeasureVO.getT21();
			teethValue[12] = adminTeethMeasureVO.getT22();
			teethValue[13] = adminTeethMeasureVO.getT23();
			teethValue[14] = adminTeethMeasureVO.getT24();
			teethValue[15] = adminTeethMeasureVO.getT25();
			teethValue[16] = adminTeethMeasureVO.getT26();
			teethValue[17] = adminTeethMeasureVO.getT27();
			teethValue[18] = adminTeethMeasureVO.getT28();
			teethValue[19] = adminTeethMeasureVO.getT29();

			cavityCaution = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
			cavityDanger = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

			for (int j = 0; j < teethValue.length; j++) {
				
				if (teethValue[j] < cavityCaution) { 														// 0~12 정상 치아
					cavityNormalCnt++;
				} else if (teethValue[j] >= cavityCaution && teethValue[j] < cavityDanger) {	// 13~24 주의
					cavityCautionCnt++;
				} else if (teethValue[j] >= cavityDanger) {	// 25~ 충치
					cavityDangerCnt++;
				}
			}
			
			adminTeethMeasureVO.setCavityNormal(cavityNormalCnt);
			adminTeethMeasureVO.setCavityCaution(cavityCautionCnt);
			adminTeethMeasureVO.setCavityDanger(cavityDangerCnt);
				
			// ST_STUDENT_USER_DETAIL 테이블에 CavityCnt 업데이트
			adminTeethService.updateUserCavityCntByMeasureDt(adminTeethMeasureVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("dataList", adminTeethMeasureVO);
		model.addAttribute("schoolName", schoolName);
		model.addAttribute("className", className);
		model.addAttribute("caution", cavityCaution);
		model.addAttribute("danger", cavityDanger);
		
		
		return "/premium/admin/statistics/diagnosisSheet";

	}
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 12. 02
	 * 기능   : diag-title 업데이트
	 */
	@PostMapping(value = {"/premium/admin/statistics/updateDiagTitle.do"})
	public String AjaxUpdateDiagTitle(@RequestBody HashMap<String, Object> paramMap) throws Exception {
		
		String userId = (String)paramMap.get("userId");
		String measureDt = (String)paramMap.get("measureDt");
		String diagTitle = (String)paramMap.get("diagTitle");
		
		JSONObject jsonObject = new JSONObject();
		
		// 타이틀 저장
		adminTeethService.updateDiagTitle(userId, measureDt, diagTitle);
		
		jsonObject.put("success", "success");
		return jsonObject.toJSONString(); 
		
	}
	
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 12. 02
	 * 기능   : Contents(descript) 업데이트
	 */
	@PostMapping(value = {"/premium/admin/statistics/updateDiagDescript.do"})
	@ResponseBody
	public String AjaxUpdateDiagDescript(@RequestBody HashMap<String, Object> paramMap) throws Exception {
		
		String userId = (String)paramMap.get("userId");
		String measureDt = (String)paramMap.get("measureDt");
		String diagDescript = (String)paramMap.get("diagDescript");
		
		// 타이틀 저장
		adminTeethService.updateDiagDescript(userId, measureDt, diagDescript);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", "success");
		return jsonObject.toJSONString(); 
		
	}
	
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 12. 02
	 * 기능   : Contents(descript) 업데이트
	 */
	@PostMapping(value = {"/premium/admin/statistics/updateMemo.do"})
	@ResponseBody
	public String AjaxUpdateMemo(@RequestBody HashMap<String, Object> paramMap) throws Exception {
		
		String userId = (String)paramMap.get("userId");
		String measureDt = (String)paramMap.get("measureDt");
		String memo = (String)paramMap.get("memo");
		
		// 타이틀 저장
		adminTeethService.updateMemo(userId, measureDt, memo);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", "success");
		return jsonObject.toJSONString(); 
		
	}
	
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 10. 27
	 * 기능   : 진단 결과 수정 페이지 return
	 */
	@RequestMapping(value = {"/premium/admin/statistics/updateUserDiagnosis"})
	public String updateUserDiagnosis(HttpServletRequest request, HttpSession session, Model model) {
		
		@SuppressWarnings("unchecked")
		AdminUserVO sessionList = (AdminUserVO)session.getAttribute("userInfo");
		if(sessionList == null ) {
			return "redirect:/premium/admin/login";
		}
		
		String userId = sessionList.getUserId();
		
		model.addAttribute("userInfo", sessionList);
		
		return "/premium/admin/statistics/updateUserDiagnosis";
		
	}
	
	
	
	
	
	
	
	
}
