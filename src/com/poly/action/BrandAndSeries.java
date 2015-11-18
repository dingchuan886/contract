package com.poly.action;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import car_beans.TbFinOrg;
import car_daos.DBConnect315che;
import car_daos.TbFinOrgDao;

import com.poly.bean.Brand315Che;

@Controller
@RequestMapping("/brandAndSeries")
public class BrandAndSeries {
	@SuppressWarnings("unchecked")
	@RequestMapping("/getBrand")
	@ResponseBody
	public String getBrand(){
		String sql = "select catalogid,catalogname from dbo_car_catalognew where fatherid='0'";
		DBConnect315che dbc = null;
		try {
			dbc = new DBConnect315che(sql);
			ResultSet query = dbc.executeQuery();
			List<Brand315Che> list = new ArrayList<Brand315Che>();
			while(query.next()){
				Brand315Che che = new Brand315Che();
				che.setCatalogid(query.getInt("catalogid"));
				che.setCatalogname(query.getString("catalogname"));
				list.add(che);
			}
			Collections.sort(list);
			JSONArray array = JSONArray.fromObject(list);
			return array.toString();
		} catch (Exception e) {
			e.printStackTrace();
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
		return "";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/getSeriesByBrand")
	@ResponseBody
	public String getSeriesByBrand(@RequestParam("catalogid")int catalogid,@RequestParam("brid")String brid){
		String sql = "select catalogid,catalogname from dbo_car_catalognew where fatherid=? and iway=?";
		String brId = "";
		try {
			brId = new String(brid.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DBConnect315che dbc = null;
		try {
			dbc = new DBConnect315che(sql);
			dbc.setInt(1, catalogid);
			dbc.setString(2, brId);
			ResultSet query = dbc.executeQuery();
			List<Brand315Che> list = new ArrayList<Brand315Che>();
			while(query.next()){
				Brand315Che brand = new Brand315Che();
				brand.setCatalogid(query.getInt("catalogid"));
				brand.setCatalogname(query.getString("catalogname"));
				list.add(brand);
			}
			Collections.sort(list);
			JSONArray array = JSONArray.fromObject(list);
			return array.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(dbc!=null){
				try {
					dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}
	@RequestMapping("/getiwayByBrand")
	@ResponseBody
	public String getiwayByBrand(@RequestParam("catalogid")int catalogid){
		String sql = "select iway from dbo_car_catalognew where fatherid=?";
		DBConnect315che dbc = null;
		try {
			dbc = new DBConnect315che(sql);
			dbc.setInt(1, catalogid);
			ResultSet query = dbc.executeQuery();
			Set<Brand315Che> set = new HashSet<Brand315Che>();
			while(query.next()){
				String str = query.getString(1);
				Brand315Che br = new Brand315Che();
				br.setIway(str);
				set.add(br);
			}
			JSONArray object = JSONArray.fromObject(set);
			return object.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return "";
	}
	
	@RequestMapping("/getAllOrg")
	@ResponseBody
	public String getAllOrg(){
		List<TbFinOrg> orgs = TbFinOrgDao.find();
		
		return JSONArray.fromObject(orgs).toString();
	}
}
