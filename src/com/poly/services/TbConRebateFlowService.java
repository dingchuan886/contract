package com.poly.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import car_beans.TbConCheckDetail;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConRebate;
import car_beans.TbConRebateFlow;
import car_beans.TbConZh;
import car_beans.TbFinCheckInfo;
import car_beans.TbFinOrg;
import car_beans.TbFinUser;
import car_beans.UserInfo;
import car_daos.DBConnect;
import car_daos.TbConCheckDetailDao;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConRebateDao;
import car_daos.TbConRebateFlowDao;
import car_daos.TbConZhDao;
import car_daos.TbFinCheckInfoDao;
import car_daos.TbFinOrgDao;
import car_daos.TbFinUserDao;

import com.poly.bean.PageResult;
import com.poly.utils.MyConfig;
import com.poly.utils.NumToRMBUtil;


public class TbConRebateFlowService {
	
	private TbFinUserService tbFinUserService = new TbFinUserService();
	
	/**
	 * 保存返利申请 同时生成返利流程
	 * @param conRebate
	 * @param user
	 * @return
	 */
	public int saveTbConRebate(TbConRebate conRebate, UserInfo user){
		DBConnect dbc = null;
		int result_flow = -1;
		try {
			dbc = new DBConnect();
			dbc.setAutoCommit(false);
			
			TbConRebateFlow conRebateFlow = new TbConRebateFlow();
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
			//获取严总的user_code
			String hQManager = "+Poly0001/";
			if(!"".equals(managerCheck)){
				conRebateFlow.setManager(managerCheck);
			}
			if(!"".equals(areaCheck)){
				conRebateFlow.setArea(areaCheck);
			}
			if(!"".equals(flowCheck)){
				conRebateFlow.setFlow_check(flowCheck);
			}
			if(!"".equals(hQManager)){
				conRebateFlow.setHqmanager(hQManager);
			}
			//设置下一个审核人
			if(!managerCheck.equals("")){
				conRebate.setRebate_state(0);
				conRebateFlow.setNextcheck(managerCheck);
				conRebateFlow.setRebate_state(0);
			}else{
				if(!areaCheck.equals("")){
					conRebate.setRebate_state(1);
					conRebateFlow.setNextcheck(areaCheck);
					conRebateFlow.setRebate_state(1);
				}else{
					if(!flowCheck.equals("")){
						conRebate.setRebate_state(7);
						conRebateFlow.setNextcheck(areaCheck);
						conRebateFlow.setRebate_state(7);
					}
				}
			}
			
			int result = TbConRebateDao.save(dbc, conRebate);
			if(result==1){
				PreparedStatement pstmt = dbc.getPreparedStatement();
				ResultSet rs = pstmt.getGeneratedKeys();
				int primaryKey = -1;
				while(rs.next()){
					primaryKey = rs.getInt(1);
				}
				
				conRebateFlow.setRebate_id(primaryKey);
				conRebate.setBack_id(primaryKey);
				int i = saveRebateToFinance(dbc,conRebate,user);
				
				result_flow = TbConRebateFlowDao.save(dbc, conRebateFlow);
				if(result_flow!=-1&&i!=-1){
					dbc.commit();
				}else{
					dbc.rollback();
				}
				String conId = conRebate.getCon_id();
				if(conId!=null && !conId.trim().equals("")){
					if(conId.startsWith("SHCT")){
						TbConCt tbConCt = TbConCtService.getConCtById(conId);
						int reCount = tbConCt.getRe_count();
						reCount --;
						TbConCt conCt = new TbConCt();
						conCt.setCon_ct_id(tbConCt.getCon_ct_id());
						conCt.setRe_count(reCount);
						result = TbConCtDao.update(dbc, conCt);
					}else if(conId.startsWith("SHCZ")){
						TbConCz tbConCz = TbConCzService.getConCzById(conId);
						int reCount = tbConCz.getRe_count();
						reCount --;
						TbConCz conCz = new TbConCz();
						conCz.setCon_cz_id(tbConCz.getCon_cz_id());
						conCz.setRe_count(reCount);
						result = TbConCzDao.update(dbc, conCz);
					}else if(conId.startsWith("SHZH")){
						TbConZh tbConZh = TbConZhService.getConZhById(conId);
						int reCount = tbConZh.getRe_count();
						reCount --;
						TbConZh conZh = new TbConZh();
						conZh.setCon_zh_id(tbConZh.getCon_zh_id());
						conZh.setRe_count(reCount);
						result = TbConZhDao.update(dbc, conZh);
					}
					dbc.commit();
				}else{
					result = -1;
				}
				if(result==-1){
					dbc.rollback();
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(dbc!=null){
					dbc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result_flow;
	}
	
	private int saveRebateToFinance(DBConnect dbc, TbConRebate conRebate,UserInfo nowuser) {
		TbFinCheckInfo checkinfo = new TbFinCheckInfo();
		checkinfo.setMoney(conRebate.getThis_back());
		checkinfo.setMoneyupcase(NumToRMBUtil.changeToBig(checkinfo.getMoney()));
		String con_id = conRebate.getCon_id();
		
		String note = "来自"+nowuser.getUsername()+"的";
		if(con_id.startsWith("SHCT")){
			note += "车团合同暂支申请";
			checkinfo.setCompany_code("CT");
		}else if(con_id.startsWith("SHCZ")){
			note += "车展合同暂支申请";
			checkinfo.setCompany_code("ZZ");
		}else if(con_id.startsWith("SHZH")){
			note += "广告合同暂支申请";
			checkinfo.setCompany_code("ZZ");
		}
		
		String content = "<p style='color:red'>请登录合同系统之后再点下面的链接查看</p>合同编号:"+con_id+"<br/>";
		if(con_id.startsWith("SHCT")){
			content += "<a href='"+MyConfig.contract_ct_url+"contract/ctContract/viewCtContract?conId="+con_id+"'>"
					+ "查看合同</a><br/>";
		}else if(con_id.startsWith("SHCZ")){
			content += "<a href='"+MyConfig.contract_ct_url+"contract/czContract/viewCzContract?conId="+con_id+"'>"
					+ "查看合同</a><br/>";
		}else if(con_id.startsWith("SHZH")){
			content += "<a href='"+MyConfig.contract_ct_url+"contract/zhContract/viewZhContract?conId="+con_id+"'>"
					+ "查看合同</a><br/>";
		}
		content += "<a href='"+MyConfig.contract_ct_url+"contract/tbConRebate/showRebate?rebateId="+conRebate.getBack_id()+"'>"
				+ "查看详情</a><br/>";
		checkinfo.setPayment_type(1);
		checkinfo.setUser_code(nowuser.getUsercode());
		checkinfo.setAcount(conRebate.getAcount());
		checkinfo.setBank_name(conRebate.getBank_name());
		checkinfo.setPayee_name(conRebate.getPayee_name());
		checkinfo.setContent(content);
		checkinfo.setNote(note);
		checkinfo.setType("合同暂支申请");
		
		String orgcode = nowuser.getOrgcode();
		//String rolecode = nowuser.getRolecode();
		String deptcode = nowuser.getDeptcode();
		String usercode = "+"+nowuser.getUsercode()+"/";
		String leadercode = editUser(getLeaderCode(orgcode,deptcode));//部门负责人
		String financecode = editUser(getFinanceCode(orgcode));//财务
		/*
		 * 查询分站:查询分站时,增加模糊查询User_City字段(一个分站有多个站长时)
		 */
		String managercode = editUser(getManagerCodeByCity(orgcode));
		
		/*
		 * 查询区域:根据分站ID查询
		 */
		String discode = editUser(getDisCode(orgcode));
		
		String hqfinancecode = editUser(getFinanceCode("0"));//总公司财务
		String hqmanagercode = editUser(getHQManagerCode());//总经理
		String tellercode = editUser(getTellerCode());//出纳
		
		
		//总站 1
		if(("0").equals(orgcode)){
			if(usercode.equals(hqmanagercode)){
				checkinfo.setHq_manager(hqmanagercode.replace("+", "-"));
				checkinfo.setNextcheck(tellercode);
			}else {
				if(usercode.equals(managercode)){//增加总公司经理
					checkinfo.setManager(usercode);
					checkinfo.setNextcheck(financecode);
				}else{
					if(leadercode.indexOf(usercode) == -1){
						checkinfo.setHandperson(usercode);
						checkinfo.setNextcheck(leadercode);
						checkinfo.setDepart_manager(leadercode);
					}else{
						/**
						 * 财务改成站长
						 */
						checkinfo.setNextcheck(financecode);
						//checkinfo.setNextcheck(ZHANGQUNGZHI);
						
//						if(deptcode.equals("11")){//张总特殊处理人事部
//							checkinfo.setNextcheck(hqmanagercode);
//						}
//						if("17".equals(deptcode)||"2".equals(deptcode)||"3".equals(deptcode)){//上海站这几个部门不是直属部门
//							checkinfo.setNextcheck(ZHANGQUNGZHI);
//							checkinfo.setDisCode(discode);
//						}
						checkinfo.setDepart_manager(usercode.replace("+", "-"));
					}
//					if(deptcode.equals("11")){//张总特殊处理人事部
//						checkinfo.setManager(hqmanagercode);
//					}
				}
				
//				if("17".equals(deptcode)||"2".equals(deptcode)||"3".equals(deptcode)){//上海站这几个部门不是直属部门
//					checkinfo.setManager(ZHANGQUNGZHI);
//					checkinfo.setDisCode(discode);
//				}
				
				checkinfo.setHq_finane(hqfinancecode);
				checkinfo.setHq_manager(hqmanagercode);
			}
			checkinfo.setTeller(tellercode);
		}else{
			if(usercode.equals(managercode)){
				checkinfo.setManager(managercode.replace("+", "-"));
				checkinfo.setNextcheck(discode);
			}else{
				if(leadercode.indexOf(usercode) == -1){
					checkinfo.setHandperson(usercode);
					checkinfo.setNextcheck(leadercode);
					checkinfo.setDepart_manager(leadercode);
				}else{
					checkinfo.setNextcheck(financecode);
					checkinfo.setDepart_manager(usercode.replace("+", "-"));
				}
				checkinfo.setFinane(financecode);
				checkinfo.setManager(managercode);
			}
			checkinfo.setHq_finane(hqfinancecode);
			checkinfo.setHq_manager(hqmanagercode);
			checkinfo.setTeller(tellercode);
			checkinfo.setDisCode(discode);
		}
		checkinfo.setAccessory("");
		checkinfo.setAdd_time(new Date());//添加时间
		checkinfo.setState(0);//状态
		checkinfo.setOrg_code(nowuser.getOrgcode());
		String evidencecode = getMaxCheck(checkinfo.getCompany_code(),nowuser.getOrgcode());
		evidencecode = this.getEvidencecode(nowuser.getOrgcode(),checkinfo.getCompany_code(),evidencecode);
		checkinfo.setEvidence_code(evidencecode);
		if(("").equals(checkinfo.getNextcheck()) || checkinfo.getNextcheck()==null){
			LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
			map.put("HANDPERSON", checkinfo.getHandperson());
			map.put("DEPART_MANAGER", checkinfo.getDepart_manager());
			map.put("FINANE", checkinfo.getFinane());
			map.put("MANAGER", checkinfo.getManager());
			map.put("discode", checkinfo.getDisCode());
			map.put("HQ_FINANE", checkinfo.getHq_finane());
			map.put("HQ_MANAGER", checkinfo.getHq_manager());
			map.put("TELLER", checkinfo.getTeller());
			boolean flg = false;
			for(String key:map.keySet()){
				System.out.println(key+"****"+map.get(key));
				if(flg && map.get(key)!=null && !"".equals(map.get(key))){
					checkinfo.setNextcheck(map.get(key));
					break;
				}
				if(!"".equals(map.get(key))&&map.get(key)!=null&&(map.get(key).replace("-", "+").indexOf(usercode)!=-1)){
					flg =true;
				}
				
			}
			
		}
		try {
			return TbFinCheckInfoDao.save(dbc, checkinfo);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	private String getMaxCheck(String companycode,String orgcode) {
		String result = "";
		DBConnect dbc = null;
		String sql = "SELECT  EVIDENCE_CODE as max FROM tb_fin_check_info where COMPANY_CODE = ? AND ORG_CODE=? ORDER BY  EVIDENCE_CODE desc LIMIT 0,1 ";
		try {
			dbc = new DBConnect(sql);
			dbc.setString(1, companycode);
			dbc.setString(2, orgcode);
			ResultSet rs = dbc.executeQuery();
			if (rs.next()) {
				result = rs.getString("max");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (dbc != null)
					dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private String getEvidencecode(String orgcode,String companycode,String evidencecode){
		String result = "";
		if(orgcode.length()==1){
			orgcode = "0"+orgcode;
		}
		if(evidencecode.equals("")){
			result = companycode+"-"+orgcode+"-00001";
		}else{
			int nextnum = Integer.parseInt(evidencecode.substring(evidencecode.length()-5, evidencecode.length()))+1;
			String next= nextnum+"";
			String zero = "0";
			String code = "";
			for(int i = 0;i<5-next.length();i++){
				code = code + zero;
			}
			result = companycode+"-"+orgcode +"-"+ code+next ;
		}
		return result;
	}
	
	//查找部门负责人
		public List<String> getLeaderCode(String orgcode,String deptcode) {
			List<String> result = new ArrayList<String>();
			//String result = "";
			DBConnect dbc = null;
			String sql = "SELECT  USER_CODE  FROM tb_fin_user WHERE ORG_CODE = ? AND DEPT_CODE = ? AND USER_ROLE = 2 AND ISONJOB <> 1";
			try {
				dbc = new DBConnect(sql);
				dbc.setString(1, orgcode);
				dbc.setString(2, deptcode);
				ResultSet rs = dbc.executeQuery();
				while(rs.next()) {
					result.add(rs.getString("USER_CODE"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (dbc != null)
						dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		//财务
		public List<String> getFinanceCode(String orgcode) {
			List<String> result = new ArrayList<String>();
			DBConnect dbc = null;
			String sql = "SELECT a.USER_CODE FROM tb_fin_user a INNER JOIN tb_fin_usertopri b ON a.USER_CODE = b.USER_ID "
					+ "WHERE 	a.ORG_CODE = ? AND b.PRI_CODE = 3 AND a.ISONJOB <> 1";
			try {
				dbc = new DBConnect(sql);
				dbc.setString(1, orgcode);
				ResultSet rs = dbc.executeQuery();
				while (rs.next()) {
					result.add(rs.getString("USER_CODE"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (dbc != null)
						dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		//分公司经理
		public List<String> getManagerCode(String orgCode) {
			List<String> result = new ArrayList<String>();
			DBConnect dbc = null;
			//2015-6-18 17:20:09 增加区域审核时更改此SQL 
			String sql = "SELECT  USER_CODE  FROM tb_fin_user WHERE ORG_CODE = ? AND USER_ROLE = 4 AND ISONJOB <> 1";
			try {
				dbc = new DBConnect(sql);
				dbc.setString(1, orgCode);
				ResultSet rs = dbc.executeQuery();
				while (rs.next()) {
					result.add(rs.getString("USER_CODE"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (dbc != null)
						dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		//分公司经理
		public List<String> getManagerCodeByCity(String orgCode) {
			List<String> result = new ArrayList<String>();
			DBConnect dbc = null;
			/*2015-6-18 17:20:09 增加区域审核时更改此SQL 
			String sql = "SELECT  USER_CODE  FROM tb_fin_user WHERE ORG_CODE = ? AND USER_ROLE = 4 AND ISONJOB <> 1";*/
			String sql ="SELECT  *  FROM tb_fin_user WHERE (ORG_CODE =? AND USER_ROLE = 4) or USER_CITY like '%#"+orgCode+"#%' AND ISONJOB <> 1";
			try {
				dbc = new DBConnect(sql);
				dbc.setString(1, orgCode);
				ResultSet rs = dbc.executeQuery();
				while (rs.next()) {
					result.add(rs.getString("USER_CODE"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (dbc != null)
						dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		//区域领导
		public List<String> getDisCode(String orgCode) {
			List<String> result = new ArrayList<String>();
			DBConnect dbc = null;
			/*2015-6-18 17:20:09 增加区域审核时更改此SQL 
			String sql = "SELECT  USER_CODE  FROM tb_fin_user WHERE ORG_CODE = ? AND USER_ROLE = 4 AND ISONJOB <> 1";*/
			//查询区域ID
			String selDisSQL = "select DIS_ID from tb_fin_org where org_id="+orgCode;
			try {
				dbc = new DBConnect(selDisSQL);
				ResultSet rsDis = dbc.executeQuery();
				String disID = "";
				while (rsDis.next()) {
					disID=rsDis.getString("DIS_ID");
				}
				
				//根据部门ID查询区域领导
				String sql ="SELECT  USER_CODE  FROM tb_fin_user WHERE USER_DIS like '%#"+disID+"#%' AND ISONJOB <> 1";
				dbc = new DBConnect(sql);
				ResultSet rs = dbc.executeQuery();
				while (rs.next()) {
					result.add(rs.getString("USER_CODE"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (dbc != null)
						dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		//总公司财务
		public List<String> getHQManagerCode() {
			List<String> result = new ArrayList<String>();
			DBConnect dbc = null;
			String sql = "SELECT  USER_CODE  FROM tb_fin_user WHERE  USER_ROLE = 1 AND ISONJOB <> 1";
			try {
				dbc = new DBConnect(sql);
				ResultSet rs = dbc.executeQuery();
				if (rs.next()) {
					result.add(rs.getString("USER_CODE"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (dbc != null)
						dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		
		public List<String> getTellerCode() {
			List<String> result = new ArrayList<String>();
			DBConnect dbc = null;
			String sql = "SELECT a.USER_CODE FROM tb_fin_user a INNER JOIN tb_fin_usertopri b ON a.USER_CODE = b.USER_ID "
					+ "WHERE b.PRI_CODE = 5 AND a.ISONJOB <> 1";
			try {
				dbc = new DBConnect(sql);
				ResultSet rs = dbc.executeQuery();
				while (rs.next()) {
					result .add(rs.getString("USER_CODE"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (dbc != null)
						dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
	
		public String editUser(List<String> list){
			String res = "";
			for(String key:list){
				res = res+"+"+key+"/";
			}
			return res;
		}
	/**
	 * 分页查询
	 * @param state
	 * @param nextCheck
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageResult<Map<String, Object>> getPager(int state, String nextCheck, int pageNum, int pageSize,String subsql){
		int startIndex = (pageNum - 1)*pageSize;
		StringBuffer whereSql = new StringBuffer();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		whereSql.append("1=1 and rebate_state <> 4 ");
		if(state>=0){
			whereSql.append(" and rebate_state = " + state);
		}
		if(nextCheck!=null){
			whereSql.append(" and nextcheck like '%+" + nextCheck + "/%' ");
		}
		if(subsql!=null){
			whereSql.append(subsql);
		}
		whereSql.append(" order by REBATE_ID DESC ");
		/*
		if(nextCheck==null){
			whereSql.append(" and nextcheck is null ");
		}else if(nextCheck.equals("")){
			whereSql.append(" and nextcheck like '" + nextCheck + "' ");
		}else{
			whereSql.append(" and nextcheck like '%+" + nextCheck + "/%' ");
		}
		*/
		int totalRecords = TbConRebateFlowDao.whereCount(whereSql.toString());
		PageResult<Map<String, Object>> pager = new PageResult<Map<String,Object>>(pageNum, totalRecords);
		pager.setPageSize(pageSize);
		whereSql.append(" limit " + startIndex + ", " + pageSize + " ");
		List<TbConRebateFlow> conRebateFlows = TbConRebateFlowDao.where(whereSql.toString());
		if(conRebateFlows!=null && conRebateFlows.size()>0){
			for(TbConRebateFlow conRebateFlow : conRebateFlows){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("conRebateFlow", conRebateFlow);
				int rebateId = conRebateFlow.getRebate_id();
				List<TbConRebate> conRebates = TbConRebateDao.where("BACK_ID = '" + rebateId + "'");
				if(conRebates!=null && conRebates.size()>0){
					TbConRebate conRebate = conRebates.get(0);
					map.put("conRebate", conRebate);
					String conId = conRebate.getCon_id();
					if(conId.startsWith("SHCZ")){
						List<TbConCz> conCzs = TbConCzDao.where("CON_CZ_ID = '" + conId + "'");
						if(conCzs!=null && conCzs.size()>0){
							TbConCz conCz = conCzs.get(0);
							map.put("conCz", conCz);
							List<TbFinUser> users = TbFinUserDao.where("user_code = '" + conCz.getUser_id() + "'");
							if(users!=null && users.size()>0){
								TbFinUser user = users.get(0);
								map.put("user", user);
								List<TbFinOrg> orgs = TbFinOrgDao.where("org_id = '" + user.getOrg_code() + "'");
								if(orgs!=null && orgs.size()>0){
									TbFinOrg org = orgs.get(0);
									map.put("org", org);
								}
							}
						}
					}else if(conId.startsWith("SHZH")){
						List<TbConZh> conZhs = TbConZhDao.where("CON_ZH_ID = '" + conId + "'");
						if(conZhs!=null && conZhs.size()>0){
							TbConZh conZh = conZhs.get(0);
							map.put("conZh", conZh);
							List<TbFinUser> users = TbFinUserDao.where("user_code = '" + conZh.getUser_id() + "'");
							if(users!=null && users.size()>0){
								TbFinUser user = users.get(0);
								map.put("user", user);
								List<TbFinOrg> orgs = TbFinOrgDao.where("org_id = '" + user.getOrg_code() + "'");
								if(orgs!=null && orgs.size()>0){
									TbFinOrg org = orgs.get(0);
									map.put("org", org);
								}
							}
						}
					}else if(conId.startsWith("SHCT")){
						List<TbConCt> conCts = TbConCtDao.where("CON_CT_ID = '" + conId + "'");
						if(conCts!=null && conCts.size()>0){
							TbConCt conCt = conCts.get(0);
							map.put("conCt", conCt);
							List<TbFinUser> users = TbFinUserDao.where("user_code = '" + conCt.getUser_id() + "'");
							if(users!=null && users.size()>0){
								TbFinUser user = users.get(0);
								map.put("user", user);
								List<TbFinOrg> orgs = TbFinOrgDao.where("org_id = '" + user.getOrg_code() + "'");
								if(orgs!=null && orgs.size()>0){
									TbFinOrg org = orgs.get(0);
									map.put("org", org);
								}
							}
						}
					}
				}
				list.add(map);
			}
		}
		pager.setList(list);
		return pager;
	}
	
	/**
	 * 审核通过，或者驳回
	 * @param conRebateFlow
	 * @return
	 */
	public int checkOrReject(TbConRebateFlow conRebateFlow, String checkUser){
		int result = -1;
		int rebateId = conRebateFlow.getRebate_id();
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.setAutoCommit(false);
			result = TbConRebateFlowDao.update(dbc, conRebateFlow);
			if(result==-1){
				dbc.rollback();
				return result;
			}
			TbConRebate conRebate = new TbConRebate();
			conRebate.setBack_id(rebateId);
			conRebate.setRebate_state(conRebateFlow.getRebate_state());
			result = TbConRebateDao.update(dbc, conRebate);
			if(result==-1){
				dbc.rollback();
				return result;
			}
			TbConCheckDetail conCheckDetail = new TbConCheckDetail();
			conCheckDetail.setCheck_type(0);
			conCheckDetail.setCheck_user(checkUser);
			conCheckDetail.setCid("rebate-"+rebateId);
			conCheckDetail.setAdd_time(new Date());
			if(conRebateFlow.getRebate_state()==4){
				conCheckDetail.setContent(conRebateFlow.getRebate_msg());
				conCheckDetail.setCheck_type(1);
			}
			result = TbConCheckDetailDao.save(dbc, conCheckDetail);
			if(result==-1){
				dbc.rollback();
				return result;
			}
			dbc.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(dbc!=null){
					dbc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	/**
	 * 确认返利
	 * @param rebateId
	 * @param rebateTime
	 * @param rebateAccount
	 * @return
	 */
	public int confirmRebate(int rebateId, String conId, String rebateTime, double rebateAccount){
		int result = -1;
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.setAutoCommit(false);
			TbConRebate rebate = new TbConRebate();
			rebate.setBack_id(rebateId);
			rebate.setRebate_time(rebateTime);
			rebate.setBack_actual(rebateAccount);
			result = TbConRebateDao.update(dbc, rebate);
			if(result==-1){
				dbc.rollback();
				return result;
			}
			if(conId!=null && !conId.trim().equals("")){
				if(conId.startsWith("SHCT")){
					TbConCt tbConCt = TbConCtService.getConCtById(conId);
					double alRebate = tbConCt.getAl_rebate();
					alRebate += rebateAccount;
					TbConCt conCt = new TbConCt();
					conCt.setCon_ct_id(tbConCt.getCon_ct_id());
					conCt.setAl_rebate(alRebate);
					result = TbConCtDao.update(dbc, conCt);
				}else if(conId.startsWith("SHCZ")){
					TbConCz tbConCz = TbConCzService.getConCzById(conId);
					double alRebate = tbConCz.getAl_rebate();
					alRebate += rebateAccount;
					TbConCz conCz = new TbConCz();
					conCz.setCon_cz_id(tbConCz.getCon_cz_id());
					conCz.setAl_rebate(alRebate);
					result = TbConCzDao.update(dbc, conCz);
				}else if(conId.startsWith("SHZH")){
					TbConZh tbConZh = TbConZhService.getConZhById(conId);
					double alRebate = tbConZh.getAl_rebate();
					alRebate += rebateAccount;
					TbConZh conZh = new TbConZh();
					conZh.setCon_zh_id(tbConZh.getCon_zh_id());
					conZh.setAl_rebate(alRebate);
					result = TbConZhDao.update(dbc, conZh);
				}
				dbc.commit();
			}else{
				result = -1;
			}
			if(result==-1){
				dbc.rollback();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(dbc!=null){
					dbc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	/**
	 * 根据Id获取。
	 * @param id
	 * @return
	 */
	public TbConRebateFlow getRebateFlowById(int id){
		TbConRebateFlow rebateFlow = null;
		List<TbConRebateFlow> rebateFlows = TbConRebateFlowDao.where("REBATE_FLOW_ID = " + id);
		if(rebateFlows!=null && rebateFlows.size()>0){
			rebateFlow = rebateFlows.get(0);
		}
		return rebateFlow;
	}
	
	/**
	 * 根据id获取返利
	 * @param id
	 * @return
	 */
	public TbConRebate getRebateById(int id){
		TbConRebate rebate = null;
		List<TbConRebate> rebates = TbConRebateDao.where("BACK_ID = " + id);
		if(rebates!=null && rebates.size()>0){
			rebate = rebates.get(0);
		}
		return rebate;
	}
	
	/**
	 * 显示返利及相关。
	 * @param condId
	 * @param rebateId
	 * @return
	 */
	public Map<String, Object> showRebateInfo(int rebateId){
		Map<String, Object> map = new HashMap<String, Object>();
		TbConRebate rebate = this.getRebateById(rebateId);
		map.put("rebate", rebate);
		if(rebate!=null){
			String conId = rebate.getCon_id();
			map.put("conId", conId);
			TbFinUserService userService = new TbFinUserService();
			TbFinUser user = null;
			String userId = null;
			if(conId.startsWith("SHCT")){
				TbConCt conCt = TbConCtService.getConCtById(conId);
				map.put("conCt", conCt);
				userId = conCt.getUser_id();
			}else if(conId.startsWith("SHCZ")){
				TbConCz conCz = TbConCzService.getConCzById(conId);
				map.put("conCz", conCz);
				userId = conCz.getUser_id();
			}else if(conId.startsWith("SHZH")){
				TbConZh conZh = TbConZhService.getConZhById(conId);
				map.put("conZh", conZh);
				userId = conZh.getUser_id();
			}
			user = userService.findUserByUserId(userId);
			map.put("user", user);
		}
		return map;
	}
	
	/**
	 * userId is  null  则查看所有的返利申请驳回。
	 * @param state
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageResult<Map<String, Object>> lookRejectRebate(int state, String userId, int pageNum, int pageSize){
		int startIndex = (pageNum-1)*pageSize;
		PageResult<Map<String, Object>> pager = null;
		String sql = "REBATE_STATE = " + state + " ";
		if(userId!=null){
			sql += " and user_id ='" + userId + "' ";
		}
		sql += " order by back_id DESC ";
		int totalRecords = TbConRebateDao.whereCount(sql);
		pager = new PageResult<Map<String, Object>>(pageNum, totalRecords);
		sql += " limit " + startIndex + ", " + pageSize;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>> ();
		List<TbConRebate> rebates = TbConRebateDao.where(sql);
		for(TbConRebate conRebate : rebates){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rebate", conRebate);
			int rebateId = conRebate.getBack_id();
			TbConRebateFlow rebateFlow = this.getFlowByRebateId(rebateId);
			map.put("rebateFlow", rebateFlow);
			String uId = conRebate.getUser_id();
			List<TbFinUser> users = TbFinUserDao.where("user_code = '" + uId + "'");
			if(users!=null && users.size()>0){
				TbFinUser user = users.get(0);
				map.put("user", user);
				List<TbFinOrg> orgs = TbFinOrgDao.where("org_id = '" + user.getOrg_code() + "'");
				if(orgs!=null && orgs.size()>0){
					TbFinOrg org = orgs.get(0);
					map.put("org", org);
				}
			}
			list.add(map);
		}
		pager.setList(list);
		return pager;
	}
	
	public TbConRebateFlow getFlowByRebateId(int id){
		String sql = "REBATE_ID = " + id;
		List<TbConRebateFlow> list = TbConRebateFlowDao.where(sql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	/**
	 * 根据合同号查看返利单。
	 * @param conId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageResult<Map<String, Object>> getRebateByCon(String conId, int pageNum, int pageSize){
		int startIndex = (pageNum-1)*pageSize;
		String sql = "CON_ID = '" + conId + "' and REBATE_STATE <> 6";
		int totalRecords = TbConRebateDao.whereCount(sql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String, Object>>(pageNum, totalRecords);
		sql += " limit " + startIndex + ", " + pageSize;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<TbConRebate> conRebates = TbConRebateDao.where(sql);
		for(TbConRebate conRebate : conRebates){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rebate", conRebate);
			int rebateId = conRebate.getBack_id();
			TbConRebateFlow rebateFlow = this.getFlowByRebateId(rebateId);
			map.put("rebateFlow", rebateFlow);
			String userId = conRebate.getUser_id();
			List<TbFinUser> users = TbFinUserDao.where("user_code = '" + userId + "'");
			if(users!=null && users.size()>0){
				TbFinUser user = users.get(0);
				map.put("user", user);
				List<TbFinOrg> orgs = TbFinOrgDao.where("org_id = '" + user.getOrg_code() + "'");
				if(orgs!=null && orgs.size()>0){
					TbFinOrg org = orgs.get(0);
					map.put("org", org);
				}
			}
			list.add(map);
		}
		pager.setList(list);
		return pager;
	}
	
	
	
	
//	/**
//	 * 驳回
//	 * @param tbConFlow
//	 * @return
//	 */
//	public int rejected(TbConRebateFlow conRebateFlow, String checkUser){
//		int result = -1;
//		int rebateId = conRebateFlow.getRebate_id();
//		DBConnect dbc = null;
//		try {
//			dbc = new DBConnect();
//			dbc.setAutoCommit(false);
//			
//			dbc.commit();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
}
