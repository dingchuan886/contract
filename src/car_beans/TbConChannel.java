package car_beans;
import java.util.*;

public class  TbConChannel  implements java.io.Serializable{

	public String KEY = "chid";
	public boolean[] COLUMN_FLAG = {false,false};
	private int chid;//
	private String chname;//ÆµµÀÃû³Æ


	public void setChid(int chid)
	{
		this.chid=chid;
		COLUMN_FLAG[0] = true;
	}
	public int getChid()
	{
		return chid;
	}
	public void setChname(String chname)
	{
		this.chname=chname;
		COLUMN_FLAG[1] = true;
	}
	public String getChname()
	{
		return chname;
	}
}
