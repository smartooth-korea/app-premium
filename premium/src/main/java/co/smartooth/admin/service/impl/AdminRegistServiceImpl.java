package co.smartooth.admin.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.admin.mapper.AdminRegistMapper;
import co.smartooth.admin.service.AdminRegistService;
import co.smartooth.admin.vo.AdminLocationVO;
import co.smartooth.admin.vo.AdminSeqNoVO;
import co.smartooth.web.service.WebRegistService;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public class AdminRegistServiceImpl implements AdminRegistService{


	
	@Autowired(required = false)
	AdminRegistMapper adminRegistMapper;
	
	
	
	// 나라 이름으로 나라 코드 조회 :: ISO_ALPHA2 코드 조회
	@Override
	public String selectCountryCd(String countryNm) throws Exception {
		return adminRegistMapper.selectCountryCd(countryNm);
	}

	
	// 시도 이름으로 시도 코드 조회
	@Override
	public String selectSiDoCd(String siDoNm) throws Exception {
		return adminRegistMapper.selectSidoCd(siDoNm);
	}

	
	// 지역 정보 조회
	@Override
	public AdminLocationVO selectLocationInfo(AdminLocationVO adminLocationVO) throws Exception {
		return adminRegistMapper.selectLocationInfo(adminLocationVO); 
	}

	
	// 시퀀스 번호 조회
	@Override
	public AdminSeqNoVO selectSeqNoAll() throws Exception {
		return adminRegistMapper.selectSeqNoAll();
	}


	// 학교 코드 시퀀스 번호 증가
	@Override
	public void updateSeqNo(@Param("seqNm") String seqNm, @Param("seqNo") String seqNo) throws Exception {
		adminRegistMapper.updateSeqNo(seqNm, seqNo);
	}


	// 학교 정보 등록
	@Override
	public void insertSchInfo(@Param("schCd") String schCd, @Param("schNm") String schNm) throws Exception {
		adminRegistMapper.insertSchInfo(schCd, schNm);
	}


	// 반 정보 등록
	@Override
	public void insertClassInfo(@Param("classCd") String classCd, @Param("classNm") String classNm) throws Exception {
		adminRegistMapper.insertClassInfo(classCd, classNm);
	}	
	

	
	
}