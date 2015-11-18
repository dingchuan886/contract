<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${contract_ct_url_html}css/bootstrap.min.css" rel="stylesheet" media="screen"> 
    <link href="${contract_ct_url_html}css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="${contract_ct_url_html}css/print.css" rel="stylesheet" type="text/css">
<title>车团开票申请单</title>
</head>
<body>
  <div class="container">
  <div class="row-fluid">
  <div class="span12">
  <div class="well">
   <form action="${contract_ct_url}contract/tbConBill/addTbConBill" id="addForm" method="post" return="checkForm()">
      <div class="v-h" style="text-align:center"><input id="edit" name="" type="button" value="修改" onClick=""><input name="" type="button" value="打印" onClick="toPrint();"><input name="" type="button" data-toggle="modal"  value="提交" onClick="javascript:submitFrom()"/>
      	<p style="color:red;font-size:12px">开票前请确保填写好合同补充，如有特殊情况请先联系审核人员，以免耽误时间</p>
      </div>
      <div class="m2">
        <h2>上海车团网络信息技术有限公司</h2>
        <h3>开票申请单</h3>
        <div class="m5">
          <ul>
            <li style="float:right; text-align:right"><span>站点：</span><p align="center">${org.org_name}</p></li>
            <li>申请开票日期：20${dateYear}年${dateMonth}月${dateDay}日</li>
          </ul>
        </div>
        <div class="m3">
          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb7">
            <tbody>
              <tr>
                <input type="hidden" name="cus_name" value="${tbConct.cus_name}"/>
                <input type="hidden" name="con_id" value="${tbConct.con_ct_id}"/>
                <td colspan="2">客户名称：${tbConct.cus_name}</td>
                <td colspan="3">销售合同号/销售订单号：${tbConct.con_ct_id}</td>
              </tr>
              <tr>
                <td colspan="2">合同总价：${tbConct.total_price}&nbsp;元</td>
                <td colspan="2">已开票金额：${tbConct.al_bill}&nbsp;元</td>
                 <input type="hidden" name="al_bill" value="${tbConct.al_bill}">
                <td>申请开票金额：  <input type="text" id="" placeholder="请填金额"  name="sal_bill" value="0">&nbsp;元</td>
              </tr>
              <tr>               
                <td colspan="5">
                  <div class="m6">
                    <ul>
                      <li style="padding-right:60px">发票金额：</li>
                      <li><label class="radio"><input type="radio" name ="state" value="0" onclick="javascript:isDis()" checked=checked >平开</label></li>
                      <li style="width:50%"><label class="radio"><input type="radio" name ="state" value="1" onclick="javascript:isDis()" id="high" >高开（金额： <input type="text" style="width:100px;height:20px;display:none" id="account"  placeholder="请填金额" value="0" name="bill_high">&nbsp;元）</label></li>
                    </ul>
                  </div>
                </td>
              </tr>
              <tr>
                <td>发票类型</td>
                <td><label class="radio"><input type="radio" name="bill_type" value="0" checked=checked >广告费</label></td>
                <td><label class="radio"><input type="radio" name="bill_type" value="1">广告发布费</label></td>
                <td><label class="radio"><input type="radio" name="bill_type" value="2">活动服务费</label></td>
                <td><label class="radio"><input type="radio" name="bill_type" value="3">其他</label></td>
              </tr>
              <tr>
                 <td colspan="5" height="40">备注：<textarea name="bill_content" style="width:1100px"></textarea></td>
              </tr>
             </tbody>
          </table>
        </div>
        <div class="m3" style="padding-top:20px">
          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb3">
            <tbody>
              <tr>
                <td>
                  <div class="control-group span5" style="width:300px">
                    <label for="type" class="kp-label">开票税号</label>
                    <div class=" kp-controls">
                      <input type="text" id="" placeholder="" style="height:30px" name="duty_para">
                    </div>
                  </div>
                </td>
                <td colspan="4"></td>
              </tr>
              <tr>
                <td>
                  <div class="control-group span5" style="width:300px">
                    <label for="type" class="kp-label">开户银行</label>
                    <div class=" kp-controls">
                      <input type="text" id="" placeholder="" style="height:30px"  name="bank_name">
                    </div>
                  </div>
                </td>
                <td colspan="4">
                  <div class="control-group span5" style="width:300px">
                    <label for="type" class="kp-label">银行账户</label>
                    <div class=" kp-controls">
                      <input type="text" id="" placeholder="" style="height:30px" name="bank_account">
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="control-group span5" style="width:300px">
                    <label for="type" class="kp-label">公司地址</label>
                    <div class=" kp-controls">
                      <input type="text" id="" placeholder="" style="height:30px" name="bank_addr">
                    </div>
                  </div>
                </td>
                <td colspan="4">
                  <div class="control-group span5" style="width:300px">
                    <label for="type" class="kp-label">联系电话</label>
                    <div class=" kp-controls">
                      <input type="text" id="" placeholder="" style="height:30px" name="phone">
                    </div>
                  </div>
                </td>
              </tr>
             </tbody>
          </table>
        </div>
      </div>
      <div class="m4">
        <ul>
          <li><span>经办人：</span><p></p></li>
          <li><span>主管签字：</span><p></p></li>
          <li><span>流程部备案签字：</span><p></p></li>
        </ul>
      </div>
      
    </form>
    </div>
</div>
</div>
</div>
      <script>
      function submitFrom(){
	    var numreg = /^\d+(\.\d{2})?$/;
	    var countreg = /^\d+$/;
        var sal_bill = $("input[name='sal_bill']").val();//申请开票金额
        var bill_high = $("input[name='bill_high']").val();//高开金额
        var duty_para = $("input[name='duty_para']").val();//开票税号
        var bank_name = $("input[name='bank_name']").val();//开票银行
        var bank_account = $("input[name='bank_account']").val();//银行账户
        var bank_addr = $("input[name='bank_addr']").val();//公司地址
        var phone = $("input[name='phone']").val();//电话
        if(sal_bill==''||sal_bill==null||!numreg.test(sal_bill)){alert("申请开票金额请输入数字");return false;}
         if(bill_high==''||bill_high==null||!numreg.test(bill_high)){alert("高开金额请输入数字");return false;}
         if(duty_para==''||duty_para==null){alert("开票税号不能为空");return false;}
         if(bank_name==''||bank_name==null){alert("开票银行不能为空");return false;}
         if(bank_account==''||bank_account==null){alert("银行账户不能为空");return false;}
         if(bank_addr==''||bank_addr==null){alert("公司地址不能为空");return false;}
         if(phone==''||phone==null){alert("联系电话不能为空");return false;}
         $("#addForm").submit();
      }
      
      function isDis() {
	if ($('input:radio[name="state"]:checked').val()==1) {
		$("#account").attr("style","width:100px;height:20px;display:''");
	}else{
		$("#account").attr("style","width:100px;height:20px;display:none");
		$("input[name='bill_high']").val(0);
	}
   }
       function toPrint(){
	$(".form1").removeClass("form1");
	window.print();
	$(".form1").addClass("form1");
}
    </script>
    <!-- Js -->
    <script src="${contract_ct_url_html}js/jquery.min.js"></script>
    <script src="${contract_ct_url_html}js/bootstrap.min.js"></script>
  </body>
</html>