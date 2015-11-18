<#import "common_con.ftl" as c/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>投放广告</title>
<link href="${contract_ct_url_html}css/ss.css" rel="stylesheet" type="text/css">
<link href="${contract_ct_url_html}css/bootstrap.min.css" rel="stylesheet" media="screen"> 
    <link href="${contract_ct_url_html}css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="${contract_ct_url_html}css/ss.css" rel="stylesheet" type="text/css">

<link href="${contract_ct_url_html}css/styles1.css" rel="stylesheet" type="text/css">
<@c.js/>
<script type="text/javascript" async="" charset="utf-8" src="http://c.cnzz.com/core.php?web_id=30001831&amp;t=q"></script>
<script type="text/javascript" src="${contract_ct_url_html}js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${contract_ct_url_html}js/ss.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
	$("#channel").change(function(){
		$("#provinceDiv").hide();
		$("#province").html("<option value='0'>请选择</option>");
		$("#city").html("<option value='0'>请选择</option>");
		$("#adv").html("<option value='0'>请选择</option>");
		$("#cityDiv").hide();
		var chId = $(this).val();
		if(chId==11){
			$("#provinceDiv").show();
			$.get("/contract/advSchedule/findProvince",{"chId":chId},function(data){

			var suc = eval("("+data+")");
			for(var i=0;i<suc.length;i++){
				$("#province").append("<option value='"+suc[i].pid+"'>"+suc[i].pname+"</option>");
			}
				
	
			});
		}
		if(chId==12){
			$("#provinceDiv").show();
			$("#cityDiv").show();
			$.get("/contract/advSchedule/findProvince",{"chId":chId},function(data){

			var suc = eval("("+data+")");
			for(var i=0;i<suc.length;i++){
				$("#province").append("<option value='"+suc[i].pid+"'>"+suc[i].pname+"</option>");
			}
				
	
			});
		}
		//只根据频道查找广告
		$.get("/contract/advSchedule/findAdvByChannel",{"chId":chId},function(data){
			var suc = eval("("+data+")");
			for(var i=0;i<suc.length;i++){
				$("#adv").append("<option value='"+suc[i].adid+"'>"+suc[i].adname+"</option>")
			}
		});
	});
	//根据省站找广告
	$("#province").change(function() {
		var chId = $("#channel").val();
		$("#city").html("<option value='0'>请选择</option>");
		$("#adv").html("<option value='0'>请选择</option>");
		var pId = $(this).val();
		if(chId==12){
		$.get("/contract/advSchedule/findCity", {"pId": pId}, function(data) {
			var suc = eval("(" + data + ")");
			for (var i = 0; i < suc.length; i++) {
				$("#city").append("<option value='" + suc[i].cid + "'>" + suc[i].cname + "</option>");
			}
	
		});
		}
		$.get("/contract/advSchedule/findAdvByProvince",{"chId":chId,"pId":pId},function(data){
	
			var suc = eval("("+data+")");
			for (var i = 0; i < suc.length; i++) {
				$("#adv").append("<option value='" + suc[i].adid + "'>" + suc[i].adname + "</option>");
			}
		});
	});
	
	//根据市站查找
	$("#city").change(function() {
		var chId = $("#channel").val();
		$("#adv").html("<option value='0'>请选择</option>");
		var pId = $("#province").val();
		var cId = $(this).val();
		if(chId==12){
			$.get("/contract/advSchedule/findAdvByCity",{"chId":chId,"pId":pId,"cId":cId},function(data){
				var suc = eval("("+data+")");
				for (var i = 0; i < suc.length; i++) {
					$("#adv").append("<option value='" + suc[i].adid + "'>" + suc[i].adname + "</option>");
				}
			});
		}
		
	});
	
});
	function findZh(){
		var conId = $("#conId").val();
		if(conId==""){
			alert("请输入广告合同编号");
			return ;
		}
		location.href="/contract/advSchedule/findZhCon?conId="+conId;
	}
function addSchedule(date){
	var dat = $(date).parent("td").siblings("td:last");
	dat.html("");
	var did = dat.attr("id");
	$("#showId").val(did);
	$("#divdwdate").show();
}

function fillDate(){
    var did = $("#showId").val();
	var span = $("#debug span");
	var dat = "";
	$(span).each(function(){
		dat += $(this).html();
	});
	$("#"+did).html(dat);
	$("#divdwdate").hide();
}

function cancel(){
	$("#delealldatedw").click();
	$("#divdwdate").hide();
}

