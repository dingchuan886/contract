<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>开票列表</title>
 <@c.html/>
 </head>
  <body style="padding-top:25px;">
 
   
        <div class="span12" style="width: 960px !important;margin: 0 auto !important;float: none !important;font-size:12px">
               <ul class="nav nav-tabs">
             <#if conId?starts_with("SHCT")>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/myContract/viewCtContract?conId=${conId}'" data-toggle="tab">查看合同</a></li>
            <#elseif conId?starts_with("SHCZ")>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/myContract/viewCzContract?conId=${conId}'" data-toggle="tab">查看合同</a></li>
            <#elseif conId?starts_with("SHZH")>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/myContract/viewZhContract?conId=${conId}'" data-toggle="tab">查看合同</a></li>
            </#if>
            <#if (accCount>0)>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/conAccount/viewAccount?conId=${conId}'" data-toggle="tab">查看相关说明</a></li>
            </#if>
            <#if (billCount>0)>
            <li class="active"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConBill/viewBills?id=${conId}'" data-toggle="tab" >查看开票</a></li>
            </#if>
            <#if (rebateCount>0)>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConRebate/rebateBill?conId=${conId}'" data-toggle="tab">查看返利</a></li>
   			</#if>
    </ul>
            <div class="page-header">
            <h4>开票列表</h4>
          </div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
          <div style="color:red">${result}</div>
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>合同编号</th>
                  <th>开票日期</th>
                  <th>开票金额</th>
                  <th>回款日期</th>
                  <th>状态</th>
                  <th>操作</th>                
                </tr>
              </thead>
              <tbody>
               <#if (pager.list?size>0)>
               <#list pager.list as map>
                <tr>
                 <td> ${map_index+index}</td>
                  <td>
                   <#if (map.conBill.con_id)?starts_with('SHCZ')>
                   ${map.conCz.con_cz_id}
                   <#elseif (map.conBill.con_id)?starts_with('SHZH')>
                     ${map.conZh.con_zh_id}
                   <#elseif (map.conBill.con_id)?starts_with('SHCT') >
                     ${map.conCt.con_ct_id}
                   </#if>
                  </td>
                  <td>
                  ${map.conBill.create?string("yyyy-MM-dd")}
                  </td>
                  <td>
                    ${map.conBill.sal_bill}
                  </td>
                  <td>
                    ${map.conBill.rm_date?string("yyyy-MM-dd")}  
                  </td>
                   <#if map.conBill.bill_state==0>	
                                                       <td> 未审核</td>
                   <#elseif map.conBill.bill_state==1>
                                                        <td>经理审核通过</td>
                   <#elseif map.conBill.bill_state==6>
                   										<td>区域经理审核通过</td>
                   <#elseif map.conBill.bill_state==2>
                                                        <td>流程部审核通过</td>
                   <#elseif map.conBill.bill_state==3>
                                                     <td>驳回，驳回原因：${map.tbBillFlow.bill_msg}</td>
                                                     
                   </#if>
                  
                   <td><a href="${contract_ct_url}contract/tbConBill/to_look?bill_id=${map.conBill.bill_id}" target="blank">查看</a></td>
                </tr>
            </#list>
            </#if>
              </tbody>
            </table>
          </div>        
        </div>
        <div align="right" class="span12" style="margin-left:200px;">
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
   function changePage(pageNum){
            var id = $("#id").val(); 
			if(pageNum<1){
				pageNum = 1;
			}
			if(pageNum>${pager.totalPages}){
				pageNum = ${pager.totalPages};
			}
			location.href = "${contract_ct_url}contract/tbConBill/viewBills?pageNum=" + pageNum+"&id=${id}";
		}
		
		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}
		
	 </script>
  </body>
</html>