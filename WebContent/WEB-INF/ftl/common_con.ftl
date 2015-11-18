<!DOCTYPE html>
<html>
<head>
 <title>车展合同管理</title>
 <#macro html>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <!-- Bootstrap -->
    <link href="${contract_ct_url_html}css/bootstrap.min.css" rel="stylesheet" media="screen"> 
    <link href="${contract_ct_url_html}css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="${contract_ct_url_html}css/time/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link href="${contract_ct_url_html}css/styles.css" rel="stylesheet" media="screen"> 

   </#macro>
  </head>
  <body>
  <#macro head>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="#" class="brand"><img src="${contract_ct_url_html}images/vice-logo.png" alt=""></a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">登录人：<a href="" class="navbar-link">${userInfo.username}</a></p>
            <ul class="nav">
              <li class="active"><a href="http://finance.chetuan.com/finance/index">首页</a></li>
              <li><a href="http://finance.chetuan.com/finance/showUserInfo_preQueryUserInfo">人事模块</a></li>
              <li><a href="http://finance.chetuan.com/finance/financeinit">财务模块</a></li>
              <li><a href="#">合同模块</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    </#macro>
<#macro left act=999 hqmanage=0>
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
            <#assign dept=["3","13","17","2"]>
              <#if dept?seq_contains(userInfo.deptcode) || (userInfo.deptcode==1 && userInfo.orgcode != 1)>
              <li class="nav-header">业务员</li>
              <li <#if act==0>class="active"</#if>><a href="${contract_ct_url}contract/czContract/toAddCzContract">新申请</a></li>
              <li <#if act==1>class="active"</#if>><a href="${contract_ct_url}contract/myContract/showCzContract">我的合同</a></li>
              <li <#if act==2>class="active"</#if>><a href="${contract_ct_url}contract/conSupply/toCzSupply">合同补充<span id="userAddCon" class="badge badge-important"></span></a></li>
             <!-- <li <#if act==2>class="active"</#if>><a href="#">新合同<span class="badge badge-important">2</span></a></li> -->
             <!-- <li <#if act==3>class="active"</#if>><a href="#">执行中<span class="badge badge-important">1</span></a></li> -->
              <li <#if act=70>class="active"</#if>><a href="${contract_ct_url}contract/tbConFlowAudit/lookRejectCon?conType=SHCZ">查看合同驳回<span id="userConReject" class="badge badge-important"></span></a></li> 
              <#-- <li <#if act=22>class="active"</#if>><a href="${contract_ct_url}contract/conAccount/lookMyRejectAccount">查看相关说明驳回<span id="userAccountReject" class="badge badge-important"></span></a></li> -->
              <li <#if act==8>class="active"</#if>><a href="${contract_ct_url}contract/tbConBill/ywyViewRejectBills">查看开票驳回<span id="userBillReject" class="badge badge-important"></span></a></li>
              <!--  <li <#if act==4>class="active"</#if>><a href="#">查看广告占用</a></li> -->
             <#-- <li <#if act=71>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/lookRejectRebate">查看返利驳回<span id="userRebateReject" class="badge badge-important"></span></a></li> -->
               <li class="nav-header">广告</li> 
               <li <#if act==5>class="active"</#if>><a href="${contract_ct_url}contract/adveritesment/toAddAheadAdvertisement" target="_blank">广告提前申请</a></li> 
               <li <#if act==53>class="active"</#if>><a href="${contract_ct_url}contract/adveritesment/showMyAheadAdv">查看广告提前申请<span id="userAheadAdvReject" class="badge badge-important"></span></a></li> 
               <li <#if act==80>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toShowSchedule">查看广告排期</a></li>
               <li <#if act==81>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toAddSchedule">添加广告排期</a></li>
               </#if>
              <#if (dept?seq_contains(userInfo.deptcode)&&userInfo.rolecode==2&&(userInfo.orgcode==1||userInfo.orgcode==0)) || (userInfo.rolecode==4&&userInfo.orgcode!=1&&userInfo.deptcode==1) || (userInfo.userCity != "" && userInfo.userCity!="##")>
              <li class="divider"></li>
              
              <li class="nav-header">部门经理</li>
              <li <#if act==6>class="active"</#if>><a href="${contract_ct_url}contract/deptManager/showCzDeptManager">查看合同</a></li>
              <li <#if act==72>class="active"</#if>><a href="${contract_ct_url}contract/tbConFlowAudit/deptConAuditBill?conType=SHCZ">合同待审核<span id="managerAuditCon" class="badge badge-important"></span></a></li>
              <#-- <li <#if act=21>class="active"</#if>><a href="${contract_ct_url}contract/deptManagerAcc/listNeedAuditAcc?conType=SHCZ">相关说明待审核<span id="managerAuditAccount" class="badge badge-important"></span></a></li> -->
              <li  <#if act==10>class="active"</#if>><a href="${contract_ct_url}contract/tbConBill/toCheckBill?type=managerCheck">开票待审核<span id="managerAuditBill" class="badge badge-important"></span></a></li>
             <#-- <li <#if act==73>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/rebateManagerBill">返利审核<span id="managerAuditRebate" class="badge badge-important"></span></a></li> -->
               <li <#if act==51>class="active"</#if>><a href="${contract_ct_url}contract/adveritesment/showManagerCheckAdv">审核广告提前申请<span id="managerAuditAheadAdv" class="badge badge-important"></span></a></li> 
              </#if>
              <#if userInfo.usercode=="Poly0085" || userInfo.deptcode=='18'>
               <li class="nav-header">流程部</li>
              <li <#if act==15>class="active"</#if>><a href="${contract_ct_url}contract/flowViewContract/showCzContract">查看合同</a></li>
              <li <#if act==74>class="active"</#if>><a href="${contract_ct_url}contract/tbConFlowAudit/flowConAuditBill?conType=SHCZ">合同待审核<span id="flowAuditCon" class="badge badge-important"></span></a></li>
              <li <#if act==75>class="active"</#if>><a href="${contract_ct_url}contract/tbConFlowAudit/flowSealConAuditBill?conType=SHCZ">待归档<span id="flowAuditStamp" class="badge badge-important"></span></a></li>
              <li <#if act==11>class="active"</#if>><a href="${contract_ct_url}contract/tbConBill/toCheckBill?type=flowCheck">开票待审核<span id="flowAuditBill" class="badge badge-important"></span></a></li>
             <#-- <li <#if act==76>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/rebateFlowBill">返利待审核<span id="flowAuditRebate" class="badge badge-important"></span></a></li> -->
               <li <#if act==52>class="active"</#if>><a href="${contract_ct_url}contract/adveritesment/showFlowCheckAdv">审核广告提前申请<span id="flowAuditAheadAdv" class="badge badge-important"></span></a></li>
               <li <#if act==54>class="active"</#if>><a href="${contract_ct_url}contract/adveritesment/showAllAheadAdv">查看所有广告提前申请</a></li> 
               <li <#if act=9>class="active"</#if>><a href="${contract_ct_url}contract/tbConBill/showAllBills">查看所有开票</a></li>
               <li <#if act=12>class="active"</#if>><a href="${contract_ct_url}contract/tbConBill/searchAccessBill">确认开票<span id="finAuditBill" class="badge badge-important"></span></a></li>
             <#-- <li <#if act=71>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/showAllRebate" target="_blank">查看所有返利</a></li> -->
              <!--  <li><a href="#">新合同</a></li> -->
              <li <#if act==80>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toShowSchedule">查看广告排期</a></li>
              </#if>
              <#-- 总站财务 -->
              <#if userInfo.usercode=="Poly0064" || userInfo.usercode=="Poly0218" || userInfo.usercode=="Poly0589" || userInfo.usercode=="Poly0590">
              <li class="nav-header">发票管理</li>
              <li <#if act=12>class="active"</#if>><a href="${contract_ct_url}contract/tbConBill/searchAccessBill">确认开票<span id="finAuditBill" class="badge badge-important"></span></a></li>
              <li <#if act=77>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/finConfirmBill">确认返利<span id="finAuditRebate" class="badge badge-important"></span></a></li>
              </#if>
              <#if userInfo.rolecode=="1">
              <li class="nav-header">严总</li>
              <li <#if hqmanage==1>class="active"</#if>><a href="${contract_ct_url}contract/flowViewContract/showCzContract">查看合同</a></li>
              <li <#if act==79>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/rebateHQManagerBill">返利审核<span id="hqManagerAuditRebate" class="badge badge-important"></span></a></li>
              <li <#if act==80>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toShowSchedule">查看广告排期</a></li>
              </#if>
              
               <#if userInfo.usercode=="Poly0024">
              <li class="nav-header">严向玲</li>
              <li <#if hqmanage==1>class="active"</#if>><a href="${contract_ct_url}contract/flowViewContract/showCzContract">查看合同</a></li>
              <li <#if act==80>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toShowSchedule">查看广告排期</a></li>
              </#if>
               <#if userInfo.usercode=="Poly0007">
              <li class="nav-header">张总</li>
              <li <#if hqmanage==1>class="active"</#if>><a href="${contract_ct_url}contract/flowViewContract/showCzContract">查看合同</a></li>
              <li <#if act==80>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toShowSchedule">查看广告排期</a></li>
              
              </#if>
              <#-- 分站财务 能够帮助分站经理查看合同的情况 -->
              <#if (userInfo.orgcode!='1' && userInfo.rolecode=='3' && userInfo.deptcode=='4') || (userInfo.deptcode=='11' && userInfo.rolecode=='3' && userInfo.orgcode!="1")>
              <li class="nav-header">分站财务</li> 
              <#-- <li <#if act==6>class="active"</#if>><a href="${contract_ct_url}contract/deptManager/showCzDeptManager">查看合同</a></li> -->
              <li <#if act==90>class="active"</#if>><a href="${contract_ct_url}contract/areaManager/showCzareaManager">查看合同</a></li>
              </#if>
              <#-- 广告发布人员的权限 -->
              <#if userInfo.deptcode==5 || userInfo.deptcode==7 || userInfo.usercode=="Poly0004" || userInfo.usercode=="Poly0066" || userInfo.usercode=="Poly0051">
               <li class="nav-header">广告</li> 
               <li <#if act==83>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/editorFindCzContract">查看广告合同</a></li>
               <li <#if act==80>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toShowSchedule">查看广告排期</a></li>
               <li <#if act==82>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toCheckSchedule">查看广告</a></li> 
               </#if>
               <#-- 董总的管理员权限  -->
               <#if userInfo.usercode=="Poly0005">
                <li class="nav-header">业务员</li>
              <li <#if act==0>class="active"</#if>><a href="${contract_ct_url}contract/czContract/toAddCzContract">新申请</a></li>
              <li <#if act==1>class="active"</#if>><a href="${contract_ct_url}contract/myContract/showCzContract">我的合同</a></li>
             <!-- <li <#if act==2>class="active"</#if>><a href="#">新合同<span class="badge badge-important">2</span></a></li> -->
             <!-- <li <#if act==3>class="active"</#if>><a href="#">执行中<span class="badge badge-important">1</span></a></li> -->
              <li <#if act==70>class="active"</#if>><a href="${contract_ct_url}contract/tbConFlowAudit/lookRejectCon?conType=SHCZ">查看合同驳回<span id="userConReject" class="badge badge-important"></span></a></li> 
              <li <#if act==22>class="active"</#if>><a href="${contract_ct_url}contract/conAccount/lookMyRejectAccount">查看相关说明驳回<span id="userAccountReject" class="badge badge-important"></span></a></li> 
              <li <#if act==8>class="active"</#if>><a href="${contract_ct_url}contract/tbConBill/ywyViewRejectBills">查看开票驳回<span id="userBillReject" class="badge badge-important"></span></a></li>
              <!--  <li <#if act==4>class="active"</#if>><a href="#">查看广告占用</a></li> -->
              <li <#if act==71>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/lookRejectRebate">查看返利驳回<span id="userRebateReject" class="badge badge-important"></span></a></li> 
               <li class="nav-header">广告</li> 
               <li <#if act==5>class="active"</#if>><a href="${contract_ct_url}contract/adveritesment/toAddAheadAdvertisement" target="_blank">广告提前申请</a></li> 
               <li <#if act==53>class="active"</#if>><a href="${contract_ct_url}contract/adveritesment/showMyAheadAdv">查看广告提前申请<span id="userAheadAdvReject" class="badge badge-important"></span></a></li> 
               <li <#if act==80>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toShowSchedule">查看广告排期</a></li>
               <li <#if act==81>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toAddSchedule">添加广告排期</a></li>
                   <li class="nav-header">流程部</li>
              <li <#if act==15>class="active"</#if>><a href="${contract_ct_url}contract/flowViewContract/showCzContract">查看合同</a></li>
              <#--<li <#if act=74>class="active"</#if>><a href="${contract_ct_url}contract/tbConFlowAudit/flowConAuditBill?conType=SHCZ">合同待审核<span id="flowAuditCon" class="badge badge-important"></span></a></li>-->
              <li <#if act==75>class="active"</#if>><a href="${contract_ct_url}contract/tbConFlowAudit/flowSealConAuditBill?conType=SHCZ">待归档<span id="flowAuditStamp" class="badge badge-important"></span></a></li>
              <li <#if act==11>class="active"</#if>><a href="${contract_ct_url}contract/tbConBill/toCheckBill?type=flowCheck">开票待审核<span id="flowAuditBill" class="badge badge-important"></span></a></li>
              <#--<li <#if act=9>class="active"</#if>><a href="${contract_ct_url}contract/tbConBill/lcbViewRejectBills">查看开票驳回<span class="badge badge-important"></span></a></li>-->
              <li <#if act==76>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/rebateFlowBill">返利待审核<span id="flowAuditRebate" class="badge badge-important"></span></a></li>
              <#--<li <#if act=71>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/lookAllRejectRebate">查看返利驳回</a></li> -->
               <li <#if act==52>class="active"</#if>><a href="${contract_ct_url}contract/adveritesment/showFlowCheckAdv">审核广告提前申请<span id="flowAuditAheadAdv" class="badge badge-important"></span></a></li>
               <li <#if act==54>class="active"</#if>><a href="${contract_ct_url}contract/adveritesment/showAllAheadAdv">查看所有广告提前申请</a></li> 
              <!--  <li><a href="#">新合同</a></li> -->
              <li <#if act==80>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toShowSchedule">查看广告排期</a></li>
               <li class="nav-header">严总</li>
               <li <#if act==79>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/rebateHQManagerBill">返利审核<span id="hqManagerAuditRebate" class="badge badge-important"></span></a></li>
               <li class="nav-header">广告</li> 
               <li <#if act==83>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/editorFindZhContract">查看广告合同</a></li>
               <li <#if act==80>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toShowSchedule">查看广告排期</a></li>
               <li <#if act==82>class="active"</#if>><a href="${contract_ct_url}contract/advSchedule/toCheckSchedule">查看广告</a></li> 
               </#if>
               <#-- 区域经理的审核 -->
               <#if userInfo.userdis != "##" && userInfo.userdis != "">
              <li class="divider"></li>
              
              <li class="nav-header">区域经理</li>
              <li <#if act==90>class="active"</#if>><a href="${contract_ct_url}contract/areaManager/showCzareaManager">查看合同</a></li>
              <li <#if act==91>class="active"</#if>><a href="${contract_ct_url}contract/tbConFlowAudit/areaConAuditBill?conType=SHCZ">合同待审核<span id="areaAuditCon" class="badge badge-important"></span></a></li>
              <#-- <li <#if act=21>class="active"</#if>><a href="${contract_ct_url}contract/deptManagerAcc/listNeedAuditAcc?conType=SHCZ">相关说明待审核<span id="managerAuditAccount" class="badge badge-important"></span></a></li> -->
              <li  <#if act==92>class="active"</#if>><a href="${contract_ct_url}contract/tbConBill/toCheckBill?type=areaCheck">开票待审核<span id="areaAuditBill" class="badge badge-important"></span></a></li>
               <#-- <li <#if act==93>class="active"</#if>><a href="${contract_ct_url}contract/tbConRebate/rebateAreaBill">返利审核<span id="areaAuditRebate" class="badge badge-important"></span></a></li> -->
              </#if>
              
              <#if userInfo.usercode=="Poly0411">
              <li class="nav-header"></li>
              <li <#if hqmanage==1>class="active"</#if>><a href="${contract_ct_url}contract/flowViewContract/showCzContract">查看合同</a></li>
              </#if>
            </ul>
          </div>
        </div>
