package co.smartooth.admin.service;

import org.springframework.stereotype.Service;

import co.smartooth.admin.vo.AdminAuthVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 7. 18 ~
 */

@Service
public interface AdminAuthService {

	// 회원 아이디와 비밀번호로 존재 여부 확인 :: true = 1, false = 0
	public int loginChkByIdPwd(AdminAuthVO adminAuthVO) throws Exception;
	
	// 회원 아이디가 존재하는지 여부 확인 :: true = 1, false = 0
	public int isIdExist(AdminAuthVO adminAuthVO) throws Exception;
	

}