<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
	<title>查看相关说明驳回</title>
	<@c.html/>
</head>

<body>
	
	<@c.head/>
	<@c.left act=22/>
	<div class="span10">
		<ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">查看驳回相关说明</li>
	    </ul>
	    <div class="page-header">
	    	<h2>查看驳回返利</h2>
	    </div>
        <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
			<#--
			<ul class="nav nav-tabs">
	            <li class="<#if conType=='SHCZ'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/lookRejectCon?conType=SHCZ'" data-toggle="tab">众智车展合同</a></li>
	            <li class="<#if conType=='SHZH'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/lookRejectCon?conType=SHZH'" data-toggle="tab">众智315广告合同</a></li>
	            <li class="<#if conType=='SHCT'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/lookRejectCon?conType=SHCT'" data-toggle="tab">车团合同</a></li>
          	</ul>
          	-->
        	<table class="table table-bordered">
    			<thead>
	                <tr>
	                  <th>业务员</th>
	                  <th>合同编号</th>
	                  <th>是否返利</th>
		              <th>返利比例</th>
		              <th>执行进度</th>
		              <th>进度描述</th>
	                  <th>驳回原因</th>
	                  <th>操作</th>
	                </tr>
              	</thead>
              	<#if (pageResult.list?size>0)>
	      			<tbody>
	      				<#list pageResult.list as map>
	      				<tr>
              				<td>${map.acc.user_name}</td>
              				<td>${map.acc.con_id}</td>
              				<td>
								<#if map.acc.isback==0>不返利</#if>
								<#if map.acc.isback==1>返利</#if>
							</td>
              				<td>${map.acc.isback_des}</td>
              				<td>
              					<#if map.acc.plan==0>按期执行</#if>
              					<#if map.acc.plan==1>适时调整</#if>
              				</td>
              				<td>${map.acc.plan_des}</td>
              				<td>${map.rejectMsg}</td>
              				<td>
              					<a href="${contract_ct_url}contract/conAccount/viewAccount?conId=${map.acc.con_id}">查看</a>&nbsp&nbsp
              					<a href="javascript:void(0)" onclick="reApply('${map.acc.con_id}')">修改并重新申请</a>
              					<a href="javascript:void(0)" onclick="delAccount('${map.acc.con_s_id}')">删除</a>
              				</td>
              			</tr>
              			</#list>
              		</tbody>
              	</#if>
        	</table>
		</div>
		</div>
		<div align="right">
			<a href="javascript:void(0)" onclick="changePage(1)">首页</a>&nbsp&nbsp&nbsp
			<a href="javascript:void(0)" onclick="changePage(${pageResult.pageNum-1})">上一页</a>&nbsp
			第${pageResult.pageNum}/${pageResult.totalPages}页		
			<a href="javascript:void(0)" onclick="changePage(${pageResult.pageNum+1})">下一页</a>&nbsp&nbsp		
			<a href="javascript:void(0)" onclick="changePage(${pageResult.totalPages})">尾页</a>&nbsp&nbsp		
			<input type="text" id="pageNum" value="${pageResult.pageNum}" style="width:25px;"/><button type="button" onclick="changePage1()">跳页</button>&nbsp	&nbsp&nbsp		
		</div>
	</div>
	<@c.js/>
	<script type="text/javascript">
		function changePage(pageNum){
			if(pageNum<1){
				pageNum = 1;
			}
			if(pageNum>${pageResult.totalPages}){
				pageNum = ${pageResult.totalPages};
			}
			location.href = "${contract_ct_url}contract/conAccount/lookMyRejectAccount?pageNum="+pageNum;
		}
		
		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}
		
		function reApply(str){
			var substr = str.substring(0,4);
			if(substr=="SHCZ"){
				window.open("${contract_ct_url}contract/czContract/toupdateCzContract?conId="+str);
			}else if(substr=="SHCT"){
				window.open("${contract_ct_url}contract/ctContract/toupdateCtContract?conId="+str);
			}else if(substr=="SHZH"){
				window.open("${contract_ct_url}contract/zhContract/toupdateZhContract?conId="+str);
		}
}
		function delAccount(accId){
			var delFlag = confirm("确定删除吗？")
			if(delFlag){ 
				location.href="/contract/conAccount/deleteAccount?accId="+accId;
			}
			return;
		}
    </script>
</body>

</html>