</#macro>
    <!-- 常用开户行选择-弹出层 -->
    <div id="popBankAddrLayer" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">常用开户行选择</h3>
      </div>
      <div class="modal-body">
        <div class="row-fluid">
          <div class="span12 bank-list">
            <ul>
              <li>
                <label for="bankaddr1">
                  <input type="radio" id="bankaddr1" name="banksela" value="1">
                  <span class="banknumber">6222 0210 0104 4341 388</span>
                  <span class="bankaddress">浦东开发区石油交易所支行</span>
                </label>
              </li>
              <li>
                <label for="bankaddr2">
                  <input type="radio" id="bankaddr2" name="banksela" value="1">
                  <span class="banknumber">6222 0210 0104 4341 388</span>
                  <span class="bankaddress">浦东开发区石油交易所支行</span>
                </label>
              </li>
              <li>
                <label for="bankaddr3">
                  <input type="radio" id="bankaddr3" name="banksela" value="1">
                  <span class="banknumber">6222 0210 0104 4341 388</span>
                  <span class="bankaddress">浦东开发区石油交易所支行</span>
                </label>
              </li>
              <li>
                <label for="bankaddr4">
                  <input type="radio" id="bankaddr4" name="banksela" value="1">
                  <span class="banknumber">6222 0210 0104 4341 388</span>
                  <span class="bankaddress">浦东开发区石油交易所支行</span>
                </label>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
        <button class="btn btn-primary">确定</button>
      </div>
    </div>
