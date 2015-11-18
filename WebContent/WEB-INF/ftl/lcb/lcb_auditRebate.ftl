<#import "../common_con.ftl" as c/>
<!DOCTYPE html>
<html>
<head>
<title>流程部返利审核</title>
 <@c.html/>
 </head>
 <body>
     <@c.head/>
	 <@c.left/>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="#" class="brand"><img src="images/vice-logo.png" alt=""></a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">登录人：<a href="" class="navbar-link">吴宇洋</a></p>
            <ul class="nav">
              <li class="active"><a href="#">首页</a></li>
              <li><a href="#">人事模块</a></li>
              <li><a href="#">财务模块</a></li>
              <li><a href="#">合同模块</a></li>
              <li><a href="#">系统设置</a></li>
            </ul>
            <form class="navbar-search pull-left" action="">
              <input type="text" class="search-query span2" placeholder="Search">
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">业务员</li>
              <li><a href="#">我的合同</a></li>
              <li><a href="#">新合同</a></li>
              <li><a href="#">执行中<span class="badge badge-important">2</span></a></li>
              <li><a href="#">查看广告位</a></li>
              <li class="divider"></li>
              <li class="nav-header">部门经理</li>
              <li><a href="#">查看合同</a></li>
              <li><a href="#">待审核</a></li>
              <li><a href="#">新合同</a></li>
              <li><a href="#">查看广告位</a></li>
              <li class="divider"></li>
              <li class="nav-header">流程部</li>
              <li><a href="#">查看合同</a></li>
              <li class="active"><a href="#">待归档</a></li>
              <li><a href="#">开票审核</a></li>
              <li><a href="#">返利审核</a></li>
              <li><a href="#">查看广告排期</a></li>
              <li class="divider"></li>
              <li class="nav-header">发票管理</li>
              <li><a href="#">待发票</a></li>
              <li><a href="#">待返利</a></li>
              <li class="nav-header">严总</li>
              <li><a href="#">新广告</a></li>
              <li><a href="#">排期管理</a></li>
              <li><a href="#">添加排期</a></li>  
              <li class="nav-header">广告执行人员</li>
              <li><a href="#">查看广告</a></li>
            </ul>
          </div>
        </div>
        <div class="span10">
          <ul class="breadcrumb">
            <li><a href="#">合同管理</a> <span class="divider">/</span></li>
            <li class="active">返利审核</li>
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
                  <th>合同金额(元)</th>
                  <th>返利金额(元)</th>
                  <th>执行金额(元)</th>
                  <th>是否归档</th>
                  <th>回款日期</th>
                  <th>回款金额</th>
                  <th>申请返利金额</th>
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
                  <td>22</td>
                  <td>Jacob</td>
                  <td>50000元一天</td>
                  <td><a href="">审核通过</a> <a href="">查看申请</a> <a href="">查看流程</a></td>
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
                  <td>22</td>
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
 </html>