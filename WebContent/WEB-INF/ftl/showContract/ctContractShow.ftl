<!DOCTYPE html>
<html>
  <head>
    <title></title>
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
    <form name="form1">
    <div class="v-h" style="text-align:center"><input id="edit" name="" type="button" value="修改" onClick="updateContract()">
    <input name="" type="button" value="打印" onClick="toPrint();">
    <input name="" type="button" data-toggle="modal" value="提交" onclick="subContract()"/>
    <ul  class="nav nav-tabs"><li><a href="#" onclick="javascript:window.open('${contract_ct_url}contract/conAccount/viewAccount?conId=${ctContract.con_ct_id}')" data-toggle="tab">查看相关说明</a></li></li></ul>
    
    </div>
    <div class="logo">
      <ul>
        <li>合同号<br>ORDER No</li>
        <li style="padding-top:5px" id="conId">${ctContract.con_ct_id}</li>
      </ul>
        <img src="${contract_ct_url_html}images/logo02.png" alt="">
    </div>
    <div class="m1" style="margin-left:170px;">
      <ul>
        <li>上海车团网络信息技术有限公司 &nbsp;Tel：021-58661818  Fax：021-58665555&nbsp;上海市浦东新区富特北路456号1号楼三层南部</li>
      </ul>
    </div>
    <h1 class="title">车团网-线下活动 合约  CONTRACT</h1>
    <h5>客户如提供虚假的车型信息等造成的一切后果，由客户方承担全部责任</h5>
    <div class="m2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb3">
          <tbody>
            <tr>
              <td><span>客户名称Client</span><p>${ctContract.cus_name}</p></td>
              <td><span>类型Kind</span><label style="margin:0;float:left;padding:0 20px 0 0">
              <#if ctContract.bus_type==1>
              <input type="checkbox" checked="checked" disabled="true">团购</label><label style="margin:0;float:left"><input type="checkbox" disabled="true">特卖会</label></td>
              </#if>
              <#if ctContract.bus_type==2>
              <input type="checkbox" disabled="true">团购</label><label style="margin:0;float:left"><input type="checkbox" checked="checked" disabled="true">特卖会</label></td>
              </#if>
            </tr>
            <tr>
              <td><span>地址Add.</span><p>${ctContract.cus_addr}</p></td>
              <td><span>电话Tel.</span><p>${ctContract.cus_tel}</p></td>
            </tr>
           </tbody>
         </table>
    </div>
    <div class="m2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb4">
          <tbody>
      			<th colspan="6"></th>
            <tr>
              <td><span>媒体</span><p>Medium</p></td>
              <td><span>品牌</span><p>Brand</p></td>
              <td><span>车系</span><p>Series</p></td>
      			  <td><span>活动地址</span><p>Place</p></td>
      			  <td><span>活动日期</span><p>Date</p></td>
      			  <td><span>合计总价</span><p>Sub-total</p></td>
            </tr>
            <tr>
              <td>${ctContract.media}</td>
              <td>${ctContract.cus_brand}</td>
			  <td>${ctContract.seriers_name}</td>
              <td>${ctContract.act_addr}</td>
              <td>${ctContract.act_date?string("yyyy-MM-dd")}</td>
              <td>${ctContract.total_price}</td>
            </tr>
			<tr>
              <td colspan="6" style="text-align:left">备注（没有可不填）：</td>
            </tr>
            <tr><td colspan="6" style="height:60px;text-align:left;">${ctContract.con_content}</td></tr>
            <td colspan="6">
      				<div class="p01" style="padding:0 0 0 5px">合同执行总价<br>Total Price</div>
              <div class="p01">大写：</div>
              <div class="p01">小写：￥</div>
              <div class="p01">金额单位：元</div>
      			</td>
           </tbody>
         </table>
    </div>
    <h5>账号信息： 上海车团网络信息技术有限公司     0300 2408 022      上海银行静安支行 </h5>
    <div class="m3">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb5">
          <tbody>
            <tr>
              <td>
                <div class= "txt">
                  <strong><p>本合同包括以下条款：</p></strong>
                  <ul>
                    <li>1．付款日期为本合同执行完毕后10个工作日以内。</li>
                    <li>2．本合同一式两份、自签约日起生效，任何更改须经双方同意。</li>
                    <li>3．活动已安排，客户因故调整内容或取消者，须按本条款，交纳合同金额20％的活动推广及组织费用。</li>
                    <li>4．如有特卖惠活动，客户需确认向我公司提供的特卖惠车辆信息真实、有效；如因虚假信息造成的一切后果与我公司无关。</li>
                  </ul>
                </div>
              </td>
              <td>
                <div class= "txt">
                  <ul>
                  	<li>5．活动车型资料须在合同签订当天确定。如客户要求撤单，须提前3个工作日书面通知我公司。因客户未按规定撤单或未按期提供车型信息造成本合同无法执行，由客户承担合同执行总价的20％赔偿责任。</li>
                    <li>6．签约合同逾期付款，违约方每天需承担合同金额3‰的违约金。</li>
                    <li>7．客户对售卖的车型负完全责任。我公司组织的车辆购买者在后期新车交付及使用过程中出现的任何问题与我公司无关。</li>
                    <li>8.本合同最终解释权归上海车团网络信息技术有限公司所有。</li>
                  </ul>
                </div>
              </td>
            </tr>
           </tbody>
        </table>
    </div>
    <div class="m2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb6">
          <tbody>
            <tr>
              <td><span>客户签署（盖章）<br>Signature of Client</span></td>
              <td><span>车团签署（盖章）<br>Signature of Agency</span></td>
            </tr>
            <tr>
               <td><span>经办人员<br>Account Executive</span><p></p></td>
              <td><span>经办人员<br>Account Executive</span><p></p></td>
            </tr>
            <tr>
              <td><span>签约日期<br>Date of Signature</span><label>20</label><label>年</label><label>月</label><label>日</label></td>
              <td><span>签约日期<br>Date of Signature</span><label>20</label><label>年</label><label>月</label><label>日</label></td>
            </tr>
           </tbody>
         </table>
    </div>
    </form>

    <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <h3>提交成功！</h3>
      <div class="modal-footer" style="text-align:center; margin-top:10px">
        <button class="btn btn-info" data-dismiss="modal"  onclick="javascript:window.close()">关闭</button>
      </div>
    </div>
</div>
</div>
</div>
</div>
    <!-- Js -->
    <script src="${contract_ct_url_html}js/jquery.min.js"></script>
    <script src="${contract_ct_url_html}js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	function subContract() {
    		var conId = $("#conId").html();
			$.ajax({
				url: "/contract/ctContract/ctStartFlow?conId="+conId,
				success:function(data){
				data = eval("("+data+")");
					if(data.ok=="200"){
						$("#myModal").attr({"style":"display:block;","class":"modal hide fade in","aria-hidden":"false"});
					}else{
						alert("提交失败，请返回合同详情页重新提交");
						location.href='/contract/myContract';
					}
				}
			});
		}
		function updateContract(){
			var conId = $("#conId").html();
			location.href="/contract/ctContract/toupdateCtContract?conId="+conId;
		}
		
		document.onkeydown = KeyDown;
function KeyDown(){
 	if ((event.keyCode==116)||(event.ctrlKey && event.keyCode==82))
  { 
     event.keyCode=0;
     event.returnValue=false;
  } 
  

}
function toPrint(){
	$(".form1").removeClass("form1");
	window.print();
	$(".form1").addClass("form1");
}
    </script>
  </body>
</html>