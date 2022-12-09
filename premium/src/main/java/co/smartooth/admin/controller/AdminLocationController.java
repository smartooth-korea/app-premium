package co.smartooth.admin.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.smartooth.admin.service.AdminLocationService;
import co.smartooth.admin.vo.AdminLocationVO;
import co.smartooth.admin.vo.AdminUserVO;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 08
 * 수정일 : 
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class AdminLocationController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	

	@Autowired(required = false)
	private AdminLocationService adminLocationService;
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 17
	 * 기능   : 시군구, 읍면동 정보 조회
	 * @throws Exception 
	 */
	@PostMapping(value = {"/premium/admin/selectSigunguInfoList.do"})
	@ResponseBody
	public HashMap<String,Object> selectSigunguInfoList(@RequestBody HashMap<String, String> paramMap){

		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		String locationType = "SIDO_CD";
		String locationCd = (String)paramMap.get("sidoCd");
		List<AdminLocationVO> locationInfoList = new ArrayList<AdminLocationVO>();
		
		try {
			
			// 시도의 지역 코드와 이름 조회
			locationInfoList = adminLocationService.selectSigunguEupmyeondongInfoList(locationType, locationCd);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("msg", "Server Error.");
		}
		
		hm.put("locationInfoList", locationInfoList);
		
		return hm;
	}
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 17
	 * 기능   : 시군구, 읍면동 정보 조회
	 * @throws Exception 
	 */
	@PostMapping(value = {"/premium/admin/selectEupmyeondognInfoList.do"})
	@ResponseBody
	public HashMap<String,Object> selectEupmyeondognInfoList(@RequestBody HashMap<String, String> paramMap){
		
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		String locationType = "SGGEMD_CD";
		String locationCd = (String)paramMap.get("sggemdCd");
		String sigunguNm = (String)paramMap.get("sigunguNm");
		List<AdminLocationVO> locationInfoList = new ArrayList<AdminLocationVO>();
		
		try {
			
			// 시도의 지역 코드와 이름 조회
			locationInfoList = adminLocationService.selectSigunguEupmyeondongInfoList(locationType, locationCd);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("msg", "Server Error.");
		}
		
		hm.put("locationInfoList", locationInfoList);
		
		return hm;
	}
	
	
	
}
