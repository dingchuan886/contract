package car_beans;
import java.util.*;

public class  TbConZhSub  implements java.io.Serializable{

	public String KEY = "con_zh_sub_id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false};
	private int con_zh_sub_id;//
	private String con_zh_id;//����id
	private String media;//ý�� 1-315che.com 2-���  3-����
	private String position;//λ��
	private String standard;//���
	private String brand;//Ʒ��
	private String put_date;//Ͷ������
	private String copyright;//������
	private String unit_price;//����
	private String content;//��ע
	private TbConAdvschedule advschedule = new TbConAdvschedule();//������ڱ�
	

	public TbConAdvschedule getAdvschedule() {
		return advschedule;
	}
	public void setAdvschedule(TbConAdvschedule advschedule) {
		this.advschedule = advschedule;
	}
	public void setCon_zh_sub_id(int con_zh_sub_id)
	{
		this.con_zh_sub_id=con_zh_sub_id;
		COLUMN_FLAG[0] = true;
	}
	public int getCon_zh_sub_id()
	{
		return con_zh_sub_id;
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
	public void setMedia(String media)
	{
		this.media=media;
		COLUMN_FLAG[2] = true;
	}
	public String getMedia()
	{
		return media;
	}
	public void setPosition(String position)
	{
		this.position=position;
		COLUMN_FLAG[3] = true;
	}
	public String getPosition()
	{
		return position;
	}
	public void setStandard(String standard)
	{
		this.standard=standard;
		COLUMN_FLAG[4] = true;
	}
	public String getStandard()
	{
		return standard;
	}
	public void setBrand(String brand)
	{
		this.brand=brand;
		COLUMN_FLAG[5] = true;
	}
	public String getBrand()
	{
		return brand;
	}
	public void setPut_date(String put_date)
	{
		this.put_date=put_date;
		COLUMN_FLAG[6] = true;
	}
	public String getPut_date()
	{
		return put_date;
	}
	public void setCopyright(String copyright)
	{
		this.copyright=copyright;
		COLUMN_FLAG[7] = true;
	}
	public String getCopyright()
	{
		return copyright;
	}
	public void setUnit_price(String unit_price)
	{
		this.unit_price=unit_price;
		COLUMN_FLAG[8] = true;
	}
	public String getUnit_price()
	{
		return unit_price;
	}
	public void setContent(String content)
	{
		this.content=content;
		COLUMN_FLAG[9] = true;
	}
	public String getContent()
	{
		return content;
	}
}
