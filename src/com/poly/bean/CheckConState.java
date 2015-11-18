package com.poly.bean;

public class CheckConState {
	private int userConReject;// 业务员驳回合同
	private int userBillReject;// 业务员驳回开票
	private int userRebateReject;// 业务员驳回返利
	// private int userAccountReject;//业务员驳回相关说明
	private int userAddCon;//业务员需要填写的合同补充
	private int userAheadAdvReject;// 业务员驳回广告提前申请
	private int managerAuditCon;// 经理审核合同
	private int managerAuditBill;// 经理审核开票
	private int managerAuditRebate;// 经理审核返利
	// private int managerAuditAccount;//经理审核合同相关说明
	private int managerAuditAheadAdv;// 经理审核广告提前申请
	private int flowAuditCon;// 流程部审核合同
	private int flowAuditStamp;// 流程部归档合同
	private int flowAuditBill;// 流程部审核开票
	private int flowAuditRebate;// 流程部审核返利
	private int flowAuditAheadAdv;// 流程部审核广告提前申请
	private int finAuditBill;// 财务部审核开票
	private int finAuditRebate;// 财务部审核返利
	private int hqManagerAuditRebate;// 严总审核返利
	private int areaAuditCon; //区域经理审核合同
	private int areaAuditBill;//区域经理审核开票
	private int areaAuditRebate;//区域经理审核返利

	public CheckConState() {
		super();
	}

	public CheckConState(int userConReject, int userBillReject,
			int userRebateReject, int userAddCon,int userAheadAdvReject, int managerAuditCon,
			int managerAuditBill, int managerAuditRebate,
			int managerAuditAheadAdv, int flowAuditCon, int flowAuditStamp,
			int flowAuditBill, int flowAuditRebate, int flowAuditAheadAdv,
			int finAuditBill, int finAuditRebate, int hqManagerAuditRebate,int areaAuditCon,int areaAuditBill,int areaAuditRebate) {
		super();
		this.userConReject = userConReject;
		this.userBillReject = userBillReject;
		this.userRebateReject = userRebateReject;
		// this.userAccountReject = userAccountReject;
		this.userAddCon = userAddCon;
		this.userAheadAdvReject = userAheadAdvReject;
		this.managerAuditCon = managerAuditCon;
		this.managerAuditBill = managerAuditBill;
		this.managerAuditRebate = managerAuditRebate;
		// this.managerAuditAccount = managerAuditAccount;
		this.managerAuditAheadAdv = managerAuditAheadAdv;
		this.flowAuditCon = flowAuditCon;
		this.flowAuditStamp = flowAuditStamp;
		this.flowAuditBill = flowAuditBill;
		this.flowAuditRebate = flowAuditRebate;
		this.flowAuditAheadAdv = flowAuditAheadAdv;
		this.finAuditBill = finAuditBill;
		this.finAuditRebate = finAuditRebate;
		this.hqManagerAuditRebate = hqManagerAuditRebate;
		this.areaAuditCon = areaAuditCon;
		this.areaAuditBill = areaAuditBill;
		this.areaAuditRebate = areaAuditRebate;
	}
	
	public int getAreaAuditCon() {
		return areaAuditCon;
	}

	public void setAreaAuditCon(int areaAuditCon) {
		this.areaAuditCon = areaAuditCon;
	}

	public int getAreaAuditBill() {
		return areaAuditBill;
	}

	public void setAreaAuditBill(int areaAuditBill) {
		this.areaAuditBill = areaAuditBill;
	}

	public int getAreaAuditRebate() {
		return areaAuditRebate;
	}

	public void setAreaAuditRebate(int areaAuditRebate) {
		this.areaAuditRebate = areaAuditRebate;
	}

	public int getUserAddCon() {
		return userAddCon;
	}

	public void setUserAddCon(int userAddCon) {
		this.userAddCon = userAddCon;
	}

	public int getUserConReject() {
		return userConReject;
	}

	public void setUserConReject(int userConReject) {
		this.userConReject = userConReject;
	}

	public int getUserBillReject() {
		return userBillReject;
	}

	public void setUserBillReject(int userBillReject) {
		this.userBillReject = userBillReject;
	}

	public int getUserRebateReject() {
		return userRebateReject;
	}

	public void setUserRebateReject(int userRebateReject) {
		this.userRebateReject = userRebateReject;
	}


	public int getUserAheadAdvReject() {
		return userAheadAdvReject;
	}

	public void setUserAheadAdvReject(int userAheadAdvReject) {
		this.userAheadAdvReject = userAheadAdvReject;
	}

	public int getManagerAuditCon() {
		return managerAuditCon;
	}

	public void setManagerAuditCon(int managerAuditCon) {
		this.managerAuditCon = managerAuditCon;
	}

	public int getManagerAuditBill() {
		return managerAuditBill;
	}

	public void setManagerAuditBill(int managerAuditBill) {
		this.managerAuditBill = managerAuditBill;
	}

	public int getManagerAuditRebate() {
		return managerAuditRebate;
	}

	public void setManagerAuditRebate(int managerAuditRebate) {
		this.managerAuditRebate = managerAuditRebate;
	}


	public int getManagerAuditAheadAdv() {
		return managerAuditAheadAdv;
	}

	public void setManagerAuditAheadAdv(int managerAuditAheadAdv) {
		this.managerAuditAheadAdv = managerAuditAheadAdv;
	}

	public int getFlowAuditCon() {
		return flowAuditCon;
	}

	public void setFlowAuditCon(int flowAuditCon) {
		this.flowAuditCon = flowAuditCon;
	}

	public int getFlowAuditStamp() {
		return flowAuditStamp;
	}

	public void setFlowAuditStamp(int flowAuditStamp) {
		this.flowAuditStamp = flowAuditStamp;
	}

	public int getFlowAuditBill() {
		return flowAuditBill;
	}

	public void setFlowAuditBill(int flowAuditBill) {
		this.flowAuditBill = flowAuditBill;
	}

	public int getFlowAuditRebate() {
		return flowAuditRebate;
	}

	public void setFlowAuditRebate(int flowAuditRebate) {
		this.flowAuditRebate = flowAuditRebate;
	}

	public int getFlowAuditAheadAdv() {
		return flowAuditAheadAdv;
	}

	public void setFlowAuditAheadAdv(int flowAuditAheadAdv) {
		this.flowAuditAheadAdv = flowAuditAheadAdv;
	}

	public int getFinAuditBill() {
		return finAuditBill;
	}

	public void setFinAuditBill(int finAuditBill) {
		this.finAuditBill = finAuditBill;
	}

	public int getFinAuditRebate() {
		return finAuditRebate;
	}

	public void setFinAuditRebate(int finAuditRebate) {
		this.finAuditRebate = finAuditRebate;
	}

	public int getHqManagerAuditRebate() {
		return hqManagerAuditRebate;
	}

	public void setHqManagerAuditRebate(int hqManagerAuditRebate) {
		this.hqManagerAuditRebate = hqManagerAuditRebate;
	}

}
