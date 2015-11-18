package com.poly.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import car_beans.TbFinDep;
import car_beans.TbFinOrg;
import car_beans.TbFinRole;
import car_beans.TbFinUser;
import car_beans.UserInfo;
import car_daos.TbFinDepDao;
import car_daos.TbFinOrgDao;
import car_daos.TbFinRoleDao;

import com.poly.services.TbFinUserService;
import com.poly.utils.MD5;
import com.poly.utils.MyConfig;
import com.sun.org.apache.xml.internal.security.utils.Base64;

@Controller
@RequestMapping
public class LoginController {
	
	private TbFinUserService tbfinuserService = new TbFinUserService();

	@RequestMapping("/login")
	public String findUser(ModelMap root,HttpSession session){
		List<TbFinUser> tbfinuserList = tbfinuserService.findUser();
		session.setAttribute("contract_ct_url", MyConfig.contract_ct_url);
		session.setAttribute("contract_ct_url_html", MyConfig.contract_ct_url);
		root.put("userList",tbfinuserList);
		return "login";
	}
	//http://contract.chetuan.com/contract/login?userid=Poly0005&token=5722f79876bfec4ad54d9ec74b7151b3
	/*
	 * 邱民Poly0025
	 * 殷文琦Poly0222
	 * 方围Poly0085
	 * 毛娣娣Poly0064
	 * 严向阳Poly0001
	 * 
	 * */
	/*@RequestMapping("/login")
	public String responseFtl(HttpServletResponse response,HttpSession session,HttpServletRequest request,Model model){
		String data = request.getParameter("data");
		String token = "";
		String userid = "";
		String tokensub = createToken();
		if(data!=null){
			BASE64Decoder decode = new BASE64Decoder();
			try {
				byte[] buffer = decode.decodeBuffer(data);
				String str = new String(buffer);
				JSONObject json = JSONObject.fromObject(str);
				userid = json.getString("userid");
				token = json.getString("token");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			return "redirect:http://oa.chetuan.com";
		}
		
		if(!token.equals("")){
			if(!token.equals(tokensub)){
				return "redirect:http://oa.chetuan.com";
			}
			
		}else{
			return "redirect:http://oa.chetuan.com";
		}
		
		TbFinUser user = null;
		
		if(userid!=null){
			user = tbfinuserService.findUserByUserId(userid);
			
		}else{
			return "redirect:http://oa.chetuan.com";
		}
		
		UserInfo userInfo = new UserInfo();
		session.setAttribute("contract_ct_url_html", MyConfig.contract_ct_url_html);
		session.setAttribute("contract_ct_url", MyConfig.contract_ct_url);
		if(user != null){
			boolean flag = getAuth(user);
			if(!flag){
				session.removeAttribute("userInfo");
				model.addAttribute("msg", "对不起，您没有权限");
				return "result/result";
			}
			userInfo.setUserCity(user.getUser_city());
			userInfo.setUserdis(user.getUser_dis());
			userInfo.setUsercode(user.getUser_code());
			userInfo.setUsername(user.getUser_name());
			userInfo.setDeptcode(user.getDept_code());
			List<TbFinDep> where = TbFinDepDao.where(" DEP_ID=" + user.getDept_code());
			String deptName = where.get(0).getDep_name();
			userInfo.setDeptname(deptName);
			userInfo.setOrgcode(user.getOrg_code());
			List<TbFinOrg> where2 = TbFinOrgDao.where(" ORG_ID=" + user.getOrg_code());
			String orgName = where2.get(0).getOrg_name();
			userInfo.setOrgname(orgName);
			userInfo.setPassword(user.getPassword());
			userInfo.setUsercode(user.getUser_code());
			List<TbFinRole> where3 = TbFinRoleDao.where(" ROLE_CODE=" + user.getUser_role());
			String roleName = where3.get(0).getRole_name();
			userInfo.setRolename(roleName);
			userInfo.setRolecode(user.getUser_role());
			
		}
		
		session.setAttribute("userInfo", userInfo);
		return "index";
	}*/
	private boolean getAuth(TbFinUser user) {
		boolean flag = false;
		String orgcode = user.getOrg_code();
		String usercode = user.getUser_code();
		String rolecode = user.getUser_role();
		String deptCode = user.getDept_code();
		String usercity = user.getUser_city();
		String userdis = user.getUser_dis();
		if(deptCode.equals("3") || deptCode.equals("13") || deptCode.equals("17") || deptCode.equals("2")){
			flag = true;
		}else if(usercode.equals("Poly0085") || usercode.equals("Poly0064") || usercode.equals("Poly0221") || usercode.equals("Poly0024") || usercode.equals("Poly0007") || usercode.equals("Poly0005")){
			
			flag = true;
		}else if(deptCode.equals("5") || deptCode.equals("7") || 
				(deptCode.equals("1")&&rolecode.equals("4")&&!orgcode.equals("1") || deptCode.equals("18"))){
			flag = true;
		}else if(rolecode.equals("1")){
			flag = true;
		}else if(usercode.equals("Poly0004") || usercode.equals("Poly0066") || usercode.equals("Poly0051") || 
				usercode.equals("Poly0218") || usercode.equals("Poly0646") || usercode.equals("Poly0473")){
			flag = true;
		}else if(deptCode.equals("4") && rolecode.equals("3") && !orgcode.equals("1")){
			flag = true;
		}else if(deptCode.equals("11") && rolecode.equals("3") && !orgcode.equals("1")){
			flag = true;
		}else if(usercity!=null && StringUtils.isNotBlank(usercity) && !usercity.equals("##")){
			flag = true;
		}else if(userdis!=null && StringUtils.isNotBlank(userdis) && !userdis.equals("##")){
			flag = true;
		}
		return flag;
	}
	@RequestMapping("/contract")
	public String responseFtl(HttpServletResponse response,HttpSession session,HttpServletRequest request){
		String userid = request.getParameter("userid");
		TbFinUser user = null;
		session.setAttribute("contract_ct_url", MyConfig.contract_ct_url);
		if(userid!=null){
			user = tbfinuserService.findUserByUserId(userid);
		}else{
			return "redirect:http://oa.chetuan.com";
		}
		
		UserInfo userInfo = new UserInfo();
		if(user != null){
			
			userInfo.setUsercode(user.getUser_code());
			userInfo.setUsername(user.getUser_name());
			userInfo.setDeptcode(user.getDept_code());
			List<TbFinDep> where = TbFinDepDao.where(" DEP_ID=" + user.getDept_code());
			String deptName = where.get(0).getDep_name();
			userInfo.setDeptname(deptName);
			userInfo.setOrgcode(user.getOrg_code());
			List<TbFinOrg> where2 = TbFinOrgDao.where(" ORG_ID=" + user.getOrg_code());
			String orgName = where2.get(0).getOrg_name();
			userInfo.setOrgname(orgName);
			userInfo.setPassword(user.getPassword());
			userInfo.setUsercode(user.getUser_code());
			List<TbFinRole> where3 = TbFinRoleDao.where(" ROLE_CODE=" + user.getUser_role());
			String roleName = where3.get(0).getRole_name();
			userInfo.setRolename(roleName);
			userInfo.setRolecode(user.getUser_role());
			userInfo.setUserdis(user.getUser_dis());
			userInfo.setUserCity(user.getUser_city());
		}
		session.setAttribute("userInfo", userInfo);
		return "index";
	}
	
