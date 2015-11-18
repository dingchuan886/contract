<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
<title>浏览合同</title>
 <@c.html/>
 </head>
  <body>
     <@c.head/>
	 <@c.left act=6/>
	 <div class="span10">
	 	<ul class="nav nav-tabs">
	 		<!-- to mycontract -->
            <li class="active"><a href="javascript:void(0)" onclick="javascript:window.location.href='${contract_ct_url}contract/deptManager/showCzDeptManager'" data-toggle="tab">众智车展合同</a></li>
            <li class=""><a href="javascript:void(0)" onclick="javascript:window.location.href='${contract_ct_url}contract/deptManager/showZhDeptManager'" data-toggle="tab">众智315广告合同</a></li>
            <li class=""><a href="javascript:void(0)" onclick="javascript:window.location.href='${contract_ct_url}contract/deptManager/showCtDeptManager'" data-toggle="tab">车团合同</a></li>
          </ul>
          <form action="/contract/deptManager/showCzDeptManager" class="form-horizontal" id="form1">
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">合同状态</label>
                <div class="controls">
                  <select name="conState" id="">
                    <option value=""></option>
                    <option value="0" <#if pageCondition.conState==0>selected</#if>>等待审核</option>
                    <option value="1" <#if pageCondition.conState==1>selected</#if>>经理审核通过</option>
                    <option value="7" <#if pageCondition.conState==7>selected</#if>>区域经理审核通过</option>
                    <option value="2" <#if pageCondition.conState==2>selected</#if>>流程部审核通过</option>
                    <option value="3" <#if pageCondition.conState==3>selected</#if>>流程部已归档</option>
                  </select>
                </div>
              </div>
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">业务员</label>
                <div class="controls">
                  <input type="text" name="userName" value="${pageCondition.userName}"/>
                  <input type="hidden" name="pageNum" value="1" id="pageNum1">
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">合同类别</label>
                <div class="controls">
                  <select name="conType">
                  	<option value=""></option>
                  	<option value="0" <#if pageCondition.conType==0>selected</#if>>车展</option>
                  	<option value="1" <#if pageCondition.conType==1>selected</#if>>车展+广告</option>
                  </select>
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">客户名称</label>
                <div class="controls">
                  <input type="text" name="cusName" value="${pageCondition.cusName}"/>
                </div>
              </div>
              
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">签订时间</label>
                <div class="controls">
                   <div class="input-append date form_datetime">
                      <input type="text" readonly="true" name="createTime" style="width:180px;" value="${pageCondition.createTime}">
                      <span class="add-on"><i class="icon-remove"></i></span>
                      <span class="add-on"><i class="icon-th"></i></span>
                  </div>
                </div>
              </div>
               <div><button onclick="doSubmit('/contract/deptManager/showCzDeptManager')">查询</button>
               	<button onclick="doSubmit('/contract/downloadAttach/downloadDeptCzExcell')">下载数据</button></div>
            </div>
           
          </form>
          <div hidden="true" id="loading"><h4>数据加载中，请稍等...</h4></div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>合同编号</th>
                  <th>合同状态</th>
                  <th>站点</th>
                  <th>业务员</th>
                  <th>填写时间</th>
                  <th>客户名称</th>
                  <th>品牌</th>
                  <th>乙方电话</th>
                  <th>是否归档</th>
                  <th>合同金额(元)</th>
                  <th>执行金额(元)</th>
                  <th>活动日期</th>
                  <th>执行日期</th>
                  <th>开票日期</th>
                  <th>开票金额(元)</th>
                  <th>回款日期</th>
                  <th>回款金额(元)</th>
                  <th>返利金额(元)</th>
                  <th>操作</th>
                  
                </tr>
              </thead>
              <tbody>
              <#list deptCzContract.list as cz>
              <#if cz.conCz.con_state != 5>
                <tr <#if cz_index%2==0>style="background-color:#FDD8C6"<#else>style="background-color:#CBE4E8"</#if>>
                  <td>
                  ${cz_index+1+(deptCzContract.pageNum-1)*10}
                  </td>
                  <td>${cz.conCz.con_cz_id}</td>
                  <td><#if cz.conCz.con_state==0>
                  	等待审核
                  </#if>
                  <#if cz.conCz.con_state==1>
                  	经理已审核
                  </#if>
                  <#if cz.conCz.con_state==7>
                  	区域经理已审核
                  </#if>
                  <#if cz.conCz.con_state==2>
                  	流程部已审核
                  </#if>
                  <#if cz.conCz.con_state==3>
                  	流程部已归档
                  </#if>
                  <#if cz.conCz.con_state==4>
                  	已驳回
                  </#if></td>
                  <td>${cz.orgName}${cz.deptName}</td>
                  <td>${cz.conCz.user_name}</td>
                   <td>${cz.conCz.create?string("yyyy-MM-dd")}</td>
                  <td>${cz.conCz.cus_name}</td>
                  <td>${cz.conCz.cus_brand}</td>
                  <td>${cz.conCz.cus_tel}</td>
                  <td><#if cz.conCz.con_state==3>是
                  	<#else>
                  	否
                  </#if>
                   </td>
                  <td>${cz.conCz.con_total_price}</td>
                  <td>${cz.supCz.exe_price}</td>
                   <td>${cz.conCz.act_date}</td>
                   <td>${cz.supCz.exe_date}</td>
                    <#--开票日期 -->
                 <td>
                 <#if (cz.conBills?size>0)>
                 	<#list cz.conBills as bill>
                 	<#if (bill_index<(cz.conBills?size-1))>
                 		<span>${bill.create?string("yyyy-MM-dd")}</span><hr color="black" SIZE="3"/>
              				<#elseif bill_index==(cz.conBills?size-1)>
              					<span>${bill.create?string("yyyy-MM-dd")}</span>
              				</#if>
                 	</#list>
                 </#if>
                 </td>
                 <#--开票金额 -->
                 <td>
                 <#if (cz.conBills?size>0)>
                 	<#list cz.conBills as bill>
                 	<#if (bill_index<(cz.conBills?size-1))>
                 		<span>${bill.sal_bill}</span><hr color="black" SIZE="3"/>
              				<#elseif bill_index==(cz.conBills?size-1)>
              					<span>${bill.sal_bill}</span>
              				</#if>
                 	</#list>
                 </#if>
                 </td>
                 <#--回款日期 -->
                 <td>
                 <#if (cz.conBills?size>0)>
                 	<#list cz.conBills as bill>
                 	<#if (bill_index<(cz.conBills?size-1))>
                 		<span>${bill.rm_date?string("yyyy-MM-dd")}</span><hr color="black" SIZE="3"/>
              				<#elseif bill_index==(cz.conBills?size-1)>
              					<span>${bill.rm_date?string("yyyy-MM-dd")}</span>
              				</#if>
                 	</#list>
                 </#if>
                 </td>
                 <#--回款金额 -->
                 <td>
                 <#if (cz.conBills?size>0)>
                 	<#list cz.conBills as bill>
                 	<#if (bill_index<(cz.conBills?size-1))>
                 		<span>${bill.rm_account}</span><hr color="black" SIZE="3"/>
              				<#elseif bill_index==(cz.conBills?size-1)>
              					<span>${bill.rm_account}</span>
              				</#if>
                 	</#list>
                 </#if>
                 </td>
                 <#--返利金额 -->
                 <td>
                 <#if (cz.conRebates?size>0)>
                 	<#list cz.conRebates as rebate>
                 	<#if (rebate_index<(cz.conRebates?size-1))>
                 		<span>${rebate.back_actual}</span><hr color="black" SIZE="3"/>
              				<#elseif rebate_index==(cz.conRebates?size-1)>
              					<span>${rebate.back_actual}</span>
              				</#if>
                 	</#list>
                 </#if>
                 </td>
                  <td>
                  <a href="javascript:void(0)" onclick="viewCon(this)">查看合同</a>&nbsp&nbsp
                  <#if cz.conCz.con_state==3><a href="javascript:void(0)" onclick="down(this)">下载附件</a></#if>
                  <#if cz.conCz.con_state==3><a href="javascript:void(0)" onclick="preView(this)">预览附件</a></#if>
                  </td>
                </tr>
                </#if>
                </#list>
            <tr>
              <td>统计</td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td id="conTotal">${allCzMoney.conMoney}</td>
              <td>${allCzMoney.actMoney}</td>
              <td></td>
              <td></td>
              <td></td>
              <td id="billMoney">${allCzMoney.billMoney}</td>
              <td></td>
              <td id="backMoney">${allCzMoney.rmMoney}</td>
              <td id="rebateMoney">${allCzMoney.rebateMoney}</td>
              <td></td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
	<div align="right">
		<a href="javascript:void(0)" onclick="changePage(1)">首页</a>&nbsp&nbsp&nbsp
		<a href="javascript:void(0)" onclick="changePage(${deptCzContract.pageNum-1})">上一页</a>&nbsp
		第${deptCzContract.pageNum}/${deptCzContract.totalPages}页		
		<a href="javascript:void(0)" onclick="changePage(${deptCzContract.pageNum+1})">下一页</a>&nbsp&nbsp		
		<a href="javascript:void(0)" onclick="changePage(${deptCzContract.totalPages})">尾页</a>&nbsp&nbsp		
		<input type="text" id="pageNum" value="${deptCzContract.pageNum}" style="width:25px;"/><button type="button" onclick="changePage1()">跳页</button>&nbsp	&nbsp&nbsp		
	</div>

    <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="close1">×</button>
        <h3 id="myModalLabel">流程信息</h3>
      </div>
      <div class="modal-body">
        <p id="p"><!--<span style=" float:right">合同编号：</span>业务员：--></p>
        <table class="table">
              <thead>
                <tr>
                  <th>流程详情</th>
                  <th>操作人</th>
                 <!-- <th>时间</th> -->
                  <th>备注</th>
                </tr>
              </thead>
              <tbody>
                <tr class="success">
                  <td id="tr1td1"></td>
                  <td id="tr1td2"></td>
                  <td id="tr1td3"></td>
                </tr>
                <tr class="error">
                  <td id="tr2td1"></td>
                  <td id="tr2td2"></td>
                  <td id="tr2td3"></td>
                </tr>
                <tr class="warning">
                  <td id="tr3td1"></td>
                  <td id="tr3td2"></td>
                  <td id="tr3td3"></td>
                </tr>
                <tr class="info">
                  <td id="tr4td1"></td>
                  <td id="tr4td2"></td>
                  <td id="tr4td3"></td>
                </tr>
                <tr class="success">
                  <td id="tr5td1"></td>
                  <td id="tr5td2"></td>
                  <td id="tr5td3"></td>
                </tr>
                
              </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button class="btn" data-dismiss="modal" id="close2">关闭</button>
      </div>
    </div>
