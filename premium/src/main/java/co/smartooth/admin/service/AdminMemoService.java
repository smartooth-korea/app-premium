package co.smartooth.admin.service;

import co.smartooth.admin.vo.AdminMemoVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
public interface AdminMemoService {
	
	
	// 측정 당시 메모 사항 조회
	public String selectUserTeethMeasureMemo(AdminMemoVO adminMemoVO) throws Exception;
	
}
