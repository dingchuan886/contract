<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>添加新合同-众智315广告合同</title>
 <@c.html/>
 </head>
  <body>
  	<@c.head/>
	<@c.left act=0/>
        <div class="span10">
          <ul class="nav nav-tabs">
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/czContract/toAddCzContract'" data-toggle="tab">众智车展合同</a></li>
            <li class="active"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/zhContract/toAddZhContract'" data-toggle="tab">众智315广告合同</a></li>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/ctContract/toAddCtContract'" data-toggle="tab">车团合同</a></li>
          </ul>
          <form action="/contract/zhContract/zhContractPreview" class="form-horizontal" method="post" id="form1" target="_blank">
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
                    <option value="1">大客户</option>
                    <option value="2">区域</option>
                    <option value="3">经销商</option>
                  </select>
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">客户名称</label>
                <div class="controls">
                  <input type="text" name="cus_name" id="cus_name" placeholder="客户名称">
                </div>
              </div>
            </div>
           <div class="row-fluid">
            
            <ul class="inline span5">

              <label for="userCode" class="control-label" style="padding:0">物料有无</label>
              <li>
                <label class="radio">
                  <input type="radio" name="material" style="margin:0" value="0">
                  无
                </label>
              </li>
              <li>
                <label class="radio">
                  <input type="radio" name="material" style="margin:0" value="1">
                  有
                </label>
              </li>
          
            
          </ul> 
          <div class="control-group span5">
                <label for="type" class="control-label">合同类型</label>
                <div class="controls">
                  <select name="con_type">
                    <option value="0">会员</option>
                    <option value="1">硬广</option>
                    <option value="2">会员兼硬广</option>
                    <option value="3">广告+活动</option>
                  </select>
                </div>
              </div>
          
         </div>
            <div class="row-fluid" style="border-bottom:1px solid #ddd">
              <div class="control-group span5">
                <label for="type" class="control-label">地址</label>
                <div class="controls">
                  <input type="text" name="cus_addr" placeholder="">
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">电话</label>
                <div class="controls">
                  <input type="text" name="cus_tel" id="cus_tel"><br/>
                  <span id="cus_telreg" style="color:red;font-size:10px"></span>
                </div>
              </div>
            </div>
            <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
              <table class="table table-bordered">
              <input type="button" value="添加行" onclick="add()"/>
                <thead>
                  <tr>
                    <th>媒体</th>
                    <th>位置</th>
                    <th>规格</th>
                    <th>品牌</th>
                    <th>投放日期</th>
                    <th>刊例价</th>
                    <th>合计单价</th>
                    <th>备注</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody id="tbody">
                  <tr class="nowtr">
                    <td>
                    <div class="btn-group">
                     <!--
                     <button class="btn dropdown-toggle" data-toggle="dropdown">315che.com<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                      </ul>
                      -->
                      <select class="btn dropdown-toggle" name="tbConZhSubs.media" style="width:130px">
                      	<option value="1">315che.com</option>
                      	<option value="2">框架</option>
                      	<option value="3">其他</option>
                      </select>
                    </div>
                    </td>
                    <td><input type="text" name="tbConZhSubs.position" style="width:100px"/></td>
                    <td><input type="text" name="tbConZhSubs.standard" style="width:80px"/></td>
                    <td><input type="text" name="tbConZhSubs.brand" style="width:80px"/></td>
                    <td>
                    
                      <!-- <input type="text" value="" readonly="" data-date="2013-02-21" style="width:100px;"> -->
                      <input type="text" name="tbConZhSubs.put_date" style="width:150px;" size="10">
                      
                   
                    </td>
                    <td><input type="text" name="tbConZhSubs.copyright" style="width:100px"/></td>
                    <td><input type="text" name="tbConZhSubs.unit_price" style="width:100px"/></td>
                    <td><input type="text" name="tbConZhSubs.content" style="width:100px"/></td>
                    <td><a href="javascript:void(0)" onclick="del(this)">删除</a></td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="row-fluid" style=" padding-top:20px">
              <div class="control-group span5">
                <label for="type" class="control-label">合计执行总价</label>
                <div class="controls">
                  <input class="input-small" type="text" name="con_total_price" style="float:left" id="con_total_price">
                </div>
                <label style="float:left; padding:5px 0 0 5px;">元</label>
              </div>
              
            </div>

            <ul class="inline">
              <label for="userCode" class="control-label" style="padding:0"></label>
              <li>
                <label class="radio">
                  <input type="radio" style="margin:0" name="stamp" value="1">
                  客户先盖章
                </label>
              </li>
              <li>
                <label class="radio">
                  <input type="radio" style="margin:0" name="stamp" value="2">
                  公司先盖章
                </label>
              </li>
            </ul>
          
             <div class="row-fluid" style="border-top:1px solid #ddd; margin-top:20px; padding-top:20px">
              <div class="control-group span5">
                <label for="paymentType" class="control-label">有无返利</label>
                <div class="controls">
                  <label class="radio" style=" float:left">
                    <input type="radio" name="isback" id="isback" value="1" checked="checked">
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
                    <input type="radio" name="plan" id="onTime" value="0" checked="checked">
                    按期执行
                  </label>
                  <label class="radio" style=" float:left; padding:5px 0 0 40px">
                    <input type="radio" name="plan" id="changeTime" value="1">
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
                  <input class="input-small" name="bill_des" id="bill_des" type="text" style="float:left">
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
                    <input type="radio" name="back_exp" id="" value="0" checked="checked">
                    分期投放，及时开票后回款
                  </label>
                </div>
              </div>
               <div class="control-group span5">
                <label class="radio" style=" float:left; padding-top:5px">
                  <input type="radio" name="back_exp" id="delay" value="1">
                  	预计
                </label>
                <div class="controls" style="margin-left:70px" >
                  <div class="input-append date form_datetime">
                      <input type="text" id="back_des" readonly="true" name="back_des" data-date="2013-02-21" style="float:left;width:80px;">
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
                  <input type="text" id="ben_person" name="ben_person">
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">联系方式</label>
                <div class="controls">
                  <input type="text" id="phone" name="phone">
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
var flag2 = true;
$(document).ready(function(){
 
	$(".form_datetime").datetimepicker({
	        minView: "month", //选择日期后，不会再跳转去选择时分秒
	        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
	        language: 'zh-CN', //汉化
	        autoclose:true //选择日期后自动关闭
	      });
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

 });
 function add(){
 	//$(".form_datetime:eq(0)").datetimepicker('remove');
	var tr = $(".nowtr:last").clone(true,true)[0].innerHTML;
	$("#tbody").append("<tr>"+tr+"</tr>");
	/*for(var i=0;i<$(".form_datetime").length;i++){
		$(".form_datetime:eq("+i+")").datetimepicker('remove');
		$(".form_datetime:eq("+i+")").datetimepicker({
	        minView: "month", //选择日期后，不会再跳转去选择时分秒
	        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
	        language: 'zh-CN', //汉化
	        autoclose:true //选择日期后自动关闭
	      });
	}*/
}

