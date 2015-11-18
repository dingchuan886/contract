<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
<title>我的合同</title>
 <@c.html/>
 </head>
  <body>
     <@c.head/>
	 <@c.left act=2/>
	 <div class="span10">
	 <ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">合同补充</li>
          </ul>
         
            <h2>车团合同补充</h2>
	 	<ul class="nav nav-tabs">
	 		<!-- to mycontract -->
            <li class=""><a href="javascript:void(0)" onclick="javascript:window.location.href='${contract_ct_url}contract/conSupply/toCzSupply'" data-toggle="tab">众智车展合同</a></li>
       
            <li class=""><a href="javascript:void(0)" onclick="javascript:window.location.href='${contract_ct_url}contract/conSupply/toZhSupply'" data-toggle="tab">众智315广告合同</a></li>
            <li class="active"><a href="javascript:void(0)" onclick="javascript:window.location.href='${contract_ct_url}contract/conSupply/toCtSupply'" data-toggle="tab">车团合同</a></li>
          </ul>
          
          <form action="${contract_ct_url}contract/conSupply/toCtSupply" class="form-horizontal" id="form1">
           <div class="row-fluid">
               <div class="control-group span5">
                <label for="type" class="control-label">客户名称</label>
                <div class="controls">
                  <input type="text" name="cusName" value="${pageCondition.cusName}"/>
                  <input type="hidden" name="pageNum" value="1" id="pageNum1">
                </div>
              </div>
            <div class="control-group span5">
                <label for="type" class="control-label">合同编号</label>
                <div class="controls">
                  <input type="text" name="conId" value="${pageCondition.conId}"/>
                </div>
              </div>
            <div class="row-fluid">
              
             <div class="control-group span5">
                <label for="type" class="control-label">签订时间</label>
                <div class="controls">
                   <div id="dateuser" class="input-append date form_datetime">
                      <input type="text" readonly="true" name="createTime" style="width:180px;" value="${pageCondition.createTime}">
                      <span class="add-on"><i class="icon-remove"></i></span>
                      <span class="add-on"><i class="icon-th"></i></span>
                  </div>
                </div>
              </div>
              
            </div>
               <div align="center"><button onclick="javascript:this.form.submit()">查询</button></div>
           
          </form>
          <div id="showDiv" style="display:none"><h4>提交中.....</h4></div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>合同编号</th>
                  <th>填写时间</th>
                  <th>客户名称</th>
                  <th>品牌</th>
                  <th>乙方电话</th>
                  <th>合同金额(元)</th>
                  <th>车辆台数/活动场次</th>
                  <th>合同总金额(元)</th>
                  <th>执行金额(元)</th>
                  <th>活动日期</th>
                  <th>执行日期</th>
                  <th>操作</th>
                  
                </tr>
              </thead>
              <tbody>
              <#list myCtContract.list as ct>
                <tr <#if ct_index%2==0>style="background-color:#FDD8C6"<#else>style="background-color:#CBE4E8"</#if>>
                  <td>${ct.conCt.con_ct_id}</td>
                  <td>${ct.conCt.create?string("yyyy-MM-dd")}</td>
                  <td>${ct.conCt.cus_name}</td>
                  <td>${ct.conCt.cus_brand}</td>
                  <td>${ct.conCt.cus_tel}</td>
                  <td>${ct.conCt.total_price}</td>
                  <td><input name="car_count" value="${ct.supCt.car_count}" style="width:50px"></td>
                  <td><input name="con_total_price" value="${ct.supCt.con_total_price}" style="width:90px"></td>
                  <td><input name="exe_price" value="${ct.supCt.exe_price}" style="width:90px"></td>
                  <td>${ct.conCt.act_date?string("yyyy-MM-dd")}</td>
                  <td><div class="input-append date form_datetime">
                      <input type="text" readonly="true" name="exe_date" value="${ct.supCt.exe_date}" style="width:80px">
                      <span class="add-on"><i class="icon-remove"></i></span>
                      <span class="add-on"><i class="icon-th"></i></span>
                  </div></td>
                  <td>
                  <a href="javascript:void(0)" onclick="viewCon(this)">查看合同</a>&nbsp&nbsp
                  <#if (ct.billCount==0)>
                 		<a href="javascript:void(0)" onclick="subSup(this)">提交/修改</a>
                 	</#if>
                  </td>
                </tr>
                </#list>
              
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
	<div align="right">
		<a href="javascript:void(0)" onclick="changePage(1)">首页</a>&nbsp&nbsp&nbsp
		<a href="javascript:void(0)" onclick="changePage(${myCtContract.pageNum-1})">上一页</a>&nbsp
		第${myCtContract.pageNum}/${myCtContract.totalPages}页		
		<a href="javascript:void(0)" onclick="changePage(${myCtContract.pageNum+1})">下一页</a>&nbsp&nbsp		
		<a href="javascript:void(0)" onclick="changePage(${myCtContract.totalPages})">尾页</a>&nbsp&nbsp		
		<input type="text" id="pageNum" value="${myCtContract.pageNum}" style="width:25px;"/><button type="button" onclick="changePage1()">跳页</button>&nbsp	&nbsp&nbsp		
			
	</div>
