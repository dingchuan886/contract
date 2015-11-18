package car_beans;

import java.util.List;

public class PageBean {
	
	private int nowpage;
	
	private int totalpage;
	
	private List<Integer> pagenumlist;

	public int getNowpage() {
		return nowpage;
	}

	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public List<Integer> getPagenumlist() {
		return pagenumlist;
	}

	public void setPagenumlist(List<Integer> pagenumlist) {
		this.pagenumlist = pagenumlist;
	}

}
