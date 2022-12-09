package co.smartooth.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.web.vo.WebTeethMeasureVO;
import co.smartooth.admin.service.AdminTeethService;
import co.smartooth.admin.vo.AdminTeethMeasureVO;
import co.smartooth.admin.vo.AdminUserVO;
import co.smartooth.web.service.WebRegistService;
import co.smartooth.web.service.WebTeethService;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 12. 02
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class AdminUtilsController {

	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired(required = false)
	private AdminTeethService AdminTeethService;
	
	

	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 12. 02
	 * 기능   : 반 별 측정일 기준 회원 충치 개수 업데이트 페이지로 이동
	 */
	@RequestMapping(value = {"/premium/admin/statistics/updateCavityCnt"})
	public String main(HttpServletRequest request, HttpSession session, Model model) {
		
		return "/premium/admin/utils/updateCavityCnt";
		
	}
	
	
	
	
	/**
	 * 기능   : 반별 측정일 기준 회원 충치 개수 업데이트
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 12. 02
	 * 수정일 : 
	 */
	@PostMapping(value = {"/premium/admin/statistics/updateCavityCnt.do"})
	@ResponseBody
	public void updateCavityCntKids(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
		logger.debug("========== UtilController ========== updateCavityCnt.do ==========");
		
		String measureDt = null;
		String schoolCode = null;

		// 20개 치아 측정 값 
		int[] teethValue = new int[20];
		
		Integer cavityCaution = 0;
		Integer cavityDanger = 0;
			    
	    List<HashMap<String, Object>> userTeethValues = new ArrayList<HashMap<String, Object>>();
		AdminTeethMeasureVO adminTeethMeasureVO = new AdminTeethMeasureVO();
		HashMap<String,Integer> cavityLevel = new HashMap<String,Integer>();
		
		measureDt= (String)paramMap.get("measureDt");
		schoolCode= (String)paramMap.get("schoolCode");
			
		try {
			
			// 모든 치아에 대한 조회 값 (오늘의 값이 없을 경우 최근 값으로 불러와야함)
			userTeethValues = AdminTeethService.selectUserTeethMeasureValueByOrganCode(schoolCode, measureDt);
			
			for(int i=0; i < userTeethValues.size(); i++) {
				
				// 정상 수치 0~9 사이의 치아 개수
				int cavityNormalCnt = 0;
				// 주의 수치 10~19 사이의 치아 개수
				int cavityCautionCnt = 0;
			    // 위험 수치 20이상의 치아 개수
			    int cavityDangerCnt = 0;
				
				// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
				cavityLevel = AdminTeethService.selectCavityLevel();
				
				teethValue[0] = ((Short)userTeethValues.get(i).get("T51")).intValue();
				teethValue[1] = ((Short)userTeethValues.get(i).get("T52")).intValue();
				teethValue[2] = ((Short)userTeethValues.get(i).get("T53")).intValue();
				teethValue[3] = ((Short)userTeethValues.get(i).get("T54")).intValue();
				teethValue[4] = ((Short)userTeethValues.get(i).get("T55")).intValue();
				teethValue[5] = ((Short)userTeethValues.get(i).get("T61")).intValue();
				teethValue[6] = ((Short)userTeethValues.get(i).get("T62")).intValue();
				teethValue[7] = ((Short)userTeethValues.get(i).get("T63")).intValue();
				teethValue[8] = ((Short)userTeethValues.get(i).get("T64")).intValue();
				teethValue[9] = ((Short)userTeethValues.get(i).get("T65")).intValue();
				teethValue[10] = ((Short)userTeethValues.get(i).get("T71")).intValue();
				teethValue[11] = ((Short)userTeethValues.get(i).get("T72")).intValue();
				teethValue[12] = ((Short)userTeethValues.get(i).get("T73")).intValue();
				teethValue[13] = ((Short)userTeethValues.get(i).get("T74")).intValue();
				teethValue[14] = ((Short)userTeethValues.get(i).get("T75")).intValue();
				teethValue[15] = ((Short)userTeethValues.get(i).get("T81")).intValue();
				teethValue[16] = ((Short)userTeethValues.get(i).get("T82")).intValue();
				teethValue[17] = ((Short)userTeethValues.get(i).get("T83")).intValue();
				teethValue[18] = ((Short)userTeethValues.get(i).get("T84")).intValue();
				teethValue[19] = ((Short)userTeethValues.get(i).get("T85")).intValue();
				
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

				adminTeethMeasureVO.setUserId(userTeethValues.get(i).get("USER_ID").toString());
				adminTeethMeasureVO.setMeasureDt(userTeethValues.get(i).get("MEASURE_DT").toString());
				adminTeethMeasureVO.setCavityNormal(cavityNormalCnt);
				adminTeethMeasureVO.setCavityCaution(cavityCautionCnt);
				adminTeethMeasureVO.setCavityDanger(cavityDangerCnt);
				
				// ST_STUDENT_USER_DETAIL 테이블에 CavityCnt 업데이트
				AdminTeethService.updateUserCavityCntByMeasureDt(adminTeethMeasureVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
