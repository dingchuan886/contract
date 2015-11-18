<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
	<title>查看合同驳回</title>
	<@c.html/>
</head>

<body>
	
	<@c.head/>
	<@c.left act=70/>
	<div class="span10">
		<ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">驳回合同列表</li>
	    </ul>
	    <div class="page-header">
	    	<h2>驳回合同列表</h2>
	    </div>
        <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
			<ul class="nav nav-tabs">
	            <li class="<#if conType=='SHCZ'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/lookRejectCon?conType=SHCZ'" data-toggle="tab">众智车展合同</a></li>
	            <li class="<#if conType=='SHZH'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/lookRejectCon?conType=SHZH'" data-toggle="tab">众智315广告合同</a></li>
	            <li class="<#if conType=='SHCT'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/lookRejectCon?conType=SHCT'" data-toggle="tab">车团合同</a></li>
          	</ul>
        	<table class="table table-bordered">
    			<thead>
	                <tr>
	                  <th>业务员</th>
	                  <th>合同编号</th>
	                  <th>驳回原因</th>
	                  <th>操作</th>
	                </tr>
              	</thead>
              	<#if (pager.list?size>0)>
	      			<tbody>
    	          		<#if conType=='SHCT'>
	              			<#list pager.list as map>
		              			<#--<#if (conFlowCon.flowCon.con_state==0)&&(session.userInfo.)>-->
		              			<tr>
		              				<td>${map.user.user_name}</td>
		              				<td>${map.conCt.con_ct_id}</td>
		              				<td>${map.conFlow.con_msg}</td>
		              				<td>
		              					<a href="${contract_ct_url}contract/myContract/viewCtContract?conId=${map.conCt.con_ct_id}" target="_blank">查看</a>
		              				</td>
		              			</tr>
		              			<#--</#if>-->
	              			</#list>
              			<#elseif conType=='SHCZ'>
	              			<#list pager.list as map>
	              				<tr>
		              				<td>${map.user.user_name}</td>
		              				<td>${map.conCz.con_cz_id}</td>
		              				<td>${map.conFlow.con_msg}</td>
		              				
		              				<td>
		              					<a href="${contract_ct_url}contract/myContract/viewCzContract?conId=${map.conCz.con_cz_id}" target="_blank">查看</a>
		              				</td>
		              			</tr>
	              			</#list>
              			<#elseif conType=='SHZH'>
	              			<#list pager.list as map>
	              				<tr>
		              				<td>${map.user.user_name}</td>
		              				<td>${map.conZh.con_zh_id}</td>
		              				<td>${map.conFlow.con_msg}</td>
		              				<td>
		              					<a href="${contract_ct_url}contract/myContract/viewZhContract?conId=${map.conZh.con_zh_id}" target="_blank">查看</a>
		              				</td>
		              			</tr>
	              			</#list>
              			</#if>
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
    </script>
</body>

</html>