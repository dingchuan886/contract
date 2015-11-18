<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>查看合同提前刊登申请</title>
 <@c.html/>
 </head>
  <body>
  <@c.head/>
<@c.left act=53/>
   
        <div class="span10">
          <ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">我的合同提前刊登申请</li>
          </ul>
            <div class="page-header">
            <h2>查看合同提前刊登申请</h2>
          </div>
           <form action="/contract/adveritesment/showMyAheadAdv" class="form-horizontal" id="form1">
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">合同状态</label>
                <div class="controls">
                  <select name="state" id="">
                    <option value=""></option>
                    <option value="1" <#if pageCondition.conState==5>selected</#if>>待审核</option>
                    <option value="2" <#if pageCondition.conState==0>selected</#if>>经理审核通过</option>
                    <option value="3" <#if pageCondition.conState==1>selected</#if>>流程部审核通过</option>
                    <option value="4" <#if pageCondition.conState==2>selected</#if>>已驳回</option>
                  </select>
                </div>
              </div>
              <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">客户名称</label>
                <div class="controls">
                  <input type="text" name="cus_name" value="${pageCondition.cus_name}"/>
                  <input type="hidden" name="pageNum" value="1">
              </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">业务员</label>
                <div class="controls">
                  <input type="text" name="userName" value="${pageCondition.userName}"/>
              </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">提交时间</label>
                <div class="controls">
                  <select name="createTime" id="">
                    <option value="0">全部</option>
                    <option value="3" <#if pageCondition.createTime==3>selected</#if>>最近三天</option>
                    <option value="7" <#if pageCondition.createTime==7>selected</#if>>最近七天</option>
                    <option value="30" <#if pageCondition.createTime==30>selected</#if>>最近一月</option>
                  </select>
                </div>
              </div>
               <div><button onclick="javascript:this.form.submit()">查询</button></div>
            </div>
          </form>
          <div id="myShow" hidden="true"><h4>正在查询中，请稍等...</h4></div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>状态</th>
                  <th>站点</th>
                  <th>申请人</th>
                  <th>原因</th>
                  <th>客户名称</th>
                  <th>合同金额</th>
                  <th>拿回原件日期</th>
                  <th>操作</th>                
                </tr>
              </thead>
              <tbody>
               <#if (pageResult.list?size>0)>
               <#list pageResult.list as map>
                <tr>
                  <td>
                  	<#if map.adv.adv_state==1>
                  	等待审核
                  	<#elseif map.adv.adv_state==2>
                  	经理审核通过
                  	<#elseif map.adv.adv_state==3>
                  	流程部审核通过
                  	<#elseif map.adv.adv_state==4>
                  	已驳回
                  	</#if>
                  </td>
                  <td>${map.orgName}</td>
                  <td>${map.adv.user_name}</td>
                   <td>${map.adv.reazon}</td>
                  <td>${map.adv.cus_name}</td>
                  <td>
                    ${map.adv.con_price}
                  </td>
                  <td>
                  <#if map.adv.year!="">
                   	${map.adv.year}年</#if>${map.adv.month}月${map.adv.day}日
                  </td>
                   <td> <#if map.adv.adv_state==4><a href="javascript:void(0)" onclick="showReject(this,'${map.adv.adv_id}')">驳回详情</a>&nbsp
                   		<a href="javascript:void(0)" onclick="delAcc('${map.adv.adv_id}')">删除</a>
                   </#if>
                    <a href="${contract_ct_url}contract/adveritesment/viewAheadAdv?advId=${map.adv.adv_id}" target="blank">详情</a></td>
                </tr>
            </#list>
            </#if>
              </tbody>
            </table>
          </div>        
        </div>
        <div align="right">
			<a href="javascript:void(0)" onclick="changePage(1)">首页</a>&nbsp&nbsp&nbsp
			<a href="javascript:void(0)" onclick="changePage(${pageResult.pageNum-1})">上一页</a>&nbsp
			第${pageResult.pageNum}/${pageResult.totalPages}页		
			<a href="javascript:void(0)" onclick="changePage(${pageResult.pageNum+1})">下一页</a>&nbsp&nbsp		
			<a href="javascript:void(0)" onclick="changePage(${pageResult.totalPages})">尾页</a>&nbsp&nbsp		
			<input type="text" id="pageNum" value="${pageResult.pageNum}" style="width:25px;"/><button type="button" onclick="changePage1()">跳页</button>&nbsp	&nbsp&nbsp		
		</div>
      </div>
    </div>
    <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-footer" style="text-align:center; margin-top:10px">
      		<label for="type" class="control-label">驳回详情</label>
      		驳回人: <input type="type" id="reject_name" disabled /><br/><br/>
      		驳回时间: <input type="type" id="reject_time" disabled /><br/><br/>
      		驳回原因: <textarea id="msg" cols="30" rows="4" readonly="true"></textarea><br/>
        <button class="btn" data-dismiss="modal" id="closeDiv">关闭</button>
      </div>
    </div>
  <@c.js/>
  <script>
    function changePage(pageNum){
			if(pageNum<1){
				pageNum = 1;
			}
			if(pageNum>${pageResult.totalPages}){
				pageNum = ${pageResult.totalPages};
			}
			$(":hidden[name='pageNum']").val(pageNum);
			$("#form1").submit();
		}
		
		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}
		
		function showReject(a,advId){
			$.ajax({
					url:"/contract/adveritesment/rejectDetail",
					type:"get",
					data:{"advId":advId},
					beforeSend:function(XMLHttpRequest){
                      $("#myShow").show();
                	},
					success:function(dat){
						$("#myShow").hide();
						var suc = eval("("+dat+")");
						var reject_time = suc.date;
						var reject_msg = suc.msg;
						var reject_name = suc.rejectName;
						$("#reject_name").val(reject_name);
						$("#reject_time").val(reject_time);
						$("#msg").val(reject_msg);
						$("#myModal").attr({"class":"modal hide fade in","style":"display:block;"})
					}
				});
			
			
		}
		function delAcc(accId){
			var delFlag = confirm("确定删除吗？");
			if(delFlag){
				location.href="/contract/adveritesment/delAheadAdv?advId="+accId;
			}
			return;
		}
		
  $(document).ready(function(){
  	$("#closeDiv").bind("click",function(){
  		$("#myModal").attr({"class":"modal hide fade","style":"display:none;"})
  	});
  });
  </script>
  </body>
</html>