package co.smartooth.admin.service;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import co.smartooth.admin.vo.AdminUserVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 08
 */
public interface AdminUserService {
	
	
	// 로그인 시 회원 정보 조회
	public AdminUserVO selectUserInfo(@Param("userId") String userId) throws Exception;
	
	
	// 사용자 목록 조회 (관리자 제외)
	public List<AdminUserVO> selectUserList() throws Exception;
	
	
	// 충치 측정한 사용자 목록 조회
	public List<HashMap<String,Object>> selectUserMeasureList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception;
	
}
