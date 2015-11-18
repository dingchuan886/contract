<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
	<title>查看合同驳回</title>
	<@c.html/>
</head>

<body>
	
	<@c.head/>
	<@c.left act=71/>
	<div class="span10">
		<ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">查看驳回返利</li>
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
	                  <th>站点</th>
	                  <th>业务员</th>
	                  <th>合同编号</th>
	                  <th>本次返利金额</th>
	                  <th>驳回原因</th>
	                  <th>操作</th>
	                </tr>
              	</thead>
              	<#if (pager.list?size>0)>
	      			<tbody>
	      			<#list pager.list as map>
	      				<tr>
              				<td>${map.org.org_name}</td>
              				<td>${map.user.user_name}</td>
              				<td>${map.rebate.con_id}</td>
              				<td>${map.rebate.this_back}</td>
              				<td>${map.rebateFlow.rebate_msg}</td>
              				<td>
              					<a href="${contract_ct_url}contract/tbConRebate/showRebate?rebateId=${map.rebate.back_id}">查看</a>
              					<a href="javascript:void(0)" onclick="delRebate('${map.rebate.back_id}')">删除</a>
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
			<a href="javascript:void(0)" onclick="changePage(${pager.pageNum-1})">上一页</a>&nbsp
			第${pager.pageNum}/${pager.totalPages}页		
			<a href="javascript:void(0)" onclick="changePage(${pager.pageNum+1})">下一页</a>&nbsp&nbsp		
			<a href="javascript:void(0)" onclick="changePage(${pager.totalPages})">尾页</a>&nbsp&nbsp		
			<input type="text" id="pageNum" value="${pager.pageNum}" style="width:25px;"/><button type="button" onclick="changePage1()">跳页</button>&nbsp	&nbsp&nbsp		
		</div>
	</div>
	<@c.js/>
	<script type="text/javascript">
		function changePage(pageNum){
			if(pageNum<1){
				pageNum = 1;
			}
			if(pageNum>${pager.totalPages}){
				pageNum = ${pager.totalPages};
			}
			location.href = "${contract_ct_url}contract/tbConFlowAudit/lookRejectCon?pageNum=" + pageNum + "&conType=${conType}";
		}
		
		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}
		
		function delRebate(rebateId){
			var delFlag = confirm("确定删除吗？");
			if(delFlag){
				location.href="/contract/tbConRebate/deleteRebate?rebateId="+rebateId;
			}
			return;
		}
		
    </script>
</body>

</html>