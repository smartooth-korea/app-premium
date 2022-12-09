package co.smartooth.admin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.smartooth.admin.vo.AdminOrganVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
@Mapper
public interface AdminOrganMapper {
	

	// 기관 조회
	public List<AdminOrganVO> selectOrganList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception;

	
	// 기관 정보 등록
	public void insertOrganInfo(AdminOrganVO adminOrganVO) throws Exception;
    
	
	// 기관 정보 삭제
	public void deleteOrganInfo(String schoolCode) throws Exception;
    
}