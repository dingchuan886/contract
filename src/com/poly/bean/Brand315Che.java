package com.poly.bean;

import java.text.CollationKey;
import java.text.Collator;

public class Brand315Che implements Comparable{
	private Collator collator = Collator.getInstance();
	private int catalogid;
	private String catalogname;
	private String iway;
	
	public String getIway() {
		return iway;
	}
	public void setIway(String iway) {
		this.iway = iway;
	}
	public int getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}
	public String getCatalogname() {
		return catalogname;
	}
	public void setCatalogname(String catalogname) {
		this.catalogname = catalogname;
	}
	public Brand315Che() {
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + catalogid;
		result = prime * result + ((iway == null) ? 0 : iway.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand315Che other = (Brand315Che) obj;
		if (catalogid != other.catalogid)
			return false;
		if (iway == null) {
			if (other.iway != null)
				return false;
		} else if (!iway.equals(other.iway))
			return false;
		return true;
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Brand315Che){
			String name1 = this.getCatalogname();
			String name2 = ((Brand315Che) o).getCatalogname();
			CollationKey key1=collator.getCollationKey(name1);//要想不区分大小写进行比较用.toLowerCase()  
		    CollationKey key2=collator.getCollationKey(name2); 
		    return key1.compareTo(key2);
		}else{
			return 0;
		}
		
	}
	
	
}
