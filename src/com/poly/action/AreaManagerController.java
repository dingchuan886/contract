package com.poly.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import car_beans.TbConAddcon;
import car_beans.TbConBill;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConRebate;
import car_beans.TbConZh;
import car_beans.TbConZhSub;
import car_beans.TbFinOrg;
import car_beans.TbFinUser;
import car_beans.UserInfo;
import car_daos.TbConBillDao;
import car_daos.TbConRebateDao;
import car_daos.TbConZhSubDao;
import car_daos.TbFinOrgDao;
import car_daos.TbFinUserDao;

import com.poly.bean.PageCondition;
import com.poly.bean.PageResult;
import com.poly.services.BrandAndSeriesService;
import com.poly.services.ConSupplyService;
import com.poly.services.DateStatisticService;
import com.poly.services.DeptManagerService;
import com.poly.services.TbFinUserService;

@Controller
@RequestMapping("/areaManager")
public class AreaManagerController {
	private DeptManagerService deptManagerService = new DeptManagerService();
	private BrandAndSeriesService brService = new BrandAndSeriesService();
	private TbFinUserService finService = new TbFinUserService();
	private DateStatisticService statisticService = new DateStatisticService();
	private ConSupplyService supplyService = new ConSupplyService();
	@RequestMapping("/showCtareaManager")
	public String showCtDeptManager(@RequestParam(value="pageNum",defaultValue="1")int pageNum,HttpSession session,Model model,PageCondition pageCondition){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		TbFinUser finUser = finService.findUserByUserId(user.getUsercode());
		if(finUser.getOrg_code().equals("6")){
			finUser.setUser_dis("#2#");
		}
		String[] split = null;
		String dis_code = finUser.getUser_dis();
		if(dis_code!=null && StringUtils.isNotBlank(dis_code)){
			split = dis_code.split("#");
		}
		String disCode = "-1";
		if(split!=null && split.length>0){
			disCode += ",";
			for(int i=0;i<split.length;i++){
				if(i==split.length-1){
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
					}
				}else{
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
						disCode+=",";
					}
				}
			}
		}
		List<TbFinOrg> where = TbFinOrgDao.where(" DIS_ID IN ("+disCode+") ");
		String orgCode = finUser.getOrg_code();
		if(where!=null && where.size()>0){
			orgCode += ",";
			for(int i=0;i<where.size();i++){
				if(i==where.size()-1){
					orgCode+=where.get(i).getOrg_id();
				}else{
					orgCode+=where.get(i).getOrg_id();
					orgCode+=",";
				}
			}
		}
		
			sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where ORG_CODE IN ("+orgCode+")) ");
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
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and BUS_TYPE='"+pageCondition.getConType()+"' ");
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
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' ");
		int totalRecords = this.deptManagerService.findTotalAllRecords(sb.toString());
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		int startIndex = page.getStartIndex();
		int pageSize = page.getPageSize();
		Map<String, Double> allCtMoney = statisticService.getAllCtMoney(sb.toString());
		List<TbConCt> list = this.deptManagerService.findAllResult(sb.toString(),startIndex,pageSize);
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConCt conCt : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conCt.getCon_ct_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conCt.getCon_ct_id()+"' and ");
				Map<String,Object> map = new HashMap<String,Object>();
				int series = Integer.parseInt(conCt.getCus_seriers());
				TbConAddcon supplyByCt = supplyService.getConSupplyByCt(conCt.getCon_ct_id());
				String seriesName = "全系";
				if(series!=-1){
					seriesName = this.brService.getNameByCatalogid(series);
				}else if(series==-2){
					seriesName = "";
				}
				String orgName = this.finService.getOrgNameByUserId(conCt.getUser_id());
				String deptName = this.finService.getDeptNameByUserId(conCt.getUser_id());
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				conCt.setSeriers_name(seriesName);
				map.put("conCt", conCt);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				map.put("supCt",supplyByCt);
				pager.add(map);
			}
		}
		page.setList(pager);
		model.addAttribute("pageCondition", pageCondition);
		model.addAttribute("deptCtContract",page);
		model.addAttribute("allCtMoney", allCtMoney);
		return "areaManager/showAreaCtContract";
	}
	
	@RequestMapping("/showCzareaManager")
	public String showCzDeptManager(@RequestParam(value="pageNum",defaultValue="1")int pageNum,HttpSession session,Model model,PageCondition pageCondition){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		TbFinUser finUser = finService.findUserByUserId(user.getUsercode());
		if(finUser.getOrg_code().equals("6")){
			finUser.setUser_dis("#2#");
		}
		String[] split = null;
		String dis_code = finUser.getUser_dis();
		if(dis_code!=null && StringUtils.isNotBlank(dis_code)){
			split = dis_code.split("#");
		}
		String disCode = "-1";
		if(split!=null && split.length>0){
			disCode += ",";
			for(int i=0;i<split.length;i++){
				if(i==split.length-1){
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
					}
				}else{
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
						disCode+=",";
					}
				}
			}
		}
		List<TbFinOrg> where = TbFinOrgDao.where(" DIS_ID IN ("+disCode+") ");
		String orgCode = finUser.getOrg_code();
		if(where!=null && where.size()>0){
			orgCode += ",";
			for(int i=0;i<where.size();i++){
				if(i==where.size()-1){
					orgCode+=where.get(i).getOrg_id();
				}else{
					orgCode+=where.get(i).getOrg_id();
					orgCode+=",";
				}
			}
		}
		
		sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where ORG_CODE IN ("+orgCode+")) ");
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
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
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and BUS_TYPE='"+pageCondition.getConType()+"' ");
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
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' ");
		Map<String, Double> allCzMoney = statisticService.getAllCzMoney(sb.toString());
		int totalRecords = this.deptManagerService.findTotalCzRecords(sb.toString());
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		int startIndex = page.getStartIndex();
		int pageSize = page.getPageSize();
		List<TbConCz> list = this.deptManagerService.findAllCzResult(sb.toString(),startIndex,pageSize);
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConCz conCz : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conCz.getCon_cz_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conCz.getCon_cz_id()+"'");
				Map<String,Object> map = new HashMap<String,Object>();
				int series = Integer.parseInt(conCz.getCus_seriers());
				TbConAddcon supplyByCz = supplyService.getConSupplyByCz(conCz.getCon_cz_id());
				String seriesName = "全系";
				if(series!=-1){
					seriesName = this.brService.getNameByCatalogid(series);
				}else if(series==-2){
					seriesName = "";
				}
				String orgName = this.finService.getOrgNameByUserId(conCz.getUser_id());
				String deptName = this.finService.getDeptNameByUserId(conCz.getUser_id());
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				conCz.setSeriers_name(seriesName);
				map.put("conCz", conCz);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				map.put("supCz",supplyByCz);
				pager.add(map);
			}
		}
		page.setList(pager);
		model.addAttribute("pageCondition", pageCondition);
		model.addAttribute("deptCzContract",page);
		model.addAttribute("allCzMoney", allCzMoney);
		return "areaManager/showAreaCzContract";
	}
	
	@RequestMapping("/showZhareaManager")
	public String showZhDeptManager(@RequestParam(value="pageNum",defaultValue="1")int pageNum,HttpSession session,Model model,PageCondition pageCondition){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		TbFinUser finUser = finService.findUserByUserId(user.getUsercode());
		if(finUser.getOrg_code().equals("6")){
			finUser.setUser_dis("#2#");
		}
		String[] split = null;
		String dis_code = finUser.getUser_dis();
		if(dis_code!=null && StringUtils.isNotBlank(dis_code)){
			split = dis_code.split("#");
		}
		String disCode = "-1";
		if(split!=null && split.length>0){
			disCode += ",";
			for(int i=0;i<split.length;i++){
				if(i==split.length-1){
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
					}
				}else{
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
						disCode+=",";
					}
				}
			}
		}
		List<TbFinOrg> where = TbFinOrgDao.where(" DIS_ID IN ("+disCode+") ");
		String orgCode = finUser.getOrg_code();
		if(where!=null && where.size()>0){
			orgCode += ",";
			for(int i=0;i<where.size();i++){
				if(i==where.size()-1){
					orgCode+=where.get(i).getOrg_id();
				}else{
					orgCode+=where.get(i).getOrg_id();
					orgCode+=",";
				}
			}
		}
		
		sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where ORG_CODE IN ("+orgCode+")) ");
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and BUS_TYPE='"+pageCondition.getConType()+"' ");
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
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' ");
		Map<String, Double> allZhMoney = statisticService.getAllZhMoney(sb.toString());
		int totalRecords = this.deptManagerService.findTotalZhRecords(sb.toString());
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		int startIndex = page.getStartIndex();
		int pageSize = page.getPageSize();
		List<TbConZh> list = this.deptManagerService.findAllZhResult(sb.toString(),startIndex,pageSize);
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConZh conZh : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conZh.getCon_zh_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conZh.getCon_zh_id()+"'");
				List<TbConZhSub> conZhSubs = TbConZhSubDao.where(" CON_ZH_ID='"+conZh.getCon_zh_id()+"' ");
				List<TbConAddcon> addCons = new ArrayList<TbConAddcon>();
				if(conZhSubs!=null && conZhSubs.size()>0){
					for (TbConZhSub tbConZhSub : conZhSubs) {
						TbConAddcon conSupplyByZh = supplyService.getConSupplyByZh(conZh.getCon_zh_id(), tbConZhSub.getCon_zh_sub_id());
						addCons.add(conSupplyByZh);
					}
				}
				Map<String,Object> map = new HashMap<String,Object>();
				String orgName = this.finService.getOrgNameByUserId(conZh.getUser_id());
				String deptName = this.finService.getDeptNameByUserId(conZh.getUser_id());
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conZh", conZh);
				map.put("conBills", tbConBills);
				map.put("conZhSub", conZhSubs);
				map.put("conRebates", tbConRebate);
				map.put("supZh", addCons);
				pager.add(map);
			}
		}
			page.setList(pager);
		model.addAttribute("pageCondition", pageCondition);
		model.addAttribute("deptZhContract",page);
		model.addAttribute("allZhMoney", allZhMoney);
		return "areaManager/showAreaZhContract";
	}
}
