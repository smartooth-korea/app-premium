package co.smartooth.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.smartooth.admin.mapper.AdminAuthMapper;
import co.smartooth.admin.service.AdminAuthService;
import co.smartooth.admin.vo.AdminAuthVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 07. 18 ~
 */
@Component
@Service
public class AdminAuthServiceImpl implements AdminAuthService{

	
	@Autowired(required = false)
	AdminAuthMapper adminAuthMapper;
	
	
	
	// 회원 아이디와 비밀번호로 존재 여부 확인 :: true = 1, false = 0
	@Override
	public int loginChkByIdPwd(AdminAuthVO adminAuthVO) throws Exception {
		return adminAuthMapper.loginChkByIdPwd(adminAuthVO);
	}

	// 회원 아이디가 존재하는지 여부 확인 :: true = 1, false = 0
	@Override
	public int isIdExist(AdminAuthVO adminAuthVO) throws Exception {
		return adminAuthMapper.isIdExist(adminAuthVO);
	}
}
