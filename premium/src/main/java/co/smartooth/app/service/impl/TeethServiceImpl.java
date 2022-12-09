package co.smartooth.app.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.app.mapper.TeethMapper;
import co.smartooth.app.service.TeethService;
import co.smartooth.app.vo.TeethInfoVO;
import co.smartooth.app.vo.TeethMeasureVO;
import co.smartooth.app.vo.ToothMeasureVO;
import co.smartooth.app.vo.UserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public class TeethServiceImpl implements TeethService{
	
	
	@Autowired(required = false)
	TeethMapper teethMapper;

	
	// 회원 치아 상태 값 INSERT
	@Override
	public void insertUserTeethInfo(TeethInfoVO teethInfoVO) throws Exception {
		teethMapper.insertUserTeethInfo(teethInfoVO);
	}
	
	
	
	// 회원의 치아 상태 값 조회
	@Override
	public List<TeethInfoVO> selectUserTeethInfo(UserVO userVO) throws Exception {
		return teethMapper.selectUserTeethInfo(userVO);
	}
	
	
	
	// 회원 치아 측정 값 INSERT
	@Override
	public void insertUserTeethMeasureValue(TeethMeasureVO teethMeasureVO) throws Exception {
		teethMapper.insertUserTeethMeasureValue(teethMeasureVO);
	}

	
	
	// 회원 치아 측정 값 입력 UPDATE
	@Override
	public void updateUserTeethMeasureValue(TeethMeasureVO teethMeasureVO) throws Exception {
		teethMapper.updateUserTeethMeasureValue(teethMeasureVO);
	}
	
	
	
	// 회원 개별 치아 측정 값 INSERT
	@Override
	public void insertUserToothMeasureValue(ToothMeasureVO toothMeasureVO) throws Exception {
		teethMapper.insertUserToothMeasureValue(toothMeasureVO);
	}
	
	
	
	// 회원 개별 치아 측정 값 UPDATE
	@Override
	public void updateUserToothMeasureValue(ToothMeasureVO toothMeasureVO) throws Exception {
		teethMapper.updateUserToothMeasureValue(toothMeasureVO);
		
	}
	
	
	// 회원 치아 측정 값을 저장하기 위해 현재 회원이 측정한 측정 값이 오늘 데이터인지 확인 후 값 반환(0 : 오늘X, 1: 오늘)
	@Override
	public Integer isExistSysDateRow(TeethMeasureVO teethMeasureVO) throws Exception {
		return teethMapper.isExistSysDateRow(teethMeasureVO);
	}
	
	
	// 회원 충치 개수 UPDATE (최근) - ST_STUDENT_USER_DETAIL
	@Override
	public void updateUserCavityCnt(TeethMeasureVO teethMeasureVO) throws Exception {
		teethMapper.updateUserCavityCnt(teethMeasureVO);
	}

	
	
	// 회원 충치 개수 UPDATE (측정일별)
	@Override
	public void updateUserCavityCntByMeasureDt(TeethMeasureVO teethMeasureVO) throws Exception {
		teethMapper.updateUserCavityCntByMeasureDt(teethMeasureVO);
	}
	
	
	
	// 회원의 치아 측정 값 조회 (기간)
	@Override
	public List<TeethMeasureVO> selectUserTeethMeasureValue(TeethMeasureVO teethMeasureVO) throws Exception {
		return teethMapper.selectUserTeethMeasureValue(teethMeasureVO);
	}
	
	
	
	// 회원 치아 개별 측정 값 조회 (기간)
	@Override
	public List<ToothMeasureVO> selectUserToothMeasureValue(ToothMeasureVO toothMeasureVO) throws Exception {
		return teethMapper.selectUserToothMeasureValue(toothMeasureVO);
	}
	
	
	
	// 회원 치아 측정 상태 목록 조회 (IsMeasuring) 
	@Override
	public List<UserVO> selectStUserIsMeasuring(UserVO userVO) throws Exception {
		return teethMapper.selectStUserIsMeasuring(userVO);
	}

	
	
    // 회원 치아 측정 상태 업데이트
	@Override
	public void updateStUserIsMeasuring(UserVO userVO) throws Exception {
		teethMapper.updateStUserIsMeasuring(userVO);
	}

	
	
	// 회원 치아 측정 값 목록 조회 (최근 3개)
	@Override
    public List<TeethMeasureVO> selectUserMeasureValueList(@Param("userId") String userId, @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception {
		return teethMapper.selectUserMeasureValueList(userId, startDt, endDt);
	}

    

    // 충치 단계별 수치 조회
	@Override
	public HashMap<String, Integer> selectCavityLevel() throws Exception {
		return teethMapper.selectCavityLevel();
	}
	
	
	// 회원 진단 정보 조회 (측정일)
	@Override
	public TeethMeasureVO selectDiagCd(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception{
		return teethMapper.selectDiagCd(userId, measureDt);
	}


	// 회원 진단 정보 업데이트 
	@Override
	public void updateDiagCd(@Param("userId") String userId, @Param("diagCd") String diagCd , @Param("measureDt") String measureDt) throws Exception {
		teethMapper.updateDiagCd(userId, diagCd, measureDt);
	}


	// 회원 비고(메모) 정보 조회 (측정일)
	@Override
	public TeethMeasureVO selectMemo(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception{
		return teethMapper.selectMemo(userId, measureDt);
	}
	
	
	// 회원 비고(메모) 정보 업데이트
	@Override
	public void updateMemo(@Param("userId") String userId, @Param("memo") String memo, @Param("measureDt") String measureDt) throws Exception {
		teethMapper.updateMemo(userId, memo, measureDt);
	}
	

}