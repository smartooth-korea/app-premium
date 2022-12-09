package co.smartooth.web.vo;

import java.io.Serializable;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 03
 */
public class WebUserVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int seqNo;
	private String userNo;
	private String userId;
	private String userName;
	private String userPwd;
	private String userType;
	private String userBirthday;
	private String countryNm;
	private String stateNm;
	private String sidoNm;
	private String sigunguNm;
	private String eupmyeondongNm;
	private String addrDetail;
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
	
	
	// ST_ST_USER :: STUDENT
	private String isMeasuring;
	private String teacherId;
	private String cavityCnt;
	
	
	// ST_TC_USER :: TEACHER
	private String schCd;
	private String classDepth;

	
	// ST_PA_USER :: PARENT
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
	public String getUserTelNo() {
		return userTelNo;
	}
	public void setUserTelNo(String userTelNo) {
		this.userTelNo = userTelNo;
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
	public String getCavityCnt() {
		return cavityCnt;
	}
	public void setCavityCnt(String cavityCnt) {
		this.cavityCnt = cavityCnt;
	}
	public String getSchCode() {
		return schCd;
	}
	public void setSchCd(String schCd) {
		this.schCd = schCd;
	}
	public String getClassDepth() {
		return classDepth;
	}
	public void setClassDepth(String classDepth) {
		this.classDepth = classDepth;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getCountryNm() {
		return countryNm;
	}
	public void setCountryNm(String countryNm) {
		this.countryNm = countryNm;
	}
	public String getStateNm() {
		return stateNm;
	}
	public void setStateNm(String stateNm) {
		this.stateNm = stateNm;
	}
	public String getSidoNm() {
		return sidoNm;
	}
	public void setSidoNm(String sidoNm) {
		this.sidoNm = sidoNm;
	}
	public String getSigunguNm() {
		return sigunguNm;
	}
	public void setSigunguNm(String sigunguNm) {
		this.sigunguNm = sigunguNm;
	}
	public String getEupmyeondongNm() {
		return eupmyeondongNm;
	}
	public void setEupmyeondongNm(String eupmyeondongNm) {
		this.eupmyeondongNm = eupmyeondongNm;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	public String getSchCd() {
		return schCd;
	}
	
}