function subSchedule(){
	var adid = $("#adv").val();
	var size = "${zhContract.tbConZhSubs?size}";
	var index = 0;
	if(size>0){
		<#list zhContract.tbConZhSubs as list>
		var td = $("#"+${list_index}).html();
			if(td!=""){
				var subId1 = "${list.con_zh_sub_id}";
				var conZh = $("<input type='hidden' name='sches["+index+"].con_zh_id' value='${zhContract.con_zh_id}'/>");
				var adv = $("<input type='hidden' name='sches["+index+"].adid' value='"+adid+"'/>");
				var scheDate = $("<input type='hidden' name='sches["+index+"].schedule_date' value='"+td+"'/>");
				var subId = $("<input type='hidden' name='sches["+index+"].con_zh_sub_id' value='"+subId1+"'/>");
				$("#form1").append(conZh);
				$("#form1").append(adv);
				$("#form1").append(scheDate);
				$("#form1").append(subId);
				index += 1;
			}
		</#list>
	}
	$("#form1").submit();

}
</script>
</head>
<body>

     <@c.head/>
	 <@c.left act=81/>
        <div class="span10">
          <ul class="breadcrumb">
            <li><a href="/contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">添加广告排期</li/>
          </ul>
          <form action="/contract/advSchedule/saveSchedule" method="post" id="form1" class="form-horizontal">
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
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">客户名</label>
                <div class="controls">
                  <label style="float:left; color:#999; padding:5px 10px 0 15px;" id="cus_name">
                  	<input type="text" readonly="true" value="${zhContract.cus_name}">
                  </label>
                </div>
              </div>
            </div>
            <ul class="nav">
              <button class="btn btn-info" type="button" style="margin-left:180px" onclick="findZh()">查询</button>
            </ul>
            <div class="row-fluid">
              <div class="control-group span2">
                <label for="evidenceCode" style="width:50px; padding-top: 5px;float:left;">频道</label>
                <div class="controls" style="float:left; margin-left:0px">
                  <select name="chId" id="channel" style="width:150px">
                  	<option value="0">请选择</option>
                    <option value="1">首页</option>
                    <option value="2">资讯频道</option>
                    <option value="3">评测频道</option>
                    <option value="4">汽车报价频道</option>
                    <option value="5">汽车图库频道</option>
                    <option value="6">车系车型页</option>
                    <option value="7">资讯内容页</option>
                    <option value="8">新车频道</option>
                    <option value="9">导购频道</option>
                    <option value="10">行情频道</option>
                    <option value="11">省区站首页</option>
                    <option value="12">城市站首页</option>
                  </select>
                </div>
              </div>
              <div class="control-group span2" style="display:none" id="provinceDiv">
                <label for="evidenceCode" style="width:50px; padding-top: 5px;float:left;">省份</label>
                <div class="controls" style="float:left; margin-left:0px">
                  <select name="pId" id="province" style="width:150px">
                    <option value="0">请选择<option>
                  </select>
                </div>
              </div>
              <div class="control-group span2" id="cityDiv" style="display:none">
                <label for="evidenceCode" style="width:50px; padding-top: 5px;float:left;">市区</label>
                <div class="controls" style="float:left; margin-left:0px">
                  <select name="cId" id="city" style="width:150px;">
                    <option value="0">请选择</option>
                  </select>
                </div>
              </div>
               <div class="control-group span2" id="advDiv">
                <label for="evidenceCode" style="width:50px; padding-top: 5px;float:left;">广告位</label>
                <div class="controls" style="float:left; margin-left:0px">
                  <select id="adv" style="width:150px;">
                    <option value="0">请选择</option>
                  </select>
                </div>
              </div>
            </div>
          </form>
          <div style="color:red">tips:添加广告时请一条条的选择。将除需要提交广告之外的排期清空，系统只会提交填写过排期的格子。表格为空并不会清除排期</div>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>媒体</th>
                  <th>位置</th>
                  <th>规格</th>
                  <th>品牌</th>
                  <th>投放日期</th>
                  <th>添加排期</th>
                </tr>
              </thead>
              <tbody>
                <#if (zhContract.tbConZhSubs?size>0)>
                	<#list zhContract.tbConZhSubs as list>
                	<tr>
                		<td>
                		<#if list.media==1>
                			315che.com
                		 <#elseif list.media==2>
                			框架
                		 <#elseif list.media==3>
                			其它
                		 </#if>
                		</td>
                		<td>${list.position}</td>
                		<td>${list.standard}</td>
                		<td>${list.brand}</td>
                		<td id="${list_index}">${list.advschedule.schedule_date}</td>
                		<td><a href="javascript:void(0)" onclick="addSchedule(this)">添加排期<a></td>
                	</tr>
                	</#list>
                </#if>
              </tbody>
            </table>
           <button class="btn btn-info" type="button" onclick="subSchedule()">提交</button>
          </div>
        </div>
      </div>
    </div>
