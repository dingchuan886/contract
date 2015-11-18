<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
	<title>部门经理合同流程审核</title>
	<@c.html/>
</head>

<body>

	<@c.head/>
	<@c.left act=21/>
	<div class="span10">
		<ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">部门审核列表</li>
	    </ul>
	    <div class="page-header">
	    	<h2>部门审核列表</h2>
	    </div>
        <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
			<ul class="nav nav-tabs">
	            <li class="<#if conType=='SHCZ'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/deptManagerAcc/listNeedAuditAcc?conType=SHCZ'" data-toggle="tab">众智车展合同</a></li>
	            <li class="<#if conType=='SHZH'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/deptManagerAcc/listNeedAuditAcc?conType=SHZH'" data-toggle="tab">众智315广告合同</a></li>
	            <li class="<#if conType=='SHCT'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/deptManagerAcc/listNeedAuditAcc?conType=SHCT'" data-toggle="tab">车团合同</a></li>
          	</ul>
			<div style="color:red">${result}</div>
        	<table class="table table-bordered">
        		<#if conType=='SHCT'>
        			<thead>
		                <tr>
		                  <th>业务员</th>
		                  <th>合同编号</th>
		                  <th>是否返利</th>
		                  <th>返利比例</th>
		                  <th>执行进度</th>
		                  <th>进度描述</th>
		                  <th>受益人</th>
		                  <th>电话</th>
		                  <th>操作</th>
		                </tr>
	              	</thead>
        		</#if>
        		<#if conType=='SHCZ'>
        			<thead>
		                <tr>
		                  <th>业务员</th>
		                  <th>合同编号</th>
		                  <th>是否返利</th>
		                  <th>返利比例</th>
		                  <th>执行进度</th>
		                  <th>进度描述</th>
		                  <th>受益人</th>
		                  <th>电话</th>
		                  <th>操作</th>
		                </tr>
	              	</thead>
        		</#if>
        		<#if conType=='SHZH'>
	              	<thead>
		                <tr>
		                  <th>业务员</th>
		                  <th>合同编号</th>
		                  <th>是否返利</th>
		                  <th>返利比例</th>
		                  <th>执行进度</th>
		                  <th>进度描述</th>
		                  <th>受益人</th>
		                  <th>电话</th>
		                  <th>操作</th>
		                </tr>
	              	</thead>
	            </#if>
              	<#if (pageResult.list?size>0)>
	      			<tbody>
    	          		<#if conType=='SHCT'>
	              			<#list pageResult.list as conFlowCon>
		              			<#--<#if (conFlowCon.flowCon.con_state==0)&&(session.userInfo.)>-->
		              			<tr>
		              				<td>${conFlowCon.user_name}</td>
		              				<td>${conFlowCon.con_id}</td>
		              				<td><#if conFlowCon.isback==0>否</#if>
		              					<#if conFlowCon.isback==1>是</#if>
		              				</td>
		              				<td><#if conFlowCon.isback==1>
										${conFlowCon.isback_des}
										</#if></td>
		              				<td><#if conFlowCon.plan==0>按期执行</#if>
		              				<#if conFlowCon.plan==1>适时调整</#if>
		              				</td>
		              				<td><#if conFlowCon.plan==1>预计${conFlowCon.plan_des}投放完毕</#if></td>
		              				<td>${conFlowCon.ben_person}</td>
		              				<td>${conFlowCon.phone}</td>
		              				<td>
		              				<a href="${contract_ct_url}contract/myContract/viewCtContract?conId=${conFlowCon.con_id}" target="_blank">查看合同</a>
										<a href="javascript:managerAudit('${conType}', ${conFlowCon.con_s_id})">通过</a>
		              					<a href="#" data-toggle="modal" onclick="showModal(this, '${conType}', ${conFlowCon.con_s_id}, '${conFlowCon.con_id}', 'rejectForm');">驳回</a>
		              				</td>
		              			</tr>
		              			<#--</#if>-->
	              			</#list>
        	      		</#if>
              			<#if conType=='SHCZ'>
	              			<#list pageResult.list as conFlowCon>
	              			<tr>
		              				<td>${conFlowCon.user_name}</td>
		              				<td>${conFlowCon.con_id}</td>
		              				<td><#if conFlowCon.isback==0>否</#if>
		              					<#if conFlowCon.isback==1>是</#if>
		              				</td>
		              				<td><#if conFlowCon.isback==1>
										${conFlowCon.isback_des}
										</#if></td>
		              				<td><#if conFlowCon.plan==0>按期执行</#if>
		              				<#if conFlowCon.plan==1>适时调整</#if>
		              				</td>
		              				<td><#if conFlowCon.plan==1>预计${conFlowCon.plan_des}投放完毕</#if></td>
		              				<td>${conFlowCon.ben_person}</td>
		              				<td>${conFlowCon.phone}</td>
		              				<td>
		              				<a href="${contract_ct_url}contract/myContract/viewCzContract?conId=${conFlowCon.con_id}" target="_blank">查看合同</a>
										<a href="javascript:managerAudit('${conType}', ${conFlowCon.con_s_id})">通过</a>
		              					<a href="#" data-toggle="modal" onclick="showModal(this, '${conType}', ${conFlowCon.con_s_id}, '${conFlowCon.con_id}', 'rejectForm');">驳回</a>
		              				</td>
		              			</tr>
	              			</#list>
              			</#if>
              			<#if conType=='SHZH'>
	              			<#list pageResult.list as conFlowCon>
	              			<tr>
		              				<td>${conFlowCon.user_name}</td>
		              				<td>${conFlowCon.con_id}</td>
		              				<td><#if conFlowCon.isback==0>否</#if>
		              					<#if conFlowCon.isback==1>是</#if>
		              				</td>
		              				<td><#if conFlowCon.isback==1>
										${conFlowCon.isback_des}
										</#if></td>
		              				<td><#if conFlowCon.plan==0>按期执行</#if>
		              				<#if conFlowCon.plan==1>适时调整</#if>
		              				</td>
		              				<td><#if conFlowCon.plan==1>预计${conFlowCon.plan_des}投放完毕</#if></td>
		              				<td>${conFlowCon.ben_person}</td>
		              				<td>${conFlowCon.phone}</td>
		              				<td>
		              				<a href="${contract_ct_url}contract/myContract/viewZhContract?conId=${conFlowCon.con_id}" target="_blank">查看合同</a>
										<a href="javascript:managerAudit('${conType}', ${conFlowCon.con_s_id})">通过</a>
		              					<a href="#" data-toggle="modal" onclick="showModal(this, '${conType}', ${conFlowCon.con_s_id}, '${conFlowCon.con_id}', 'rejectForm');">驳回</a>
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
			<a href="javascript:void(0)" onclick="changePage(${pageResult.pageNum-1})">上一页</a>&nbsp
			第${pageResult.pageNum}/${pageResult.totalPages}页
			<a href="javascript:void(0)" onclick="changePage(${pageResult.pageNum+1})">下一页</a>&nbsp&nbsp
			<a href="javascript:void(0)" onclick="changePage(${pageResult.totalPages})">尾页</a>&nbsp&nbsp
			&nbsp&nbsp&nbsp
		</div>
	</div>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-footer" style="text-align:center; margin-top:10px">
      	<form id="rejectForm" action="${contract_ct_url}contract/deptManagerAcc/rejectAccount" method="post">
      		<input type="hidden" name="conType" vlaue=""/>
      		<input type="hidden" name="accId" vlaue=""/>
      		<label for="type" class="control-label">审核驳回</label>
      		合同编号: <input type="type" name="conId" disabled vlaue=""/><br/><br/>
      		驳回原因: <textarea name="conMsg" cols="30" rows="4"></textarea><br/>
      	</form>
        <button class="btn btn-info" data-dismiss="modal" onclick="rejected('rejectForm');">确定驳回</button>
        <button class="btn" data-dismiss="modal">暂不处理</button>
      </div>
    </div>
	<@c.js/>
	<script type="text/javascript">
    	function managerAudit(conType, accId){
      		if(confirm("确定通过该相关说明么?")){
      			window.location.href = "${contract_ct_url}contract/deptManagerAcc/auditAccount?conType="+conType+"&accId="+accId;
      		}
      	}
      	function showModal(a, conType, conFlowId, conId, formId){
      		buildRejectMsg(conType, conFlowId, conId, formId);
      		$(a).attr({href :"#myModal"});
      	}
      	function buildRejectMsg(conType, conFlowId, conId, formId){
      		$("#"+formId+" input[name='conType']").val(conType);
      		$("#"+formId+" input[name='accId']").val(conFlowId);
      		$("#"+formId+" input[name='conId']").val(conId);
      	}
      	function rejected(formId){
      		$("#"+formId).submit();
      	}
		function changePage(pageNum){
			if(pageNum<1){
				pageNum = 1;
			}
			if(pageNum>${pageResult.totalPages}){
				pageNum = ${pageResult.totalPages};
			}
			location.href = "${contract_ct_url}contract/deptManagerAcc/listNeedAuditAcc?pageNum=" + pageNum + "&conType=${conType}";
		}

		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}

//显示合同相关说明
function viewAccount(obj){
	var id = $(obj).parent().siblings("td").eq(1).html();
	window.open("/contract/conAccount/viewAccount?conId="+id);
}
    </script>
</body>

</html>