package com.poly.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import car_beans.TbAttachUpload;
import car_beans.TbConCheckDetail;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConFlow;
import car_beans.TbConZh;
import car_beans.TbFinOrg;
import car_beans.TbFinUser;
import car_daos.DBConnect;
import car_daos.TbAttachUploadDao;
import car_daos.TbConCheckDetailDao;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConFlowDao;
import car_daos.TbConZhDao;
import car_daos.TbFinOrgDao;
import car_daos.TbFinUserDao;

import com.poly.bean.PageResult;

/**
 * 合同签订流程
 * @author 
 *
 */
public class TbConFlowService {
	private BrandAndSeriesService brService = new BrandAndSeriesService();
	/**
	 * 获取所有的流程合同
	 * @return
	 */
	public List<TbConFlow> findTbConFlows(){
		return TbConFlowDao.find();
	}
	
	/**
	 * 根据审核状态获取对应的合同流程
	 * @param CON_STATE
	 * @return
	 */
	public List<TbConFlow> findTbConFlowsByConState(Integer CON_STATE){
		List<TbConFlow> tbConFlows = TbConFlowDao.where("CON_STATE = " + CON_STATE);
		return tbConFlows;
	}
	/**
	 * 根据合同流程状态获取该状态的合同流程有多少个。
	 * @param CON_STATE
	 * @return
	 */
	public int findCountByState(Integer CON_STATE){
		int count = TbConFlowDao.whereCount("CON_STATE = " + CON_STATE);
		return count;
	}
	
	public List<TbConFlow> findTbFlowsByConTypeState(Integer CON_STATE, String conType){
		String whereSql = "CON_STATE = " + CON_STATE + " AND CON_ID LIKE '" + conType + "%'";
		List<TbConFlow> tbConFlows = TbConFlowDao.where(whereSql);
		return tbConFlows;
	}
	
	/**
	 * 获取未审核的车团合同
	 * @param CON_STATE
	 * @return
	 */
	public List<TbConCt> findTbConCtByState(Integer CON_STATE){
		String whereSql = 
				"CON_CT_ID IN "
				+ "(SELECT CON_ID FROM TB_CON_FLOW CON_STATE = " + CON_STATE + " AND CON_ID LIKE 'SHCT%')";
		List<TbConCt> tbConCts = TbConCtDao.where(whereSql);
		return tbConCts;
	}
	
