package com.poly.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import car_beans.TbConAddcon;
import car_beans.TbConAdv;
import car_beans.TbConAdvschedule;
import car_beans.TbConAheadadvertisment;
import car_beans.TbConBill;
import car_beans.TbConCity;
import car_beans.TbConCz;
import car_beans.TbConProvince;
import car_beans.TbConRebate;
import car_beans.TbConZh;
import car_beans.TbConZhSub;
import car_daos.DBConnect;
import car_daos.TbConAdvDao;
import car_daos.TbConAdvscheduleDao;
import car_daos.TbConAheadadvertismentDao;
import car_daos.TbConBillDao;
import car_daos.TbConCityDao;
import car_daos.TbConCzDao;
import car_daos.TbConProvinceDao;
import car_daos.TbConRebateDao;
import car_daos.TbConZhDao;
import car_daos.TbConZhSubDao;

import com.poly.bean.PageCondition;
import com.poly.bean.PageResult;
import com.poly.bean.ScheduleUtils;
import com.poly.services.MyContractService;
import com.poly.services.TbFinAdvService;
import com.poly.services.TbFinUserService;

@Controller
@RequestMapping("/advSchedule")
public class AdvScheduleController {
	private TbFinUserService userService = new TbFinUserService();
	private TbFinAdvService advService = new TbFinAdvService();
	private MyContractService myContractService = new MyContractService();
	@RequestMapping("/toShowSchedule")
	public String toShowSchedule(){

		return "advSchedule/viewSchedule";
	}
	/**
	 * 初始化表格，返回表格的必要信息
	 * @return
	 */
	@RequestMapping("/initTable")
	@ResponseBody
	public String initTable(@RequestParam(value="year")String year,@RequestParam(value="month")String month,@RequestParam(value="chId",defaultValue="0")int chId,
							@RequestParam(value="pId",defaultValue="0")int pId,@RequestParam(value="cId",defaultValue="0")int cId){
		int day = dayOfMonth(year,month);
		StringBuilder sbdate = new StringBuilder();
                for(int i=1;i<=day;i++){
                    sbdate.append("<td><div class=shu_bg>"+i+"</div></td>");
                }
		/*
		 * json格式
		 * days 返回天数 添加到固定的tr下
		 * datas 返回行数，添加到tbody下
		 *
		 * */
		if(chId==0){
			//只返回天数

			return "{'days':'"+sbdate.toString()+"'}";
		}else{
			if(chId==11 && pId==0){
				//选择到了频道，但是没有选择省份
				return "{'days':'"+sbdate.toString()+"'}";
			}else if(chId==11 && pId!=0){
				//省站
				List<TbConAdv> pros = TbConAdvDao.where(" CHId='"+chId+"' and PId='"+pId+"'");
				StringBuilder dayAnddate = new StringBuilder();
				for (TbConAdv tbConAdv : pros) {
					//遍历获取到的广告，有几个页面上显示几个
					List<Integer> listDays = new ArrayList<Integer>();
					String channelName = this.advService.getAdvChNameById(tbConAdv.getAdid());
					dayAnddate.append("<tr calss=border_b_b>");
					dayAnddate.append("<td class=border_n_l><div class=data_bg>"+channelName+"</div></td><td class=border_n_l><div class=data_bg>"+tbConAdv.getAdposition()+"</div></td><td class=border_n_l><div class=data_bg>"+tbConAdv.getAdname()+"</div></td><td class=border_n_l><div class=data_bg>"+tbConAdv.getStandard()+"</div></td>");
					//获取所有的时间排期表
					List<TbConAdvschedule> schedule = TbConAdvscheduleDao.where(" ADID='"+tbConAdv.getAdid()+"'");
					if(schedule!=null && schedule.size()>0){
						StringBuilder sb2 = new StringBuilder();
						for (TbConAdvschedule tbConAdvschedule : schedule) {
							sb2.append(tbConAdvschedule.getSchedule_date());
						}
						String[] split = sb2.toString().split(",");

						for(int i=0;i<split.length;i++){
							String year1 = split[i].substring(0,4);
							String month1 = split[i].substring(5,7);
							String day1 = split[i].substring(8,10);
							if(year1.equals(year) && month1.equals(month)){
								//确定是哪月了，将天取出来，放入容器中进行处理
								listDays.add(Integer.parseInt(day1));
							}

						}
						//遍历日期，如果包含在那一天，则将其样式进行处理
						for(int i=1;i<=day;i++){
						  //添加与天数相同的td
							if(listDays.contains(i)){
							    //有数据的样式
							    dayAnddate.append("<td><div class=sk_toufang></div></td>");
							}else{
							    //无数据的样式
							    dayAnddate.append("<td>&nbsp;</td>");
							}


						}
					}else{
					    //广告还没有排期的，直接返回天数
					    for(int i=1;i<=day;i++){
					    	dayAnddate.append("<td>&nbsp;</td>");

                       }
					}
					    dayAnddate.append("</tr>");

				}
				return "{'days':'"+sbdate.toString()+"','datas':'"+dayAnddate.toString()+"'}";
			}else if(chId==12 && cId==0){
			  //选择到了频道，但是没有选择市站(可能选择了省站，但是不做处理)
                            return "{'days':'"+sbdate.toString()+"'}";
			}else if(chId==12 && cId!=0){
			    //处理直辖市，直辖市只属于省站，市站处理为省站
			      if(pId==2 || pId==3 || pId==22 || pId==27){
			          List<TbConAdv> pros = TbConAdvDao.where(" CHId='"+chId+"' and PId='"+pId+"'");
			          String dayAnddate = getDayAndDates(day,pros,year,month);
			          return "{'days':'"+sbdate.toString()+"','datas':'"+dayAnddate+"'}";
			      }else{
			          List<TbConAdv> pros = TbConAdvDao.where(" CHId='"+chId+"' and PId='"+pId+"' and CID='"+cId+"'");
			          String dayAnddate = getDayAndDates(day,pros,year,month);
			          return "{'days':'"+sbdate.toString()+"','datas':'"+dayAnddate+"'}";
			      }
			}else{
			    List<TbConAdv> pros = TbConAdvDao.where(" CHId='"+chId+"'");
                            String dayAnddate = getDayAndDates(day,pros,year,month);
                            return "{'days':'"+sbdate.toString()+"','datas':'"+dayAnddate+"'}";
			}

		}

	}
	private String getDayAndDates(int day,List<TbConAdv> pros,String year,String month){
            StringBuilder dayAnddate = new StringBuilder();
            for (TbConAdv tbConAdv : pros) {
                    //遍历获取到的广告，有几个页面上显示几个
                    List<Integer> listDays = new ArrayList<Integer>();
                    String channelName = this.advService.getAdvChNameById(tbConAdv.getAdid());
                    //行的表头
                    dayAnddate.append("<tr calss=border_b_b>");

                    dayAnddate.append("<td class=border_n_l><div class=data_bg>"+channelName+"</div></td><td class=border_n_l><div class=data_bg>"+tbConAdv.getAdposition()+"</div></td><td class=border_n_l><div class=data_bg>"+tbConAdv.getAdname()+"</div></td><td class=border_n_l><div class=data_bg>"+tbConAdv.getStandard()+"</div></td>");
                  //获取所有的时间排期表
                    List<TbConAdvschedule> schedule = TbConAdvscheduleDao.where(" ADID='"+tbConAdv.getAdid()+"'");
                    if(schedule!=null && schedule.size()>0){
                            StringBuilder sb2 = new StringBuilder();
                            for (TbConAdvschedule tbConAdvschedule : schedule) {
                                    sb2.append(tbConAdvschedule.getSchedule_date());
                            }
                            String[] split = sb2.toString().split(",");

                            for(int i=0;i<split.length;i++){
                                    String year1 = split[i].substring(0,4);
                                    String month1 = split[i].substring(5,7);
                                    String day1 = split[i].substring(8,10);
                                    if(year1.equals(year) && month1.equals(month)){
                                            //确定是哪月了，将天取出来，放入容器中进行处理
                                            listDays.add(Integer.parseInt(day1));
                                    }

                            }

                            //遍历日期，如果包含在那一天，则将其样式进行处理
                            for(int i=1;i<=day;i++){
                              //添加与天数相同的td
                                    if(listDays.contains(i)){
                                        //有数据的样式
                                        dayAnddate.append("<td><div class=sk_toufang></div></td>");
                                    }else{
                                        //无数据的样式
                                        dayAnddate.append("<td>&nbsp;</td>");
                                    }


                            }
                    }else{
                        //广告还没有排期的，直接返回天数
                        for(int i=1;i<=day;i++){

                         dayAnddate.append("<td>&nbsp;</td>");

                       }
                    }
                        dayAnddate.append("</tr>");

            }
            return dayAnddate.toString();
	}
	private int dayOfMonth(String year, String month) {
		//只返回日期
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM");
	    Calendar rightNow = Calendar.getInstance();
	    try {
			rightNow.setTime(simpleDate.parse(year+"-"+month));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    int day = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);//根据年月 获取月份天数

		return day;
	}
	/**
	 * 根据频道查省
	 */
	@RequestMapping("/findProvince")
	@ResponseBody
	public String findProvince(@RequestParam("chId")int chId){
		if(chId==11 || chId==12){
			List<TbConProvince> find = TbConProvinceDao.find();
			return JSONArray.fromObject(find).toString();
		}

		return "";
	}
	/**
	 * 根据省站查市
	 */
	@RequestMapping("/findCity")
	@ResponseBody
	public String findCity(@RequestParam("pId")int pId){
		List<TbConCity> where = TbConCityDao.where(" PID='"+pId+"'");
		if(where!=null && where.size()>0){
			return JSONArray.fromObject(where).toString();
		}
		return "";
	}
	/**
	 * 跳转到添加合同排期的页面
	 * @return
	 */
	@RequestMapping("/toAddSchedule")
	public String toAddSchedule(){

		return "advSchedule/addSchedule";
	}
	/**
	 * 根据频道查找广告
	 * @return
	 */
	@RequestMapping("/findAdvByChannel")
	@ResponseBody
	public String findAdvByChannel(@RequestParam("chId")int chId){
		
		
		List<TbConAdv> where = TbConAdvDao.where(" CHID='"+chId+"' and PID='0' and CID='0'");
		if(where!=null && where.size()>0){
			return JSONArray.fromObject(where).toString();
		}
		
		
		
		return "";
	}

