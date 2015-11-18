package com.poly.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DbInsertUtils {
	public static void main(String[] args) {
		/*TbConAdv tb1 = new TbConAdv();
		TbConAdv tb2 = new TbConAdv();
		TbConAdv tb3 = new TbConAdv();
		TbConAdv tb4 = new TbConAdv();
		TbConAdv tb5 = new TbConAdv();
		TbConAdv tb6 = new TbConAdv();
		TbConAdv tb7 = new TbConAdv();
		TbConAdv tb8 = new TbConAdv();
		TbConAdv tb9 = new TbConAdv();
		TbConAdv tb10 = new TbConAdv();
		TbConAdv tb11 = new TbConAdv();
		for(int i=1;i<=31;i++){
			//查询市站表，找到一个省对应多少个市
			
			 *  省区站首页	压屏广告	顶部压屏	990X500(SWF)		60000
				省区站首页	通栏一	导航栏下方广告条	990X80(SWF/JPG/GIF)		30000
				省区站首页	通栏二	焦点区上方	990X80(SWF/JPG/GIF)		25000
				省区站首页	焦点图	焦点区左侧	360X250(JPG/GIF)		12000
				省区站首页	焦点文字链	焦点区中部	限13个汉字		8000
				省区站首页	推荐文字链	焦点图下方	限8个汉字		4000
				省区站首页	通栏三	地方车市资讯上方	990X80(SWF/JPG/GIF)		20000
				省区站首页	通栏四	阳光车市上方	990X80(SWF/JPG/GIF)		12000
				省区站首页	通栏五	阳光车市下方	990X80(SWF/JPG/GIF)		10000
				省区站首页	底部大图标	新车图库左侧大图	450X320(JPG/GIF)		8000
				省区站首页	底部小图标	新车图库右侧小图	260X155(JPG/GIF)		4000

			 
			 List<TbConCity> where = TbConCityDao.where(" PID='"+i+"'");
			 if(where!=null && where.size()>0)
			for (TbConCity tbConCity : where) {
				int cid = tbConCity.getCid();
				tb1.setPid(i);tb1.setCid(cid);tb1.setChid(12);tb1.setAdname("压屏广告");tb1.setAdposition("顶部压屏");tb1.setStandard("990X500(SWF)");tb1.setAdarea("北京上海;一线;二线;三线;");
				tb1.setAdprice("北京 上海   60000<br/>一线   45000<br/>二线  30000<br/>三线   20000");
				tb2.setPid(i);tb2.setCid(cid);tb2.setChid(12);tb2.setAdname("通栏一");tb2.setAdposition("导航栏下方广告条");tb2.setStandard("990X80(SWF/JPG/GIF)");tb2.setAdarea("北京上海;一线;二线;三线;");
				tb2.setAdprice("北京 上海   30000<br/>一线   25000<br/>二线  20000<br/>三线   15000");
				tb3.setPid(i);tb3.setCid(cid);tb3.setChid(12);tb3.setAdname("通栏二");tb3.setAdposition("焦点区上方");tb3.setStandard("990X80(SWF/JPG/GIF)");tb3.setAdarea("北京上海;一线;二线;三线;");
				tb3.setAdprice("北京 上海   25000<br/>一线   20000<br/>二线  15000<br/>三线   10000");
				tb4.setPid(i);tb4.setCid(cid);tb4.setChid(12);tb4.setAdname("焦点图");tb4.setAdposition("焦点区左侧");tb4.setStandard("360X250(JPG/GIF)");tb4.setAdarea("北京上海;一线;二线;三线;");
				tb4.setAdprice("北京 上海  12000<br/>一线   10000<br/>二线  8000<br/>三线   6000");
				tb5.setPid(i);tb5.setCid(cid);tb5.setChid(12);tb5.setAdname("焦点文字链");tb5.setAdposition("焦点区中部");tb5.setStandard("限13个汉字");tb5.setAdarea("北京上海;一线;二线;三线;");
				tb5.setAdprice("北京 上海  8000<br/>一线   6000<br/>二线  4000<br/>三线   3000");
				tb6.setPid(i);tb6.setCid(cid);tb6.setChid(12);tb6.setAdname("推荐文字链");tb6.setAdposition("焦点图下方");tb6.setStandard("限8个汉字");tb6.setAdarea("北京上海;一线;二线;三线;");
				tb6.setAdprice("北京 上海  4000<br/>一线   3000<br/>二线  2000<br/>三线   1000");
				tb7.setPid(i);tb7.setCid(cid);tb7.setChid(12);tb7.setAdname("通栏三");tb7.setAdposition("地方车市资讯上方");tb7.setStandard("990X80(SWF/JPG/GIF)");tb7.setAdarea("北京上海;一线;二线;三线;");
				tb7.setAdprice("北京 上海  20000<br/>一线   15000<br/>二线  12000<br/>三线   8000");
				tb8.setPid(i);tb8.setCid(cid);tb8.setChid(12);tb8.setAdname("通栏四");tb8.setAdposition("阳光车市上方");tb8.setStandard("990X80(SWF/JPG/GIF)");tb8.setAdarea("北京上海;一线;二线;三线;");
				tb8.setAdprice("北京 上海  12000<br/>一线   10000<br/>二线   8000<br/>三线   6000");
				tb9.setPid(i);tb9.setCid(cid);tb9.setChid(12);tb9.setAdname("通栏五");tb9.setAdposition("阳光车市下方");tb9.setStandard("990X80(SWF/JPG/GIF)");tb9.setAdarea("北京上海;一线;二线;三线;");
				tb9.setAdprice("北京 上海  10000<br/>一线   8000<br/>二线   5000<br/>三线   3000");
				tb10.setPid(i);tb10.setCid(cid);tb10.setChid(12);tb10.setAdname("底部大图标");tb10.setAdposition("新车图库左侧大图");tb10.setStandard("450X320(JPG/GIF)");tb10.setAdarea("北京上海;一线;二线;三线;");
				tb10.setAdprice("北京 上海  8000<br/>一线   6000<br/>二线   4000<br/>三线   2000");
				tb11.setPid(i);tb11.setCid(cid);tb11.setChid(12);tb11.setAdname("底部小图标");tb11.setAdposition("新车图库右侧小图");tb11.setStandard("260X155(JPG/GIF)");tb11.setAdarea("北京上海;一线;二线;三线;");
				tb11.setAdprice("北京 上海  4000<br/>一线   3000<br/>二线   2000<br/>三线   1000");
				DBConnect dbc = null;
				try {
					dbc = new DBConnect();
					dbc.setAutoCommit(false);
					TbConAdvDao.save(dbc,tb1);
					TbConAdvDao.save(dbc,tb2);
					TbConAdvDao.save(dbc,tb3);
					TbConAdvDao.save(dbc,tb4);
					TbConAdvDao.save(dbc,tb5);
					TbConAdvDao.save(dbc,tb6);
					TbConAdvDao.save(dbc,tb7);
					TbConAdvDao.save(dbc,tb8);
					TbConAdvDao.save(dbc,tb9);
					TbConAdvDao.save(dbc,tb10);
					TbConAdvDao.save(dbc,tb11);
					dbc.commit();
					System.out.println("存储成功");
				} catch (Exception e) {
					try {
						dbc.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
					System.out.println("存储失败");
				}finally{
					if(dbc!=null){
						try {
							dbc.close();
							System.out.println("连接关闭");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	*/
		/*String str = "123,sdfdsf,vvvvv,bbbbb,cccccc,";
		String[] str1 = str.split(",");
		System.out.println("数组长度为：：：："+str1.length);
		for(int i=0;i<str1.length;i++){
			System.out.println(str1[i]);
			}*/
	}
	
}