<@c.js/>
<script type="text/javascript">
$(".form_datetime").datetimepicker({
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
	/*var totalbill = 0;
	var b_index = 0;
	var billmoney = new Array();
	<#list deptCzContract.list as cz>
		<#if cz.conCz.con_state != 5>
			<#if (cz.conBills?size>0)>
				<#list cz.conBills as bill>
					billmoney[b_index] = ${bill.sal_bill};
					b_index++;
				</#list>
			</#if>
		</#if>
	</#list>
	if(billmoney.length>0){
		for(var i=0;i<billmoney.length;i++){
			totalbill += billmoney[i];
		}
	}
	$("#billMoney").html(totalbill);
	var conTotal = new Array();
	var totalConMoney = 0;
	<#list deptCzContract.list as cz>
		<#if cz.conCz.con_state != 5>
			conTotal[${cz_index}] = ${cz.conCz.con_total_price};
		</#if>
	</#list>
	if(conTotal.length>0){
		for(var i=0;i<conTotal.length;i++){
			totalConMoney += conTotal[i];
		}
	}
	$("#conTotal").html(totalConMoney);
	var backMoney = 0;
	var rm_index = 0;
	var backMoneyArray = new Array();
	<#list deptCzContract.list as cz>
		<#if (cz.conBills?size>0)>
			<#list cz.conBills as bill>
				backMoneyArray[rm_index] = ${bill.rm_account};
				rm_index++;
			</#list>
		</#if>
	</#list>;
	if(backMoneyArray.length>0){
		for(var i=0;i<backMoneyArray.length;i++){
			backMoney += backMoneyArray[i];
		}
	}
	$("#backMoney").html(backMoney);
	var rebateMoney = 0;
	var r_index = 0;
	var rebateMoneyArray = new Array();
	<#list deptCzContract.list as cz>
		<#if (cz.conRebates?size>0)>
			<#list cz.conRebates as rebate>
				rebateMoneyArray[r_index] = ${rebate.back_actual}
				r_index++;
			</#list>
		</#if>
	</#list>
	if(rebateMoneyArray.length>0){
		for(var i=0;i<rebateMoneyArray.length;i++){
			rebateMoney +=  rebateMoneyArray[i];
		}
	}
	$("#rebateMoney").html(rebateMoney);
	*/
});
	function changePage(pageNum){
	if(pageNum<1){
		pageNum = 1;
	}
	if(pageNum>${deptCzContract.totalPages}){
		pageNum = ${deptCzContract.totalPages};
	}
	$("#pageNum1").val(pageNum);
	$("#form1").submit();
}

