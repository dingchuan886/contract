package com.poly.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import car_beans.TbAttachUpload;
import car_beans.TbConFlow;
import car_beans.UserInfo;

import com.poly.bean.PageCondition;
import com.poly.bean.PageResult;
import com.poly.dao.subDaos.MailDao;
import com.poly.services.TbConFlowService;
import com.poly.services.TbFinUserService;
import com.poly.utils.MyConfig;

@Controller
@RequestMapping("/tbConFlowAudit")
public class TbConFlowAuditController {
	
	private TbConFlowService tbConFlowSerivce = new TbConFlowService();
	private TbFinUserService tbFinUserService = new TbFinUserService();
	private MailDao mailDao = new MailDao();
	/**
	 * 部门经理的审核列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/deptConAuditBill")
	public String listTbConFlow(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value="pageNum",defaultValue="1")int pageNum){
		this.setCharsetEncoding(request, response);
		String conType = request.getParameter("conType");
		model.addAttribute("act", "deptConAuditBill");
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
//		list = this.listConAuditBill(0, conType, userInfo.getUsercode(), pageNum, 10);
		PageResult<Map<String, Object>> pager = this.listConAuditBill(0, conType, userInfo.getUsercode(), pageNum, 10,null);
		model.addAttribute("pager", pager);
		model.addAttribute("conType", conType);
		String result = request.getParameter("result");
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("result", result);
		return "conAudit/detpConAuditBill";
	}
	
	/**
	 * 部门经理审核。
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/managerCheck")
	public String managerCheck(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redAtts){
		this.setCharsetEncoding(request, response);
//		String conId = request.getParameter("conId");
		String conFlowIdStr = request.getParameter("conFlowId");
		int conFlowId = 0;
		if(conFlowIdStr!=null){
			conFlowId = Integer.parseInt(conFlowIdStr);
		}
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String conType = request.getParameter("conType");
		TbConFlow conFlow = tbConFlowSerivce.getTbConFlowById(conFlowId);
		String areaCheck = conFlow.getArea();
		String flowCheck = conFlow.getFlow_check();
		String manager = conFlow.getManager();
		TbConFlow tbConFlow = new TbConFlow();
		tbConFlow.setCon_flow_id(conFlowId);
		
		tbConFlow.setCon_id(conFlow.getCon_id());
//		tbConFlow.setCon_id(con_id);
		if(manager!=null){
			manager = manager.replace("+" + userInfo.getUsercode() + "/", "-" + userInfo.getUsercode() + "/");
		}
		tbConFlow.setManager(manager);
		if(areaCheck!=null && !areaCheck.equals("")){
			tbConFlow.setNextcheck(areaCheck);
			tbConFlow.setCon_state(1);
		}else{
			if(flowCheck!=null && !flowCheck.equals("")){
				tbConFlow.setNextcheck(flowCheck);
				tbConFlow.setCon_state(7);
			}
		}
		String[] split = tbConFlow.getNextcheck().split("/");
		
		for(int i=0;i<split.length;i++){
			split[i] = split[i].replace("+", "");
		}
		List<String> phones = tbFinUserService.getUserPhoneByUserIds(Arrays.asList(split));
		for(String phone : phones){
			this.mailDao.sendMail("", "", "您有一条的合同审核申请，请尽快处理。", phone, "0");
		}
		
//		TbConFlow tbConFlowSeal = new TbConFlow();
//		tbConFlowSeal.setCon_flow_id(conFlowId);
//		tbConFlowSeal.setCon_state(2);//跳到7
//		tbConFlowSeal.setCon_id(conFlow.getCon_id());
//		tbConFlowSeal.setManager(manager);
//		tbConFlowSeal.setNextcheck(sealCheck);
//		int result = tbConFlowSerivce.managerCheck(tbConFlow, tbConFlowSeal, userInfo.getUsercode());
		//部门经理审核(这个审核过程，尼玛都改了八遍了，还特么的不能固定一下谁审核（也就三个审核而已。。）。我也是醉了。。。。)
		int result = tbConFlowSerivce.managerCheck(tbConFlow, userInfo.getUsercode());
//		int result = tbConFlowSerivce.managerCheck(tbConFlowSeal, userInfo.getUsercode());
		redAtts.addAttribute("conType", conType);
		if(result != -1){
			redAtts.addAttribute("result", "审核操作成功");
			return "redirect:deptConAuditBill";
//			return "result/auditPass";
		}
		redAtts.addAttribute("result", "审核失败");
		return "redirect:deptConAuditBill";
	}
	
	/**
	 * 区域部门的审核列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/areaConAuditBill")
	public String areaConAuditBill(PageCondition condition,HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value="pageNum",defaultValue="1")int pageNum){
		this.setCharsetEncoding(request, response);
		String conType = request.getParameter("conType");
		model.addAttribute("act", "deptConAuditBill");
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
//		list = this.listConAuditBill(0, conType, userInfo.getUsercode(), pageNum, 10);
		StringBuilder sb = new StringBuilder();
		if(condition.getOrgName()!=null && StringUtils.isNotBlank(condition.getOrgName())){
			if(conType.startsWith("SHCZ")){
			sb.append(" and con_id in (select con_cz_id from tb_con_cz where USER_ID in (select u.user_code from tb_fin_user u,tb_fin_org o where u.org_code=o.org_id and "
					+ "o.org_name like '%"+condition.getOrgName()+"%')) ");
			}else if(conType.startsWith("SHZH")){
				sb.append(" and con_id in (select con_zh_id from tb_con_zh where USER_ID in (select u.user_code from tb_fin_user u,tb_fin_org o where u.org_code=o.org_id and "
						+ "o.org_name like '%"+condition.getOrgName()+"%')) ");
			}else if(conType.startsWith("SHCT")){
				sb.append(" and con_id in (select con_cz_id from tb_con_cz where USER_ID in (select u.user_code from tb_fin_user u,tb_fin_org o where u.org_code=o.org_id and "
						+ "o.org_name like '%"+condition.getOrgName()+"%')) ");
			}
		}
		PageResult<Map<String, Object>> pager = this.listConAuditBill(1, conType, userInfo.getUsercode(), pageNum, 10,sb.toString());
		model.addAttribute("pager", pager);
		model.addAttribute("conType", conType);
		String result = request.getParameter("result");
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("result", result);
		model.addAttribute("pageCondition", condition);
		return "conAudit/areaConAuditBill";
	}
	
	
	/**
	 * 区域部门经理审核。
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/areaCheck")
	public String areaCheck(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redAtts){
		this.setCharsetEncoding(request, response);
		String conFlowIdStr = request.getParameter("conFlowId");
		int conFlowId = 0;
		if(conFlowIdStr!=null){
			conFlowId = Integer.parseInt(conFlowIdStr);
		}
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String conType = request.getParameter("conType");
		TbConFlow conFlow = tbConFlowSerivce.getTbConFlowById(conFlowId);
		String areaCheck = conFlow.getArea();
		
		String flowCheck = conFlow.getFlow_check();
		TbConFlow tbConFlow = new TbConFlow();
		tbConFlow.setCon_flow_id(conFlowId);
		tbConFlow.setCon_state(7);
		tbConFlow.setCon_id(conFlow.getCon_id());
		if(areaCheck!=null){
			areaCheck = areaCheck.replace("+" + userInfo.getUsercode() + "/", "-" + userInfo.getUsercode() + "/");
		}
		tbConFlow.setArea(areaCheck);
		tbConFlow.setNextcheck(flowCheck);
		String[] split = tbConFlow.getNextcheck().split("/");
		
		for(int i=0;i<split.length;i++){
			split[i] = split[i].replace("+", "");
		}
		List<String> phones = tbFinUserService.getUserPhoneByUserIds(Arrays.asList(split));
		for(String phone : phones){
			this.mailDao.sendMail("", "", "您有一条的合同审核申请，请尽快处理。", phone, "0");
		}
		int result = tbConFlowSerivce.managerCheck(tbConFlow, userInfo.getUsercode());
		redAtts.addAttribute("conType", conType);
		if(result != -1){
			redAtts.addAttribute("result", "审核操作成功");
			return "redirect:areaConAuditBill";
		}
		redAtts.addAttribute("result", "审核失败");
		return "redirect:areaConAuditBill";
	}
	
	/**
	 * 流程部审核
	 * @param request
	 * @param conFile
	 * @param response
	 * @param model
	 * @param redAtts
	 * @return
	 */
	@RequestMapping("/flowCheck")
	public String flowCheck(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redAtts){
		this.setCharsetEncoding(request, response);
		String conFlowIdStr = request.getParameter("conFlowId");
		int conFlowId = 0;
		if(conFlowIdStr!=null){
			conFlowId = Integer.parseInt(conFlowIdStr);
		}
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String conType = request.getParameter("conType");
		TbConFlow conFlow = tbConFlowSerivce.getTbConFlowById(conFlowId);
		String flowCheck = conFlow.getFlow_check();
		TbConFlow tbConFlow = new TbConFlow();
		if(flowCheck!=null){
			flowCheck = flowCheck.replace("+" + userInfo.getUsercode() + "/", "-" + userInfo.getUsercode() + "/");
		}
		tbConFlow.setCon_flow_id(conFlowId);
		tbConFlow.setFlow_check(flowCheck);
		tbConFlow.setCon_state(2);
		tbConFlow.setNextcheck(conFlow.getFlow_seal());
		tbConFlow.setCon_id(conFlow.getCon_id());
		int result = tbConFlowSerivce.flowCheck(tbConFlow, null, userInfo.getUsercode());
		redAtts.addAttribute("conType", conType);
		String[] split = tbConFlow.getNextcheck().split("/");
		
		for(int i=0;i<split.length;i++){
			split[i] = split[i].replace("+", "");
		}
		List<String> phones = tbFinUserService.getUserPhoneByUserIds(Arrays.asList(split));
		for(String phone : phones){
			this.mailDao.sendMail("", "", "您有一条的合同审核申请，请尽快处理。", phone, "0");
		}
		if(result != -1){
			redAtts.addAttribute("result", "审核操作成功");
			return "redirect:flowConAuditBill";
//			return "result/auditPass";
		}
		redAtts.addAttribute("result", "审核失败");
		return "redirect:flowConAuditBill";
	}
	
