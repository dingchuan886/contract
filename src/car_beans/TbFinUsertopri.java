package car_beans;
import java.util.*;

public class  TbFinUsertopri  implements java.io.Serializable{

	public String KEY = "user_id";
	public boolean[] COLUMN_FLAG = {false,false};
	private String user_id;//用户编号
	private String pri_code;//权限编号


	public void setUser_id(String user_id)
	{
		this.user_id=user_id;
		COLUMN_FLAG[0] = true;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setPri_code(String pri_code)
	{
		this.pri_code=pri_code;
		COLUMN_FLAG[1] = true;
	}
	public String getPri_code()
	{
		return pri_code;
	}
}
