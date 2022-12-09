package co.smartooth.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.admin.mapper.AdminTeethMapper;
import co.smartooth.admin.service.AdminTeethService;
import co.smartooth.admin.vo.AdminTeethInfoVO;
import co.smartooth.admin.vo.AdminTeethMeasureVO;
import co.smartooth.admin.vo.AdminToothMeasureVO;
import co.smartooth.admin.vo.AdminUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public class AdminTeethServiceImpl implements AdminTeethService{
	
	
	@Autowired(required = false)
	AdminTeethMapper adminTeethMapper;


	// 회원 치아 상태 값 INSERT
	@Override
	public void insertUserTeethInfo(AdminTeethInfoVO adminTeethInfoVO) throws Exception {
		adminTeethMapper.insertUserTeethInfo(adminTeethInfoVO);
	}
	
	
	
	// 회원의 치아 상태 값 조회
	@Override
	public List<AdminTeethInfoVO> selectUserTeethInfo(AdminUserVO adminUserVO) throws Exception {
		return adminTeethMapper.selectUserTeethInfo(adminUserVO);
	}
	
	
	
	// 회원 치아 측정 값 INSERT
	@Override
	public void insertUserTeethMeasureValue(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception {
		adminTeethMapper.insertUserTeethMeasureValue(adminTeethMeasureVO);
	}

	
	
	// 회원 치아 측정 값 입력 UPDATE
	@Override
	public void updateUserTeethMeasureValue(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception {
		adminTeethMapper.updateUserTeethMeasureValue(adminTeethMeasureVO);
	}
	
	
	
	// 회원 개별 치아 측정 값 INSERT
	@Override
	public void insertUserToothMeasureValue(AdminToothMeasureVO adminToothMeasureVO) throws Exception {
		adminTeethMapper.insertUserToothMeasureValue(adminToothMeasureVO);
	}
	
	
	
	// 회원 개별 치아 측정 값 UPDATE
	@Override
	public void updateUserToothMeasureValue(AdminToothMeasureVO adminToothMeasureVO) throws Exception {
		adminTeethMapper.updateUserToothMeasureValue(adminToothMeasureVO);
		
	}
	
	
	// 회원 치아 측정 값을 저장하기 위해 현재 회원이 측정한 측정 값이 오늘 데이터인지 확인 후 값 반환(0 : 오늘X, 1: 오늘)
	@Override
	public Integer isExistSysDateRow(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception {
		return adminTeethMapper.isExistSysDateRow(adminTeethMeasureVO);
	}
	
	
	// 회원 충치 개수 UPDATE (최근) - ST_STUDENT_USER_DETAIL
	@Override
	public void updateUserCavityCnt(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception {
		adminTeethMapper.updateUserCavityCnt(adminTeethMeasureVO);
	}

	
	
	// 회원 충치 개수 UPDATE (측정일별)
	@Override
	public void updateUserCavityCntByMeasureDt(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception {
		adminTeethMapper.updateUserCavityCntByMeasureDt(adminTeethMeasureVO);
	}
	
	
	
	// 회원의 치아 측정 값 조회 (기간)
	@Override
	public List<AdminTeethMeasureVO> selectUserTeethMeasureValue(AdminTeethMeasureVO adminTeethMeasureVO) throws Exception {
		return adminTeethMapper.selectUserTeethMeasureValue(adminTeethMeasureVO);
	}
	
	
	
	// 회원 치아 개별 측정 값 조회 (기간)
	@Override
	public List<AdminToothMeasureVO> selectUserToothMeasureValue(AdminToothMeasureVO adminToothMeasureVO) throws Exception {
		return adminTeethMapper.selectUserToothMeasureValue(adminToothMeasureVO);
	}
	
	
	
	// 회원 치아 측정 상태 목록 조회 (IsMeasuring) 
	@Override
	public List<AdminUserVO> selectStUserIsMeasuring(AdminUserVO adminUserVO) throws Exception {
		return adminTeethMapper.selectStUserIsMeasuring(adminUserVO);
	}

	
	
    // 회원 치아 측정 상태 업데이트
	@Override
	public void updateStUserIsMeasuring(AdminUserVO adminUserVO) throws Exception {
		adminTeethMapper.updateStUserIsMeasuring(adminUserVO);
	}

	
	
	// 회원 치아 측정 값 목록 조회
	@Override
    public AdminTeethMeasureVO selectUserMeasureValueList(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception {
		return adminTeethMapper.selectUserMeasureValueList(userId, measureDt);
	}

    

    // 충치 단계별 수치 조회
	@Override
	public HashMap<String, Integer> selectCavityLevel() throws Exception {
		return adminTeethMapper.selectCavityLevel();
	}


	
	// 진단 제목 (diag-title) 업데이트
	@Override
	public void updateDiagTitle(@Param("userId") String userId, @Param("measureDt") String measureDt, @Param("diagTitle") String diagTitle) throws Exception{
		adminTeethMapper.updateDiagTitle(userId, measureDt, diagTitle);
	}


	
	// 진단 내용 (diag-decript) 업데이트 
	@Override
	public void updateDiagDescript(@Param("userId") String userId, @Param("measureDt") String measureDt, @Param("diagDescript") String diagDescript) throws Exception {
		adminTeethMapper.updateDiagDescript(userId, measureDt ,diagDescript);
	}

	
	
	// 비고 (memo) 업데이트 
	@Override
	// memo 업데이트
	public void updateMemo(@Param("userId") String userId, @Param("measureDt") String measureDt, @Param("memo") String memo) throws Exception{
		adminTeethMapper.updateMemo(userId, measureDt ,memo);
	}
	
	
	
	// 회원 치아 충치 값 목록 조회 (기관별) 
	@Override
	public List<HashMap<String, Object>> selectUserTeethMeasureValueByOrganCode(@Param("schoolCode") String schoolCode, @Param("measureDt") String measureDt) throws Exception {
		return adminTeethMapper.selectUserTeethMeasureValueByOrganCode(schoolCode, measureDt);
	}
	
	
	// 진단 제목 조회
	@Override
	public String selectDiagTitle(String diagCd) throws Exception{
		return adminTeethMapper.selectDiagTitle(diagCd);
	}

}