	@RequestMapping("/toIndex")
	public String toIndex(Model model){
		model.addAttribute("msg", "欢迎访问合同管理");
		
		return "result/result";
	}
	
	String createToken(){
		String token = "";
		String key1 = "www.finance.chetuan.com";
		String key2 = "www.315che.com";
		String key3 = "0f1340da76c0305a793ad45b69bc01b0";
		token = MD5.toMD5(key1+key2+key3);
		return token;
	}
	public static void main(String[] args) {
		BASE64Decoder decode = new BASE64Decoder();
		BASE64Encoder encode = new BASE64Encoder();
		String str1 = "{'userid':'Poly0699','token':'5722f79876bfec4ad54d9ec74b7151b3'}";
		String encode2;
		try {
			encode2 = encode.encode(str1.getBytes("utf-8"));
			System.out.println(encode2);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			byte[] buffer = decode.decodeBuffer("eyd1c2VyaWQnOidQb2x5MDA0MCcsJ3Rva2VuJzonNTcyMmY3OTg3NmJmZWM0YWQ1NGQ5ZWM3NGI3MTUxYjMnfQ==");
			String str = new String(buffer);
			JSONObject json = JSONObject.fromObject(str);
			String userid = json.getString("userid");
			String token = json.getString("token");
			System.out.println(userid);
			System.out.println(token);
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
