package com.poly.action;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import car_beans.TbConAddcon;
import car_beans.TbConBill;
import car_beans.TbConBillFlow;
import car_beans.TbConCheckDetail;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConZh;
import car_beans.TbFinOrg;
import car_beans.TbFinUser;
import car_beans.UserInfo;
import car_daos.TbConAccountDao;
import car_daos.TbConAddconDao;
import car_daos.TbConBillDao;
import car_daos.TbConBillFlowDao;
import car_daos.TbConCheckDetailDao;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConRebateDao;
import car_daos.TbConZhDao;
import car_daos.TbFinOrgDao;
import car_daos.TbFinUserDao;

import com.poly.bean.PageCondition;
import com.poly.bean.PageResult;
import com.poly.dao.subDaos.MailDao;
import com.poly.dao.subDaos.TbConBillSubDao;
import com.poly.services.TbConBillService;
import com.poly.services.TbFinUserService;

/**
 * 开票申请单
 * 
 * @author lff
 *
 */
@Controller
@RequestMapping(value = "/tbConBill")
public class TbConBillController {
	private TbFinUserService tbFinUserService = new TbFinUserService();
	private TbConBillService tbConBillService = new TbConBillService();
	private MailDao mailDao = new MailDao();

	/**
	 * 进入填写开票单
	 * 
	 * @param id
	 *            合同号
	 * @param type
	 *            合同类型
	 * @return
	 */
	@RequestMapping(value = "toAddConBill")
	public String toAddConBill(
			@RequestParam(value = "id", required = true) String id,
			Model model, HttpSession session) {
		/* if (!tbConBillService.checkAddBill(id)) {
			 model.addAttribute("msg", "已有未审核的开票，请等待审核完毕后再重新开票！");
				return "result/result";
		}
		 List<TbConAddcon> tbConAddcons =TbConAddconDao.where("CON_ID = '"+id+"'");
		 if (tbConAddcons==null||tbConAddcons.size()==0) {
			 model.addAttribute("msg", "您的合同没有填写合同补充！");
				return "result/result";
			}*/
		
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user == null) {
			return "redirect:/login";
		}
		TbFinOrg org = TbFinOrgDao.where("org_id='" + user.getOrgcode() + "'")
				.get(0);
		Calendar cal = Calendar.getInstance();
		// 车团合同
		if ("SHCT".equals(id.substring(0, 4))) {
			cal.setTime(new Date());
			TbConCt conCt = TbConCtDao.where("con_ct_id='" + id + "'").get(0);
			model.addAttribute("tbConct", conCt);
			model.addAttribute("dateYear", cal.get(Calendar.YEAR) - 2000);
			model.addAttribute("dateMonth", cal.get(Calendar.MONTH) + 1);
			model.addAttribute("dateDay", cal.get(Calendar.DAY_OF_MONTH));
			model.addAttribute("org", org);
			return "conBill/ctConBill";
		}// 车展合同
		else if ("SHCZ".equals(id.substring(0, 4))) {
			TbConCz conCz = TbConCzDao.where("con_cz_id='" + id + "'").get(0);
			Date date = new Date();
			model.addAttribute("date", date);
			model.addAttribute("conCz", conCz);
			model.addAttribute("dateYear", cal.get(Calendar.YEAR) - 2000);
			model.addAttribute("dateMonth", cal.get(Calendar.MONTH) + 1);
			model.addAttribute("dateDay", cal.get(Calendar.DAY_OF_MONTH));
			model.addAttribute("org", org);
			return "conBill/czConBill";
		}// 广告合同
		else if ("SHZH".equals(id.substring(0, 4))) {
			TbConZh conZh = TbConZhDao.where("con_zh_id='" + id + "'").get(0);
			model.addAttribute("conZh", conZh);
			model.addAttribute("dateYear", cal.get(Calendar.YEAR) - 2000);
			model.addAttribute("dateMonth", cal.get(Calendar.MONTH) + 1);
			model.addAttribute("dateDay", cal.get(Calendar.DAY_OF_MONTH));
			model.addAttribute("org", org);
			return "conBill/zhConBill";
		} else {
			model.addAttribute("msg", "系统内部错误,请联系管理员");
			return "result/result";
		}
	}

	/*
	 * 保存开票申请
	 */
	@RequestMapping(value = "addTbConBill")
	public String addTbConBill(TbConBill tbConBill, HttpSession session,
			HttpServletRequest request,Model model) {
		TbConBill bill = new TbConBill();
		// 保存申请单信息
		BeanUtils.copyProperties(tbConBill, bill);
		TbConBillFlow conBillFlow = new TbConBillFlow();
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user == null || user.getUsercode() == null) {
			return "redirect:/login";
		}
		// 判断平开高开 平开的话 高开金额置为0,高开的话，计算开票金额
		if (bill.getState() == 0) {
			bill.setBill_high(0);
		}
		bill.setUser_id(user.getUsercode());
		bill.setUser_name(user.getUsername());
		bill.setCreate(new Date());
		String managerCheck = "";
		if (user != null && user.getUsercode() != null) {
			if (user.getOrgcode().equals("1") || user.getOrgcode().equals("0")) {
				managerCheck = this.tbFinUserService.findManagerCheck(
						user.getOrgcode(), user.getDeptcode());
			} else {
				managerCheck = this.tbFinUserService.findManagerCheck(user
						.getOrgcode());
			}
		}
		String areaCheck = this.tbFinUserService.findAreaCheck(user.getOrgcode());
		String flowCheck = this.tbFinUserService.findFlowCheck();
		if (!managerCheck.equals("")) {
			conBillFlow.setManager(managerCheck);
		}
		if (!flowCheck.equals("")) {
			conBillFlow.setFlow_check(flowCheck);
		}
		if(!areaCheck.equals("")){
			conBillFlow.setArea(areaCheck);
		}
		//设置下一个审核人
		if(!managerCheck.equals("")){
			conBillFlow.setNextcheck(managerCheck);
			bill.setBill_state(0);
			conBillFlow.setBill_state(0);
		}else{
			if(!areaCheck.equals("")){
				conBillFlow.setNextcheck(areaCheck);
				bill.setBill_state(1);
				conBillFlow.setBill_state(1);
			}else{
				if(!flowCheck.equals("")){
					conBillFlow.setNextcheck(flowCheck);
					bill.setBill_state(6);
					conBillFlow.setBill_state(6);
				}
			}
		}
		
		String[] split = conBillFlow.getNextcheck().split("/");
		for(int i=0;i<split.length;i++){
			split[i] = split[i].replace("+", "");
		}
		List<String> phones = tbFinUserService.getUserPhoneByUserIds(Arrays.asList(split));
		for(String phone : phones){
			this.mailDao.sendMail("", "", "您有一条来自"+bill.getUser_name()+"的合同开票审核申请，请尽快处理。", phone, "0");
		}
		int bill_id = 0;
		try {
			bill_id = TbConBillSubDao.save(bill);
		} catch (Exception e) {
			model.addAttribute("msg", "系统内部错误，保存失败，请联系管理员!");
			e.printStackTrace();
			return "result/result";
		}
		// 保存申请单流程表
		
		conBillFlow.setBill_id(bill_id);
		
		
		try {
			TbConBillFlowDao.save(conBillFlow);
		} catch (Exception e) {
			model.addAttribute("msg", "系统内部错误,请联系管理员");
			e.printStackTrace();
			return "result/result";
		}

		model.addAttribute("msg", "保存成功！");
		return "result/result";
	}

	/*
	 * 获取分页信息的详细信息
	 */
	public PageResult<Map<String, Object>> getPageInfoss(
			PageResult<Map<String, Object>> pageResult) {
		for (int i = 0; i < pageResult.getList().size(); i++) {
			TbConBill connBill = ((TbConBill) (pageResult.getList().get(i)
					.get("conBill")));
			String id = connBill.getCon_id();
			// 车团合同
			if ("SHCT".equals(id.substring(0, 4))) {
				TbConCt conCt = TbConCtDao.where("con_ct_id='" + id + "'").get(
						0);
				TbFinUser user = TbFinUserDao.where(
						"user_code = '" + conCt.getUser_id() + "'").get(0);
				TbFinOrg org = TbFinOrgDao.where(
						"org_id='" + user.getOrg_code() + "'").get(0);
				
				TbConBillFlow tbBillFlow = TbConBillFlowDao.where(
						"BILL_ID='" + connBill.getBill_id() + "'").get(0);
				TbConAddcon addcon = null;
				List<TbConAddcon> where = TbConAddconDao.where(" CON_ID='"+id+"' ");
				if(where!=null && where.size()>0){
					addcon = where.get(0);
				}
				pageResult.getList().get(i).put("addCon", addcon);
				pageResult.getList().get(i).put("tbBillFlow", tbBillFlow);
				pageResult.getList().get(i).put("conCt", conCt);
				pageResult.getList().get(i).put("org", org);
				
			}// 车展合同
			else if ("SHCZ".equals(id.substring(0, 4))) {
				TbConCz conCz = TbConCzDao.where("con_cz_id='" + id + "'").get(
						0);
				TbFinUser user = TbFinUserDao.where(
						"user_code = '" + conCz.getUser_id() + "'").get(0);
				TbFinOrg org = TbFinOrgDao.where(
						"org_id='" + user.getOrg_code() + "'").get(0);
				TbConBillFlow tbBillFlow = TbConBillFlowDao.where(
						"BILL_ID='" + connBill.getBill_id() + "'").get(0);
				TbConAddcon addcon = null;
				List<TbConAddcon> where = TbConAddconDao.where(" CON_ID='"+id+"' ");
				if(where!=null && where.size()>0){
					addcon = where.get(0);
				}
				pageResult.getList().get(i).put("addCon", addcon);
				pageResult.getList().get(i).put("tbBillFlow", tbBillFlow);
				pageResult.getList().get(i).put("conCz", conCz);
				pageResult.getList().get(i).put("org", org);
			}// 广告合同
			else if ("SHZH".equals(id.substring(0, 4))) {
				TbConZh conZh = TbConZhDao.where("con_zh_id='" + id + "'").get(
						0);
				TbFinUser user = TbFinUserDao.where(
						"user_code = '" + conZh.getUser_id() + "'").get(0);
				TbFinOrg org = TbFinOrgDao.where(
						"org_id='" + user.getOrg_code() + "'").get(0);
				TbConBillFlow tbBillFlow = TbConBillFlowDao.where(
						"BILL_ID='" + connBill.getBill_id() + "'").get(0);
				TbConAddcon addcon = null;
				List<TbConAddcon> where = TbConAddconDao.where(" CON_ID='"+id+"' ");
				if(where!=null && where.size()>0){
					addcon = where.get(0);
				}
				pageResult.getList().get(i).put("addCon", addcon);
				pageResult.getList().get(i).put("tbBillFlow", tbBillFlow);
				pageResult.getList().get(i).put("conZh", conZh);
				pageResult.getList().get(i).put("org", org);
			}

		}
		return pageResult;
	}

	/**
	 * 进入审核页面
	 * 
	 * @param type
	 * 审核类型 managerCheck：经理审核 flowCheck:流程部审核
	 */
	@RequestMapping(value = "toCheckBill")
	public String toCheckBill(@RequestParam("type") String type, Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			HttpServletRequest request,HttpSession session) {
		String result = request.getParameter("result");
		if (result != null) {
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		model.addAttribute("result", result);
		if ("managerCheck".equals(type)) {
			PageResult<Map<String, Object>> pageResult = tbConBillService
					.getAccessTbConBill(pageNum, 10, 0,userInfo.getUsercode(),null);
			pageResult = getPageInfoss(pageResult);
			model.addAttribute("pager", pageResult);
			model.addAttribute("pageNum", pageNum);
			return "conBill/bmjlCheckBill";
		} else if ("flowCheck".equals(type)) {
			PageResult<Map<String, Object>> pageResult = tbConBillService
					.getAccessTbConBill(pageNum, 10, 6,userInfo.getUsercode(),null);
			pageResult = getPageInfoss(pageResult);
			model.addAttribute("pager", pageResult);
			model.addAttribute("pageNum", pageNum);
			return "conBill/lcbCheckBill";
		}else if ("areaCheck".equals(type)){
			PageResult<Map<String,Object>> pageResult = tbConBillService
					.getAccessTbConBill(pageNum,10,1,userInfo.getUsercode(),null);
			pageResult = getPageInfoss(pageResult);
			model.addAttribute("pager", pageResult);
			model.addAttribute("pageNum", pageNum);
			return "conBill/areaCheckBill";
		} else {
			model.addAttribute("msg", "系统内部错误,请联系管理员");
			return "result/result";
		}
	}

	/**
	 * 申请单审核(通过)
	 * 
	 * @param bill_id
	 *            申请单号
	 * @param type
	 *            审核类型 managerCheck：经理审核 flowCheck:流程部审核
	 * @return
	 */
	@RequestMapping(value = "assessBill")
	public String assessBill(@RequestParam("bill_id") int bill_id,
			@RequestParam("type") String type, HttpSession session,
			RedirectAttributes redAtts,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,Model model) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user == null || user.getUsercode() == null) {
			return "redirect:/login";
		}
		TbConBill bill = tbConBillService.getBillById(bill_id);
		TbFinUser findUserByUserId = this.tbFinUserService.findUserByUserId(bill.getUser_id());
		String areaCheck = this.tbFinUserService.findAreaCheck(findUserByUserId.getOrg_code());
		String flowCheck = this.tbFinUserService.findFlowCheck();
		if (bill_id != 0) {
			TbConBillFlow conBillFlow = TbConBillFlowDao.where(
					"bill_id='" + bill_id + "'").get(0);
			TbConBill conBill = new TbConBill();
			conBill.setBill_id(conBillFlow.getBill_id());
			// 经理审核
			if ("managerCheck".equals(type)) {
				
				conBillFlow.setManager(conBillFlow.getManager().replace(
						"+" + user.getUsercode() + "/",
						"-" + user.getUsercode() + "/"));
				
				if(!areaCheck.equals("")){
					conBillFlow.setNextcheck(areaCheck);
					conBillFlow.setBill_state(1);
					conBill.setBill_state(1);
				}else{
					if(!flowCheck.equals("")){
						conBillFlow.setNextcheck(flowCheck);
						conBillFlow.setBill_state(6);
						conBill.setBill_state(6);
					}
				}
				String[] split = conBillFlow.getNextcheck().split("/");
				for(int i=0;i<split.length;i++){
					split[i] = split[i].replace("+", "");
				}
				List<String> phones = tbFinUserService.getUserPhoneByUserIds(Arrays.asList(split));
				for(String phone : phones){
					this.mailDao.sendMail("", "", "您有一条来自"+bill.getUser_name()+"的合同开票审核申请，请尽快处理。", phone, "0");
				}
				TbConBillFlowDao.update(conBillFlow);
				TbConBillDao.update(conBill);

				// 增加审批记录
				TbConCheckDetail checkDetail = new TbConCheckDetail();
				checkDetail.setCid("bill_" + conBill.getBill_id());
				checkDetail.setAdd_time(new Date());
				checkDetail.setCheck_type(0);
				checkDetail.setCheck_user(user.getUsercode());
				try {
					TbConCheckDetailDao.save(checkDetail);
				} catch (Exception e) {
					e.printStackTrace();
				}
				redAtts.addAttribute("result", "审核操作成功");
				return "redirect:toCheckBill?type=managerCheck&pageNum="
						+ pageNum;

			} else if ("flowCheck".equals(type)) {// 流程部审核
				conBillFlow.setBill_state(2);
				conBillFlow.setNextcheck("");
				conBillFlow.setFlow_check(conBillFlow.getFlow_check().replace(
						"+" + user.getUsercode() + "/",
						"-" + user.getUsercode() + "/"));
				conBill.setBill_state(2);
				TbConBillFlowDao.update(conBillFlow);
				TbConBillDao.update(conBill);
				String[] split = conBillFlow.getNextcheck().split("/");
				for(int i=0;i<split.length;i++){
					split[i] = split[i].replace("+", "");
				}
				List<String> phones = tbFinUserService.getUserPhoneByUserIds(Arrays.asList(split));
				for(String phone : phones){
					this.mailDao.sendMail("", "", "您有一条来自"+bill.getUser_name()+"的合同开票审核申请，请尽快处理。", phone, "0");
				}
				
				// 增加审批记录
				TbConCheckDetail checkDetail = new TbConCheckDetail();
				checkDetail.setCid("bill_" + conBill.getBill_id());
				checkDetail.setAdd_time(new Date());
				checkDetail.setCheck_type(0);
				checkDetail.setCheck_user(user.getUsercode());
				try {
					TbConCheckDetailDao.save(checkDetail);
				} catch (Exception e) {
					e.printStackTrace();
				}
				redAtts.addAttribute("result", "审核操作成功");
				return "redirect:toCheckBill?type=flowCheck&pageNum=" + pageNum;

			}else if ("areaCheck".equals(type)){
				conBillFlow.setBill_state(6);
				conBillFlow.setNextcheck(flowCheck);
				conBillFlow.setArea(conBillFlow.getArea().replace(
						"+" + user.getUsercode() + "/",
						"-" + user.getUsercode() + "/"));
				conBill.setBill_state(6);
				TbConBillFlowDao.update(conBillFlow);
				TbConBillDao.update(conBill);
				String[] split = conBillFlow.getNextcheck().split("/");
				for(int i=0;i<split.length;i++){
					split[i] = split[i].replace("+", "");
				}
				List<String> phones = tbFinUserService.getUserPhoneByUserIds(Arrays.asList(split));
				for(String phone : phones){
					this.mailDao.sendMail("", "", "您有一条来自"+bill.getUser_name()+"的合同开票审核申请，请尽快处理。", phone, "0");
				}
				// 增加审批记录
				TbConCheckDetail checkDetail = new TbConCheckDetail();
				checkDetail.setCid("bill_" + conBill.getBill_id());
				checkDetail.setAdd_time(new Date());
				checkDetail.setCheck_type(0);
				checkDetail.setCheck_user(user.getUsercode());
				try {
					TbConCheckDetailDao.save(checkDetail);
				} catch (Exception e) {
					e.printStackTrace();
				}
				redAtts.addAttribute("result", "审核操作成功");
				return "redirect:toCheckBill?type=areaCheck&pageNum=" + pageNum;
			} else {
				model.addAttribute("msg", "系统内部错误,请联系管理员");
				return "result/result";
			}
		} else {
			model.addAttribute("msg", "系统内部错误,请联系管理员");
			return "result/result";
		}

	}

	/**
	 * 申请单被驳回
	 */
	@RequestMapping(value = "rejectBill")
	public String rejectBill(TbConBillFlow billFlow,
			HttpServletRequest request, HttpSession session,
			RedirectAttributes redAtts,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user == null || user.getUsercode() == null) {
			return "redirect:/login";
		}
		TbConBillFlow conBillFlow = TbConBillFlowDao.where(
				"bill_id='" + billFlow.getBill_id() + "'").get(0);
		TbConBill conBill = new TbConBill();
		conBill.setBill_id(conBillFlow.getBill_id());
		conBillFlow.setBill_state(3);
		conBillFlow.setBill_msg(billFlow.getBill_msg());
		conBillFlow.setNextcheck(conBillFlow.getManager());// 下一个审核人变为经理
		conBill.setBill_state(3);
		TbConBillFlowDao.update(conBillFlow);
		TbConBillDao.update(conBill);
		
		// 增加审批记录
		TbConCheckDetail checkDetail = new TbConCheckDetail();
		checkDetail.setCid("bill_" + conBill.getBill_id());
		checkDetail.setAdd_time(new Date());
		checkDetail.setCheck_type(0);
		checkDetail.setContent(billFlow.getBill_msg());
		checkDetail.setCheck_user(user.getUsercode());
		try {
			TbConCheckDetailDao.save(checkDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		redAtts.addAttribute("result", "驳回成功");
		return "redirect:toCheckBill?type=" + type + "&pageNum=" + pageNum;
	}
	
	/**
	 * 
	 * @param billFlow
	 * @param request
	 * @param session
	 * @param redAtts
	 * @param type
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value = "rejectBillByCW")
	public String rejectBillByCW(TbConBillFlow billFlow,
			HttpServletRequest request, HttpSession session,
			RedirectAttributes redAtts,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user == null || user.getUsercode() == null) {
			return "redirect:/login";
		}
		TbConBillFlow conBillFlow = TbConBillFlowDao.where(
				"bill_id='" + billFlow.getBill_id() + "'").get(0);
		TbConBill conBill = new TbConBill();
		conBill.setBill_id(conBillFlow.getBill_id());
		conBillFlow.setBill_state(3);
		conBillFlow.setBill_msg(billFlow.getBill_msg());
		conBillFlow.setNextcheck(conBillFlow.getManager());// 下一个审核人变为经理
		conBill.setBill_state(3);
		TbConBillFlowDao.update(conBillFlow);
		TbConBillDao.update(conBill);
		TbConBill bill = TbConBillDao.where(" BILL_ID='"+conBillFlow.getBill_id()+"' ").get(0);
		String conid = bill.getCon_id();
		if(conid.startsWith("SHCZ")){
			TbConCz existsCz = TbConCzDao.where(" CON_CZ_ID='"+bill.getCon_id()+"' ").get(0);
			TbConCz cz = new TbConCz();
			cz.setAl_bill(existsCz.getAl_bill()-bill.getSal_bill());
			cz.setCon_cz_id(conid);
			TbConCzDao.update(cz);
		}else if(conid.startsWith("SHZH")){
			TbConZh existsZh = TbConZhDao.where(" CON_ZH_ID='"+bill.getCon_id()+"' ").get(0);
			TbConZh zh = new TbConZh();
			zh.setAl_bill(existsZh.getAl_bill()-bill.getSal_bill());
			zh.setCon_zh_id(conid);
			TbConZhDao.update(zh);
		}else if(conid.startsWith("SHCT")){
			TbConCt existsCt = TbConCtDao.where(" CON_CT_ID='"+bill.getCon_id()+"' ").get(0);
			TbConCt ct = new TbConCt();
			ct.setAl_bill(existsCt.getAl_bill()-bill.getSal_bill());
			ct.setCon_ct_id(conid);
			TbConCtDao.update(ct);
		}
	
		// 增加审批记录
		TbConCheckDetail checkDetail = new TbConCheckDetail();
		checkDetail.setCid("bill_" + conBill.getBill_id());
		checkDetail.setAdd_time(new Date());
		checkDetail.setCheck_type(0);
		checkDetail.setContent(billFlow.getBill_msg());
		checkDetail.setCheck_user(user.getUsercode());
		try {
			TbConCheckDetailDao.save(checkDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		redAtts.addAttribute("result", "驳回成功");
		return "redirect:searchAccessBill?pageNum="+pageNum;
	}
	
	

	/**
	 * 删除申请单
	 * 
	 * @param bill_id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "to_delete")
	public String deleteBill(
			@RequestParam(value = "bill_id", required = true) String bill_id,Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum
			) throws Exception {
		TbConBillFlow conBillFlow = TbConBillFlowDao.where(
				"BILL_ID=" + bill_id ).get(0);
		conBillFlow.setBill_state(5);
		conBillFlow.setNextcheck("");
		TbConBillFlowDao.update(conBillFlow);
		TbConBill conBill = TbConBillDao.where(
				"BILL_ID=" + bill_id ).get(0);
		TbConBill bill_new = new TbConBill();
		bill_new.setBill_id(Integer.parseInt(bill_id));
		bill_new.setBill_state(5);
		bill_new.setUpdate(new Date());
		TbConBillDao.update(bill_new);
		model.addAttribute("msg", "删除成功");
		return "redirect:ywyViewRejectBills?pageNum=" + pageNum;
	}
	
	/**
	 * 查看待开单的申请单 开单申请表---合同表---机构表 信息
	 */
	@RequestMapping(value = "searchAccessBill")
	public String searchAccessBill(Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			PageCondition pageCondition,HttpServletRequest request) {
		String result = request.getParameter("result");
		if (result != null) {
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
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
		if(pageCondition.getConId()!=null && !pageCondition.getConId().equals("")){
			sb.append(" and con_id like '%"+pageCondition.getConId()+"%' ");
		}
		if(pageCondition.getOrgName()!=null && StringUtils.isNotBlank(pageCondition.getOrgName()) && !pageCondition.getOrgName().equals("-1")){
			sb.append(" and user_id in (select user_code from tb_fin_user where org_code='"+pageCondition.getOrgName()+"') ");
		}
		PageResult<Map<String, Object>> pageResult = tbConBillService
				.getAccessTbConBill(pageNum, 10, 2,null,sb.toString());
		for (int i = 0; i < pageResult.getList().size(); i++) {
			String id = ((TbConBill) (pageResult.getList().get(i)
					.get("conBill"))).getCon_id();
			// 车团合同
			if ("SHCT".equals(id.substring(0, 4))) {
				TbConCt conCt = TbConCtDao.where("con_ct_id='" + id + "'").get(
						0);
				TbFinUser user = TbFinUserDao.where(
						"user_code = '" + conCt.getUser_id() + "'").get(0);
				TbFinOrg org = TbFinOrgDao.where(
						"org_id='" + user.getOrg_code() + "'").get(0);
				pageResult.getList().get(i).put("conCt", conCt);
				pageResult.getList().get(i).put("org", org);
			}// 车展合同
			else if ("SHCZ".equals(id.substring(0, 4))) {
				TbConCz conCz = TbConCzDao.where("con_cz_id='" + id + "'").get(
						0);
				TbFinUser user = TbFinUserDao.where(
						"user_code = '" + conCz.getUser_id() + "'").get(0);
				TbFinOrg org = TbFinOrgDao.where(
						"org_id='" + user.getOrg_code() + "'").get(0);
				pageResult.getList().get(i).put("conCz", conCz);
				pageResult.getList().get(i).put("org", org);
			}// 广告合同
			else if ("SHZH".equals(id.substring(0, 4))) {
				TbConZh conZh = TbConZhDao.where("con_zh_id='" + id + "'").get(
						0);
				TbFinUser user = TbFinUserDao.where(
						"user_code = '" + conZh.getUser_id() + "'").get(0);
				TbFinOrg org = TbFinOrgDao.where(
						"org_id='" + user.getOrg_code() + "'").get(0);
				pageResult.getList().get(i).put("conZh", conZh);
				pageResult.getList().get(i).put("org", org);
			}

		}
		model.addAttribute("pager", pageResult);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageCondition", pageCondition);
		model.addAttribute("result", result);
		return "conBill/waitBills";
	}

	/**
	 * 预览
	 */
	@RequestMapping(value = "to_look")
	public String toLook(
			@RequestParam(value = "bill_id", required = true) String bill_id,
			Model model) {
		TbConBill conBill = TbConBillDao.where("bill_id='" + bill_id + "'")
				.get(0);
		String id = conBill.getCon_id();
		Calendar cal = Calendar.getInstance();
		TbConBillFlow conBillFlow = TbConBillFlowDao.where(
				"bill_id='" + bill_id + "'").get(0);
		  String area_writeUrl = tbFinUserService.getUrlByMsg(conBillFlow.getArea());
		  String manager_writeUrl = tbFinUserService.getUrlByMsg(conBillFlow.getManager());
		  String flow_writeUrl = tbFinUserService.getUrlByMsg(conBillFlow.getFlow_check());
		  String user_writerUrl = tbFinUserService.getUrlByUserId(conBill.getUser_id());
			model.addAttribute("manager_writeUrl", manager_writeUrl);
  			model.addAttribute("flow_writeUrl", flow_writeUrl);
  			model.addAttribute("user_writerUrl", user_writerUrl);
  			model.addAttribute("area_writeUrl", area_writeUrl);
		// 车团合同
		if ("SHCT".equals(id.substring(0, 4))) {
			cal.setTime(new Date());
			TbConCt conCt = TbConCtDao.where("con_ct_id='" + id + "'").get(0);
			TbFinUser user = TbFinUserDao.where(
					"user_code = '" + conCt.getUser_id() + "'").get(0);
			TbFinOrg org = TbFinOrgDao.where(
					"org_id='" + user.getOrg_code() + "'").get(0);
			model.addAttribute("tbConct", conCt);
			model.addAttribute("conBill", conBill);
			model.addAttribute("org", org);
			return "conBill/look_ctConBill";
		}// 车展合同
		else if ("SHCZ".equals(id.substring(0, 4))) {
			TbConCz conCz = TbConCzDao.where("con_cz_id='" + id + "'").get(0);
			TbFinUser user = TbFinUserDao.where(
					"user_code = '" + conCz.getUser_id() + "'").get(0);
			TbFinOrg org = TbFinOrgDao.where(
					"org_id='" + user.getOrg_code() + "'").get(0);
			Date date = new Date();
			model.addAttribute("date", date);
			model.addAttribute("tbConcz", conCz);
			model.addAttribute("conBill", conBill);
			model.addAttribute("org", org);
			return "conBill/look_czConBill";
		}// 广告合同
		else if ("SHZH".equals(id.substring(0, 4))) {
			TbConZh conZh = TbConZhDao.where("con_zh_id='" + id + "'").get(0);
			TbFinUser user = TbFinUserDao.where(
					"user_code = '" + conZh.getUser_id() + "'").get(0);
			TbFinOrg org = TbFinOrgDao.where(
					"org_id='" + user.getOrg_code() + "'").get(0);
			model.addAttribute("conZh", conZh);
			model.addAttribute("conBill", conBill);
			model.addAttribute("org", org);
			return "conBill/look_zhConBill";
		}
		model.addAttribute("msg", "系统内部错误,请联系管理员");
		return "result/result";
	}

	/**
	 * 确认开票
	 */
	@RequestMapping(value = "checkBill")
	public String checkBill(TbConBill bill, HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,RedirectAttributes reattr) {
		TbConBill tbConBillNew = new TbConBill();
		// 修改合同表中的已开票金额
		TbConBill conBill = TbConBillDao.where(
				"bill_id='" + bill.getBill_id() + "'").get(0);
		String htid = conBill.getCon_id();
		// 车团合同
		if ("SHCT".equals(htid.substring(0, 4))) {
			TbConCt conCtData = TbConCtDao.where("CON_CT_ID='"+htid+"'").get(0);
			TbConCt conCt = new TbConCt();
			conCt.setCon_ct_id(htid);
			conCt.setAl_bill(conCtData.getAl_bill() + conBill.getSal_bill());
			TbConCtDao.update(conCt);
		}// 车展合同
		else if ("SHCZ".equals(htid.substring(0, 4))) {
			TbConCz TbConCzData = TbConCzDao.where("CON_CZ_ID='"+htid+"'").get(0);
			TbConCz conCz = new TbConCz();
			conCz.setCon_cz_id(htid);
			conCz.setAl_bill(TbConCzData.getAl_bill() + conBill.getSal_bill());
			TbConCzDao.update(conCz);
		}// 广告合同
		else if ("SHZH".equals(htid.substring(0, 4))) {
			TbConZh TbConZhData = TbConZhDao.where("CON_ZH_ID='"+htid+"'").get(0);
			TbConZh conZh = new TbConZh();
			conZh.setCon_zh_id(htid);
			conZh.setAl_bill(TbConZhData.getAl_bill() + conBill.getSal_bill());
			TbConZhDao.update(conZh);
		}

		tbConBillNew.setBill_id(bill.getBill_id());
		tbConBillNew.setBill_money(bill.getBill_money());
		tbConBillNew.setBill_code(bill.getBill_code());
		TbConBillDao.update(tbConBillNew);
		reattr.addAttribute("result", "确认开票成功");
		return "redirect:searchAccessBill?pageNum=" + pageNum;
	}
    
	/**
	 * 确认回款
	 */
	@RequestMapping(value = "checkRmBill")
	public String checkRmBill(TbConBill conBill, HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,HttpSession session,RedirectAttributes reattr) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user == null || user.getUsercode() == null) {
			return "redirect:/login";
		}
		TbConBill tbConBill = new TbConBill();
		tbConBill.setBill_id(conBill.getBill_id());
		tbConBill.setRm_user(user.getUsercode());
		tbConBill.setRm_account(conBill.getRm_account());
		String rm_date = request.getParameter("rm_date_string");
		try {
			tbConBill.setRm_date(new SimpleDateFormat("yyyy-MM-dd")
					.parse(rm_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		TbConBillDao.update(tbConBill);
		// 更改合同表中的回款次数
		
		String id = TbConBillDao
				.where("bill_id='" + conBill.getBill_id() + "'").get(0)
				.getCon_id();
		// 车团合同
		if ("SHCT".equals(id.substring(0, 4))) {
			TbConCt conCt = TbConCtDao.where("con_ct_id='" + id + "'").get(0);
			TbConCt conCt2 = new TbConCt();
			conCt2.setCon_ct_id(id);
			conCt2.setRe_count(conCt.getRe_count() + 1);
			TbConCtDao.update(conCt2);
		}// 车展合同
		else if ("SHCZ".equals(id.substring(0, 4))) {
			TbConCz conCz = TbConCzDao.where("con_cz_id='" + id + "'").get(0);
			TbConCz conCz2 = new TbConCz();
			conCz2.setCon_cz_id(id);
			conCz2.setRe_count(conCz.getRe_count() + 1);
			TbConCzDao.update(conCz2);
		}// 广告合同
		else if ("SHZH".equals(id.substring(0, 4))) {
			TbConZh conZh = TbConZhDao.where("con_zh_id='" + id + "'").get(0);
			TbConZh conZh2 = new TbConZh();
			conZh2.setCon_zh_id(id);
			conZh2.setRe_count(conZh.getRe_count() + 1);
			TbConZhDao.update(conZh2);
		}
		reattr.addAttribute("result", "回款成功");
		return "redirect:searchAccessBill?pageNum=" + pageNum;
	}

	/**
	 * 业务员查询被驳回的申请单
	 */
	@RequestMapping(value = "ywyViewRejectBills")
	public String ywyViewRejectBills(Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		PageResult<Map<String, Object>> pager = tbConBillService
				.getRejectTbConBill(pageNum, 10, user.getUsercode());
		pager = getPageInfoss(pager);
		model.addAttribute("pager", pager);
		return "conBill/ywyViewRejectBill";
	}
	
	
	
	

	/**
	 * 流程部查询被驳回的单子
	 */
	@RequestMapping(value = "lcbViewRejectBills")
	public String lcbViewRejectBills(Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		PageResult<Map<String, Object>> pager = tbConBillService
				.getRejectTbConBill(pageNum, 10, null);
		pager = getPageInfoss(pager);
		model.addAttribute("pager", pager);
		return "conBill/lcbViewRejectBill";
	}

	/**
	 * 显示合同下面所有的申请单
	 */
	@RequestMapping(value = "viewBills")
	public String ViewBills(@RequestParam(value = "id") String id, Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		
		PageResult<Map<String, Object>> pager = tbConBillService
				.getTbConBillsByHtId(id, pageNum, 10);
		pager = getPageInfoss(pager);
		int billCount = TbConBillDao.whereCount(" CON_ID='"+id+"' and BILL_STATE <> '5' ");
		int rebateCount = TbConRebateDao.whereCount(" CON_ID='"+id+"' and REBATE_STATE <> '6'");
		int accCount = TbConAccountDao.whereCount("CON_ID='"+id+"' and ACC_STATE <> '0'");
		model.addAttribute("billCount", billCount);
		model.addAttribute("rebateCount", rebateCount);
		model.addAttribute("accCount", accCount);
		model.addAttribute("pager", pager);
		model.addAttribute("conId",id);
		if (pageNum==1) {
			model.addAttribute("index", 1);
		}else if (pageNum>1) {
			model.addAttribute("index", (pageNum-1)*10);
		}
		return "conBill/viewBills";
	}
	
	
	@RequestMapping("showAllBills")
	public String showAllBills(PageCondition pageCondition,@RequestParam(value="pageNum",defaultValue="1")int pageNum
			,Model model){
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		if(pageCondition.getConState()!=null && !pageCondition.getConState().equals("")){
			sb.append(" and BILL_STATE='"+pageCondition.getConState()+"' ");
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
		
		if(pageCondition.getOrgName()!=null && StringUtils.isNotBlank(pageCondition.getOrgName())){
			sb.append(" and user_id in (select user_code from tb_fin_user where org_code='"+pageCondition.getOrgName()+"') ");
		}
		sb.append(" and BILL_STATE not in (3,4,5) order by BILL_ID desc ");
		int totalRecords = TbConBillDao.whereCount(sb.toString());
		PageResult<Map<String, Object>> pager = tbConBillService.getBillsByWhere(pageNum,totalRecords,sb.toString());
		model.addAttribute("pager", pager);
		model.addAttribute("pageCondition", pageCondition);
		return "conBill/showAllBills";
	}
}
