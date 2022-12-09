package co.smartooth.web.vo;


public class WebLocationVO {
	
	
	// 시,도 지역코드
	private String sidoCd;
	// 시,도 지역 이름
	private String sidoNm;
	// 시,군,구,읍,면,동 지역 코드
	private String sggemdCd;
	// 시,군,구 지역 이름
	private String sigunguNm;
	// 읍,면,동 지역 이름
	private String eupmyeondongNm;
	public String getSidoCd() {
		return sidoCd;
	}
	
	
	
	
	public void setSidoCd(String sidoCd) {
		this.sidoCd = sidoCd;
	}
	public String getSidoNm() {
		return sidoNm;
	}
	public void setSidoNm(String sidoNm) {
		this.sidoNm = sidoNm;
	}
	public String getSggemdCd() {
		return sggemdCd;
	}
	public void setSggemdCd(String sggemdCd) {
		this.sggemdCd = sggemdCd;
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
	
	
	
}
