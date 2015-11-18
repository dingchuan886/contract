package com.poly.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import car_beans.TbFinDep;
import car_beans.TbFinOrg;
import car_beans.TbFinRole;
import car_beans.TbFinUser;
import car_beans.UserInfo;
import car_daos.TbFinDepDao;
import car_daos.TbFinOrgDao;
import car_daos.TbFinRoleDao;

import com.poly.services.TbFinUserService;
import com.poly.utils.MyConfig;

public class LoginInterceptor implements HandlerInterceptor{
	
	public  String[] allowUrl;
	

	public void setAllowUrl(String[] allowUrl) {
		this.allowUrl = allowUrl;
	}

	@Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mv)
            throws Exception {
       
    }

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		
		  String path = request.getRequestURI();
	        if(null != allowUrl && allowUrl.length>=1)  
	            for(String url : allowUrl) {    
	                if(path.equals(url)) {    
	                    return true;    
	                }    
	            }  
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo!=null){
           
            return true;
        }else{
        	response.sendRedirect("http://oa.chetuan.com/finance/login");
            return false;
        }
		
    }

}
