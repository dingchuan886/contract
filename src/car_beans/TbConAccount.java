package car_beans;
import java.util.*;

public class  TbConAccount  implements java.io.Serializable{

	public String KEY = "con_s_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private int con_s_id;//����������id
	private String con_id;//
	private int isback;//�Ƿ���  0-������  1-����
	private String isback_des;//�������� ��  Ԫ
	private int plan;//ִ�н��� 0-����ִ�� 1-��ʱ����
	private String plan_des;//�������� ��__�·�Ͷ�����
	private int con_type;//ҵ������ 0-��Ա  1-Ӳ��  2-��Ա��Ӳ��  3-��չ  4-��չ+��� 5-�Ź�  6-������
	private int bill_type;//��Ʊ���� 0-ƽ�� 1-�߿� 2-����Ʊ
	private String bill_des;//�߿����� Ԥ�ƿ�Ʊ���Ϊ__Ԫ
	private int back_exp;//�ؿ�Ԥ��  0-��ʱ�ؿ�  1-Ԥ�ƻؿ�
	private String back_des;//�ؿ�����  Ԥ��__��__�¿ɻؿ�
	private String ben_person;//������
	private String phone;//�绰
	private String user_id;//ҵ��Աid
	private String user_name;//ҵ��Ա����
	private Date create;//����ʱ��
	private Date update;//����ʱ��
	private int acc_state;//��ͬ���˵��״̬ 0-δ�ύ 1����� 2-����� 3-����


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
