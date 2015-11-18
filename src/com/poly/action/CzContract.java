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
import car_beans.TbConCz;
import car_beans.TbConFlow;
import car_beans.TbConZhSub;
import car_beans.UserInfo;
import car_daos.DBConnect;
import car_daos.TbConAccountDao;
import car_daos.TbConBillDao;
import car_daos.TbConCzDao;
import car_daos.TbConFlowDao;
import car_daos.TbConRebateDao;
import car_daos.TbConZhSubDao;

import com.poly.dao.subDaos.MailDao;
import com.poly.services.BrandAndSeriesService;
import com.poly.services.TbConCzService;
import com.poly.services.TbFinUserService;
import com.poly.utils.ChineseToPinyin;
import com.poly.utils.NumToRMBUtil;

@Controller
@RequestMapping(value="/czContract")
public class CzContract {

	private TbFinUserService tbFinUserService = new TbFinUserService();
	private BrandAndSeriesService brService = new BrandAndSeriesService();
	private MailDao mailDao = new MailDao();
	 @RequestMapping(value="/toAddCzContract")
	 public String toAddCtContract(Model model){
		 SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
	    	String nowDate = sim.format(new Date());
	    	model.addAttribute("nowDate", nowDate);

		 return "czContractAdd";
	 }

	 @RequestMapping(value = "/czContractPreview")
		public String czContractPreview(TbConCz tbConCz, Model model,
				HttpSession session, HttpServletRequest request,TbConAccount tbConAccount) {
			TbConCz cz = new TbConCz();
			TbConAccount acc = new TbConAccount();
			BeanUtils.copyProperties(tbConCz, cz);
			BeanUtils.copyProperties(tbConAccount, acc);
			String conId = null;
			SimpleDateFormat sim = new SimpleDateFormat("yyyy");
			List<TbConZhSub> czSubs = cz.getTbConZhSubs();
			String year = sim.format(new Date());
			conId = TbConCzService.findMaxConId(Integer.parseInt(year));
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
				conId = "SHCZ-" + orgName + "-" + year + "-" + conId;
			} else {
				conId = "SHCZ-" + orgName + "-" + year + "-" + "0001";
			}
			cz.setCon_cz_id(conId);
			cz.setCreate(new Date());
			cz.setUpdate(new Date());
			cz.setCon_state(5);
			acc.setCon_id(conId);
			int i = cz.getCon_type();
			if (i == 0) {
				acc.setCon_type(3);
			} else if (i == 1) {
				acc.setCon_type(4);
			} else if (i == 2){
				//设置为车展+活动
				acc.setCon_type(7);
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
                    TbConCzDao.save(dbc, cz);
                    TbConAccountDao.save(dbc,acc);
                    if(cz.getCon_type()==1){
                    	if(czSubs.size()>0){
                    		for(TbConZhSub sub : czSubs){
                    			sub.setCon_zh_id(cz.getCon_cz_id());
                    			TbConZhSubDao.save(dbc, sub);
                    		}
                    	}
                    }
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
			double totalPrice = cz.getCon_total_price();
			String upperCase = NumToRMBUtil.changeToBig(totalPrice);
			int series = Integer.parseInt(cz.getCus_seriers());
			String seriesName = "全系";
			if(series!=-1){
				seriesName = this.brService.getNameByCatalogid(series);
			}else if(series==-2){
				seriesName = "";
			}
			cz.setSeriers_name(seriesName);
			cz.setCon_total_priceUpperCase(upperCase);
			model.addAttribute("czContract", cz);
			return "showContract/czContractShow";
		}

