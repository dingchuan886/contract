package car_beans;
import java.util.*;

public class  TbFinDep  implements java.io.Serializable{

	public String KEY = "dep_id";
	public boolean[] COLUMN_FLAG = {false,false,false};
	private int dep_id;//部门编号
	private String dep_name;//部门名
	private String dep_comment;//部门描述


	public void setDep_id(int dep_id)
	{
		this.dep_id=dep_id;
		COLUMN_FLAG[0] = true;
	}
	public int getDep_id()
	{
		return dep_id;
	}
	public void setDep_name(String dep_name)
	{
		this.dep_name=dep_name;
		COLUMN_FLAG[1] = true;
	}
	public String getDep_name()
	{
		return dep_name;
	}
	public void setDep_comment(String dep_comment)
	{
		this.dep_comment=dep_comment;
		COLUMN_FLAG[2] = true;
	}
	public String getDep_comment()
	{
		return dep_comment;
	}
}
