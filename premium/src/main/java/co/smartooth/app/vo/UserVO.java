package co.smartooth.app.vo;

import java.io.Serializable;

public class UserVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int seqNo;
	private String userNo;
	private String userId;
	private String userName;
	private String userPwd;
	private String userType;
	private String userBirthday;
	private String userCountry;
	private String userState;
	private String userAddress;
	private String userTelNo;
	private String userSex;
	private String userRgstDt;
	private String pushToken;
	private String userDeleteYn;
	private String userDeleteDt;
	private String loginDt;
	private String userEmailYn;
	private int loginCk;
	private String userAuthToken;
	
	
	
	// STUDENT_USER_DEATAIL
	private String schoolType;
	private String isMeasuring;
	private String teacherId;
	
	
	// TEACHER_USER_DEATAIL
	private String schoolCode;
	private String classCode;
	private String className;

	
	// PARENT_USER_DETAIL
	private String studentId;
	
	
	
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserTelNo() {
		return userTelNo;
	}
	public void setUserTelNo(String userTelNo) {
		this.userTelNo = userTelNo;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserRgstDt() {
		return userRgstDt;
	}
	public void setUserRgstDt(String userRgstDt) {
		this.userRgstDt = userRgstDt;
	}
	public String getPushToken() {
		return pushToken;
	}
	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}
	public String getUserDeleteYn() {
		return userDeleteYn;
	}
	public void setUserDeleteYn(String userDeleteYn) {
		this.userDeleteYn = userDeleteYn;
	}
	public String getUserDeleteDt() {
		return userDeleteDt;
	}
	public void setUserDeleteDt(String userDeleteDt) {
		this.userDeleteDt = userDeleteDt;
	}
	public String getUserEmailYn() {
		return userEmailYn;
	}
	public void setUserEmailYn(String userEmailYn) {
		this.userEmailYn = userEmailYn;
	}
	public String getLoginDt() {
		return loginDt;
	}
	public void setLoginDt(String loginDt) {
		this.loginDt = loginDt;
	}	
	public int getLoginCk() {
		return loginCk;
	}
	public void setLoginCk(int loginCk) {
		this.loginCk = loginCk;
	}
	public String getUserAuthToken() {
        return userAuthToken;
    }
    public void setUserAuthToken(String userAuthToken) {
        this.userAuthToken = userAuthToken;
    }
    public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
    public String getIsMeasuring() {
		return isMeasuring;
	}
	public void setIsMeasuring(String isMeasuring) {
		this.isMeasuring = isMeasuring;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

}
