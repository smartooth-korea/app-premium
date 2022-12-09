package co.smartooth.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.admin.mapper.AdminMemoMapper;
import co.smartooth.admin.service.AdminMemoService;
import co.smartooth.admin.vo.AdminMemoVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public class AdminMemoServiceImpl implements AdminMemoService{
	
	
	@Autowired(required = false)
	AdminMemoMapper adminMemoMapper;

	
	// 측정 당시 메모 사항 조회
	@Override
	public String selectUserTeethMeasureMemo(AdminMemoVO adminMemoVO) throws Exception {
		return adminMemoMapper.selectUserTeethMeasureMemo(adminMemoVO);
	}

}