package car_beans;
import java.util.*;

public class  TbConAdv  implements java.io.Serializable{

	public String KEY = "adid";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false};
	private int adid;//
	private int pid;//省id 0锟斤拷锟斤拷全锟斤拷
	private int cid;//锟斤拷id 0锟斤拷锟斤拷省站
	private int chid;//频锟斤拷id
	private String adname;//锟斤拷锟斤拷锟�
	private String adposition;//锟斤拷锟轿伙拷锟�
	private String standard;//锟斤拷锟斤拷锟�
	private String adarea;//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	private String adprice;//锟斤拷锟斤拷锟斤拷 元/锟斤拷
	private String direct;//锟斤拷锟斤拷 省/直辖锟叫碉拷
	public String chName;
	
	
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public void setAdid(int adid)
	{
		this.adid=adid;
		COLUMN_FLAG[0] = true;
	}
	public int getAdid()
	{
		return adid;
	}
	public void setPid(int pid)
	{
		this.pid=pid;
		COLUMN_FLAG[1] = true;
	}
	public int getPid()
	{
		return pid;
	}
	public void setCid(int cid)
	{
		this.cid=cid;
		COLUMN_FLAG[2] = true;
	}
	public int getCid()
	{
		return cid;
	}
	public void setChid(int chid)
	{
		this.chid=chid;
		COLUMN_FLAG[3] = true;
	}
	public int getChid()
	{
		return chid;
	}
	public void setAdname(String adname)
	{
		this.adname=adname;
		COLUMN_FLAG[4] = true;
	}
	public String getAdname()
	{
		return adname;
	}
	public void setAdposition(String adposition)
	{
		this.adposition=adposition;
		COLUMN_FLAG[5] = true;
	}
	public String getAdposition()
	{
		return adposition;
	}
	public void setStandard(String standard)
	{
		this.standard=standard;
		COLUMN_FLAG[6] = true;
	}
	public String getStandard()
	{
		return standard;
	}
	public void setAdarea(String adarea)
	{
		this.adarea=adarea;
		COLUMN_FLAG[7] = true;
	}
	public String getAdarea()
	{
		return adarea;
	}
	public void setAdprice(String adprice)
	{
		this.adprice=adprice;
		COLUMN_FLAG[8] = true;
	}
	public String getAdprice()
	{
		return adprice;
	}
	public void setDirect(String direct)
	{
		this.direct=direct;
		COLUMN_FLAG[9] = true;
	}
	public String getDirect()
	{
		return direct;
	}
}
