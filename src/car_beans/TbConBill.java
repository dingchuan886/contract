package car_beans;
import java.util.*;

public class  TbConBill  implements java.io.Serializable{

	public String KEY = "bill_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private int bill_id;//主键自增长id
	private String con_id;//合同id
	private String cus_name;//客户名称
	private double al_bill;//已开票金额
	private double sal_bill;//申请开票金额
	private int bill_type;//0-广告  1-广告发布费  2-活动服务费  3-其他
	private double bill_money;//发票金额
	private int state;//开票类型 0-平开  1-高开
	private double bill_high;//高开金额
	private String duty_para;//税号
	private String bank_name;//开户银行
	private String bank_account;//银行账户
	private String bank_addr;//公司地址
	private String phone;//联系电话
	private String user_id;//业务员id
	private String user_name;//业务员姓名
	private Date create;//创建时间
	private Date update;//更新时间
	private int bill_state;//0-未审核  1-经理审核  2-流程部审核  3-驳回 4-未提交  5-已删除
	private String bill_code;//发票号
	private Date rm_date;//回款日期
	private double rm_account;//回款金额
	private String rm_user;//回款人
	private String bill_content;//备注


	public void setBill_id(int bill_id)
	{
		this.bill_id=bill_id;
		COLUMN_FLAG[0] = true;
	}
	public int getBill_id()
	{
		return bill_id;
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
	public void setCus_name(String cus_name)
	{
		this.cus_name=cus_name;
		COLUMN_FLAG[2] = true;
	}
	public String getCus_name()
	{
		return cus_name;
	}
	public void setAl_bill(double al_bill)
	{
		this.al_bill=al_bill;
		COLUMN_FLAG[3] = true;
	}
	public double getAl_bill()
	{
		return al_bill;
	}
	public void setSal_bill(double sal_bill)
	{
		this.sal_bill=sal_bill;
		COLUMN_FLAG[4] = true;
	}
	public double getSal_bill()
	{
		return sal_bill;
	}
	public void setBill_type(int bill_type)
	{
		this.bill_type=bill_type;
		COLUMN_FLAG[5] = true;
	}
	public int getBill_type()
	{
		return bill_type;
	}
	public void setBill_money(double bill_money)
	{
		this.bill_money=bill_money;
		COLUMN_FLAG[6] = true;
	}
	public double getBill_money()
	{
		return bill_money;
	}
	public void setState(int state)
	{
		this.state=state;
		COLUMN_FLAG[7] = true;
	}
	public int getState()
	{
		return state;
	}
	public void setBill_high(double bill_high)
	{
		this.bill_high=bill_high;
		COLUMN_FLAG[8] = true;
	}
	public double getBill_high()
	{
		return bill_high;
	}
	public void setDuty_para(String duty_para)
	{
		this.duty_para=duty_para;
		COLUMN_FLAG[9] = true;
	}
	public String getDuty_para()
	{
		return duty_para;
	}
	public void setBank_name(String bank_name)
	{
		this.bank_name=bank_name;
		COLUMN_FLAG[10] = true;
	}
	public String getBank_name()
	{
		return bank_name;
	}
	public void setBank_account(String bank_account)
	{
		this.bank_account=bank_account;
		COLUMN_FLAG[11] = true;
	}
	public String getBank_account()
	{
		return bank_account;
	}
	public void setBank_addr(String bank_addr)
	{
		this.bank_addr=bank_addr;
		COLUMN_FLAG[12] = true;
	}
	public String getBank_addr()
	{
		return bank_addr;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
		COLUMN_FLAG[13] = true;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setUser_id(String user_id)
	{
		this.user_id=user_id;
		COLUMN_FLAG[14] = true;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_name(String user_name)
	{
		this.user_name=user_name;
		COLUMN_FLAG[15] = true;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setCreate(Date create)
	{
		this.create=create;
		COLUMN_FLAG[16] = true;
	}
	public Date getCreate()
	{
		return create;
	}
	public void setUpdate(Date update)
	{
		this.update=update;
		COLUMN_FLAG[17] = true;
	}
	public Date getUpdate()
	{
		return update;
	}
	public void setBill_state(int bill_state)
	{
		this.bill_state=bill_state;
		COLUMN_FLAG[18] = true;
	}
	public int getBill_state()
	{
		return bill_state;
	}
	public void setBill_code(String bill_code)
	{
		this.bill_code=bill_code;
		COLUMN_FLAG[19] = true;
	}
	public String getBill_code()
	{
		return bill_code;
	}
	public void setRm_date(Date rm_date)
	{
		this.rm_date=rm_date;
		COLUMN_FLAG[20] = true;
	}
	public Date getRm_date()
	{
		return rm_date;
	}
	public void setRm_account(double rm_account)
	{
		this.rm_account=rm_account;
		COLUMN_FLAG[21] = true;
	}
	public double getRm_account()
	{
		return rm_account;
	}
	public void setRm_user(String rm_user)
	{
		this.rm_user=rm_user;
		COLUMN_FLAG[22] = true;
	}
	public String getRm_user()
	{
		return rm_user;
	}
	public void setBill_content(String bill_content)
	{
		this.bill_content=bill_content;
		COLUMN_FLAG[23] = true;
	}
	public String getBill_content()
	{
		return bill_content;
	}
}
