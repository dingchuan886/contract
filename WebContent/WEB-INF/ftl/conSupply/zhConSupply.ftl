<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
<title>我的合同</title>
 <@c.html/>
 </head>
  <body>
     <@c.head/>
	 <@c.left act=2/>
	 <div class="span10">
	 <ul class="breadcrumb">
            <li><a href="http://oa.chetuan.com/finance/index"><i class="icon-home"></i> 首页</a> <span class="divider">/</span></li>
            <li><a href="${contract_ct_url}contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">合同补充</li>
          </ul>
            <h2>广告合同补充</h2>
	 	<ul class="nav nav-tabs">
	 		<!-- to mycontract -->
            <li class=""><a href="javascript:void(0)" onclick="javascript:window.location.href='${contract_ct_url}contract/conSupply/toCzSupply'" data-toggle="tab">众智车展合同</a></li>
       
            <li class="active"><a href="javascript:void(0)" onclick="javascript:window.location.href='${contract_ct_url}contract/conSupply/toZhSupply'" data-toggle="tab">众智315广告合同</a></li>
            <li class=""><a href="javascript:void(0)" onclick="javascript:window.location.href='${contract_ct_url}contract/conSupply/toCtSupply'" data-toggle="tab">车团合同</a></li>
          </ul>
          <form action="${contract_ct_url}contract/conSupply/toZhSupply" class="form-horizontal" id="form1">
           <div class="row-fluid">
               <div class="control-group span5">
                <label for="type" class="control-label">客户名称</label>
                <div class="controls">
                  <input type="text" name="cusName" value="${pageCondition.cusName}"/>
                  <input type="hidden" name="pageNum" value="1" id="pageNum1">
                </div>
              </div>
            <div class="control-group span5">
                <label for="type" class="control-label">合同编号</label>
                <div class="controls">
                  <input type="text" name="conId" value="${pageCondition.conId}"/>
                </div>
              </div>
            <div class="row-fluid">
              
             <div class="control-group span5">
                <label for="type" class="control-label">签订时间</label>
                <div class="controls">
                   <div id="dateuser" class="input-append date form_datetime">
                      <input type="text" readonly="true" name="createTime" style="width:180px;" value="${pageCondition.createTime}">
                      <span class="add-on"><i class="icon-remove"></i></span>
                      <span class="add-on"><i class="icon-th"></i></span>
                  </div>
                </div>
              </div>
              
            </div>
               <div align="center"><button onclick="javascript:this.form.submit()">查询</button></div>
           
          </form>
          <div id="showDiv" style="display:none"><h4>提交中.....</h4></div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>合同编号</th>
                  <th>填写时间</th>
                  <th>客户名称</th>
                  <th>乙方电话</th>
                  <th>位置</th>
                  <th>规格</th>
                  <th>品牌</th>
                  <th>投放日期</th>
                  <th>执行日期</th>
                  <th>合同总金额(元)</th>
                  <th>执行金额(元)</th>
                  <th>操作</th>
                  
                </tr>
              </thead>
              <tbody>
              <#list myZhContract.list as zh>
                <tr <#if zh_index%2==0>style="background-color:#FDD8C6"<#else>style="background-color:#CBE4E8"</#if>>
                  <td>${zh.conZh.con_zh_id}</td>
                  <td>${zh.conZh.create?string("yyyy-MM-dd")}</td>
                  <td>${zh.conZh.cus_name}</td>
                  <td>${zh.conZh.cus_tel}</td>
                  
                  <#-- 位置 -->
                  <td>
                  <#list zh.conZhSub as zhSub>
                  	<#if zhSub_index==(zh.conZhSub?size-1)>
                  		${zhSub.position}
                  		<input type="hidden" name="zh_sub_id[${zhSub_index}]" value="${zhSub.con_zh_sub_id}">
                  	<#else>
                  		${zhSub.position}<hr/>
                  		<input type="hidden" name="zh_sub_id[${zhSub_index}]" value="${zhSub.con_zh_sub_id}">
                  	</#if>
                  </#list>
                  </td>
                  <#-- 规格 -->
                  <td>
                  	<#list zh.conZhSub as zhSub>
                  	<#if zhSub_index==(zh.conZhSub?size-1)>
                  		${zhSub.standard}
                  	<#else>
                  		${zhSub.standard}<hr/>
                  	</#if>
                  </#list>
                  </td>
                  <#-- 品牌 -->
                  <td>	
                  <#list zh.conZhSub as zhSub>
                  	<#if zhSub_index==(zh.conZhSub?size-1)>
                  		${zhSub.brand}
                  	<#else>
                  		${zhSub.brand}<hr/>
                  	</#if>
                  </#list></td>
                  <#-- 投放日期 -->
                  <td>
                  	<#list zh.conZhSub as zhSub>
                  	  <#if zhSub_index==(zh.conZhSub?size-1)>
                  		${zhSub.put_date}
                  	  <#else>
                  		${zhSub.put_date}<hr/>
                  	  </#if>
                  	</#list>
                  </td>
                  <td>
                  <#list zh.conZhSub as zhSub>
                  	  <#if zhSub_index==(zh.conZhSub?size-1)>
                  		<div class="input-append date form_datetime">
	                      <input type="text" readonly="true" name="exe_date[${zhSub_index}]" value="${zh.supZh[zhSub_index].exe_date}" style="width:80px">
	                      <span class="add-on"><i class="icon-remove"></i></span>
	                      <span class="add-on"><i class="icon-th"></i></span>
                 		 </div>
                  	  <#else>
                  		<div class="input-append date form_datetime">
	                      <input type="text" readonly="true" name="exe_date[${zhSub_index}]" value="${zh.supZh[zhSub_index].exe_date}" style="width:80px">
	                      <span class="add-on"><i class="icon-remove"></i></span>
	                      <span class="add-on"><i class="icon-th"></i></span>
		                </div><hr/>
                  	  </#if>
                  	</#list>
                  </td>
                  <td>${zh.conZh.con_total_price}</td>
                  <td><input name="exe_price" value="${zh.supZh[0].exe_price}" style="width:90px"></td>
                  
                  <td>
                  <input type="hidden" name="conZhSubCount" value="${zh.conZhSubCount}">
                  <a href="javascript:void(0)" onclick="viewCon(this)">查看合同</a>&nbsp&nbsp
                  <#if (zh.billCount==0)>
                 		<a href="javascript:void(0)" onclick="subSup(this)">提交/修改</a>
                 	</#if>
                  </td>
                </tr>
                </#list>
              
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
	<div align="right">
		<a href="javascript:void(0)" onclick="changePage(1)">首页</a>&nbsp&nbsp&nbsp
		<a href="javascript:void(0)" onclick="changePage(${myZhContract.pageNum-1})">上一页</a>&nbsp
		第${myZhContract.pageNum}/${myZhContract.totalPages}页		
		<a href="javascript:void(0)" onclick="changePage(${myZhContract.pageNum+1})">下一页</a>&nbsp&nbsp		
		<a href="javascript:void(0)" onclick="changePage(${myZhContract.totalPages})">尾页</a>&nbsp&nbsp		
		<input type="text" id="pageNum" value="${myZhContract.pageNum}" style="width:25px;"/><button type="button" onclick="changePage1()">跳页</button>&nbsp	&nbsp&nbsp		
			
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
$("#dateuser").datetimepicker('remove');
$("#dateuser").datetimepicker({
        startView: 3,
		weekStart:1,
        minView: 3, //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true //选择日期后自动关闭
      });
