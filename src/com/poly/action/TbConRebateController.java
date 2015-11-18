package com.poly.action;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import car_beans.TbConAccount;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConRebate;
import car_beans.TbConRebateFlow;
import car_beans.TbConZh;
import car_beans.UserInfo;
import car_daos.TbConAccountDao;
import car_daos.TbConBillDao;
import car_daos.TbConRebateDao;
import car_daos.TbConRebateFlowDao;

import com.poly.bean.PageCondition;
import com.poly.bean.PageResult;
import com.poly.dao.subDaos.MailDao;
import com.poly.services.TbConAccountService;
import com.poly.services.TbConCtService;
import com.poly.services.TbConCzService;
import com.poly.services.TbConRebateFlowService;
import com.poly.services.TbConZhService;
import com.poly.services.TbFinUserService;

@Controller
@RequestMapping("/tbConRebate")
public class TbConRebateController {
	
	private TbConRebateFlowService conRebateFlowService = new TbConRebateFlowService();
	private TbFinUserService tbFinUserService = new TbFinUserService();
	private TbConAccountService conAccountService = new TbConAccountService();
	private MailDao mailDao = new MailDao();
	
	/**
	 * 进入返利申请列表
	 * @param model
	 * @param session
	 * @param conId
	 * @return
	 */
	@RequestMapping("/toAddRebate")
	public String toAddRebate(Model model, HttpSession session, @RequestParam(value="conId",defaultValue="")String conId){
		String result = "result/result";
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		TbConAccount account = conAccountService.getConAccountByConId(conId);
		if(account==null){
			model.addAttribute("msg", "对不起，该合同为没有填写合同相关说明！");
			return result;
		}else if(account.getIsback()==0){
			model.addAttribute("msg", "对不起，该合同为不返利的合同！");
			return result;
		}
		if(conId.startsWith("SHCT")){
			TbConCt conCt = TbConCtService.getConCtById(conId);
	 		model.addAttribute("conCt", conCt);
			model.addAttribute("user", user);
			model.addAttribute("applyDate", new Date());
			result = "rebate/ctRebateAdd";
		}else if(conId.startsWith("SHCZ")){
			TbConCz conCz = TbConCzService.getConCzById(conId);
			model.addAttribute("conCz", conCz);
			model.addAttribute("user", user);
			model.addAttribute("applyDate", new Date());
			result = "rebate/czRebateAdd";
		}else if(conId.startsWith("SHZH")){
			TbConZh conZh = TbConZhService.getConZhById(conId);
			model.addAttribute("conZh", conZh);
			model.addAttribute("user", user);
			model.addAttribute("applyDate", new Date());
			result = "rebate/zhRebateAdd";
		}
		return result;
	}
	
	
	/**
	 * 保存返利申请
	 * @param model
	 * @param conRebate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveRebate")
	public String saveRebate(Model model,TbConRebate conRebate, HttpSession session,HttpServletRequest request){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String applyDate = request.getParameter("applyDate");
		Date appDate = null;
		try {
			appDate = new SimpleDateFormat("yyyy-MM-dd").parse(applyDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		conRebate.setCreate(appDate);
		conRebate.setUpdate(appDate);
		conRebate.setUser_name(user.getUsername());
		int flag = conRebateFlowService.saveTbConRebate(conRebate, user);
		if(flag==-1){
			model.addAttribute("msg", "对不起，请重新操作！");
			return "result/result";
		}
		model.addAttribute("msg", "保存成功！");
		return "result/result";
	}
	
	
	/**
	 * 部门经理审核列表
	 * @param result
	 * @param model
	 * @param session
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/rebateManagerBill")
	public String rebateManagerBill(
				@RequestParam(value="result",defaultValue="")String result, 
				@RequestParam(value="pageNum",defaultValue="1")int pageNum,
				Model model, HttpSession session){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		PageResult<Map<String, Object>> pager = conRebateFlowService.getPager(0, user.getUsercode(), pageNum, 10,null);
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("result", result);
		model.addAttribute("pager", pager);
		return "rebateAudit/rebateManagerBill";
	}
	
	
	/**
	 * 部门经理审核
	 * @param rebateFlowId
	 * @param session
	 * @param model
	 * @param redAtts
	 * @return
	 */
	@RequestMapping("/managerCheck")
	public String managerCheck(@RequestParam(value="rebateFlowId",defaultValue="0")int rebateFlowId, HttpSession session, Model model, RedirectAttributes redAtts){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TbConRebateFlow rebateFlow = conRebateFlowService.getRebateFlowById(rebateFlowId);
		String areaCheck = rebateFlow.getArea();
		String manager = rebateFlow.getManager();
		TbConRebateFlow flow = new TbConRebateFlow();
		flow.setRebate_flow_id(rebateFlowId);
		String flowCheck = rebateFlow.getFlow_check();
		flow.setRebate_id(rebateFlow.getRebate_id());
		if(manager!=null){
			manager = manager.replace("+" + userInfo.getUsercode() + "/", "-" + userInfo.getUsercode() + "/");
		}
		flow.setManager(manager);
		if(areaCheck!=null && !areaCheck.equals("")){
			flow.setNextcheck(areaCheck);
			flow.setRebate_state(1);
		}else{
			if(!flowCheck.equals("")){
				flow.setNextcheck(flowCheck);
				flow.setRebate_state(7);
			}
		}
		int result = conRebateFlowService.checkOrReject(flow, userInfo.getUsercode());
		if(result != -1){
			redAtts.addAttribute("result", "审核操作成功");
			return "redirect:rebateManagerBill";
		}
		model.addAttribute("msg", "对不起，操作有误。。。请返回原页面刷新，重新操作！");
		return "result/result";
	}
	
