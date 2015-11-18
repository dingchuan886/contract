package com.poly.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import car_beans.TbConAddcon;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConZh;
import car_beans.TbConZhSub;
import car_beans.UserInfo;
import car_daos.DBConnect;
import car_daos.TbConAddconDao;
import car_daos.TbConCtDao;
import car_daos.TbConZhSubDao;

import com.poly.bean.PageCondition;
import com.poly.bean.PageResult;
import com.poly.services.ConSupplyService;
import com.poly.services.MyContractService;
import com.poly.services.TbConBillService;

@Controller
@RequestMapping("/conSupply")
public class ConSupplyAction {
	private MyContractService myService = new MyContractService();
	private TbConBillService billService = new TbConBillService();
	private ConSupplyService supService = new ConSupplyService();

	@RequestMapping("/toCzSupply")
	public String toCzConSupply(Model model, HttpSession session,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,PageCondition pageCondition) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String usercode = user.getUsercode();
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getConId()!=null && (!pageCondition.getConId().equals(""))){
			sb.append(" and CON_CZ_ID='"+pageCondition.getConId()+"' ");
		}
			
		sb.append(" and USER_ID='" + usercode + "' and (CON_STATE='2' or CON_STATE='3') ");
		int count = myService.findAllCzContractCount(sb.toString());
		PageResult<Map<String, Object>> page = new PageResult<Map<String, Object>>(
				pageNum, count);
		List<TbConCz> czList = myService.findCzContract(sb.toString(),
				page.getStartIndex(), page.getPageSize());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (czList != null && czList.size() > 0) {
			for (TbConCz cz : czList) {
				String conId = cz.getCon_cz_id();
				TbConAddcon supplyByCz = this.supService
						.getConSupplyByCz(conId);
				int billCount = this.billService.findCzSupplyBill(conId);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("conCz", cz);
				map.put("billCount", billCount);
				map.put("supCz", supplyByCz);
				list.add(map);
			}
		}
		page.setList(list);
		model.addAttribute("myCzContract", page);
		model.addAttribute("pageCondition", pageCondition);
		return "conSupply/czConSupply";
	}
	@RequestMapping("/saveCzSupply")
	@ResponseBody
	public String saveCzSupply(@RequestParam("conId") String conId,@RequestParam("exePrice") double exePrice,
			@RequestParam("exeDate") String exeDate) {
		TbConAddcon addCon = new TbConAddcon();
		addCon.setCon_id(conId);
		addCon.setExe_date(exeDate);
		addCon.setExe_price(exePrice);
		TbConAddcon supplyByCz = supService.getConSupplyByCz(conId);
		if(supplyByCz!=null){
			addCon.setSid(supplyByCz.getSid());
			int i = TbConAddconDao.update(addCon);
			if(i==1){
				return "{back:200}";
			}else{
				return "";
			}
		}
		try {
			TbConAddconDao.save(addCon);
			return "{back:200}";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@RequestMapping("/toCtSupply")
	public String toCtConSupply(Model model, HttpSession session,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,PageCondition pageCondition) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String usercode = user.getUsercode();
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getConId()!=null && (!pageCondition.getConId().equals(""))){
			sb.append(" and CON_CT_ID='"+pageCondition.getConId()+"' ");
		}
			
		sb.append(" and USER_ID='" + usercode + "' and (CON_STATE='2' or CON_STATE='3') ");
		int count = myService.findAllCtContractCount(sb.toString());
		PageResult<Map<String, Object>> page = new PageResult<Map<String, Object>>(
				pageNum, count);
		List<TbConCt> ctList = myService.findCtContract(sb.toString(),
				page.getStartIndex(), page.getPageSize());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (ctList != null && ctList.size() > 0) {
			for (TbConCt ct : ctList) {
				String conId = ct.getCon_ct_id();
				TbConAddcon supplyByCt = this.supService
						.getConSupplyByCt(conId);
				int billCount = this.billService.findCzSupplyBill(conId);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("conCt", ct);
				map.put("billCount", billCount);
				map.put("supCt", supplyByCt);
				list.add(map);
			}
		}
		page.setList(list);
		model.addAttribute("myCtContract", page);
		model.addAttribute("pageCondition", pageCondition);
		return "conSupply/ctConSupply";
	}
	@RequestMapping("/saveCtSupply")
	@ResponseBody
	public String saveCtSupply(@RequestParam("conId") String conId,@RequestParam("exePrice") double exePrice,
			@RequestParam("exeDate") String exeDate,@RequestParam("con_total_price")double con_total_price,
			@RequestParam("car_count")int car_count){
		TbConAddcon addCon = new TbConAddcon();
		addCon.setCon_id(conId);
		addCon.setExe_date(exeDate);
		addCon.setExe_price(exePrice);
		addCon.setCon_total_price(con_total_price);
		addCon.setCar_count(car_count);
		TbConAddcon supplyByCz = supService.getConSupplyByCt(conId);
		if(supplyByCz!=null){
			addCon.setSid(supplyByCz.getSid());
			int i = TbConAddconDao.update(addCon);
			TbConCt ct = new TbConCt();
			ct.setUpdate(new Date());
			ct.setCon_ct_id(conId);
			ct.setCon_total_price(con_total_price);
			int j = TbConCtDao.update(ct);
			if(i==1 && j==1){
				return "{back:200}";
			}else{
				return "";
			}
		}
		try {
			TbConAddconDao.save(addCon);
			TbConCt ct = new TbConCt();
			ct.setUpdate(new Date());
			ct.setCon_ct_id(conId);
			ct.setCon_total_price(con_total_price);
			int j = TbConCtDao.update(ct);
			if(j==1){
				return "{back:200}";
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@RequestMapping("/toZhSupply")
	public String toZhConSupply(Model model, HttpSession session,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,PageCondition pageCondition) {
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String usercode = user.getUsercode();
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		if(pageCondition.getConId()!=null && (!pageCondition.getConId().equals(""))){
			sb.append(" and CON_ZH_ID='"+pageCondition.getConId()+"' ");
		}
			
		sb.append(" and USER_ID='" + usercode + "' and (CON_STATE='2' or CON_STATE='3') ");
		int count = myService.findAllZhContractCount(sb.toString());
		PageResult<Map<String, Object>> page = new PageResult<Map<String, Object>>(
				pageNum, count);
		List<TbConZh> zhList = myService.findZhContract(sb.toString(),
				page.getStartIndex(), page.getPageSize());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (zhList != null && zhList.size() > 0) {
			for (TbConZh zh : zhList) {
				String conId = zh.getCon_zh_id();
				List<TbConAddcon> supplyByZhs = new ArrayList<TbConAddcon>();
				List<TbConZhSub> where = TbConZhSubDao.where(" CON_ZH_ID='"+conId+"' ");
				if(where!=null&&where.size()>0){
					for(int i=0;i<where.size();i++){
						TbConZhSub zhSub = where.get(i);
						TbConAddcon supplyByZh = this.supService
								.getConSupplyByZh(conId,zhSub.getCon_zh_sub_id());
						supplyByZhs.add(supplyByZh);
					}
					
				}
				
				int billCount = this.billService.findCzSupplyBill(conId);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("conZhSubCount", where.size());
				map.put("conZhSub", where);
				map.put("conZh", zh);
				map.put("billCount", billCount);
				map.put("supZh", supplyByZhs);
				list.add(map);
			}
		}
		page.setList(list);
		model.addAttribute("myZhContract", page);
		model.addAttribute("pageCondition", pageCondition);
		return "conSupply/zhConSupply";
	}
	@RequestMapping("/saveZhSupply")
	@ResponseBody
	public String saveZhSupply(@RequestParam("exePrice")double exePrice,@RequestParam("exeDate[]")List<String> dates,
			@RequestParam("conId")String conId,@RequestParam("sub_con_id[]")List<Integer> sub_con_id){
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.setAutoCommit(false);
			for(int i=0;i<sub_con_id.size();i++){
				TbConAddcon supplyByZh = supService.getConSupplyByZh(conId, sub_con_id.get(i));
				if(supplyByZh!=null){
					TbConAddcon addCon = new TbConAddcon();
					addCon.setSid(supplyByZh.getSid());
					addCon.setExe_date(dates.get(i));
					addCon.setExe_price(exePrice);
					TbConAddconDao.update(dbc, addCon);
				}else{
					TbConAddcon addCon = new TbConAddcon();
					addCon.setExe_date(dates.get(i));
					addCon.setExe_price(exePrice);
					addCon.setCon_s_id(sub_con_id.get(i));
					addCon.setCon_id(conId);
					TbConAddconDao.save(dbc, addCon);
				}
			}
			dbc.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				dbc.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "";
		}finally{
			if(dbc!=null){
				try {
					dbc.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return "{back:200}";
	}
}
