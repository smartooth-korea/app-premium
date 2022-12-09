package co.smartooth.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.admin.mapper.AdminLocationMapper;
import co.smartooth.admin.mapper.AdminOrganMapper;
import co.smartooth.admin.mapper.AdminUserMapper;
import co.smartooth.admin.service.AdminLocationService;
import co.smartooth.admin.service.AdminOrganService;
import co.smartooth.admin.service.AdminUserService;
import co.smartooth.admin.vo.AdminLocationVO;
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
public class AdminLocationServiceImpl implements AdminLocationService{
	
	
	@Autowired(required = false)
	AdminLocationMapper adminLocationMapper;

	
	// 지역구 조회
	@Override
	public List<AdminLocationVO> selectLocationInfo(@Param("sidoCd") String sidoCd, @Param("sggemdCd") String sggemdCd) throws Exception {
		return adminLocationMapper.selectLocationInfo(sidoCd, sggemdCd);
	}

	// 지역이름으로 지역코드(SGGMD_CD) 조회
	@Override
	public AdminLocationVO selectLocationCd(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception {
		return adminLocationMapper.selectLocationCd(searchType, searchData);
	}

	// 전체 시도 코드, 이름 조회
	@Override
	public List<AdminLocationVO> selectSidoInfoList() throws Exception {
		return adminLocationMapper.selectSidoInfoList();
	}

	// 전체 시군구 읍면동 코드, 이름 조회
	@Override
	public List<AdminLocationVO> selectSigunguEupmyeondongInfoList(@Param("locationType") String locationType, @Param("locationCd") String locationCd) throws Exception {
		return adminLocationMapper.selectSigunguEupmyeondongInfoList(locationType, locationCd);
	}

	// 각 각 읍면동에 대한 SeqNo 업데이트
	@Override
	public void updateAddrSeqNo(AdminLocationVO adminLocationVO) throws Exception{
		adminLocationMapper.updateAddrSeqNo(adminLocationVO);
	} 

	

	
	
}