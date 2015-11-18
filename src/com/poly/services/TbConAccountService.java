package com.poly.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import car_beans.TbConAccount;
import car_beans.TbConAccountFlow;
import car_beans.TbConCheckDetail;
import car_daos.DBConnect;
import car_daos.TbConAccountDao;
import car_daos.TbConAccountFlowDao;
import car_daos.TbConCheckDetailDao;

import com.poly.bean.PageResult;

public class TbConAccountService {
//根据合同id查找相关说明
    public TbConAccount getConAccountByConId(String conId) {
        String sql = "CON_ID = '" + conId + "'";
        List<TbConAccount> accounts = TbConAccountDao.where(sql);
        TbConAccount account = null;
        if (accounts != null && accounts.size() > 0) {
            account = accounts.get(0);
        }
        return account;
    }
//暂时没用
    public int findCount(String conType, String userId) {
        String subsql = "select count(*) from tb_con_account acc,tb_con_account_flow af where acc.CON_ID like '" + conType
                + "%' and acc.CON_s_ID = af.CON_ACCOUNT_ID and af.NEXT_CHECK like '%+"+ userId + "/%' and acc.ACC_STATE=1";
        DBConnect dbc = null;
        try {
            dbc = new DBConnect(subsql);
            ResultSet query = dbc.executeQuery();
            if (query.next()) {
                int totalRecords = query.getInt(1);
                return totalRecords;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
			if(dbc!=null){
				try {
					dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

        return 0;
    }
    
    /**
     * 更新合同相关说明相关的流程
     * @param acc
     * @param accFlow
     * @param detail
     * @return
     */
    public int updateFlow(TbConAccount acc, TbConAccountFlow accFlow,TbConCheckDetail detail) {
         int updateResult = -1;
         DBConnect dbc = null;
         try{
             dbc = new DBConnect();
             TbConAccountDao.update(dbc,acc);
             TbConAccountFlowDao.update(dbc,accFlow);
             TbConCheckDetailDao.save(dbc, detail);
         }catch(Exception e){
             e.printStackTrace();
             try {
                dbc.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally{
                try {
                if(dbc!=null){
                    dbc.close();
                }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
         }

         updateResult = 1;
         return updateResult;
    }

    /**
     * 显示所有的被驳回的合同相关说明
     * @param userId
     * @param pageNum
     * @return
     */
	public PageResult<Map<String,Object>> lookMyRejectAccount(String userId, int pageNum) {
		List<TbConAccount> list = null;
		if(userId!=null){
			 int totalRecords = TbConAccountDao.whereCount(" USER_ID='"+userId+"' and ACC_STATE=3");
			 PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
			 list = TbConAccountDao.where(" USER_ID='"+userId+"' and ACC_STATE=3 order by `UPDATE` limit "+page.getStartIndex()+","+page.getPageSize());
			 List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
			 if(list!=null && list.size()>0){
				 for(TbConAccount acc : list){
					 List<TbConAccountFlow> where = TbConAccountFlowDao.where(" CON_ACCOUNT_ID='"+acc.getCon_s_id()+"'");
					 String msg = "";
					 if(where!=null && where.size()>0){
						 msg = where.get(0).getCon_msg();
					 }
					 Map<String,Object> map = new HashMap<String,Object>();
					 map.put("acc", acc);
					 map.put("rejectMsg", msg);
					 pager.add(map);
				 }
			 }
			 page.setList(pager);
			 return page;
		}else{
			int totalRecords = TbConAccountDao.whereCount(" ACC_STATE=3");
			 PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
			 list = TbConAccountDao.where(" ACC_STATE=3 order by `UPDATE` limit "+page.getStartIndex()+","+page.getPageSize());
			 List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
			 if(list!=null && list.size()>0){
				 for(TbConAccount acc : list){
					 List<TbConAccountFlow> where = TbConAccountFlowDao.where(" CON_ACCOUNT_ID='"+acc.getCon_s_id()+"'");
					 String msg = "";
					 if(where!=null && where.size()>0){
						 msg = where.get(0).getCon_msg();
					 }
					 Map<String,Object> map = new HashMap<String,Object>();
					 map.put("acc", acc);
					 map.put("rejectMsg", msg);
					 pager.add(map);
				 }
			 }
			 page.setList(pager);
			 return page;
		}
		
	}

}