function changePage1(){
	var pageNum = $("#pageNum").val();
	changePage(pageNum);
}

function showFlow(data){
	var id = $(data).parent().siblings("td:first").html();
	
	$.ajax({
		url:"/contract/myContract/showCzFlow",
		type:"GET",
		data:{"czContractId":id},
		beforeSend:function(XMLHttpRequest){
                      $("#loading").show();
        },
        success:function(suc){
         $("#loading").hide();
        	$("#myModal").attr({"class":"modal hide fade in","aria-hidden":"false","style":"display:block;"});
		var flowName = eval("("+suc+")");
		var span = "<span style=' float:right'>合同编号："+flowName.conId+"</span>业务员："+flowName.userName;
		$("#p").html(span);
		$("#tr1td1").html("");
		$("#tr1td2").html("");
		$("#tr1td3").html("");
		$("#tr2td1").html("");
		$("#tr2td2").html("");
		$("#tr2td3").html("");
		$("#tr3td1").html("");
		$("#tr3td2").html("");
		$("#tr3td3").html("");
		$("#tr4td1").html("");
		$("#tr4td2").html("");
		$("#tr4td3").html("");
		$("#tr5td1").html("");
		$("#tr5td2").html("");
		$("#tr5td3").html("");
		if(flowName.con_state==0){
			$("#tr1td1").html("1、等待审核");
		}
		if(flowName.con_state==1){
			$("#tr1td1").html("1、等待审核");
			$("#tr2td1").html("2、经理审核通过");
			$("#tr2td2").html(""+flowName.manageCheck);
			
		}
		if(flowName.con_state==2){
			$("#tr1td1").html("1、等待审核");
			$("#tr2td1").html("2、经理审核通过");
			$("#tr2td2").html(""+flowName.manageCheck);
			$("#tr3td1").html("3、流程部已下载");
			$("#tr3td2").html(""+flowName.flowCheck);
		}
		if(flowName.con_state==3){
			$("#tr1td1").html("1、等待审核");
			$("#tr2td1").html("2、经理审核通过");
			$("#tr2td2").html(""+flowName.manageCheck);
			$("#tr3td1").html("3、流程部已下载");
			$("#tr3td2").html(""+flowName.flowCheck);
			$("#tr4td1").html("4、流程部已归档");
			$("#tr4td2").html(""+flowName.flowCheck2);
		}
		if(flowName.con_state==4){
			$("#tr5td1").html("已驳回");
			$("#tr5td2").html(""+flowName.reject);
			$("#tr5td3").html(""+flowName.content);
		}
		
        }
	});
}

