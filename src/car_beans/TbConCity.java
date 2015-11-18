package car_beans;
import java.util.*;

public class  TbConCity  implements java.io.Serializable{

	public String KEY = "cid";
	public boolean[] COLUMN_FLAG = {false,false,false};
	private int cid;//自增长id 市列表
	private String cname;//市名称
	private int pid;//外键，省id


	public void setCid(int cid)
	{
		this.cid=cid;
		COLUMN_FLAG[0] = true;
	}
	public int getCid()
	{
		return cid;
	}
	public void setCname(String cname)
	{
		this.cname=cname;
		COLUMN_FLAG[1] = true;
	}
	public String getCname()
	{
		return cname;
	}
	public void setPid(int pid)
	{
		this.pid=pid;
		COLUMN_FLAG[2] = true;
	}
	public int getPid()
	{
		return pid;
	}
}
