package co.smartooth.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.admin.mapper.AdminOrganMapper;
import co.smartooth.admin.mapper.AdminUserMapper;
import co.smartooth.admin.service.AdminOrganService;
import co.smartooth.admin.service.AdminUserService;
import co.smartooth.admin.vo.AdminOrganVO;
import co.smartooth.admin.vo.AdminUserVO;
import co.smartooth.web.mapper.WebUserMapper;
import co.smartooth.web.service.WebUserService;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 7. 15 ~
 */
@Service
public class AdminOrganServiceImpl implements AdminOrganService{
	
	
	@Autowired(required = false)
	AdminOrganMapper adminOrganMapper; 

	// 기관 조회
	@Override
	public List<AdminOrganVO> selectOrganList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception {
		return adminOrganMapper.selectOrganList(searchType, searchData);
	}

	// 기관 정보 등록
	@Override
	public void insertOrganInfo(AdminOrganVO adminOrganVO) throws Exception {
		adminOrganMapper.insertOrganInfo(adminOrganVO);
	}

	
	// 기관 정보 삭제
	@Override
	public void deleteOrganInfo(String schoolCode) throws Exception {
		adminOrganMapper.deleteOrganInfo(schoolCode);
	}
	

	
	
}