	/**
	 * 获取未审核的车团合同,车团流程表的信息
	 * @param CON_STATE
	 * @return
	 */
	public List<Map<String, Object>> getConFlowConCtsByConState2(Integer CON_STATE, String auditor, int startIndex, int pageSize){
		List<Map<String, Object>> conFlowConCts = new ArrayList<Map<String, Object>>();;
		String whereSql = "CON_STATE = " + CON_STATE + " and CON_ID LIKE 'SHCT%' and nextcheck like '%+" + auditor + "/%' DESC limit " + startIndex + ", " + pageSize;
		List<TbConFlow> conFlows = TbConFlowDao.where(whereSql);
		if(conFlows!=null){
			for(TbConFlow conFlow : conFlows){
				Map<String, Object> conFlowConCt = new HashMap<String, Object>();
				conFlowConCt.put("conFlow", conFlow);
				String conId = conFlow.getCon_id();
				String sql = "CON_CT_ID = '" + conId + "'";
				List<TbConCt> conCts = TbConCtDao.where(sql);
				if(conCts!=null && conCts.size()>0){
					TbConCt conCt = conCts.get(0);
					conFlowConCt.put("conCt", conCt);
				}
				conFlowConCts.add(conFlowConCt);
			}
		}
		return conFlowConCts;
	}
	
	
	public PageResult<Map<String, Object>> getConFlowConCtsByConState(Integer CON_STATE, String nextCheck, int pageNum, int pageSize,String subsql){
		List<Map<String, Object>> conFlowConCts = new ArrayList<Map<String, Object>>();;
		//pager.setPageNum(pageNum);
		int startIndex = (pageNum - 1)*pageSize;
		String whereSql = "";
		if(subsql!=null && !subsql.equals("")){
			whereSql += "CON_STATE = " + CON_STATE + " and CON_ID LIKE 'SHCT%' and nextcheck like '%+" + nextCheck + "/%' "+subsql;
		}else{
			whereSql += "CON_STATE = " + CON_STATE + " and CON_ID LIKE 'SHCT%' and nextcheck like '%+" + nextCheck + "/%' ";
		}
			int totalRecords = TbConFlowDao.whereCount(whereSql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String, Object>>(pageNum, totalRecords);
		pager.setPageSize(pageSize);
		whereSql = whereSql + " limit " + startIndex + ", " + pageSize;
		List<TbConFlow> conFlows = TbConFlowDao.where(whereSql);
		if(conFlows!=null){
			for(TbConFlow conFlow : conFlows){
				Map<String, Object> conFlowConCt = new HashMap<String, Object>();
				conFlowConCt.put("conFlow", conFlow);
				String conId = conFlow.getCon_id();
				String sql = "CON_CT_ID = '" + conId + "'";
				List<TbConCt> conCts = TbConCtDao.where(sql);
				if(conCts!=null && conCts.size()>0){
					TbConCt conCt = conCts.get(0);
					int series = Integer.parseInt(conCt.getCus_seriers());
					String seriesName = "全系";
					if(series!=-1){
						seriesName = this.brService.getNameByCatalogid(series);
					}else if(series==-2){
						seriesName = "";
					}
					conCt.setSeriers_name(seriesName);
					conFlowConCt.put("conCt", conCt);
					sql = "user_code = '" + conCt.getUser_id() + "'";
					List<TbFinUser> users = TbFinUserDao.where(sql);
					if(users!=null && users.size()>0){
						TbFinUser user = users.get(0);
						sql = "org_id = '" + user.getOrg_code() + "'";
						List<TbFinOrg> orgs = TbFinOrgDao.where(sql);
						if(orgs!=null && orgs.size()>0){
							TbFinOrg org = orgs.get(0);
							conFlowConCt.put("org", org);
						}
					}
				}
				conFlowConCts.add(conFlowConCt);
			}
		}
		pager.setList(conFlowConCts);
		return pager;
	}
	
	/**
	 * 获取未审核的众智车展合同
	 * @param CON_STATE
	 * @return
	 */
	public List<TbConCz> findTbConCzByState(Integer CON_STATE){
		String whereSql = 
				"CON_CZ_ID IN "
				+ "(SELECT CON_ID FROM TB_CON_FLOW CON_STATE = " + CON_STATE + " AND CON_ID LIKE 'SHCZ%')";
		List<TbConCz> tbConCzs = TbConCzDao.where(whereSql);
		return tbConCzs;
	}
	
	/**
	 * 获取未审核的众智车展合同,众智车展流程表的信息
	 * @param CON_STATE
	 * @return
	 */
	public List<Map<String, Object>> getConFlowConCzsByConState2(Integer CON_STATE, String auditor, int startIndex, int pageSize){
		List<Map<String, Object>> conFlowConCzs = new ArrayList<Map<String, Object>>();
		//List<TbConZh> where3 = TbConZhDao.where(" USER_ID='" + userId + "' and CON_STATE<>6 ORDER BY `update` DESC limit "+startIndex+", "+pageSize);
		String whereSql = "CON_STATE = " + CON_STATE + " and CON_ID LIKE 'SHCZ%' and nextcheck like '%+" + auditor + "/%' DESC limit " + startIndex + ", " + pageSize;
		List<TbConFlow> conFlows = TbConFlowDao.where(whereSql);
		if(conFlows!=null){
			for(TbConFlow conFlow : conFlows){
				Map<String, Object> conFlowConCz = new HashMap<String, Object>();
				conFlowConCz.put("conFlow", conFlow);
				String conId = conFlow.getCon_id();
				String sql = "CON_CZ_ID = '" + conId + "'";
				List<TbConCz> conCzs = TbConCzDao.where(sql);
				if(conCzs!=null && conCzs.size()>0){
					TbConCz conCz = conCzs.get(0);
					conFlowConCz.put("conCz", conCz);
				}
				conFlowConCzs.add(conFlowConCz);
			}
		}
		return conFlowConCzs;
	}
	
	public PageResult<Map<String, Object>> getConFlowConCzsByConState(Integer CON_STATE, String nextCheck, int pageNum, int pageSize,String subsql){
		int startIndex = (pageNum - 1)*pageSize;
		List<Map<String, Object>> conFlowConCzs = new ArrayList<Map<String, Object>>();
		String whereSql = "";
		if(subsql!=null && !subsql.equals("")){
			whereSql += "CON_STATE = " + CON_STATE + " and CON_ID LIKE 'SHCZ%' and nextcheck like '%+" + nextCheck + "/%' "+subsql;
		}else{
			whereSql += "CON_STATE = " + CON_STATE + " and CON_ID LIKE 'SHCZ%' and nextcheck like '%+" + nextCheck + "/%' ";
		}
			int totalRecords = TbConFlowDao.whereCount(whereSql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String, Object>>(pageNum, totalRecords);
		pager.setPageSize(pageSize);
		whereSql = whereSql + " limit " + startIndex + ", " + pageSize;
		List<TbConFlow> conFlows = TbConFlowDao.where(whereSql);
		if(conFlows!=null){
			for(TbConFlow conFlow : conFlows){
				Map<String, Object> conFlowConCz = new HashMap<String, Object>();
				conFlowConCz.put("conFlow", conFlow);
				String conId = conFlow.getCon_id();
				String sql = "CON_CZ_ID = '" + conId + "'";
				List<TbConCz> conCzs = TbConCzDao.where(sql);
				if(conCzs!=null && conCzs.size()>0){
					TbConCz conCz = conCzs.get(0);
					int series = Integer.parseInt(conCz.getCus_seriers());
					String seriesName = "全系";
					if(series!=-1){
						seriesName = this.brService.getNameByCatalogid(series);
					}else if(series==-2){
						seriesName = "";
					}
					conCz.setSeriers_name(seriesName);
					conFlowConCz.put("conCz", conCz);
					sql = "user_code = '" + conCz.getUser_id() + "'";
					List<TbFinUser> users = TbFinUserDao.where(sql);
					if(users!=null && users.size()>0){
						TbFinUser user = users.get(0);
						sql = "org_id = '" + user.getOrg_code() + "'";
						List<TbFinOrg> orgs = TbFinOrgDao.where(sql);
						if(orgs!=null && orgs.size()>0){
							TbFinOrg org = orgs.get(0);
							conFlowConCz.put("org", org);
						}
					}
				}
				
				conFlowConCzs.add(conFlowConCz);
			}
		}
		pager.setList(conFlowConCzs);
		return pager;
	}
	
	
	/**
	 * 获取未审核的众智广告合同
	 * @param CON_STATE
	 * @return
	 */
	public List<TbConZh> findTbConZhByState(Integer CON_STATE){
		String whereSql = 
				"CON_ZH_ID IN "
				+ "(SELECT CON_ID FROM TB_CON_FLOW CON_STATE = " + CON_STATE + " AND CON_ID LIKE 'SHZH%')";
		List<TbConZh> tbConZhs = TbConZhDao.where(whereSql);
		return tbConZhs;
	}
	
	/**
	 * 获取未审核的众智广告合同,众智广告流程表的信息
	 * @param CON_STATE
	 * @return
	 */
	public List<Map<String, Object>> getConFlowConZhsByConState2(Integer CON_STATE, String auditor, int startIndex, int pageSize){
		List<Map<String, Object>> conFlowConZhs = new ArrayList<Map<String, Object>>();
		String whereSql = "CON_STATE = " + CON_STATE + " and CON_ID LIKE 'SHZH%' and nextcheck like '%+" + auditor + "/%' limit " + startIndex + ", " + pageSize;
		List<TbConFlow> conFlows = TbConFlowDao.where(whereSql);
		if(conFlows!=null){
			for(TbConFlow conFlow : conFlows){
				String nextchecks = conFlow.getNextcheck();
				if(nextchecks!=null && !nextchecks.trim().equals("")){
					if(nextchecks.contains("+"+auditor+"/")){
						Map<String, Object> conFlowConZh = new HashMap<String, Object>();
						conFlowConZh.put("conFlow", conFlow);
						String conId = conFlow.getCon_id();
						String sql = "CON_ZH_ID = '" + conId + "'";
						List<TbConZh> conZhs = TbConZhDao.where(sql);
						if(conZhs!=null && conZhs.size()>0){
							TbConZh conZh = conZhs.get(0);
							conFlowConZh.put("conZh", conZh);
						}
						conFlowConZhs.add(conFlowConZh);
					}
				}
			}
		}
		return conFlowConZhs;
	}
	
	public PageResult<Map<String, Object>> getConFlowConZhsByConState(Integer CON_STATE, String nextCheck, int pageNum, int pageSize,String subsql){
		int startIndex = (pageNum - 1)*pageSize;		
		List<Map<String, Object>> conFlowConZhs = new ArrayList<Map<String, Object>>();
		String whereSql = "CON_ID LIKE 'SHZH%' AND CON_STATE = " + CON_STATE ;
		if(subsql!=null && !subsql.equals("")){
			if(nextCheck!=null && !nextCheck.equals("")){
				whereSql += " and nextcheck like '%+" + nextCheck + "/%' "+subsql;
			}else{
				if(nextCheck==null){
					whereSql += " and nextcheck is null "+subsql;
				}else{
					whereSql += " and nextcheck = '" + nextCheck + "' "+subsql;
				}
			}
		}else{
			if(nextCheck!=null && !nextCheck.equals("")){
				whereSql += " and nextcheck like '%+" + nextCheck + "/%' ";
			}else{
				if(nextCheck==null){
					whereSql += " and nextcheck is null ";
				}else{
					whereSql += " and nextcheck = '" + nextCheck + "' ";
				}
			}
		}
		int totalRecords = TbConFlowDao.whereCount(whereSql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String, Object>>(pageNum, totalRecords);
		pager.setPageSize(pageSize);
		whereSql = whereSql +  " limit " + startIndex + ", " + pageSize;
		List<TbConFlow> conFlows = TbConFlowDao.where(whereSql);
		if(conFlows!=null){
			for(TbConFlow conFlow : conFlows){
				Map<String, Object> conFlowConZh = new HashMap<String, Object>();
				conFlowConZh.put("conFlow", conFlow);
				String conId = conFlow.getCon_id();
				String sql = "CON_ZH_ID = '" + conId + "'";
				List<TbConZh> conZhs = TbConZhDao.where(sql);
				if(conZhs!=null && conZhs.size()>0){
					TbConZh conZh = conZhs.get(0);
					conFlowConZh.put("conZh", conZh);
					sql = "user_code = '" + conZh.getUser_id() + "'";
					List<TbFinUser> users = TbFinUserDao.where(sql);
					if(users!=null && users.size()>0){
						TbFinUser user = users.get(0);
						sql = "org_id = '" + user.getOrg_code() + "'";
						List<TbFinOrg> orgs = TbFinOrgDao.where(sql);
						if(orgs!=null && orgs.size()>0){
							TbFinOrg org = orgs.get(0);
							conFlowConZh.put("org", org);
						}
					}
				}
				conFlowConZhs.add(conFlowConZh);
			}
		}
		pager.setList(conFlowConZhs);
		return pager;
	}
	
	
	/**
	 * 部门经理审核
	 * @param tbConFlow
	 * @param checkUser
	 * @return
	 */
	public int managerCheck(TbConFlow tbConFlow, String checkUser){
		String conId = tbConFlow.getCon_id();
		int result = -1;
		if(conId!=null){
			DBConnect dbc = null;
			try{
//				int con_state = 0;
				dbc = new DBConnect();
				dbc.setAutoCommit(false);
				if(conId.startsWith("SHCZ")){
					List<TbConCz> conCzs = TbConCzDao.where("CON_CZ_ID = '" + conId + "'");
					if(conCzs!=null && conCzs.size()>0){
						TbConCz conCz = conCzs.get(0);
						conCz.setCon_cz_id(conId);
						conCz.setCon_state(tbConFlow.getCon_state());
						result = TbConCzDao.update(dbc, conCz);
					}
				}else if(conId.startsWith("SHCT")){
					List<TbConCt> conCts = TbConCtDao.where("CON_CT_ID = '" + conId + "'");
					if(conCts!=null && conCts.size()>0){
						TbConCt conCt = conCts.get(0);
						conCt.setCon_ct_id(conId);
						conCt.setCon_state(tbConFlow.getCon_state());
						result = TbConCtDao.update(dbc, conCt);
					}
				}else if(conId.startsWith("SHZH")){
					List<TbConZh> conZhs = TbConZhDao.where("CON_ZH_ID = '" + conId + "'");
					if(conZhs!=null && conZhs.size()>0){
						TbConZh conZh = conZhs.get(0);
						conZh.setCon_zh_id(conId);
						conZh.setCon_state(tbConFlow.getCon_state());
						result = TbConZhDao.update(dbc, conZh);
					}
				}
				if(result==-1){
					dbc.rollback();
					return result;
				}
				result = TbConFlowDao.update(dbc, tbConFlow);
				if(result==-1){
					dbc.rollback();
					return result;
				}
				
				TbConCheckDetail conCheckDetail = new TbConCheckDetail();
				conCheckDetail.setCheck_type(0);
				conCheckDetail.setCheck_user(checkUser);
				conCheckDetail.setCid(conId);
				conCheckDetail.setAdd_time(new Date());
				if(tbConFlow.getCon_state()==4){
					conCheckDetail.setContent(tbConFlow.getCon_msg());
					conCheckDetail.setCheck_type(1);
				}
				result = TbConCheckDetailDao.save(dbc, conCheckDetail);
				if(result==-1){
					dbc.rollback();
					return result;
				}
				dbc.commit();
				
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				try {
					if(dbc!=null){
						dbc.close();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	
	/**
	 * 部门经理审核
	 * @param tbConFlow
	 * @return
	 */
	public int managerCheck(TbConFlow tbConFlow, TbConFlow tbConFlowSeal, String checkUser){
		String conId = tbConFlow.getCon_id();
		int result = -1;
		if(conId!=null){
			DBConnect dbc = null;
			try {
				int con_state = 0;
				dbc = new DBConnect();
				dbc.setAutoCommit(false);
				int stamp = 0;
				if(conId.startsWith("SHCZ")){
					List<TbConCz> conCzs = TbConCzDao.where("CON_CZ_ID = '" + conId + "'");
					if(conCzs!=null){
						TbConCz conCz = conCzs.get(0);
						conCz.setCon_cz_id(conId);
						conCz.setCon_state(tbConFlow.getCon_state());
//						stamp = conCz.getStamp();
//						if(stamp==2){
//							//公司先盖章
//							conCz.setCon_state(tbConFlow.getCon_state());
//							con_state = tbConFlow.getCon_state();
//						}else if(stamp==1){
//							//客户先盖章
//							conCz.setCon_state(tbConFlow.getCon_state());
//							con_state = tbConFlow.getCon_state() + 1;
//						}else {
//							result = -1;
//						}
						result = TbConCzDao.update(dbc, conCz);
					}
				}else if(conId.startsWith("SHCT")){
					List<TbConCt> conCts = TbConCtDao.where("CON_CT_ID = '" + conId + "'");
					if(conCts!=null){
						TbConCt conCt = conCts.get(0);
						conCt.setCon_ct_id(conId);
						conCt.setCon_state(tbConFlow.getCon_state());
//						stamp = conCt.getStamp();
//						if(stamp==2){
//							//公司先盖章
//							conCt.setCon_state(tbConFlow.getCon_state());
//							con_state = tbConFlow.getCon_state();
//						}else if(stamp==1){
//							//客户先盖章
//							conCt.setCon_state(tbConFlow.getCon_state());
//							con_state = tbConFlow.getCon_state() + 1;
//						}else {
//							result = -1;
//						}
						result = TbConCtDao.update(dbc, conCt);
					}
				}else if(conId.startsWith("SHZH")){
					List<TbConZh> conZhs = TbConZhDao.where("CON_ZH_ID = '" + conId + "'");
					if(conZhs!=null){
						TbConZh conZh = conZhs.get(0);
						conZh.setCon_zh_id(conId);
						conZh.setCon_state(tbConFlow.getCon_state());
//						stamp = conZh.getStamp();
//						if(stamp==2){
//							//公司先盖章
//							conZh.setCon_state(tbConFlow.getCon_state());
//							con_state = tbConFlow.getCon_state();
//						}else if(stamp==1){
//							//客户先盖章
//							conZh.setCon_state(tbConFlow.getCon_state());
//							con_state = tbConFlow.getCon_state() + 1;
//						}else {
//							result = -1;
//						}
						result = TbConZhDao.update(dbc, conZh);
					}
				}
				if(result==-1){
					dbc.rollback();
					return result;
				}
//				tbConFlow.setCon_state(con_state);
				if(stamp==2){
					result = TbConFlowDao.update(dbc, tbConFlow);
				}else if(stamp==1){
					result = TbConFlowDao.update(dbc, tbConFlowSeal);
				}
				if(result==-1){
					dbc.rollback();
					return result;
				}
				TbConCheckDetail conCheckDetail = new TbConCheckDetail();
				conCheckDetail.setCheck_type(0);
				conCheckDetail.setCheck_user(checkUser);
				conCheckDetail.setCid(conId);
				conCheckDetail.setAdd_time(new Date());
				if(tbConFlow.getCon_state()==4){
					conCheckDetail.setContent(tbConFlow.getCon_msg());
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
				throw new RuntimeException(e);
			} finally {
				try {
					if(dbc!=null){
						dbc.close();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	
	
	/**
	 * 流程部审核
	 * @param tbConFlow
	 * @return
	 */
	public int flowCheck(TbConFlow tbConFlow, TbAttachUpload attach, String checkUser){
		String conId = tbConFlow.getCon_id();
		int result = -1;
		int primaryKey = -1;
		if(conId!=null){
			DBConnect dbc = null;
			try {
				dbc = new DBConnect();
				dbc.setAutoCommit(false);
				if(attach!=null){
					result = TbAttachUploadDao.save(dbc, attach);
					if(result==-1){
						dbc.rollback();
						return result;
					}
					PreparedStatement pstmt = dbc.getPreparedStatement();
					ResultSet rs = pstmt.getGeneratedKeys();
					
					while(rs.next()){
						primaryKey = rs.getInt(1);
					}
				}
				if(conId.startsWith("SHCZ")){
					List<TbConCz> conCzs = TbConCzDao.where("CON_CZ_ID = '" + conId + "'");
					if(conCzs!=null){
						TbConCz conCz = conCzs.get(0);
						conCz.setCon_cz_id(conId);
						conCz.setCon_state(tbConFlow.getCon_state());
						if(attach!=null){
							conCz.setUpload_id(primaryKey);
						}
//						con
//						if(conCz.getStamp()==1){
//							tbConFlow.setFlow_check(tbConFlow.getFlow_seal());
//						}
						result = TbConCzDao.update(dbc, conCz);
					}
				}else if(conId.startsWith("SHCT")){
					List<TbConCt> conCts = TbConCtDao.where("CON_CT_ID = '" + conId + "'");
					if(conCts!=null){
						TbConCt conCt = conCts.get(0);
						conCt.setCon_ct_id(conId);
						conCt.setCon_state(tbConFlow.getCon_state());
						if(attach!=null){
							conCt.setUpload_id(primaryKey);
						}
//						if(conCt.getStamp()==1){
//							tbConFlow.setFlow_check(tbConFlow.getFlow_seal());
//						}
						result = TbConCtDao.update(dbc, conCt);
					}
				}else if(conId.startsWith("SHZH")){
					List<TbConZh> conZhs = TbConZhDao.where("CON_ZH_ID = '" + conId + "'");
					if(conZhs!=null){
						TbConZh conZh = conZhs.get(0);
						conZh.setCon_zh_id(conId);
						conZh.setCon_state(tbConFlow.getCon_state());
						if(attach!=null){
							conZh.setUpload_id(primaryKey);
						}
//						if(conZh.getStamp()==1){
//							tbConFlow.setFlow_check(tbConFlow.getFlow_seal());
//						}
						result = TbConZhDao.update(dbc, conZh);
					}
				}
				if(result==-1){
					dbc.rollback();
					return result;
				}
				result = TbConFlowDao.update(dbc, tbConFlow);
				if(result==-1){
					dbc.rollback();
					return result;
				}
				
				TbConCheckDetail conCheckDetail = new TbConCheckDetail();
				conCheckDetail.setCheck_type(0);
				conCheckDetail.setCheck_user(checkUser);
				conCheckDetail.setCid(conId);
				conCheckDetail.setAdd_time(new Date());
				if(tbConFlow.getCon_state()==4){
					conCheckDetail.setContent(tbConFlow.getCon_msg());
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
			
		}
		return result;
	}
	
	/**
	 * 驳回。
	 * @param tbConFlow
	 * @return
	 */
	public int rejected(TbConFlow tbConFlow, String checkUser){
		String conId = tbConFlow.getCon_id();
		int result = -1;
		if(conId!=null){
			DBConnect dbc = null;
			try {
				dbc = new DBConnect();
				dbc.setAutoCommit(false);
				if(conId.startsWith("SHCZ")){
					List<TbConCz> conCzs = TbConCzDao.where("CON_CZ_ID = '" + conId + "'");
					if(conCzs!=null){
						TbConCz conCz = conCzs.get(0);
						conCz.setCon_cz_id(conId);
						conCz.setCon_state(tbConFlow.getCon_state());
						result = TbConCzDao.update(dbc, conCz);
					}
				}else if(conId.startsWith("SHCT")){
					List<TbConCt> conCts = TbConCtDao.where("CON_CT_ID = '" + conId + "'");
					if(conCts!=null){
						TbConCt conCt = conCts.get(0);
						conCt.setCon_ct_id(conId);
						conCt.setCon_state(tbConFlow.getCon_state());
						result = TbConCtDao.update(dbc, conCt);
					}
				}else if(conId.startsWith("SHZH")){
					List<TbConZh> conZhs = TbConZhDao.where("CON_ZH_ID = '" + conId + "'");
					if(conZhs!=null){
						TbConZh conZh = conZhs.get(0);
						conZh.setCon_zh_id(conId);
						conZh.setCon_state(tbConFlow.getCon_state());
						result = TbConZhDao.update(dbc, conZh);
					}
				}
				if(result==-1){
					dbc.rollback();
					return result;
				}
				result = TbConFlowDao.update(dbc, tbConFlow);
				if(result==-1){
					dbc.rollback();
					return result;
				}
				TbConCheckDetail conCheckDetail = new TbConCheckDetail();
				conCheckDetail.setCheck_type(0);
				conCheckDetail.setCheck_user(checkUser);
				conCheckDetail.setCid(conId);
				conCheckDetail.setAdd_time(new Date());
				if(tbConFlow.getCon_state()==4){
					conCheckDetail.setContent(tbConFlow.getCon_msg());
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
			
		}
		return result;
	}
	
	/**
	 * 根据Id获取流程合同表
	 * @param con_flow_id
	 * @return
	 */
	public TbConFlow getTbConFlowById(int con_flow_id){
		TbConFlow tbConFlow = null;
		List<TbConFlow> tbConFlows = TbConFlowDao.where("con_flow_id = " + con_flow_id);
		if(tbConFlows!=null && tbConFlows.size()>0){
			tbConFlow = tbConFlows.get(0);
			return tbConFlow;
		}
		return tbConFlow;
	}
	
	
	/**
	 * 级联查询众智广告和流程 分页
	 * @return
	 */
	public PageResult<Map<String, Object>> getZhFlowPager(int stamp, int conState, String nextCheck, int pageNum, int pageSize){
		String countSql = "select count(*) from tb_con_zh zh, tb_con_flow f where zh.stamp = " + stamp + " and zh.con_zh_id = f.con_id and f.con_state = "+conState+" and f.nextcheck like '%+"+nextCheck+"/%' ";
		int startIndex = (pageNum - 1)*pageSize;	
		String sql = "select zh.con_zh_id, zh.cus_name, zh.user_name, zh.create, f.con_flow_id from tb_con_zh zh, tb_con_flow f where zh.stamp = " + stamp + " and zh.con_zh_id = f.con_id and f.con_state = "+conState+" and f.nextcheck like '%+"+nextCheck+"/%' limit " + startIndex + ", " + pageSize;
		PageResult<Map<String, Object>> pager = null;
		DBConnect dbCon = null;
		DBConnect dbc = null;
		ResultSet rs = null;
		try {
			dbCon = new DBConnect(countSql);
			rs = dbCon.executeQuery();
			int totalRecords = 0;
			while (rs.next()) {
				totalRecords = rs.getInt(1);
			}
			dbc = new DBConnect(sql);
			pager = new PageResult<Map<String, Object>>(pageNum,totalRecords);
			pager.setPageSize(pageSize);
			rs = dbc.executeQuery();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			while(rs.next()){
				Map<String, Object> map =  new HashMap<String, Object>();
				TbConZh conZh = new TbConZh();
				conZh.setCon_zh_id(rs.getString("con_zh_id"));
				conZh.setCus_name(rs.getString("cus_name"));
				conZh.setUser_id("user_name");
				conZh.setCreate(rs.getDate("create"));
				TbConFlow conFlow = new TbConFlow();
				conFlow.setCon_flow_id(rs.getInt("con_flow_id"));
				map.put("conZh", conZh);
				map.put("conFlow", conFlow);
				list.add(map);
			}
			pager.setList(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			try {
				if (dbc != null){
					dbc.close();
				}
				if(dbCon != null){
					dbCon.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pager;
	}
	
	public PageResult<Map<String, Object>> getCtFlowPager(int stamp, int conState, String nextCheck, int pageNum, int pageSize){
		String countSql = "select count(*) from tb_con_ct ct, tb_con_flow f where ct.stamp = " + stamp + " and ct.con_ct_id = f.con_id and f.con_state = "+conState+" and f.nextcheck like '%+"+nextCheck+"/%' ";
		System.out.println("countSql:" + countSql);
		int startIndex = (pageNum - 1)*pageSize;	
		String sql = "select ct.con_ct_id, ct.cus_name, ct.user_name, ct.create, ct.cus_brand, ct.act_date, f.con_flow_id from tb_con_ct ct, tb_con_flow f where ct.stamp = " + stamp + " and ct.con_ct_id = f.con_id and f.con_state = "+conState+" and f.nextcheck like '%+"+nextCheck+"/%' limit " + startIndex + ", " + pageSize;
		System.out.println("sql:" + sql);
		PageResult<Map<String, Object>> pager = null;
		DBConnect dbCon = null;
		DBConnect dbc = null;
		ResultSet rs = null;
		try {
			dbCon = new DBConnect(countSql);
			rs = dbCon.executeQuery();
			int totalRecords = 0;
			while (rs.next()) {
				totalRecords = rs.getInt(1);
			}
			dbc = new DBConnect(sql);
			pager = new PageResult<Map<String, Object>>(pageNum,totalRecords);
			pager.setPageSize(pageSize);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			rs = dbc.executeQuery();
			while(rs.next()){
				Map<String, Object> map =  new HashMap<String, Object>();
				TbConCt conCt = new TbConCt();
				conCt.setCon_ct_id(rs.getString("con_ct_id"));
				conCt.setCus_name(rs.getString("cus_name"));
				conCt.setUser_id("user_name");
				conCt.setCus_brand(rs.getString("cus_brand"));
				conCt.setAct_date(rs.getDate("act_date"));
				conCt.setCreate(rs.getDate("create"));
				TbConFlow conFlow = new TbConFlow();
				conFlow.setCon_flow_id(rs.getInt("con_flow_id"));
				map.put("conCt", conCt);
				map.put("conFlow", conFlow);
				list.add(map);
			}
			pager.setList(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if (dbc != null){
					dbc.close();
				}
				if(dbCon != null){
					dbCon.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return pager;
	}
	
	
	public PageResult<Map<String, Object>> getCzFlowPager(int stamp, int conState, String nextCheck, int pageNum, int pageSize){
		String countSql = "select count(*) from tb_con_cz cz, tb_con_flow f where cz.stamp = " + stamp + " and cz.con_cz_id = f.con_id and f.con_state = "+conState+" and f.nextcheck like '%+"+nextCheck+"/%' ";
		int startIndex = (pageNum - 1)*pageSize;	
		String sql = "select cz.con_cz_id, cz.cus_name, cz.user_name, cz.create, cz.cus_brand, cz.act_date, f.con_flow_id from tb_con_cz cz, tb_con_flow f where cz.stamp = " + stamp + " and cz.con_cz_id = f.con_id and f.con_state = "+conState+" and f.nextcheck like '%+"+nextCheck+"/%' limit " + startIndex + ", " + pageSize;
		PageResult<Map<String, Object>> pager = null;
		DBConnect dbCon = null;
		DBConnect dbc = null;
		ResultSet rs = null;
		try {
			dbCon = new DBConnect(countSql);
			rs = dbCon.executeQuery();
			int totalRecords = 0;
			while (rs.next()) {
				totalRecords = rs.getInt(1);
			}
			dbc = new DBConnect(sql);
			pager = new PageResult<Map<String, Object>>(pageNum,totalRecords);
			pager.setPageSize(pageSize);
			rs = dbc.executeQuery();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			while(rs.next()){
				Map<String, Object> map =  new HashMap<String, Object>();
				TbConCz conCz = new TbConCz();
				conCz.setCon_cz_id(rs.getString("con_cz_id"));
				conCz.setCus_name(rs.getString("cus_name"));
				conCz.setUser_id("user_name");
				conCz.setCus_brand(rs.getString("cus_brand"));
				conCz.setAct_date(rs.getString("act_date"));
				conCz.setCreate(rs.getDate("create"));
				TbConFlow conFlow = new TbConFlow();
				conFlow.setCon_flow_id(rs.getInt("con_flow_id"));
				map.put("conCz", conCz);
				map.put("conFlow", conFlow);
				list.add(map);
			}
			pager.setList(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			try {
				if (dbc != null){
					dbc.close();
				}
				if(dbCon != null){
					dbCon.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pager;
	}
	
	
	public PageResult<Map<String, Object>> lookRejectCon(String conType, int state, String userId, int pageNum, int pageSize){
		PageResult<Map<String, Object>> pager = null;
		if("SHCT".equals(conType)){
			pager = this.lookRejectConCt(state, userId, pageNum, pageSize);
		}else if("SHCZ".equals(conType)){
			pager = this.lookRejectConCz(state, userId, pageNum, pageSize);
		}else if("SHZH".equals(conType)){
			pager = this.lookRejectConZh(state, userId, pageNum, pageSize);
		}
		return pager;
	}
	
	public PageResult<Map<String, Object>> lookRejectConCz(int state, String userId, int pageNum, int pageSize){
		int startIndex = (pageNum-1)*pageSize;
		String sql = "CON_STATE = " + state + " ";
		if(userId!=null){
			sql += " and user_id = '" + userId + "'" ;
		}
		int totalRecords = TbConCzDao.whereCount(sql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String,Object>>(pageNum, totalRecords);
		sql += " limit " + startIndex + ", " + pageSize;
		List<TbConCz> conCzs = TbConCzDao.where(sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(TbConCz conCz : conCzs){
			Map<String, Object> map = new HashMap<String, Object>();
			String conId = conCz.getCon_cz_id();
			map.put("conCz", conCz);
			String user_id = conCz.getUser_id();
			TbFinUser user = new TbFinUserService().findUserByUserId(user_id);
			map.put("user", user);
			TbConFlow conFlow = this.getConFlowByConId(conId);
			map.put("conFlow", conFlow);
			list.add(map);
		}
		pager.setList(list);
		return pager;
	}
	
	
	public PageResult<Map<String, Object>> lookRejectConCt(int state, String userId, int pageNum, int pageSize){
		int startIndex = (pageNum-1)*pageSize;
		String sql = "CON_STATE = " + state + " ";
		if(userId!=null){
			sql += " and user_id = '" + userId + "'" ;
		}
		int totalRecords = TbConCtDao.whereCount(sql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String,Object>>(pageNum, totalRecords);
		sql += " limit " + startIndex + ", " + pageSize;
		List<TbConCt> conCts = TbConCtDao.where(sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(TbConCt conCt : conCts){
			Map<String, Object> map = new HashMap<String, Object>();
			String conId = conCt.getCon_ct_id();
			map.put("conCt", conCt);
			String user_id = conCt.getUser_id();
			TbFinUser user = new TbFinUserService().findUserByUserId(user_id);
			map.put("user", user);
			TbConFlow conFlow = this.getConFlowByConId(conId);
			map.put("conFlow", conFlow);
			list.add(map);
		}
		pager.setList(list);
		return pager;
	}
	
	public PageResult<Map<String, Object>> lookRejectConZh(int state, String userId, int pageNum, int pageSize){
		int startIndex = (pageNum-1)*pageSize;
		String sql = "CON_STATE = " + state + " ";
		if(userId!=null){
			sql += " and user_id = '" + userId + "'" ;
		}
		int totalRecords = TbConZhDao.whereCount(sql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String,Object>>(pageNum, totalRecords);
		sql += " limit " + startIndex + ", " + pageSize;
		List<TbConZh> conZhs = TbConZhDao.where(sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(TbConZh conZh : conZhs){
			Map<String, Object> map = new HashMap<String, Object>();
			String conId = conZh.getCon_zh_id();
			map.put("conZh", conZh);
			String user_id = conZh.getUser_id();
			TbFinUser user = new TbFinUserService().findUserByUserId(user_id);
			map.put("user", user);
			TbConFlow conFlow = this.getConFlowByConId(conId);
			map.put("conFlow", conFlow);
			list.add(map);
		}
		pager.setList(list);
		return pager;
	}
	
	public TbConFlow getConFlowByConId(String conId){
		TbConFlow conFlow = null;
		List<TbConFlow> conFlows = TbConFlowDao.where("CON_ID = '" + conId + "'");
		if(conFlows!=null && conFlows.size()>0){
			conFlow = conFlows.get(0);
		}
		return conFlow;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
