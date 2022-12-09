package co.smartooth.admin.mapper;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 7. 18 ~
 */
import org.apache.ibatis.annotations.Mapper;

import co.smartooth.admin.vo.AdminAuthVO;


@Mapper
public interface AdminAuthMapper {

	// 회원 아이디 존재 여부 :: true = 1, false = 0
	public int loginChkByIdPwd(AdminAuthVO adminAuthVO) throws Exception;
	
	// 회원 아이디가 존재하는지 여부 확인 :: true = 1, false = 0
	public int isIdExist(AdminAuthVO adminAuthVO) throws Exception;

}
