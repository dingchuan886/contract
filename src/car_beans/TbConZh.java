package car_beans;
import java.util.*;

public class  TbConZh  implements java.io.Serializable{

	public String KEY = "con_zh_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private String con_zh_id;//广告表id，主键
	private String cus_name;//客户名称
	private int cus_type;//客户类型  0-未知  1-大客户 2-区域 3-经销商
	private int con_type;//0-会员  1-硬广  2-会员+硬广
	private String cus_addr;//地址
	private String cus_tel;//客户电话
	private double con_total_price;//合同执行总价
	private String con_total_priceUpperCase;
	private int material;//物料 0-无 1-有
	private int stamp;//0-异常 1-客户先盖章  2-公司先盖章
	private Date create;//创建时间
	private Date update;//更新时间
	private String user_name;//业务员姓名
	private int con_state;//0-等待审核  1-部门经理审核  2-流程部客户未盖章  3-流程部客户已盖章  4-驳回 5-未提交 6-已删除
	private double al_bill;//已开票金额
	private String user_id;//业务员id
	private String con_zh_sub;//广告表包含的子表
	private int issave;//是否排期  0-不排期 1-排期
	private int re_count;//回款次数
	private double al_rebate;//已返利金额
	private String al_billUpperCase;
	private List<TbConZhSub> tbConZhSubs = new ArrayList<TbConZhSub>();
	private int upload_id;//附件id
	private double act_price;//执行金额 合同金额-返利金额-成本


	public String getCon_total_priceUpperCase() {
		return con_total_priceUpperCase;
	}
	public void setCon_total_priceUpperCase(String con_total_priceUpperCase) {
		this.con_total_priceUpperCase = con_total_priceUpperCase;
	}
	public String getAl_billUpperCase() {
		return al_billUpperCase;
	}
	public void setAl_billUpperCase(String al_billUpperCase) {
		this.al_billUpperCase = al_billUpperCase;
	}
	public List<TbConZhSub> getTbConZhSubs() {
		return tbConZhSubs;
	}
	public void setTbConZhSubs(List<TbConZhSub> tbConZhSubs) {
		this.tbConZhSubs = tbConZhSubs;
	}
	public void setCon_zh_id(String con_zh_id)
	{
		this.con_zh_id=con_zh_id;
		COLUMN_FLAG[0] = true;
	}
	public String getCon_zh_id()
	{
		return con_zh_id;
	}
	public void setCus_name(String cus_name)
	{
		this.cus_name=cus_name;
		COLUMN_FLAG[1] = true;
	}
	public String getCus_name()
	{
		return cus_name;
	}
	public void setCus_type(int cus_type)
	{
		this.cus_type=cus_type;
		COLUMN_FLAG[2] = true;
	}
	public int getCus_type()
	{
		return cus_type;
	}
	public void setCon_type(int con_type)
	{
		this.con_type=con_type;
		COLUMN_FLAG[3] = true;
	}
	public int getCon_type()
	{
		return con_type;
	}
	public void setCus_addr(String cus_addr)
	{
		this.cus_addr=cus_addr;
		COLUMN_FLAG[4] = true;
	}
	public String getCus_addr()
	{
		return cus_addr;
	}
	public void setCus_tel(String cus_tel)
	{
		this.cus_tel=cus_tel;
		COLUMN_FLAG[5] = true;
	}
	public String getCus_tel()
	{
		return cus_tel;
	}
	public void setCon_total_price(double con_total_price)
	{
		this.con_total_price=con_total_price;
		COLUMN_FLAG[6] = true;
	}
	public double getCon_total_price()
	{
		return con_total_price;
	}
	public void setMaterial(int material)
	{
		this.material=material;
		COLUMN_FLAG[7] = true;
	}
	public int getMaterial()
	{
		return material;
	}
	public void setStamp(int stamp)
	{
		this.stamp=stamp;
		COLUMN_FLAG[8] = true;
	}
	public int getStamp()
	{
		return stamp;
	}
	public void setCreate(Date create)
	{
		this.create=create;
		COLUMN_FLAG[9] = true;
	}
	public Date getCreate()
	{
		return create;
	}
	public void setUpdate(Date update)
	{
		this.update=update;
		COLUMN_FLAG[10] = true;
	}
	public Date getUpdate()
	{
		return update;
	}
	public void setUser_name(String user_name)
	{
		this.user_name=user_name;
		COLUMN_FLAG[11] = true;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setCon_state(int con_state)
	{
		this.con_state=con_state;
		COLUMN_FLAG[12] = true;
	}
	public int getCon_state()
	{
		return con_state;
	}
	public void setAl_bill(double al_bill)
	{
		this.al_bill=al_bill;
		COLUMN_FLAG[13] = true;
	}
	public double getAl_bill()
	{
		return al_bill;
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
	public void setCon_zh_sub(String con_zh_sub)
	{
		this.con_zh_sub=con_zh_sub;
		COLUMN_FLAG[15] = true;
	}
	public String getCon_zh_sub()
	{
		return con_zh_sub;
	}
	public void setIssave(int issave)
	{
		this.issave=issave;
		COLUMN_FLAG[16] = true;
	}
	public int getIssave()
	{
		return issave;
	}
	public void setRe_count(int re_count)
	{
		this.re_count=re_count;
		COLUMN_FLAG[17] = true;
	}
	public int getRe_count()
	{
		return re_count;
	}
	public void setAl_rebate(double al_rebate)
	{
		this.al_rebate=al_rebate;
		COLUMN_FLAG[18] = true;
	}
	public double getAl_rebate()
	{
		return al_rebate;
	}
	public void setUpload_id(int upload_id)
	{
		this.upload_id=upload_id;
		COLUMN_FLAG[19] = true;
	}
	public int getUpload_id()
	{
		return upload_id;
	}
	public void setAct_price(double act_price)
	{
		this.act_price=act_price;
		COLUMN_FLAG[20] = true;
	}
	public double getAct_price()
	{
		return act_price;
	}
}
