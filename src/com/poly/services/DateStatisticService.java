package com.poly.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import car_beans.TbConAddcon;
import car_beans.TbConBill;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConRebate;
import car_beans.TbConZh;
import car_beans.TbConZhSub;
import car_daos.TbConBillDao;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConRebateDao;
import car_daos.TbConZhDao;
import car_daos.TbConZhSubDao;

public class DateStatisticService {
	private ConSupplyService supplyService = new ConSupplyService();
	/**
	 * 根据条件查询车展合同的总价
	 * @param sql
	 * @return
	 */
	public Map<String,Double> getAllCzMoney(String sql){
		double conMoney = 0;
		double billMoney = 0;
		double rmMoney = 0;
		double rebateMoney = 0;
		double actMoney = 0;
		List<TbConCz> where = TbConCzDao.where(sql);
		if(where!=null && where.size()>0){
			for (TbConCz tbConCz : where) {
				String conId = tbConCz.getCon_cz_id();
				conMoney += tbConCz.getCon_total_price();
				TbConAddcon supplyByCz = supplyService.getConSupplyByCz(conId);
				if(supplyByCz!=null){
					actMoney += supplyByCz.getExe_price();
				}
				List<TbConBill> bills = TbConBillDao.where(" CON_ID='"+conId+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> rebates = TbConRebateDao.where(" CON_ID='"+conId+"' ");
				if(bills!=null && bills.size()>0){
					for (TbConBill tbConBill : bills) {
						billMoney += tbConBill.getSal_bill();
						rmMoney += tbConBill.getRm_account();
					}
				}
				if(rebates!=null && rebates.size()>0){
					for(TbConRebate tbConRebate : rebates){
						rebateMoney += tbConRebate.getBack_actual();
					}
				}
			}
		}
		Map<String,Double> map = new HashMap<String, Double>();
		map.put("conMoney", conMoney);
		map.put("billMoney", billMoney);
		map.put("rmMoney", rmMoney);
		map.put("rebateMoney", rebateMoney);
		map.put("actMoney", actMoney);
		return map;
	}
	
	/**
	 * 根据条件查询车团合同的总价
	 * @param sql
	 * @return
	 */
	public Map<String,Double> getAllCtMoney(String sql){
		double conMoney = 0;
		double billMoney = 0;
		double rmMoney = 0;
		double rebateMoney = 0;
		double actMoney = 0;
		List<TbConCt> where = TbConCtDao.where(sql);
		if(where!=null && where.size()>0){
			for (TbConCt tbConCt : where) {
				String conId = tbConCt.getCon_ct_id();
				TbConAddcon supplyByCt = supplyService.getConSupplyByCt(conId);
				if(supplyByCt!=null){
					actMoney += supplyByCt.getExe_price();
					conMoney += supplyByCt.getCon_total_price();
				}
				List<TbConBill> bills = TbConBillDao.where(" CON_ID='"+conId+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> rebates = TbConRebateDao.where(" CON_ID='"+conId+"' ");
				if(bills!=null && bills.size()>0){
					for (TbConBill tbConBill : bills) {
						billMoney += tbConBill.getSal_bill();
						rmMoney += tbConBill.getRm_account();
					}
				}
				if(rebates!=null && rebates.size()>0){
					for(TbConRebate tbConRebate : rebates){
						rebateMoney += tbConRebate.getBack_actual();
					}
				}
			}
		}
		Map<String,Double> map = new HashMap<String, Double>();
		map.put("conMoney", conMoney);
		map.put("billMoney", billMoney);
		map.put("rmMoney", rmMoney);
		map.put("rebateMoney", rebateMoney);
		map.put("actMoney", actMoney);
		return map;
	}
	/**
	 * 根据条件查询广告合同的总价
	 * @param sql
	 * @return
	 */
	public Map<String,Double> getAllZhMoney(String sql){
		double conMoney = 0;
		double billMoney = 0;
		double rmMoney = 0;
		double rebateMoney = 0;
		double actMoney = 0;
		List<TbConZh> where = TbConZhDao.where(sql);
		if(where!=null && where.size()>0){
			for (TbConZh tbConZh : where) {
				String conId = tbConZh.getCon_zh_id();
				conMoney += tbConZh.getCon_total_price();
				List<TbConZhSub> where2 = TbConZhSubDao.where("CON_ZH_ID='"+conId+"' ");
				if(where2!=null&&where2.size()>0){
					TbConZhSub tbConZhSub = where2.get(0);
					TbConAddcon supplyByCz = supplyService.getConSupplyByZh(conId,tbConZhSub.getCon_zh_sub_id());
					if(supplyByCz!=null){
						actMoney += supplyByCz.getExe_price();
					}
				}
				List<TbConBill> bills = TbConBillDao.where(" CON_ID='"+conId+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> rebates = TbConRebateDao.where(" CON_ID='"+conId+"' ");
				if(bills!=null && bills.size()>0){
					for (TbConBill tbConBill : bills) {
						billMoney += tbConBill.getSal_bill();
						rmMoney += tbConBill.getRm_account();
					}
				}
				if(rebates!=null && rebates.size()>0){
					for(TbConRebate tbConRebate : rebates){
						rebateMoney += tbConRebate.getBack_actual();
					}
				}
			}
		}
		Map<String,Double> map = new HashMap<String, Double>();
		map.put("conMoney", conMoney);
		map.put("billMoney", billMoney);
		map.put("rmMoney", rmMoney);
		map.put("rebateMoney", rebateMoney);
		map.put("actMoney", actMoney);
		return map;
	}
}