	@RequestMapping("/managerReject")
	public String managerReject(@RequestParam(value="rebateFlowId",defaultValue="0")int rebateFlowId, @RequestParam(value="rebateMsg",defaultValue="")String rebateMsg, HttpSession session, Model model, RedirectAttributes redAtts){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TbConRebateFlow rebateFlow = conRebateFlowService.getRebateFlowById(rebateFlowId);
		TbConRebateFlow flow = new TbConRebateFlow();
		flow.setRebate_flow_id(rebateFlowId);
		flow.setRebate_state(4);
		flow.setRebate_id(rebateFlow.getRebate_id());
		flow.setRebate_msg(rebateMsg);
		int result = conRebateFlowService.checkOrReject(flow, userInfo.getUsercode());
		if(result != -1){
			redAtts.addAttribute("result", "驳回操作成功");
			return "redirect:rebateManagerBill";
		}
		model.addAttribute("msg", "对不起，操作有误。。。请返回原页面刷新，重新操作！");
		return "result/result";
	}
	
	/**
	 * 区域经理审核列表
	 * @param result
	 * @param model
	 * @param session
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/rebateAreaBill")
	public String rebateAreaBill(
				@RequestParam(value="result",defaultValue="")String result, 
				@RequestParam(value="pageNum",defaultValue="1")int pageNum,
				Model model, HttpSession session){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		PageResult<Map<String, Object>> pager = conRebateFlowService.getPager(1, user.getUsercode(), pageNum, 10,null);
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("result", result);
		model.addAttribute("pager", pager);
		return "rebateAudit/rebateAreaBill";
	}
	
	/**
	 * 区域经理审核
	 * @param rebateFlowId
	 * @param session
	 * @param model
	 * @param redAtts
	 * @return
	 */
	@RequestMapping("/areaCheck")
	public String areaCheck(@RequestParam(value="rebateFlowId",defaultValue="0")int rebateFlowId, HttpSession session, Model model, RedirectAttributes redAtts){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TbConRebateFlow rebateFlow = conRebateFlowService.getRebateFlowById(rebateFlowId);
		String flowCheck = rebateFlow.getFlow_check();
		String areaCheck = rebateFlow.getArea();
		TbConRebateFlow flow = new TbConRebateFlow();
		flow.setRebate_flow_id(rebateFlowId);
		flow.setRebate_state(7);
		flow.setRebate_id(rebateFlow.getRebate_id());
		if(areaCheck!=null){
			areaCheck = areaCheck.replace("+" + userInfo.getUsercode() + "/", "-" + userInfo.getUsercode() + "/");
		}
		flow.setArea(areaCheck);
		flow.setNextcheck(flowCheck);
		int result = conRebateFlowService.checkOrReject(flow, userInfo.getUsercode());
		if(result != -1){
			redAtts.addAttribute("result", "审核操作成功");
			return "redirect:rebateAreaBill";
		}
		model.addAttribute("msg", "对不起，操作有误。。。请返回原页面刷新，重新操作！");
		return "result/result";
	}
	
