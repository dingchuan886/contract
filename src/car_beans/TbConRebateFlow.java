package car_beans;
import java.util.*;

public class  TbConRebateFlow  implements java.io.Serializable{

	public String KEY = "rebate_flow_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false};
	private int rebate_flow_id;//������id
	private int rebate_id;//��ͬ��
	private int rebate_state;//�������״̬ 0-δ��� 1-������� 2-������� 3-�ܾ������ 4-���� 5-δ�ύ 6-��ɾ�� 7-���������
	private String rebate_msg;//����ԭ��
	private String nextcheck;//��һ�������
	private String manager;//���������
	private String flow_check;//���̲����
	private String hqmanager;//�ܾ������
	private String area;//���������


	public void setRebate_flow_id(int rebate_flow_id)
	{
		this.rebate_flow_id=rebate_flow_id;
		COLUMN_FLAG[0] = true;
	}
	public int getRebate_flow_id()
	{
		return rebate_flow_id;
	}
	public void setRebate_id(int rebate_id)
	{
		this.rebate_id=rebate_id;
		COLUMN_FLAG[1] = true;
	}
	public int getRebate_id()
	{
		return rebate_id;
	}
	public void setRebate_state(int rebate_state)
	{
		this.rebate_state=rebate_state;
		COLUMN_FLAG[2] = true;
	}
	public int getRebate_state()
	{
		return rebate_state;
	}
	public void setRebate_msg(String rebate_msg)
	{
		this.rebate_msg=rebate_msg;
		COLUMN_FLAG[3] = true;
	}
	public String getRebate_msg()
	{
		return rebate_msg;
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
	public void setHqmanager(String hqmanager)
	{
		this.hqmanager=hqmanager;
		COLUMN_FLAG[7] = true;
	}
	public String getHqmanager()
	{
		return hqmanager;
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
