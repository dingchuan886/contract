<!DOCTYPE html>
<html>
  <head>
    <title>添加新合同-众智车展合同</title>
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
    <form action="${contract_ct_url}contract/tbConRebate/saveRebate" method="POST" id="rebateForm">
      <div class="v-h" style="text-align:center"><input id="edit" name="" type="button" value="修改" onClick=""><input name="" type="button" value="打印" onClick="toPrint();"><input name="" type="button" data-toggle="modal" href="#myModal" value="提交" onclick="edit.disabled='false';"/>
      <p style="text-align:center;color:red">注意：提交返利申请之后流程改到财务系统，查看流程及其他信息请到财务系统查看</p>
      </div>
      <div class="m2">
        <h2>上海众智电子商务有限公司</h2>
        <h3>业务返利申请单</h3>
        <div class="m3">
          	<input type="hidden" name="con_id" value="${conZh.con_zh_id}" >
          	<input type="hidden" name="user_id" value="${conZh.user_id}" >
        	<input type="hidden" name="cus_name" value="${conZh.cus_name}" >
        	<input type="hidden" name="applyDate" value="${applyDate?string("yyyy-MM-dd")}" >
        	<input type="hidden" name="al_back" value="${conZh.al_rebate}">
          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb7">
            <tbody>
              <tr>
                <td>业务员：${conZh.user_name}</td>
                <td>申请日期：${applyDate?string("yyyy-MM-dd")}</td>
              </tr>
              <tr>
                <td>客户名称：${conZh.cus_name}</td>
                <td>销售合同号/销售订单号：${conZh.con_zh_id}</td>
              </tr>
              <tr>
              	
                	<td colspan="2">
                	<div class="m-sqd">
                		<ul>
		                	<li><span>返利内容明细金额：</span>${conZh.al_rebate}</li>
                		</ul>
	              	</div>
	              	</td>
              </tr>
              <tr>
                <td colspan="2">
                  <div class="m-sqd">
                    <ul>
                      <li><span>合同总金额：</span>${conZh.con_total_price}</li>
                      <li><span>其中高开：</span><input type="text" id="" name="con_high" placeholder="其中高开" class="p"></li>
                    </ul>
                    <ul>
                      <li><span>已返利金额：</span>${conZh.al_rebate}</li>
                    </ul>
                    <ul>
                      <li><span>本次返利金额：</span><input type="text" id="" name="this_back" placeholder="本次返利金额" onkeyup="backAcountTrans(this, 'smallAccount', 'bigAccount');" class="p"></li>
                    </ul>
                    <ul>
                      <li><span>扣除开票税点：</span><input type="text" id="" name="deduct" placeholder="扣除开票税点" class="p"></li>
                      <li><span>返还：</span></span><input type="text" id="" name="back" placeholder="返还" class="p"></li>
                    </ul>
                    <ul>
                      <li><span>银行账号：</span><input type="text" id="acount" name="acount" placeholder="返还" class="p"></li>
                      <li><span>开户银行</span><input type="text" id="bank_name" name="bank_name" class="p"></li>
                      <li><span>收款人：</span><input type="text" id="payee_name" name="payee_name" class="p"></li>
                    </ul>
                  </div>
                </td>
              </tr>
              <tr>
                <td>返利金额（小写）：¥   <span id="smallAccount"></span></td>
                <td>（大写）：人民币 	<span id="bigAccount"></span></td>
              </tr>
             </tbody>
          </table>
        </div>

      </div>
      <div class="m4">
        <ul>
          <#--li>财务核实回款：</li>-->
          <li>主管签字：</li>
          <li>流程部备案签字：</li>
          <li>总经理签字：</li>
        </ul>
      </div>
    </form>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <h3>确定申请返利?</h3>
      <div class="modal-footer" style="text-align:center; margin-top:10px">
        <button class="btn btn-info" data-dismiss="modal" onclick="submitRebate('rebateForm');">确定申请</button>
        <button class="btn" data-dismiss="modal"  onclick="">暂不填写</button>
      </div>
    </div>
   </div>
</div>
</div>
</div>
    <!-- Js -->
    <script type="text/javascript">
    
    	function submitFrom(){
		    var numreg = /^\d+(\.\d{2})?$/;
		    var countreg = /^\d+$/;
	        var con_high = $("input[name='con_high']").val();//其中高开
	        var this_back = $("input[name='this_back']").val();//本次返利金额
	        var deduct = $("input[name='deduct']").val();//扣除税点
	        var back = $("input[name='back']").val();//返还
	        var bank_name = $("#bank_name").val();
	        var payname = $("#payee_name").val();
	        var acount = $("#acount").val();
	        if(bank_name==""){
	        	alert("请输入银行地址");
	        	return false;
	        }
	        if(payname==""){
	        	alert("请输入收款人姓名");
	        	return false;
	        }
	        if(acount==""){
	        	alert("请输入银行账号")
	        	return false;
	        }
	        if(con_high==''||con_high==null){
	        	$("input[name='con_high']").val(0);
	        }else{
	        	if(!countreg.test(con_high)){
		        	alert("【其中高开】请输入数字");
		        	return false;
	        	}
	        }
	        if(deduct==''||deduct==null){
	        	$("input[name='deduct']").val(0);
	        }else{
	        	if(deduct.length>2||!countreg.test(deduct)){
	        		alert("【扣除税点】请输入数字,且必须为两位数");
	        		return false;
	        	}
	        }
	        if(back==''|| back==null){
				$("input[name='back']").val(0);
	        }else{
	        	if(!countreg.test(back)){
		        	alert("【返还】请输入数字");
		        	return false;
	        	}
	        }
	       	if(this_back==''||this_back==null||!countreg.test(this_back)){
       			alert("【本次返利金额】请输入数字");
       			return false;
	       	}
	        return true;
      	}
    
    
    	function submitRebate(formId){
    		var flag = this.submitFrom();
    		if(flag){
    			$("#"+formId).submit();
    		}
    	}
    
    	//数字 转成 中文大写
    	function backAcountTrans(input, smallAccount, bigAccount){
    		var number = $(input).val();
    		var chineseNumber = this.numberToChinese(number, input);
    		if(chineseNumber==""){
    			number = chineseNumber;
    		}
    		$("#"+smallAccount).html(number);
    		$("#"+bigAccount).html(chineseNumber);
    	}
    	function numberToChinese(num, input) { 
			if (!/^\d*(\.\d*)?$/.test(num)) { 
				alert("Number is wrong!"); 
				$(input).val("");
				return ""; 
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
    <script src="${contract_ct_url_html}js/jquery.min.js"></script>
    <script src="${contract_ct_url_html}js/bootstrap.min.js"></script>
  </body>
</html>