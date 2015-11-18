<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
<title>流程部审核</title>
 <@c.html/>
 </head>
  <body>
  <@c.head/>
<@c.left act=51/>
   
        <div class="span10">
          <ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">经理审核合同提前申请</li>
          </ul>
            <div class="page-header">
            <h2>经理审核</h2>
          </div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
          <div style="color:red">${result}</div>
            <table class="table table-bordered">
              <thead>
                <tr>
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
                   <td><a href="javascript:managerAudit('${map.adv.adv_id}')">通过</a>
                   <a href="#" data-toggle="modal" onclick="showModal(this, '${map.adv.adv_id}', 'rejectForm');">驳回</a>
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
      	<form id="rejectForm" action="${contract_ct_url}contract/adveritesment/managerRejectAdv" method="post">
      		<label for="type" class="control-label">审核驳回</label>
      		        <input type="hidden" name="advId"/>   
      		         <br/>  
      		驳回原因: <textarea name="adv_msg" cols="30" rows="4"></textarea><br/>
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
			if(pageNum>${pageResult.totalPages}){
				pageNum = ${pageResult.totalPages};
			}
			location.href = "${contract_ct_url}contract/adveritesment/showManagerCheckAdv?pageNum=" + pageNum;
		}
		
		function changePage1(){
			var pageNum = $("#pageNum").val();
			changePage(pageNum);
		}
  
  function managerAudit(advId){
      		if(confirm("确定通过该合同么?")){
      			window.location.href = "${contract_ct_url}contract/adveritesment/managerAuditAdv?advId="+advId;
      		}
      	}
  	function showModal(a, advId, formId){
      		buildRejectMsg(advId,formId);
      		$(a).attr({href :"#myModal"});
      	}
      	
      	function buildRejectMsg(advId, formId){
      		$("#"+formId+" input[name='advId']").val(advId);
      	}
      	function rejected(formId){
      	    var bill_msg = $("textarea[name='adv_msg']").val();
      	    if(bill_msg==''||bill_msg==null){alert("驳回原因不能为空！");$("#myModal").attr("style","display:'display'");return false;}
      		$("#"+formId).submit();
      	}
  </script>
  </body>
</html>