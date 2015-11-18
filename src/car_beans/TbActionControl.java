package car_beans;
import java.util.*;

public class  TbActionControl  implements java.io.Serializable{

	public String KEY = "pri_code";
	public boolean[] COLUMN_FLAG = {false,false};
	private String pri_code;//
	private String action;//


	public void setPri_code(String pri_code)
	{
		this.pri_code=pri_code;
		COLUMN_FLAG[0] = true;
	}
	public String getPri_code()
	{
		return pri_code;
	}
	public void setAction(String action)
	{
		this.action=action;
		COLUMN_FLAG[1] = true;
	}
	public String getAction()
	{
		return action;
	}
}
