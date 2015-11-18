<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>返利列表</title>
 <@c.html/>
  <@c.js/>
   <script type="text/javascript">
   $(document).ready(function(){
    $(".form_datetime").datetimepicker({
		startView: 3,
		weekStart:1,
        minView: 3, //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true //选择日期后自动关闭
      });
   
   })
   
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

 </head>
  <body style="padding-top:25px;">
        <div class="span12">
         
	    <div class="page-header">
	    	<h4>返利列表</h4>
	    </div>
	    <form action="/contract/tbConRebate/showAllRebate" method="post" class="form-horizontal" id="form1">
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">返利状态</label>
                <div class="controls">
                  <select name="conState" id="">
                    <option value=""></option>
                    <option value="1" <#if pageCondition.conState=='1'>selected</#if>>经理审核通过</option>
                    <option value="7" <#if pageCondition.conState=='7'>selected</#if>>区域经理审核通过</option>
                    <option value="2" <#if pageCondition.conState=='2'>selected</#if>>流程部审核通过</option>
                    <option value="3" <#if pageCondition.conState=='3'>selected</#if>>总经理审核通过</option>
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
                 <td> ${map.org.org_name!}</td>
                  <td>${map.rebate.con_id!}</td>
                  <td>${map.user.user_name!}</td>
                  <td>${map.rebate.create?string("yyyy-MM-dd")}</td>
                  <td>${map.rebate.this_back!}</td>
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