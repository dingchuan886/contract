<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>业务员驳回查看</title>
 <@c.html/>
 </head>
  <body>
  <@c.head/>
<@c.left act=8/>
   
        <div class="span10">
            <ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">业务员驳回查看</li>
          </ul>
            <div class="page-header">
            <h2>业务员驳回查看</h2>
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
                  <th>驳回理由</th>
                  <th>申请单详情</th>                
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
                   ${map.conCz.create}
                   <#elseif (map.conBill.con_id)?starts_with('SHZH')>
                     ${map.conZh.create}
                   <#elseif (map.conBill.con_id)?starts_with('SHCT') >
                     ${map.conCt.create}
                   </#if>
                  </td>
                  <td>
                   <#if (map.conBill.con_id)?starts_with('SHCZ')>
                   ${map.conCz.act_date}
                   <#elseif (map.conBill.con_id)?starts_with('SHZH')>
                     ${map.conZh.act_date}
                   <#elseif (map.conBill.con_id)?starts_with('SHCT') >
                     ${map.conCt.act_date}
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
                   <td> ${map.tbBillFlow.bill_msg}</td>
                    <td><a href="${contract_ct_url}contract/tbConBill/to_look?bill_id=${map.conBill.bill_id}" target="blank">查看</a></td>
                    <td> <a href="javascript:todelete('${map.conBill.bill_id}')">删除</td>
                </tr>
            </#list>
            </#if>
              </tbody>
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
         
        
  <@c.js/>
  </body>
  <script>
    function todelete(id){
 	if(confirm("确定删除该开票么?")){
     location.href="${contract_ct_url}contract/tbConBill/to_delete?bill_id="+id+"&pageNum=${pager.pageNum}";
     }
    
    }
  
             function changePage(pageNum){
			if(pageNum<1){
				pageNum = 1;
			}
			if(pageNum>${pager.totalPages}){
				pageNum = ${pager.totalPages};
			}
			location.href = "${contract_ct_url}contract/tbConBill/ywyViewRejectBills?pageNum="+pageNum;
		}
		
		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}  
  </script>
</html>