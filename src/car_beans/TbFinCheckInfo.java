package car_beans;
import java.util.*;

public class  TbFinCheckInfo{

	public String KEY = "evidence_code";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private String evidence_code;//ƾ֤��
	private String user_code;//��˱��
	private String company_code;//���˹�˾����
	private String org_code;//վ��
	private String type;//��������
	private Date add_time;//�����
	private String content;//��������
	private int payment_type;//���ʽ 0-�ֽ� -1����ת��
	private String payee_name;//�տ���
	private String bank_name;//��������
	private String acount;//�����˺�
	private double money;//�ϼ�
	private String moneyupcase;//�ϼƴ�д
	private String accessory;//����
	private String handperson;//������
	private String depart_manager;//���ž���
	private String finane;//����
	private String manager;//����
	/*
	 * ���������쵼
	 */
	private String disCode;//�����쵼ID
	private String hq_finane;//�ܲ�����
	private String hq_manager;//�ܾ���
	private String teller;//����
	private String nextcheck;//�������
	private int state;//���״̬ -0δ���-1�����-2������-3���-4ȡ��
	private int isback;//�Ƿ��������� -0 �� -1 ��
	private String note;//���ע


	public String getDisCode() {
		return disCode;
	}
	public void setDisCode(String disCode) {
		COLUMN_FLAG[25] = true;
		this.disCode = disCode;
	}
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
	public void setNote(String note)
	{
		this.note=note;
		COLUMN_FLAG[24] = true;
	}
	public String getNote()
	{
		return note;
	}
}
