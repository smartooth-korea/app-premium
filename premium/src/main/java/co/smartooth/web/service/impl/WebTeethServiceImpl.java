package co.smartooth.web.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.web.mapper.WebTeethMapper;
import co.smartooth.web.service.WebTeethService;
import co.smartooth.web.vo.WebTeethInfoVO;
import co.smartooth.web.vo.WebTeethMeasureVO;
import co.smartooth.web.vo.WebToothMeasureVO;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public class WebTeethServiceImpl implements WebTeethService{
	
	
	@Autowired(required = false)
	WebTeethMapper webTeethMapper;

	
	// 회원 치아 상태 값 INSERT
	@Override
	public void insertUserTeethInfo(WebTeethInfoVO webTeethInfoVO) throws Exception {
		webTeethMapper.insertUserTeethInfo(webTeethInfoVO);
	}
	
	
	
	// 회원의 치아 상태 값 조회
	@Override
	public List<WebTeethInfoVO> selectUserTeethInfo(WebUserVO webUserVO) throws Exception {
		return webTeethMapper.selectUserTeethInfo(webUserVO);
	}
	
	
	
	// 회원 치아 측정 값 INSERT
	@Override
	public void insertUserTeethMeasureValue(WebTeethMeasureVO webTeethMeasureVO) throws Exception {
		webTeethMapper.insertUserTeethMeasureValue(webTeethMeasureVO);
	}

	
	
	// 회원 치아 측정 값 입력 UPDATE
	@Override
	public void updateUserTeethMeasureValue(WebTeethMeasureVO webTeethMeasureVO) throws Exception {
		webTeethMapper.updateUserTeethMeasureValue(webTeethMeasureVO);
	}
	
	
	
	// 회원 개별 치아 측정 값 INSERT
	@Override
	public void insertUserToothMeasureValue(WebToothMeasureVO webToothMeasureVO) throws Exception {
		webTeethMapper.insertUserToothMeasureValue(webToothMeasureVO);
	}
	
	
	
	// 회원 개별 치아 측정 값 UPDATE
	@Override
	public void updateUserToothMeasureValue(WebToothMeasureVO webToothMeasureVO) throws Exception {
		webTeethMapper.updateUserToothMeasureValue(webToothMeasureVO);
		
	}
	
	
	// 회원 치아 측정 값을 저장하기 위해 현재 회원이 측정한 측정 값이 오늘 데이터인지 확인 후 값 반환(0 : 오늘X, 1: 오늘)
	@Override
	public Integer isExistSysDateRow(WebTeethMeasureVO webTeethMeasureVO) throws Exception {
		return webTeethMapper.isExistSysDateRow(webTeethMeasureVO);
	}
	
	
	// 회원 충치 개수 UPDATE (최근) - ST_STUDENT_USER_DETAIL
	@Override
	public void updateUserCavityCnt(WebTeethMeasureVO webTeethMeasureVO) throws Exception {
		webTeethMapper.updateUserCavityCnt(webTeethMeasureVO);
	}

	
	
	// 회원 충치 개수 UPDATE (측정일별)
	@Override
	public void updateUserCavityCntByMeasureDt(WebTeethMeasureVO webTeethMeasureVO) throws Exception {
		webTeethMapper.updateUserCavityCntByMeasureDt(webTeethMeasureVO);
	}
	
	
	
	// 회원의 치아 측정 값 조회 (기간)
	@Override
	public List<WebTeethMeasureVO> selectUserTeethMeasureValue(WebTeethMeasureVO webTeethMeasureVO) throws Exception {
		return webTeethMapper.selectUserTeethMeasureValue(webTeethMeasureVO);
	}
	
	
	
	// 회원 치아 개별 측정 값 조회 (기간)
	@Override
	public List<WebToothMeasureVO> selectUserToothMeasureValue(WebToothMeasureVO webToothMeasureVO) throws Exception {
		return webTeethMapper.selectUserToothMeasureValue(webToothMeasureVO);
	}
	
	
	
	// 회원 치아 측정 상태 목록 조회 (IsMeasuring) 
	@Override
	public List<WebUserVO> selectStUserIsMeasuring(WebUserVO webUserVO) throws Exception {
		return webTeethMapper.selectStUserIsMeasuring(webUserVO);
	}

	
	
    // 회원 치아 측정 상태 업데이트
	@Override
	public void updateStUserIsMeasuring(WebUserVO webUserVO) throws Exception {
		webTeethMapper.updateStUserIsMeasuring(webUserVO);
	}

	
	
	// 회원 치아 측정 값 목록 조회 (최근 3개)
	@Override
    public List<WebTeethMeasureVO> selectUserMeasureValueList(@Param("userId") String userId, @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception {
		return webTeethMapper.selectUserMeasureValueList(userId, startDt, endDt);
	}

    

    // 충치 단계별 수치 조회
	@Override
	public HashMap<String, Integer> selectCavityLevel() throws Exception {
		return webTeethMapper.selectCavityLevel();
	}

}