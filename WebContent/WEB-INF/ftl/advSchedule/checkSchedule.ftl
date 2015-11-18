<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
    <title>查看广告排期</title>
  <@c.html/>
  </head>
  <body>
     <@c.head/>
	 <@c.left act=82/>
         <div class="span10">
          <ul class="breadcrumb">
            <li><a href="#">合同管理</a> <span class="divider">/</span></li>
            <li class="active">添加广告排期</li/>
          </ul>
          <form action="#" class="form-horizontal">
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">合同号</label>
                <div class="controls">
                 	 <input type="text" name="conId" id="conId" 
                 	 	<#if zhContract.con_zh_id??>
                 	 	value="${zhContract.con_zh_id}"
                 	 	<#elseif zhContract.con_cz_id??>
                 	 	value="${zhContract.con_cz_id}"
                 	 	</#if>
                 	 >
              </div>
            </div>
            </div>
            <ul class="nav">
              <button class="btn btn-info" type="button" style="margin-left:180px" onclick="findZh()">查询</button>
            </ul>
            <div class="row-fluid">
                <div class="control-group span9">
                  <label for="paymentType" class="control-label">部门</label>
                  <label style="float:left; color:#999; padding:5px 10px 0 15px;">${orgName}${deptName}</label>
                  <label style="float:left; padding:5px 10px 0 30px;">业务员</label>
                  <label style="float:left; color:#999; padding:5px 10px 0 15px;">${userName}</label>
                  <label style="float:left; padding:5px 10px 0 30px;">签订时间</label>
                  <label style="float:left; color:#999; padding:5px 10px 0 15px;">${zhContract.create?string("yyyy-MM-dd")}</label>
                </div>
              </div>
            <div class="row-fluid">
              <label for="paymentType" class="control-label">合同状态</label>
              <label style="float:left; color:#999; padding:5px 10px 0 15px;"><#if zhContract.con_state==3>
              	已归档
              	<#elseif zhContract.con_state==4>
              	已驳回
              	<#elseif zhContract.conState==5>
              	未提交
              	<#else>
              	审核中
              </#if></label>
              <label style="float:left; padding:5px 10px 0 30px;">提前刊登申请</label>
              <label style="float:left; color:#999; padding:5px 10px 0 15px;"><#if !(aheadAdv?exists)>
              	未填写
              	<#elseif aheadAdv.adv_state==4>
              	已驳回
              	<#elseif aheadAdv.adv_state==3>
              	已通过
              	<#else>
              	审核中
              </#if></label>
            </div>
          </form>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>媒体</th>
                  <th>位置</th>
                  <th>规格</th>
                  <th>品牌</th>
                  <th>投放日期</th>
                </tr>
              </thead>
              <tbody id="tbody">
              <#if (zhContract.tbConZhSubs?size>0)>
              	<#list zhContract.tbConZhSubs as list>
                <tr>
                  <td>${list.advschedule.tbConAdv.chName}</td>
                  <td>${list.advschedule.tbConAdv.adposition}</td>
                  <td>${list.advschedule.tbConAdv.standard}</td>
                  <td>${list.brand}</td>
                  <td>${list.advschedule.schedule_date}</td>
                </tr>
                </#list>
                </#if>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

<@c.js/>
    <script type="text/javascript">
    $(document).ready(function(){
    	var trs = $("#tbody tr");
    	$(trs).each(function(){
    		var td = $(this).children("td:last");
    		var dates = $(td).html().split(',');
    		$(td).html("");
    		for(var i=0;i<dates.length;i++){
    			if(i%4==0 && i!=0){
    			$(td).append("<span style='color:gray;margin-left:18px'>"+dates[i]+"</span></br>");
    			}else{
    				$(td).append("<span style='color:gray;margin-left:18px'>"+dates[i]+"</span>");
    			}
    		    
    		}
    	})
    	
    });
   function findZh(){
   		var conId = $("#conId").val();
   		if(conId==""){
			alert("请输入广告合同编号");
			return ;
		}
   		location.href="/contract/advSchedule/findConZhCheck?conId="+conId;
   }
      $(".form_datetime").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒 
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 
        language: 'zh-CN', //汉化 
        autoclose:true //选择日期后自动关闭
      });
    </script>
  </body>
</html>