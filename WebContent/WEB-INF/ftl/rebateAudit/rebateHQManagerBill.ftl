<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
	<title>总经理返利流程审核</title>
	<@c.html/>
</head>

<body>
	<@c.head/>
	<@c.left act=79/>
	<div class="span10">
		<ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">总经理返利审核</li>
	    </ul>
	    <div class="page-header">
	    	<h2>总经理返利审核</h2>
	    </div>
        <!--
        <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
        	<ul class="nav nav-tabs">
	            <li class="<#if conType=='SHCZ'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/flowSealConAuditBill?conType=SHCZ'" data-toggle="tab">众智车展合同</a></li>
	            <li class="<#if conType=='SHZH'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/flowSealConAuditBill?conType=SHZH'" data-toggle="tab">众智315广告合同</a></li>
	            <li class="<#if conType=='SHCT'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/flowSealConAuditBill?conType=SHCT'" data-toggle="tab">车团合同</a></li>
          	</ul>
		</div>
		-->
		
      	<div style="color:red">${result}</div>
		<table class="table table-bordered">
			<thead>
                <tr>
                  <th>站点</th>
                  <th>业务员</th>
                  <th>合同编号</th>
                  <th>客户名称</th>
                  <th>签订日期</th>          
                  <th>合同金额(元)</th>
                  <th>返利金额(元)</th>
                  <th>执行金额(元)</th>
                  <th>申请返利金额</th>
                  <th>操作</th>
                </tr>
          	</thead>
          	<#if (pager.list?size>0)>
          	<tbody>
          		<#list pager.list as map>
          			<tr>
          				<td>${map.org.org_name}</td>
          				<td>${map.user.user_name}</td>
          				<td>${map.conRebate.con_id}</td>
          				<td>${map.conRebate.cus_name}</td>
          				<#if (map.conRebate.con_id)?starts_with('SHZH')>
	          				<td>${map.conZh.create?string("yyyy-MM-dd")}</td>
	          				<td>${map.conZh.con_total_price}</td>
          				</#if>
          				<#if (map.conRebate.con_id)?starts_with('SHCT')>
	          				<td>${map.conCt.create?string("yyyy-MM-dd")}</td>
	          				<td>${map.conCt.con_total_price}</td>
          				</#if>
          				<#if (map.conRebate.con_id)?starts_with('SHCZ')>
	          				<td>${map.conCz.create?string("yyyy-MM-dd")}</td>
	          				<td>${map.conCz.con_total_price}</td>
          				</#if>
          				<td>${map.conRebate.al_back}</td>
          				<td>${map.conRebate.back_actual}</td>
          				<td>${map.conRebate.this_back}</td>
          				<td>
          					
          					<a href="#" data-toggle="modal" onclick="showCheckModal(this, ${map.conRebateFlow.rebate_flow_id}, 'checkForm');">审核通过</a>
          					<a href="#" data-toggle="modal" onclick="showRejectModal(this, ${map.conRebateFlow.rebate_flow_id}, ${map.conRebate.back_id}, 'rejectForm');">驳回</a>
          					<a href="${contract_ct_url}contract/tbConRebate/showRebate?rebateId=${map.conRebate.back_id}" target="_blank">查看详情</a>
          							<#if (map.conRebate.con_id)?starts_with('SHCT')>
                   <a href="${contract_ct_url}contract/ctContract/viewCtContract?conId=${map.conRebate.con_id}" target="_blank">查看合同</a>
                   <#elseif (map.conRebate.con_id)?starts_with('SHCZ')>
                     <a href="${contract_ct_url}contract/czContract/viewCzContract?conId=${map.conRebate.con_id}" target="_blank">查看合同</a>
                   <#elseif (map.conRebate.con_id)?starts_with('SHZH') >
                     <a href="${contract_ct_url}contract/zhContract/viewZhContract?conId=${map.conRebate.con_id}" target="_blank">查看合同</a>
                   </#if>
          				</td>
          			</tr>
          		</#list>
          	</tbody>
          	</#if>
       </table>
       	<div align="right">
			<a href="javascript:void(0)" onclick="changePage(1)">首页</a>&nbsp&nbsp&nbsp
			<a href="javascript:void(0)" onclick="changePage(${pager.pageNum-1})">上一页</a>&nbsp
			第${pager.pageNum}/${pager.totalPages}页		
			<a href="javascript:void(0)" onclick="changePage(${pager.pageNum+1})">下一页</a>&nbsp&nbsp		
			<a href="javascript:void(0)" onclick="changePage(${pager.totalPages})">尾页</a>&nbsp&nbsp		
			<input type="text" id="pageNum" value="${pager.pageNum}" style="width:25px;"/><button type="button" onclick="changePage1()">跳页</button>&nbsp	&nbsp&nbsp		
		</div>
	</div>
	<div id="checkModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-footer" style="text-align:center; margin-top:10px">
        <form id="checkForm" action="${contract_ct_url}contract/tbConRebate/hqManagerCheck" method="post">
      		<input type="hidden" name="rebateFlowId" vlaue=""/>
      		<label for="type" class="control-label">确认通过？</label>
      	</form>
        <button class="btn btn-info" data-dismiss="modal" onclick="checked('checkForm');">确定审核</button>
        <button class="btn" data-dismiss="modal">暂不处理</button>
      </div>
    </div>
    <div id="rejectModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-footer" style="text-align:center; margin-top:10px">
      	<form id="rejectForm" action="${contract_ct_url}contract/tbConRebate/managerReject" method="post">
      		<input type="hidden" name="rebateFlowId" vlaue=""/>
      		<label for="type" class="control-label">审核驳回?</label>
      		返利编号: <input type="type" name="rebateId" disabled vlaue=""/><br/><br/>
      		驳回原因: <textarea name="rebateMsg" cols="30" rows="4"></textarea><br/>
      	</form>
        <button class="btn btn-info" data-dismiss="modal" onclick="checked('rejectForm');">确定驳回</button>
        <button class="btn" data-dismiss="modal">暂不处理</button>
      </div>
    </div>
	<@c.js/>
	<script type="text/javascript">
		
		function showRejectModal(a, rebateFlowId, rebateId, formId){
      		buildFormMsg(rebateFlowId, rebateId, formId);
      		$(a).attr({href :"#rejectModal"});
      	}
      	function buildFormMsg(rebateFlowId,rebateId, formId){
      		$("#"+formId+" input[name='rebateFlowId']").val(rebateFlowId);
      		$("#"+formId+" input[name='rebateId']").val(rebateId);
      	}
	
		
		function showCheckModal(a, rebateFlowId, formId){
      		buildRejectMsg(rebateFlowId, formId);
      		$(a).attr({href :"#checkModal"});
      	}
		function buildRejectMsg(rebateFlowId, formId){
      		$("#"+formId+" input[name='rebateFlowId']").val(rebateFlowId);
      	}
		function checked(formId){
	      		$("#"+formId).submit();
      	}
	
		function changePage(pageNum){
			if(pageNum<1){
				pageNum = 1;
			}
			if(pageNum>${pager.totalPages}){
				pageNum = ${pager.totalPages};
			}
			location.href = "${contract_ct_url}contract/tbConRebate/rebateHQManagerBill?pageNum=" + pageNum;
		}
		
		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}
	</script>
</body>
</html>