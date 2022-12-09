package co.smartooth.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.smartooth.admin.vo.AdminOrganVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 08
 */
public interface AdminOrganService {
	
	
	// 기관 조회 : default 값을 정해야하며, 검색이 될 수 있도록 해야함
	// 지역별 : 그럴 경우에 SCHOOL_CODE로 분리를 해야함
	public List<AdminOrganVO> selectOrganList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception;
	
	
	// 기관 정보 등록
	public void insertOrganInfo(AdminOrganVO adminOrganVO) throws Exception;
	
	
	// 기관 정보 삭제
	public void deleteOrganInfo(String schoolCode) throws Exception;
	
}
