package car_beans;
import java.util.*;

public class  TbConAccountFlow  implements java.io.Serializable{

	public String KEY = "con_acc_flow_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false};
	private int con_acc_flow_id;//主键id
	private int con_account_id;//合同相关说明id
	private int acc_state;//合同相关说明状态 0-未提交 1-待审核 2-通过 3-驳回
	private String con_msg;//驳回原因
	private String next_check;//下一个审核人
	private String manager;//经理审核人


	public void setCon_acc_flow_id(int con_acc_flow_id)
	{
		this.con_acc_flow_id=con_acc_flow_id;
		COLUMN_FLAG[0] = true;
	}
	public int getCon_acc_flow_id()
	{
		return con_acc_flow_id;
	}
	public void setCon_account_id(int con_account_id)
	{
		this.con_account_id=con_account_id;
		COLUMN_FLAG[1] = true;
	}
	public int getCon_account_id()
	{
		return con_account_id;
	}
	public void setAcc_state(int acc_state)
	{
		this.acc_state=acc_state;
		COLUMN_FLAG[2] = true;
	}
	public int getAcc_state()
	{
		return acc_state;
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
	public void setNext_check(String next_check)
	{
		this.next_check=next_check;
		COLUMN_FLAG[4] = true;
	}
	public String getNext_check()
	{
		return next_check;
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
}
