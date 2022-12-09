package co.smartooth.web.service;


import org.apache.ibatis.annotations.Param;

import co.smartooth.web.vo.WebLocationVO;
import co.smartooth.web.vo.WebSeqNoVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 01
 */
public interface WebRegistService {
	
	
	// 나라 이름으로 나라 코드 조회 :: ISO_ALPHA2 코드 조회
	public String selectCountryCd(String nationalNameKor) throws Exception;
	
	// 시도 이름으로 시도 코드 조회
	public String selectSiDoCd(String siDoNm) throws Exception;

	// 지역 정보 조회
	public WebLocationVO selectLocationInfo(WebLocationVO webLocationVO) throws Exception;
	
	// 전체 시퀀스 조회
	public WebSeqNoVO selectSeqNoAll() throws Exception;
	
	// 시퀀스 증가
	public void updateSeqNo(@Param("seqNm") String seqNm, @Param("seqNo") String seqNo) throws Exception;

	// 학교 정보 등록
	public void insertSchInfo(@Param("schCd") String schCd, @Param("schNm") String schNm) throws Exception;
	
	// 반 정보 등록
	public void insertClassInfo(@Param("classCd") String classCd, @Param("classNm") String classNm) throws Exception;
	
	
	
	
}
