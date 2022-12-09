package co.smartooth.admin.controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.smartooth.admin.service.AdminLocationService;
import co.smartooth.admin.service.AdminOrganService;
import co.smartooth.admin.service.AdminUserService;
import co.smartooth.admin.vo.AdminLocationVO;
import co.smartooth.admin.vo.AdminOrganVO;
import co.smartooth.admin.vo.AdminUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 09
 * 수정일 : 
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class AdminOrganController {
	
	@Value("${loginUrl}")
    private String loginUrl;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	

	@Autowired(required = false)
	private AdminOrganService adminOrganService;
	
	@Autowired(required = false)
	private AdminUserService adminUserService;
	
	@Autowired(required = false)
	private AdminLocationService adminLocationService;
//
//	@Autowired(required = false)
//	private AdminMemoService adminMemoService;
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 09
	 * 기능   : 프리미엄 앱에 등록 되어있는 기관 조회 페이지 반환
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/premium/admin/organ/selectOrganList"})
	public String AjaxSelectOrganList(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		
		@SuppressWarnings("unchecked")
		AdminUserVO sessionList = (AdminUserVO)session.getAttribute("userInfo");
		if(sessionList == null ) {
			return "redirect:/premium/admin/login";
		}
		
		String userId = sessionList.getUserId();
		AdminUserVO userInfo = new AdminUserVO();
		List<AdminLocationVO> sidoList = new ArrayList<AdminLocationVO>();
		
		// 시도의 지역 코드와 이름 조회
		sidoList = adminLocationService.selectSidoInfoList();
		
		// 로그인 한 아이디의 권한 조회
		userInfo = adminUserService.selectUserInfo(userId);

		model.addAttribute("sidoList", sidoList);
		model.addAttribute("userInfo", userInfo);

		return "/premium/admin/organ/selectOrganList";
		
	}
	
	
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 10
	 * 기능   : 프리미엄 앱에 등록 되어있는 기관 조회
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/premium/admin/organ/selectOrganList.do"})
	public @ResponseBody String selectOrganList(HttpServletRequest request, HttpSession session, Model model, @RequestParam Map<String, String> paramMap) throws Exception {
		
		@SuppressWarnings("unchecked")
		AdminUserVO sessionList = (AdminUserVO)session.getAttribute("userInfo");
		if(sessionList == null ) {
			return "redirect:/premium/admin/login";
		}
		
		String sggemdCd = null;
		
		AdminLocationVO locationCdList = new AdminLocationVO();
		
		String searchData = paramMap.get("searchData");
		String searchType = paramMap.get("searchType");
		String userId = sessionList.getUserId();
	
		
		List<AdminOrganVO> organList = new ArrayList<AdminOrganVO>();
		List<AdminUserVO> userInfo = new ArrayList<AdminUserVO>();

		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
	
		if(searchData == null) {
			searchData = "";
		}
		if(searchType == null) {
			searchType = "";
		}
		
		
		// 등록되어있는 기관(치과, 어린이집, 유치원, 학교)를 조회
		if(!searchType.equals("SIGUNGU_NM") && !searchType.equals("EUPMYEONDONG_NM") ) {
			organList = adminOrganService.selectOrganList(searchType, searchData);
		}else {

			// 지역이름을 검색하기 전에 양천구 혹은 신정1동 과 같은 경우 
			
			if(searchData.contains("\"[^0-9]\"") && searchData.contains("동")) {
				searchData = searchData.substring(0, searchData.indexOf("\"[^0-9]\""));
			}else if(searchData.contains("동")) {
				searchData = searchData.substring(0, searchData.indexOf("동"));
			}
			
			if(searchData.contains("\"[^0-9]\"") && searchData.contains("구")) {
				searchData = searchData.substring(0, searchData.indexOf("\"[^0-9]\""));
			}else if(searchData.contains("구")) {
				searchData = searchData.substring(0, searchData.indexOf("구"));
			}
			
			// 지역이름으로 지역코드(SGGMD_CD) 조회
			locationCdList = adminLocationService.selectLocationCd(searchType, searchData);
			// 양천구로 조회했을때 SGGMD_CD를 150으로 받으면 해당 SGGMD_CD로 전체를 LIKE로 검색
			sggemdCd = locationCdList.getSggemdCd();
			// 나라 학교 타입으로 검색해야함 : 150이 나왔다 그럼 이 150으로 유치원 코드로가서 검색을 해야함
			organList = adminOrganService.selectOrganList(searchType, sggemdCd);
			
		}
		
		for(int i=0; i < organList.size(); i++) {
			
			String schoolCode = organList.get(i).getSchoolCode();
			String sidoCd = null;
			String sigunguNm = null;
			
			String eupMyeonDongNm = null;
			
			List<AdminLocationVO> locationList = new ArrayList<AdminLocationVO>();
					
			if(schoolCode.length() == 14) {
				sidoCd = schoolCode.substring(4,6);
				sggemdCd = schoolCode.substring(6,12);
				
				locationList = adminLocationService.selectLocationInfo(sidoCd, sggemdCd);
				sigunguNm = locationList.get(0).getSigunguNm();
				eupMyeonDongNm = locationList.get(0).getEupmyeondongNm();
			}
			
			JSONObject tmpObj = new JSONObject();
			
			tmpObj.put("SCHOOL_CODE", schoolCode);
			tmpObj.put("SCHOOL_NAME", organList.get(i).getSchoolName());
			tmpObj.put("SIGUNGU_NM", sigunguNm);
			tmpObj.put("EUPMYEONDONG_NM", eupMyeonDongNm);
			jsonArray.add(tmpObj);
		}
		
		jsonObject.put("rows", jsonArray);
		
		return jsonObject.toJSONString(); 
		
	}
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 17
	 * 기능   : 기관 등록 페이지
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/premium/admin/registOrganInfo"})
	public String registOrganInfo(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		
		@SuppressWarnings("unchecked")
		AdminUserVO sessionList = (AdminUserVO)session.getAttribute("userInfo");
		if(sessionList == null ) {
			return "redirect:/premium/admin/login";
		}
		
		String userId = sessionList.getUserId();
		AdminUserVO userInfo = new AdminUserVO();
		List<AdminLocationVO> sidoList = new ArrayList<AdminLocationVO>();
		
		
		// 로그인 한 아이디의 권한 조회
		userInfo = adminUserService.selectUserInfo(userId);
		
		// 시도의 지역 코드와 이름 조회
		sidoList = adminLocationService.selectSidoInfoList();
		
		
		
		model.addAttribute("sidoList", sidoList);
		model.addAttribute("userInfo", userInfo);

		return "/premium/admin/organ/registOrganInfo";
		
	}
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 17
	 * 기능   : 기관 등록 (DB INSERT)
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/premium/admin/insertOrganInfo"})
	@ResponseBody
	public String adminLoginForm(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception {

		AdminLocationVO adminLocationVO = new AdminLocationVO(); 
		AdminOrganVO adminOrganVO = new AdminOrganVO();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject tmpObj = new JSONObject();
		
		String schoolCode = null;
		// form으로 전송받은 데이터를 INSERT 시켜줘야함
		String schoolName = request.getParameter("schoolName");
		String schoolType = request.getParameter("schoolType");
		String sidoCd = request.getParameter("sido");
		String eupmyeondongCd = request.getParameter("eupmyeondong");
		int addrSeqNo = Integer.parseInt(eupmyeondongCd.substring(eupmyeondongCd.indexOf("|")+1,eupmyeondongCd.length()));
		
		eupmyeondongCd = eupmyeondongCd.substring(0,  eupmyeondongCd.indexOf("|"));
		addrSeqNo += 1;

		/**해당 부분의 KR은 하드코딩이므로 접속하는 나라에 따라서 다르도록 설정이 가능한지 알아봐야함**/
		// 각 각의 읍면동에 대한 seq번호를 넣어서 학교코드를 만들어주고 그 이후 seq를 업데이트
		schoolCode = "KR"+schoolType+sidoCd+eupmyeondongCd+String.format("%02d", addrSeqNo);
		
		adminLocationVO.setAddrSeqNo(addrSeqNo);
		adminLocationVO.setSggemdCd(eupmyeondongCd);
		// 각 각 읍면동에 대한 SeqNo 업데이트 eupmyeondongCd, String.valueOf(addrSeqNo++)
		adminLocationService.updateAddrSeqNo(adminLocationVO);		
		
		adminOrganVO.setSchoolName(schoolName);
		adminOrganVO.setSchoolCode(schoolCode);
		adminOrganVO.setIsVisible("Y");
		
		try {
			// 기관 정보 등록
			adminOrganService.insertOrganInfo(adminOrganVO);
			tmpObj.put("data", "success");
			jsonArray.add(tmpObj);
			jsonObject.put("rows", jsonArray);
			return jsonObject.toJSONString(); 
			
		} catch (Exception e) {
			tmpObj.put("data", "error");
			jsonArray.add(tmpObj);
			jsonObject.put("rows", jsonArray);
			return jsonObject.toJSONString(); 
		}
	}
		
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 23
	 * 기능   : 기관 삭제 (DB DELETE)
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = {"/premium/admin/deleteOrganInfo"})
	@ResponseBody
	public String deleteOrganInfo(@RequestBody HashMap<String, Object> paramMap) throws Exception{

		HashMap<String,Object> hm = new HashMap<String, Object>();
		ArrayList<String> schoolCodeArray = new ArrayList<String>(); 
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject tmpObj = new JSONObject();
		
		int schoolCodeArrayLength = Integer.parseInt(paramMap.get("schoolCodeArrayLength").toString());

		schoolCodeArray = (ArrayList<String>) paramMap.get("schoolCodeArray");
		
		
		try {
			// 기관 정보 등록
			for(int i=0; i < schoolCodeArrayLength; i++) {
				adminOrganService.deleteOrganInfo(schoolCodeArray.get(i)); 
			}
			tmpObj.put("data", "success");
			jsonArray.add(tmpObj);
			jsonObject.put("rows", jsonArray);
			return jsonObject.toJSONString(); 
			
		} catch (Exception e) {
			tmpObj.put("data", "error");
			jsonArray.add(tmpObj);
			jsonObject.put("rows", jsonArray);
			return jsonObject.toJSONString(); 
		}
	}
}
