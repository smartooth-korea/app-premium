package co.smartooth.web.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import co.smartooth.app.service.TeethService;
import co.smartooth.app.vo.TeethMeasureVO;
import co.smartooth.web.service.WebMemoService;
import co.smartooth.web.service.WebTeethService;
import co.smartooth.web.service.WebUserService;
import co.smartooth.web.vo.WebMemoVO;
import co.smartooth.web.vo.WebTeethMeasureVO;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 07
 * 수정일 : 
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class WebUserRegistController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	

	@Autowired(required = false)
	private WebUserService webUserService;
	
	@Autowired(required = false)
	private WebTeethService webTeethService;

	
	@Autowired(required = false)
	private WebMemoService webMemoService;
	
	
	
	
//	/**
//	 * 작성자 : 정주현 
//	 * 작성일 : 2022. 10. 27
//	 * 기능   : 결과지 웹 메인 화면 접속
//	 */
//	@RequestMapping(value = {"/premium/statistics/main.do"})
//	public String main(HttpServletRequest request, HttpSession session, Model model) {
//		
//		List<WebUserVO> sessionList = (List<WebUserVO>)session.getAttribute("userInfo");
//		if(sessionList == null ) {
//			return "/premium/statistics/login/loginForm";
//		}
//		
//		String userId = null;
//		String startDt = null;
//		String endDt = null;
//		
//		String[] memoCd = null;
//		String tmpMemoCd = null;
//		
//		String memo = "";
//		
//		List<WebTeethMeasureVO> dataList = new ArrayList<WebTeethMeasureVO>();
//		WebMemoVO webMemoVO = new WebMemoVO();
//		
//		
//		userId = sessionList.get(0).getUserId();
//		
//		
//		// 오늘 날짜 구하기 (SYSDATE) - 마지막 날
//		LocalDate now = LocalDate.now();
//		DateTimeFormatter endDtformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		endDt = now.format(endDtformatter);
//		
//		// 검색 - 시작 날짜
//		now = now.minusYears(1);
//		DateTimeFormatter startDtformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		startDt = now.format(startDtformatter);
//		
//		
//		// 회원의 치아 정보 및 기타 필요한 정보들 가져와야함
//		try {
//			
//			
//			// 회원 치아 측정 값 목록 조회 (최근 3개)
//			dataList = webTeethService.selectUserMeasureValueList(userId, startDt, endDt);
//			
//			for (int i = 0; i < dataList.size(); i++) {
//
//				tmpMemoCd = dataList.get(i).getMemoCd();
//				if (tmpMemoCd != null) {
//					memoCd = tmpMemoCd.split(",");
//					for (int j = 0; j < memoCd.length; j++) {
//						// 메모 번호로 DB값 조회
//						webMemoVO.setMemoCd(memoCd[j]);
//						// 가져온 메모를 합쳐야함
//						memo += webMemoService.selectUserTeethMeasureMemo(webMemoVO);
//						memo += "<br/>";
//					}
//					dataList.get(i).setMemoTxt(memo);
//
//				}
//			}
//				
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		model.addAttribute("dataList", dataList);
//		
//		return "/premium/statistics/main";
//		
//	}
	
	
	
}
