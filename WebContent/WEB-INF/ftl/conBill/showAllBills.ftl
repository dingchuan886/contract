<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>流程部审核</title>
 <@c.html/>
 </head>
  <body>
  <@c.head/>
<@c.left act=9/>
   
        <div class="span10">
          <ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">流程部查看开票</li>
          </ul>
            <div class="page-header">
            <h2>流程部查看开票</h2>
          </div>
          <form action="/contract/tbConBill/showAllBills" method="post" class="form-horizontal" id="form1">
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">开票状态</label>
                <div class="controls">
                  <select name="conState" id="">
                    <option value=""></option>
                    <option value="1" <#if pageCondition.conState=='1'>selected</#if>>经理审核通过</option>
                    <option value="6" <#if pageCondition.conState=='6'>selected</#if>>区域经理审核通过</option>
                    <option value="2" <#if pageCondition.conState=='2'>selected</#if>>流程部审核通过</option>
                  </select>
                </div>
              </div>
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">业务员</label>
                <div class="controls">
                  <input type="text" name="userName" value="${pageCondition.userName!}"/>
                  <input type="hidden" name="pageNum" value="1" id="pageNum1">
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">客户名称</label>
                <div class="controls">
                  <input type="text" name="cusName" value="${pageCondition.cusName!}"/>
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">签订时间</label>
                <div class="controls">
                   <div class="input-append date form_datetime">
                      <input type="text" readonly="true" name="createTime" style="width:180px;" value="${pageCondition.createTime!}">
                      <span class="add-on"><i class="icon-remove"></i></span>
                      <span class="add-on"><i class="icon-th"></i></span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
             <div class="control-group span5">
                <label for="type" class="control-label">站点</label>
                <div class="controls">
                  <select name="orgName" id="queryOrg">
                  	<option value="">---请选择---</option>
                  </select>
                </div>
             </div>
               <div><button onclick="javascript:document.forms[0].submit()">查询</button>
               </div>
            </div>
           
          </form>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>站点</th>
                  <th>业务员</th>
                  <th>合同编号</th>
                  <th>客户名称</th>
                  <th>签订日期</th>
                  <th>开票状态</th>
                  <th>合同金额(元)</th>
                  <th>执行金额(元)</th>
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
                   <#elseif (map.conBill.con_id)?starts_with('SHCT')>
                   ${map.conCt.create?string("yyyy-MM-dd")}  
                   </#if>
                  </td>
                  <td>
                  	<#if map.conBill.bill_state==1>
                  	部门经理已审核
                  	<#elseif map.conBill.bill_state==2>
                  	流程部已审核
                  	<#elseif map.conBill.bill_state==6>
                  	区域经理已审核
                  	<#elseif map.conBill.bill_state==0>
                  	未审核
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
  <@c.js/>
  <script>
  $(".form_datetime").datetimepicker({
		startView: 3,
		weekStart:1,
        minView: 3, //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true //选择日期后自动关闭
      });
      
   $(document).ready(function(){
   		var id = '${pageCondition.orgName!}';
   		$.post("${contract_ct_url}contract/brandAndSeries/getAllOrg",function(i){
   			var data = eval("("+i+")");
   			for(var i=0;i<data.length;i++){
   				if(id==data[i].org_id){
   					$("#queryOrg").append("<option value='"+data[i].org_id+"' selected>"+data[i].org_name+"</option>");
   				}else{
   					$("#queryOrg").append("<option value='"+data[i].org_id+"'>"+data[i].org_name+"</option>");
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
  
  </script>
  </body>
</html>