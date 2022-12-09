package co.smartooth.admin.controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.smartooth.admin.service.AdminLocationService;
import co.smartooth.admin.service.AdminOrganService;
import co.smartooth.admin.service.AdminUserService;
import co.smartooth.admin.vo.AdminLocationVO;
import co.smartooth.admin.vo.AdminOrganVO;
import co.smartooth.admin.vo.AdminUserVO;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 08
 * 수정일 : 
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class AdminUserController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	

//	@Autowired(required = false)
//	private AdminUserService adminUserService;
//	
//	@Autowired(required = false)
//	private AdminTeethService adminTeeㅈthService;
//
//	@Autowired(required = false)
//	private AdminMemoService adminMemoService;
	
	@Autowired(required = false)
	private AdminOrganService adminOrganService;
	
	@Autowired(required = false)
	private AdminUserService adminUserService;
	
	@Autowired(required = false)
	private AdminLocationService adminLocationService;
	
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 07
	 * 기능   : 프리미엄 관리자 웹 메인 화면 접속
	 */
	@RequestMapping(value = {"/premium/admin/main.do"})
	public String main(HttpServletRequest request, HttpSession session, Model model) {
		
		AdminUserVO sessionList = (AdminUserVO)session.getAttribute("userInfo");
		if(sessionList == null ) {
			return "redirect:/premium/admin/login";
		}
		model.addAttribute("userInfo", sessionList);
		return "/premium/admin/main";
		
	}
	
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 24
	 * 기능   : 프리미엄 앱 사용자 조회
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/premium/admin/user/selectUserList"})
	public String selectUserList(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		
		@SuppressWarnings("unchecked")
		AdminUserVO sessionList = (AdminUserVO)session.getAttribute("userInfo");
		if(sessionList == null ) {
			return "redirect:/premium/admin/login";
		}
		
		String userId = sessionList.getUserId();
		AdminUserVO userInfo = new AdminUserVO();
		List<AdminUserVO> userList = new ArrayList<AdminUserVO>();

		// List<AdminLocationVO> sidoList = new ArrayList<AdminLocationVO>();
		// 시도의 지역 코드와 이름 조회
		// sidoList = adminLocationService.selectSidoInfoList();
		
		// 로그인 한 아이디의 권한 조회
		userInfo = adminUserService.selectUserInfo(userId);
		 
		//model.addAttribute("sidoList", sidoList);
		model.addAttribute("userInfo", userInfo);

		return "/premium/admin/user/selectUserList";
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/premium/admin/user/selectUserList.do"})
	public @ResponseBody String AjaxSelectUserList(HttpServletRequest request, HttpSession session, Model model, @RequestParam Map<String, String> paramMap) throws Exception {
		
		@SuppressWarnings("unchecked")
		AdminUserVO sessionList = (AdminUserVO)session.getAttribute("userInfo");
		if(sessionList == null ) {
			return "redirect:/premium/admin/login";
		}
		
		List<AdminUserVO> userList = new ArrayList<AdminUserVO>();

		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		// 사용자 목록 조회 (관리자 제외) - 반 아이디 제외 
		userList = adminUserService.selectUserList();
		
		for(int i=0; i< userList.size(); i++) {
			
			JSONObject tmpObj = new JSONObject();
			
			tmpObj.put("userName", userList.get(i).getUserName());
			tmpObj.put("userId", userList.get(i).getUserId());
			tmpObj.put("userType", userList.get(i).getUserType());
			tmpObj.put("userSex", userList.get(i).getUserSex());
			tmpObj.put("userBirthday", userList.get(i).getUserBirthday());
			tmpObj.put("userTelNo", userList.get(i).getUserTelNo());
			tmpObj.put("userRgstDt", userList.get(i).getUserRgstDt());
			tmpObj.put("sidoNm", userList.get(i).getSidoNm());
			tmpObj.put("sigunguNm", userList.get(i).getSigunguNm());
			tmpObj.put("eupmyeondongNm", userList.get(i).getEupmyeondongNm());
			jsonArray.add(tmpObj);
			
		}
		
		jsonObject.put("rows", jsonArray);
		
		return jsonObject.toJSONString(); 
		
	}

	
}
