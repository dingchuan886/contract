<#import "common_con.ftl" as c>
	<head>
		<title>Login</title>
	</head>
	<body>
		<form id="login" action="contract">
				<#list userList as user>
					${user.user_name}
					<#if user.dept_code=='1'>
						总经办 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='2'>
						广告销售部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='3'>
						互动部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='4'>
						财务部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='5'>
						编辑部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='6'>
						技术部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='7'>
						设计部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='8'>
						品牌战略部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='9'>
						商务扩展部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='10'>
						网络运营部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='11'>
						人事部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='12'>
						产品部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					<#if user.dept_code=='12'>
						广告销售二部 <a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if>
					
					<#if user.dept_code=='18'>
						流程部<a href="/contract/contract?userid=${user.user_code}&username=${user.user_name}">登录</a>
					</#if><br />
				</#list>
		</form>
	</body>


