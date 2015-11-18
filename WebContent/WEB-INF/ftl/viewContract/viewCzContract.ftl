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
    <#if czContract.user_id==userInfo.usercode && czContract.con_state==0><input id="edit" name="" type="button" value="修改" onClick="updateContract()"></#if>
    <input name="" type="button" value="打印" onClick="toPrint();">
    <#if czContract.con_state==5><input name="" type="button" data-toggle="modal" href="javascript:void(0)" value="提交" onclick="subContract()"/></#if>
     <ul class="nav nav-tabs">
            <li class="active"><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/myContract/viewCzContract?conId=${czContract.con_cz_id}'" data-toggle="tab">查看合同</a></li>
            <#if (accCount>0)>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/conAccount/viewAccount?conId=${czContract.con_cz_id}'" data-toggle="tab">查看相关说明</a></li>
            </#if>
            <#if (billCount>0)>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConBill/viewBills?id=${czContract.con_cz_id}'" data-toggle="tab" >查看开票</a></li>
            </#if>
            <#if (rebateCount>0)>
            <li class=""><a href="#" onclick="javascript:window.location.href='${contract_ct_url}contract/tbConRebate/rebateBill?conId=${czContract.con_cz_id}'" data-toggle="tab">查看返利</a></li>
   			</#if>
    </ul>
    </div>
    
    <div class="logo">
      <ul>
        <li>合同号<br>ORDER No</li>
        <li style="padding-top:5px"  id="conId">${czContract.con_cz_id}</li>
      </ul>
        <img src="${contract_ct_url_html}images/logo01.png" alt="">
    </div>
    <div class="m1" style="text-align:center;">
      <ul>
        <li>上海众智电子商务有限公司&nbsp;Tel：021-58661818 Fax：021-58665555&nbsp;上海市浦东新区富特北路456号1号楼三层南部</li>
      </ul>
    </div>
    <h1 class="title">中国汽车消费网-车展合约  CONTRACT</h1>
    <h5>客户提供的广告刊登材料如有违反广告法、著作权法、肖像权等相关法规，由客户方承担全部责任</h5>
    <div class="m2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb3">
          <tbody>
            <tr>
              <td><span>客户名称Client</span><p>${czContract.cus_name}</p></td>
              <td><span>类型Kind</span><label style="margin:0;float:left;padding:0 20px 0 0">
              <#if czContract.con_type==0>
              <input type="checkbox" checked="checked" disabled="true">车展</label><label style="margin:0;float:left"><input type="checkbox" disabled="true">车展+广告</label><label style="margin:0;float:left"><input type="checkbox" disabled="true">车展+活动</label>
              <#elseif czContract.con_type==1>
              <input type="checkbox" disabled="true">车展</label><label style="margin:0;float:left"><input type="checkbox" checked="checked" disabled="true">车展+广告</label><label style="margin:0;float:left"><input type="checkbox" disabled="true">车展+活动</label>
              <#elseif czContract.con_type==2>
               <input type="checkbox" disabled="true">车展</label><label style="margin:0;float:left"><input type="checkbox" disabled="true">车展+广告</label><label style="margin:0;float:left"><input type="checkbox" checked="checked" disabled="true">车展+活动</label>
              </#if>
              </td>
            </tr>
            <tr>
              <td><span>地址Add.</span><p>${czContract.cus_addr}</p></td>
              <td><span>电话Tel.</span><p>${czContract.cus_tel}</p></td>
            </tr>
           </tbody>
         </table>
    </div>
    <div class="m2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb4">
          <tbody>
      			<th colspan="8">媒介Medium： Ⅰ中国汽车消费网www.315che.com     Ⅱ 框架Frame     Ⅲ 其他Others</th>
            <tr>
              <td><span>品牌</span><p>Brand</p></td>
              <td><span>车系</span><p>Series</p></td>
      			  <td><span>车辆台数</span><p>Units</p></td>
      			  <td><span>活动地址</span><p>Venue</p></td>
      			  <td><span>活动日期</span><p>Date</p></td>
      			  <td><span>合计总价</span><p>Sub-total</p></td>
      			  <td colspan="2"><span>备注</span><p>Remark</p></td>
            </tr>
            <tr>
              <td><span>${czContract.cus_brand}</span></td>
              <td><span>${czContract.seriers_name}</span></td>
			  <td><span>${czContract.cus_count}</span></td>
              <td><span>${czContract.act_addr}</span></td>
              <td><span>${czContract.act_date}</span></td>
              <td><span>${czContract.con_total_price}元</span></td>
              <td colspan="2"></td>
            </tr>
            <#if (czContract.tbConZhSubs?size>0)>
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
	      		<#list czContract.tbConZhSubs as subs>
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
            </#if>
			      <tr>
              <td colspan="8" style="text-align:left">备注（没有可不填）：</td>
            </tr>
            <tr><td colspan="8" style="height:60px;text-align:left;">${czContract.con_content}</td></tr>
            <td colspan="8">
      				<div class="p01" style="padding:0 0 0 5px">合同执行总价<br>Total Price</div>
              <div class="p01">大写：${czContract.con_total_priceUpperCase}</div>
              <div class="p01">小写：￥${czContract.con_total_price}</div>
              <div class="p01">金额单位：元</div>
      			</td>
           </tbody>
         </table>
    </div>
    <h5>账号信息： 上海众智电子商务有限公司    1001279909006951954    工商银行保税区支行</h5>
    <div class="m3">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tb5">
          <tbody>
            <tr>
              <td>
                <div class= "txt">
                  <strong><p>本合同包括以下条款：</p></strong>
                  <ul>
                    <li>1．付款日期：参展前10个工作日预付50%定金，参展后5个工作日付清尾款。</li>
                    <li>2．车展车型及台数在合同签订当天确定，如有增减更换，请在开展前3个工作日书面通知我公司。客户展车超过，本公司有权拒绝，或收取单台参展费用。</li>
                    <li>3．搭建责任：参展商在指定的时间内进行展位的搭建和布置。由参展方或其委托搭建商的原因使其他参展商或公共财产受到损害，乙方须作出赔偿。</li>
                    <li>4．安保责任：车辆参展期间安全及参展人员安全自行负责；参展如有特殊活动提前报备并得到组办方批准，安保问题自行解决。主办方提供标准的场地安保保障。</li>
                  </ul>
                </div>
              </td>
              <td>
                <div class= "txt">
                  <ul>
                  	<li>5．客户对售卖的车型负完全责任。我公司组织的车辆购买者在后期新车交付及使用过程中出现的任何问题与我公司无关。</li>
                    <li>6．如有特卖活动，客户需确认向我公司提供的特卖车辆信息真实、有效；如因虚假信息造成的一切后果与我公司无关。</li>
                    <li>7．在合同履行期间，由于地震、台风等自然灾害难以继续履行合同，双方按照事故对合同影响程度，本着最大限度减小损失的原则协商解决，双方无需承担因此所产生的违约责任。</li>
                    <li>8．签约合同逾期付款，违约方每天需承担合同金额3‰的违约金。</li>
                    <li>9．本合同最终解释权归上海众智电子商务有限公司所有。本合同一式两份、自签约日起生效，任何更改须经双方同意。</li>
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
				url: "/contract/czContract/czStartFlow?conId="+conId,
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
			location.href="/contract/czContract/toupdateCzContract?conId="+conId;
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