<#macro js>
    <script src="${contract_ct_url_html}js/jquery.min.js"></script>
    <script src="${contract_ct_url_html}js/bootstrap.min.js"></script>
    <script src="${contract_ct_url_html}js/time/bootstrap-datetimepicker.min.js"></script>
    <script src="${contract_ct_url_html}js/functions.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	$.ajax({
    		url:"/contract/checkState/getState",
    		data:{
    			"userId":"${userInfo.usercode}"
    		},
    		success:function(data){
    			var suc = eval("("+data+")");
    			$("#userConReject").html(suc.userConReject);
    			$("#userAddCon").html(suc.userAddCon);
			    $("#userAccountReject").html(suc.userAccountReject);
			    $("#userBillReject").html(suc.userBillReject);
			    $("#userRebateReject").html(suc.userRebateReject);
			    $("#userAheadAdvReject").html(suc.userAheadAdvReject);
			    $("#managerAuditCon").html(suc.managerAuditCon);
			    $("#managerAuditAccount").html(suc.managerAuditAccount);
			    $("#managerAuditBill").html(suc.managerAuditBill);
			    $("#managerAuditRebate").html(suc.managerAuditRebate);
			    $("#managerAuditAheadAdv").html(suc.managerAuditAheadAdv);
			    $("#flowAuditCon").html(suc.flowAuditCon);
			    $("#flowAuditStamp").html(suc.flowAuditStamp);
			    $("#flowAuditBill").html(suc.flowAuditBill);
			    $("#flowAuditRebate").html(suc.flowAuditRebate);
			    $("#flowAuditAheadAdv").html(suc.flowAuditAheadAdv);
			    $("#finAuditBill").html(suc.finAuditBill);
			    $("#finAuditRebate").html(suc.finAuditRebate);
			    $("#hqManagerAuditRebate").html(suc.hqManagerAuditRebate);
			    $("#areaAuditCon").html(suc.areaAuditCon);
			    $("#areaAuditBill").html(suc.areaAuditBill);
			    $("#areaAuditRebate").html(suc.areaAuditRebate);
    		}
    	});
    });
    </script>
</#macro>
    <script type="text/javascript">
      $(".form_datetime").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true //选择日期后自动关闭
      });
      
    </script>

  </body>
</html>
