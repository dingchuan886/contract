package car_beans;
import java.util.*;

public class  TbFinCheckInfoCopy  implements java.io.Serializable{

	public String KEY = "evidence_code";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private String evidence_code;//凭证号
	private String user_code;//填单人编号
	private String company_code;//报账公司代号
	private String org_code;//站号
	private String type;//单据类型
	private Date add_time;//填单日期
	private String content;//付款事由
	private int payment_type;//付款方式 0-现金 -1银行转帐
	private String payee_name;//收款人
	private String bank_name;//开户银行
	private String acount;//银行账号
	private double money;//合计
	private String moneyupcase;//合计大写
	private String accessory;//附件
	private String handperson;//经手人
	private String depart_manager;//部门经理
	private String finane;//财务
	private String manager;//经理
	private String hq_finane;//总部财务
	private String hq_manager;//总经理
	private String teller;//出纳
	private String nextcheck;//待审核人
	private int state;//审核状态 -0未审核-1审核中-2审核完成-3打回-4取消
	private int isback;//是否是在申请 -0 否 -1 是


	public void setEvidence_code(String evidence_code)
	{
		this.evidence_code=evidence_code;
		COLUMN_FLAG[0] = true;
	}
	public String getEvidence_code()
	{
		return evidence_code;
	}
	public void setUser_code(String user_code)
	{
		this.user_code=user_code;
		COLUMN_FLAG[1] = true;
	}
	public String getUser_code()
	{
		return user_code;
	}
	public void setCompany_code(String company_code)
	{
		this.company_code=company_code;
		COLUMN_FLAG[2] = true;
	}
	public String getCompany_code()
	{
		return company_code;
	}
	public void setOrg_code(String org_code)
	{
		this.org_code=org_code;
		COLUMN_FLAG[3] = true;
	}
	public String getOrg_code()
	{
		return org_code;
	}
	public void setType(String type)
	{
		this.type=type;
		COLUMN_FLAG[4] = true;
	}
	public String getType()
	{
		return type;
	}
	public void setAdd_time(Date add_time)
	{
		this.add_time=add_time;
		COLUMN_FLAG[5] = true;
	}
	public Date getAdd_time()
	{
		return add_time;
	}
	public void setContent(String content)
	{
		this.content=content;
		COLUMN_FLAG[6] = true;
	}
	public String getContent()
	{
		return content;
	}
	public void setPayment_type(int payment_type)
	{
		this.payment_type=payment_type;
		COLUMN_FLAG[7] = true;
	}
	public int getPayment_type()
	{
		return payment_type;
	}
	public void setPayee_name(String payee_name)
	{
		this.payee_name=payee_name;
		COLUMN_FLAG[8] = true;
	}
	public String getPayee_name()
	{
		return payee_name;
	}
	public void setBank_name(String bank_name)
	{
		this.bank_name=bank_name;
		COLUMN_FLAG[9] = true;
	}
	public String getBank_name()
	{
		return bank_name;
	}
	public void setAcount(String acount)
	{
		this.acount=acount;
		COLUMN_FLAG[10] = true;
	}
	public String getAcount()
	{
		return acount;
	}
	public void setMoney(double money)
	{
		this.money=money;
		COLUMN_FLAG[11] = true;
	}
	public double getMoney()
	{
		return money;
	}
	public void setMoneyupcase(String moneyupcase)
	{
		this.moneyupcase=moneyupcase;
		COLUMN_FLAG[12] = true;
	}
	public String getMoneyupcase()
	{
		return moneyupcase;
	}
	public void setAccessory(String accessory)
	{
		this.accessory=accessory;
		COLUMN_FLAG[13] = true;
	}
	public String getAccessory()
	{
		return accessory;
	}
	public void setHandperson(String handperson)
	{
		this.handperson=handperson;
		COLUMN_FLAG[14] = true;
	}
	public String getHandperson()
	{
		return handperson;
	}
	public void setDepart_manager(String depart_manager)
	{
		this.depart_manager=depart_manager;
		COLUMN_FLAG[15] = true;
	}
	public String getDepart_manager()
	{
		return depart_manager;
	}
	public void setFinane(String finane)
	{
		this.finane=finane;
		COLUMN_FLAG[16] = true;
	}
	public String getFinane()
	{
		return finane;
	}
	public void setManager(String manager)
	{
		this.manager=manager;
		COLUMN_FLAG[17] = true;
	}
	public String getManager()
	{
		return manager;
	}
	public void setHq_finane(String hq_finane)
	{
		this.hq_finane=hq_finane;
		COLUMN_FLAG[18] = true;
	}
	public String getHq_finane()
	{
		return hq_finane;
	}
	public void setHq_manager(String hq_manager)
	{
		this.hq_manager=hq_manager;
		COLUMN_FLAG[19] = true;
	}
	public String getHq_manager()
	{
		return hq_manager;
	}
	public void setTeller(String teller)
	{
		this.teller=teller;
		COLUMN_FLAG[20] = true;
	}
	public String getTeller()
	{
		return teller;
	}
	public void setNextcheck(String nextcheck)
	{
		this.nextcheck=nextcheck;
		COLUMN_FLAG[21] = true;
	}
	public String getNextcheck()
	{
		return nextcheck;
	}
	public void setState(int state)
	{
		this.state=state;
		COLUMN_FLAG[22] = true;
	}
	public int getState()
	{
		return state;
	}
	public void setIsback(int isback)
	{
		this.isback=isback;
		COLUMN_FLAG[23] = true;
	}
	public int getIsback()
	{
		return isback;
	}
}
