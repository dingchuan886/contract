package com.poly.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import car_beans.TbConAddcon;
import car_beans.TbConBill;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConFlow;
import car_beans.TbConRebate;
import car_beans.TbConZh;
import car_beans.TbConZhSub;
import car_beans.UserInfo;
import car_daos.TbConAccountDao;
import car_daos.TbConBillDao;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConFlowDao;
import car_daos.TbConRebateDao;
import car_daos.TbConZhDao;
import car_daos.TbConZhSubDao;

import com.poly.bean.PageCondition;
import com.poly.bean.PageResult;
import com.poly.bean.ShowFlowBean;
import com.poly.dao.subDaos.TbConDetailSubDao;
import com.poly.dao.subDaos.TbConUserSubDao;
import com.poly.services.BrandAndSeriesService;
import com.poly.services.ConSupplyService;
import com.poly.services.DateStatisticService;
import com.poly.services.MyContractService;
import com.poly.services.TbFinUserService;
import com.poly.utils.NumToRMBUtil;
@Controller
@RequestMapping("/flowViewContract")
public class FlowViewContractController {
	MyContractService myContractService = new MyContractService();
	TbConUserSubDao tbConUserSubDao = new TbConUserSubDao();
	TbConDetailSubDao tbConDetailSubDao = new TbConDetailSubDao();
	private BrandAndSeriesService brService = new BrandAndSeriesService();
	private TbFinUserService finservice = new TbFinUserService();
	private DateStatisticService statisticService = new DateStatisticService();
	private ConSupplyService supplyService = new ConSupplyService();
	@RequestMapping(value = "/showZhContract")
	public String showZhContract(HttpSession session,Model model,PageCondition pageCondition,@RequestParam(value="pageNum",defaultValue="1")int pageNum) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" 1=1 ");
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and CON_TYPE="+pageCondition.getConType()+" ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setUserName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getOrgName()!=null &&(!pageCondition.getOrgName().equals(""))){
			String orgName = pageCondition.getOrgName();
			String str = "";
			try {
				str = new String(orgName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setOrgName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and USER_ID in (select u.USER_CODE from tb_fin_user u,tb_fin_org o where u.ORG_CODE=o.ORG_ID and o.ORG_NAME like '%"+str+"%') ");
		}
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' ");
		Map<String, Double> allZhMoney = statisticService.getAllZhMoney(sb.toString());
		int totalRecords = this.myContractService.findAllZhContractCount(sb.toString());
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		int startIndex = page.getStartIndex();
		int pageSize = page.getPageSize();
		List<TbConZh> list = this.myContractService.findZhContract(sb.toString(),startIndex,pageSize);
		
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConZh conZh : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conZh.getCon_zh_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conZh.getCon_zh_id()+"'");
				String orgName = this.finservice.getOrgNameByUserId(conZh.getUser_id());
				String deptName = this.finservice.getDeptNameByUserId(conZh.getUser_id());
				List<TbConZhSub> conZhSubs = TbConZhSubDao.where(" CON_ZH_ID='"+conZh.getCon_zh_id()+"' ");
				List<TbConAddcon> addCons = new ArrayList<TbConAddcon>();
				if(conZhSubs!=null && conZhSubs.size()>0){
					for (TbConZhSub tbConZhSub : conZhSubs) {
						TbConAddcon conSupplyByZh = supplyService.getConSupplyByZh(conZh.getCon_zh_id(), tbConZhSub.getCon_zh_sub_id());
						addCons.add(conSupplyByZh);
					}
				}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conZh", conZh);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				map.put("conZhSub", conZhSubs);
				map.put("supZh", addCons);
				pager.add(map);
			}
		}	
			page.setList(pager);
		
		
			model.addAttribute("myZhContract", page);
		
		model.addAttribute("pageCondition", pageCondition);
		model.addAttribute("allZhMoney", allZhMoney);
		return "lcb/lcbZhContract";
	}
	
	@RequestMapping(value="/showCzContract")
	public String showCzContract(HttpSession session,Model model,PageCondition pageCondition,@RequestParam(value="pageNum",defaultValue="1")int pageNum){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" 1=1 ");
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and CON_TYPE="+pageCondition.getConType()+" ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setUserName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getOrgName()!=null &&(!pageCondition.getOrgName().equals(""))){
			String orgName = pageCondition.getOrgName();
			String str = "";
			try {
				str = new String(orgName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setOrgName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and USER_ID in (select u.USER_CODE from tb_fin_user u,tb_fin_org o where u.ORG_CODE=o.ORG_ID and o.ORG_NAME like '%"+str+"%') ");
		}
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' ");
		Map<String, Double> allCzMoney = statisticService.getAllCzMoney(sb.toString());
		int totalRecords = this.myContractService.findAllCzContractCount(sb.toString());
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		int startIndex = page.getStartIndex();
		int pageSize = page.getPageSize();
		List<TbConCz> list = this.myContractService.findCzContract(sb.toString(),startIndex,pageSize);
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConCz conCz : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conCz.getCon_cz_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conCz.getCon_cz_id()+"'");
				Map<String,Object> map = new HashMap<String,Object>();
				TbConAddcon supplyByCz = supplyService.getConSupplyByCz(conCz.getCon_cz_id());
				int series = Integer.parseInt(conCz.getCus_seriers());
				String seriesName = "全系";
				if(series!=-1){
					seriesName = this.brService.getNameByCatalogid(series);
				}else if(series==-2){
					seriesName = "";
				}
				conCz.setSeriers_name(seriesName);
				String orgName = this.finservice.getOrgNameByUserId(conCz.getUser_id());
				String deptName = this.finservice.getDeptNameByUserId(conCz.getUser_id());
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conCz", conCz);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				map.put("supCz",supplyByCz);
				pager.add(map);
			}
		}
		page.setList(pager);
		
		
			model.addAttribute("myCzContract", page);
		
		model.addAttribute("pageCondition", pageCondition);
		model.addAttribute("allCzMoney", allCzMoney);
		return "lcb/lcbCzContract";
	}
	
	@RequestMapping(value="/showCtContract")
	public String showCtContract(HttpSession session,Model model,PageCondition pageCondition,@RequestParam(value="pageNum",defaultValue="1")int pageNum){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" 1=1 ");
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and BUS_TYPE='"+pageCondition.getConType()+"' ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setUserName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getOrgName()!=null &&(!pageCondition.getOrgName().equals(""))){
			String orgName = pageCondition.getOrgName();
			String str = "";
			try {
				str = new String(orgName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setOrgName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and USER_ID in (select u.USER_CODE from tb_fin_user u,tb_fin_org o where u.ORG_CODE=o.ORG_ID and o.ORG_NAME like '%"+str+"%') ");
		}
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' ");
		Map<String, Double> allCtMoney = statisticService.getAllCtMoney(sb.toString());
		int totalRecords = this.myContractService.findAllCtContractCount(sb.toString());
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		int startIndex = page.getStartIndex();
		int pageSize = page.getPageSize();
		List<TbConCt> list = this.myContractService.findCtContract(sb.toString(),startIndex,pageSize);
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConCt conCt : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conCt.getCon_ct_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conCt.getCon_ct_id()+"'");
				Map<String,Object> map = new HashMap<String,Object>();
				TbConAddcon supplyByCt = supplyService .getConSupplyByCt(conCt.getCon_ct_id());
				int series = Integer.parseInt(conCt.getCus_seriers());
				String seriesName = "全系";
				if(series!=-1){
					seriesName = this.brService.getNameByCatalogid(series);
				}else if(series==-2){
					seriesName = "";
				}
				conCt.setSeriers_name(seriesName);
				String orgName = this.finservice.getOrgNameByUserId(conCt.getUser_id());
				String deptName = this.finservice.getDeptNameByUserId(conCt.getUser_id());
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conCt", conCt);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				map.put("supCt",supplyByCt);
				pager.add(map);
			}
		}
		page.setList(pager);
		
			model.addAttribute("myCtContract", page);
		model.addAttribute("pageCondition", pageCondition);
		model.addAttribute("allCtMoney", allCtMoney);
		return "lcb/lcbCtContract";
	}
	

	@RequestMapping(value="/showZhFlow")
	public void showZhFlow(@RequestParam(value="zhContractId")String conId,HttpServletResponse response){
		ShowFlowBean sf = new ShowFlowBean();
		String managerName = "";
		String flowCheck1Name = "";
		String flowCheck2Name = "";
		List<TbConZh> where = TbConZhDao.where(" CON_ZH_ID='"+conId+"' ");
		if(where != null && where.size()>0){
			TbConZh zh = where.get(0);
			sf.setConId(zh.getCon_zh_id());
			sf.setUserName(zh.getUser_name());
			List<TbConFlow> where2 = TbConFlowDao.where(" CON_ID='"+conId+"' order by CON_FLOW_ID desc");
			if(where2 != null && where2.size() >0){
			TbConFlow tbConFlow = where2.get(0);
			String manager = tbConFlow.getManager();
			String flowCheck1 = tbConFlow.getFlow_seal();
			String flowCheck2 = tbConFlow.getFlow_check();
			if(manager.contains("-")){
				manager = manager.substring(manager.indexOf("-")+1,manager.indexOf("-")+9);
				managerName = this.tbConUserSubDao.findUserNameById(manager);
			}
			
			if(flowCheck1.contains("-")){
				flowCheck1 = flowCheck1.substring(flowCheck1.indexOf("-")+1,flowCheck1.indexOf("-")+9);
				flowCheck1Name = this.tbConUserSubDao.findUserNameById(flowCheck1);
			}
			
			if(flowCheck2.contains("-")){
				flowCheck2 = flowCheck2.substring(flowCheck2.indexOf("-")+1,flowCheck2.indexOf("-")+9);
				flowCheck2Name = this.tbConUserSubDao.findUserNameById(flowCheck2);
				
			}
			int i = zh.getCon_state();
			sf.setCon_state(i);
			if(i==1){
				sf.setManageCheck(managerName);
				sf.setFlowCheck("");
				sf.setFlowCheck2("");
			}
			if(i==2){
				sf.setManageCheck(managerName);
				sf.setFlowCheck(flowCheck1Name);
				sf.setFlowCheck2("");
			}
			if(i==3){
				sf.setManageCheck(managerName);
				sf.setFlowCheck(flowCheck1Name);
				sf.setFlowCheck2(flowCheck2Name);
			}
			if(i==4){
				sf.setManageCheck(managerName);
				sf.setFlowCheck(flowCheck1Name);
				sf.setFlowCheck2(flowCheck2Name);
				sf.setReject(this.tbConDetailSubDao.findConRejectName(conId));
			}
			}
		}
		JSONObject object = JSONObject.fromObject(sf);
		try {
			response.getWriter().write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/showCtFlow")
	public void showCtFlow(@RequestParam(value="ctContractId")String conId,HttpServletResponse response){
		ShowFlowBean sf = new ShowFlowBean();
		String managerName = "";
		String flowCheck1Name = "";
		String flowCheck2Name = "";
		List<TbConCt> where = TbConCtDao.where(" CON_CT_ID='"+conId+"' ");
		if(where != null && where.size()>0){
			TbConCt ct = where.get(0);
			sf.setConId(ct.getCon_ct_id());
			sf.setUserName(ct.getUser_name());
			List<TbConFlow> where2 = TbConFlowDao.where(" CON_ID='"+conId+"' order by CON_FLOW_ID desc");
			if(where2 != null && where2.size() >0){
			TbConFlow tbConFlow = where2.get(0);
			String manager = tbConFlow.getManager();
			String flowCheck1 = tbConFlow.getFlow_seal();
			String flowCheck2 = tbConFlow.getFlow_check();
			if(manager.contains("-")){
				manager = manager.substring(manager.indexOf("-")+1,manager.indexOf("-")+9);
				managerName = this.tbConUserSubDao.findUserNameById(manager);
			}
			
			if(flowCheck1.contains("-")){
				flowCheck1 = flowCheck1.substring(flowCheck1.indexOf("-")+1,flowCheck1.indexOf("-")+9);
				flowCheck1Name = this.tbConUserSubDao.findUserNameById(flowCheck1);
			}
			
			if(flowCheck2.contains("-")){
				flowCheck2 = flowCheck2.substring(flowCheck2.indexOf("-")+1,flowCheck2.indexOf("-")+9);
				flowCheck2Name = this.tbConUserSubDao.findUserNameById(flowCheck2);
				
			}
			int i = ct.getCon_state();
			sf.setCon_state(i);
			if(i==1){
				sf.setManageCheck(managerName);
				sf.setFlowCheck("");
				sf.setFlowCheck2("");
			}
			if(i==2){
				sf.setManageCheck(managerName);
				sf.setFlowCheck(flowCheck1Name);
				sf.setFlowCheck2("");
			}
			if(i==3){
				sf.setManageCheck(managerName);
				sf.setFlowCheck(flowCheck1Name);
				sf.setFlowCheck2(flowCheck2Name);
			}
			if(i==4){
				sf.setManageCheck(managerName);
				sf.setFlowCheck(flowCheck1Name);
				sf.setFlowCheck2(flowCheck2Name);
				sf.setReject(this.tbConDetailSubDao.findConRejectName(conId));
			}
			}
		}
		JSONObject object = JSONObject.fromObject(sf);
		try {
			response.getWriter().write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/showCzFlow")
	public void showCzFlow(@RequestParam(value="czContractId")String conId,HttpServletResponse response){
		ShowFlowBean sf = new ShowFlowBean();
		String managerName = "";
		String flowCheck1Name = "";
		String flowCheck2Name = "";
		List<TbConCz> where = TbConCzDao.where(" CON_CZ_ID='"+conId+"' ");
		if(where != null && where.size()>0){
			TbConCz cz = where.get(0);
			sf.setConId(cz.getCon_cz_id());
			sf.setUserName(cz.getUser_name());
			List<TbConFlow> where2 = TbConFlowDao.where(" CON_ID='"+conId+"' order by CON_FLOW_ID desc");
			if(where2 != null && where2.size() >0){
			TbConFlow tbConFlow = where2.get(0);
			String manager = tbConFlow.getManager();
			String flowCheck1 = tbConFlow.getFlow_seal();
			String flowCheck2 = tbConFlow.getFlow_check();
			if(manager.contains("-")){
				manager = manager.substring(manager.indexOf("-")+1,manager.indexOf("-")+9);
				managerName = this.tbConUserSubDao.findUserNameById(manager);
			}
			
			if(flowCheck1.contains("-")){
				flowCheck1 = flowCheck1.substring(flowCheck1.indexOf("-")+1,flowCheck1.indexOf("-")+9);
				flowCheck1Name = this.tbConUserSubDao.findUserNameById(flowCheck1);
			}
			
			if(flowCheck2.contains("-")){
				flowCheck2 = flowCheck2.substring(flowCheck2.indexOf("-")+1,flowCheck2.indexOf("-")+9);
				flowCheck2Name = this.tbConUserSubDao.findUserNameById(flowCheck2);
				
			}
			int i = cz.getCon_state();
			sf.setCon_state(i);
			if(i==1){
				sf.setManageCheck(managerName);
				sf.setFlowCheck("");
				sf.setFlowCheck2("");
			}
			if(i==2){
				sf.setManageCheck(managerName);
				sf.setFlowCheck(flowCheck1Name);
				sf.setFlowCheck2("");
			}
			if(i==3){
				sf.setManageCheck(managerName);
				sf.setFlowCheck(flowCheck1Name);
				sf.setFlowCheck2(flowCheck2Name);
			}
			if(i==4){
				sf.setManageCheck(managerName);
				sf.setFlowCheck(flowCheck1Name);
				sf.setFlowCheck2(flowCheck2Name);
				sf.setReject(this.tbConDetailSubDao.findConRejectName(conId));
				sf.setContent(tbConFlow.getCon_msg());
			}
			}
		}
		JSONObject object = JSONObject.fromObject(sf);
		try {
			response.getWriter().write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/viewCtContract")
	public String viewCtContract(@RequestParam("conId")String conId,HttpSession session,Model model){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if(user == null || user.getUsercode() == null){
			return "redirect:/login";
		}
		TbConCt ct = null;
		List<TbConCt> where = TbConCtDao.where(" CON_CT_ID='"+conId+"' ");
		if(where!=null && where.size()>0){
			ct = where.get(0);
			int series = Integer.parseInt(ct.getCus_seriers());
			String seriesName = "全系";
			if(series!=-1){
				seriesName = this.brService.getNameByCatalogid(series);
			}else if(series==-2){
				seriesName = "";
			}
			ct.setSeriers_name(seriesName);
		}
		//double totalPrice = ct.getCon_total_price();
		//String upperCase = NumToRMBUtil.changeToBig(totalPrice);
		//ct.setCon_total_priceUpperCase(upperCase);
		int billCount = TbConBillDao.whereCount(" CON_ID='"+conId+"' and BILL_STATE <> '5' and bill_state<>3 ");
		int rebateCount = TbConRebateDao.whereCount(" CON_ID='"+conId+"' and REBATE_STATE <> '6'");
		int accCount = TbConAccountDao.whereCount("CON_ID='"+conId+"' ");
		model.addAttribute("billCount", billCount);
		model.addAttribute("rebateCount", rebateCount);
		model.addAttribute("accCount", accCount);
		model.addAttribute("ctContract", ct);
		return "viewContract/viewCtContract";
	}
	
	@RequestMapping(value="/viewZhContract")
	public String viewZhContract(@RequestParam("conId")String conId,HttpSession session,Model model){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if(user == null || user.getUsercode() == null){
			return "redirect:/login";
		}
		TbConZh zh = null;
		List<TbConZh> where = TbConZhDao.where(" CON_ZH_ID='"+conId+"' ");
		if(where!=null && where.size()>0){
			zh = where.get(0);
		}
		List<TbConZhSub> where2 = TbConZhSubDao.where(" CON_ZH_ID='"+conId+"' ");
		if(where2!=null && where2.size()>0){
			zh.setTbConZhSubs(where2);
		}
		double totalPrice = zh.getCon_total_price();
		String upperCase = NumToRMBUtil.changeToBig(totalPrice);
		zh.setCon_total_priceUpperCase(upperCase);
		int billCount = TbConBillDao.whereCount(" CON_ID='"+conId+"' and BILL_STATE <> '5' and bill_state<>3 ");
		int rebateCount = TbConRebateDao.whereCount(" CON_ID='"+conId+"' and REBATE_STATE <> '6'");
		int accCount = TbConAccountDao.whereCount("CON_ID='"+conId+"' ");
		model.addAttribute("billCount", billCount);
		model.addAttribute("rebateCount", rebateCount);
		model.addAttribute("accCount", accCount);
		model.addAttribute("zhContract", zh);
		return "viewContract/viewZhContract";
	}
	
	@RequestMapping(value="/viewCzContract")
	public String viewCzContract(@RequestParam("conId")String conId,HttpSession session,Model model){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		if(user == null || user.getUsercode() == null){
			return "redirect:/login";
		}
		TbConCz cz = null;
		List<TbConZhSub> where2 = TbConZhSubDao.where(" CON_ZH_ID='"+conId+"' ");
		List<TbConCz> where = TbConCzDao.where(" CON_CZ_ID='"+conId+"' ");
		if(where!=null && where.size()>0){
			cz = where.get(0);
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
		double totalPrice = cz.getCon_total_price();
		String upperCase = NumToRMBUtil.changeToBig(totalPrice);
		cz.setCon_total_priceUpperCase(upperCase);
		int billCount = TbConBillDao.whereCount(" CON_ID='"+conId+"' and BILL_STATE <> '5' and bill_state<>3 ");
		int rebateCount = TbConRebateDao.whereCount(" CON_ID='"+conId+"' and REBATE_STATE <> '6'");
		int accCount = TbConAccountDao.whereCount("CON_ID='"+conId+"' ");
		model.addAttribute("billCount", billCount);
		model.addAttribute("rebateCount", rebateCount);
		model.addAttribute("accCount", accCount);
		model.addAttribute("czContract", cz);
		return "viewContract/viewCzContract";
	}
}