	/**
	 * 根据省站找广告
	 */
	@RequestMapping("/findAdvByProvince")
	@ResponseBody
	public String findAdvByProvince(@RequestParam("chId")int chId,@RequestParam("pId")int pId){
		List<TbConAdv> where = TbConAdvDao.where(" CHID='"+chId+"' and PID='"+pId+"' and CID='0'");
		if(where!=null && where.size()>0){
			return JSONArray.fromObject(where).toString();
		}
		return "";
	}

	/**
	 * 根据市站找广告
	 */
	@RequestMapping("/findAdvByCity")
	@ResponseBody
	public String findAdvByCity(@RequestParam("chId")int chId,@RequestParam("pId")int pId,@RequestParam("cId")int cId){
		List<TbConAdv> where = null;
		if(pId==2||pId==3||pId==22||pId==27){
			where = TbConAdvDao.where(" CHID='11' and PID='"+pId+"' and CID='0'");
		}else{
			where = TbConAdvDao.where(" CHID='"+chId+"' and PID='"+pId+"' and CID='"+cId+"' ");
		}
		if(where!=null && where.size()>0){
			return JSONArray.fromObject(where).toString();
		}
		return "";
	}
/**
 * 广告人员上广告前查询根据合同号查询广告
 * @param conId
 * @param model
 * @return
 */
	@RequestMapping("/findZhCon")
	public String toCalendar(@RequestParam("conId")String conId,Model model){
		TbConZh zh = null;
		TbConCz cz = null;
		if(conId.startsWith("SHCZ")){
			List<TbConCz> where = TbConCzDao.where(" CON_CZ_ID='"+conId+"'");
			if(where!=null && where.size()>0){
				cz = where.get(0);
			}
		}
		if(conId.startsWith("SHZH")){
			List<TbConZh> where = TbConZhDao.where(" CON_ZH_ID='"+conId+"'");
			if(where!=null && where.size()>0){
				zh = where.get(0);
			}
		}
		
		
		if(zh!=null || cz!=null){
			List<TbConZhSub> where2 = TbConZhSubDao.where(" CON_ZH_ID='"+conId+"'");
			if(where2!=null && where2.size()>0){
				List<TbConZhSub> list = new ArrayList<TbConZhSub>();
				for (TbConZhSub tbConZhSub : where2) {
					List<TbConAdvschedule> advschedules = TbConAdvscheduleDao.where(" CON_ZH_SUB_ID='"+tbConZhSub.getCon_zh_sub_id()+"'");
					if(advschedules!=null && advschedules.size()>0){
						tbConZhSub.setAdvschedule(advschedules.get(0));

					}
					list.add(tbConZhSub);
				}
				if(conId.startsWith("SHCZ")){
					cz.setTbConZhSubs(list);
					model.addAttribute("zhContract",cz);
				}
				if(conId.startsWith("SHZH")){
					zh.setTbConZhSubs(list);
					model.addAttribute("zhContract", zh);
				}
			}
		}
		return "advSchedule/addSchedule";
	}
	