	/**
	 * 区域经理驳回
	 * @param rebateFlowId
	 * @param rebateMsg
	 * @param session
	 * @param model
	 * @param redAtts
	 * @return
	 */
	@RequestMapping("/areaReject")
	public String areaReject(
			@RequestParam(value="rebateFlowId",defaultValue="0")int rebateFlowId, 
			@RequestParam(value="rebateMsg",defaultValue="")String rebateMsg, 
			HttpSession session, Model model, RedirectAttributes redAtts){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TbConRebateFlow rebateFlow = conRebateFlowService.getRebateFlowById(rebateFlowId);
		TbConRebateFlow flow = new TbConRebateFlow();
		flow.setRebate_flow_id(rebateFlowId);
		flow.setRebate_state(4);
		flow.setRebate_id(rebateFlow.getRebate_id());
		flow.setRebate_msg(rebateMsg);
		int result = conRebateFlowService.checkOrReject(flow, userInfo.getUsercode());
		if(result != -1){
			redAtts.addAttribute("result", "驳回操作成功");
			return "redirect:rebateAreaBill";
		}
		model.addAttribute("msg", "对不起，操作有误。。。请返回原页面刷新，重新操作！");
		return "result/result";
	}
	
	
	/**
	 * 流程部审核列表
	 * @param result
	 * @param model
	 * @param session
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/rebateFlowBill")
	public String rebateFlowBill(@RequestParam(value="result",defaultValue="")String result, Model model, HttpSession session, @RequestParam(value="pageNum",defaultValue="1")int pageNum){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		PageResult<Map<String, Object>> pager = conRebateFlowService.getPager(7, user.getUsercode(), pageNum, 10,null);
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("result", result);
		model.addAttribute("pager", pager);
		return "rebateAudit/rebateFlowBill";
	}
	
	/**
	 * 流程部审核
	 * @param rebateFlowId
	 * @param session
	 * @param model
	 * @param redAtts
	 * @return
	 */
	@RequestMapping("/flowCheck")
	public String flowCheck(@RequestParam(value="rebateFlowId",defaultValue="")String rebateFlowId, HttpSession session, Model model, RedirectAttributes redAtts){
		int flowId = 0;
		if(rebateFlowId!=null){
			flowId = Integer.parseInt(rebateFlowId);
		}
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TbConRebateFlow rebateFlow = conRebateFlowService.getRebateFlowById(flowId);
		String hqManager = rebateFlow.getHqmanager();
		String flowCheck = rebateFlow.getFlow_check();
		TbConRebateFlow flow = new TbConRebateFlow();
		flow.setRebate_flow_id(flowId);
		flow.setRebate_state(2);
		flow.setRebate_id(rebateFlow.getRebate_id());
		if(flowCheck!=null){
			flowCheck = flowCheck.replace("+" + userInfo.getUsercode() + "/", "-" + userInfo.getUsercode() + "/");
		}
		flow.setFlow_check(flowCheck);
		flow.setNextcheck(hqManager);
		int result = conRebateFlowService.checkOrReject(flow, userInfo.getUsercode());
		if(result != -1){
			redAtts.addAttribute("result", "审核操作成功");
			String[] split = flow.getNextcheck().split("/");
			for(int i=0;i<split.length;i++){
				split[i] = split[i].replace("+", "");
			}
			List<String> phones = tbFinUserService.getUserPhoneByUserIds(Arrays.asList(split));
			for(String phone : phones){
				this.mailDao.sendMail("", "", "您有一条新的返利申请需要审核，请尽快处理。", phone, "0");
			}
			return "redirect:rebateFlowBill";
		}
		model.addAttribute("msg", "对不起，操作有误。。。请返回原页面刷新，重新操作！");
		return "result/result";
	}
	/**
	 * 流程部驳回
	 * @param rebateFlowId
	 * @param rebateMsg
	 * @param session
	 * @param model
	 * @param redAtts
	 * @return
	 */
	@RequestMapping("/flowReject")
	public String flowReject(
				@RequestParam(value="rebateFlowId",defaultValue="0")int rebateFlowId, 
				@RequestParam(value="rebateMsg",defaultValue="")String rebateMsg, 
				HttpSession session, Model model, RedirectAttributes redAtts){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TbConRebateFlow rebateFlow = conRebateFlowService.getRebateFlowById(rebateFlowId);
		TbConRebateFlow flow = new TbConRebateFlow();
		flow.setRebate_flow_id(rebateFlowId);
		flow.setRebate_state(4);
		flow.setRebate_id(rebateFlow.getRebate_id());
		flow.setRebate_msg(rebateMsg);
		int result = conRebateFlowService.checkOrReject(flow, userInfo.getUsercode());
		if(result != -1){
			redAtts.addAttribute("result", "驳回操作成功");
			return "redirect:rebateFlowBill";
		}
		model.addAttribute("msg", "对不起，操作有误。。。请返回原页面刷新，重新操作！");
		return "result/result";
	}
	
