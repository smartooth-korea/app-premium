package co.smartooth.app.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.smartooth.app.vo.SchoolClassInfoVO;
import co.smartooth.app.vo.UserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 08. 03
 */
public interface UserService {
	
	
	// 회원 아이디 중복 체크
	public int duplicateChkId(String userId) throws Exception;
	
	
	
	//	회원 등록 (회원가입)
	public void insertUserInfo(UserVO userVO) throws Exception;

	
	
	// 회원 정보 업데이트
	public void updateUserInfo(UserVO userVO) throws Exception;
	
	
	
	// 회원 번호 (회원 번호 생성 전 SEQ_NO) 조회
	public Integer selectUserSeqNo(@Param("userType") String userType) throws Exception;

	
	
	// 회원 번호 (회원 번호 생성 전 SEQ_STR) 조회
	public int selectUserSeqStr() throws Exception;

	
	
	// 회원 번호 (회원 번호 생성 후 SEQ_NO) 업데이트 
	public void updateUserSeqNo(@Param("userType") String userType, @Param("seqNo") int seqNo) throws Exception;

	
	
	// 회원 번호 (회원 번호 생성 후 SEQ_STR) 업데이트 
	public void updateUserSeqStr(@Param("seqStr") int seqStr) throws Exception;

	
	
	// 회원 정보 조회
	public List<UserVO> selectUserInfo(UserVO userVO) throws Exception;

	

	// 회원 비밀번호 변경(찾기)
	public void updateUserPwd(UserVO userVO) throws Exception;
	
	
	
	// 회원 삭제 (임시)
	// public void deleteUser(String userId) throws Exception;
	
	
	
	// 학교 목록 조회
	public List<SchoolClassInfoVO> selectSchoolList() throws Exception;
	
	
	
	// 선생님 회원 리스트 조회
	public List<UserVO> selectTcUserList(@Param("schoolCode") String schoolCode) throws Exception;

	
	
	// 선생님 ID로 해당 학생들 목록 조회
	public List<UserVO> selectStUserListByTc(String userId, String orderBy) throws Exception;
	
	
	
	
	
	
	// 학생 회원 상세 정보 등록
	public void insertStudentUserDetail(UserVO userVO) throws Exception;
	
	
	
	// 선생님 ID로 테스트계정 목록 조회
	public List<UserVO> selectTestUserListByTc(@Param("userId") String userId, @Param("userName") String userName) throws Exception;
	
	
	
	// 학생 이름 변경
	public void updateTestUserName(@Param("userId") String userId, @Param("userName") String userName) throws Exception;
	
}
