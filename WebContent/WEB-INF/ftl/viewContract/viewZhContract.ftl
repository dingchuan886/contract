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
    <div class="v-h" style="text-align:center">
     <#if zhContract.user_id==userInfo.usercode && zhContract.con_state==0><input id="edit" name="" type="button" value="修改" onClick="updateContract()"></#if>
    <input name="" type="button" value="打印" onclick="toPrint();">
     <#if zhContract.con_state==5><input name="" type="button" data-toggle="modal" value="提交" onclick="subContract()"/></#if>
      <ul class="nav nav-tabs">
            <li class="active"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/myContract/viewZhContract?conId=${zhContract.con_zh_id}'" data-toggle="tab">查看合同</a></li>
            <#if (accCount>0)>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/conAccount/viewAccount?conId=${zhContract.con_zh_id}'" data-toggle="tab">查看相关说明</a></li>
            </#if>
            <#if (billCount>0)>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConBill/viewBills?id=${zhContract.con_zh_id}'" data-toggle="tab" >查看开票</a></li>
            </#if>
            <#if (rebateCount>0)>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConRebate/rebateBill?conId=${zhContract.con_zh_id}'" data-toggle="tab">查看返利</a></li>
   			</#if>
    </ul>
     </div>
    <div class="logo">
      <ul>
        <li>合同号<br>ORDER No</li>
        <li style="padding-top:5px" id="conId">${zhContract.con_zh_id}</li>
      </ul>
        <img src="${contract_ct_url_html}images/logo01.png"/>
    </div>
    <div class="m1" style="text-align:center;">
      <ul>
        <li>上海众智电子商务有限公司 &nbsp;Tel：021-58661818  Fax：021-58665555&nbsp;上海市浦东新区富特北路456号1号楼三层南部</li>
      </ul>
    </div>
    <h1 class="title">广 告 合 约  CONTRACT</h1>
    <h5>客户提供的广告刊登材料如有违反广告法、著作权法、肖像权等相关法规，由客户方承担全部责任</h5>
    <div class="m2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb3">
          <tbody>
            <tr>
              <td><span>客户名称Client</span><p>${zhContract.cus_name}</p></td>
              <td><span>物料Material</span><p><#if zhContract.material==0>无</#if>
              <#if zhContract.material==1>有</#if>
              </p></td>
            </tr>
            <tr>
              <td><span>地址Add.</span><p>${zhContract.cus_addr}</p></td>
              <td><span>电话Tel.</span><p>${zhContract.cus_tel}</p></td>
            </tr>
           </tbody>
         </table>
    </div>
    <div class="m2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb4">
          <tbody>
          	<tr>
      			<th colspan="8">媒介Medium： Ⅰ中国汽车消费网www.315che.com     Ⅱ 框架Frame     Ⅲ 其他Others</th>
      		</tr>
      		    <tr>
              <td><span>媒体</span><p>Medium</p></td>
              <td><span>位置</span><p>Place</p></td>
      			  <td><span>规格</span><p>Description</p></td>
      			  <td><span>品牌</span><p>Brand</p></td>
      			  <td><span>投放日期</span><p>Insertion Dates</p></td>
      			  <td><span>刊例价</span><p>Unit Price</p></td>
              <td><span>合计单价</span><p>Sub-total</p></td>
              <td><span>备注</span><p>Remark</p></td>
            </tr>
      		<#list zhContract.tbConZhSubs as subs>
            <tr>
              <td><p><#if subs.media==1>中国汽车消费网www.315che.com</#if>
              <#if subs.media==2>框架Frame </#if>
              <#if subs.media==3>其他Others</#if>
              </p></td>
              <td><p>${subs.position}</p></td>
      			  <td><p>${subs.standard}</p></td>
      			  <td><p>${subs.brand}</p></td>
      			  <td><p>${subs.put_date}</p></td>
      			  <td><p>${subs.copyright}</p></td>
	              <td><p>${subs.unit_price}</p></td>
	              <td><p>${subs.content}</p></td>
            </tr>
            </#list>

            <td colspan="8">
      				<div class="p01" style="padding:0 0 0 5px">合同执行总价<br>Total Price</div>
              <div class="p01">大写：${zhContract.con_total_priceUpperCase}</div>
              <div class="p01">小写：￥${zhContract.con_total_price}</div>
              <div class="p01">金额单位：元</div>
      			</td>
           </tbody>
         </table>
    </div>
    <h5>账号信息： 上海众智电子商务有限公司    1001279909006951954    工行外高桥支行 </h5>
    <div class="m3">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb5">
          <tbody>
            <tr>
              <td>
                <div class= "txt">
                  <strong><p>本合同包括以下条款：</p></strong>
                  <ul>
                    <li>1．付款日期为广告刊登后10个工作日。</li>
                    <li>2．本合同自签约日起生效，任何更改须经双方同意。</li>
                    <li>3．广告已安排，客户因故调整内容或取消者，须按本规定，交纳合同金额20％的调版费或撤版费。</li>
                    <li>4．指定版位加收20％的广告费，特殊规格加收50％的广告费，仅指定非广告版位须加收20％的广告费。</li>
                    <li>5．如客户提供的材料违反广告法等相关法律法规，本公司有权停止刊登或修改刊登，并且不视为违反第二条款规定。</li>
                  </ul>
                </div>
              </td>
              <td>
                <div class= "txt">
                  <ul>
                    <li>6．广告资料须在刊登前5天交稿或确认。如客户要求撤单，须提前3个工作日书面通知我公司。因客户未按规定撤单或未按期交稿造成本合同无法执行，由客户承担空版责任，即承担合同执行价的20％赔偿责任。</li>
                    <li>7．签约合同逾期付款，违约方每天需承担合同金额3‰的违约金。</li>
                    <li>8．如果本合同前一次刊登后，客户未按期付款，众智有权暂停后续次合同刊登的执行。</li>
                  </ul>
                </div>
              </td>
            </tr>
           </tbody>
        </table>
    </div>
    <h4>备注（没有可不填）：</h4>
    <div class="m2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb6">
          <tbody>
            <tr>
              <td><span>客户签署（盖章）<br>Signature of Client</span></td>
              <td><span>众智签署（盖章）<br>Signature of Agency</span></td>
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
				url: "/contract/zhContract/zhStartFlow?conId="+conId,
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
	location.href="/contract/zhContract/toupdateZhContract?conId="+conId;
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