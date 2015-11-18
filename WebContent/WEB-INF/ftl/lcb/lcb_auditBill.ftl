<#import "../common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
<title>流程部开票审核</title>
 <@c.html/>
 </head>
 <body>
     <@c.head/>
	 <@c.left/>
    
        <div class="span10">
          <ul class="breadcrumb">
            <li><a href="#">合同管理</a> <span class="divider">/</span></li>
            <li class="active">开票审核</li>
          </ul>
          <div class="bs-docs-example" style="width:100%; overflow-x:scroll;white-space:nowrap;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>站点</th>
                  <th>业务员</th>
                  <th>合同编号</th>
                  <th>客户名称</th>
                  <th>签订日期</th>
                  <th>活动日期</th>
                  <th>合同金额(元)</th>
                  <th>返利金额(元)</th>
                  <th>执行金额(元)</th>
                  <th>是否归档</th>
                  <th>操作</th>                
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>111</td>
                  <td>右侧文字链</td>
                  <td>右侧文字链</td>
                  <td>大众</td>
                  <td>22</td>
                  <td>Jacob</td>
                  <td>50000元一天</td>
                  <td>22</td>
                  <td>Jacob</td>
                  <td>50000元一天</td>
                  <td><a href="">审核通过</a> <a href="">驳回</a> <a href="">查看</a></td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>Jacob</td>
                  <td>Thornton</td>
                  <td>@fat</td>
                  <td>1</td>
                  <td>Jacob</td>
                  <td>22</td>
                  <td>Jacob</td>
                  <td>50000元一天</td>
                  <td>Thornton</td>
                  <td>@fat</td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>Jacob</td>
                  <td>Thornton</td>
                  <td>22</td>
                  <td>Jacob</td>
                  <td>50000元一天</td>
                  <td>@fat</td>
                  <td>1</td>
                  <td>Jacob</td>
                  <td>Thornton</td>
                  <td>@fat</td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>Jacob</td>
                  <td>Thornton</td>
                  <td>@fat</td>
                  <td>22</td>
                  <td>Jacob</td>
                  <td>50000元一天</td>
                  <td>1</td>
                  <td>Jacob</td>
                  <td>Thornton</td>
                  <td>@fat</td>
                </tr>
              </tbody>
            </table>
          </div>        
        </div>
      </div>
    </div>
<@c.js/>
    <script type="text/javascript">
      $(".form_datetime").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒 
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 
        language: 'zh-CN', //汉化 
        autoclose:true //选择日期后自动关闭
      });
    </script>
  </body>