$(document).ready(function(){
	$("#close1").bind("click",function(){
		$("#myModal").attr({"class":"modal hide fade","aria-hidden":"true","style":"display:none;"});
	});
	
	$("#close2").bind("click",function(){
		$("#myModal").attr({"class":"modal hide fade","aria-hidden":"true","style":"display:none;"});
	});
});

function changePage(pageNum){
	if(pageNum<1){
		pageNum = 1;
	}
	if(pageNum>${myZhContract.totalPages}){
		pageNum = ${myZhContract.totalPages};
	}
	$("#pageNum1").val(pageNum);
	$("#form1").submit();
}

function changePage1(){
	var pageNum = $("#pageNum").val();
	changePage(pageNum);
}


//查看合同
function viewCon(obj){
	var id = $(obj).parent().siblings("td").eq(0).html();
	window.open("/contract/myContract/viewZhContract?conId="+id);
}

function subSup(data){
	var flag = false;
	var numreg = /^\d+(\.\d{2})?$/;
	var dates = [];
	var subCount = $(data).siblings(":hidden[name='conZhSubCount']").val();
	var price = $(data).parent().parent("tr").find(":text[name='exe_price']").val();
	for(var i=0;i<subCount;i++){
		dates[i] = $(data).parent().parent("tr").find(":text[name='exe_date["+i+"]']").val();
		if(dates[i]==""){
			alert("请填写执行日期!");
			flag = false;
			return;
		}
	}
	var con_zh_sub_ids = [];
	var conId = $(data).parent().siblings("td:first").html();
	for(var j=0;j<subCount;j++){
		con_zh_sub_ids[j] = $(data).parent().parent("tr").find(":hidden[name='zh_sub_id["+j+"]']").val();
	}
	if(!numreg.test(price)){
		alert("请正确填写执行金额!");
		flag = false;
		return;
	}
	
	
	flag = confirm("确定提交吗?");
	if(flag){
		$.ajax({
			url:"${contract_ct_url}contract/conSupply/saveZhSupply",
			data:{"exePrice":price,"exeDate":dates,"conId":conId,"sub_con_id":con_zh_sub_ids
			},
			beforeSend:function(){
				$("#showDiv").show();
			},
			success:function(data){
				$("#showDiv").hide();
				var suc = eval("("+data+")");
				if(suc.back=="200"){
					alert("提交成功!");
					window.location.reload();
				}else{
					alert("提交失败!");
					window.location.reload();
				}
			}

		});
	}
}

</script>
  </body>
</html>