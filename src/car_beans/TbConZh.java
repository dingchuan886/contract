package car_beans;
import java.util.*;

public class  TbConZh  implements java.io.Serializable{

	public String KEY = "con_zh_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private String con_zh_id;//����id������
	private String cus_name;//�ͻ�����
	private int cus_type;//�ͻ�����  0-δ֪  1-��ͻ� 2-���� 3-������
	private int con_type;//0-��Ա  1-Ӳ��  2-��Ա+Ӳ��
	private String cus_addr;//��ַ
	private String cus_tel;//�ͻ��绰
	private double con_total_price;//��ִͬ���ܼ�
	private String con_total_priceUpperCase;
	private int material;//���� 0-�� 1-��
	private int stamp;//0-�쳣 1-�ͻ��ȸ���  2-��˾�ȸ���
	private Date create;//����ʱ��
	private Date update;//����ʱ��
	private String user_name;//ҵ��Ա����
	private int con_state;//0-�ȴ����  1-���ž������  2-���̲��ͻ�δ����  3-���̲��ͻ��Ѹ���  4-���� 5-δ�ύ 6-��ɾ��
	private double al_bill;//�ѿ�Ʊ���
	private String user_id;//ҵ��Աid
	private String con_zh_sub;//����������ӱ�
	private int issave;//�Ƿ�����  0-������ 1-����
	private int re_count;//�ؿ����
	private double al_rebate;//�ѷ������
	private String al_billUpperCase;
	private List<TbConZhSub> tbConZhSubs = new ArrayList<TbConZhSub>();
	private int upload_id;//����id
	private double act_price;//ִ�н�� ��ͬ���-�������-�ɱ�


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
