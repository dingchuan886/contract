package car_beans;
import java.util.*;

public class  TbConAheadadvertismentSub  implements java.io.Serializable{

	public String KEY = "adv_sub_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false};
	private int adv_sub_id;//
	private int adv_id;//�����ǰ��������id
	private String put_pos;//����λ��
	private String put_date;//����ʱ��


	public void setAdv_sub_id(int adv_sub_id)
	{
		this.adv_sub_id=adv_sub_id;
		COLUMN_FLAG[0] = true;
	}
	public int getAdv_sub_id()
	{
		return adv_sub_id;
	}
	public void setAdv_id(int adv_id)
	{
		this.adv_id=adv_id;
		COLUMN_FLAG[1] = true;
	}
	public int getAdv_id()
	{
		return adv_id;
	}
	public void setPut_pos(String put_pos)
	{
		this.put_pos=put_pos;
		COLUMN_FLAG[2] = true;
	}
	public String getPut_pos()
	{
		return put_pos;
	}
	public void setPut_date(String put_date)
	{
		this.put_date=put_date;
		COLUMN_FLAG[3] = true;
	}
	public String getPut_date()
	{
		return put_date;
	}
}
