package co.smartooth.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import co.smartooth.admin.vo.AdminAuthVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
@Mapper
public interface AdminLogMapper {

	// 회원 로그인 기록 INSERT
	public void insertUserLoginHistory(AdminAuthVO AdminAuthVO) throws Exception;
	
	// 회원 접속일 UPDATE (선생님테이블)
	public void updateLoginDtbyTC(AdminAuthVO AdminAuthVO) throws Exception;
	
	// 회원 접속일 UPDATE (학생테이블)
	public void updateLoginDtbyST(AdminAuthVO AdminAuthVO) throws Exception;
	
	// 회원 접속일 UPDATE (부모님테이블)
	public void updateLoginDtbyPA(AdminAuthVO AdminAuthVO) throws Exception;
	
	// 회원 접속일 UPDATE (관리자테이블)
	public void updateLoginDt(AdminAuthVO AdminAuthVO) throws Exception;
	
}
