<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
  <head>
    <title>查看广告排期</title>
  <@c.html/>
  <link href="${contract_ct_url_html}/css/ypick.css" rel="stylesheet" type="text/css">
  </head>
  <body>
     <@c.head/>
	 <@c.left act=80/>
        <div class="span10">
          <ul class="breadcrumb">
            <li><a href="/contract/toIndex">合同管理</a> <span class="divider">/</span></li>
            <li class="active">查看广告排期</li>
          </ul>
          <form action="#" class="form-horizontal">
            <div class="row-fluid">
              <div class="control-group span2">
                <label for="evidenceCode" style="width:50px; padding-top: 5px;float:left;">媒介</label>
                <div class="controls" style="float:left; margin-left:0px">
                  <select name="" id="" style="width:150px">
                    <option value="">网页端</option>
                  </select>
                </div>
              </div>
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
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                
              </div>
            </div>
          </form>
          <div hidden="true" id="beforeSend"><h4>正在查询...</h4></div>
<div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">

      <div style="height:600px;">
       <div class="col_r">
				 <div class="top_title">
					  <div class="tab_c">
					       <ul>

						        <li class="f12_b ">广告位排期</li>
						        <li class="select_t center">
								  <select name="selectyear" id="selectyear" style="width:90px;">

							      </select>
								  年
								</li>
								<li class="select_t center">
								  <select name="selectmonth" style="width:60px;" id="selectmonth">
										<option value="01">1</option>
                                        <option value="02">2</option>
                                        <option value="03">3</option>
                                        <option value="04">4</option>
                                        <option value="05">5</option>
                                        <option value="06">6</option>
                                        <option value="07">7</option>
                                        <option value="08">8</option>
                                        <option value="09">9</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                  </select>
								  月
								</li>

						   </ul>
				      </div>

				 <div style="over-flow:auto">
				 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_b blue" id="slot_paiqitable">
                  <tbody id="tbody">
                   <tr class="border_b_b" id="headTr">
                   <td class="border_n_l"><div>频道</div></td>
                   <td class="border_n_l"><div>广告位置</div></td>
                   <td class="border_n_l"><div>广告名称</div></td>
                    <td class="border_n_l"><div>广告规格</div></td>
                   </tr>

                   </tbody></table>
	         		</div>
		  		</div>
		   </div>
		   <div class="clear"></div>
	  </div>
</div>

          </div>
        </div>

    </div>

<@c.js/>
    <script type="text/javascript">

    $(document).ready(function(){

    var nowDate = new Date();
$("#selectyear").append($("<option selected='selected' value='"+nowDate.getFullYear()+"'>"+nowDate.getFullYear()+"</option><option value='"+(nowDate.getFullYear()+1)+"'>"+(nowDate.getFullYear()+1)+"</option>"));
$("#selectmonth option").each(function(){
	if($(this).val()==(nowDate.getMonth()+1)){
		$(this).attr("selected","selected");
	}
});

var year = $("#selectyear").val();
	var month = $("#selectmonth").val();
	$.ajax({
		url:"/contract/advSchedule/initTable",
		data:{
			"year":year,
			"month":month
		},
		success:function(succe){
			var suc = eval("("+succe+")");
			$("#headTr").append(suc.days);
		}
	});
	$("#channel").change(function(){
	    var year = $("#selectyear").val();
	    var month = $("#selectmonth").val();
		$("#provinceDiv").hide();
		$("#province").html("<option value='0'>请选择</option>");
		$("#city").html("<option value='0'>请选择</option>");
		$("#cityDiv").hide();
		var chId = $(this).val();
		if(chId==11){
			$("#provinceDiv").show();
		}
		if(chId==12){
			$("#provinceDiv").show();
			$("#cityDiv").show();
		}
		$.get("/contract/advSchedule/findProvince",{"chId":chId},function(data){

			var suc = eval("("+data+")");
			for(var i=0;i<suc.length;i++){
				$("#province").append("<option value='"+suc[i].pid+"'>"+suc[i].pname+"</option>");
			}


			});

		getDayAnddates(chId,0,0,year,month);
		});

		$("#province").change(function(){
			var chId = $("#channel").val();
			 var year = $("#selectyear").val();
	   		 var month = $("#selectmonth").val();
			$("#city").html("<option value='0'>请选择</option>");
			var pId = $(this).val();
			$.get("/contract/advSchedule/findCity",{"pId":pId},function(data){
			var suc = eval("("+data+")");
			for(var i=0;i<suc.length;i++){
				$("#city").append("<option value='"+suc[i].cid+"'>"+suc[i].cname+"</option>");
			}

		});

		getDayAnddates(chId,pId,0,year,month);
		});

		$("#city").change(function(){
			var chId = $("#channel").val();
			 var year = $("#selectyear").val();
	   		 var month = $("#selectmonth").val();
	   		 var pId = $("#province").val();
	   		 var cId = $(this).val();
	   		 getDayAnddates(chId,pId,cId,year,month);
		})
	});
	$("#selectyear").change(function(){
		var chId = $("#channel").val();
			 var year = $("#selectyear").val();
	   		 var month = $("#selectmonth").val();
	   		 var pId = $("#province").val();
	   		 var cId = $("#city").val();
	   		 getDayAnddates(chId,pId,cId,year,month);
	});

		$("#selectmonth").change(function(){
		var chId = $("#channel").val();
			 var year = $("#selectyear").val();
	   		 var month = $("#selectmonth").val();
	   		 var pId = $("#province").val();
	   		 var cId = $("#city").val();
	   		 getDayAnddates(chId,pId,cId,year,month);
	});

	function getDayAnddates(chId,pId,cId,year,month){

    $("#tbody").html("");
    	$("#tbody").html("<tr class='border_b_b' id='headTr'><td class='border_n_l'><div>频道</div></td><td class='border_n_l'><div>广告位置</div></td><td class='border_n_l'><div>广告名称</div></td><td class='border_n_l'><div>广告规格</div></td></tr>");

	$.ajax({
		url:"/contract/advSchedule/initTable",
		beforeSend:function(){
			$("#beforeSend").show();
		},
		data:{
			'year':year,
			'month':month,
			'chId':chId,
			'pId':pId,
			'cId':cId
		},
		success:function(succe){
			$("#beforeSend").hide();
			var suc = eval("("+succe+")");
			$("#headTr").append(suc.days);
			$("#tbody").append(suc.datas);
		}
	});
}

    </script>
  </body>
</html>