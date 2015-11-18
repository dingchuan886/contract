package car_beans;
import java.util.*;

public class  TbConAheadadvertismentFlow  implements java.io.Serializable{

	public String KEY = "adv_flow_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false};
	private int adv_flow_id;//
	private int adv_id;//广告提前申请id
	private String next_check;//下一审核人
	private String manager_check;//经理审核人
	private String flow_check;//流程部审核人
	private String adv_msg;//驳回原因
	private int adv_state;//提前申请状态 1-待审核 2-经历审核通过 3-流程部审核通过 4-驳回


	public void setAdv_flow_id(int adv_flow_id)
	{
		this.adv_flow_id=adv_flow_id;
		COLUMN_FLAG[0] = true;
	}
	public int getAdv_flow_id()
	{
		return adv_flow_id;
	}
	public void setAdv_id(int adv_id)
	{
		this.adv_id=adv_id;
		COLUMN_FLAG[1] = true;
	}
	public int getAdv_id()
	{
		return adv_id;
	}
	public void setNext_check(String next_check)
	{
		this.next_check=next_check;
		COLUMN_FLAG[2] = true;
	}
	public String getNext_check()
	{
		return next_check;
	}
	public void setManager_check(String manager_check)
	{
		this.manager_check=manager_check;
		COLUMN_FLAG[3] = true;
	}
	public String getManager_check()
	{
		return manager_check;
	}
	public void setFlow_check(String flow_check)
	{
		this.flow_check=flow_check;
		COLUMN_FLAG[4] = true;
	}
	public String getFlow_check()
	{
		return flow_check;
	}
	public void setAdv_msg(String adv_msg)
	{
		this.adv_msg=adv_msg;
		COLUMN_FLAG[5] = true;
	}
	public String getAdv_msg()
	{
		return adv_msg;
	}
	public void setAdv_state(int adv_state)
	{
		this.adv_state=adv_state;
		COLUMN_FLAG[6] = true;
	}
	public int getAdv_state()
	{
		return adv_state;
	}
}
