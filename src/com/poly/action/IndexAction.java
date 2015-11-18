package com.poly.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import car_beans.TbConZhSub;
import car_daos.DBConnect315che;

import com.poly.utils.MyConfig;
import com.poly.utils.SortByFirstPinyin;

@Controller
@RequestMapping(value="/index")
public class IndexAction{
	@RequestMapping(value="/index")
	public String index(HttpSession session){

		session.setAttribute("contract_ct_url", MyConfig.contract_ct_url);
		return "index";
	}
	//测试
	@RequestMapping("/testdc315che")
	public String testDb315che(Model model){
		DBConnect315che dbc = null;
		String sql = "select catalogname from dbo_car_catalognew where fatherid='0'";
		try {
			dbc = new DBConnect315che(sql);
			ResultSet query = dbc.executeQuery();
			StringBuilder sb = new StringBuilder();
			List<String> list = new ArrayList<String>();
			while(query.next()){
				list.add(query.getString(1));
			}
			 SortByFirstPinyin.sortByName(list);
			for (String str : list) {
				sb.append(str);
			}
			model.addAttribute("msg", sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "result/result";
	}

}
