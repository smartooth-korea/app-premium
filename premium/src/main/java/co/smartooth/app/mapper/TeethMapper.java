package co.smartooth.app.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.smartooth.app.vo.TeethInfoVO;
import co.smartooth.app.vo.TeethMeasureVO;
import co.smartooth.app.vo.ToothMeasureVO;
import co.smartooth.app.vo.UserVO;

/**
 * 작성자 : 정주현
 * 작성일 : 2022. 04. 28
 */
@Mapper
public interface TeethMapper {

	// 회원 치아 측정 값 INSERT
	public void insertUserTeethMeasureValue(TeethMeasureVO teethMeasureVO) throws Exception;

	
	
	// 회원 개별 치아 측정 값 INSERT
	public void insertUserToothMeasureValue(ToothMeasureVO toothMeasureVO) throws Exception;

	
	
	// 회원 치아 측정 값 UPDATE
	public void updateUserTeethMeasureValue(TeethMeasureVO teethMeasureVO) throws Exception;

	
	
	// 회원 개별 치아 측정 값 UPDATE
	public void updateUserToothMeasureValue(ToothMeasureVO toothMeasureVO) throws Exception;

	
	
	// 회원 충치 개수 UPDATE (최근) - ST_STUDENT_USER_DETAIL
	public void updateUserCavityCnt(TeethMeasureVO teethMeasureVO) throws Exception;

	
	
	// 회원 충치 개수 UPDATE (측정일별)
	public void updateUserCavityCntByMeasureDt(TeethMeasureVO teethMeasureVO) throws Exception;

	
	
	// 회원 치아 측정 값을 저장하기 위해 현재 회원이 측정한 측정 값이 오늘 데이터인지 확인 후 값 반환(0 : 오늘X, 1: 오늘)
	public Integer isExistSysDateRow(TeethMeasureVO teethMeasureVO) throws Exception;

	
	
	// 회원 치아 상태 값 INSERT
	public void insertUserTeethInfo(TeethInfoVO teethInfoVO) throws Exception;

	
	
	// 회원의 치아 상태 값 조회
	public List<TeethInfoVO> selectUserTeethInfo(UserVO userVO) throws Exception;

	
	
	// 회원의 치아 측정 값 조회 (기간)
	public List<TeethMeasureVO> selectUserTeethMeasureValue(TeethMeasureVO teethMeasureVO) throws Exception;

	
	
	// 회원 치아 개별 측정 값 조회 (기간)
	public List<ToothMeasureVO> selectUserToothMeasureValue(ToothMeasureVO toothMeasureVO) throws Exception;

	
	
	// 회원 목록 측정 상태 조회
	public List<UserVO> selectStUserIsMeasuring(UserVO userVO) throws Exception;

	
	
	// 회원 치아 측정 상태 업데이트
	public void updateStUserIsMeasuring(UserVO userVO) throws Exception;

	
	
	// 회원 치아 측정 값 목록 조회 (최근 3개)
	public List<TeethMeasureVO> selectUserMeasureValueList(@Param("userId") String userId, @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception;

	
	
	// 충치 단계별 수치 조회
	public HashMap<String, Integer> selectCavityLevel() throws Exception;
	
	
	
	// 회원 진단 정보 조회 (측정일)
	public TeethMeasureVO selectDiagCd(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception;
	
	
	
	// 회원 진단 정보 업데이트
	public void updateDiagCd(@Param("userId") String userId, @Param("diagCd") String diagCd , @Param("measureDt") String measureDt) throws Exception;
	
	
	
	// 회원 비고(메모) 정보 조회 (측정일)
	public TeethMeasureVO selectMemo(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception;

	
	
	// 회원 메모(비고) 정보 업데이트 
	public void updateMemo(@Param("userId") String userId, @Param("memo") String memo, @Param("measureDt") String measureDt) throws Exception;
	
	
}