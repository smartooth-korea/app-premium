package co.smartooth.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import co.smartooth.admin.vo.AdminMemoVO;

/**
 * 작성자 : 정주현 작성일 : 2022. 10. 31
 */
@Mapper
public interface AdminMemoMapper {

	// 측정 당시 메모 사항 조회 
	public String selectUserTeethMeasureMemo(AdminMemoVO adminMemoVO) throws Exception;
	
}