	/**
	 * 总经理审核列表
	 * @param result
	 * @param model
	 * @param session
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/rebateHQManagerBill")
	public String rebateHQManagerBill(@RequestParam(value="result",defaultValue="")String result, Model model, HttpSession session, @RequestParam(value="pageNum",defaultValue="1")int pageNum){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		PageResult<Map<String, Object>> pager = conRebateFlowService.getPager(2, user.getUsercode(), pageNum, 10,null);
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("result", result);
		model.addAttribute("pager", pager);
		return "rebateAudit/rebateHQManagerBill";
	}
	
	/**
	 * 总经理审核
	 * @param rebateFlowId
	 * @param session
	 * @param model
	 * @param redAtts
	 * @return
	 */
	@RequestMapping("/hqManagerCheck")
	public String hqManagerCheck(@RequestParam(value="rebateFlowId",defaultValue="")String rebateFlowId, HttpSession session, Model model, RedirectAttributes redAtts){
		int flowId = 0;
		if(rebateFlowId!=null){
			flowId = Integer.parseInt(rebateFlowId);
		}
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TbConRebateFlow rebateFlow = conRebateFlowService.getRebateFlowById(flowId);
		String hqManager = rebateFlow.getHqmanager();
		TbConRebateFlow flow = new TbConRebateFlow();
		flow.setRebate_flow_id(flowId);
		flow.setRebate_state(3);
		flow.setRebate_id(rebateFlow.getRebate_id());
		if(hqManager!=null){
			hqManager = hqManager.replace("+" + userInfo.getUsercode() + "/", "-" + userInfo.getUsercode() + "/");
		}
		flow.setHqmanager(hqManager);
		flow.setNextcheck(null);
		int result = conRebateFlowService.checkOrReject(flow, userInfo.getUsercode());
		if(result != -1){
			redAtts.addAttribute("result", "审核操作成功");
			return "redirect:rebateHQManagerBill";
		}
		model.addAttribute("msg", "对不起，操作有误。。。请返回原页面刷新，重新操作！");
		return "result/result";
	}
	