function del(date){
	if ($(date).parent("td").parent("tr").siblings("tr").size() > 0) {
		$(date).parent("td").parent("tr").remove();
	};

}
function subContract(){
    var flag = true;
	$("select[name='tbConZhSubs.media']").each(function(i){
			$(this).attr("name","tbConZhSubs["+i+"].media");
		});
		$(":input[name='tbConZhSubs.position']").each(function(i){
			$(this).attr("name","tbConZhSubs["+i+"].position");
		});
		$(":input[name='tbConZhSubs.standard']").each(function(i){
			$(this).attr("name","tbConZhSubs["+i+"].standard");
		});
		$(":input[name='tbConZhSubs.brand']").each(function(i){
			$(this).attr("name","tbConZhSubs["+i+"].brand");
		});
		$(":input[name='tbConZhSubs.put_date']").each(function(i){
			$(this).attr("name","tbConZhSubs["+i+"].put_date");
		});
		$(":input[name='tbConZhSubs.copyright']").each(function(i){
			$(this).attr("name","tbConZhSubs["+i+"].copyright");
		});
		$(":input[name='tbConZhSubs.unit_price']").each(function(i){

			$(this).attr("name","tbConZhSubs["+i+"].unit_price");
		});
		$(":input[name='tbConZhSubs.content']").each(function(i){
			$(this).attr("name","tbConZhSubs["+i+"].content");
		})


		var telreg = /^1[3,4,5,7,8][0-9]{9}$/;
		var tel = $("#cus_tel").val();
		var numreg = /^\d+(\.\d{2})?$/;
		var num = $("#con_total_price").val();
		var cusName = $("#cus_name").val();
		
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
	
		if(tel==""){
			alert("请输入手机号");
			flag = false;
			return;
		}
		
		if(num==""){
			alert("请填写合计执行总价");
			flag = false;
			return;
		}
	
		if(!numreg.test(num)){
			alert("合计执行总价不符合规范");
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
	var actPrice = $("#act_price").val();
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
	if(!flag2){
		alert("已提交，请不要重复提交");
		return;
	}
	if(flag){
	$("#form1").submit();
	flag2 = false;
	}
}

function myflag(flag){
	return flag;
}
 function isCom() {

	var check = $("#iscomplete").prop("checked")
	if (check == true) {
		$("#showAccount").attr("style","display:''");
	}else{
		$("#showAccount").attr("style","display:none");
	}
}
    </script>
  </body>
</html>