package car_beans;
import java.util.*;

public class  TbConAdvschedule  implements java.io.Serializable{

	public String KEY = "aid";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false};
	private int aid;//����������id
	private String con_zh_id;//����id
	private int adid;//
	private int con_zh_sub_id;//
	private String schedule_date;//
	private TbConAdv tbConAdv = new TbConAdv();//存放广告表
	
	public TbConAdv getTbConAdv() {
		return tbConAdv;
	}
	public void setTbConAdv(TbConAdv tbConAdv) {
		this.tbConAdv = tbConAdv;
	}
	public void setAid(int aid)
	{
		this.aid=aid;
		COLUMN_FLAG[0] = true;
	}
	public int getAid()
	{
		return aid;
	}
	public void setCon_zh_id(String con_zh_id)
	{
		this.con_zh_id=con_zh_id;
		COLUMN_FLAG[1] = true;
	}
	public String getCon_zh_id()
	{
		return con_zh_id;
	}
	public void setAdid(int adid)
	{
		this.adid=adid;
		COLUMN_FLAG[2] = true;
	}
	public int getAdid()
	{
		return adid;
	}
	public void setCon_zh_sub_id(int con_zh_sub_id)
	{
		this.con_zh_sub_id=con_zh_sub_id;
		COLUMN_FLAG[3] = true;
	}
	public int getCon_zh_sub_id()
	{
		return con_zh_sub_id;
	}
	public void setSchedule_date(String schedule_date)
	{
		this.schedule_date=schedule_date;
		COLUMN_FLAG[4] = true;
	}
	public String getSchedule_date()
	{
		return schedule_date;
	}
}