	/**
	 * 总经理驳回。
	 * @param rebateFlowId
	 * @param rebateMsg
	 * @param session
	 * @param model
	 * @param redAtts
	 * @return
	 */
	@RequestMapping("/hqManagerReject")
	public String hqManagerReject(@RequestParam(value="rebateFlowId",defaultValue="0")int rebateFlowId, @RequestParam(value="rebateMsg",defaultValue="")String rebateMsg, HttpSession session, Model model, RedirectAttributes redAtts){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TbConRebateFlow rebateFlow = conRebateFlowService.getRebateFlowById(rebateFlowId);
		TbConRebateFlow flow = new TbConRebateFlow();
		flow.setRebate_flow_id(rebateFlowId);
		flow.setRebate_state(4);
		flow.setRebate_id(rebateFlow.getRebate_id());
		flow.setRebate_msg(rebateMsg);
		int result = conRebateFlowService.checkOrReject(flow, userInfo.getUsercode());
		if(result != -1){
			redAtts.addAttribute("result", "驳回操作成功");
			return "redirect:rebateHQManagerBill";
		}
		model.addAttribute("msg", "对不起，操作有误。。。请返回原页面刷新，重新操作！");
		return "result/result";
	}
	
	
	/**
	 * 财务审核列表
	 * @param result
	 * @param model
	 * @param session
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/finConfirmBill")
	public String finConfirmBill(
				@RequestParam(value="result",defaultValue="")String result, 
				@RequestParam(value="pageNum",defaultValue="1")int pageNum,
				Model model, HttpSession session,
				PageCondition pageCondition){
		StringBuilder sb = new StringBuilder();
		if(pageCondition.getConType()!=null && !pageCondition.getConType().equals("")){
			sb.append(" and CON_ID like '"+pageCondition.getConType()+"%' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			sb.append(" and USER_NAME like '%"+pageCondition.getUserName()+"%' ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		String subsql = " and REBATE_ID in (select BACK_ID from tb_con_rebate where 1=1 "+sb.toString()+")";
		PageResult<Map<String, Object>> pager = conRebateFlowService.getPager(3, null, pageNum, 10,subsql);
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("result", result);
		model.addAttribute("pager", pager);
		model.addAttribute("pageCondition", pageCondition);
		return "rebateAudit/finConfirmBill";
	}
	
	
	/**
	 * 财务确认。
	 * @param result
	 * @param model
	 * @param session
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/finConfirm")
	public String finConfirm(HttpSession session, 
			@RequestParam(value="result",defaultValue="")String result, 
			@RequestParam(value="rebateId", defaultValue="0")int rebateId, 
			@RequestParam(value="rebateAccount", defaultValue="0.00")double rebateAccount,
			@RequestParam(value="rebateTime", defaultValue="1970-01-01")String rebateTime,
			@RequestParam(value="pageNum", defaultValue="1")int pageNum,
			@RequestParam(value="conId", defaultValue="")String conId,
			Model model, RedirectAttributes redAtts){
		
//		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		int flag = conRebateFlowService.confirmRebate(rebateId, conId,  rebateTime, rebateAccount);
		redAtts.addAttribute("pageNum", pageNum);
		if(flag==-1){
			model.addAttribute("msg", "对不起，操作有误。。。请返回原页面刷新，重新操作！");
			return "result/result";
		}
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("result", result);
		return "redirect:finConfirmBill";
	}
	
	/**
	 * 财务部驳回。
	 * @param rebateFlowId
	 * @param rebateMsg
	 * @param session
	 * @param model
	 * @param redAtts
	 * @return
	 */
	@RequestMapping("finReject")
	public String finReject(@RequestParam(value="rebateFlowId",defaultValue="0")int rebateFlowId, 
			@RequestParam(value="rebateMsg",defaultValue="")String rebateMsg, 
			HttpSession session, Model model, RedirectAttributes redAtts){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TbConRebateFlow rebateFlow = conRebateFlowService.getRebateFlowById(rebateFlowId);
		TbConRebateFlow flow = new TbConRebateFlow();
		flow.setRebate_flow_id(rebateFlowId);
		flow.setRebate_state(4);
		flow.setRebate_id(rebateFlow.getRebate_id());
		flow.setRebate_msg(rebateMsg);
		int result = conRebateFlowService.checkOrReject(flow, userInfo.getUsercode());
		if(result != -1){
			redAtts.addAttribute("result", "驳回操作成功");
			return "redirect:finConfirmBill";
		}
		model.addAttribute("msg", "对不起，操作有误。。。请返回原页面刷新，重新操作！");
		return "result/result";
	}
	
	/**
	 * 根据合同号查看返利单。
	 * @param model
	 * @param session
	 * @param conId
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/rebateBill")
	public String rebateBill(Model model, HttpSession session, 
			@RequestParam(value="conId",defaultValue="")String conId,
			@RequestParam(value="pageNum",defaultValue="1")int pageNum){
		PageResult<Map<String, Object>> pager = conRebateFlowService.getRebateByCon(conId, pageNum, 10);
		int billCount = TbConBillDao.whereCount(" CON_ID='"+conId+"' and BILL_STATE <> '5' ");
		int rebateCount = TbConRebateDao.whereCount(" CON_ID='"+conId+"' and REBATE_STATE <> '6'");
		int accCount = TbConAccountDao.whereCount("CON_ID='"+conId+"' and ACC_STATE <> '0'");
		model.addAttribute("billCount", billCount);
		model.addAttribute("rebateCount", rebateCount);
		model.addAttribute("accCount", accCount);
		model.addAttribute("act", "rebateBill");
		model.addAttribute("pager", pager);
		model.addAttribute("conId", conId);
		return "rebate/rebateBill";
	}
	
	/**
	 * 显示具体返利申请单。
	 * @param rebateId
	 * @param model
	 * @return
	 */
	@RequestMapping("/showRebate")
	public String showRebate(@RequestParam(value="rebateId",defaultValue="-1")int rebateId, Model model){
		String result = "error/error";
//		int reId = Integer.parseInt(rebateId);
		if(rebateId==-1){
			return result;
		}
		Map<String, Object> map = conRebateFlowService.showRebateInfo(rebateId);
		String conId = (String) map.get("conId");
		if(conId.startsWith("SHCT")){
			result = "rebate/ctRebateShow";
		}else if(conId.startsWith("SHCZ")){
			result = "rebate/czRebateShow";
		}else if(conId.startsWith("SHZH")){
			result = "rebate/zhRebateShow";
		}
		TbConRebateFlow conRebateFlow = conRebateFlowService.getFlowByRebateId(rebateId);
		TbConRebate re = conRebateFlowService.getRebateById(rebateId);
		String manager_writeUrl = tbFinUserService.getUrlByMsg(conRebateFlow.getManager());
		String area_writerUrl = tbFinUserService.getUrlByMsg(conRebateFlow.getArea());
		String flow_writeUrl = tbFinUserService.getUrlByMsg(conRebateFlow.getFlow_check());
		String user_writerUrl = tbFinUserService.getUrlByUserId(re.getUser_id());
		String hqManagerUrl = tbFinUserService.getUrlByMsg(conRebateFlow.getHqmanager());
		model.addAttribute("manager_writeUrl", manager_writeUrl);
		model.addAttribute("area_writerUrl", area_writerUrl);
		model.addAttribute("flow_writeUrl", flow_writeUrl);
		model.addAttribute("user_writerUrl", user_writerUrl);
		model.addAttribute("hqManager_writeUrl", hqManagerUrl);
		model.addAttribute("map", map);
		return result;
	}
	
