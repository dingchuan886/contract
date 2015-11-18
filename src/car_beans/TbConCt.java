package car_beans;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class  TbConCt  implements java.io.Serializable{

	public String KEY = "con_ct_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private String con_ct_id;//���ű�id������
	private int cus_type;//�ͻ�����  0-δ֪  1-��ͻ� 2-���� 3-������
	private String cus_name;//�ͻ�����
	private String cus_addr;//��ַ
	private String cus_tel;//�ͻ��绰
	private String cus_brand;//Ʒ��
	private String cus_seriers;//��ϵ
	private String seriers_name;
	private int cus_count;//��̨����
	private String media;//ý��
	private int bus_type;//ҵ������  0-���� 1-�Ź� 2-������
	private String act_addr;//���ַ
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date act_date;//�ʱ��
	private String total_price;//�ϼ��ܼ�
	private String total_priceUpperCase;
	private double con_total_price;//��ִͬ���ܼ�
	private String con_total_priceUpperCase;
	private String con_content;//��ͬ��ע
	private int stamp;//0-�쳣 1-�ͻ��ȸ���  2-��˾�ȸ���
	private Date create;//����ʱ��
	private Date update;//����ʱ��
	private String user_name;//ҵ��Ա����
	private int con_state;//0-�ȴ����  1-���ž������  2-���̲��ͻ�δ����  3-���̲��ͻ��Ѹ���  4-���� 5-δ�ύ 6-��ɾ��
	private double al_bill;//�ѿ�Ʊ���
	private String al_billUpperCase;
	private String user_id;//ҵ��Աid
	private int re_count;//�ؿ����
	private double al_rebate;//�ѷ������
	private int upload_id;//����id
	private double act_price;//ִ�н�� ��ͬ���-�������-�ɱ�

	public String getSeriers_name() {
		return seriers_name;
	}
	public void setSeriers_name(String seriers_name) {
		this.seriers_name = seriers_name;
	}
	public String getTotal_priceUpperCase() {
		return total_priceUpperCase;
	}
	public void setTotal_priceUpperCase(String total_priceUpperCase) {
		this.total_priceUpperCase = total_priceUpperCase;
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
	public void setCon_ct_id(String con_ct_id)
	{
		this.con_ct_id=con_ct_id;
		COLUMN_FLAG[0] = true;
	}
	public String getCon_ct_id()
	{
		return con_ct_id;
	}
	public void setCus_type(int cus_type)
	{
		this.cus_type=cus_type;
		COLUMN_FLAG[1] = true;
	}
	public int getCus_type()
	{
		return cus_type;
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
	public void setCus_addr(String cus_addr)
	{
		this.cus_addr=cus_addr;
		COLUMN_FLAG[3] = true;
	}
	public String getCus_addr()
	{
		return cus_addr;
	}
	public void setCus_tel(String cus_tel)
	{
		this.cus_tel=cus_tel;
		COLUMN_FLAG[4] = true;
	}
	public String getCus_tel()
	{
		return cus_tel;
	}
	public void setCus_brand(String cus_brand)
	{
		this.cus_brand=cus_brand;
		COLUMN_FLAG[5] = true;
	}
	public String getCus_brand()
	{
		return cus_brand;
	}
	public void setCus_seriers(String cus_seriers)
	{
		this.cus_seriers=cus_seriers;
		COLUMN_FLAG[6] = true;
	}
	public String getCus_seriers()
	{
		return cus_seriers;
	}
	public void setCus_count(int cus_count)
	{
		this.cus_count=cus_count;
		COLUMN_FLAG[7] = true;
	}
	public int getCus_count()
	{
		return cus_count;
	}
	public void setMedia(String media)
	{
		this.media=media;
		COLUMN_FLAG[8] = true;
	}
	public String getMedia()
	{
		return media;
	}
	public void setBus_type(int bus_type)
	{
		this.bus_type=bus_type;
		COLUMN_FLAG[9] = true;
	}
	public int getBus_type()
	{
		return bus_type;
	}
	public void setAct_addr(String act_addr)
	{
		this.act_addr=act_addr;
		COLUMN_FLAG[10] = true;
	}
	public String getAct_addr()
	{
		return act_addr;
	}
	public void setAct_date(Date act_date)
	{
		this.act_date=act_date;
		COLUMN_FLAG[11] = true;
	}
	public Date getAct_date()
	{
		return act_date;
	}
	public void setTotal_price(String total_price)
	{
		this.total_price=total_price;
		COLUMN_FLAG[12] = true;
	}
	public String getTotal_price()
	{
		return total_price;
	}
	public void setCon_total_price(double con_total_price)
	{
		this.con_total_price=con_total_price;
		COLUMN_FLAG[13] = true;
	}
	public double getCon_total_price()
	{
		return con_total_price;
	}
	public void setCon_content(String con_content)
	{
		this.con_content=con_content;
		COLUMN_FLAG[14] = true;
	}
	public String getCon_content()
	{
		return con_content;
	}
	public void setStamp(int stamp)
	{
		this.stamp=stamp;
		COLUMN_FLAG[15] = true;
	}
	public int getStamp()
	{
		return stamp;
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
	public void setUser_name(String user_name)
	{
		this.user_name=user_name;
		COLUMN_FLAG[18] = true;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setCon_state(int con_state)
	{
		this.con_state=con_state;
		COLUMN_FLAG[19] = true;
	}
	public int getCon_state()
	{
		return con_state;
	}
	public void setAl_bill(double al_bill)
	{
		this.al_bill=al_bill;
		COLUMN_FLAG[20] = true;
	}
	public double getAl_bill()
	{
		return al_bill;
	}
	public void setUser_id(String user_id)
	{
		this.user_id=user_id;
		COLUMN_FLAG[21] = true;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setRe_count(int re_count)
	{
		this.re_count=re_count;
		COLUMN_FLAG[22] = true;
	}
	public int getRe_count()
	{
		return re_count;
	}
	public void setAl_rebate(double al_rebate)
	{
		this.al_rebate=al_rebate;
		COLUMN_FLAG[23] = true;
	}
	public double getAl_rebate()
	{
		return al_rebate;
	}
	public void setUpload_id(int upload_id)
	{
		this.upload_id=upload_id;
		COLUMN_FLAG[24] = true;
	}
	public int getUpload_id()
	{
		return upload_id;
	}
	public void setAct_price(double act_price)
	{
		this.act_price=act_price;
		COLUMN_FLAG[25] = true;
	}
	public double getAct_price()
	{
		return act_price;
	}
}
