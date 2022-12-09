package co.smartooth.admin.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.smartooth.admin.vo.AdminLocationVO;
import co.smartooth.admin.vo.AdminOrganVO;
import co.smartooth.admin.vo.AdminUserVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
@Mapper
public interface AdminLocationMapper {
	

	// 지역구 조회
	public List<AdminLocationVO> selectLocationInfo(@Param("sidoCd") String sidoCd, @Param("sggemdCd") String sggemdCd) throws Exception;
    
    
	// 지역이름으로 지역코드(SGGMD_CD) 조회
	public AdminLocationVO selectLocationCd(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception;
	
	
	// 전체 시도 코드, 이름 조회
	public List<AdminLocationVO> selectSidoInfoList() throws Exception;
	
	
	// 전체 시군구 읍면동 코드, 이름 조회
	public List<AdminLocationVO> selectSigunguEupmyeondongInfoList(@Param("locationType") String locationType, @Param("locationCd") String locationCd) throws Exception;
	
	
	// 각 각 읍면동에 대한 SeqNo 업데이트
	public void updateAddrSeqNo(AdminLocationVO adminLocationVO) throws Exception;
	
}