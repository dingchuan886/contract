package car_beans;
import java.util.*;

public class  DboAreCatalog  implements java.io.Serializable{

	public String KEY = "catalogid";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private int catalogid;//
	private String catalogname;//
	private int fatherid;//
	private String byname;//
	private int classid;//
	private int isdelete;//
	private String map;//
	private String coords;//
	private String path;//
	private int sortid;//
	private int forumsid;//
	private String mapareaid;//
	private String lname;//
	private int pathlevel;//级别1、代表省、直辖市如江苏省、上海市2、如南京市
	private int iscity;//
	private int adareakeyid;//
	private String englingname;//


	public void setCatalogid(int catalogid)
	{
		this.catalogid=catalogid;
		COLUMN_FLAG[0] = true;
	}
	public int getCatalogid()
	{
		return catalogid;
	}
	public void setCatalogname(String catalogname)
	{
		this.catalogname=catalogname;
		COLUMN_FLAG[1] = true;
	}
	public String getCatalogname()
	{
		return catalogname;
	}
	public void setFatherid(int fatherid)
	{
		this.fatherid=fatherid;
		COLUMN_FLAG[2] = true;
	}
	public int getFatherid()
	{
		return fatherid;
	}
	public void setByname(String byname)
	{
		this.byname=byname;
		COLUMN_FLAG[3] = true;
	}
	public String getByname()
	{
		return byname;
	}
	public void setClassid(int classid)
	{
		this.classid=classid;
		COLUMN_FLAG[4] = true;
	}
	public int getClassid()
	{
		return classid;
	}
	public void setIsdelete(int isdelete)
	{
		this.isdelete=isdelete;
		COLUMN_FLAG[5] = true;
	}
	public int getIsdelete()
	{
		return isdelete;
	}
	public void setMap(String map)
	{
		this.map=map;
		COLUMN_FLAG[6] = true;
	}
	public String getMap()
	{
		return map;
	}
	public void setCoords(String coords)
	{
		this.coords=coords;
		COLUMN_FLAG[7] = true;
	}
	public String getCoords()
	{
		return coords;
	}
	public void setPath(String path)
	{
		this.path=path;
		COLUMN_FLAG[8] = true;
	}
	public String getPath()
	{
		return path;
	}
	public void setSortid(int sortid)
	{
		this.sortid=sortid;
		COLUMN_FLAG[9] = true;
	}
	public int getSortid()
	{
		return sortid;
	}
	public void setForumsid(int forumsid)
	{
		this.forumsid=forumsid;
		COLUMN_FLAG[10] = true;
	}
	public int getForumsid()
	{
		return forumsid;
	}
	public void setMapareaid(String mapareaid)
	{
		this.mapareaid=mapareaid;
		COLUMN_FLAG[11] = true;
	}
	public String getMapareaid()
	{
		return mapareaid;
	}
	public void setLname(String lname)
	{
		this.lname=lname;
		COLUMN_FLAG[12] = true;
	}
	public String getLname()
	{
		return lname;
	}
	public void setPathlevel(int pathlevel)
	{
		this.pathlevel=pathlevel;
		COLUMN_FLAG[13] = true;
	}
	public int getPathlevel()
	{
		return pathlevel;
	}
	public void setIscity(int iscity)
	{
		this.iscity=iscity;
		COLUMN_FLAG[14] = true;
	}
	public int getIscity()
	{
		return iscity;
	}
	public void setAdareakeyid(int adareakeyid)
	{
		this.adareakeyid=adareakeyid;
		COLUMN_FLAG[15] = true;
	}
	public int getAdareakeyid()
	{
		return adareakeyid;
	}
	public void setEnglingname(String englingname)
	{
		this.englingname=englingname;
		COLUMN_FLAG[16] = true;
	}
	public String getEnglingname()
	{
		return englingname;
	}
}