//查看开票
function viewBill(obj){
	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/tbConBill/viewBills?id="+id);

}

//开票申请
function addBill(obj){
	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/tbConBill/toAddConBill?id="+id);
}

//查看返利
function viewRebate(obj){
	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/tbConRebate/rebateBill?conId="+id);
}
//申请返利
function addRebate(obj){
	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/tbConRebate/toAddRebate?conId="+id);

}

//查看合同
function viewCon(obj){
	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/flowViewContract/viewCzContract?conId="+id);
}
//修改合同
function editCon(obj){
	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/czContract/toupdateCzContract?conId="+id);
}
//提交合同
function subCon(obj){
	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/czContract/viewCzContract?conId="+id);
}
//删除合同
function delCon(obj){

	var flag = confirm("确定删除吗？");
	var id = $(obj).parent().siblings("td").eq(1).html();
	var pageNum = ${deptCzContract.pageNum};
	if(flag){
		location.href="/contract/czContract/delCzContract?conId="+id+"&pageNum="+pageNum;
	}
}

//显示合同相关说明
function viewAccount(obj){

	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/conAccount/viewAccount?conId="+id);
}

//下载合同
function down(obj){
	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/downloadAttach/download?conId="+id);
	
}

//预览附件
function preView(obj){
	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/downloadAttach/preAttach?conId="+id);
}

//下载数据
function doSubmit(url){
	$("#form1").attr("action",url);
	$("#form1").submit();
}
</script>
  </body>
</html>