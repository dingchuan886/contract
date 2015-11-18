package com.poly.action;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import car_beans.TbFinUser;

import com.poly.bean.CheckConState;
import com.poly.services.CheckConService;
import com.poly.services.TbFinUserService;
@Controller
@RequestMapping("/checkState")
public class CheckConUtils {
private CheckConService service = new CheckConService();
private TbFinUserService userService = new TbFinUserService();
	@RequestMapping("/getState")
	@ResponseBody
	public String getCheckConState(@RequestParam("userId")String userId){
		TbFinUser user = this.userService.findUserByUserId(userId);
		String orgcode = user.getOrg_code();
		String usercode = user.getUser_code();
		String role = user.getUser_role();
		String dept = user.getDept_code();
		//业务员的状态所有角色查询
		int userRejectCon = this.service.getUserConReject(4, userId);
		//int userRejectAccount = this.service.getUserAccountReject(3, userId);
		int userAddCon = this.service.getUserAddCon(2, userId);
		int userBillReject = this.service.getUserBillReject(3, userId);
		int userRebateReject = this.service.getUserRebateReject(4, userId);
		int userAheadAdvReject = this.service.getUserAheadAdvReject(4, userId);
		int managerAuditCon = 0;
		//int managerAuditAccount = 0;
		int managerAuditBill = 0;
		int managerAuditRebate = 0;
		int managerAuditAheadAdv = 0;
		int areaAuditCon = 0;
		int areaAuditBill = 0;
		int areaAuditRebate = 0;
		int flowAuditCon = 0;
		int flowAuditStamp = 0;
		int flowAuditBill = 0;
		int flowAuditRebate = 0;
		int flowAuditAheadAdv = 0;
		int finAuditBill = 0;
		int finAuditRebate = 0;
		int hqManagerAuditRebate = 0;
		if((orgcode.equals("1")&&role.equals("2")) || (orgcode.equals("0")&&role.equals("2")) || (!orgcode.equals("0")&&role.equals("4")&&dept.equals("1")) ||((!orgcode.equals("1")&&role.equals("4")&&dept.equals("1"))) || usercode.equals("Poly0007")
			|| (user.getUser_city()!=null && !user.getUser_city().equals("")&&!user.getUser_city().equals("##"))){
			//部门主管
			managerAuditCon = this.service.getManagerAuditCon(userId, 0);
			//managerAuditAccount = this.service.getManagerAuditAccount(userId, 1);
			managerAuditBill = this.service.getManagerAuditBill(userId, 0);
			managerAuditRebate = this.service.getManagerAuditRebate(userId, 0);
			managerAuditAheadAdv = this.service.getManagerAuditAheadAdv(userId, 1);
		}
		if(usercode.equals("Poly0085") || dept.equals("18")){
			flowAuditCon = this.service.getFlowAuditCon(userId, 7);
			flowAuditStamp = this.service.getFlowAuditStamp(userId, 2);
			flowAuditBill = this.service.getFlowAuditBill(userId, 6);
			flowAuditRebate = this.service.getFlowAuditRebate(userId, 7);
			flowAuditAheadAdv = this.service.getFlowAuditAheadAdv(userId, 2);
		}
		if(usercode.equals("Poly0064") || usercode.equals("Poly0218") || usercode.equals("Poly0589") || usercode.equals("Poly0590")){
			finAuditBill = this.service.getFinAuditBill(userId, 2);
			finAuditRebate = this.service.getFinAuditRebate(userId, 3);
		}
		if(role.equals("1")){
			hqManagerAuditRebate = this.service.getHqManagerAuditRebate(userId, 2);
		}
		if(user.getUser_dis()!=null && !user.getUser_dis().equals("")&&!user.getUser_dis().equals("##")){
			areaAuditCon = this.service.getManagerAuditCon(userId,1);
			areaAuditBill = this.service.getManagerAuditBill(userId,1);
			areaAuditRebate = this.service.getManagerAuditRebate(userId,1);
		}
		CheckConState state = new CheckConState(userRejectCon, userBillReject, userRebateReject, userAddCon, userAheadAdvReject, managerAuditCon, managerAuditBill, managerAuditRebate, managerAuditAheadAdv, flowAuditCon, flowAuditStamp, flowAuditBill, flowAuditRebate, flowAuditAheadAdv, finAuditBill, finAuditRebate, hqManagerAuditRebate,areaAuditCon,areaAuditBill,areaAuditRebate);
		JSONObject obj = JSONObject.fromObject(state);
		return obj.toString();
	}
}
