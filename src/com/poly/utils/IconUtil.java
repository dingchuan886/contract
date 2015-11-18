package com.poly.utils;

import java.util.ArrayList;
import java.util.List;

public class IconUtil {
	private int width;
	private int heigth;

	public IconUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IconUtil(int width, int heigth) {
		super();
		this.width = width;
		this.heigth = heigth;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public static List<IconUtil> iconList() {
		List<IconUtil> iconList = new ArrayList<IconUtil>();
		iconList.add(new IconUtil(90,60));
		iconList.add(new IconUtil(510,336));
		iconList.add(new IconUtil(343,247));
		iconList.add(new IconUtil(160,98));
		iconList.add(new IconUtil(105,73));
		iconList.add(new IconUtil(222,166));
		iconList.add(new IconUtil(150,50));
		return iconList;
	}

}