	/**
	 * 根据业务员查看被驳回的返利申请。
	 * @param model
	 * @param session
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/lookRejectRebate")
	public String lookRejectRebate(Model model, HttpSession session, @RequestParam(value="pageNum",defaultValue="1")int pageNum){
		String result = "error/error";
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		PageResult<Map<String, Object>> pager = conRebateFlowService.lookRejectRebate(4, user.getUsercode(), pageNum, 10);
		model.addAttribute("pager", pager);
		result = "rebate/lookRejectRebateBill";
		return result;
	}
	
	/**
	 * 查看所有的被驳回的返利申请。
	 * @param model
	 * @param session
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/lookAllRejectRebate")
	public String lookAllRejectRebate(Model model, HttpSession session, @RequestParam(value="pageNum",defaultValue="1")int pageNum){
		String result = "error/error";
		PageResult<Map<String, Object>> pager = conRebateFlowService.lookRejectRebate(4, null, pageNum, 10);
		model.addAttribute("pager", pager);
		result = "rebate/lookRejectRebateBill";
		return result;
	}
	
	/**
	 * 删除返利申请单。
	 * @param rebateId
	 * @return
	 */
	@RequestMapping("/deleteRebate")
	public String deleteRebate(@RequestParam("rebateId")int rebateId){
		List<TbConRebate> where = TbConRebateDao.where(" BACK_ID='"+rebateId+"'");
		if(where!=null && where.size()>0){
			TbConRebate rebate = where.get(0);
			rebate.setRebate_state(6);
			rebate.setUpdate(new Date());
			
			List<TbConRebateFlow> where2 = TbConRebateFlowDao.where(" REBATE_ID='"+rebateId+"'");
			if(where2!=null&&where2.size()>0){
				TbConRebateFlow flow = where2.get(0);
				TbConRebateFlow flow1 = new TbConRebateFlow();
				flow1.setRebate_flow_id(flow.getRebate_flow_id());
				flow1.setFlow_check("");
				flow1.setNextcheck("");
				flow1.setHqmanager("");
				flow1.setNextcheck("");
				flow1.setRebate_msg("");
				flow1.setRebate_state(6);
				TbConRebateDao.update(rebate);
				TbConRebateFlowDao.update(flow1);
			}
		}
		return "redirect:/tbConRebate/lookRejectRebate";
	}
	
	@RequestMapping("/showAllRebate")
	public String showAllRebate(@RequestParam(value="pageNum",defaultValue="1")int pageNum,PageCondition pageCondition,
			Model model){
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		if(pageCondition.getConState()!=null && !pageCondition.getConState().equals("")){
			sb.append(" and REBATE_STATE='"+pageCondition.getConState()+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			sb.append(" and USER_NAME like '%"+pageCondition.getUserName()+"%' ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getCusName()!=null && !pageCondition.getCusName().equals("")){
			sb.append(" and CUS_NAME like '%"+pageCondition.getCusName()+"%' ");
		}
		sb.append(" and REBATE_STATE not in (4,5,6) order by BACK_ID desc ");
		 int totalRecords = TbConRebateDao.whereCount(sb.toString());
		PageResult<Map<String,Object>> pager = tbFinUserService.showRebateByWhere(pageNum,totalRecords,sb.toString());
		model.addAttribute("pageCondition", pageCondition);
		model.addAttribute("pager", pager);
		return "rebate/showAllRebate";
	}
}
