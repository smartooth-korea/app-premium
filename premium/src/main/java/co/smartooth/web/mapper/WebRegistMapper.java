package co.smartooth.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.smartooth.web.vo.WebLocationVO;
import co.smartooth.web.vo.WebSeqNoVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
@Mapper
public interface WebRegistMapper {
	
	
	// 나라 이름으로 나라 코드 조회 :: ISO_ALPHA2 코드 조회
	public String selectCountryCd(String countryNm) throws Exception;

	
	// 시도 이름으로 시도 코드 조회
	public String selectSidoCd(String siDoNm) throws Exception;

	
	// 지역 정보 조회
	public  WebLocationVO selectLocationInfo(WebLocationVO webLocationVO) throws Exception;
	
	
	// 시퀀스 번호 조회
	public WebSeqNoVO selectSeqNoAll() throws Exception;
	
	// 시퀀스 번호 업데이트
	public void updateSeqNo(@Param("seqNm") String seqNm, @Param("seqNo") String seqNo) throws Exception;
	
	// 학교 정보 등록
	public void insertSchInfo(@Param("schCd") String schCd, @Param("schNm") String schNm) throws Exception;
	
	// 반 정보 등록
	public void insertClassInfo(@Param("classCd") String classCd, @Param("classNm") String classNm) throws Exception;
    
}