package com.poly.bean;

public class DeptPageCondition {
	private String user_name;
	private String brand;
	private String con_month;//签单月份
	private String back;	//是否回款
	private String rebate;  //是否返利
	private String save;    //是否归档
	private String cus_type;//客户类别
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCon_month() {
		return con_month;
	}
	public void setCon_month(String con_month) {
		this.con_month = con_month;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public String getRebate() {
		return rebate;
	}
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	public String getSave() {
		return save;
	}
	public void setSave(String save) {
		this.save = save;
	}
	public String getCus_type() {
		return cus_type;
	}
	public void setCus_type(String cus_type) {
		this.cus_type = cus_type;
	}
	
	
}
