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
import car_beans.TbConCt;
import car_beans.TbConFlow;
import car_beans.UserInfo;
import car_daos.DBConnect;
import car_daos.TbConAccountDao;
import car_daos.TbConBillDao;
import car_daos.TbConCtDao;
import car_daos.TbConFlowDao;
import car_daos.TbConRebateDao;

import com.poly.dao.subDaos.MailDao;
import com.poly.services.BrandAndSeriesService;
import com.poly.services.TbConCtService;
import com.poly.services.TbFinUserService;
import com.poly.utils.ChineseToPinyin;

@Controller
@RequestMapping(value = "/ctContract")
public class CtContract {

	private TbFinUserService tbFinUserService = new TbFinUserService();
	private BrandAndSeriesService brService = new BrandAndSeriesService();
	private MailDao mailDao = new MailDao();
	// 打开添加车团合同的页面
	@RequestMapping(value = "/toAddCtContract")
	public String toAddCtContract(Model model) {

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sim.format(new Date());
		model.addAttribute("nowDate", nowDate);
		return "ctContractAdd";
	}

	@RequestMapping(value = "/ctContractPreview")
	public String ctContractPreview(TbConCt tbConCt, Model model,
			HttpSession session, HttpServletRequest request,TbConAccount tbConAccount) {
		TbConCt ct = new TbConCt();
		BeanUtils.copyProperties(tbConCt, ct);
		String conId = null;
		SimpleDateFormat sim = new SimpleDateFormat("yyyy");
		String year = sim.format(new Date());
		conId = TbConCtService.findMaxConId(Integer.parseInt(year));
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
		
		if (conId != null) {
			conId = conId.substring(conId.length() - 4, conId.length());
			int i = 0;
			try {
				i = Integer.parseInt(conId);
				i++;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return "redirect:/login";
			}
			String num = String.valueOf(i);
			if (num.length() == 1) {
				conId = "000" + num;
			} else if (num.length() == 2) {
				conId = "00" + num;
			} else if (num.length() == 3) {
				conId = "0" + num;
			} else if (num.length() == 4) {
				conId = num;
			}
			conId = "SHCT-" + orgName + "-" + year + "-" + conId;
		} else {
			conId = "SHCT-" + orgName + "-" + year + "-" + "0001";
		}
		ct.setCon_ct_id(conId);
		ct.setCreate(new Date());
		ct.setUpdate(new Date());
		ct.setCon_state(5);
		TbConAccount acc = new TbConAccount();
		BeanUtils.copyProperties(tbConAccount, acc);
		acc.setCon_id(conId);
			int i = ct.getBus_type();
			if (i == 1) {
				acc.setCon_type(5);
			}else if(i == 2){
				acc.setCon_type(6);
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
			DBConnect dbc = null;
		try {
                    dbc = new DBConnect();
                    dbc.setAutoCommit(false);
                    TbConCtDao.save(dbc, ct);
                    TbConAccountDao.save(dbc,acc);
                    dbc.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
						dbc.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    model.addAttribute("msg", "生成失败");
                    return "result/result";
                }finally{
                	if(dbc!=null){
                		try {
							dbc.close();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                	}
                }
		//double totalPrice = ct.getCon_total_price();
		int series = Integer.parseInt(ct.getCus_seriers());
		String seriesName = "全系";
		if(series!=-1){
			seriesName = this.brService.getNameByCatalogid(series);
		}else if(series==-2){
			seriesName = "";
		}
		ct.setSeriers_name(seriesName);
		//String upperCase = NumToRMBUtil.changeToBig(totalPrice);
		//ct.setCon_total_priceUpperCase(upperCase);
		model.addAttribute("ctContract", ct);
		return "showContract/ctContractShow";
	}

	 @RequestMapping(value="/ctStartFlow")
	 @ResponseBody
	 public String startFlow(@RequestParam("conId")String conId,HttpSession session,Model model){
		 TbConCt ct = null;
		 List<TbConCt> where = TbConCtDao.where(" CON_CT_ID='"+conId+"' ");
		 UserInfo user = (UserInfo) session.getAttribute("userInfo");
		 if(where!=null && where.size()>0){
		     ct = where.get(0);
		 }
		 if(ct != null && ct.getCon_ct_id() != null){

			 ct.setUpdate(new Date());
			
			 
			 String managerCheck = "";
			 if(user != null && user.getUsercode() != null){
				 if(user.getOrgcode().equals("1") || user.getOrgcode().equals("0")){
					 managerCheck = this.tbFinUserService.findManagerCheck(user.getOrgcode(),user.getDeptcode());
				 }else{
					 managerCheck = this.tbFinUserService.findManagerCheck(user.getOrgcode());
				 }
			 }
			 String areaCheck = this.tbFinUserService.findAreaCheck(user.getOrgcode());
			 String flowCheck = this.tbFinUserService.findFlowCheck();
			 model.addAttribute("ctContract", ct);
			 TbConFlow tbflow = new TbConFlow();
			 if(!managerCheck.equals("")){
				 tbflow.setManager(managerCheck);
			 }
			 if(!areaCheck.equals("")){
				 tbflow.setArea(areaCheck);
			 }
			 if(!flowCheck.equals("")){
				 tbflow.setFlow_check(flowCheck);
				 tbflow.setFlow_seal(flowCheck);
			 }
			 if(!managerCheck.equals("")){
				 tbflow.setNextcheck(managerCheck);
				 ct.setCon_state(0);
				 tbflow.setCon_state(0);
			 }else{
				 if(!areaCheck.equals("")){
					 tbflow.setNextcheck(areaCheck);
					 ct.setCon_state(1);
					 tbflow.setCon_state(1);
				 }else{
					 if(!flowCheck.equals("")){
						 tbflow.setNextcheck(flowCheck);
						 ct.setCon_state(7);
						 tbflow.setCon_state(7);
					 }
				 }
				 
			 }
			 tbflow.setCon_id(ct.getCon_ct_id());
			 List<TbConFlow> where2 = TbConFlowDao.where(" CON_ID='"+ct.getCon_ct_id()+"'");
				//防止用户重复提交
				if(where2 != null && where2.size()>0){
					TbConFlow exist = where2.get(0);
					tbflow.setCon_flow_id(exist.getCon_flow_id());
					TbConFlowDao.update(tbflow);
					TbConCtDao.update(ct);
				}else{
					 try {
							TbConFlowDao.save(tbflow);
							TbConCtDao.update(ct);
						} catch (Exception e) {
							e.printStackTrace();
							return "";
						}
			    }
				String[] split = tbflow.getNextcheck().split("/");
				for(int i=0;i<split.length;i++){
					split[i] = split[i].replace("+", "");
				}
				List<String> phones = tbFinUserService.getUserPhoneByUserIds(Arrays.asList(split));
				for(String phone : phones){
					this.mailDao.sendMail("", "", "您有一条来自"+ct.getUser_name()+"的车团合同审核申请，请尽快处理。", phone, "0");
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
		@RequestMapping(value = "/toupdateCtContract")
		public String toupdateCtContract(@RequestParam(value = "conId") String conId,Model model,HttpSession session) {
			List<TbConCt> where = TbConCtDao.where(" CON_CT_ID='" + conId + "' ");
			if (where != null && where.size() > 0) {
				TbConCt ct = where.get(0);
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				String nowdate = sim.format(new Date());
				List<TbConAccount> list = TbConAccountDao.where(" CON_ID='"+ct.getCon_ct_id()+"' order by `update` desc");
				
				if(list!=null && list.size()>0){
					model.addAttribute("conAccount", list.get(0));
				}
				model.addAttribute("nowDate", nowdate);
				
				model.addAttribute("ctContract", ct);
			}

			return "updateContract/updateCtContract";
		}
	/**
	 * 对更新操作进行处理，最后统一跳转到preview页面
	 * @param tbConCt
	 * @param session
	 * @return
	 */
		@RequestMapping(value = "/updateCtContract")
		public String updateCtContract(TbConCt tbConCt, HttpSession session,Model model,TbConAccount tbConAccount) {
			TbConCt ct = new TbConCt();
			BeanUtils.copyProperties(tbConCt, ct);
			TbConAccount acc = new TbConAccount();
			BeanUtils.copyProperties(tbConAccount, acc);
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			if (user == null || user.getUsercode() == null) {
				return "redirect:/login";
			}
			int accId = 0;
			List<TbConAccount> where = TbConAccountDao.where(" CON_ID='"+ct.getCon_ct_id()+"' ");
			if(where!=null && where.size()>0){
				accId = where.get(0).getCon_s_id();
			}
			int i = ct.getBus_type();
			if (i == 1) {
				acc.setCon_type(5);
			}else if(i == 2){
				acc.setCon_type(6);
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
			ct.setUpdate(new Date());
			ct.setCon_state(5);
			acc.COLUMN_FLAG[15] = false;
			//String upperCase = NumToRMBUtil.changeToBig(ct.getCon_total_price());
			//ct.setCon_total_priceUpperCase(upperCase);
			ct.COLUMN_FLAG[16] = false;
			DBConnect dbc = null;
			try {
				dbc = new DBConnect();
				dbc.setAutoCommit(false);
				TbConCtDao.update(dbc,ct);
				TbConAccountDao.update(dbc, acc);
				dbc.commit();
			} catch (Exception e) {
				try {
					dbc.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
					
				}
				e.printStackTrace();
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
			
			int series = Integer.parseInt(ct.getCus_seriers());
			String seriesName = "全系";
			if(series!=-1){
				seriesName = this.brService.getNameByCatalogid(series);
			}else if(series==-2){
				seriesName = "";
			}
			ct.setSeriers_name(seriesName);
			model.addAttribute("ctContract", ct);
			return "/showContract/ctContractShow";
		}
		
		@RequestMapping(value="/viewCtContract")
		public String viewCtContract(@RequestParam("conId")String conId,HttpSession session,Model model){
			List<TbConCt> where = TbConCtDao.where(" CON_CT_ID='"+conId+"' ");
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			if(user==null || user.getUsercode() == null){
				return "redirect:/login";
			}
			TbConCt ct = null;
			if(where!=null && where.size()>0){
				ct = where.get(0);
			}
			if(ct != null){
				//ct.setCon_total_priceUpperCase(NumToRMBUtil.changeToBig(ct.getCon_total_price()));
				int series = Integer.parseInt(ct.getCus_seriers());
				String seriesName = "全系";
				if(series!=-1){
					seriesName = this.brService.getNameByCatalogid(series);
				}else if(series==-2){
					seriesName = "";
				}
				ct.setSeriers_name(seriesName);
			}
			int billCount = TbConBillDao.whereCount(" CON_ID='"+conId+"' and BILL_STATE <> '5' and bill_state<>3 ");
			int rebateCount = TbConRebateDao.whereCount(" CON_ID='"+conId+"' and REBATE_STATE <> '6'");
			int accCount = TbConAccountDao.whereCount("CON_ID='"+conId+"'");
			model.addAttribute("billCount", billCount);
			model.addAttribute("rebateCount", rebateCount);
			model.addAttribute("accCount", accCount);
			model.addAttribute("ctContract", ct);
			
			return "viewContract/viewCtContract";
		}
		
		@RequestMapping(value="/delCtContract")
		public String delCtContract(@RequestParam(value="conId")String conId,@RequestParam(value="pageNum")String pageNum,HttpSession session,Model model){
			List<TbConCt> where = TbConCtDao.where(" CON_CT_ID='"+conId+"' ");
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			if(user==null || user.getUsercode() == null){
				return "redirect:/login";
			}
			TbConCt ct = null;
			if(where!=null && where.size()>0){
				ct = where.get(0);
			}
			if(ct!=null){
				ct.setUpdate(new Date());
				ct.setCon_state(6);
				TbConCtDao.update(ct);
			}
			return "redirect:/myContract/showCtContract?pageNum="+pageNum;
		}
}
