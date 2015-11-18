<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>流程部审核</title>
 <@c.html/>
 </head>
  <body>
  <@c.head/>
<@c.left act=11/>
   
        <div class="span10">
          <ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">流程部开票审核</li>
          </ul>
            <div class="page-header">
            <h2>流程部开票审核</h2>
          </div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
          <div style="color:red">${result}</div>
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>站点</th>
                  <th>业务员</th>
                  <th>合同编号</th>
                  <th>客户名称</th>
                  <th>签订日期</th>
                  <th>活动日期</th>
                  <th>合同金额(元)</th>
                  <th>执行金额(元)</th>
                  <th>开票金额</th>
                  <th>操作</th>                
                </tr>
              </thead>
              <tbody>
               <#if (pager.list?size>0)>
               <#list pager.list as map>
                <tr>
                  <td>${map.org.org_name}</td>
                  <td>${map.conBill.user_name}</td>
                   <td>${map.conBill.con_id}</td>
                  <td>${map.conBill.cus_name}</td>
                  <td>
                    <#if (map.conBill.con_id)?starts_with('SHCZ')>
                   ${map.conCz.create?string("yyyy-MM-dd")}  
                   <#elseif (map.conBill.con_id)?starts_with('SHZH')>
                     ${map.conZh.create?string("yyyy-MM-dd")}  
                   <#elseif (map.conBill.con_id)?starts_with('SHCT') >
                   ${map.conCt.create?string("yyyy-MM-dd")}  
                   </#if>
                  </td>
                  <td>
                   <#if (map.conBill.con_id)?starts_with('SHCZ')>
                    ${map.conCz.act_date}  
                   <#elseif (map.conBill.con_id)?starts_with('SHZH')>
                    ${map.conZh.act_date?string("yyyy-MM-dd")}
                   <#elseif (map.conBill.con_id)?starts_with('SHCT') >
                   ${map.conCt.act_date?string("yyyy-MM-dd")}
                   </#if>
                  </td>
                  <td>
                   <#if (map.conBill.con_id)?starts_with('SHCZ')>
                   ${map.conCz.con_total_price}
                   <#elseif (map.conBill.con_id)?starts_with('SHZH')>
                     ${map.conZh.con_total_price}
                   <#elseif (map.conBill.con_id)?starts_with('SHCT') >
                     ${map.conCt.con_total_price}
                   </#if>
                  </td>
                  <td>
                    <#if (map.conBill.con_id)?starts_with('SHCZ')>
                   ${map.addCon.exe_price}
                   <#elseif (map.conBill.con_id)?starts_with('SHZH')>
                     ${map.addCon.exe_price}
                   <#elseif (map.conBill.con_id)?starts_with('SHCT') >
                     ${map.addCon.exe_price}
                   </#if>
                  </td>
                  <td>
                  	${map.conBill.sal_bill!}
                  </td>
                   <td><a href="javascript:managerAudit('flowCheck',  ${map.conBill.bill_id})">审核通过</a>
                   <a href="#" data-toggle="modal" onclick="showModal(this, ${map.conBill.bill_id}, 'rejectForm');">驳回</a>
                    <a href="${contract_ct_url}contract/tbConBill/to_look?bill_id=${map.conBill.bill_id}" target="_blank">查看详情</a>
                       <#if (map.conBill.con_id)?starts_with('SHCT')>
                   <a href="${contract_ct_url}contract/ctContract/viewCtContract?conId=${map.conBill.con_id}" target="_blank">查看合同</a>
                   <#elseif (map.conBill.con_id)?starts_with('SHCZ')>
                     <a href="${contract_ct_url}contract/czContract/viewCzContract?conId=${map.conBill.con_id}" target="_blank">查看合同</a>
                   <#elseif (map.conBill.con_id)?starts_with('SHZH')>
                     <a href="${contract_ct_url}contract/zhContract/viewZhContract?conId=${map.conBill.con_id}" target="_blank">查看合同</a>
                   </#if>
                    </td>
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
    
    <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-footer" style="text-align:center; margin-top:10px">
      	<form id="rejectForm" action="${contract_ct_url}contract/tbConBill/rejectBill" method="post">
      		<label for="type" class="control-label">审核驳回</label>
      		合同编号: <input type="type" name="bill_Id" disabled /><br/><br/>
      		        <input type="hidden" name="bill_id"  />   
      		        <input type="hidden" name="pageNum" value="${pageNum}">
      		        <input type="hidden" name="type" value="flowCheck"/>   
      		驳回原因: <textarea name="bill_msg" cols="30" rows="4"></textarea><br/>
      	</form>
        <button class="btn btn-info" data-dismiss="modal" onclick="rejected('rejectForm');">确定驳回</button>
        <button class="btn" data-dismiss="modal">暂不处理</button>
      </div>
    </div>
  <@c.js/>
  <script>
    function changePage(pageNum){
			if(pageNum<1){
				pageNum = 1;
			}
			if(pageNum>${pager.totalPages}){
				pageNum = ${pager.totalPages};
			}
			location.href = "${contract_ct_url}contract/tbConBill/toCheckBill?type=flowCheck&pageNum=" + pageNum;
		}
		
		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}
  
  function managerAudit(conType, billId){
      		if(confirm("确定审核通过么?")){
      			window.location.href = "${contract_ct_url}contract/tbConBill/assessBill?type="+conType+"&bill_id="+billId+"&pageNum=${pageNum}";
      		}
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
  </script>
  </body>
</html>