<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>更新合同-车团合同</title>
 <@c.html/>
 </head>
  <body>
  <@c.head/>
<@c.left/>

        <div class="span10">

        <ul class="nav nav-tabs">
          <!--    <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/czContract/toAddCzContract'" data-toggle="tab">众智车展合同</a></li> -->
           <!--   <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/zhContract/toAddZhContract'" data-toggle="tab">众智315广告合同</a></li> -->
           <li class="active"><a href="#" data-toggle="tab">车团合同</a></li>
          </ul>

          <form action="/contract/ctContract/updateCtContract" class="form-horizontal" id="form1" method="post" target="_blank">
           <div class="row-fluid">
              <div class="control-group span5">
                <label for="userCode" class="control-label">姓名</label>
                <div class="controls">
                  <input type="text" name="user_name" value="${userInfo.username}" readonly="readonly"/>
                  <input type="hidden" name="user_id" value="${userInfo.usercode}">
                </div>
              </div>
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">日期</label>
                <div class="controls">
                  <input type="text" name="create" placeholder="${nowDate}" disabled>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="userCode" class="control-label">分站</label>
                <div class="controls">
                  <input type="text"  placeholder="${userInfo.orgname}" disabled/>
                </div>
              </div>
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">部门</label>
                <div class="controls">
                  <input type="text" id="evidenceCode" placeholder="${userInfo.deptname}" disabled/>
                </div>
              </div>
            </div>
            <div class="alert alert-info">
              <button type="button" class="close" data-dismiss="alert">×</button>
              以上姓名，日期，站点，部门信息仅供查看无法修改！
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">客户类型</label>
                <div class="controls">
                   <select name="cus_type" id="">
                    <option value="1" <#if ctContract.cus_type==1>selected</#if>>大客户</option>
                    <option value="2" <#if ctContract.cus_type==2>selected</#if>>区域</option>
                    <option value="3" <#if ctContract.cus_type==3>selected</#if>>经销商</option>
                  </select>
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">媒体</label>
                <div class="controls">
                 <input type="text" name="media" value="${ctContract.media}"/>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">客户名称</label>
                <div class="controls">
                  <input type="text" name="cus_name" id="cus_name" value="${ctContract.cus_name}">
                </div>
              </div>
              <ul class="inline">
                <label for="userCode" class="control-label" style="padding-left:25px">业务类型</label>
                <li>
                  <label class="radio">
                    <input type="radio" name="bus_type" style="margin:0" value="1" <#if ctContract.bus_type==1>checked="checked"</#if>>
                    团购
                  </label>
                </li>
                <li>
                  <label class="radio">
                    <input type="radio" name="bus_type" style="margin:0" value="2" <#if ctContract.bus_type==2>checked="checked"</#if>>
                    特卖惠
                  </label>
                </li>
              </ul>
            </div>
            <div class="row-fluid" style="border-bottom:1px solid #ddd">
              <div class="control-group span5">
                <label for="type" class="control-label">地址</label>
                <div class="controls">
                  <input type="text" name="cus_addr" value="${ctContract.cus_addr}"/>
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">电话</label>
                <div class="controls">
                  <input type="text" name="cus_tel" id="cus_tel" value="${ctContract.cus_tel}"/>
                </div>
              </div>
            </div>
            <div class="row-fluid" style="padding-top:20px;">
             <div class="control-group span3">
                <label for="type" class="control-label">品牌</label>
                <div class="controls">
                  <select name="brand" id="brand" style="width:170px">
                	<option value="0">---请选择---</option>
                	</select>
                </div>
              </div>
              
              <div class="control-group span3">
                <label for="type" class="control-label">渠道</label>
                <div class="controls">
                  <select name="cus_brand" id="cus_brand" style="width:170px">
                	<option value="0">---请选择---</option>
                	</select>
                </div>
              </div>
              
              <div class="control-group span3">
                <label for="type" class="control-label">车系</label>
                <div class="controls">
                   <select name="cus_seriers" id="cus_seriers" style="width:200px">
                  	<option value="0">---请选择---</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">活动地址</label>
                <div class="controls">
                  <input class="input-small" type="text" name="act_addr" id="act_addr" value="${ctContract.act_addr}" style="width:205px;"/>
                </div>
              </div>
              <div class="control-group span6">
                <label for="contractEnd" class="control-label">活动日期</label>
                <div class="controls">
                  <div class="input-append date form_datetime">
                      <input type="text" readonly="true" name="act_date" data-date="2013-02-21" style="width:180px;" id="act_date" value="${ctContract.act_date?string("yyyy-MM-dd")}">
                      <span class="add-on"><i class="icon-remove"></i></span>
                      <span class="add-on"><i class="icon-th"></i></span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">合计总价</label>
                <div class="controls">
                  <input class="input-small" type="text" name="total_price" id="total_price" style="float:left" value="${ctContract.total_price}"/>
               	  <input type="hidden" id="con_state" value="${ctContract.con_state}">
               	  <input type="hidden" name="con_ct_id" value="${ctContract.con_ct_id}">
               	  <input type="hidden" name="con_id" value="${ctContract.con_ct_id}">
                </div>
                <label style="float:left; padding:5px 0 0 5px;">元</label>
              </div>
            </div>
            
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">备注</label>
                <div class="controls"><textarea name="con_content" id="" rows="5" style=" width:555px; float:left">${ctContract.con_content}</textarea></div>
              </div>
            </div>
            <ul class="inline">
              <label for="userCode" class="control-label" style="padding:0"></label>
              <li>
                <label class="radio">
                  <input type="radio" name="stamp" value="1" style="margin:0" <#if ctContract.stamp==1>checked="checked"</#if>>
                  客户先盖章
                </label>
              </li>
              <li>
                <label class="radio">
                  <input type="radio" name="stamp" value="2" style="margin:0" <#if ctContract.stamp==2>checked="checked"</#if>>
                  公司先盖章
                </label>
              </li>
            </ul>
         
            <div class="row-fluid" style="border-top:1px solid #ddd; margin-top:20px; padding-top:20px">
              <div class="control-group span5">
                <label for="paymentType" class="control-label">有无返利</label>
                <div class="controls">
                  <label class="radio" style=" float:left">
                    <input type="radio" name="isback" id="isback" value="1" checked="">
                    有
                  </label>
                  <label class="radio" style=" float:left; padding:5px 0 0 40px">
                    <input type="radio" name="isback" id="notback" value="0">
                    无
                  </label>
                </div>
              </div>
              <div class="control-group span5" id="showDiv">
                <label for="type" class="control-label">返利比例或xx元</label>
                <div class="controls">
                  <input class="input-small" type="text" id="backDes" name="isback_des" style="float:left" value="${conAccount.isback_des}">
                  <span style="color:grey;font-size:15px;margin-left:10px;margin-bottom:30px;">(示例：17%或xxx元)</span>
                </div>
               <!-- <label style="float:left; padding:5px 10px 0 5px; color:#999">或</label>
                <div class="controls">
                  <input class="input-mini" type="text" name="isback_des" id="isback_des" style="float:left" disabled="disabled">
                </div>
                <label style="float:left; padding:5px 0 0 5px; ">元</label>-->
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="paymentType" class="control-label">执行进度</label>
                <div class="controls">
                  <label class="radio" style=" float:left">
                    <input type="radio" name="plan" id="onTime" value="0" <#if conAccount.plan==0>checked="checked"</#if>>
                    按期执行
                  </label>
                  <label class="radio" style=" float:left; padding:5px 0 0 40px">
                    <input type="radio" name="plan" id="changeTime" value="1" <#if conAccount.plan==1>checked="checked"</#if>>
                    适时调整
                  </label>
                </div>
              </div>
              <div class="control-group span5" id="showDiv2">
                <label for="type" class="control-label">将于</label>
                <div class="controls">
                  <div class="input-append date form_datetime">
                      <input type="text" value="${conAccount.plan_des}" readonly="true" name="plan_des" id="plan_des" data-date="2013-02-21" style="float:left;width:80px;">
                      <span class="add-on" style="float:left; "><i class="icon-remove"></i></span>
                      <span class="add-on" style="float:left; "><i class="icon-th"></i></span>
                      <label style="float:left; padding:5px 10px 0 5px;">投放完毕</label>
                  </div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="paymentType" class="control-label">开票说明</label>
                <div class="controls">
                  <label class="radio" style=" float:left">
                    <input type="radio" name="bill_type" id="pingkai" value="0" checked="checked">
                    平开
                  </label>
                  <label class="radio" style=" float:left; padding:5px 0 0 40px">
                    <input type="radio" name="bill_type" id="bukai" value="2">
                    不开票
                  </label>
                  <label class="radio" style=" float:left; padding:5px 0 0 40px">
                    <input type="radio" name="bill_type" id="gaokai" value="1">
                    高开
                  </label>
                </div>
              </div>
              <div class="control-group span5" id="showDiv3">
                <label for="type" class="control-label">预计开票金额为</label>
                <div class="controls">
                  <input class="input-small" name="bill_des" id="bill_des" type="text" style="float:left" value="${conAccount.bill_des}">
                </div>
                <span style="color:grey;font-size:15px;margin-left:10px;margin-bottom:30px;">请输入保留两位小数的数字</span>
                <label style="float:left; padding:5px 10px 0 5px;">元</label>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="paymentType" class="control-label">回款预计</label>
                <div class="controls">
                  <label class="radio" style=" float:left">
                    <input type="radio" name="back_exp" id="" value="0" <#if conAccount.back_exp==0>checked="checked"</#if>>
                    分期投放，及时开票后回款
                  </label>
                </div>
              </div>
               <div class="control-group span5">
                <label class="radio" style=" float:left; padding-top:5px">
                  <input type="radio" name="back_exp" id="delay" value="1" <#if conAccount.back_exp==1>checked="checked"</#if>>
                  	预计
                </label>
                <div class="controls" style="margin-left:70px" >
                  <div class="input-append date form_datetime">
                      <input type="text" value="${conAccount.back_des}" id="back_des" readonly="true" name="back_des" data-date="2013-02-21" style="float:left;width:80px;">
                      <span class="add-on" style="float:left; "><i class="icon-remove"></i></span>
                      <span class="add-on" style="float:left; "><i class="icon-th"></i></span>
                      <label style="float:left; padding:5px 0 0 5px;">可回款</label>
                  </div>
                </div>
              </div>

            </div>
            <div class="row-fluid" style="border-bottom:1px solid #ddd; margin-bottom:20px">
              <div class="control-group span5">
                <label for="type" class="control-label">返点受益人</label>
                <div class="controls">
                  <input type="text" id="ben_person" name="ben_person" value="${conAccount.ben_person}">
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">联系方式</label>
                <div class="controls">
                  <input type="text" id="phone" name="phone" value="${conAccount.phone}">
                </div>
              </div>
            </div>
            <div class="controls">
              <p>
                <a class="btn btn-danger" href="javascript:void(0)" onclick="subContract()">生成合同</a>
              </p>
             
            </div>
            </div>
