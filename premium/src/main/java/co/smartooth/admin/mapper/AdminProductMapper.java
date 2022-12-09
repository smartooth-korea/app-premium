package co.smartooth.admin.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.smartooth.admin.vo.AdminProductVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 09
 */
@Mapper
public interface AdminProductMapper {
	
	
	// 시퀀스 넘버 조회
	public int selectSeqNo() throws Exception;

		
	// 시퀀스 넘버 업데이트
	public void updateSeqNo() throws Exception;

	
	// 제품 목록 조회
    public List<HashMap<String, Object>> selectProductInfo(@Param("searchField") String searchField, @Param("searchStr") String searchStr) throws Exception;
    
    
    // 제품 중복 확인
    public int isExistProductInfo(@Param("prodNo") String prodNo) throws Exception;
    
    
    // 제품 정보 등록
    public void insertProductInfo(AdminProductVO adminProductVO) throws Exception;
    
    
    // 제품 상세 정보 등록
    public void insertProductInfoDetail(AdminProductVO adminProductVO) throws Exception;
    
}