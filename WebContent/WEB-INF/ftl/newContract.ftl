<#import "common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<@c.html/>
<body>
<@c.head/>
<@c.left/>
<div class="span10">
          <ul class="nav nav-tabs">
            <li class="active"><a href="#A" data-toggle="tab">众智车展合同</a></li>
            <li class=""><a href="#B" data-toggle="tab">众智315广告合同</a></li>
            <li class=""><a href="#C" data-toggle="tab">车团合同</a></li>
          </ul>
          <form action="/contract/chetuan" class="form-horizontal">
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="userCode" class="control-label">姓名</label>
                <div class="controls">
                  <input type="text" placeholder="${userInfo.username}" name="username" disabled>
                </div>
              </div>
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">日期</label>
                <div class="controls">
                  <input type="text" id="evidenceCode" placeholder="${nowDate}" disabled>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="userCode" class="control-label">分站</label>
                <div class="controls">
                  <input type="text" id="evidenceCode" placeholder="${userInfo.orgname}" disabled>
                </div>
              </div>
              <div class="control-group span5">
                <label for="evidenceCode" class="control-label">部门</label>
                <div class="controls">
                  <input type="text" id="evidenceCode" placeholder="${userInfo.deptname}" disabled>
                </div>
              </div>
            </div>
            <div class="alert alert-info">
              <button type="button" class="close" data-dismiss="alert">×</button>
              以上姓名，日期，站点，部门信息仅供查看无法修改！
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">客户类型</label>
                <div class="controls">
                  <select name="cus_type" id="">
                    <option value="1">大客户</option>
                    <option value="2">区域</option>
                    <option value="3">经销商</option>
                  </select>
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">客户名称</label>
                <div class="controls">
                  <input type="text" name="cus_name" placeholder="客户名称">
                </div>
              </div>
            </div>
            <ul class="inline">
              <label for="userCode" class="control-label" style="padding:0">类型</label>
              <li>
                <label class="radio">
                  <input type="radio" name="con_type" style="margin:0" value="0">
                  车展
                </label>
              </li>
              <li>
                <label class="radio">
                  <input type="radio" name="con_type" style="margin:0" value="1">
                  车展+广告
                </label>
              </li>
            </ul>
            <div class="row-fluid" style="border-bottom:1px solid #ddd">
              <div class="control-group span5">
                <label for="type" class="control-label">地址</label>
                <div class="controls">
                  <input type="text" name="cus_addr" placeholder="">
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">电话</label>
                <div class="controls">
                  <input type="text" name="cus_tel" id="cus_tel">
                </div>
              </div>
            </div>
            <div class="row-fluid" style="padding-top:20px;">
              <div class="control-group span5">
                <label for="type" class="control-label">品牌</label>
                <div class="controls">
                  <select name="cus_brand">
                    <option value="">奥迪</option>
                  </select>
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">车系</label>
                <div class="controls">
                  <select name="cus_seriers" id="">
                    <option value="">SUV</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">车台数量</label>
                <div class="controls">
                  <input class="input-small" type="text" name="cus_count" id="cus_count">
                </div>
              </div>
              <div class="control-group span6">
                <label for="contractEnd" class="control-label">活动日期</label>
                <div class="controls">
                  <div class="input-append date form_datetime">
                      <input type="text" value="" readonly="true" name="act_data" data-date="2013-02-21" style="width:180px;">
                      <span class="add-on"><i class="icon-remove"></i></span>
                      <span class="add-on"><i class="icon-th"></i></span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">活动地址</label>
                <div class="controls">
                  <input type="text" id="" name="act_addr" placeholder="请输入详细地址">
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">合计执行总价</label>
                <div class="controls">
                  <input class="input-small" type="text" name="con_total_price" placeholder="100" style="float:left">
                </div>
                <label style="float:left; padding:5px 0 0 5px;">元</label>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="type" class="control-label">备注</label>
                <div class="controls"><textarea name="con_content" id="" rows="5" style=" width:555px; float:left"></textarea></div>
              </div>
            </div>
            <ul class="inline">
              <label for="userCode" class="control-label" style="padding:0"></label>
              <li>
                <label class="radio">
                  <input type="radio" name="stamp" style="margin:0">
                  客户先盖章
                </label>
              </li>
              <li>
                <label class="radio">
                  <input type="radio" name="stamp" style="margin:0">
                  公司先盖章
                </label>
              </li>
            </ul>
            <div class="controls">
              <p>
                <a class="btn btn-danger" href="">生成合同</a>
              </p>
            </div>
            <div class="row-fluid" style="border-top:1px solid #ddd; margin-top:20px; padding-top:20px">
              <div class="control-group span5">
                <label for="paymentType" class="control-label">有无返利</label>
                <div class="controls">
                  <label class="radio" style=" float:left">
                    <input type="radio" name="pay-type" id="" value="option1" checked="">
                    有
                  </label>
                  <label class="radio" style=" float:left; padding:5px 0 0 40px">
                    <input type="radio" name="pay-type" id="" value="option2">
                    无
                  </label>
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">返利比例</label>
                <div class="controls">
                  <input class="input-small" type="text" placeholder="100" style="float:left">
                </div>
                <label style="float:left; padding:5px 10px 0 5px; color:#999">或</label>
                <div class="controls">
                  <input class="input-mini" type="text" placeholder="100" style="float:left">
                </div>
                <label style="float:left; padding:5px 0 0 5px; ">元</label>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="paymentType" class="control-label">执行进度</label>
                <div class="controls">
                  <label class="radio" style=" float:left">
                    <input type="radio" name="pay-type" id="" value="option1" checked="">
                    按期执行
                  </label>
                  <label class="radio" style=" float:left; padding:5px 0 0 40px">
                    <input type="radio" name="pay-type" id="" value="option2">
                    适时调整
                  </label>
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">将于</label>
                <div class="controls">
                  <input class="input-small" type="text" placeholder="100" style="float:left">
                </div>
                <label style="float:left; padding:5px 10px 0 5px;">月份投放完毕</label>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="paymentType" class="control-label">开票说明</label>
                <div class="controls">
                  <label class="radio" style=" float:left">
                    <input type="radio" name="pay-type" id="" value="option1" checked="">
                    平开
                  </label>
                  <label class="radio" style=" float:left; padding:5px 0 0 40px">
                    <input type="radio" name="pay-type" id="" value="option2">
                    不开票
                  </label>
                  <label class="radio" style=" float:left; padding:5px 0 0 40px">
                    <input type="radio" name="pay-type" id="" value="option2">
                    高开
                  </label>
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">预计开票金额为</label>
                <div class="controls">
                  <input class="input-small" type="text" placeholder="100" style="float:left">
                </div>
                <label style="float:left; padding:5px 10px 0 5px;">元</label>
              </div>
            </div>
            <div class="row-fluid">
              <div class="control-group span5">
                <label for="paymentType" class="control-label">回款预计</label>
                <div class="controls">
                  <label class="radio" style=" float:left">
                    <input type="radio" name="pay-type" id="" value="option1" checked="">
                    分期投放，及时开票后回款
                  </label>
                </div>
              </div>
              <div class="control-group span5">
                <label class="radio" style=" float:left; padding-top:5px">
                  <input type="radio" name="pay-type" id="" value="option2">
                  预计
                </label>
                <input class="input-mini" type="text" placeholder="100" style="float:left; margin-left:10px">
                <label style="float:left; padding:5px 0 0 5px;">可回款</label>
              </div>
            </div>
            <div class="row-fluid" style="border-bottom:1px solid #ddd; margin-bottom:20px">
              <div class="control-group span5">
                <label for="type" class="control-label">返点受益人</label>
                <div class="controls">
                  <input type="text" id="" placeholder="">
                </div>
              </div>
              <div class="control-group span5">
                <label for="type" class="control-label">联系方式</label>
                <div class="controls">
                  <input type="text" id="" placeholder="">
                </div>
              </div>
            </div>
            <div class="controls">
              <p>
                <a class="btn btn-danger" href="">生成合同相关说明</a>
              </p>
            </div>
          </form>
        </div>
<@c.js/>
<script type="text/javascript">
	var num = $("#cus_tel");
</script>
</body>
</html>