package car_beans;
import java.util.*;

public class  TbConRebate  implements java.io.Serializable{

	public String KEY = "back_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private int back_id;//����������id
	private String con_id;//��ͬ��
	private String user_id;//ҵ��Աid
	private String user_name;//ҵ��Ա����
	private String cus_name;//�ͻ�����
	private String cus_s_id;//
	private String back_des;//�����������ݸ���ϸ���
	private double con_high;//���и߿�
	private double al_back;//�Է������
	private double this_back;//���η������
	private double deduct;//�۳���Ʊ˰��
	private double back;//����
	private double back_actual;//ʵ�ʷ���
	private Date create;//����ʱ��
	private Date update;//����ʱ��
	private int rebate_state;//�������״̬ 0-δ��� 1-������� 2-������� 3-�ܾ������ 4-���� 5-δ�ύ 6-��ɾ�� 7-���������
	private String rebate_time;//����ʱ��
	private String acount;//�����˻�
	private String payee_name;//�տ���
	private String bank_name;//������������


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
