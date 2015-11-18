package com.poly.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import car_beans.TbConAccount;
import car_beans.TbConFlow;
import car_beans.TbConZh;
import car_beans.TbConZhSub;
import car_beans.UserInfo;
import car_daos.DBConnect;
import car_daos.TbConAccountDao;
import car_daos.TbConBillDao;
import car_daos.TbConFlowDao;
import car_daos.TbConRebateDao;
import car_daos.TbConZhDao;
import car_daos.TbConZhSubDao;

import com.poly.dao.subDaos.MailDao;
import com.poly.services.TbConZhService;
import com.poly.services.TbFinUserService;
import com.poly.utils.ChineseToPinyin;
import com.poly.utils.NumToRMBUtil;

@Controller
@RequestMapping(value = "/zhContract")
public class ZhContract {

	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 * dateFormat.setLenient(false); binder.registerCustomEditor(Date.class, new
	 * CustomDateEditor(dateFormat, false)); }
	 */
	private TbFinUserService tbFinUserService = new TbFinUserService();
	private MailDao mailDao = new MailDao();
	@RequestMapping(value = "/toAddZhContract")
	public String toAddCtContract(Model model) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sim.format(new Date());
		model.addAttribute("nowDate", nowDate);

		return "zhContractAdd";
	}
	//进入合同预览页面
	@RequestMapping(value = "/zhContractPreview")
	public String zhContractPreview(TbConZh tbConZh, Model model,
			HttpSession session, HttpServletRequest request,TbConAccount tbConAccount) {
		TbConZh yb = new TbConZh();
		TbConAccount acc = new TbConAccount();
		BeanUtils.copyProperties(tbConAccount, acc);
		BeanUtils.copyProperties(tbConZh, yb);
		SimpleDateFormat sim = new SimpleDateFormat("yyyy");
		String year = sim.format(new Date());
		String id = TbConZhService.findMaxConId(Integer.parseInt(year));
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user == null || user.getUsercode() == null) {
			return "redirect:/login";
		}
		String orgName = user.getOrgname();
		if(user.getOrgcode().equals("0")){
			orgName = "上海站";
		}
		orgName = orgName.substring(0, orgName.length() - 1);
		orgName = ChineseToPinyin.converterToFirstSpell(orgName);
		
		yb.setAl_bill(0);
		yb.setCon_total_priceUpperCase(NumToRMBUtil.changeToBig(yb.getCon_total_price()));
		if (id != null) {
			id = id.substring(id.length() - 4, id.length());
			int i = 0;
			try {
				i = Integer.parseInt(id);
				i++;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return "redirect:/login";
			}
			String num = String.valueOf(i);
			if (num.length() == 1) {
				id = "000" + num;
			} else if (num.length() == 2) {
				id = "00" + num;
			} else if (num.length() == 3) {
				id = "0" + num;
			} else if (num.length() == 4) {
				id = num;
			}
			id = "SHZH-" + orgName + "-" + year + "-" + id;
		} else {
			id = "SHZH-" + orgName + "-" + year + "-" + "0001";
		}
		yb.setCon_zh_id(id);
		acc.setCon_id(id);
		acc.setCreate(new Date());
		acc.setUpdate(new Date());
		if(yb.getCon_type()==3){
			//设置为广告+活动
			acc.setCon_type(8);
		}else{
			acc.setCon_type(yb.getCon_type());
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
		
		double con_total_price = yb.getCon_total_price();
		yb.setCreate(new Date());
		yb.setUpdate(new Date());
		List<TbConZhSub> list = yb.getTbConZhSubs();
		yb.setCon_state(5);// 设置合同状态为未提交
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.setAutoCommit(false);
			
			for (TbConZhSub tbConZhSub : list) {
				tbConZhSub.setCon_zh_id(id);
				TbConZhSubDao.save(dbc,tbConZhSub);
			}
			TbConZhDao.save(dbc,yb);
			TbConAccountDao.save(dbc, acc);
			dbc.commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				dbc.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("msg", "生成失败");
			return "result/result";
		}finally{
			if(dbc!=null){
				try {
					dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String upperCase = NumToRMBUtil.changeToBig(con_total_price);
		yb.setCon_total_priceUpperCase(upperCase);
		model.addAttribute("zhContract", yb);
		return "showContract/zhContractShow";
	}
	/**
	 * 开始流程，show页面中点击提交
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/zhStartFlow")
	@ResponseBody
	public String startFlow(HttpSession session,@RequestParam(value="conId",required=true)String conId,Model model) {
		List<TbConZh> where = TbConZhDao.where(" CON_ZH_ID='"+conId+"'");
		TbConZh zh = null;
		if(where != null && where.size() > 0){
			zh = where.get(0);
		}
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (zh != null && zh.getCon_zh_id() != null) {
			TbConZh zh1 = new TbConZh();
			BeanUtils.copyProperties(zh, zh1);
			zh1.setUpdate(new Date());
			
			String managerCheck = "";
			if (user != null && user.getUsercode() != null) {
				if (user.getOrgcode().equals("1") || user.getOrgcode().equals("0")) {
					managerCheck = this.tbFinUserService.findManagerCheck(user.getOrgcode(), user.getDeptcode());
				} else {
					managerCheck = this.tbFinUserService.findManagerCheck(user.getOrgcode());
				}
			}
			String areaCheck = this.tbFinUserService.findAreaCheck(user.getOrgcode());
			String flowCheck = this.tbFinUserService.findFlowCheck();
			model.addAttribute("zhContract", zh1);
			TbConFlow tbflow = new TbConFlow();
			if (!managerCheck.equals("")) {
				tbflow.setManager(managerCheck);
			}
			if(!areaCheck.equals("")){
				tbflow.setArea(areaCheck);
			}
			if (!flowCheck.equals("")) {
				tbflow.setFlow_check(flowCheck);
				tbflow.setFlow_seal(flowCheck);
			}
			 if(!managerCheck.equals("")){
				 tbflow.setNextcheck(managerCheck);
				 zh1.setCon_state(0);
				 tbflow.setCon_state(0);
			 }else{
				 if(!areaCheck.equals("")){
					 tbflow.setNextcheck(areaCheck);
					 zh1.setCon_state(1);
					 tbflow.setCon_state(1);
				 }else{
					 if(!flowCheck.equals("")){
						 tbflow.setNextcheck(flowCheck);
						 zh1.setCon_state(7);
						 tbflow.setCon_state(7);
					 }
				 }
				 
			 }
			tbflow.setCon_id(zh1.getCon_zh_id());
			List<TbConFlow> where2 = TbConFlowDao.where(" CON_ID='"+zh1.getCon_zh_id()+"' ");
			//防止用户重复提交
			if(where2 != null && where2.size()>0){
				TbConFlow exist = where2.get(0);
				tbflow.setCon_flow_id(exist.getCon_flow_id());
				TbConFlowDao.update(tbflow);
				TbConZhDao.update(zh1);
			}else{
				try {
					TbConFlowDao.save(tbflow);
					TbConZhDao.update(zh1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			String[] split = tbflow.getNextcheck().split("/");
			for(int i=0;i<split.length;i++){
				split[i] = split[i].replace("+", "");
			}
			List<String> phones = tbFinUserService.getUserPhoneByUserIds(Arrays.asList(split));
			for(String phone : phones){
				this.mailDao.sendMail("", "", "您有一条来自"+zh.getUser_name()+"的众智广告合同审核申请，请尽快处理。", phone, "0");
			}
			return "{'ok':'200'}";
		}

		return "";
	}

	/**
	 * 返回到update页面，将id带过去。并在页面中做判断，如果是提交过的合同不能更改
	 * @param conId
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/toupdateZhContract")
	public String toupdateZhContract(@RequestParam(value = "conId") String conId,Model model,HttpSession session) {
		List<TbConZh> where = TbConZhDao.where(" CON_ZH_ID='" + conId + "'");
		if (where != null && where.size() > 0) {
			TbConZh zh = where.get(0);
			List<TbConZhSub> where2 = TbConZhSubDao.where(" CON_ZH_ID='"+zh.getCon_zh_id()+"' ");
			if(where2 != null && where2.size()>0){
				zh.setTbConZhSubs(where2);
			}
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			String nowdate = sim.format(new Date());
			List<TbConAccount> list = TbConAccountDao.where(" CON_ID='"+zh.getCon_zh_id()+"' order by `update` desc");
			if(list!=null && list.size()>0){
				model.addAttribute("conAccount", list.get(0));
			}
			model.addAttribute("nowdate", nowdate);
			model.addAttribute("zhContract", zh);
		}

		return "/updateContract/updateZhContract";
	}
/**
 * 对更新操作进行处理，最后统一跳转到preview页面
 * @param tbConZh
 * @param session
 * @return
 */
	@RequestMapping(value = "/updateZhContract")
	public String updateZhContract(TbConZh tbConZh, Model model,HttpSession session,TbConAccount tbConAccount) {
		TbConZh tb = new TbConZh();
		TbConAccount acc = new TbConAccount();
		BeanUtils.copyProperties(tbConAccount, acc);
		BeanUtils.copyProperties(tbConZh, tb);
		
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if (user == null || user.getUsercode() == null) {
			return "redirect:/login";
		}
		int accId = 0;
		List<TbConAccount> where = TbConAccountDao.where("CON_ID='"+tb.getCon_zh_id()+"' ");
		if(where!=null&&where.size()>0){
			accId = where.get(0).getCon_s_id();
		}
		if(tb.getCon_type()==3){
			//设置为广告+活动
			acc.setCon_type(8);
		}else{
			acc.setCon_type(tb.getCon_type());
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
		acc.setCon_s_id(accId);
		acc.setUpdate(new Date());
		tb.setUpdate(new Date());
		tb.setCon_state(5);
		//更新时先把子表中的数据全部删除掉
		
		List<TbConZhSub> tbConZhSubs = tb.getTbConZhSubs();
		String upperCase = NumToRMBUtil.changeToBig(tb.getCon_total_price());
		tb.setCon_total_priceUpperCase(upperCase);
		tb.COLUMN_FLAG[9] = false;
		acc.COLUMN_FLAG[15] = false;
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.setAutoCommit(false);
			TbConZhSubDao.delete(dbc," CON_ZH_ID='"+tb.getCon_zh_id()+"' ");
			for (TbConZhSub tbConZhSub : tbConZhSubs) {
				tbConZhSub.setCon_zh_id(tb.getCon_zh_id());
				TbConZhSubDao.save(dbc,tbConZhSub);
			}
			TbConZhDao.update(dbc,tbConZh);
			TbConAccountDao.update(dbc,acc);
			dbc.commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				dbc.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("msg", "更新失败");
			return "result/result";
		}finally{
			if(dbc!=null){
				try {
					dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("zhContract", tb);
		return "/showContract/zhContractShow";
	}
	

	@RequestMapping(value="/viewZhContract")
	public String viewZhContract(@RequestParam("conId")String conId,HttpSession session,Model model){
		List<TbConZh> where = TbConZhDao.where(" CON_ZH_ID='"+conId+"' ");
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if(user==null || user.getUsercode() == null){
			return "redirect:/login";
		}
		List<TbConZhSub> where2 = TbConZhSubDao.where(" CON_ZH_ID='"+conId+"' ");
		TbConZh zh = null;
		if(where!=null && where.size()>0){
			zh = where.get(0);
			
		}
		if(zh != null){
			zh.setCon_total_priceUpperCase(NumToRMBUtil.changeToBig(zh.getCon_total_price()));
		}
		if(where2 !=null && where2.size()>0){
			zh.setTbConZhSubs(where2);
		}
		int billCount = TbConBillDao.whereCount(" CON_ID='"+conId+"' and BILL_STATE <> '5' and bill_state<>3 ");
		int rebateCount = TbConRebateDao.whereCount(" CON_ID='"+conId+"' and REBATE_STATE <> '6'");
		int accCount = TbConAccountDao.whereCount("CON_ID='"+conId+"' ");
		model.addAttribute("billCount", billCount);
		model.addAttribute("rebateCount", rebateCount);
		model.addAttribute("accCount", accCount);
		model.addAttribute("zhContract", zh);
		return "viewContract/viewZhContract";
	}
	
	
	@RequestMapping(value="/delZhContract")
	public String delZhContract(@RequestParam(value="conId")String conId,@RequestParam(value="pageNum")String pageNum,HttpSession session,Model model){
		List<TbConZh> where = TbConZhDao.where(" CON_Zh_ID='"+conId+"' ");
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if(user==null || user.getUsercode() == null){
			return "redirect:/login";
		}
		TbConZh zh = null;
		if(where!=null && where.size()>0){
			zh = where.get(0);
		}
		if(zh!=null){
			zh.setUpdate(new Date());
			zh.setCon_state(6);
			TbConZhSubDao.delete(" CON_ZH_ID='"+zh.getCon_zh_id()+"' ");
			TbConZhDao.update(zh);
		}
		return "redirect:/myContract/showZhContract?pageNum="+pageNum;
	}

}
