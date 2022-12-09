package co.smartooth.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.admin.mapper.AdminLogMapper;
import co.smartooth.admin.service.AdminLogService;
import co.smartooth.admin.vo.AdminAuthVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
@Service
public class AdminLogServiceImpl implements AdminLogService{

	
	@Autowired(required = false)
	AdminLogMapper adminLogMapper;
	
	
	// 회원 로그인 기록 INSERT
	@Override
	public void insertUserLoginHistory(AdminAuthVO adminAuthVO) throws Exception {
		adminLogMapper.insertUserLoginHistory(adminAuthVO);
	}
	
	// 회원 접속일 UPDATE (관리자테이블)
	@Override
	public void updateLoginDt(AdminAuthVO adminAuthVO) throws Exception {
		adminLogMapper.updateLoginDt(adminAuthVO);
	}
}
