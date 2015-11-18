<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>确认回款</title>
 <@c.html/>
 </head>
  <body>
  <@c.head/>
<@c.left act=12/>
        <div class="span10">
           <ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">确认回款</li>
          </ul>
            <div class="page-header">
            <h3>确认回款</h3>
          </div>
           <form action="/contract/tbConBill/searchAccessBill" method="post" class="form-horizontal" id="form1">
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
              <div class="control-group span5">
              	<label for="type" class="control-label">合同编号</label>
              	<div class="controls">
              	<input type="text" name="conId" value="${pageCondition.conId!}">
              	</div>
              </div>
               
            </div>
           <div class="row-fluid">
           		<div class="control-group span5">
           			<label class="control-label">站点</label>
           			<div class="controls">
           				<select name="orgName" id="orgName">
           					<option value="-1">--请选择--</option>
           				</select>
           			</div>
           		</div>
           		<div class="control-group span4"><button onclick="doSubmit('/contract/tbConBill/searchAccessBill')">查询</button>
               </div>
           </div>
          </form>
          <div style="color:red">${result!}</div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>操作</th>
                  <th>站点</th>
                  <th>业务员</th>
                  <th>客户名称</th>
                  <th>合同编号</th>
                  <th>合同总价</th>
                  <th>已开票金额</th>
                  <th>申请开票金额</th>
                  <th>发票类型</th>
                  <th>是否高开</th>
                  <th>高开金额</th>
                  <th>开票税号</th>
                  <th>开户银行</th>
                  <th>银行账号</th>
                  <th>公司地址</th>
                  <th>联系电话</th>
                  <th>开票确认</th>
                  <th>回款确认</th>
                </tr>
              </thead>
              <tbody>
              <#if (pager.list?size>0)>
               <#list pager.list as map>
                <tr>
                  <td><a href="${contract_ct_url}contract/tbConBill/to_look?bill_id=${map.conBill.bill_id}" target="blank">查看</a>
                       <#if map.conBill.bill_code==null && map.conBill.rm_date==null>
                   <a href="#" data-toggle="modal" onclick="showModal(this, ${map.conBill.bill_id}, 'rejectForm');">驳回</a>
                       <#else>
                       </#if>
                  </td>
                  <td>${map.org.org_name}</td>
                  <td>${map.conBill.user_name}</td>
                  <td>${map.conBill.cus_name}</td>
                  <td>${map.conBill.con_id}</td>
                  <td>
                   <#if (map.conBill.con_id)?starts_with('SHCZ')>
                   ${map.conCz.con_total_price}
                   <#elseif (map.conBill.con_id)?starts_with('SHZH')>
                     ${map.conZh.con_total_price}
                   <#elseif (map.conBill.con_id)?starts_with('SHCT') >
                     ${map.conCt.con_total_price}
                   </#if>
                  </td>
                  <td>${map.conBill.al_bill}</td>
                  <td>${map.conBill.sal_bill}</td>
                  <td>
                  <#if map.conBill.bill_type==0>
                  	广告
                  	<#elseif map.conBill.bill_type==1>
                  	广告发布费
                  		<#elseif map.conBill.bill_type==2>
                  	活动服务费
                  		<#elseif map.conBill.bill_type==3>
                  	其他
                  </#if>
                  </td>
                  <td>
                  <#if map.conBill.state==1>
                                                         是
                   <#else>
                                                          否
                   </#if>
                   </td>
                  <td>${map.conBill.bill_high}</td>
                  <td>${map.conBill.duty_para}</td>
                  <td>${map.conBill.bank_name}</td>
                  <td>${map.conBill.bank_account}</td>
                  <td>${map.conBill.bank_addr}</td>
                  <td>${map.conBill.phone}</td>
                 
                  <#if map.conBill.bill_code==null>
                   <td><a data-toggle="modal" href="#yhk" class="btn" onclick="javascript:assignForm(${map.conBill.bill_id},'checkBill')">未开票</a></td>
                  <#else>
                  <td> 已开票&nbsp;开票金额：${map.conBill.bill_money}</td>
                  </#if>
                  <#if map.conBill.rm_date==null>
                  <td><a data-toggle="modal" href="#ykp" class="btn" onclick="javascript:assignForm(${map.conBill.bill_id},'checkRmBill')">未回款</a></td>
                  <#else>
                  <td>已回款&nbsp;已回款金额:${map.conBill.rm_account}</td>
                  </#if>
                </tr>
            </#list>
            </#if>
             </tbody>
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
    </div>
     
		
    <div id="ykp" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
       <form action="${contract_ct_url}contract/tbConBill/checkRmBill" method="post" id="checkRmBill">
      <div class="bank-radio" style="display:block;">
        <div class="control-group" style="padding:15px">
          <label>回款金额</label>
          <input type="text" id="" placeholder="请输入金额" name="rm_account">
          <input type="hidden" name="pageNum" value="${pageNum}"/>
          <label>回款时间</label>
           <div class="controls">
                  <div class="input-append date form_datetime">
                      <input type="text" value="" readonly="true" name="rm_date_string" data-date="2013-02-21" style="width:180px;" id="rm_date">
                      <span class="add-on"><i class="icon-remove"></i></span>
                      <span class="add-on"><i class="icon-th"></i></span>
                  </div>
                   <input type="hidden" name="bill_id"/>
                </div>
          </div>
        <div class="modal-footer" style="text-align:center; margin-top:10px">
          <button class="btn btn-danger" data-dismiss="modal" onclick="javascript:submitCheckRmBill()">确认</button>
        </div>
      </div>
      </form>
    </div>


    <div id="yhk" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <form action="${contract_ct_url}contract/tbConBill/checkBill" method="post" id="checkBill">
      <div class="bank-radio" style="display:block;">
        <div class="control-group" style="padding:15px">
          <label>开票金额</label>
          <input type="text" id=""name="bill_money" placeholder="">
          <input type="hidden" name="pageNum" value="${pageNum}"/>
          <label>发票号</label>
          <input type="text" id="" placeholder="" name="bill_code">
          <input type="hidden" name="bill_id"/>
        </div>
        <div class="modal-footer" style="text-align:center; margin-top:10px">
          <button class="btn btn-danger" data-dismiss="modal" onclick="javascript:submitCheckBill()">确认</button>
        </div>
      </div>
     </form>
   </div>
     <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-footer" style="text-align:center; margin-top:10px">
      	<form id="rejectForm" action="${contract_ct_url}contract/tbConBill/rejectBillByCW" method="post">
      		<label for="type" class="control-label">审核驳回</label>
      		申请单编号: <input type="type" name="bill_Id" disabled /><br/><br/>
      		        <input type="hidden" name="bill_id"  /> 
      		        <input type="hidden" name="pageNum" value="${pageNum}">  
      		        <input type="hidden" name="type" value="managerCheck"/>   
      		驳回原因: <textarea name="bill_msg" cols="30" rows="4"></textarea><br/>
      	</form>
        <button class="btn btn-info" data-dismiss="modal" onclick="rejected('rejectForm');">确定驳回</button>
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
   $(document).ready(function(){
   		var org = '${pageCondition.orgName!}';
		$.post("/contract/brandAndSeries/getAllOrg",function(data){
			var suc = eval("("+data+")");
			for(var i=0;i<suc.length;i++){
				if(suc[i].org_id==parseInt(org)){
					$("#orgName").append("<option value='"+suc[i].org_id+"' selected>"+suc[i].org_name+"</option>");
				}else{
					$("#orgName").append("<option value='"+suc[i].org_id+"'>"+suc[i].org_name+"</option>");
				}
			}
		});
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
  		function assignForm(id,formId){
		 $("#"+formId+" input[name='bill_id']").val(id);
		}
		
		function submitCheckBill(){
			
		   var countreg = /^\d+(\.\d{2})?$/;
		   var bill_money = $("input[name='bill_money']").val();
		   var bill_code =$("input[name='bill_code']").val();
		   if(bill_money==''||bill_money==null||!countreg.test(bill_money)){alert("开票金额必须是数字！");return false;}
		   if(bill_code==''||bill_code==null){alert("发票号不能为空！");return false;}
		   $("#checkBill").submit();
		}
		
		function submitCheckRmBill(){
		      var countreg = /^\d+(\.\d{2})?$/;
		      var rm_account = $("input[name='rm_account']").val();
		      var rm_date_string = $("input[name='rm_date_string']").val();
		      if(rm_account==''||rm_account==null||!countreg.test(rm_account)){alert("回款金额必须是数字！");return false;}
		     if(rm_date_string==''||rm_date_string==null){alert("回款时间不能为空！");return false;}
		     $("#checkRmBill").submit();
		}
		
			function showModal(a, conFlowId, formId){
      		buildRejectMsg( conFlowId,formId);
      		$(a).attr({href :"#myModal"});
      	}
      	
      	function buildRejectMsg(billId, formId){
      		$("#"+formId+" input[name='bill_Id']").val(billId);
      		$("#"+formId+" input[name='bill_id']").val(billId);
      	}
      	function rejected(formId){
      	    var bill_msg = $("textarea[name='bill_msg']").val();
      	    if(bill_msg==''||bill_msg==null){alert("驳回原因不能为空！");$("#myModal").attr("style","display:'display'");return false;}
      		$("#"+formId).submit();
      	}
		//下载数据
		function doSubmit(url){
			$("#form1").attr("action",url);
			$("#form1").submit();
		}	
</script>
  </body>
</html>