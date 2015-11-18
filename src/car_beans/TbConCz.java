package car_beans;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class  TbConCz  implements java.io.Serializable{

	public String KEY = "con_cz_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private String con_cz_id;//车展表id，主键
	private String cus_name;//客户名称
	private int con_type;//合同类型 0-车展 1-车展+广告
	private int cus_type;//客户类型  0-未知  1-大客户 2-区域 3-经销商
	private String cus_addr;//地址
	private String cus_tel;//客户电话
	private String cus_brand;//品牌
	private String cus_seriers;//车系
	private String seriers_name;
	private int cus_count;//数量
	private String act_addr;//活动地址
	private String act_date;//活动时间
	private double con_total_price;//合同执行总价
	private String con_total_priceUpperCase;
	private String con_content;//合同备注
	private int stamp;//0-异常 1-客户先盖章  2-公司先盖章
	private Date create;//创建时间
	private Date update;//更新时间
	private String user_name;//业务员姓名
	private int con_state;//0-等待审核  1-部门经理审核  2-流程部客户未盖章  3-流程部客户已盖章  4-驳回  5-未提交  6-已删除
	private double al_bill;//已开票金额
	private String al_billUpperCase;
	private String user_id;//业务员id
	private int re_count;//回款次数
	private double al_rebate;//已返利金额
	private int upload_id;//附件id
	private double act_price;//执行金额 合同金额-返利金额-成本
	private List<TbConZhSub> tbConZhSubs = new ArrayList<TbConZhSub>();
	
	public List<TbConZhSub> getTbConZhSubs() {
		return tbConZhSubs;
	}
	public void setTbConZhSubs(List<TbConZhSub> tbConZhSubs) {
		this.tbConZhSubs = tbConZhSubs;
	}
	public String getSeriers_name() {
		return seriers_name;
	}
	public void setSeriers_name(String seriers_name) {
		this.seriers_name = seriers_name;
	}
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
	public void setCon_cz_id(String con_cz_id)
	{
		this.con_cz_id=con_cz_id;
		COLUMN_FLAG[0] = true;
	}
	public String getCon_cz_id()
	{
		return con_cz_id;
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
	public void setCon_type(int con_type)
	{
		this.con_type=con_type;
		COLUMN_FLAG[2] = true;
	}
	public int getCon_type()
	{
		return con_type;
	}
	public void setCus_type(int cus_type)
	{
		this.cus_type=cus_type;
		COLUMN_FLAG[3] = true;
	}
	public int getCus_type()
	{
		return cus_type;
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
	public void setCus_brand(String cus_brand)
	{
		this.cus_brand=cus_brand;
		COLUMN_FLAG[6] = true;
	}
	public String getCus_brand()
	{
		return cus_brand;
	}
	public void setCus_seriers(String cus_seriers)
	{
		this.cus_seriers=cus_seriers;
		COLUMN_FLAG[7] = true;
	}
	public String getCus_seriers()
	{
		return cus_seriers;
	}
	public void setCus_count(int cus_count)
	{
		this.cus_count=cus_count;
		COLUMN_FLAG[8] = true;
	}
	public int getCus_count()
	{
		return cus_count;
	}
	public void setAct_addr(String act_addr)
	{
		this.act_addr=act_addr;
		COLUMN_FLAG[9] = true;
	}
	public String getAct_addr()
	{
		return act_addr;
	}
	public void setAct_date(String act_date)
	{
		this.act_date=act_date;
		COLUMN_FLAG[10] = true;
	}
	public String getAct_date()
	{
		return act_date;
	}
	public void setCon_total_price(double con_total_price)
	{
		this.con_total_price=con_total_price;
		COLUMN_FLAG[11] = true;
	}
	public double getCon_total_price()
	{
		return con_total_price;
	}
	public void setCon_content(String con_content)
	{
		this.con_content=con_content;
		COLUMN_FLAG[12] = true;
	}
	public String getCon_content()
	{
		return con_content;
	}
	public void setStamp(int stamp)
	{
		this.stamp=stamp;
		COLUMN_FLAG[13] = true;
	}
	public int getStamp()
	{
		return stamp;
	}
	public void setCreate(Date create)
	{
		this.create=create;
		COLUMN_FLAG[14] = true;
	}
	public Date getCreate()
	{
		return create;
	}
	public void setUpdate(Date update)
	{
		this.update=update;
		COLUMN_FLAG[15] = true;
	}
	public Date getUpdate()
	{
		return update;
	}
	public void setUser_name(String user_name)
	{
		this.user_name=user_name;
		COLUMN_FLAG[16] = true;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setCon_state(int con_state)
	{
		this.con_state=con_state;
		COLUMN_FLAG[17] = true;
	}
	public int getCon_state()
	{
		return con_state;
	}
	public void setAl_bill(double al_bill)
	{
		this.al_bill=al_bill;
		COLUMN_FLAG[18] = true;
	}
	public double getAl_bill()
	{
		return al_bill;
	}
	public void setUser_id(String user_id)
	{
		this.user_id=user_id;
		COLUMN_FLAG[19] = true;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setRe_count(int re_count)
	{
		this.re_count=re_count;
		COLUMN_FLAG[20] = true;
	}
	public int getRe_count()
	{
		return re_count;
	}
	public void setAl_rebate(double al_rebate)
	{
		this.al_rebate=al_rebate;
		COLUMN_FLAG[21] = true;
	}
	public double getAl_rebate()
	{
		return al_rebate;
	}
	public void setUpload_id(int upload_id)
	{
		this.upload_id=upload_id;
		COLUMN_FLAG[22] = true;
	}
	public int getUpload_id()
	{
		return upload_id;
	}
	public void setAct_price(double act_price)
	{
		this.act_price=act_price;
		COLUMN_FLAG[23] = true;
	}
	public double getAct_price()
	{
		return act_price;
	}
}
