<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
<title>浏览合同</title>
 <@c.html/>
 </head>
  <body>
     <@c.head/>
	 <@c.left act=83 hqmanage=1/>
	 <div class="span10">
	 	<ul class="nav nav-tabs">
	 		<li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/advSchedule/editorFindCzContract'" data-toggle="tab">众智车展合同</a></li>
	        <li class="active"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/advSchedule/editorFindZhContract'" data-toggle="tab">众智315广告合同</a></li>
        </ul>
          <form action="/contract/advSchedule/editorFindZhContract" class="form-horizontal" id="form1">
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">业务员</label>
                <div class="controls">
                  <input type="text" name="userName" value="${pageCondition.userName}"/>
                  <input type="hidden" name="pageNum" value="1" id="pageNum1">
                  </select>
                </div>
              </div>
               <div class="control-group span5">
                <label for="type" class="control-label">合同类别</label>
                <div class="controls">
                  <select name="conType">
                  	<option value=""></option>
                  	<option value="0" <#if pageCondition.conType==0>selected</#if>>会员</option>
                  	<option value="1" <#if pageCondition.conType==1>selected</#if>>硬广</option>
                  	<option value="2" <#if pageCondition.conType==2>selected</#if>>会员+硬广</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row-fluid">
             
              <div class="control-group span5">
                <label for="type" class="control-label">客户名称</label>
                <div class="controls">
                  <input type="text" name="cusName" value="${pageCondition.cusName}"/>
                </div>
              </div>
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
            </div>
             <div class="row-fluid">
              <div class="control-group span5">
              </div>
              <div><button onclick="doSubmit('/contract/advSchedule/editorFindZhContract')">查询</button></div>
            </div>
          </form>
          <div hidden="true" id="loading"><h4>数据加载中，请稍等...</h4></div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
            <table class="table table-bordered">
              <thead>
                 <tr>
                  <th>序号</th>
                  <th>合同编号</th>
                  <th>合同状态</th>
                  <th>站点</th>
                  <th>业务员</th>
                  <th>填写时间</th>
                  <th>是否归档</th>
                  <th>位置</th>
                  <th>规格</th>
                  <th>品牌</th>
                  <th>投放日期</th>
                </tr>
              </thead>
              <tbody>
              <#list myZhContract.list as zh>
                <tr <#if zh_index%2==0>style="background-color:#FDD8C6"<#else>style="background-color:#CBE4E8"</#if>>
                  <td>
                  ${zh_index+1+(myZhContract.pageNum-1)*10}
                  </td>
                  <td>${zh.conZh.con_zh_id}</td>
                  <td>
                  <#if zh.conZh.con_state==0>
                  	等待审核
                  </#if>
                  <#if zh.conZh.con_state==2>
                  	经理已审核
                  </#if>
                  <#if zh.conZh.con_state==3>
                  	流程部已归档
                  </#if>
                  <#if zh.conZh.con_state==4>
                  	已驳回
                  </#if></td>
                  <td>${zh.orgName}${zh.deptName}</td>
                  <td>${zh.conZh.user_name}</td>
                  <td>${zh.conZh.create?string("yyyy-MM-dd")}</td>
                   <td><#if zh.conZh.con_state==3>是
                  	<#else>
                  	否
                  </#if>
                 </td>
                  <#-- 位置 -->
                  <td>
                  <#list zh.conZhSub as zhSub>
                  	<#if zhSub_index==(zh.conZhSub?size-1)>
                  		${zhSub.position}
                  		<input type="hidden" name="zh_sub_id[${zhSub_index}]" value="${zhSub.con_zh_sub_id}">
                  	<#else>
                  		${zhSub.position}<hr color="black" SIZE="3"/>
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
                  		${zhSub.standard}<hr color="black" SIZE="3"/>
                  	</#if>
                  </#list>
                  </td>
                  <#-- 品牌 -->
                  <td>	
                  <#list zh.conZhSub as zhSub>
                  	<#if zhSub_index==(zh.conZhSub?size-1)>
                  		${zhSub.brand}
                  	<#else>
                  		${zhSub.brand}<hr color="black" SIZE="3"/>
                  	</#if>
                  </#list></td>
                  <#-- 投放日期 -->
                  <td>
                  	<#list zh.conZhSub as zhSub>
                  	  <#if zhSub_index==(zh.conZhSub?size-1)>
                  		${zhSub.put_date}
                  	  <#else>
                  		${zhSub.put_date}<hr color="black" SIZE="3"/>
                  	  </#if>
                  	</#list>
                  </td>
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
   </div>
<@c.js/>
<script type="text/javascript">
$(".form_datetime").datetimepicker({
		startView: 3,
		weekStart:1,
        minView: 3, //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true //选择日期后自动关闭
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





//下载数据
function doSubmit(url){
	$("#form1").attr("action",url);
	$("#form1").submit();
}
</script>
  </body>
</html>