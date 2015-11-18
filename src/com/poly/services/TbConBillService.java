package com.poly.services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import car_beans.TbConAddcon;
import car_beans.TbConBill;
import car_beans.TbConBillFlow;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConZh;
import car_beans.TbFinOrg;
import car_beans.TbFinUser;
import car_daos.DBConnect;
import car_daos.TbConAddconDao;
import car_daos.TbConBillDao;
import car_daos.TbConBillFlowDao;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConZhDao;
import car_daos.TbFinOrgDao;
import car_daos.TbFinUserDao;

import com.poly.bean.PageResult;

public class TbConBillService {
	/**
	 * 判断是否能开票
	 * 
	 */
	public boolean checkAddBill(String id) {
		boolean flag = true;
		List<TbConBill> conBills = TbConBillDao
				.where("( BILL_STATE = 0 or BILL_STATE = 1 ) and CON_ID = '" + id
						+ "'");
		if (conBills!=null&&conBills.size() > 0) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 根据获取开票集合(0-未审核 1-经理审核 2-流程部审核 )
	 */
	public PageResult<Map<String, Object>> getTbConBillsByHtId(int pageNum,
			int pageSize) {
		int startIndex = (pageNum - 1) * pageSize;
		String whereSql = " 1=1 ";
		int totalRecords = TbConBillDao.whereCount(whereSql);
		whereSql = whereSql + " limit " + startIndex + ", " + pageSize;
		List<TbConBill> tbConBills = TbConBillDao.where(whereSql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String, Object>>(
				pageNum, totalRecords);
		pager.setPageSize(pageSize);
		pager.setTotalPages((totalRecords + pageSize - 1) / pageSize);
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (TbConBill conBill : tbConBills) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("conBill", conBill);
			lists.add(map);
		}
		pager.setList(lists);
		return pager;
	}

	/**
	 * 根据合同号获取开票集合(0-未审核 1-经理审核 2-流程部审核 )
	 */
	public PageResult<Map<String, Object>> getTbConBillsByHtId(String id,
			int pageNum, int pageSize) {
		int startIndex = (pageNum - 1) * pageSize;
		String whereSql = "CON_ID='" + id + "' and BILL_STATE <> '5' ";
		int totalRecords = TbConBillDao.whereCount(whereSql);
		whereSql = whereSql + " limit " + startIndex + ", " + pageSize;
		List<TbConBill> tbConBills = TbConBillDao.where(whereSql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String, Object>>(
				pageNum, totalRecords);
		pager.setPageSize(pageSize);
		pager.setTotalPages((totalRecords + pageSize - 1) / pageSize);
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (TbConBill conBill : tbConBills) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("conBill", conBill);
			lists.add(map);
		}
		pager.setList(lists);
		return pager;
	}

	/**
	 * 根据申请单状态获取分页申请单集合0-未审核 1-经理审核 2-流程部审核
	 */
	public PageResult<Map<String, Object>> getAccessTbConBill(int pageNum,
			int pageSize, int state, String userId , String subsql) {
		int startIndex = (pageNum - 1) * pageSize;
		String whereSql = "BILL_STATE = " + state;
		if(subsql != null){
			whereSql += subsql;
		}
		if (userId != null) {
			String sql = "NEXTCHECK like '%" + userId + "%'";
			List<TbConBillFlow> billFlows = TbConBillFlowDao.where(sql);
			if (billFlows != null && billFlows.size() > 0) {
				whereSql += " and BILL_ID in ( ";
				String bill_id_String = "";
				for (int i = 0; i < billFlows.size(); i++) {
					bill_id_String += billFlows.get(i).getBill_id() + ",";
				}
				bill_id_String = bill_id_String.substring(0,
						bill_id_String.lastIndexOf(","));
				whereSql += bill_id_String + " ) ";

			}else{
				PageResult<Map<String, Object>> pager = new PageResult<Map<String, Object>>(
						pageNum, 0);
				return pager;
			}
		}
		int totalRecords = TbConBillDao.whereCount(whereSql);
		whereSql = whereSql + " order by bill_id desc " + " limit "
				+ startIndex + ", " + pageSize;
		List<TbConBill> tbConBills = TbConBillDao.where(whereSql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String, Object>>(
				pageNum, totalRecords);
		pager.setPageSize(pageSize);
		pager.setTotalPages((totalRecords + pageSize - 1) / pageSize);
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (TbConBill conBill : tbConBills) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("conBill", conBill);
			lists.add(map);
		}
		pager.setList(lists);
		return pager;
	}

	/**
	 * 根据条件查询被驳回的申请单 userId 传值为NULL的时候 查询所有被驳回的单子
	 */
	public PageResult<Map<String, Object>> getRejectTbConBill(int pageNum,
			int pageSize, String userId) {
		int startIndex = (pageNum - 1) * pageSize;
		String whereSql = "";
		if (userId == null) {
			whereSql = "BILL_STATE = " + 3 + " order by bill_id desc ";
		} else {
			whereSql = "BILL_STATE = " + 3 + " and USER_ID ='" + userId
					+ "' order by bill_id desc ";
		}
		int totalRecords = TbConBillDao.whereCount(whereSql);
		whereSql = whereSql + " limit " + startIndex + ", " + pageSize;
		List<TbConBill> tbConBills = TbConBillDao.where(whereSql);
		PageResult<Map<String, Object>> pager = new PageResult<Map<String, Object>>(
				pageNum, totalRecords);
		pager.setPageSize(pageSize);
		pager.setTotalPages((totalRecords + pageSize - 1) / pageSize);
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (TbConBill conBill : tbConBills) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("conBill", conBill);
			lists.add(map);
		}
		pager.setList(lists);
		return pager;
	}