</form>

<@c.js/>
    <script type="text/javascript">
      $(".form_datetime").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true, //选择日期后自动关闭
        pickerPosition:'bottom-left'
      });
 $(document).ready(function(){
 
 	$("#cus_name").bind("click", function() {
 		$(this).attr("placeholder", "");
 	});
 	$("#act_addr").click(function() {
 		$(this).attr("placeholder", "");
 	});
 	$("#isback").click(function() {
 		$("#showDiv").attr("style", "display:''");
 	});
 	$("#notback").click(function() {
 		$("#showDiv").attr("style", "display:none");
 	});
 		$("#onTime").click(function(){
 		$("#showDiv2").attr("style","display:none");
 	});
 	if($("#onTime").attr("checked")=="checked"){
 			$("#showDiv2").attr("style","display:none");
 	}
 	$("#changeTime").click(function(){
 		$("#showDiv2").attr("style","display:''");
 	});
 	$(":radio[name='bill_type']").each(function(){
 		$(this).click(function(){
 			if(this.value==2){
 				$("#showDiv3").attr("style","display:none");
 			}else{
 				$("#showDiv3").attr("style","display:''");
 			}
 		});
 	});
 	 	//页面加载时加载所有的品牌
 	$.get("/contract/brandAndSeries/getBrand",function(data){
		$("#brand").html("<option value='0'>---请选择---</option><option value='-1'>新能源</option>");
		$("#cus_seriers").html("<option value='0'>---请选择---</option>");
		$("#cus_brand").html("<option value='0'>---请选择---</option>");
	 	var suc = eval("("+data+")");
	 	for (var i = 0; i < suc.length; i++) {
	 		$("#brand").append("<option value='"+suc[i].catalogid+"'>"+suc[i].catalogname+"</option>");
	 	};
	});
	//根据车型找渠道
	$("#brand").change(function(){
		$("#cus_brand").html("<option value='0'>---请选择---</option>");
		$("#cus_seriers").html("<option value='0'>---请选择---</option>");
		var catalogid = $(this).val();
		if(catalogid==-1){
			$("#cus_brand").append("<option value='新能源'>新能源</option>")
		}
		$.get("/contract/brandAndSeries/getiwayByBrand",{"catalogid":catalogid},function(data){
			var suc = eval("("+data+")");
			if(suc.length>0){
				for(var i=0;i<suc.length;i++){
					$("#cus_brand").append("<option value='"+suc[i].iway+"'>"+suc[i].iway+"</option>");
				}
			}
	
		});
	});
	//根据品牌获取车系
	$("#cus_brand").change(function(){
	$("#cus_seriers").html("<option value='0'>---请选择---</option>");
	var brid = $(this).val();
	var catalogid = $("#brand").val();
	if(catalogid!=0 && brid!=0){
		if(brid=="新能源"){
			$("#cus_seriers").html("<option value='0'>---请选择---</option><option value='-2'>新能源</option>")
		}else{
		$("#cus_seriers").html("<option value='0'>---请选择---</option><option value='-1'>全系</option>");
		}
		$.get("/contract/brandAndSeries/getSeriesByBrand",{"catalogid":catalogid,"brid":brid},function(data){
			var suc = eval("("+data+")");
			for (var i = 0; i < suc.length; i++) {
 				$("#cus_seriers").append("<option value='"+suc[i].catalogid+"'>"+suc[i].catalogname+"</option>");
 			};

		});
	}
});
 });

 function isCom() {

	var check = $("#iscomplete").prop("checked")
	if (check == true) {
		$("#showAccount").attr("style","display:''");
	}else{
		$("#showAccount").attr("style","display:none");
	}
}

