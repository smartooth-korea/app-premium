package co.smartooth.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 11. 16
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Slf4j
@Controller
public class InterceptorController {
    
	
	// 진단지 로그인 화면
	@RequestMapping(value = {"/premium/login"})
	public String login() {
		return "/login/loginForm";
	}
	
	
	@RequestMapping(value = {"/premium/user/selectUserTeethInfo"})
	public String teethInfo() {
		return "/test/premium/user/selectUserTeethInfo";
	}
	
	
	@RequestMapping(value = {"/premium/user/selectUserTeethMeasureValue"})
	public String teethMeasureValue() {
		return "/test/premium/user/selectUserTeethMeasureValue";
	}

	
	@RequestMapping(value = {"/premium/user/selectUserToothMeasureValue"})
	public String selectUserToothMeasureValue() {
		return "/test/premium/user/selectUserToothMeasureValue";
	}
 	
	
	@RequestMapping(value = {"/premium/user/deleteUser"})
	public String deleteUser() {
		return "/test/premium/user/deleteUser";
	}
	
	
	@RequestMapping(value = {"/premium/user/insertCalibrationInfoValue"})
	public String insertCalibrationInfo() {
		return "/test/premium/user/insertCalibrationInfoValue";
	}
	
	
	@RequestMapping(value = {"/premium/user/findUserPwd"})
	public String findUserPwd() {
		return "/test/premium/user/findUserPwd";
	}
	
	
	@GetMapping(value= {"/premium/user/selectUserIsMeasuringValue"})
	public String selectUserIsMeasuringValue(){
	    return "/test/premium/user/selectUserIsMeasuringValue";
	}
	
	
	@GetMapping(value= {"/premium/user/selectStUserListByTc"})
    public String selectUserListSTbyTC(){
        return "/test/premium/user/selectStUserListByTc";
    }
	
	
	@GetMapping(value = {"/premium/user/selectDiagnosisInfo"})
	public String selectDiagnosisInfo() {
		return "/test/premium/user/selectDiagnosisInfo";
	}

	
	
	
	
	
	
	
	/** 리액트 관련 테스트 **/
	@GetMapping(value = {"/test/app/user/react.do"})
	public String reactDemo(){
		return "Hello React";
	}
	
	

	
	
	
	
	
	/** 유틸리티 **/
	
	@GetMapping(value = {"/premium/utils/passwordCrypto"})
	public String passwordCrypto() {
		return "/test/premium/utils/passwordCrypto";
	}
	
	
	@GetMapping(value = {"/premium/utils/updateCavityCnt"})
	public String updateCavityCnt() {
		return "/test/premium/utils/updateCavityCnt";
	}
	
	
	@GetMapping(value = {"/premium/utils/selectCavityLevel"})
	public String selectCavityLevel() {
		return "/test/premium/utils/selectCavityLevel";
	}
	
	
	@GetMapping(value = {"/premium/utils/registUsers"})
	public String uploadCvs() {
		return "/test/premium/utils/registUsers";
	}

	
	@GetMapping(value = {"/premium/utils/updateDiagCd"})
	public String updateDiagCd() {
		return "/test/premium/utils/updateDiagCd";
	}

}
