package co.smartooth.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.admin.mapper.AdminUserMapper;
import co.smartooth.admin.service.AdminUserService;
import co.smartooth.admin.vo.AdminUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 7. 15 ~
 */
@Service
public class AdminUserServiceImpl implements AdminUserService{
	
	
	@Autowired(required = false)
	AdminUserMapper adminUserMapper;

	
	
	// 로그인 시 회원 정보 조회
	@Override
	public AdminUserVO selectUserInfo(String userId) throws Exception {
		return adminUserMapper.selectUserInfo(userId);
	}

	
	// 사용자 목록 조회 (관리자 제외)
	@Override
	public List<AdminUserVO> selectUserList() throws Exception {
		return adminUserMapper.selectUserList();
	}

	
	// 충치 측정한 사용자 목록 조회
	public List<HashMap<String,Object>> selectUserMeasureList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception{
		return adminUserMapper.selectUserMeasureList(searchType, searchData);
	}


	
}