	/**
	 * 广告负责人员查看所有的广告合同
	 * @return
	 */
	@RequestMapping("/editorFindZhContract")
	public String editorFindZhContract(PageCondition pageCondition,Model model,@RequestParam(value="pageNum",defaultValue="1")int pageNum){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" 1=1 ");
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and CON_TYPE="+pageCondition.getConType()+" ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setUserName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		sb.append(" and CON_STATE='3' ");
		int totalRecords = this.myContractService.findAllZhContractCount(sb.toString());
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		int startIndex = page.getStartIndex();
		int pageSize = page.getPageSize();
		List<TbConZh> list = this.myContractService.findZhContract(sb.toString(),startIndex,pageSize);
		
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConZh conZh : list) {
				String orgName = this.userService.getOrgNameByUserId(conZh.getUser_id());
				String deptName = this.userService.getDeptNameByUserId(conZh.getUser_id());
				List<TbConZhSub> conZhSubs = TbConZhSubDao.where(" CON_ZH_ID='"+conZh.getCon_zh_id()+"' ");
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conZh", conZh);
				map.put("conZhSub", conZhSubs);
				pager.add(map);
			}
		}	
			page.setList(pager);
		
		if(list !=null && list.size() > 0){
			model.addAttribute("myZhContract", page);
		}
		model.addAttribute("pageCondition", pageCondition);
		return "advSchedule/editorZhContract";
		
	}
	/**
	 * 查找所有的车展广告
	 * @param pageCondition
	 * @param model
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/editorFindCzContract")
	public String editorFindCzContract(PageCondition pageCondition,Model model,@RequestParam(value="pageNum",defaultValue="1")int pageNum){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" 1=1 ");
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and CON_TYPE="+pageCondition.getConType()+" ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setUserName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		sb.append(" and CON_STATE='3' ");
		int totalRecords = this.myContractService.findAllCzContractCount(sb.toString());
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		int startIndex = page.getStartIndex();
		int pageSize = page.getPageSize();
		List<TbConCz> list = this.myContractService.findCzContract(sb.toString(),startIndex,pageSize);
		
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConCz conCz : list) {
				List<TbConZhSub> conCzSub = TbConZhSubDao.where(" CON_ZH_ID='"+conCz.getCon_cz_id()+"' ");
				if(conCzSub!=null && conCzSub.size()>0){
					String orgName = this.userService.getOrgNameByUserId(conCz.getUser_id());
					String deptName = this.userService.getDeptNameByUserId(conCz.getUser_id());
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("deptName", deptName);
					map.put("orgName", orgName);
					map.put("conCz", conCz);
					map.put("conCzSub", conCzSub);
					pager.add(map);
				}
			}
		}	
			page.setList(pager);
		
		if(list !=null && list.size() > 0){
			model.addAttribute("myCzContract", page);
		}
		model.addAttribute("pageCondition", pageCondition);
		return "advSchedule/editorCzContract";
		
	}
	/**
	 * 保存排期
	 * @param schedule
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveSchedule")
	public String saveSchedule(ScheduleUtils schedule,Model model){
		List<TbConAdvschedule> sches = schedule.getSches();

		if(sches!=null && sches.size()>0){
			DBConnect dbc = null;
			try {
				dbc = new DBConnect();
				dbc.setAutoCommit(false);
				for (TbConAdvschedule sche : sches) {
					int sub_id = sche.getCon_zh_sub_id();
					List<TbConAdvschedule> where = TbConAdvscheduleDao.where(" CON_ZH_SUB_ID='"+sub_id+"' ");
						//如果存在就进行更新操作
						if(where!=null && where.size()>0){
							TbConAdvschedule updatesche = where.get(0);
							sche.setAid(updatesche.getAid());
							TbConAdvscheduleDao.update(dbc, sche);
						}else{
							TbConAdvscheduleDao.save(dbc, sche);
						}
				}
				dbc.commit();
			} catch (Exception e) {
				try {
					dbc.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				model.addAttribute("msg", "提交失败");
				return "result/result";
			}finally{
				if(dbc!=null){
					try {
						dbc.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			model.addAttribute("msg", "提交成功");
			return "result/result";
		}
		model.addAttribute("msg", "没有广告添加到排期");
		return "result/result";
	}
	@RequestMapping("/toCheckSchedule")
	public String toCheckSchedule(){

		return "advSchedule/checkSchedule";
	}
	/**
	 * 广告发布人员查看合同相关情况
	 * @param conId
	 * @return
	 */
	@RequestMapping("/findConZhCheck")
	public String findConZhCheck(@RequestParam("conId")String conId,Model model){
		TbConZh zh = null;
		TbConCz cz = null;
		if(conId.startsWith("SHZH")){
			List<TbConZh> where = TbConZhDao.where(" CON_ZH_ID='"+conId+"'");
			if(where!=null && where.size()>0){
				zh = where.get(0);
			}
		}
		if(conId.startsWith("SHCZ")){
			List<TbConCz> where = TbConCzDao.where(" CON_CZ_ID='"+conId+"'");
			if(where!=null && where.size()>0){
				cz = where.get(0);
			}
		}
		if(zh!=null || cz!=null){
			List<TbConAheadadvertisment> aheads = TbConAheadadvertismentDao.where(" CON_ID='"+conId+"'");
			if(aheads!=null && aheads.size()>0){
				model.addAttribute("aheadAdv", aheads.get(0));
			}
			String username = null;
			String userId = null;
			String orgName = null;
			String deptName = null;
			if(conId.startsWith("SHZH")){
				username = zh.getUser_name();
				userId = zh.getUser_id();
				orgName = this.userService.getOrgNameByUserId(userId);
				deptName = this.userService.getDeptNameByUserId(userId);
			}
			if(conId.startsWith("SHCZ")){
				username = cz.getUser_name();
				userId = cz.getUser_id();
				orgName = this.userService.getOrgNameByUserId(userId);
				deptName = this.userService.getDeptNameByUserId(userId);
			}
			List<TbConZhSub> where2 = TbConZhSubDao.where(" CON_ZH_ID='"+conId+"'");
			if(where2!=null && where2.size()>0){
				//广告表之间的关系
				/*
				 * 广告主表List存放广告子表
				 * 子表存放排期表对象
				 * 排期表存放广告表对象
				 * */
				//查找出所有的广告表子表(广告详情)
				List<TbConZhSub> list = new ArrayList<TbConZhSub>();
				for (TbConZhSub tbConZhSub : where2) {
					//根据子表id查找到时间排期表，存放的是对应的广告表id，广告占用时间等
					List<TbConAdvschedule> advschedules = TbConAdvscheduleDao.where(" CON_ZH_SUB_ID='"+tbConZhSub.getCon_zh_sub_id()+"'");
					if(advschedules!=null && advschedules.size()>0){
						TbConAdvschedule advschedule = advschedules.get(0);
						//根据广告排期表中的广告表id查找广告表，并将其存放到排期表中
						List<TbConAdv> where3 = TbConAdvDao.where(" ADID='"+advschedule.getAdid()+"'");
						if(where3!=null && where3.size()>0){
							TbConAdv tbConAdv = where3.get(0);
							int chid = tbConAdv.getChid();
							String chName = this.advService.findChNameByChId(chid);
							tbConAdv.setChName(chName);
							advschedule.setTbConAdv(tbConAdv);
						}
						//将广告排期表存放到广告子表中
						tbConZhSub.setAdvschedule(advschedule);
					}
					list.add(tbConZhSub);
				}
				if(conId.startsWith("SHCZ")){
					cz.setTbConZhSubs(list);
					model.addAttribute("zhContract", cz);
				}
				if(conId.startsWith("SHZH")){
					zh.setTbConZhSubs(list);
					model.addAttribute("zhContract", zh);
				}
				model.addAttribute("orgName", orgName);
				model.addAttribute("userName", username);
				model.addAttribute("deptName", deptName);
			}
		}
		return "advSchedule/checkSchedule";
	}
}
