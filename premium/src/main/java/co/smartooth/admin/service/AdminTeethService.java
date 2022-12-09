package co.smartooth.admin.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.smartooth.admin.vo.AdminTeethInfoVO;
import co.smartooth.admin.vo.AdminTeethMeasureVO;
import co.smartooth.admin.vo.AdminToothMeasureVO;
import co.smartooth.admin.vo.AdminUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
public interface AdminTeethService {
	
	
	// 회원 치아 상태 값 INSERT
	public void insertUserTeethInfo(AdminTeethInfoVO webTeethInfoVO) throws Exception;
	
	
	
	// 회원의 치아 상태 값 조회
	public List<AdminTeethInfoVO> selectUserTeethInfo(AdminUserVO adminUserVO) throws Exception; 
	
	
	
	// 회원 치아 측정 값 INSERT
	public void insertUserTeethMeasureValue(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception;

	
	
	// 회원 치아 측정 값 UPDATE
	public void updateUserTeethMeasureValue(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception;
	
	
	
	// 회원 개별 치아 측정 값 INSERT
	public void insertUserToothMeasureValue(AdminToothMeasureVO adminToothMeasureVO) throws Exception;

	
	
	// 회원 개별 치아 측정 값 UPDATE
	public void updateUserToothMeasureValue(AdminToothMeasureVO adminToothMeasureVO) throws Exception;
	
	
	
	// 회원 충치 개수 UPDATE (최근) - ST_STUDENT_USER_DETAIL
	public void updateUserCavityCnt(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception;
	
	
	
	// 회원 충치 개수 UPDATE (측정일별)
	public void updateUserCavityCntByMeasureDt(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception;
	
	
	
	// 회원 치아 측정 값을 저장하기 위해 현재 회원이 측정한 측정 값이 오늘 데이터인지 확인 후 값 반환(0 : 오늘X, 1: 오늘)
	public Integer isExistSysDateRow(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception;

	
	
	// 회원의 치아 측정 값 조회 (기간)
	public List<AdminTeethMeasureVO> selectUserTeethMeasureValue(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception;
	
	
	
	// 회원 치아 개별 측정 값 조회 (기간)
	public List<AdminToothMeasureVO> selectUserToothMeasureValue(AdminToothMeasureVO adminToothMeasureVO) throws Exception;


	
	// 회원 치아 측정 상태 목록 조회 (IsMeasuring) 
    public List<AdminUserVO> selectStUserIsMeasuring(AdminUserVO adminUserVO) throws Exception;
    
    
    
    // 회원 치아 측정 상태 업데이트
    public void updateStUserIsMeasuring(AdminUserVO adminUserVO) throws Exception;
    
    
    
	// 회원 치아 측정 값 목록 조회
    public AdminTeethMeasureVO selectUserMeasureValueList(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception;

    
    
    // 충치 단계별 수치 조회
	public HashMap<String, Integer> selectCavityLevel() throws Exception;
	
	
	
	// 진단 제목 (diag-title) 업데이트
	public void updateDiagTitle(@Param("userId") String userId, @Param("measureDt") String measureDt, @Param("diagTitle") String diagTitle) throws Exception;

	
	
	// 진단 내용 (diag-decript) 업데이트 
	public void updateDiagDescript(@Param("userId") String userId, @Param("measureDt") String measureDt, @Param("diagDescript") String diagDescript) throws Exception;

	
	
	// 비고 (memo) 업데이트
	public void updateMemo(@Param("userId") String userId, @Param("measureDt") String measureDt, @Param("memo") String memo) throws Exception;
	
	
	
	// 회원 치아 충치 값 목록 조회 (기관별) 
	public List<HashMap<String, Object>> selectUserTeethMeasureValueByOrganCode(@Param("schoolCode") String schoolCode, @Param("measureDt") String measureDt) throws Exception;
	
	
	// 진단 제목 조회
	public String selectDiagTitle(String diagCd) throws Exception;
	
}
