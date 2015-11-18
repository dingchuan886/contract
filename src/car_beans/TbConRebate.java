package car_beans;
import java.util.*;

public class  TbConRebate  implements java.io.Serializable{

	public String KEY = "back_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private int back_id;//主键自增长id
	private String con_id;//合同号
	private String user_id;//业务员id
	private String user_name;//业务员姓名
	private String cus_name;//客户名称
	private String cus_s_id;//
	private String back_des;//返利具体内容跟明细金额
	private double con_high;//其中高开
	private double al_back;//以返利金额
	private double this_back;//本次返利金额
	private double deduct;//扣除开票税点
	private double back;//返还
	private double back_actual;//实际返利
	private Date create;//创建时间
	private Date update;//更新时间
	private int rebate_state;//返利审核状态 0-未审核 1-经理审核 2-流程审核 3-总经理审核 4-驳回 5-未提交 6-已删除 7-区域经理审核
	private String rebate_time;//返利时间
	private String acount;//银行账户
	private String payee_name;//收款人
	private String bank_name;//开户银行名称


	public void setBack_id(int back_id)
	{
		this.back_id=back_id;
		COLUMN_FLAG[0] = true;
	}
	public int getBack_id()
	{
		return back_id;
	}
	public void setCon_id(String con_id)
	{
		this.con_id=con_id;
		COLUMN_FLAG[1] = true;
	}
	public String getCon_id()
	{
		return con_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id=user_id;
		COLUMN_FLAG[2] = true;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_name(String user_name)
	{
		this.user_name=user_name;
		COLUMN_FLAG[3] = true;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setCus_name(String cus_name)
	{
		this.cus_name=cus_name;
		COLUMN_FLAG[4] = true;
	}
	public String getCus_name()
	{
		return cus_name;
	}
	public void setCus_s_id(String cus_s_id)
	{
		this.cus_s_id=cus_s_id;
		COLUMN_FLAG[5] = true;
	}
	public String getCus_s_id()
	{
		return cus_s_id;
	}
	public void setBack_des(String back_des)
	{
		this.back_des=back_des;
		COLUMN_FLAG[6] = true;
	}
	public String getBack_des()
	{
		return back_des;
	}
	public void setCon_high(double con_high)
	{
		this.con_high=con_high;
		COLUMN_FLAG[7] = true;
	}
	public double getCon_high()
	{
		return con_high;
	}
	public void setAl_back(double al_back)
	{
		this.al_back=al_back;
		COLUMN_FLAG[8] = true;
	}
	public double getAl_back()
	{
		return al_back;
	}
	public void setThis_back(double this_back)
	{
		this.this_back=this_back;
		COLUMN_FLAG[9] = true;
	}
	public double getThis_back()
	{
		return this_back;
	}
	public void setDeduct(double deduct)
	{
		this.deduct=deduct;
		COLUMN_FLAG[10] = true;
	}
	public double getDeduct()
	{
		return deduct;
	}
	public void setBack(double back)
	{
		this.back=back;
		COLUMN_FLAG[11] = true;
	}
	public double getBack()
	{
		return back;
	}
	public void setBack_actual(double back_actual)
	{
		this.back_actual=back_actual;
		COLUMN_FLAG[12] = true;
	}
	public double getBack_actual()
	{
		return back_actual;
	}
	public void setCreate(Date create)
	{
		this.create=create;
		COLUMN_FLAG[13] = true;
	}
	public Date getCreate()
	{
		return create;
	}
	public void setUpdate(Date update)
	{
		this.update=update;
		COLUMN_FLAG[14] = true;
	}
	public Date getUpdate()
	{
		return update;
	}
	public void setRebate_state(int rebate_state)
	{
		this.rebate_state=rebate_state;
		COLUMN_FLAG[15] = true;
	}
	public int getRebate_state()
	{
		return rebate_state;
	}
	public void setRebate_time(String rebate_time)
	{
		this.rebate_time=rebate_time;
		COLUMN_FLAG[16] = true;
	}
	public String getRebate_time()
	{
		return rebate_time;
	}
	public void setAcount(String acount)
	{
		this.acount=acount;
		COLUMN_FLAG[17] = true;
	}
	public String getAcount()
	{
		return acount;
	}
	public void setPayee_name(String payee_name)
	{
		this.payee_name=payee_name;
		COLUMN_FLAG[18] = true;
	}
	public String getPayee_name()
	{
		return payee_name;
	}
	public void setBank_name(String bank_name)
	{
		this.bank_name=bank_name;
		COLUMN_FLAG[19] = true;
	}
	public String getBank_name()
	{
		return bank_name;
	}
}
