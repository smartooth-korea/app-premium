package co.smartooth.web.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.smartooth.web.vo.WebTeethInfoVO;
import co.smartooth.web.vo.WebTeethMeasureVO;
import co.smartooth.web.vo.WebToothMeasureVO;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
public interface WebTeethService {
	
	
	// 회원 치아 상태 값 INSERT
	public void insertUserTeethInfo(WebTeethInfoVO webTeethInfoVO) throws Exception;
	
	
	
	// 회원의 치아 상태 값 조회
	public List<WebTeethInfoVO> selectUserTeethInfo(WebUserVO webUserVO) throws Exception; 
	
	
	
	// 회원 치아 측정 값 INSERT
	public void insertUserTeethMeasureValue(WebTeethMeasureVO webTeethMeasureVO) throws Exception;

	
	
	// 회원 치아 측정 값 UPDATE
	public void updateUserTeethMeasureValue(WebTeethMeasureVO webTeethMeasureVO) throws Exception;
	
	
	
	// 회원 개별 치아 측정 값 INSERT
	public void insertUserToothMeasureValue(WebToothMeasureVO webToothMeasureVO) throws Exception;

	
	
	// 회원 개별 치아 측정 값 UPDATE
	public void updateUserToothMeasureValue(WebToothMeasureVO webToothMeasureVO) throws Exception;
	
	
	
	// 회원 충치 개수 UPDATE (최근) - ST_STUDENT_USER_DETAIL
	public void updateUserCavityCnt(WebTeethMeasureVO webTeethMeasureVO) throws Exception;
	
	
	
	// 회원 충치 개수 UPDATE (측정일별)
	public void updateUserCavityCntByMeasureDt(WebTeethMeasureVO webTeethMeasureVO) throws Exception;
	
	
	
	// 회원 치아 측정 값을 저장하기 위해 현재 회원이 측정한 측정 값이 오늘 데이터인지 확인 후 값 반환(0 : 오늘X, 1: 오늘)
	public Integer isExistSysDateRow(WebTeethMeasureVO webTeethMeasureVO) throws Exception;

	
	
	// 회원의 치아 측정 값 조회 (기간)
	public List<WebTeethMeasureVO> selectUserTeethMeasureValue(WebTeethMeasureVO webTeethMeasureVO) throws Exception;
	
	
	
	// 회원 치아 개별 측정 값 조회 (기간)
	public List<WebToothMeasureVO> selectUserToothMeasureValue(WebToothMeasureVO webToothMeasureVO) throws Exception;


	
	// 회원 치아 측정 상태 목록 조회 (IsMeasuring) 
    public List<WebUserVO> selectStUserIsMeasuring(WebUserVO webUserVO) throws Exception;
    
    
    
    // 회원 치아 측정 상태 업데이트
    public void updateStUserIsMeasuring(WebUserVO webUserVO) throws Exception;
    
    
    
	// 회원 치아 측정 값 목록 조회 (최근 3개)
    public List<WebTeethMeasureVO> selectUserMeasureValueList(@Param("userId") String userId, @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception;

    
    
    // 충치 단계별 수치 조회
	public HashMap<String, Integer> selectCavityLevel() throws Exception;
	
	
	
}
