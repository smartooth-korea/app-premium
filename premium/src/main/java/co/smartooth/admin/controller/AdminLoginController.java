package co.smartooth.admin.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import co.smartooth.admin.service.AdminAuthService;
import co.smartooth.admin.service.AdminLogService;
import co.smartooth.admin.service.AdminUserService;
import co.smartooth.admin.vo.AdminAuthVO;
import co.smartooth.admin.vo.AdminUserVO;
import co.smartooth.app.utils.AES256Util;
import lombok.extern.slf4j.Slf4j;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022.11. 07
 * 현재 JSP로 개발 예정이므로 RestController는 사용하지 않음
 * 
 */

@Slf4j
@PropertySource({ "classpath:application.yml" })
@Controller
public class AdminLoginController {

	
	@Value("${loginUrl}")
    private String loginUrl;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginController.class);
	
	@Autowired(required = false)
	private AdminAuthService adminAuthService;

	@Autowired(required = false)
	private AdminUserService adminUserService;
	
	@Autowired(required = false)
	private AdminLogService adminLogService;


	
	
	/**
	 * 요청 : 프리미엄 관리자 페이지 로그인 화면
	 * 기능   : 선생님 ID로 해당 학생들 목록 조회
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 7. 15
	 */
	@GetMapping(value = {"/premium/admin/login"})
	public String adminLogin(HttpServletRequest request, Model model, HttpSession session) {
		
		
		// 세션이 끊겼을 경우 로그인 페이지 리턴
		@SuppressWarnings("unchecked")
		List<AdminUserVO> sessionList = (List<AdminUserVO>)session.getAttribute("userInfo");
		if(sessionList == null) {
			return "/premium/admin/login/loginForm";
		}
		return "/premium/admin/main";
		
	}	
	
	
	
	
	/**
	 * 기능   : 프리미엄 관리자 페이지 로그인 요청
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 07
	 * 			 APP에서 JSON형식으로 전달 받은 회원의 ID와 비밀번호를 확인 후 JSON으로 반환
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/premium/admin/login.do"})
	public String adminLoginForm(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
       
		// 세션이 없을 경우 로그인 페이지로 보내주는 부분 필요
		logger.debug("===== Premium ===== WebController ===== /premium/admin/login.do =====");
		
		int loginChkByIdPwd = 0;
		int isIdExist = 0;
		
		HttpSession session = request.getSession(true);
		// 세션 유지 시간 10분
		session.setMaxInactiveInterval(60*10*1);
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userType= request.getParameter("userType");
		// String loginIp = null;
		
		AdminUserVO userInfo = new AdminUserVO();
		
		AdminAuthVO adminAuthVO = new AdminAuthVO();
		AdminUserVO adminUserVO = new AdminUserVO();
		
		
		// 오늘 일자 계산
		Date tmpDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		String sysDate = sdf.format(tmpDate);
		
		// 비밀번호 암호화 
		AES256Util aes256Util = new AES256Util();
		userPwd = aes256Util.aesEncode(userPwd);
		
		if(userPwd.equals("false")) { // 암호화에 실패할 경우
			return "/premium/admin/login/loginForm";
		}
		
		// 로그인 VO
		adminAuthVO.setUserId(userId);
		adminAuthVO.setUserPwd(userPwd);
		adminAuthVO.setLoginDt(sysDate);
		adminAuthVO.setUserType(userType);
		// 회원 VO
		adminUserVO.setUserId(userId);
		adminUserVO.setUserType(userType);
		
		
		try {
			// 아이디 검증
			loginChkByIdPwd = adminAuthService.loginChkByIdPwd(adminAuthVO);
			
			// 해당 아이디의 USER_TYPE이 관리자 일경우 로그인 하도록 변경
			if(loginChkByIdPwd == 0){
				// 0일 경우는 Database에 ID와 비밀번호가 틀린 것
				isIdExist = adminAuthService.isIdExist(adminAuthVO);
				if(isIdExist == 0) {
					 // ID가 존재하지 않을 경우
					model.addAttribute("msg", "등록 되어 있지 않은 ID입니다.");
					model.addAttribute("loginUrl", loginUrl);
					return "/premium/admin/common/alertMessage";
					
				}else { 
					// PWD가 틀렸을 경우
					model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해주시기 바랍니다.");
					model.addAttribute("loginUrl", loginUrl);
					return "/premium/admin/common/alertMessage";
					
				}
			}else { // 아이디와 비밀번호가 검증 된 이후 JWT 토큰과 회원의 정보를 제공

				// 로그인 일자 업데이트
				adminLogService.updateLoginDt(adminAuthVO);
				
				// 회원의 정보를 가져옴
				userInfo = adminUserService.selectUserInfo(userId);
				
				adminAuthVO.setUserNo(userInfo.getUserNo());
				adminAuthVO.setLoginDt(userInfo.getLoginDt());
				adminAuthVO.setLoginIp(request.getRemoteAddr());
				// 회원 로그인 기록 등록
				adminLogService.insertUserLoginHistory(adminAuthVO);
				
				// 세션 생성 및 HTTP응답을 받고 세션을 쿠키에 담고, response에 쿠기를 담음
				session.setAttribute("userInfo", userInfo);
			}
		} catch (Exception e) {
			// 로그인 실패
			adminAuthVO.setLoginResult("FAILURE");
			adminLogService.insertUserLoginHistory(adminAuthVO);
			e.printStackTrace();
			return "error/500";
		}
		return "redirect:/premium/admin/main.do";
	}
	
	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2022. 07. 18
	 * 기능   : 로그아웃
	 */
	@RequestMapping(value = {"/premium/admin/logout.do"})
	public String logout(HttpServletRequest request, HttpSession session) {
		session.invalidate();
		return "redirect:/premium/admin/login";
	}
	
	
