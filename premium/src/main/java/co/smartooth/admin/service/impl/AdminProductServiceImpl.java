package co.smartooth.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.admin.mapper.AdminProductMapper;
import co.smartooth.admin.service.AdminProductService;
import co.smartooth.admin.vo.AdminProductVO;
import co.smartooth.web.mapper.WebProductMapper;
import co.smartooth.web.service.WebProductService;
import co.smartooth.web.vo.WebProductVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 09
 */
@Service
public class AdminProductServiceImpl implements AdminProductService{
	
	
	@Autowired(required = false)
	AdminProductMapper adminProductMapper;


	
	// 시퀀스 넘버 조회
	@Override
	public int selectSeqNo() throws Exception {
		return adminProductMapper.selectSeqNo();
	}

	
	// 시퀀스 넘버 업데이트
	@Override
	public void updateSeqNo() throws Exception{
		adminProductMapper.updateSeqNo();
	}
	

	// 제품 목록 조회
	@Override
	public List<HashMap<String, Object>> selectProductInfo(@Param("searchField") String searchField, @Param("searchStr") String searchStr) throws Exception {
		return adminProductMapper.selectProductInfo(searchField, searchStr);
	}


	// 제품 중복 확인
	@Override
	public int isExistProductInfo(@Param("prodNo") String prodNo) throws Exception {
		return adminProductMapper.isExistProductInfo(prodNo);
	}


	// 제품 정보 등록
	@Override
	public void insertProductInfo(AdminProductVO adminProductVO) throws Exception {
		adminProductMapper.insertProductInfo(adminProductVO);
	}


	// 제품 상세 정보 등록
	@Override
	public void insertProductInfoDetail(AdminProductVO adminProductVO) throws Exception {
		adminProductMapper.insertProductInfoDetail(adminProductVO);
	}
	

	
}