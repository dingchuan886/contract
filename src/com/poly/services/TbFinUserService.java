package com.poly.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import car_beans.TbConBillFlow;
import car_beans.TbConRebate;
import car_beans.TbFinOrg;
import car_beans.TbFinUser;
import car_daos.TbConBillFlowDao;
import car_daos.TbConRebateDao;
import car_daos.TbFinOrgDao;
import car_daos.TbFinUserDao;

import com.poly.bean.PageResult;
import com.poly.dao.subDaos.TbFinUserDaoEnhance;

public class TbFinUserService {
	private TbFinUserDaoEnhance tbfinuserDao = new TbFinUserDaoEnhance();
	/**
	 * 根据用户ID获取签名URL;
	 */
	public String getUrlByUserId(String id) {
	 TbFinUser user = findUserByUserId(id);
	 String url ="http://oa.chetuan.com/";
	 try {
		url = url+user.getWarter_sign();
	} catch (Exception e) {
		return "";
	}
	return url;
	}
	
	/** 
	 * 根据审核部门的字段获取签名URL
	 * @return url
	 */
	public String getUrlByMsg(String msg) {
		String id ="";
		if(msg!=null){
			if(msg.contains("-")){
				id = msg.substring(msg.indexOf("-")+1,msg.indexOf("-")+9);
			}else{
				return "";
			}
			 TbFinUser user = findUserByUserId(id);
			 String url ="http://oa.chetuan.com/";
			 try {
				url = url+user.getWarter_sign();
			} catch (Exception e) {
				return "";
			}
		return url;
		}else{
			return "";
		}
	}

	 

	public List<TbFinUser> findUser() {
		return TbFinUserDao.find();
	}

	public TbFinUser findUserByUserId(String userId) {
 
		List<TbFinUser> where = TbFinUserDao.where(" USER_CODE='" + userId + "' and ISONJOB='0' ");
		if (where.size() > 0) {
			return where.get(0);
		}
		return null;
	}

	public String findManagerCheck(String org_code,String dept_code) {
		List<TbFinUser> where = TbFinUserDao.where(" ORG_CODE='" + org_code + "' AND USER_ROLE='2' and DEPT_CODE='"+dept_code+"' and ISONJOB='0' ");
		StringBuilder sb = new StringBuilder();
		if (where != null && where.size() > 0) {
			for (TbFinUser tbFinUser : where) {
				sb.append("+");
				sb.append(tbFinUser.getUser_code());
				sb.append("/");
			}
		}
		return sb.toString();

	}
	
	public String findManagerCheck(String org_code) {
		List<TbFinUser> where = TbFinUserDao.where(" (ORG_CODE='" + org_code + "' AND USER_ROLE='4' AND DEPT_CODE='1' and ISONJOB='0') or (USER_CITY like '%#"+org_code+"#%' and ISONJOB='0') ");
		StringBuilder sb = new StringBuilder();
		if (where != null && where.size() > 0) {
			for (TbFinUser tbFinUser : where) {
				sb.append("+");
				sb.append(tbFinUser.getUser_code());
				sb.append("/");
			}
		}
		return sb.toString();

	}
//流程部查询（后期需要修改流程部的deptcode）
	public String findFlowCheck() {
		//TODO 修改dept_code
		List<TbFinUser> where = TbFinUserDao.where(" (USER_CODE='Poly0085') or (DEPT_CODE='18') and ISONJOB='0'");
		StringBuilder sb = new StringBuilder();
		if (where != null && where.size() > 0) {
			for (TbFinUser tbFinUser : where) {
				sb.append("+");
				sb.append(tbFinUser.getUser_code());
				sb.append("/");
			}
		}
		return sb.toString();
	}
	//财务部查询
	public String findFinCheck(){
		List<TbFinUser> where = TbFinUserDao.where(" (USER_CODE='Poly0064' or USER_CODE='Poly0218' or USER_CODE='Poly0589'"
				+ " or USER_CODE='Poly0590') and ISONJOB='0'");
		StringBuilder sb = new StringBuilder();
		if (where != null && where.size() > 0) {
			for (TbFinUser tbFinUser : where) {
				sb.append("+");
				sb.append(tbFinUser.getUser_code());
				sb.append("/");
			}
		}
		return sb.toString();
	}
	//区域经理审核
	public String findAreaCheck(String org_code){
		//根据org找到所属的区域id
		StringBuilder sb = new StringBuilder();
		List<TbFinOrg> where = TbFinOrgDao.where(" ORG_ID='"+org_code+"'");
		if(where!=null && where.size()>0){
			String disId = where.get(0).getDis_id();
			List<TbFinUser> where2 = TbFinUserDao.where(" USER_DIS like '%#"+disId+"#%' and ISONJOB='0'");
			if(where2!=null && where2.size()>0){
				for(TbFinUser user : where2){
					sb.append("+");
					sb.append(user.getUser_code());
					sb.append("/");
				}
			}
		}
		return sb.toString();
	}
	
	public String findHManagerCheck(){
		
		List<TbFinUser> where = TbFinUserDao.where(" USER_ROLE='1'");
		StringBuilder sb = new StringBuilder();
		if (where != null && where.size() > 0) {
			for (TbFinUser tbFinUser : where) {
				sb.append("+");
				sb.append(tbFinUser.getUser_code());
				sb.append("/");
			}
		}
		return sb.toString();
	}
	
	public String getOrgNameByConid(String conId){
		if(conId.startsWith("SHZH")){
			String orgName = this.tbfinuserDao.getOrgNameByZhid(conId);
			return orgName;
			
		}else if(conId.startsWith("SHCZ")){
			String orgName = this.tbfinuserDao.getOrgNameByCzid(conId);
			return orgName;
		}else if(conId.startsWith("SHCT")){
			String orgName = this.tbfinuserDao.getOrgNameByCtid(conId);
			return orgName;
		}
		
		return "";
	}

	public String getOrgNameByUserId(String advUserId) {
		return this.tbfinuserDao.getOrgNameByUserId(advUserId);
		
	}

	public String getUserNameByUserId(String userId) {
		TbFinUser user = findUserByUserId(userId);
		if(user!=null){
			return user.getUser_name();
		}
		return "";
	}

	public String getDeptNameByUserId(String userId) {
		return this.tbfinuserDao.getDeptNameByUserId(userId);
		
	}
	
	public List<String> getUserPhoneByUserIds(List<String> userIds){
		List<String> list = new ArrayList<String>();
		for(String userid : userIds){
			TbFinUser user = this.findUserByUserId(userid);
			if(user!=null){
				list.add(user.getTelephone());
			}
		}
		return list;
	}

	public PageResult<Map<String, Object>> showRebateByWhere(int pageNum,
			int totalRecords, String str) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum, totalRecords);
		List<TbConRebate> where = TbConRebateDao.where(str+" limit "+page.getStartIndex()+","+page.getPageSize());
		if(where!=null && where.size()>0){
			for (TbConRebate rebate : where) {
				TbFinUser user = TbFinUserDao.where(
						"user_code = '" + rebate.getUser_id() + "'").get(0);
				TbFinOrg org = TbFinOrgDao.where(
						"org_id='" + user.getOrg_code() + "'").get(0);
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("rebate", rebate);
				map.put("org", org);
				map.put("user", user);
				list.add(map);
			}
		}
		page.setList(list);
		return page;
	}
}
