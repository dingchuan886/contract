package com.poly.services;

import java.sql.ResultSet;

import car_beans.TbFinUser;
import car_daos.DBConnect;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConZhDao;

import com.poly.dao.subDaos.CheckConSubDao;

public class CheckConService {
	/*
    private int userConReject;//业务员驳回合同
	private int userBillReject;//业务员驳回开票
	private int userRebateReject;//业务员驳回返利
	private int userAccountReject;//业务员驳回相关说明
	private int userAheadAdvReject;//业务员驳回广告提前申请
	private int managerAuditCon;//经理审核合同
	private int managerAuditBill;//经理审核开票
	private int managerAuditRebate;//经理审核返利
	private int managerAuditAccount;//经理审核合同相关说明
	private int managerAuditAheadAdv;//经理审核广告提前申请
	private int flowAuditCon;//流程部审核合同
	private int flowAuditStamp;//流程部归档合同
	private int flowAuditBill;//流程部审核开票
	private int flowAuditRebate;//流程部审核返利
	private int flowAuditAheadAdv;//流程部审核广告提前申请
	private int finAuditBill;//流程部审核开票
	private int finAuditRebate;//流程部审核返利
	private int hqManagerAuditRebate;//严总审核返利
	private int userAddCon;
	*/
	private TbFinUserService userService = new TbFinUserService();
	public int getUserConReject(int conState,String userId){
		int ct = TbConCtDao.whereCount(" CON_STATE='"+conState+"' and USER_ID='"+userId+"'");
		int cz = TbConCzDao.whereCount(" CON_STATE='"+conState+"' and USER_ID='"+userId+"'");
		int zh = TbConZhDao.whereCount(" CON_STATE='"+conState+"' and USER_ID='"+userId+"'");
		return ct+cz+zh;
	}
	
	public int getUserBillReject(int billState,String userId){
		
		int i = CheckConSubDao.findUserBillReject(billState,userId);
		return i;
	}
	public int getUserRebateReject(int rebateState,String userId){
		int i = CheckConSubDao.findUserRebateReject(rebateState,userId);
		return i;
	}
	public int getUserAccountReject(int accountState,String userId){
		int i = CheckConSubDao.findUserAccountReject(accountState,userId);
		return i;
	}
	public int getUserAddCon(int conState,String userId){
		int i = CheckConSubDao.findUserAddCon(conState,userId);
		return i;
	}
	public int getUserAheadAdvReject(int advState,String userId){
		int i = CheckConSubDao.findUserAheadAdvReject(advState,userId);
		return i;
	}
	public int getManagerAuditCon(String userId,int conState){
		int i = CheckConSubDao.findManagerAuditCon(userId,conState);
		return i;
	}
	public int getManagerAuditBill(String userId,int billState){
		int i = CheckConSubDao.findManagerAuditBill(userId,billState);
		return i;
	}
	public int getManagerAuditRebate(String userId,int rebateState){
		int i = CheckConSubDao.findManagerAuditRebate(userId,rebateState);
		return i;
	}
	public int getManagerAuditAccount(String userId,int accState){
		int i = CheckConSubDao.findManagerAuditAccount(userId,accState);
		return i;
	}
	public int getManagerAuditAheadAdv(String userId,int advState){
		int i = CheckConSubDao.findManagerAuditAheadAdv(userId,advState);
		return i;
	}
	/**
	 * 流程部审核后还有下一步 合同状态1
	 * @param userId
	 * @param conState
	 * @return
	 */
	public int getFlowAuditCon(String userId,int conState){
		int i = CheckConSubDao.findManagerAuditCon(userId,conState);
		return i;
	}
	/**
	 * 流程部审核后归档 合同状态2
	 * @param userId
	 * @param conState
	 * @return
	 */
	public int getFlowAuditStamp(String userId,int conState){
		int i = CheckConSubDao.findManagerAuditCon(userId,conState);
		return i;
	}
	public int getFlowAuditBill(String userId,int billState){
		int i = CheckConSubDao.findManagerAuditBill(userId,billState);
		return i;
	}
	public int getFlowAuditRebate(String userId,int rebateState){
		int i = CheckConSubDao.findManagerAuditRebate(userId,rebateState);
		return i;
	}
	public int getFlowAuditAheadAdv(String userId,int advState){
		int i = CheckConSubDao.findManagerAuditAheadAdv(userId,advState);
		return i;
	}
	/**
	 * 开票完成标志，有回款金额，回款日期
	 * @return
	 */
	public int getFinAuditBill(String userId,int billState){
		int i = CheckConSubDao.findFinAuditBill(userId,billState);
		return i;
	}
	/**
	 * 返利完成标志 有返利日期
	 * @return
	 */
	public int getFinAuditRebate(String userId,int rebateState){
		int i = CheckConSubDao.findFinAuditRebate(userId,rebateState);
		return i;
	}
	/**
	 * 严总审核返利
	 * @param userId
	 * @param rebateState
	 * @return
	 */
	public int getHqManagerAuditRebate(String userId,int rebateState){
		int i = CheckConSubDao.findManagerAuditRebate(userId,rebateState);
		return i;
	}

	
	
}
