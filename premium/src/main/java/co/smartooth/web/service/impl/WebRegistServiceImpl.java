package co.smartooth.web.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.web.mapper.WebRegistMapper;
import co.smartooth.web.service.WebRegistService;
import co.smartooth.web.vo.WebLocationVO;
import co.smartooth.web.vo.WebSeqNoVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public class WebRegistServiceImpl implements WebRegistService{


	
	@Autowired(required = false)
	WebRegistMapper webRegistMapper;
	
	
	
	// 나라 이름으로 나라 코드 조회 :: ISO_ALPHA2 코드 조회
	@Override
	public String selectCountryCd(String countryNm) throws Exception {
		return webRegistMapper.selectCountryCd(countryNm);
	}

	
	// 시도 이름으로 시도 코드 조회
	@Override
	public String selectSiDoCd(String siDoNm) throws Exception {
		return webRegistMapper.selectSidoCd(siDoNm);
	}

	
	// 지역 정보 조회
	@Override
	public WebLocationVO selectLocationInfo(WebLocationVO webLocationVO) throws Exception {
		return webRegistMapper.selectLocationInfo(webLocationVO); 
	}

	
	// 시퀀스 번호 조회
	@Override
	public WebSeqNoVO selectSeqNoAll() throws Exception {
		return webRegistMapper.selectSeqNoAll();
	}


	// 학교 코드 시퀀스 번호 증가
	@Override
	public void updateSeqNo(@Param("seqNm") String seqNm, @Param("seqNo") String seqNo) throws Exception {
		webRegistMapper.updateSeqNo(seqNm, seqNo);
	}


	// 학교 정보 등록
	@Override
	public void insertSchInfo(@Param("schCd") String schCd, @Param("schNm") String schNm) throws Exception {
		webRegistMapper.insertSchInfo(schCd, schNm);
	}


	// 반 정보 등록
	@Override
	public void insertClassInfo(@Param("classCd") String classCd, @Param("classNm") String classNm) throws Exception {
		webRegistMapper.insertClassInfo(classCd, classNm);
	}	
	

	
	
}