<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>返利列表</title>
 <@c.html/>
  <@c.js/>
   <script type="text/javascript">
   		function changePage(pageNum){
            var id = $("#id").val(); 
			if(pageNum<1){
				pageNum = 1;
			}
			if(pageNum>${pager.totalPages}){
				pageNum = ${pager.totalPages};
			}
			location.href = "${contract_ct_url}contract/tbConRebate/rebateBill?pageNum=" + pageNum+"&conId=${conId}";
		}
		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}
		
	 </script>

 </head>
  <body style="padding-top:25px;">
        <div class="span12"  style="width: 960px !important;margin: 0 auto !important;float: none !important;font-size:12px">
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
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConBill/viewBills?id=${conId}'" data-toggle="tab" >查看开票</a></li>
            </#if>
            <#if (rebateCount>0)>
            <li class="active"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConRebate/rebateBill?conId=${conId}'" data-toggle="tab">查看返利</a></li>
   			</#if>
    </ul>
	    <div class="page-header">
	    	<h4>返利列表</h4>
	    </div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
          <div style="color:red">${result}</div>
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>站点</th>
                  <th>合同编号</th>
                  <th>业务员</th>
                  <th>申请日期</th>
                  <th>返利金额</th>
                  <th>状态</th>
                  <th>操作</th>                
                </tr>
              </thead>
              <tbody>
               <#if (pager.list?size>0)>
               <#list pager.list as map>
                <tr>
                 <td> ${map.org.org_name}</td>
                  <td>${map.rebate.con_id}</td>
                  <td>${map.user.user_name}</td>
                  <td>${map.rebate.create?string("yyyy-MM-dd")}</td>
                  <td>${map.rebate.this_back}</td>
                  <td>
                   <#if map.rebate.rebate_state==0>
                       	 未审核
                   <#elseif map.rebate.rebate_state==1>
                    	部门经理审核通过
                   <#elseif map.rebate.rebate_state==7>
                                                            区域经理审核通过
                   <#elseif map.rebate.rebate_state==2>
                                                            流程部审核通过
                   <#elseif map.rebate.rebate_state==3>
                   		总经理审核通过
                   <#elseif map.rebate.rebate_state==4>
                                                            驳回，驳回原因：${map.rebateFlow.rebate_msg}
                   </#if>
                  	</td>
                   <td><a href="${contract_ct_url}contract/tbConRebate/showRebate?rebateId=${map.rebate.back_id}" target="blank">查看</a></td>
                </tr>
            </#list>
            </#if>
              </tbody>
            </table>
          </div>        
        </div>
       
      </div>
    </div>
     <div class="span12" align="right" style="margin-left:200px;">
			<a href="javascript:void(0)" onclick="changePage(1)">首页</a>&nbsp&nbsp&nbsp
			<a href="javascript:void(0)" onclick="changePage(${pager.pageNum-1})">上一页</a>&nbsp
			第${pager.pageNum}/${pager.totalPages}页		
			<a href="javascript:void(0)" onclick="changePage(${pager.pageNum+1})">下一页</a>&nbsp&nbsp		
			<a href="javascript:void(0)" onclick="changePage(${pager.totalPages})">尾页</a>&nbsp&nbsp		
			<input type="text" id="pageNum" value="${pager.pageNum}" style="width:25px;"/><button type="button" onclick="changePage1()">跳页</button>&nbsp	&nbsp&nbsp		
		</div>

 
  </body>
</html>