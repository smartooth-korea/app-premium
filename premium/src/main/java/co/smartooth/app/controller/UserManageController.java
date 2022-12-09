package co.smartooth.app.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.app.service.MailAuthService;
import co.smartooth.app.service.TeethService;
import co.smartooth.app.service.UserService;
import co.smartooth.app.utils.AES256Util;
import co.smartooth.app.utils.JwtTokenUtil;
import co.smartooth.app.vo.SchoolClassInfoVO;
import co.smartooth.app.vo.TeethInfoVO;
import co.smartooth.app.vo.UserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 08. 03
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class UserManageController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
//	인증 여부 확인 flag
//	private static boolean tokenValidation = false; 
//	private static boolean tokenValidation1 = true; 

	@Autowired(required = false)
	private UserService userService;
	
	@Autowired(required = false)
	private TeethService teethService;
	
	
	
	
	
	// 학생 회원 추가 화면(수동)
	@GetMapping(value = {"/premium/manage/userRregistView.do"})
	public String studentRegist() {
		return "/manage/stUserInfoView";
	}
	
	/**
	 * 기능   : 학생 회원 추가
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 08. 22
	 * 수정일 : 2022. 08. 22
	 */
	@PostMapping(value = {"/premium/manage/InserStUserInfo.do"})
	@ResponseBody
	public HashMap<String,Object> studentRegist(@RequestBody HashMap<String, Object> paramMap) throws Exception{
		
		//회원 ID
		String userId = null;
		// 회원 비밀번호
		String userPwd = null;
		// 회원 이름
		String userType = null;
		// 회원 생일
		String userName = null;
		// 회원 타입 (개인, 단체, 그룹)
		String userBirthday = null;
		// 회원 거주 - 국
		String userCountry = null;
		// 회원 거주 - 주
		String userState = null;
		// 회원 상세 주소
		String userAddress = null;
		// 회원 전화번호
		String userTelNo = null;
		// 회원 성별
		String userSex = null;
		// 푸쉬토큰
		String pushToken = null;
		// 회원 치아 상태
		String teethStatus = null;
		// 담임 선생님
		String teacherId = null;
		// 학교 종류
		String schoolType = null;
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		TeethInfoVO teethInfoVO = new TeethInfoVO();
		UserVO userVO = new UserVO();
		
		try {
			
			userPwd = (String)paramMap.get("userPwd");
			// 비밀번호 암호화
			AES256Util aes256Util = new AES256Util();
			userPwd = aes256Util.aesEncode(userPwd);
			// 회원 구분 :: 파라미터가 전달되지 않을 경우, 일반 회원으로 등록
			// 회원 ID
			userId = (String)paramMap.get("userId");
			// 회원 종류
			userType = (String)paramMap.get("userType");
			// 회원 이름
			userName = (String)paramMap.get("userName");
			// 회원 생일
			userBirthday = (String)paramMap.get("userBirthday");
			// 회원 거주 국
			userCountry = (String)paramMap.get("userCountry");
			// 회원 거주 주
			userState = (String)paramMap.get("userState");
			// 회원 상세주소
			userAddress = (String)paramMap.get("userAddress");
			// 회원 전화번호
			userTelNo = (String)paramMap.get("userTelNo");
			// 회원 성별
			userSex = (String)paramMap.get("userSex");
			// 푸쉬토큰
			pushToken = (String)paramMap.get("pushToken");
			// 치아 상태
			teethStatus = (String)paramMap.get("teethStatus");
			// 담임 선생님
			teacherId = (String)paramMap.get("teacherId"); 
			// 학교 종류
			schoolType = (String)paramMap.get("schoolType"); 
			
			userVO.setUserId(userId);
			userVO.setUserPwd(userPwd);
			userVO.setUserType(userType);
			userVO.setUserName(userName);
			userVO.setUserBirthday(userBirthday);
			userVO.setUserCountry(userCountry);
			userVO.setUserState(userState);
			userVO.setUserAddress(userAddress);
			userVO.setUserTelNo(userTelNo);
			userVO.setUserSex(userSex);
			userVO.setPushToken(pushToken);
			userVO.setTeacherId(teacherId);
			userVO.setSchoolType(schoolType);
			
			
			teethInfoVO.setUserId(userId);
			teethInfoVO.setTeethStatus(teethStatus); 
			
			// 학생 회원 등록 (회원가입)
			userService.insertUserInfo(userVO);
			// 학생 회원 상세 정보 등록
			userService.insertStudentUserDetail(userVO);
			
			// 일반 회원 치아 상태 등록
			teethService.insertUserTeethInfo(teethInfoVO);
			// 일반 회원 번호 (일반 회원 번호 생성 후 SEQ_NO) 업데이트 
			
		} catch (Exception e) {
			
			hm.put("code", "500");
			hm.put("msg", "Server Error.");
			e.printStackTrace();
			
		}
		
		hm.put("code", "000");
		hm.put("msg", "Success.");
		
		return hm;
	}
	
	
	// 학생 회원 정보 업데이트 전 선생 목록 조회(수동)
	//@GetMapping(value = {"/test/studentList"})
	@GetMapping(value = {"/premium/manage/stUserListView.do"})
	public String studentList(HttpServletRequest request, Model model) {
		
		// 학교 코드 :: 하드코딩
		String schoolCode = "KG0002";
		List<UserVO> tcList = new ArrayList<UserVO>(); 
		
		try {
			// 선생님 목록 조회 (반 조회)
			tcList = userService.selectTcUserList(schoolCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("tcList", tcList);
		
		return "/manage/stUserListView";
	}
	
	
	// 학생 회원 정보 업데이트 전 선생 목록 조회(수동)
	@GetMapping(value = {"/premium/manage/stUserInfoListView.do"})
	public String studentInfo(HttpServletRequest request, Model model) {
		
		// 학교 코드 :: 하드코딩
		String userId = request.getParameter("userId");
		String userName = "흰이테스트";
		List<UserVO> stList = new ArrayList<UserVO>(); 

		try {
			// 선생님 ID로 해당 학생들 목록 조회
			stList = userService.selectTestUserListByTc(userId, userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		model.addAttribute("stList", stList);
		
		return "/manage/stUserInfoListView";
	}
	
	
	
	// 학생 회원 이름 업데이트
	@PostMapping(value = {"/premium/manage/stUserUpdateName.do"})
	public HashMap<String,Object> studentUpdateName(@RequestBody HashMap<String, String> paramMap, HttpServletRequest request, Model model) {
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		String userId = (String)paramMap.get("userId");
		String userName = (String)paramMap.get("userName");

		try {
			// 학생 이름 업데이트
			userService.updateTestUserName(userId, userName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		hm.put("code", "000");
		hm.put("msg", "success");
		return hm;
		
	}
	
}