	/**
	 * 流程部门审核(归档)
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/flowSealCheck")
	public String flowSealCheck(HttpServletRequest request, MultipartFile conFile, HttpServletResponse response, Model model, RedirectAttributes redAtts){
		this.setCharsetEncoding(request, response);
		String conFlowIdStr = request.getParameter("conFlowId");
		int conFlowId = 0;
		if(conFlowIdStr!=null){
			conFlowId = Integer.parseInt(conFlowIdStr);
		}
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String conType = request.getParameter("conType");
		TbConFlow conFlow = tbConFlowSerivce.getTbConFlowById(conFlowId);
		String flowSeal = conFlow.getFlow_seal();
		TbConFlow tbConFlow = new TbConFlow();
		flowSeal = flowSeal.replace("+" + userInfo.getUsercode() + "/", "-" + userInfo.getUsercode() + "/");
		tbConFlow.setCon_flow_id(conFlowId);
		tbConFlow.setCon_state(3);
		tbConFlow.setFlow_seal(flowSeal);
		tbConFlow.setNextcheck(null);
		tbConFlow.setCon_id(conFlow.getCon_id());
		TbAttachUpload attach = null;
		if(!conFile.isEmpty()){
			String realPath = MyConfig.contract_upload_path;
			File realPathFolder = new File(realPath);
			if(!realPathFolder.exists()){
				realPathFolder.mkdirs();
			}
			try {
				String saveAttachFileName = System.currentTimeMillis()+"";
				String orFileName = conFile.getOriginalFilename();
				String suffix = this.getExtensionName(orFileName);
				FileUtils.copyInputStreamToFile(conFile.getInputStream(), new File(realPathFolder, saveAttachFileName+"."+suffix));
				String saveAddr = this.getRelativePath(realPath);
				attach = new TbAttachUpload();
				attach.setDownload_name(conFile.getOriginalFilename());
				attach.setFile_name(saveAttachFileName+"."+suffix);
				attach.setUpload_file(saveAddr);
				attach.setUpload_time(new Date());
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("msg", "合同扫描件上传失败！");
				return "result/result";
			}  
		}
		int result = tbConFlowSerivce.flowCheck(tbConFlow, attach, userInfo.getUsercode());
		redAtts.addAttribute("conType", conType);
		if(result != -1){
			redAtts.addAttribute("result", "归档操作成功");
			return "redirect:flowSealConAuditBill";
//			return "result/auditPass";
		}
		return "result/error";
	}
	
	
	private String getRelativePath(String realPath) {
		int i = realPath.lastIndexOf("/");
		if(i>-1 && i<realPath.length()-1){
			String str = realPath.substring(i);
			return str;
		}
		return realPath;
	}

	/**
	 * 部门经理审核驳回。
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/rejectedManager")
	public String rejectedManager(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redAtts) throws UnsupportedEncodingException{
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		this.rejected(request, response, model, redAtts, user);
		return "redirect:deptConAuditBill";
	}
	
	/**
	 * 区域经理审核驳回。
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/rejectedArea")
	public String rejectedArea(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redAtts) throws UnsupportedEncodingException{
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		this.rejected(request, response, model, redAtts, user);
		return "redirect:areaConAuditBill";
	}
	
	
	/**
	 * 流程部初步审核驳回
	 * @param request
	 * @param response
	 * @param model
	 * @param redAtts
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/rejectedFlow")
	public String rejectedFlow(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redAtts) throws UnsupportedEncodingException{
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		int result = this.rejected(request, response, model, redAtts, user);
		if(result!=-1){
			return "redirect:flowConAuditBill";
		}
		return "error";
	}
	
	/**
	 * 流程部盖章审核
	 * @param request
	 * @param response
	 * @param model
	 * @param redAtts
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/rejectedFlowSeal")
	public String rejectedFlowSeal(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redAtts) throws UnsupportedEncodingException{
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		int result = this.rejected(request, response, model, redAtts, user);
		if(result!=-1){
			return "redirect:flowSealConAuditBill";
		}
		return "error";
	}
	
	/**
	 * 流程部审核列表。
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/flowConAuditBill")
	public String flowBill(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value="pageNum",defaultValue="1")int pageNum){
		this.setCharsetEncoding(request, response);
		String conType = request.getParameter("conType");
		model.addAttribute("act", "flowConAuditBill");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
//		list = this.listConAuditBill(1, conType, userInfo.getUsercode(), pageNum, 10);
		PageResult<Map<String, Object>> pager = this.listConAuditBill(7, conType, userInfo.getUsercode(), pageNum, 10,null);
		model.addAttribute("pager", pager);
		model.addAttribute("conType", conType);
		String result = request.getParameter("result");
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("result", result);
		return "conAudit/lcbConAuditBill";
	}
	
	/**
	 * 流程盖章部审核列表(需要盖章审核)
	 * @param request
	 * @param response
	 * @param model
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/flowSealConAuditBill")
	public String flowSealBill(HttpServletRequest request, HttpServletResponse response, Model model, 
			@RequestParam(value="pageNum",defaultValue="1")int pageNum,PageCondition pageCondition){
		StringBuilder sb = new StringBuilder();
		this.setCharsetEncoding(request, response);
		String conType = request.getParameter("conType");
		if(conType.equals("SHCZ")){
			sb.append(" and CON_ID in(select CON_CZ_ID from tb_con_cz where 1=1 ");
			if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("0")){
				//DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(时间字段名)
				sb.append(" and DATE_SUB(CURDATE(),INTERVAL "+pageCondition.getCreateTime()+" DAY) <= date(`CREATE`) ");
			}
			if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
				sb.append(" and USER_NAME like '%"+pageCondition.getUserName()+"%' ");
			}
			if(pageCondition.getOrgName()!=null && !pageCondition.getOrgName().equals("")){
				sb.append(" and USER_ID in (select u.USER_CODE from tb_fin_user u,tb_fin_org o where u.ORG_CODE=o.ORG_ID and o.ORG_NAME like '%"+pageCondition.getOrgName()+"%') ");
			}
			sb.append(") ");
		}else if(conType.equals("SHZH")){
			sb.append(" and CON_ID in(select CON_ZH_ID from tb_con_zh where 1=1 ");
			if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("0")){
				//DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(时间字段名)
				sb.append(" and DATE_SUB(CURDATE(),INTERVAL "+pageCondition.getCreateTime()+" DAY) <= date(`CREATE`) ");
			}
			if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
				sb.append(" and USER_NAME like '%"+pageCondition.getUserName()+"%' ");
			}
			if(pageCondition.getOrgName()!=null && !pageCondition.getOrgName().equals("")){
				sb.append(" and USER_ID in (select u.USER_CODE from tb_fin_user u,tb_fin_org o where u.ORG_CODE=o.ORG_ID and o.ORG_NAME like '%"+pageCondition.getOrgName()+"%') ");
			}
			sb.append(") ");
		}else if(conType.equals("SHCT")){
			sb.append(" and CON_ID in(select CON_CT_ID from tb_con_ct where 1=1 ");
			if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("0")){
				//DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(时间字段名)
				sb.append(" and DATE_SUB(CURDATE(),INTERVAL "+pageCondition.getCreateTime()+" DAY) <= date(`CREATE`) ");
			}
			if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
				sb.append(" and USER_NAME like '%"+pageCondition.getUserName()+"%' ");
			}
			if(pageCondition.getOrgName()!=null && !pageCondition.getOrgName().equals("")){
				sb.append(" and USER_ID in (select u.USER_CODE from tb_fin_user u,tb_fin_org o where u.ORG_CODE=o.ORG_ID and o.ORG_NAME like '%"+pageCondition.getOrgName()+"%') ");
			}
			sb.append(") ");
		}
		model.addAttribute("act", "flowSealConAuditBill");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		PageResult<Map<String, Object>> pager = this.listConAuditBill(2, conType, userInfo.getUsercode(), pageNum, 10,sb.toString());
		model.addAttribute("pager", pager);
		model.addAttribute("conType", conType);
		String result = request.getParameter("result");
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("pageCondition", pageCondition);
		model.addAttribute("result", result);
		return "conAudit/lcbSealConAuditBill";
	}
	
	@RequestMapping("/lookRejectCon")
	public String lookRejectCon(Model model, HttpSession session, @RequestParam(value="pageNum",defaultValue="1")int pageNum,@RequestParam(value="conType",defaultValue="")String conType){
		String result = "error/error";
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		model.addAttribute("act", "lookRejectCon");
		PageResult<Map<String, Object>> pager = tbConFlowSerivce.lookRejectCon(conType, 4, user.getUsercode(), pageNum, 10);
		model.addAttribute("pager", pager);
		model.addAttribute("conType", conType);
		result = "conAudit/lookRejectCon";
		return result;
	}
	
	public int rejected(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redAtts, UserInfo user) throws UnsupportedEncodingException{
		this.setCharsetEncoding(request, response);
		String conFlowIdStr = request.getParameter("conFlowId");
		String conMsg = request.getParameter("conMsg");
		String conType = request.getParameter("conType");
		int conFlowId = 0;
		if(conFlowIdStr!=null){
			conFlowId = Integer.parseInt(conFlowIdStr);
		}
		TbConFlow conFlow = tbConFlowSerivce.getTbConFlowById(conFlowId);
		TbConFlow tbConFlow = new TbConFlow();
		tbConFlow.setCon_flow_id(conFlowId);
		tbConFlow.setCon_state(4);
		tbConFlow.setCon_id(conFlow.getCon_id());
		tbConFlow.setCon_msg(conMsg);
		int result = tbConFlowSerivce.rejected(tbConFlow, user.getUsercode());
		redAtts.addAttribute("conType", conType);
		if(result != -1){
			redAtts.addAttribute("result", "驳回操作成功");
		}else{
			redAtts.addAttribute("result", "驳回操作错误");
		}
		return result;
//		return "";
	}
	
	/**
	 * 获取可审核的合同列表。
	 * @param conState
	 * @param conType
	 * @param auditor
	 * @return
	 */
	private PageResult<Map<String, Object>> listConAuditBill(int conState, String conType, String auditor, int pageNum, int pageSize,String subsql){
		double t1 = System.currentTimeMillis();
		PageResult<Map<String, Object>> pager = null;
		if("SHCZ".equals(conType)){
			//众智车展SHCZ
			pager = tbConFlowSerivce.getConFlowConCzsByConState(conState, auditor, pageNum, pageSize,subsql);
//			pager = tbConFlowSerivce.getCzFlowPager(1, conState, auditor, pageNum, pageSize);
		} else if("SHZH".equals(conType)){
			//众智广告SHZH
			pager = tbConFlowSerivce.getConFlowConZhsByConState(conState, auditor, pageNum, pageSize,subsql);
//			pager = tbConFlowSerivce.getZhFlowPager(1, conState, auditor, pageNum, pageSize);
		} else if("SHCT".equals(conType)){
			//车团SHCT
			pager = tbConFlowSerivce.getConFlowConCtsByConState(conState, auditor, pageNum, pageSize,subsql);
//			pager = tbConFlowSerivce.getCtFlowPager(1, conState, auditor, pageNum, pageSize);
		}
		double t2 = System.currentTimeMillis();
		System.out.println("用了：" + (t2-t1) + "毫秒");
		return pager;
	}
	
	private  String getExtensionName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return filename.substring(dot + 1);   
            }   
        }   
        return filename;   
    }  
	
	private void setCharsetEncoding(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
}
