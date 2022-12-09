package co.smartooth.web.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
@Mapper
public interface WebUserMapper {
	

	// 회원 정보 조회
	public List<WebUserVO> selectUserInfo(WebUserVO webUserVO) throws Exception;

	
	// 학생 정보 및 측정 데이터 조회 (선생님에게 배정된 학생 목록)
    public List<WebUserVO> selectStUserInfoList(@Param("userId") String userId, @Param("orderBy") String orderBy) throws Exception;
	
	
	// 학생 정보 및 측정 데이터 조회 (학생 한명)
    public List<HashMap<String, Object>> selectStUserInfo(String userId) throws Exception;
    
    
    
}