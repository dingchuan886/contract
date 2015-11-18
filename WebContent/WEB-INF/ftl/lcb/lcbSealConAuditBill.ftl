<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
	<title>部门经理合同流程审核</title>
	<@c.html/>
</head>

<body>
	
	<@c.head/>
	<@c.left/>
	<div class="span10">
		<ul class="breadcrumb">
        	<li><a href="#">合同管理</a> <span class="divider">/</span></li>
            <li class="active">部门审核</li>
        </ul>
        <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
        	<ul class="nav nav-tabs">
	            <li class="<#if conType=='SHCZ'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/flowSealConAuditBill?conType=SHCZ'" data-toggle="tab">众智车展合同</a></li>
	            <li class="<#if conType=='SHZH'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/flowSealConAuditBill?conType=SHZH'" data-toggle="tab">众智315广告合同</a></li>
	            <li class="<#if conType=='SHCT'>active</#if>"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConFlowAudit/flowSealConAuditBill?conType=SHCT'" data-toggle="tab">车团合同</a></li>
          	</ul>
          	<div style="color:red">${result}</div>
        	<table class="table table-bordered">
              	<#if conType=='SHCT'>
        			<thead>
		                <tr>
		                  <th>站点</th>
		                  <th>业务员</th>
		                  <th>合同编号</th>
		                  <th>客户名称</th>
		                  <th>品牌</th>          
		                  <th>签订日期</th>
		                  <th>活动日期</th>
		                  <th>操作</th>
		                </tr>
	              	</thead>
        		</#if>
        		<#if conType=='SHCZ'>
        			<thead>
		                <tr>
		                  <th>站点</th>
		                  <th>业务员</th>
		                  <th>合同编号</th>
		                  <th>客户名称</th>
		                  <th>品牌</th>          
		                  <th>签订日期</th>
		                  <th>活动日期</th>
		                  <th>操作</th>
		                </tr>
	              	</thead>
        		</#if>
        		<#if conType=='SHZH'>
	              	<thead>
		                <tr>
		                  <th>站点</th>
		                  <th>业务员</th>
		                  <th>合同编号</th>
		                  <th>客户名称</th>
		                  <th>签订日期</th>
		                  <th>操作</th>
		                </tr>
	              	</thead>
	            </#if>
              	<#if (pager.list?size>0)>
	      			<tbody>
    	          		<#if conType=='SHCT'>
	              			<#list pager.list as conFlowCon>
		              			<#--<#if (conFlowCon.flowCon.con_state==0)&&(session.userInfo.)>-->
		              			<tr>
		              				<td>${conFlowCon.org.org_name}</td>
		              				<td>${conFlowCon.conCt.user_name}</td>
		              				<td>${conFlowCon.conCt.con_ct_id}</td>
		              				<td>${conFlowCon.conCt.cus_name}</td>
		              				<td>${conFlowCon.conCt.cus_brand}</td>
		              				<td>${conFlowCon.conCt.create}</td>
		              				<td>${conFlowCon.conCt.act_date}</td>
		              				<td>
		              					<a href="#" data-toggle="modal" onclick="showAuditModal(this, '${conType}', ${conFlowCon.conFlow.con_flow_id}, '${conFlowCon.conCt.con_ct_id}', 'auditForm');">审核</a>
		              					<a href="">查看</a>
		              				</td>
		              			</tr>
		              			<#--</#if>-->
	              			</#list>
        	      		</#if>
              			<#if conType=='SHCZ'>
	              			<#list pager.list as conFlowCon>
	              			<tr>
	              				<td>${conFlowCon.org.org_name}</td>
	              				<td>${conFlowCon.conCz.user_name}</td>
	              				<td>${conFlowCon.conCz.con_cz_id}</td>
	              				<td>${conFlowCon.conCz.cus_name}</td>
	              				<td>${conFlowCon.conCz.cus_brand}</td>
	              				<td>${conFlowCon.conCz.create}</td>
	              				<td>${conFlowCon.conCz.act_date}</td>
	              				<td>
	              					<a href="#" data-toggle="modal" onclick="showAuditModal(this, '${conType}', ${conFlowCon.conFlow.con_flow_id}, '${conFlowCon.conCz.con_cz_id}', 'auditForm');">审核</a>
	              					<a href="">查看</a>
	              				</td>
	              			</tr>
	              			</#list>
              			</#if>
              			<#if conType=='SHZH'>
	              			<#list pager.list as conFlowCon>
	              			<tr>
	              				<td>${conFlowCon.org.org_name}</td>
	              				<td>${conFlowCon.conZh.user_name}</td>
	              				<td>${conFlowCon.conZh.con_zh_id}</td>
	              				<td>${conFlowCon.conZh.cus_name}</td>
	              				<td>${conFlowCon.conZh.cus_brand}</td>
	              				<td>${conFlowCon.conZh.create}</td>
	              				<td>${conFlowCon.conZh.act_date}</td>
	              				<td>
	              					<a href="#" data-toggle="modal" onclick="showAuditModal(this, '${conType}', ${conFlowCon.conFlow.con_flow_id}, '${conFlowCon.conZh.con_zh_id}', 'auditForm');">审核</a>
	              					<a href="">查看</a>
	              				</td>
	              			</tr>
	              			</#list>
              			</#if>
              		</tbody>
              	</#if>
        	</table>
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
	<div id="rejectModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      	<div class="modal-footer" style="text-align:center; margin-top:10px">
      		<form id="rejectForm" action="${contract_ct_url}contract/tbConFlowAudit/rejectedFlowSeal" method="post">
	      		<input type="hidden" name="conType" vlaue=""/>
	      		<input type="hidden" name="conFlowId" vlaue=""/>
	      		<label for="type" class="control-label">审核驳回</label>
	      		合同编号: <input type="type" name="conId" disabled vlaue=""/><br/><br/>
	      		驳回原因: <textarea name="conMsg" cols="30" rows="4"></textarea><br/>
      		</form>
        	<button class="btn btn-info" data-dismiss="modal" onclick="submitForm('rejectForm');">确定驳回</button>
        	<button class="btn" data-dismiss="modal">暂不处理</button>
      	</div>
    </div>
    
    <div id="auditModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	<div class="modal-footer" style="text-align:center; margin-top:10px">
    		<form id="auditForm" action="${contract_ct_url}contract/tbConFlowAudit/flowSealCheck" method="POST" enctype="multipart/form-data">
    			<input type="hidden" name="conType" vlaue=""/>
      			<input type="hidden" name="conFlowId" vlaue=""/>
      			<label for="type" class="control-label">合同文件上传</label>
      			合同编号: <input type="type" name="conId" disabled vlaue=""/><br/><br/>
      			选取文件: <input type="file" name="conFile" id="fileupload"/><br/><br/> 
      			<button class="btn btn-info" data-dismiss="modal" onclick="submitForm('auditForm');">确定审核</button>
       	 		<button class="btn" data-dismiss="modal">暂不处理</button> 
    		</form>
    	</div>
    </div>
    
	<@c.js/>
	
