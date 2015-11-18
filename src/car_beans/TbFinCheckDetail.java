package car_beans;
import java.util.*;

public class  TbFinCheckDetail  implements java.io.Serializable{

	public String KEY = "id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false};
	private int id;//
	private String cid;//凭证号
	private String check_user;//审批人
	private String content;//审批意见
	private Date add_time;//审批时间
	private int check_type;//审批状态 -0同意 -1打回


	public void setId(int id)
	{
		this.id=id;
		COLUMN_FLAG[0] = true;
	}
	public int getId()
	{
		return id;
	}
	public void setCid(String cid)
	{
		this.cid=cid;
		COLUMN_FLAG[1] = true;
	}
	public String getCid()
	{
		return cid;
	}
	public void setCheck_user(String check_user)
	{
		this.check_user=check_user;
		COLUMN_FLAG[2] = true;
	}
	public String getCheck_user()
	{
		return check_user;
	}
	public void setContent(String content)
	{
		this.content=content;
		COLUMN_FLAG[3] = true;
	}
	public String getContent()
	{
		return content;
	}
	public void setAdd_time(Date add_time)
	{
		this.add_time=add_time;
		COLUMN_FLAG[4] = true;
	}
	public Date getAdd_time()
	{
		return add_time;
	}
	public void setCheck_type(int check_type)
	{
		this.check_type=check_type;
		COLUMN_FLAG[5] = true;
	}
	public int getCheck_type()
	{
		return check_type;
	}
}
