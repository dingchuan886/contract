package car_beans;
import java.util.*;

public class  TbConFlow  implements java.io.Serializable{

	public String KEY = "con_flow_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false};
	private int con_flow_id;//������id
	private String con_id;//��ͬ��
	private int con_state;//0-�ȴ����  1-���ž������  2-���̲����  3-���̲��鵵  4-���� 5-δ�ύ  6-��ɾ��  7-���������
	private String con_msg;//����ԭ��
	private String nextcheck;//��һ�������
	private String manager;//���������
	private String flow_check;//���̲����
	private String flow_seal;//���̲�����
	private String area;//���������


	public void setCon_flow_id(int con_flow_id)
	{
		this.con_flow_id=con_flow_id;
		COLUMN_FLAG[0] = true;
	}
	public int getCon_flow_id()
	{
		return con_flow_id;
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
	public void setCon_state(int con_state)
	{
		this.con_state=con_state;
		COLUMN_FLAG[2] = true;
	}
	public int getCon_state()
	{
		return con_state;
	}
	public void setCon_msg(String con_msg)
	{
		this.con_msg=con_msg;
		COLUMN_FLAG[3] = true;
	}
	public String getCon_msg()
	{
		return con_msg;
	}
	public void setNextcheck(String nextcheck)
	{
		this.nextcheck=nextcheck;
		COLUMN_FLAG[4] = true;
	}
	public String getNextcheck()
	{
		return nextcheck;
	}
	public void setManager(String manager)
	{
		this.manager=manager;
		COLUMN_FLAG[5] = true;
	}
	public String getManager()
	{
		return manager;
	}
	public void setFlow_check(String flow_check)
	{
		this.flow_check=flow_check;
		COLUMN_FLAG[6] = true;
	}
	public String getFlow_check()
	{
		return flow_check;
	}
	public void setFlow_seal(String flow_seal)
	{
		this.flow_seal=flow_seal;
		COLUMN_FLAG[7] = true;
	}
	public String getFlow_seal()
	{
		return flow_seal;
	}
	public void setArea(String area)
	{
		this.area=area;
		COLUMN_FLAG[8] = true;
	}
	public String getArea()
	{
		return area;
	}
}
