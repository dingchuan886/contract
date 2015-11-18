package car_beans;
import java.util.*;

public class  TbConAccount  implements java.io.Serializable{

	public String KEY = "con_s_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private int con_s_id;//主键自增长id
	private String con_id;//
	private int isback;//是否返利  0-不返利  1-返利
	private String isback_des;//返利比例 或  元
	private int plan;//执行进度 0-按期执行 1-适时调整
	private String plan_des;//进度描述 于__月份投放完毕
	private int con_type;//业务类型 0-会员  1-硬广  2-会员兼硬广  3-车展  4-车展+广告 5-团购  6-特卖惠
	private int bill_type;//开票类型 0-平开 1-高开 2-不开票
	private String bill_des;//高开描述 预计开票金额为__元
	private int back_exp;//回款预期  0-及时回款  1-预计回款
	private String back_des;//回款描述  预计__年__月可回款
	private String ben_person;//受益人
	private String phone;//电话
	private String user_id;//业务员id
	private String user_name;//业务员姓名
	private Date create;//创建时间
	private Date update;//更新时间
	private int acc_state;//合同相关说明状态 0-未提交 1待审核 2-已审核 3-驳回


	public void setCon_s_id(int con_s_id)
	{
		this.con_s_id=con_s_id;
		COLUMN_FLAG[0] = true;
	}
	public int getCon_s_id()
	{
		return con_s_id;
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
	public void setIsback(int isback)
	{
		this.isback=isback;
		COLUMN_FLAG[2] = true;
	}
	public int getIsback()
	{
		return isback;
	}
	public void setIsback_des(String isback_des)
	{
		this.isback_des=isback_des;
		COLUMN_FLAG[3] = true;
	}
	public String getIsback_des()
	{
		return isback_des;
	}
	public void setPlan(int plan)
	{
		this.plan=plan;
		COLUMN_FLAG[4] = true;
	}
	public int getPlan()
	{
		return plan;
	}
	public void setPlan_des(String plan_des)
	{
		this.plan_des=plan_des;
		COLUMN_FLAG[5] = true;
	}
	public String getPlan_des()
	{
		return plan_des;
	}
	public void setCon_type(int con_type)
	{
		this.con_type=con_type;
		COLUMN_FLAG[6] = true;
	}
	public int getCon_type()
	{
		return con_type;
	}
	public void setBill_type(int bill_type)
	{
		this.bill_type=bill_type;
		COLUMN_FLAG[7] = true;
	}
	public int getBill_type()
	{
		return bill_type;
	}
	public void setBill_des(String bill_des)
	{
		this.bill_des=bill_des;
		COLUMN_FLAG[8] = true;
	}
	public String getBill_des()
	{
		return bill_des;
	}
	public void setBack_exp(int back_exp)
	{
		this.back_exp=back_exp;
		COLUMN_FLAG[9] = true;
	}
	public int getBack_exp()
	{
		return back_exp;
	}
	public void setBack_des(String back_des)
	{
		this.back_des=back_des;
		COLUMN_FLAG[10] = true;
	}
	public String getBack_des()
	{
		return back_des;
	}
	public void setBen_person(String ben_person)
	{
		this.ben_person=ben_person;
		COLUMN_FLAG[11] = true;
	}
	public String getBen_person()
	{
		return ben_person;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
		COLUMN_FLAG[12] = true;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setUser_id(String user_id)
	{
		this.user_id=user_id;
		COLUMN_FLAG[13] = true;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_name(String user_name)
	{
		this.user_name=user_name;
		COLUMN_FLAG[14] = true;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setCreate(Date create)
	{
		this.create=create;
		COLUMN_FLAG[15] = true;
	}
	public Date getCreate()
	{
		return create;
	}
	public void setUpdate(Date update)
	{
		this.update=update;
		COLUMN_FLAG[16] = true;
	}
	public Date getUpdate()
	{
		return update;
	}
	public void setAcc_state(int acc_state)
	{
		this.acc_state=acc_state;
		COLUMN_FLAG[17] = true;
	}
	public int getAcc_state()
	{
		return acc_state;
	}
}