	 @RequestMapping(value="/czStartFlow")
	 @ResponseBody
	 public String startFlow(@RequestParam("conId")String conId,HttpSession session,Model model){

	         List<TbConCz> where = TbConCzDao.where(" CON_CZ_ID='"+conId+"' ");
	         TbConCz cz = null;
	         if(where!=null && where.size()>0){
	             cz = where.get(0);
	         }

		 UserInfo user = (UserInfo) session.getAttribute("userInfo");
		 if(cz != null && cz.getCon_cz_id() != null){

			 cz.setUpdate(new Date());
			 String managerCheck = "";
			 if(user != null && user.getUsercode() != null){
				 if(user.getOrgcode().equals("1") || user.getOrgcode().equals("0")){
					 managerCheck = this.tbFinUserService.findManagerCheck(user.getOrgcode(),user.getDeptcode());
				 }else{
					 managerCheck = this.tbFinUserService.findManagerCheck(user.getOrgcode());
				 }
			 }
			 String flowCheck = this.tbFinUserService.findFlowCheck();
			 String areaCheck = this.tbFinUserService.findAreaCheck(user.getOrgcode());
			model.addAttribute("czContract", cz);
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
				 cz.setCon_state(0);
				 tbflow.setCon_state(0);
			 }else{
				 if(!areaCheck.equals("")){
					 tbflow.setNextcheck(areaCheck);
					 cz.setCon_state(1);
					 tbflow.setCon_state(1);
				 }else{
					 if(!flowCheck.equals("")){
						 tbflow.setNextcheck(flowCheck);
						 cz.setCon_state(7);
						 tbflow.setCon_state(7);
					 }
				 }
				 
			 }
			 tbflow.setCon_id(cz.getCon_cz_id());
			 List<TbConFlow> where2 = TbConFlowDao.where(" CON_ID='"+cz.getCon_cz_id()+"'");
				//防止用户重复提交
				if(where2 != null && where2.size()>0){
					TbConFlow exist = where2.get(0);
					tbflow.setCon_flow_id(exist.getCon_flow_id());
					TbConFlowDao.update(tbflow);
					TbConCzDao.update(cz);
				}else{
					 try {
							TbConFlowDao.save(tbflow);
							TbConCzDao.update(cz);
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
					this.mailDao.sendMail("", "", "您有一条来自"+cz.getUser_name()+"的车展合同审核申请，请尽快处理。",phone, "0");
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
		@RequestMapping(value = "/toupdateCzContract")
		public String toupdateCzContract(@RequestParam(value = "conId") String conId,Model model,HttpSession session) {
			List<TbConCz> where = TbConCzDao.where(" CON_CZ_ID='" + conId + "'");
			if (where != null && where.size() > 0) {
				TbConCz cz = where.get(0);
				List<TbConZhSub> where2 = TbConZhSubDao.where(" CON_ZH_ID='"+cz.getCon_cz_id()+"' ");
				if(where2 != null && where2.size()>0){
					cz.setTbConZhSubs(where2);
				}
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				String nowdate = sim.format(new Date());
				List<TbConAccount> list = TbConAccountDao.where(" CON_ID='"+cz.getCon_cz_id()+"' order by `update` desc");
				if(list!=null && list.size()>0){
					model.addAttribute("conAccount", list.get(0));
				}
				model.addAttribute("nowDate", nowdate);
				model.addAttribute("czContract", cz);
			}

			return "/updateContract/updateCzContract";
		}
	/**
	 * 对更新操作进行处理，最后统一跳转到preview页面
	 * @param tbConCt
	 * @param session
	 * @return
	 */
		@RequestMapping(value = "/updateCzContract")
		public String updateCzContract(TbConCz tbConCz, HttpSession session,Model model,TbConAccount tbConAccount) {
			TbConCz cz = new TbConCz();
			TbConAccount acc = new TbConAccount();
			BeanUtils.copyProperties(tbConAccount, acc);
			BeanUtils.copyProperties(tbConCz, cz);
			int accId = 0;
			List<TbConAccount> where = TbConAccountDao.where(" CON_ID='"+cz.getCon_cz_id()+"' ");
			if(where!=null && where.size()>0){
				accId = where.get(0).getCon_s_id();
			}
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			if (user == null || user.getUsercode() == null) {
				return "redirect:/login";
			}
			int i = cz.getCon_type();
			if (i == 0) {
				acc.setCon_type(3);
			} else if (i == 1) {
				acc.setCon_type(4);
			} else if (i == 2){
				//设置为车展+活动
				acc.setCon_type(7);
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
			cz.setUpdate(new Date());
			cz.setCon_state(5);
			String upperCase = NumToRMBUtil.changeToBig(cz.getCon_total_price());
			cz.setCon_total_priceUpperCase(upperCase);
			cz.COLUMN_FLAG[14] = false;
			acc.COLUMN_FLAG[15] = false;
			acc.setCon_s_id(accId);
			acc.setUpdate(new Date());
			DBConnect dbc = null;
			try {
				dbc = new DBConnect();
				dbc.setAutoCommit(false);
				if(cz.getCon_type()==1){
					if(cz.getTbConZhSubs().size()>0){
						TbConZhSubDao.delete(dbc," CON_ZH_ID='"+cz.getCon_cz_id()+"' ");
						for (TbConZhSub tbConZhSub : cz.getTbConZhSubs()) {
							tbConZhSub.setCon_zh_id(cz.getCon_cz_id());
							TbConZhSubDao.save(dbc,tbConZhSub);
						}
					}
				}
				TbConCzDao.update(dbc,cz);
				TbConAccountDao.update(dbc, acc);
				dbc.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					dbc.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
			int series = Integer.parseInt(cz.getCus_seriers());
			String seriesName = "全系";
			if(series!=-1){
				seriesName = this.brService.getNameByCatalogid(series);
			}else if(series==-2){
				seriesName = "";
			}
			cz.setSeriers_name(seriesName);
			model.addAttribute("czContract", cz);
			return "/showContract/czContractShow";
		}
		
		/**
		 * 展示车展合同
		 * @param conId
		 * @param session
		 * @param model
		 * @return
		 */
		@RequestMapping(value="/viewCzContract")
		public String viewCzContract(@RequestParam("conId")String conId,HttpSession session,Model model){
			List<TbConCz> where = TbConCzDao.where(" CON_CZ_ID='"+conId+"' ");
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			if(user==null || user.getUsercode() == null){
				return "redirect:/login";
			}
			List<TbConZhSub> where2 = TbConZhSubDao.where(" CON_ZH_ID='"+conId+"' ");
			
			TbConCz cz = null;
			if(where!=null && where.size()>0){
				cz = where.get(0);
			}
			if(cz != null){
				cz.setCon_total_priceUpperCase(NumToRMBUtil.changeToBig(cz.getCon_total_price()));
				int series = Integer.parseInt(cz.getCus_seriers());
				String seriesName = "全系";
				if(series!=-1){
					seriesName = this.brService.getNameByCatalogid(series);
				}else if(series==-2){
					seriesName = "";
				}
				if(where2!=null && where2.size()>0){
					cz.setTbConZhSubs(where2);
				}
				cz.setSeriers_name(seriesName);
			}
			int billCount = TbConBillDao.whereCount(" CON_ID='"+conId+"' and BILL_STATE <> '5' and bill_state<>3 ");
			int rebateCount = TbConRebateDao.whereCount(" CON_ID='"+conId+"' and REBATE_STATE <> '6'");
			int accCount = TbConAccountDao.whereCount("CON_ID='"+conId+"' ");
			model.addAttribute("billCount", billCount);
			model.addAttribute("rebateCount", rebateCount);
			model.addAttribute("accCount", accCount);
			model.addAttribute("czContract", cz);
			return "viewContract/viewCzContract";
		}
		
		
		@RequestMapping(value="/delCzContract")
		public String delCzContract(@RequestParam(value="conId")String conId,@RequestParam(value="pageNum")String pageNum,HttpSession session,Model model){
			List<TbConCz> where = TbConCzDao.where(" CON_CZ_ID='"+conId+"' ");
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			if(user==null || user.getUsercode() == null){
				return "redirect:/login";
			}
			TbConCz cz = null;
			if(where!=null && where.size()>0){
				cz = where.get(0);
			}
			if(cz!=null){
				cz.setUpdate(new Date());
				cz.setCon_state(6);
				TbConZhSubDao.delete(" CON_ZH_ID='"+cz.getCon_cz_id()+"' ");
				TbConCzDao.update(cz);
			}
			return "redirect:/myContract/showCzContract?pageNum="+pageNum;
		}
}

