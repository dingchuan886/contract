package car_beans;
import java.util.*;

public class  TbFinPri  implements java.io.Serializable{

	public String KEY = "pri_code";
	public boolean[] COLUMN_FLAG = {false,false,false};
	private String pri_code;//权限编号
	private String pri_name;//权限名称
	private String pri_comment;//权限描述


	public void setPri_code(String pri_code)
	{
		this.pri_code=pri_code;
		COLUMN_FLAG[0] = true;
	}
	public String getPri_code()
	{
		return pri_code;
	}
	public void setPri_name(String pri_name)
	{
		this.pri_name=pri_name;
		COLUMN_FLAG[1] = true;
	}
	public String getPri_name()
	{
		return pri_name;
	}
	public void setPri_comment(String pri_comment)
	{
		this.pri_comment=pri_comment;
		COLUMN_FLAG[2] = true;
	}
	public String getPri_comment()
	{
		return pri_comment;
	}
}
