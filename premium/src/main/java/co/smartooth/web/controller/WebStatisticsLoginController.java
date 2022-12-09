package co.smartooth.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.smartooth.web.service.WebAuthService;
import co.smartooth.web.service.WebLogService;
import co.smartooth.web.service.WebUserService;
import co.smartooth.app.utils.AES256Util;
import co.smartooth.web.vo.WebAuthVO;
import co.smartooth.web.vo.WebUserVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 07. 07
 * 현재 JSP로 개발 예정이므로 RestController는 사용하지 않음
 * 
 */

@Slf4j
@Controller
@PropertySource({ "classpath:application.yml" })
public class WebStatisticsLoginController {

	@Value("${loginUrl}")
    private String loginUrl;
	
	private static final Logger logger = LoggerFactory.getLogger(WebStatisticsLoginController.class);
	
	@Autowired(required = false)
	private WebAuthService webAuthService;

	@Autowired(required = false)
	private WebUserService webUserService;
	
	@Autowired(required = false)
	private WebLogService webLogService;


	
	
	/**
	 * 요청 : 결과지 웹 WEB
	 * 기능   : 선생님 ID로 해당 학생들 목록 조회
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 7. 15
	 */
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String statisticsLoginForm(HttpServletRequest request, Model model, HttpSession session) {
		
		List<WebUserVO> sessionList = (List<WebUserVO>)session.getAttribute("userInfo");
		if(sessionList == null) {
			return "/premium/statistics/login/loginForm";
		}
		// 로그인 화면 접속 시 학교 코드를 전달해줌
		return "/premium/statistics/main";
	}
	
	
	
	
	/**
	 * 기능   : 결과지 웹 로그인 요청
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 07
	 * 			 APP에서 JSON형식으로 전달 받은 회원의 ID와 비밀번호를 확인 후 JSON으로 반환
	 * @throws Exception 
	 */
	@PostMapping(value = {"/premium/statistics/login.do"})
	public String webStatisticsLoginForm(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
       
		
		// 세션이 없을 경우 로그인 페이지로 보내주는 부분 필요
		logger.debug("===== Premium ===== WebController ===== /premium/statistics/login.do =====");
		
		String schCd = null;
		
		schCd = request.getParameter("schCd");
		
		
		int loginChkByIdPwd = 0;
		int isIdExist = 0;
		
		HttpSession session = request.getSession(true);
		// 세션 유지 시간 10분
		session.setMaxInactiveInterval(60*10*1);
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userType= request.getParameter("userType");
		String loginIp = null;
		
		List<WebUserVO> userInfo = new ArrayList<WebUserVO>();
		
		WebAuthVO webAuthVO = new WebAuthVO();
		WebUserVO webUserVO = new WebUserVO();
		
		
		// 오늘 일자 계산
		Date tmpDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		String sysDate = sdf.format(tmpDate);
		
		// 비밀번호 암호화 
		AES256Util aes256Util = new AES256Util();
		userPwd = aes256Util.aesEncode(userPwd);
		
		if(userPwd.equals("false")) { // 암호화에 실패할 경우
			return "/premium/statistics/login/loginForm";
		}
		
		// 로그인 VO
		webAuthVO.setUserId(userId);
		webAuthVO.setUserPwd(userPwd);
		webAuthVO.setLoginDt(sysDate);
		webAuthVO.setUserType(userType);
		// 회원 VO
		webUserVO.setUserId(userId);
		webUserVO.setUserType(userType);
		
		
		try {
			// 아이디 검증
			loginChkByIdPwd = webAuthService.loginChkByIdPwd(webAuthVO);
			if(loginChkByIdPwd == 0){
				// 0일 경우는 Database에 ID와 비밀번호가 틀린 것
				isIdExist = webAuthService.isIdExist(webAuthVO);
				if(isIdExist == 0) {
					 // ID가 존재하지 않을 경우
					model.addAttribute("msg", "등록 되어 있지 않은 ID입니다.");
					model.addAttribute("loginUrl", loginUrl);
					return "/premium/statistics/common/alertMessage";
					
				}else { 
					// PWD가 틀렸을 경우
					model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해주시기 바랍니다.");
					model.addAttribute("loginUrl", loginUrl);
					return "/premium/statistics/common/alertMessage";
					
				}
			}else { // 아이디와 비밀번호가 검증 된 이후 JWT 토큰과 회원의 정보를 제공

				// 로그인 일자 업데이트
				webLogService.updateLoginDt(webAuthVO);
				
				// 회원의 정보를 가져옴
				userInfo = webUserService.selectUserInfo(webUserVO);
				
				webAuthVO.setUserNo(userInfo.get(0).getUserNo());
				webAuthVO.setLoginDt(userInfo.get(0).getLoginDt());
				webAuthVO.setLoginIp(request.getRemoteAddr());
				// 회원 로그인 기록 등록
				webLogService.insertUserLoginHistory(webAuthVO);
				
				// 세션 생성 및 HTTP응답을 받고 세션을 쿠키에 담고, response에 쿠기를 담음
				session.setAttribute("userInfo", userInfo);
				session.setAttribute("userNo", webAuthVO.getUserNo());
				session.setAttribute("schoolCode", schCd);
			}
		} catch (Exception e) {
			// 로그인 실패
			webAuthVO.setLoginResult("FAILURE");
			webLogService.insertUserLoginHistory(webAuthVO);
			e.printStackTrace();
			return "error/500";
		}
		return "redirect:/premium/admin/statistics/main.do";
	}
	
	
	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2022. 07. 18
	 * 기능   : 로그아웃
	 */
	@RequestMapping(value = {"/premium/statistics/logout.do"})
	public String logout(HttpServletRequest request, HttpSession session) {
		session.invalidate();
		return "/premium/statistics/login/loginForm";
	}
}
