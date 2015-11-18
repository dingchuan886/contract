<!DOCTYPE html>
<html>
  <head>
    <title>返利申请-车团返利申请显示</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${contract_ct_url_html}css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${contract_ct_url_html}css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="${contract_ct_url_html}css/print.css" rel="stylesheet" type="text/css">
    <style type="text/css" media="print">
    .v-h {display:none;}
    </style>
  </head>
  <body>
    <div class="container">
  <div class="row-fluid">
  <div class="span12">
  <div class="well">
    <form action="#">
      <div class="v-h" style="text-align:left">
      	<input name="" type="button" value="打印" onClick="toPrint();">
      	<#--
      	<input id="edit" name="" type="button" value="修改" onClick="">
      	<input name="" type="button" data-toggle="modal" href="#myModal" value="提交" onclick="edit.disabled='false';"/>
      	-->
      </div>
      <div class="m2">
        <h2>上海车团网络信息技术有限公司</h2>
        <h3>业务返利申请单</h3>
        <div class="m3">
          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb7">
            <tbody>
              <tr>
                <td>业务员：${map.user.user_name}</td>
                <td>申请日期：${map.rebate.create?string("yyyy-MM-dd")}</td>
              </tr>
              <tr>
                <td>客户名称：${map.conCt.cus_name}</td>
                <td>销售合同号/销售订单号：${map.conCt.con_ct_id}</td>
              </tr>
              <tr>
                <td colspan="2">返利具体内容和明细金额：${map.rebate.back_des}</td>
              </tr>
              <tr>
                <td colspan="2">
                  <div class="m-sqd">
                    <ul>
                      <li><span>合同总金额：</span><p>${map.conCt.con_total_price}</p></li>
                      <li><span>其中高开：</span><p>${map.rebate.con_high}</p></li>
                    </ul>
                    <ul>
                      <li><span>已返利金额：</span><p>${map.rebate.al_back}</p></li>
                    </ul>
                    <ul>
                      <li><span>本次返利金额：</span><p>${map.rebate.this_back}</p></li>
                    </ul>
                    <ul>
                      <li><span>扣除开票税点：</span><p>${map.rebate.deduct}</p></li>
                      <li><span>返还：</span><p>${map.rebate.back}</p></li>
                    </ul>
                    <ul>
                      <li><span>本次实际返利金额：</span><p>${map.rebate.back_actual}</p></li>
                    </ul>
                  </div>
                </td>
              </tr>
              <tr>
                <td>返利金额（小写）：¥ <span>${map.rebate.this_back}</span>元</td>
                <td>（大写）：人民币<span id="bigAccount"></span>元 </td>
              </tr>
             </tbody>
          </table>
        </div>

      </div>
      <div class="m4">
        <ul>
          <#--li>财务核实回款：</li>-->
          <li><span>主管签字：</span>
          <p>
          <#if manager_writeUrl!=''>
           <image src="${manager_writeUrl}" width="80px" height="60px"/>
          </#if>
          </li>
          </p>
          <li><span>区域经理签字：</span>
          <p>
          <#if manager_writeUrl!=''>
           <image src="${area_writerUrl}" width="80px" height="60px"/>
          </#if>
          </li>
          </p>
          <li><span>流程部备案签字：</span>
          <p>
          <#if flow_writeUrl!=''>
           <image src="${flow_writeUrl}" width="80px" height="60px"/>
          </#if>
          </li>
          </p>
          <li><span>总经理签字：</span>
          <p>
          <#if flow_writeUrl!=''>
           <image src="${hqManager_writeUrl}" width="80px" height="60px"/>
          </#if>
          </p>
          </li>
        </ul>
      </div>
    </form>
   </div>
</div>
</div>
</div>
  </body>
  <!-- Js -->
    <script src="${contract_ct_url_html}js/jquery.min.js"></script>
    <script src="${contract_ct_url_html}js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		var chineseNum = numberToChinese(${map.rebate.this_back});
    		$("#bigAccount").html(chineseNum);
		});
		function numberToChinese(num) { 
			if (!/^\d*(\.\d*)?$/.test(num)) { 
				alert("Number is wrong!"); 
				//$(input).val("");
				return "Number is wrong!"; 
			} 
			var AA = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); 
			var BB = new Array("", "拾", "佰", "仟", "萬", "億", "点", ""); 
			var a = ("" + num).replace(/(^0*)/g, "").split("."), k = 0, re = ""; 
			for (var i = a[0].length - 1; i >= 0; i--) { 
				switch (k) { 
					case 0: 
						re = BB[7] + re; 
						break; 
					case 4: 
						if (!new RegExp("0{4}\\d{" + (a[0].length - i - 1) + "}$").test(a[0])){ 
							re = BB[4] + re; 
						}
						break; 
					case 8: 
						re = BB[5] + re; 
						BB[7] = BB[5]; 
						k = 0; 
						break; 
				} 
				if (k % 4 == 2 && a[0].charAt(i + 2) != 0 && a[0].charAt(i + 1) == 0) {
					re = AA[0] + re; 
				}
				if (a[0].charAt(i) != 0){ 
					re = AA[a[0].charAt(i)] + BB[k % 4] + re; 
				}	
				k++; 
			} 
			//加上小数部分(如果有小数部分)
			if (a.length > 1) { 
				re += BB[6]; 
				for (var i = 0; i < a[1].length; i++) {
					re += AA[a[1].charAt(i)]; 
				}
			} 
			return re; 
		} 	
		        function toPrint(){
	$(".form1").removeClass("form1");
	window.print();
	$(".form1").addClass("form1");
}
    </script>
  
</html>