function subContract() {
	var flag = true;
	var telreg = /^1[3,4,5,7,8][0-9]{9}$/;
	var tel = $("#cus_tel").val();
	var numreg = /^\d+(\.\d{2})?$/;
	var num = $("#con_total_price").val();
	var num1 = $("#total_price").val();
	var conState = $("#con_state").val();
	var actAddr = $("#act_addr").val();
	var cusName = $("#cus_name").val();
	var cus_brand = $("#cus_brand").val();
	var cus_series = $("#cus_seriers").val();
	if(cus_brand==0){
		alert("请选择品牌!");
		flag = false;
		return;
	}
	
	if(cus_series==0){
		alert("请选择车系!");
		flag = false;
		return;
	}
	var flag3 = false;
	var gaizhang = $(":radio[name='stamp']");
	
	
	gaizhang.each(function(){
		if($(this).prop("checked")){
			flag3 = true;
		}
	});
	
	if(!flag3){
		alert("请选择客户公司谁先盖章！");
		return;	
	}
	
	if($("#act_date").val() == ""){
		alert("请输入活动时间!");
		flag = false;
		return;
	}

	if(tel==""){
			alert("请输入手机号");
			flag = false;
			return;
	}

	if (num1=="") {
		alert("请填写合计总价");
		flag = false;
		return;
	}

	
	
	if(actAddr==""){
		alert("请输入活动地址！");
		flag = false;
		return;
	}
	
	
	if(cusName==""){
			alert("请填写客户姓名");
			flag = false;
			return;
		}

	
var phone = $("#phone").val();
	var phonereg = /^1[3,4,5,7,8][0-9]{9}$/;
	var isBack = $("#isback").prop("checked");
	var benPerson = $("#ben_person").val();
	var backDes = $("#backDes").val();
	var bukai = $("#bukai").prop("checked");
	var planDes = $("#plan_des").val();
	var billDes = $("#bill_des").val();
	var delay = $("#delay").prop("checked");
	var changetime = $("#changeTime").prop("checked");
	var backTimeDes = $("#back_des").val();
	if(isBack && backDes==""){
		alert("请填写返利详细信息!");
		flag=false;
		return;
	}
	if(!bukai && billDes==""){
		alert("请填写开票金额");
		flag = false;
		return;
	}
	if(changetime && planDes==""){
		alert("请填写执行进度推迟时间");
		flag = false;
		return;
	}
	if(delay && backTimeDes==""){
		alert("请填写回款预计时间");
		flag = false;
		return;
	}
	if(isBack){
		if(benPerson==""){
			alert("请填写受益人");
			flag = false;
			return;
		}
		if(phone==""){
			alert("请填写补充说明手机号");
			flag = false;
			return;
		}
	}
	if(conState != 5 && conState != 4){
		alert("合同已提交，不能修改。");
		flag=false;
	}

	if (flag) {
		$("#form1").submit();
	}

}
function subAccount(){
	var flag = true;
	var phone = $("#phone").val();
	var phonereg = /^1[3,4,5,7,8][0-9]{9}$/;
	var isBack = $("#isback").prop("checked");
	
	var backDes = $("#backDes").val();
	var bukai = $("#bukai").prop("checked");
	var planDes = $("#plan_des").val();
	var billDes = $("#bill_des").val();
	var delay = $("#delay").prop("checked");
	var changetime = $("#changeTime").prop("checked");
	var backTimeDes = $("#back_des").val();
	if(isBack && backDes==""){
		alert("请填写返利详细信息!");
		flag=false;
		return;
	}
	if(!bukai && billDes==""){
		alert("请填写开票金额");
		flag = false;
		return;
	}
	if(changetime && planDes==""){
		alert("请填写执行进度推迟时间");
		flag = false;
		return;
	}
	if(delay && backTimeDes==""){
		alert("请填写回款预计时间");
		flag = false;
		return;
	}
	
	if(phone==""){
			alert("请填写补充说明手机号");
			flag = false;
			return;
		}
	if(accType!="" && (accType != "0" && accType != "3")){
		alert("合同补充说明已提交，不能修改");
		flag = false;
		return;
	}
	

	if(flag){
		$("#form2").submit();
	}
}
    </script>
  </body>
</html>