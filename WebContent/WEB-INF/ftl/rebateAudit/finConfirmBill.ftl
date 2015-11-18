<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
	<title>确认返利</title>
	<@c.html/>
</head>

<body>
	<@c.head/>
	<@c.left act=77/>
	<div class="span10">
		<ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">返利确认列表</li>
	    </ul>
	    <div class="page-header">
	    	<h3>返利确认列表</h3>
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
		<form action="/contract/tbConRebate/finConfirmBill" method="post" class="form-horizontal" id="form1">
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">合同类型</label>
                <div class="controls">
                <#-- 这里的conType和合同的有些不一样，指的是合同编号的前面四个字母 -->
                  <select name="conType" id="">
                    <option value=""></option>
                    <option value="SHCT" <#if pageCondition.conType=='SHCT'>selected</#if>>车团合同</option>
                    <option value="SHCZ" <#if pageCondition.conType=='SHCZ'>selected</#if>>车展合同</option>
                    <option value="SHZH" <#if pageCondition.conType=='SHZH'>selected</#if>>广告合同</option>
                  </select>
                </div>
              </div>
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">业务员</label>
                <div class="controls">
                  <input type="text" name="userName" value="${pageCondition.userName}"/>
                  <input type="hidden" name="pageNum" value="1" id="pageNum1">
                  </select>
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
               <div><button onclick="doSubmit('/contract/tbConRebate/finConfirmBill')">查询</button>
               </div>
            </div>
          </form>
      	<div style="color:red">${result}</div>
		<table class="table table-bordered">
			<thead>
                <tr>
                  <th>查看返利申请单</th>
                  <th>站点</th>
                  <th>业务员</th>
                  <th>客户名称</th>
                  <th>合同编号</th>
                  <th>合同总价</th>
                  <th>本次返利金额(元)</th>
                  <th>扣除开票税点</th>
                  <th>返还</th>          
                  <th>本次实际返利(元)</th>
                  <th>确认返利</th>
                  <th>驳回</th>
                </tr>
          	</thead>
          	<#if (pager.list?size>0)>
          	<tbody>
          		<#list pager.list as map>
          			<tr>
          				<td><a href="${contract_ct_url}contract/tbConRebate/showRebate?rebateId=${map.conRebate.back_id}" target="_blank">查看</a></td>
          				<td>${map.org.org_name}</td>
          				<td>${map.user.user_name}</td>
          				<td>${map.conRebate.cus_name}</td>
          				<td>${map.conRebate.con_id}</td>
          				<#if (map.conRebate.con_id)?starts_with('SHZH')>
	          				<td>${map.conZh.con_total_price}</td>
          				</#if>
          				<#if (map.conRebate.con_id)?starts_with('SHCT')>
	          				<td>${map.conCt.con_total_price}</td>
          				</#if>
          				<#if (map.conRebate.con_id)?starts_with('SHCZ')>
	          				<td>${map.conCz.con_total_price}</td>
          				</#if>
          				<td>${map.conRebate.this_back}</td>
          				<td>${map.conRebate.deduct}</td>
          				<td>${map.conRebate.back_actual}</td>
          				<td>${map.conRebate.this_back}</td>
          				<td>
          					<#if ((map.conRebate.rebate_time)=="")>
	          					<a  data-toggle="modal" href="#" onclick="showCheckModal(this, ${map.conRebate.back_id}, '${map.conRebate.con_id}', 'firmModal')" class="btn">返利确认</a>
          					<#else>
          						已返利：${map.conRebate.back_actual}
          					</#if>
          				</td>
          				<td>
							<a href="#" data-toggle="modal" onclick="showRejectModal(this, ${map.conRebateFlow.rebate_flow_id}, ${map.conRebate.back_id}, 'rejectForm');">驳回</a>
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
	<div id="firmModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="bank-radio" style="display:block;">
        <div class="control-group" style="padding:15px">
        <form id="checkForm" action="${contract_ct_url}contract/tbConRebate/finConfirm" method="POST">
          <input type="hidden" name="rebateId" placeholder="" value="">
          <input type="hidden" name="conId" placeholder="" value="">
          <input type="hidden" name="pageNum" placeholder="" value="${pager.pageNum}">
          <label>返利金额</label>
          <input type="text" name="rebateAccount" id="rebateAcc" placeholder="1111">
          <span>只能是数字</span>
          <label>返利时间</label>
          <div class="input-append date form_datetime">
	          <input type="text" value="" readonly="true" name="rebateTime" id="rebateTi" data-date="2013-02-21" style="width:180px;">
	          <span class="add-on"><i class="icon-remove"></i></span>
	          <span class="add-on"><i class="icon-th"></i></span>
	      </div>
        </form>
        </div>
        <div class="modal-footer" style="text-align:center; margin-top:10px">
          <button class="btn btn-danger" data-dismiss="modal" onclick="checked('checkForm');">确认</button>
          <button class="btn btn-danger" data-dismiss="modal">取消</button>
        </div>
      </div>
    </div>
    <div id="rejectModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-footer" style="text-align:center; margin-top:10px">
      	<form id="rejectForm" action="${contract_ct_url}contract/tbConRebate/finReject" method="post">
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
		$(".form_datetime").datetimepicker({
		startView: 2,
		weekStart:1,
        minView: 2, //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true //选择日期后自动关闭
      });
     function changePage(pageNum){
		if(pageNum<1){
			pageNum = 1;
		}
		if(pageNum>${pager.totalPages}){
			pageNum = ${pager.totalPages};
		}
		$("#pageNum1").val(pageNum);
		$("#form1").submit();
	}
	
	function changePage1(){
		var pageNum = $("#pageNum").val();
		changePage(pageNum);
	}
		function showRejectModal(a, rebateFlowId, rebateId, formId){
      		buildRejectMsg(rebateFlowId, rebateId, formId);
      		$(a).attr({href :"#rejectModal"});
      	}
		function buildRejectMsg(rebateFlowId, rebateId, formId){
      		$("#"+formId+" input[name='rebateFlowId']").val(rebateFlowId);
      		$("#"+formId+" input[name='rebateId']").val(rebateId);
      	}
		function showCheckModal(a, rebateFlowId, conId, formId){
      		buildCheckMsg(rebateFlowId, conId, formId);
      		$(a).attr({href :"#firmModal"});
      	}
		function buildCheckMsg(rebateId, conId, formId){
      		$("#"+formId+" input[name='rebateId']").val(rebateId);
      		$("#"+formId+" input[name='conId']").val(conId);
      	}
      	
		function checked(formId){
	      	$("#"+formId).submit();
      	}
      	function doSubmit(url){
			$("#form1").attr("action",url);
			$("#form1").submit();
		}	

	</script>
</body>
</html>