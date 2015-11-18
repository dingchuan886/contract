package car_beans;
import java.util.*;

public class  TbConBillFlow  implements java.io.Serializable{

	public String KEY = "bill_flow_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false};
	private int bill_flow_id;//自增长id
	private int bill_id;//开票id
	private int bill_state;//0-未审核  1-经理审核  2-流程部审核  3-驳回 4-未提交 5-已删除 6-区域经理审核
	private String bill_msg;//驳回原因
	private String nextcheck;//下一个审核人
	private String manager;//经理审核人
	private String flow_check;//流程部审核
	private String area;//区域经理审核


	public void setBill_flow_id(int bill_flow_id)
	{
		this.bill_flow_id=bill_flow_id;
		COLUMN_FLAG[0] = true;
	}
	public int getBill_flow_id()
	{
		return bill_flow_id;
	}
	public void setBill_id(int bill_id)
	{
		this.bill_id=bill_id;
		COLUMN_FLAG[1] = true;
	}
	public int getBill_id()
	{
		return bill_id;
	}
	public void setBill_state(int bill_state)
	{
		this.bill_state=bill_state;
		COLUMN_FLAG[2] = true;
	}
	public int getBill_state()
	{
		return bill_state;
	}
	public void setBill_msg(String bill_msg)
	{
		this.bill_msg=bill_msg;
		COLUMN_FLAG[3] = true;
	}
	public String getBill_msg()
	{
		return bill_msg;
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
	public void setArea(String area)
	{
		this.area=area;
		COLUMN_FLAG[7] = true;
	}
	public String getArea()
	{
		return area;
	}
}