<@c.js/>
<script type="text/javascript">
$(".form_datetime").datetimepicker({
        startView: 2,
		weekStart:1,
        minView: 2, //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true //选择日期后自动关闭
      });
$("#dateuser").datetimepicker('remove');
$("#dateuser").datetimepicker({
        startView: 3,
		weekStart:1,
        minView: 3, //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true //选择日期后自动关闭
      });
$(document).ready(function(){
	$("#close1").bind("click",function(){
		$("#myModal").attr({"class":"modal hide fade","aria-hidden":"true","style":"display:none;"});
	});
	
	$("#close2").bind("click",function(){
		$("#myModal").attr({"class":"modal hide fade","aria-hidden":"true","style":"display:none;"});
	});
});

function changePage(pageNum){
	if(pageNum<1){
		pageNum = 1;
	}
	if(pageNum>${myCtContract.totalPages}){
		pageNum = ${myCtContract.totalPages};
	}
	$("#pageNum1").val(pageNum);
	$("#form1").submit();
}

function changePage1(){
	var pageNum = $("#pageNum").val();
	changePage(pageNum);
}


//查看合同
function viewCon(obj){
	var id = $(obj).parent().siblings("td").eq(0).html();
	window.open("/contract/myContract/viewCtContract?conId="+id);
}

function subSup(data){
	var numreg = /^\d+(\.\d{2})?$/;
	var carCount = $(data).parent().parent("tr").find(":text[name='car_count']").val();
	var conTotalPrice = $(data).parent().parent("tr").find(":text[name='con_total_price']").val();
	var price = $(data).parent().parent("tr").find(":text[name='exe_price']").val();
	var date = $(data).parent().parent("tr").find(":text[name='exe_date']").val();
	var conId = $(data).parent().siblings("td:first").html();
	var flag = false;
	if(!numreg.test(price)){
		alert("请正确填写执行金额!");
		flag = false;
		return;
	}
	if(!numreg.test(conTotalPrice)){
		alert("请正确填写合同总金额!");
		flag = false;
		return;
	}
	if(date==""){
		alert("请填写执行日期!");
		flag = false;
		return;
	}
	if(!numreg.test(carCount)){
		alert("请正确填写车辆台数/活动场次");
		flag = false;
		return;
	}
	flag = confirm("确定提交吗?");
	if(flag){
		$.ajax({
			url:"${contract_ct_url}contract/conSupply/saveCtSupply",
			data:{"exePrice":price,"exeDate":date,"conId":conId,"con_total_price":conTotalPrice,
				"car_count":carCount
			},
			beforeSend:function(){
				$("#showDiv").show();
			},
			success:function(data){
				$("#showDiv").hide();
				var suc = eval("("+data+")");
				if(suc.back=="200"){
					alert("提交成功!");
					window.location.reload();
				}else{
					alert("提交失败!");
					window.location.reload();
				}
			}

		});
	}
}

</script>
  </body>
</html>