<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${contract_ct_url_html}css/bootstrap.min.css" rel="stylesheet" media="screen"> 
    <link href="${contract_ct_url_html}css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="${contract_ct_url_html}css/print.css" rel="stylesheet" type="text/css">
<title>众智开票申请单查看</title>
</head>
<body>
  <div class="container">
  <div class="row-fluid">
  <div class="span12">
  <div class="well">
   <form action="${contract_ct_url}contract/tbConBill/addTbConBill" id="addForm" method="post">
      <div class="v-h" style="text-align:center"><input name="" type="button" value="打印" onClick="toPrint();"></div>
      <div class="m2">
        <h2>上海车团网络信息技术有限公司</h2>
        <h3>开票申请单</h3>
        <div class="m5">
          <ul>
            <li style="float:right; text-align:right"><span>站点：</span><p align="center">${org.org_name}</p></li>
            <li>申请开票日期：${conBill.create?string("yyyy-MM-dd")}</li>
          </ul>
        </div>
        <div class="m3">
          <table width="100%" border="1" cellspacing="0" cellpadding="0" class="tb7">
            <tbody>
              <tr>
                <td colspan="2">客户名称：${tbConct.cus_name}</td>
                <td colspan="3">销售合同号/销售订单号：${tbConct.con_ct_id}</td>
              </tr>
              <tr>
                <td colspan="2">合同总价：${tbConct.total_price}&nbsp;元</td>
                <td colspan="2">已开票金额：${tbConct.al_bill}&nbsp;元</td>
                 <input type="hidden" name="al_bill" value="${tbConct.al_bill}">
                <td>申请开票金额： ${conBill.sal_bill} &nbsp;元</td>
              </tr>
              <tr>               
                <td colspan="5">
                  <div class="m6">
                    <ul>
                      <li style="padding-right:60px">发票金额：</li>
                      <#if  conBill.state==0 >
                      <li>平开</li>
                      <#elseif conBill.state==1>
                      <li style="width:50%">高开（金额： ${conBill.bill_high}&nbsp;元）</li>
                      </#if>
                    </ul>
                  </div>
                </td>
              </tr>
              <tr>
                <td>发票类型</td>
                      <#if  conBill.bill_type==0 >
                       <td colspan="3"><label>广告费</label> </td>
                      <#elseif conBill.bill_type==1>
                       <td colspan="3"><label> 广告发布费</label> </td>
                      <#elseif conBill.bill_type==2>
                         <td colspan="3"><label> 活动服务费 </label> </td>
                        <#elseif conBill.bill_type==3>
                        <td colspan="3"> <label>其他</label> </td>
                      </#if>
              </tr>
              <tr>
                 <td colspan="5" height="40">备注：${conBill.bill_content}</td>
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
                      ${conBill.duty_para}
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
                      ${conBill.bank_name}
                    </div>
                  </div>
                </td>
                <td colspan="4">
                  <div class="control-group span5" style="width:300px">
                    <label for="type" class="kp-label">银行账户</label>
                    <div class=" kp-controls">
                      ${conBill.bank_account}
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="control-group span5" style="width:300px">
                    <label for="type" class="kp-label">公司地址</label>
                    <div class=" kp-controls">
                     ${conBill.bank_addr} 
                    </div>
                  </div>
                </td>
                <td colspan="4">
                  <div class="control-group span5" style="width:300px">
                    <label for="type" class="kp-label">联系电话</label>
                    <div class=" kp-controls">
                      ${conBill.phone}
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
          <li><span>经办人：</span><p>
          <#if user_writerUrl!=''>
          <image src="${user_writerUrl}" width="80px" height="60px"/>
          </#if>
          </p></li>
          <li><span>主管签字：</span><p>
          <#if manager_writeUrl!=''>
           <image src="${manager_writeUrl}" width="80px" height="60px"/>
          </#if>
          </p></li>
          <li><span>区域经理签字：</span><p>
          <#if area_writeUrl!=''>
           <image src="${area_writeUrl}" width="80px" height="60px"/>
          </#if>
          </p></li>
          <li><span>流程部备案签字：</span><p>
          <#if flow_writeUrl!=''>
           <image src="${flow_writeUrl}" width="80px" height="60px"/>
          </#if>
          </p></li>
        </ul>
      </div>
    </form>
    </div>
</div>
</div>
</div>
    <!-- Js -->
    <script src="${contract_ct_url_html}js/jquery.min.js"></script>
    <script src="${contract_ct_url_html}js/bootstrap.min.js"></script>
    <script type="text/javascript">
      function toPrint(){
		$(".form1").removeClass("form1");
		window.print();
		$(".form1").addClass("form1");
	}

    </script>
  </body>
</html>