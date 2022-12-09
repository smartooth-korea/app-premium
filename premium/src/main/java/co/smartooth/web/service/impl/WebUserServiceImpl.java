package co.smartooth.web.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.web.mapper.WebUserMapper;
import co.smartooth.web.service.WebUserService;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 7. 15 ~
 */
@Service
public class WebUserServiceImpl implements WebUserService{
	
	
	@Autowired(required = false)
	WebUserMapper webUserMapper;


	// 로그인 시 회원 정보 조회
	@Override
	public List<WebUserVO> selectUserInfo(WebUserVO webUserVO) throws Exception {
		return webUserMapper.selectUserInfo(webUserVO);
	}
	

	// 학생 정보 및 측정 데이터 조회 (선생님에게 배정된 학생 목록)
	@Override
	public List<WebUserVO> selectStUserInfoList(@Param("userId") String userId, @Param("orderBy") String orderBy) throws Exception {
		return webUserMapper.selectStUserInfoList(userId, orderBy);
	}


	// 학생 정보 및 측정 데이터 조회 (학생 한명)
	@Override
	public List<HashMap<String, Object>> selectStUserInfo(String userId) throws Exception {
		return webUserMapper.selectStUserInfo(userId);
	}
	
}