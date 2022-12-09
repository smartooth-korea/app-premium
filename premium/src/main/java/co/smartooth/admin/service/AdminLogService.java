package co.smartooth.admin.service;

import org.springframework.stereotype.Service;

import co.smartooth.admin.vo.AdminAuthVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
@Service
public interface AdminLogService {
	
	// 회원 로그인 기록 INSERT
	public void insertUserLoginHistory(AdminAuthVO adminAuthVO) throws Exception;
	
	// 회원 접속일 UPDATE (관리자테이블)
	public void updateLoginDt(AdminAuthVO adminAuthVO) throws Exception;
	
}