//	/**
//	 * 기능   : 결과지 웹 로그인 요청
//	 * 작성자 : 정주현 
//	 * 작성일 : 2022. 07. 07
//	 * 			 APP에서 JSON형식으로 전달 받은 회원의 ID와 비밀번호를 확인 후 JSON으로 반환
//	 * @throws Exception 
//	 */
//	@PostMapping(value = {"/premium/admin/login.do"})
//	public String webStatisticsLoginForm(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//      
//		
//		// 세션이 없을 경우 로그인 페이지로 보내주는 부분 필요
//		logger.debug("===== Premium ===== WebController ===== /premium/admin/login.do =====");
//		
//		String schCd = null;
//		
//		schCd = request.getParameter("schCd");
//		
//		
//		int loginChkByIdPwd = 0;
//		int isIdExist = 0;
//		
//		HttpSession session = request.getSession(true);
//		// 세션 유지 시간 10분
//		session.setMaxInactiveInterval(60*10*1);
//		
//		String userId = request.getParameter("userId");
//		String userPwd = request.getParameter("userPwd");
//		String userType= request.getParameter("userType");
//		String loginIp = null;
//		
//		List<WebUserVO> userInfo = new ArrayList<WebUserVO>();
//		
//		WebAuthVO adminAuthVO = new WebAuthVO();
//		WebUserVO adminUserVO = new WebUserVO();
//		
//		
//		// 오늘 일자 계산
//		Date tmpDate = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
//		String sysDate = sdf.format(tmpDate);
//		
//		// 비밀번호 암호화 
//		AES256Util aes256Util = new AES256Util();
//		userPwd = aes256Util.aesEncode(userPwd);
//		
//		if(userPwd.equals("false")) { // 암호화에 실패할 경우
//			return "/premium/admin/login/loginForm";
//		}
//		
//		// 로그인 VO
//		adminAuthVO.setUserId(userId);
//		adminAuthVO.setUserPwd(userPwd);
//		adminAuthVO.setLoginDt(sysDate);
//		adminAuthVO.setUserType(userType);
//		// 회원 VO
//		adminUserVO.setUserId(userId);
//		adminUserVO.setUserType(userType);
//		
//		
//		try {
//			// 아이디 검증
//			loginChkByIdPwd = webAuthService.loginChkByIdPwd(adminAuthVO);
//			if(loginChkByIdPwd == 0){
//				// 0일 경우는 Database에 ID와 비밀번호가 틀린 것
//				isIdExist = webAuthService.isIdExist(adminAuthVO);
//				if(isIdExist == 0) {
//					 // ID가 존재하지 않을 경우
//					model.addAttribute("msg", "등록 되어 있지 않은 ID입니다.");
//					model.addAttribute("loginUrl", loginUrl);
//					return "/premium/admin/common/alertMessage";
//					
//				}else { 
//					// PWD가 틀렸을 경우
//					model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해주시기 바랍니다.");
//					model.addAttribute("loginUrl", loginUrl);
//					return "/premium/admin/common/alertMessage";
//					
//				}
//			}else { // 아이디와 비밀번호가 검증 된 이후 JWT 토큰과 회원의 정보를 제공
//
//				// 로그인 일자 업데이트
//				webLogService.updateLoginDt(adminAuthVO);
//				
//				// 회원의 정보를 가져옴
//				userInfo = webUserService.selectUserInfo(adminUserVO);
//				
//				adminAuthVO.setUserNo(userInfo.get(0).getUserNo());
//				adminAuthVO.setLoginDt(userInfo.get(0).getLoginDt());
//				adminAuthVO.setLoginIp(request.getRemoteAddr());
//				// 회원 로그인 기록 등록
//				webLogService.insertUserLoginHistory(adminAuthVO);
//				
//				// 세션 생성 및 HTTP응답을 받고 세션을 쿠키에 담고, response에 쿠기를 담음
//				session.setAttribute("userInfo", userInfo);
//				session.setAttribute("userNo", adminAuthVO.getUserNo());
//				session.setAttribute("schoolCode", schCd);
//			}
//		} catch (Exception e) {
//			// 로그인 실패
//			adminAuthVO.setLoginResult("FAILURE");
//			webLogService.insertUserLoginHistory(adminAuthVO);
//			e.printStackTrace();
//			return "error/500";
//		}
//		return "redirect:/premium/admin/main.do";
//	}
//	
	
}
