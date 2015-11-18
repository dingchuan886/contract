package com.poly.services;

import java.sql.SQLException;

import com.poly.dao.subDaos.TbConAheadAdvertismentDaoEnhance;
import com.poly.dao.subDaos.TbConAheadAdvertismentFlowDaoEnhance;

import car_beans.TbConAheadadvertisment;
import car_beans.TbConAheadadvertismentFlow;
import car_beans.TbConCheckDetail;
import car_daos.DBConnect;
import car_daos.TbConCheckDetailDao;

public class TbConAheadadvertismentService {
	
	public int manageAuditAdv(TbConAheadadvertisment adv,TbConAheadadvertismentFlow advFlow, TbConCheckDetail tbDetail) {
		DBConnect dbc = null;
		int i = -1;
		try{
			dbc = new DBConnect();
			dbc.setAutoCommit(false);
			TbConAheadAdvertismentDaoEnhance.update(dbc, adv);
			TbConAheadAdvertismentFlowDaoEnhance.update(dbc, advFlow);
			TbConCheckDetailDao.save(dbc, tbDetail);
			dbc.commit();
			i = 1;
		}catch(Exception e){
			e.printStackTrace();
			try {
				dbc.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
		
		return i;
	}
}
	
