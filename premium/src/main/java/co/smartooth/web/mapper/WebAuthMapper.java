package co.smartooth.web.mapper;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 7. 18 ~
 */
import org.apache.ibatis.annotations.Mapper;

import co.smartooth.web.vo.WebAuthVO;


@Mapper
public interface WebAuthMapper {

	// 회원 아이디 존재 여부 :: true = 1, false = 0
	public int loginChkByIdPwd(WebAuthVO webAuthVO) throws Exception;
	
	// 회원 아이디가 존재하는지 여부 확인 :: true = 1, false = 0
	public int isIdExist(WebAuthVO webAuthVO) throws Exception;

}
