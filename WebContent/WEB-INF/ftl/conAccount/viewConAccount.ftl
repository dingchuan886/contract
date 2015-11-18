<!DOCTYPE html>
<html>
  <head>
    <title>合同相关说明</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${contract_ct_url_html}css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${contract_ct_url_html}css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="${contract_ct_url_html}css/print.css" rel="stylesheet" type="text/css">
  <style type="text/css" media="print">
    .v-h {display:none;}
    </style>
  </head>
  <body>
    <div class="container">
  <div class="row-fluid">
  <div class="span12">
  <div class="well">
  <form action="#">
  <div class="v-h" style="text-align:center">
      <input name="" type="button" value="打印" onClick="toPrint();">
       <ul class="nav nav-tabs">
       		<#if conAccount.con_id?starts_with("SHCT")>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/myContract/viewCtContract?conId=${conAccount.con_id}'" data-toggle="tab">查看合同</a></li>
            <#elseif conAccount.con_id?starts_with("SHCZ")>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/myContract/viewCzContract?conId=${conAccount.con_id}'" data-toggle="tab">查看合同</a></li>
            <#elseif conAccount.con_id?starts_with("SHZH")>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/myContract/viewZhContract?conId=${conAccount.con_id}'" data-toggle="tab">查看合同</a></li>
            </#if>
            <#if (accCount>0)>
            <li class="active"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/conAccount/viewAccount?conId=${conAccount.con_id}'" data-toggle="tab">查看相关说明</a></li>
            </#if>
            <#if (billCount>0)>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConBill/viewBills?id=${conAccount.con_id}'" data-toggle="tab" >查看开票</a></li>
            </#if>
            <#if (rebateCount>0)>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConRebate/rebateBill?conId=${conAccount.con_id}'" data-toggle="tab">查看返利</a></li>
   			</#if>
    </ul>
      </div>
      
      <div class="m2">
        <h2>合同相关说明</h2>
        <ul>
          <div class="m-info">
            <ul>
              <li><span>合同号：</span><p>${conAccount.con_id}</p></li>
              <li><span>签单人：</span><p>${conAccount.user_name}</p></li>
              <li><span>日期：</span><p>${conAccount.update?string("yyyy-MM-dd")}</p></li>
            </ul>
          </div>
          <li><span>站点</span>${orgName}</li>
          <li><span>业务类型</span><label class="checkbox"><input type="checkbox" <#if conAccount.con_type==0>checked="checked"</#if> disabled>1、会员</label><label class="checkbox"><input type="checkbox"  <#if conAccount.con_type==1>checked="checked"</#if> disabled>2、硬广</label><label class="checkbox"><input type="checkbox"  <#if conAccount.con_type==2>checked="checked"</#if> disabled>3、会员兼硬广</label>
          <label class="checkbox"><input type="checkbox" <#if conAccount.con_type==3>checked="checked"</#if> disabled>4、车展</label></li><li><label class="checkbox"><input type="checkbox" <#if conAccount.con_type==4>checked="checked"</#if> disabled>5、车展+广告</label><label class="checkbox"><input type="checkbox" <#if conAccount.con_type==5>checked="checked"</#if> disabled>6、团购</label>
          <label class="checkbox"><input type="checkbox" <#if conAccount.con_type==6>checked="checked"</#if> disabled>7、特卖惠</label>
          <label class="checkbox"><input type="checkbox" <#if conAccount.con_type==7>checked="checked"</#if> disabled>8、车展+活动</label>
          <label class="checkbox"><input type="checkbox" <#if conAccount.con_type==8>checked="checked"</#if> disabled>9、广告+活动</label>
          </li>
          <li><span>有无返利</span><label class="checkbox"><input type="checkbox" <#if conAccount.isback==0>checked="checked"</#if> disabled>无</label><label class="checkbox"><input type="checkbox" <#if conAccount.isback==1>checked="checked"</#if> disabled>有</label><label >返利比例：&nbsp${conAccount.isback_des}</label></li>
          <li><span>执行进度</span><label class="checkbox"><input type="checkbox" <#if conAccount.plan==0>checked="checked"</#if> disabled>1、按期执行</label><label class="checkbox"><input type="checkbox" <#if conAccount.plan==1>checked="checked"</#if> disabled>2、适当调整</label><label >将于${conAccount.plan_des}&nbsp&nbsp&nbsp月份投放完毕。</label></li>
          <li><span>开票说明</span><label class="checkbox"><input type="checkbox" <#if conAccount.bill_type==0>checked="checked"</#if> disabled>1、平开</label>
          <label class="checkbox"><input type="checkbox" <#if conAccount.bill_type==1>checked="checked"</#if> disabled>2、高开</label>
          <label class="checkbox"><input type="checkbox" <#if conAccount.bill_type==2>checked="checked"</#if> disabled>3、不开票</label>
          <label >预计开票金额：${conAccount.bill_des}</label>
          </li>
          <li><span>回款预计</span><label class="checkbox"><input type="checkbox" <#if conAccount.back_exp==0>checked="checked"</#if> disabled>1、分期投放，及时开票后回款</label><label class="checkbox"><input type="checkbox" <#if conAccount.back_exp==1>checked="checked"</#if> disabled>2、适当延期</label><label >预计${conAccount.back_des}&nbsp&nbsp可回款</label></li>
          <li><span>&nbsp;</span><label>返点受益人：<em>${conAccount.ben_person}</em></label></li><li><span>&nbsp;</span><label>联系方式：<em>${conAccount.phone}</em></label></li>
        </ul> 
      </div>
    </form>
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
        <button class="btn" data-dismiss="modal" onclick="closeDiv()">暂不处理</button>
      </div>
    </div>
   </div>
</div>
</div>
</div>
    <!-- Js -->
    <script src="${contract_ct_url_html}js/jquery.min.js"></script>
    <script src="${contract_ct_url_html}js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	function managerAudit(conType, accId){
      		if(confirm("确定通过该相关说明么?")){
      			window.location.href = "${contract_ct_url}contract/deptManagerAcc/auditAccount?conType="+conType+"&accId="+accId;
      		}
      	}
      	function showModal(conType, conFlowId, conId, formId){
      		buildRejectMsg(conType, conFlowId, conId, formId);
      		$("#myModal").attr({"class":"modal hide fade in","style":"display:block"});
      		
      	}
      	function buildRejectMsg(conType, conFlowId, conId, formId){
      		$("#"+formId+" input[name='conType']").val(conType);
      		$("#"+formId+" input[name='conFlowId']").val(conFlowId);
      		$("#"+formId+" input[name='conId']").val(conId);
      	}
      	function rejected(formId){
      		$("#"+formId).submit();
      	}
      	
      	function closeDiv(){
      		$("#myModal").attr({"class":"modal hide fade","style":"display:none"});
      	}
      	
    function toPrint(){
	$(".form1").removeClass("form1");
	window.print();
	$(".form1").addClass("form1");
}
    </script>
  </body>
</html>