<script type="text/javascript">

      	function showAuditModal(a, conType, conFlowId, conId, formId){
      		buildFormMsg(conType, conFlowId, conId, formId);
      		$(a).attr({href :"#auditModal"});
      	}
      	function buildAuditModal(conType, conFlowId, conId, formId){
      		$("#"+formId+" input[name='conType']").val(conType);
      		$("#"+formId+" input[name='conFlowId']").val(conFlowId);
      		$("#"+formId+" input[name='conId']").val(conId);
      	}
      	function showRejectModal(a, conType, conFlowId, conId, formId){
      		buildFormMsg(conType, conFlowId, conId, formId);
      		$(a).attr({href :"#rejectModal"});
      	}
      	function buildFormMsg(conType, conFlowId, conId, formId){
      		$("#"+formId+" input[name='conType']").val(conType);
      		$("#"+formId+" input[name='conFlowId']").val(conFlowId);
      		$("#"+formId+" input[name='conId']").val(conId);
      	}
      	function submitForm(formId){
      		
      		$("#"+formId).submit();
      	}
      	function changePage(pageNum){
			if(pageNum<1){
				pageNum = 1;
			}
			if(pageNum>${pager.totalPages}){
				pageNum = ${pager.totalPages};
			}
			location.href = "${contract_ct_url}contract/tbConFlowAudit/flowConAuditBill?pageNum=" + pageNum + "&conType=${conType}";
		}
		
		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}
    </script>
</body>

</html>