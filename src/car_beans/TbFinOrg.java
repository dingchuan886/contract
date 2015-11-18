package car_beans;
import java.util.*;

public class  TbFinOrg  implements java.io.Serializable{

	public String KEY = "org_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false};
	private int org_id;//组织编号
	private String org_name;//组织名
	private String org_comment;//组织描述
	private String dis_id;//区域id


	public void setOrg_id(int org_id)
	{
		this.org_id=org_id;
		COLUMN_FLAG[0] = true;
	}
	public int getOrg_id()
	{
		return org_id;
	}
	public void setOrg_name(String org_name)
	{
		this.org_name=org_name;
		COLUMN_FLAG[1] = true;
	}
	public String getOrg_name()
	{
		return org_name;
	}
	public void setOrg_comment(String org_comment)
	{
		this.org_comment=org_comment;
		COLUMN_FLAG[2] = true;
	}
	public String getOrg_comment()
	{
		return org_comment;
	}
	public void setDis_id(String dis_id)
	{
		this.dis_id=dis_id;
		COLUMN_FLAG[3] = true;
	}
	public String getDis_id()
	{
		return dis_id;
	}
}
