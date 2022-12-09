package co.smartooth.admin.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.admin.mapper.AdminDiagnosisMapper;
import co.smartooth.admin.vo.AdminDiagnosisVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 08. 03
 */
@Service
public class AdminDiagnosisServiceImpl implements AdminDiagnosisService{

	
	@Autowired(required = false)
	AdminDiagnosisMapper adminDiagnosisMapper;
	
	
	
	@Override
	public List<AdminDiagnosisVO> selectDiagDept1List() throws Exception {
		return adminDiagnosisMapper.selectDiagDept1List();
	}

	
	
	@Override
	public List<AdminDiagnosisVO> selectDiagDept2List() throws Exception {
		return adminDiagnosisMapper.selectDiagDept2List();
	}

	
	
	@Override
	public String selectDiagDescript(@Param("descCd") String descCd) throws Exception {
		return adminDiagnosisMapper.selectDiagDescript(descCd);
	}
	
	
	

	
}