<div id="divdwdate" align="center" style="display:none">
				     
										      <div  style="height:226px; overflow:hidden;float:left;margin-left:600px">
											        <div id="calendarTdatedw"><table class="data_top"><tbody><tr><td id="prevYearF3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" class="prevYearActive" title="前一年">&nbsp;</td><td id="prevMonthF3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" class="prevMonthActive" title="前一月">&nbsp;</td><td class="currentDate">五月  2015</td><td id="nextMonthF3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" class="nextMonthActive" title="后一月">&nbsp;</td><td id="nextYearF3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" class="nextYearActive" title="后一年">&nbsp;</td></tr></tbody></table><div id="contentdingF3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" class="contentding"><table><tbody><tr class="weekdays"><td> 日</td><td> 一</td><td> 二</td><td> 三</td><td> 四</td><td> 五</td><td> 六</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="isbeforeDayClass" id="20150501F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">1</td><td class="isbeforeDayClass" id="20150502F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">2</td></tr><tr><td class="isbeforeDayClass" id="20150503F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">3</td><td class="isbeforeDayClass" id="20150504F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">4</td><td class="isbeforeDayClass" id="20150505F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">5</td><td class="isbeforeDayClass" id="20150506F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">6</td><td class="isbeforeDayClass" id="20150507F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">7</td><td class="isbeforeDayClass" id="20150508F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">8</td><td class="isbeforeDayClass" id="20150509F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">9</td></tr><tr><td class="isbeforeDayClass" id="20150510F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">10</td><td class="isToday" id="20150511F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">11</td><td class="isSelected" id="20150512F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" style="background-color: rgb(255, 255, 255);">12</td><td class="isDate" id="20150513F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" style="background-color: rgb(255, 255, 255);">13</td><td class="isDate" id="20150514F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" style="background-color: rgb(255, 255, 255);">14</td><td class="isDate" id="20150515F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" style="background-color: rgb(255, 255, 255);">15</td><td class="isDate" id="20150516F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">16</td></tr><tr><td class="isDate" id="20150517F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">17</td><td class="isDate" id="20150518F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">18</td><td class="isDate" id="20150519F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" style="background-color: rgb(255, 255, 255);">19</td><td class="isSelected" id="20150520F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" style="background-color: rgb(255, 255, 255);">20</td><td class="isDate" id="20150521F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" style="background-color: rgb(255, 255, 255);">21</td><td class="isDate" id="20150522F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">22</td><td class="isDate" id="20150523F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">23</td></tr><tr><td class="isDate" id="20150524F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">24</td><td class="isDate" id="20150525F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">25</td><td class="isDate" id="20150526F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">26</td><td class="isDate" id="20150527F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa" style="background-color: rgb(255, 255, 255);">27</td><td class="isDate" id="20150528F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">28</td><td class="isDate" id="20150529F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">29</td><td class="isDate" id="20150530F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">30</td></tr><tr><td class="isDate" id="20150531F3yYQq8a6EOMAAyPG6kJRuABo8S6zEUa">31</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table></div></div>
                                                   
                                                 <script>
													var ecT = new calendar_dd();
													ecT.properties.holder='calendarTdatedw';
													ecT.properties.callback = function(cal){
													var j=0;
													var getdate;
													var getmonth;
													var datearraycome= new Array();
													for(var i in cal.cache.selectedDates){
														if(cal.cache.selectedDates[i] == true){
															
															var selectedDate = ecT.stringToDate(i);
														
															getdate=selectedDate.getDate();
															
															if(selectedDate.getDate()<10) 
																{
																	getdate='0'+selectedDate.getDate();
																}
															getmonth=parseInt(selectedDate.getMonth()+1);
															if(getmonth<10)
																{
																	getmonth='0'+parseInt(selectedDate.getMonth()+1);
																}
																j++;
														}
														datearraycome.push(selectedDate.getFullYear()+'-'+getmonth+'-'+getdate);
														
													}
													$('#datedwnumall').html(j);
													
													if(datearraycome.length<datedb2.length)
														{
															
															
															deletedatecheck(removeSamedate(datearraycome,datedb2));
															datedb2=datearraycome;
														}
													else
														{
															datecheck(removeSamedate(datedb2,datearraycome));
															datedb2=datearraycome;
														}
														};
													ecT.init(null,null,'calendarTdatedw');
													$("#delealldatedw").live("click",function(){
											            var flag = confirm("确定清除日期吗？");
											          if(flag){
											            for(var i in ecT.cache.selectedDates){

											            	if(ecT.cache.selectedDates[i]==true){ecT.deleteSelect($clock(i+randid),i)}
											            }
											        	datedb=[];datedb2=[];$('#debug span').remove();$('#datedwnumall').html(0);$('#datedengyu').val('');
													}else{
															return;
													}
													})
												</script>
										      </div>
											                     
											  <div style="height:226px;width:400px;overflow:auto;float:left;">
											        <div class="data_del">
													      <span class="left"><b>已添加日期</b></span>
														  <span class="right blue"><a href="javascript:" id="delealldatedw">全部删除</a>　共<font id="datedwnumall">0</font>天</span>
													</div>
											        <div class="r_b blue" style="height:200px;width:300px">
													    <div class="data_list" id="debug"></div>
													</div>
											  </div>
											  <input type="hidden" id="showId">
									<div style="height:226px"></div>
										  <div style="margin-right:580px">　
										<input type="button" value="完成" style="cursor:pointer;width:60px;" onclick="fillDate()">
										<input type="button" value="取消" style="cursor:pointer;width:60px;" onclick="cancel()">
										 </div>	
                   
</div>
</body>
</html>