	/**
	 * 查找能够进行修改的合同补充 如果该合同有正在审核的开票申请，就不能修改
	 * 
	 * @param conId
	 * @return
	 */
	public int findCzSupplyBill(String conId) {
		String sql = "select count(1) from tb_con_bill where CON_ID='" + conId
				+ "' and BILL_STATE not in (3,5) ";
		DBConnect dbc = null;
		try {
			dbc = new DBConnect(sql);
			ResultSet query = dbc.executeQuery();
			while (query.next()) {
				return query.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dbc != null) {
				try {
					dbc.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
/**
 * 获取所有的开票单
 * @param totalRecords
 * @param string
 */
	public PageResult<Map<String,Object>> getBillsByWhere(int pageNum,int totalRecords, String str) {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		String sql = str+" limit "+page.getStartIndex()+","+page.getPageSize();
		List<TbConBill> where = TbConBillDao.where(sql);
		System.out.println(sql);
		if(where!=null && where.size()>0){
			for(TbConBill connBill : where){
				Map<String,Object> map = new HashMap<String, Object>();
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
					List<TbConAddcon> where1 = TbConAddconDao.where(" CON_ID='"+id+"' ");
					if(where1!=null && where1.size()>0){
						addcon = where1.get(0);
					}
					map.put("addCon", addcon);
					map.put("tbBillFlow", tbBillFlow);
					map.put("conCt", conCt);
					map.put("org", org);
					map.put("conBill", connBill);
					
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
					List<TbConAddcon> where1 = TbConAddconDao.where(" CON_ID='"+id+"' ");
					if(where1!=null && where1.size()>0){
						addcon = where1.get(0);
					}
					map.put("addCon", addcon);
					map.put("tbBillFlow", tbBillFlow);
					map.put("conCz", conCz);
					map.put("org", org);
					map.put("conBill", connBill);
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
					List<TbConAddcon> where1 = TbConAddconDao.where(" CON_ID='"+id+"' ");
					if(where1!=null && where1.size()>0){
						addcon = where1.get(0);
					}
					map.put("addCon", addcon);
					map.put("tbBillFlow", tbBillFlow);
					map.put("conZh", conZh);
					map.put("org", org);
					map.put("conBill", connBill);
				}
				list.add(map);
			}
		}
		page.setList(list);
		return page;
	}

	public TbConBill getBillById(int bill_id) {
		return TbConBillDao.where(" BILL_ID='"+bill_id+"' ").get(0);
	}
}
