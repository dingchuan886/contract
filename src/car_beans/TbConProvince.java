package car_beans;
import java.util.*;

public class  TbConProvince  implements java.io.Serializable{

	public String KEY = "pid";
	public boolean[] COLUMN_FLAG = {false,false};
	private int pid;//���������� ʡ�б�
	private String pname;//


	public void setPid(int pid)
	{
		this.pid=pid;
		COLUMN_FLAG[0] = true;
	}
	public int getPid()
	{
		return pid;
	}
	public void setPname(String pname)
	{
		this.pname=pname;
		COLUMN_FLAG[1] = true;
	}
	public String getPname()
	{
		return pname;
	}
}
