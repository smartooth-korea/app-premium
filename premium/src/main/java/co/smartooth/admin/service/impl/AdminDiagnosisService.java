package co.smartooth.admin.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.smartooth.admin.vo.AdminDiagnosisVO;



/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 16
 * 수정일 : 2022. 11. 16
 */
public interface AdminDiagnosisService {
	
	
	// 최상위 진단 정보 조회
	public List<AdminDiagnosisVO> selectDiagDept1List() throws Exception;

	
	// 중위 진단 정보 조회 
	public List<AdminDiagnosisVO> selectDiagDept2List() throws Exception;

	
	// 진단 코드 및 설명 조회
	public String selectDiagDescript(@Param("descCd") String descCd) throws Exception;
	
	
}
