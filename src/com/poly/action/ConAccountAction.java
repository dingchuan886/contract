package com.poly.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import car_beans.TbConAccount;
import car_beans.TbConAccountFlow;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConZh;
import car_beans.UserInfo;
import car_daos.TbConAccountDao;
import car_daos.TbConAccountFlowDao;
import car_daos.TbConBillDao;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConRebateDao;
import car_daos.TbConZhDao;

import com.poly.bean.PageResult;
import com.poly.services.TbConAccountService;
import com.poly.services.TbFinUserService;

@Controller
@RequestMapping(value = "/conAccount")
public class ConAccountAction {
	TbFinUserService tbFinUserService = new TbFinUserService();
	TbConAccountService tbConAccountService = new TbConAccountService();
	@RequestMapping(value = "/previewAccount")
	public String previewAccount(TbConAccount tbConAccount,
			HttpSession session, Model model) {
		TbConAccount acc = new TbConAccount();
		BeanUtils.copyProperties(tbConAccount, acc);
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user == null || user.getUsercode() == null) {
			return "redirect:/login";
		}
		String orgName = user.getOrgname();
		model.addAttribute("orgName", orgName);
		String conId = acc.getCon_id();
		if (acc.getCon_id() != null) {
			// 如果数据库中有数据，则进行修改
			List<TbConAccount> where2 = TbConAccountDao.where(" CON_ID='"
					+ acc.getCon_id() + "' ");
			if (where2 != null && where2.size() > 0) {
				TbConAccount tbc = where2.get(0);
				acc.setCon_s_id(tbc.getCon_s_id());
				acc.setCreate(tbc.getCreate());
				acc.setUpdate(new Date());
				if (acc.getPlan() == 0) {
					acc.setPlan_des("");
				}
				if (acc.getIsback() == 0) {
					acc.setBack_des("");
				}
				if (acc.getBack_exp() == 0) {
					acc.setBack_des("");
				}
				if (acc.getBill_type() == 2) {
					acc.setBack_des("");
				}
				TbConAccountDao.update(acc);
				model.addAttribute("conAccount", acc);
				return "conAccount/conAccount";
			}

			
			  if(conId.substring(2,4).equals("ZH")){ 
				  
				 List<TbConZh> where = TbConZhDao.where(" CON_ZH_ID='"+conId+"' "); 
				 TbConZh tbConZh = where.get(0); 
				 int i = tbConZh.getCon_type();
				 acc.setCon_type(i);
			  }

			if (conId.substring(2, 4).equals("CT")) {
				List<TbConCt> where = TbConCtDao.where(" CON_CT_ID='" + conId
						+ "' ");
				TbConCt tbConCt = where.get(0);
				int i = tbConCt.getBus_type();
				if (i == 1) {
					acc.setCon_type(5);
				}else if(i == 2){
					acc.setCon_type(6);
				}
			}

				if (conId.substring(2, 4).equals("CZ")) {
					List<TbConCz> where1 = TbConCzDao.where(" CON_CZ_ID='"
							+ conId + "' ");
					TbConCz tbConCz = where1.get(0);
					int i = tbConCz.getCon_type();
					if (i == 0) {
						acc.setCon_type(3);
					} else if (i == 1) {
						acc.setCon_type(4);
					}
				}
				if (acc.getPlan() == 0) {
					acc.setPlan_des("");
				}
				if (acc.getIsback() == 0) {
					acc.setBack_des("");
				}
				if (acc.getBack_exp() == 0) {
					acc.setBack_des("");
				}
				if (acc.getBill_type() == 2) {
					acc.setBack_des("");
				}
				acc.setCreate(new Date());
				acc.setUpdate(new Date());
				acc.setAcc_state(0);
				try {
					TbConAccountDao.save(acc);
				} catch (Exception e) {
					e.printStackTrace();
				}
				List<TbConAccount> where = TbConAccountDao.where(" CON_ID='"+acc.getCon_id()+"' order by `UPDATE` desc");
				TbConAccount account = where.get(0);
				acc.setCon_s_id(account.getCon_s_id());
				model.addAttribute("conAccount", acc);
			}
			return "conAccount/conAccount";
		
		
	}
	
	@RequestMapping("/viewAccount")
	public String viewAccount(Model model,@RequestParam(value="conId")String conId,HttpSession session){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user == null || user.getUsercode() == null) {
			return "redirect:/login";
		}
		String orgName = this.tbFinUserService.getOrgNameByConid(conId);
		model.addAttribute("orgName", orgName);
		List<TbConAccount> where = TbConAccountDao.where(" CON_ID='"+conId+"' order by `update` desc");
		TbConAccount acc = null;
		if(where!=null && where.size()>0){
			acc = where.get(0);
		}
		if(acc == null){
			model.addAttribute("msg", "还没有填写合同相关说明");
			return "result/result";
		}else{
			int billCount = TbConBillDao.whereCount(" CON_ID='"+conId+"' and BILL_STATE <> '5' and bill_state<>3 ");
			int rebateCount = TbConRebateDao.whereCount(" CON_ID='"+conId+"' and REBATE_STATE <> '6'");
			int accCount = TbConAccountDao.whereCount("CON_ID='"+conId+"' ");
			model.addAttribute("billCount", billCount);
			model.addAttribute("rebateCount", rebateCount);
			model.addAttribute("accCount", accCount);
			model.addAttribute("conAccount", acc);
			model.addAttribute("conType",conId.substring(0,4));
			return "conAccount/viewConAccount";
		}
		
	}
	@RequestMapping("/startFlowAcc")
	@ResponseBody
	public String startFlowAcc(@RequestParam("accId")int accId,HttpSession session){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		List<TbConAccount> where = TbConAccountDao.where(" CON_S_ID='"+accId+"'");
		TbConAccount acc = null;
		if(where!=null && where.size()>0){
			acc = where.get(0);
		}
		if(acc!=null){
			acc.setAcc_state(1);
			acc.setUpdate(new Date());
			TbConAccountFlow accFlow = new TbConAccountFlow();
			String managerCheck = "";
			if (user != null && user.getUsercode() != null) {
				if (user.getOrgcode().equals("1") || user.getOrgcode().equals("0")) {
					managerCheck = this.tbFinUserService.findManagerCheck(user.getOrgcode(), user.getDeptcode());
				} else {
					managerCheck = this.tbFinUserService.findManagerCheck(user.getOrgcode());
				}
			}
			
			accFlow.setNext_check(managerCheck);
			accFlow.setManager(managerCheck);
			accFlow.setAcc_state(1);
			accFlow.setCon_account_id(accId);
			List<TbConAccountFlow> where2 = TbConAccountFlowDao.where(" CON_ACCOUNT_ID='"+accId+"' ");
			//直接更新，防止重复提交
			if(where2!=null && where2.size()>0){
				accFlow.setCon_acc_flow_id(where2.get(0).getCon_acc_flow_id());
				TbConAccountFlowDao.update(accFlow);
				TbConAccountDao.update(acc);
			}else{
				try {
					TbConAccountDao.update(acc);
					TbConAccountFlowDao.save(accFlow);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return "{'ok':'200'}";
		}
		return "";
		
	}
	
	@RequestMapping("/lookMyRejectAccount")
	public String lookMyRejectAccount(HttpSession session,Model model,@RequestParam(value="pageNum",defaultValue="1")int pageNum){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String userId = user.getUsercode();
		PageResult<Map<String,Object>> pageResult = this.tbConAccountService.lookMyRejectAccount(userId,pageNum);
		model.addAttribute("pageResult", pageResult);
		
		return "conAccount/lookRejectAccount";
	}
	
	@RequestMapping("/deleteAccount")
	public String deleteAccount(@RequestParam("accId")int accId,Model model){
		TbConAccountDao.delete(" CON_S_ID='"+accId+"'");
		TbConAccountFlowDao.delete(" CON_ACCOUNT_ID='"+accId+"'");
		return "redirect:/conAccount/lookMyRejectAccount";
	}
}
