package car_beans;
import java.util.*;

public class  TbConAheadadvertisment  implements java.io.Serializable{

	public String KEY = "adv_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false};
	private int adv_id;//
	private String cus_name;//锟酵伙拷锟斤拷锟斤拷
	private String reazon;//
	private String year;//锟斤拷
	private String month;//锟斤拷
	private String day;//锟斤拷
	private double con_price;//锟斤拷同锟斤拷锟�
	private int adv_state;//锟斤拷前锟斤拷锟斤拷状态 1-锟斤拷锟斤拷锟� 2-锟斤拷锟斤拷锟斤拷锟酵拷锟� 3-锟斤拷锟教诧拷锟斤拷锟酵拷锟� 4-锟斤拷锟斤拷
	private String user_name;//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	private String user_id;//锟斤拷锟斤拷锟斤拷id
	private Date create;//锟斤拷锟斤拷时锟斤拷
	private Date update;//锟斤拷锟斤拷时锟斤拷
	private String con_id;//
	private List<TbConAheadadvertismentSub> tbConAheadadvertismentSub = new ArrayList<TbConAheadadvertismentSub>();

	
	public List<TbConAheadadvertismentSub> getTbConAheadadvertismentSub() {
		return tbConAheadadvertismentSub;
	}
	public void setTbConAheadadvertismentSub(
			List<TbConAheadadvertismentSub> tbConAheadadvertismentSub) {
		this.tbConAheadadvertismentSub = tbConAheadadvertismentSub;
	}
	public void setAdv_id(int adv_id)
	{
		this.adv_id=adv_id;
		COLUMN_FLAG[0] = true;
	}
	public int getAdv_id()
	{
		return adv_id;
	}
	public void setCus_name(String cus_name)
	{
		this.cus_name=cus_name;
		COLUMN_FLAG[1] = true;
	}
	public String getCus_name()
	{
		return cus_name;
	}
	public void setReazon(String reazon)
	{
		this.reazon=reazon;
		COLUMN_FLAG[2] = true;
	}
	public String getReazon()
	{
		return reazon;
	}
	public void setYear(String year)
	{
		this.year=year;
		COLUMN_FLAG[3] = true;
	}
	public String getYear()
	{
		return year;
	}
	public void setMonth(String month)
	{
		this.month=month;
		COLUMN_FLAG[4] = true;
	}
	public String getMonth()
	{
		return month;
	}
	public void setDay(String day)
	{
		this.day=day;
		COLUMN_FLAG[5] = true;
	}
	public String getDay()
	{
		return day;
	}
	public void setCon_price(double con_price)
	{
		this.con_price=con_price;
		COLUMN_FLAG[6] = true;
	}
	public double getCon_price()
	{
		return con_price;
	}
	public void setAdv_state(int adv_state)
	{
		this.adv_state=adv_state;
		COLUMN_FLAG[7] = true;
	}
	public int getAdv_state()
	{
		return adv_state;
	}
	public void setUser_name(String user_name)
	{
		this.user_name=user_name;
		COLUMN_FLAG[8] = true;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_id(String user_id)
	{
		this.user_id=user_id;
		COLUMN_FLAG[9] = true;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setCreate(Date create)
	{
		this.create=create;
		COLUMN_FLAG[10] = true;
	}
	public Date getCreate()
	{
		return create;
	}
	public void setUpdate(Date update)
	{
		this.update=update;
		COLUMN_FLAG[11] = true;
	}
	public Date getUpdate()
	{
		return update;
	}
	public void setCon_id(String con_id)
	{
		this.con_id=con_id;
		COLUMN_FLAG[12] = true;
	}
	public String getCon_id()
	{
		return con_id;
	}
}
