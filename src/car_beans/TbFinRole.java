package car_beans;
import java.util.*;

public class  TbFinRole  implements java.io.Serializable{

	public String KEY = "role_code";
	public boolean[] COLUMN_FLAG = {false,false,false};
	private String role_code;//½ÇÉ«±àºÅ
	private String role_name;//½ÇÉ«Ãû
	private String role_comment;//½ÇÉ«ÃèÊö


	public void setRole_code(String role_code)
	{
		this.role_code=role_code;
		COLUMN_FLAG[0] = true;
	}
	public String getRole_code()
	{
		return role_code;
	}
	public void setRole_name(String role_name)
	{
		this.role_name=role_name;
		COLUMN_FLAG[1] = true;
	}
	public String getRole_name()
	{
		return role_name;
	}
	public void setRole_comment(String role_comment)
	{
		this.role_comment=role_comment;
		COLUMN_FLAG[2] = true;
	}
	public String getRole_comment()
	{
		return role_comment;
	}
}
