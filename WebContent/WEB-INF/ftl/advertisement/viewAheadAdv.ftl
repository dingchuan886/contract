<!DOCTYPE html>
<html>
  <head>
    <title>广告合同提前刊登申请</title>
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
  
    <form action="/contract/adveritesment/saveAdvertisement" class="form-horizontal" method="post">
      <div class="v-h" style="text-align:center"></div>
      <div class="m2">
        <h2>广告合同提前刊登申请</h2>
      </div>
      <div class="m7">
        <ul>
          <li><span>合同编号(请填写准确)</span><p>${aheadAdv.con_id}</p></li>
          <li><span style="text-indent:25px">兹有</span>
          <p style="text-align:center;">${aheadAdv.cus_name}</p><span>（客户名称）拟在我司投放广告，因</span>
          <#--此处的reazon数据库写错 更改量较大，就这么写着吧-->
          <p style="text-align:center;">${aheadAdv.reazon}</p><span>原因无法在刊登前拿回签署完毕的合同原件，现申请刊登如下位置广告，请予批准：</span></li>
        </ul>
        <ul>
          <li>
	          <table>
		          <tbody>
		          	<#if (aheadAdv.tbConAheadadvertismentSub?size>0)>
		          	<#list aheadAdv.tbConAheadadvertismentSub as list>
			          <tr>
				          <td><span>投放位置:</span><p style="text-align:center;">${list.put_pos}</p></td>
				          <td><span>投放时间:</span><p style="text-align:center;">${list.put_date}</p></td>
			          </tr>
			          </#list>
			         </#if>
		          </tbody>
          	  </table>
          </li>
          
          <li><span>合同金额：</span><p style="text-align:center;">${aheadAdv.con_price}</p>元</li>
        </ul>
        <ul>
          <li><span>本人保证以上刊登内容与合同原件一致，愿意承担由此带来的一切后果，并在</span>
          <p style="width:50px;text-align:center;">${aheadAdv.year}
          </p><span>年</span><p style="width:50px;text-align:center;">${aheadAdv.month}</p><span>月</span>
          <p style="width:50px;text-align:center;">${aheadAdv.day}</p><span>日前拿回合同原件交与公司。</span></li>
        </ul>
      </div>
      <div class="m4" style="width:800px;margin:0 auto;height:30px">
        <ul>
          <li>申请人：<img src="${userUrl}" width="60px" height="30px"></li><br/>
          <li>申请日期：${aheadAdv.create?string("yyyy年MM月dd日")}</li>
        </ul>
      </div>
      <div class="m4" style="width:800px;margin:0 auto;">
        <ul>
          <li>经办人：<img src="${userUrl}" width="60px" height="30px"></li>
          <li>主管签字：<#if manageCheck??><img src="${manageCheck}" width="60px" height="30px"></#if></li>
          <li>流程部备案签字：<#if flowCheck??><img src="${flowCheck}" width="60px" height="30px"></#if></li>
      </div>
    </form>

    <!-- Js -->
    <script src="${contract_ct_url_html}js/jquery.min.js"></script>
    <script src="${contract_ct_url_html}js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	var flag1 = true;
    	function addTr(){
			var tr = $("tr:last").clone(true,true);
			$("tbody").append($(tr));
		}
		
		function del(obj){
			if($(obj).parent().parent().siblings("tr").size()>0){
				$(obj).parent().parent().remove();
			}
			
		}
		
		function subAdv(){
			var flag = true;
			if(!flag1){
				alert("已提交，请勿重复提交");
				flag = false;
				return;
			}
			$(":text[name='put_pos']").each(function(i){
				if($(this).val()==""){
					alert("请填写投放位置");
					flag = false;
					return;
				}
				$(this).attr("name","tbConAheadadvertismentSub["+i+"].put_pos");
			})
		
			$(":text[name='put_date']").each(function(i){
				if($(this).val()==""){
					alert("请填写投放时间");
					flag = false;
					return;
				}
				$(this).attr("name","tbConAheadadvertismentSub["+i+"].put_date");
			})
			var cusName = $("#cus_name").val();
			var year = $("#year").val();
			var month = $("#month").val();
			var day = $("#day").val();
			var price = $("#con_price").val();
			var priceReg = /^\d+(\.\d{2})?$/;
		
			
			if(!priceReg.test(price)){
				flag = false;
				alert("合同金额请输入数字");
				return;
			}
			if(cusName==""){
				flag = false;
				alert("请输入客户名");
				return;
			}
			if(month=="" || month<1 || month>12){
				flag = false;
				alert("月份输入有误");
				return;
			}
			if(day=="" || day<1 || day>31){
				flag = false;
				alert("日子输入有误");
				return;
			}
			if(flag){
				flag1 = false;
				$("form")[0].submit();
			}
		}
    </script>
  </body>
</html>