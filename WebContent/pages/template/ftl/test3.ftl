<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ftl Page</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

</head>

<body>
	这是Freemarker里的视图内容
	 <table>
      <tr><td>index</td><td>登录名</td><td>用户名</td><td>真实名字</td></tr>
      <#list key1 as item> 
       <tr><td>${item_index}</td><td>${item.loginCode?if_exists}</td><td>${item.userName?if_exists}</td><td><a href="show/${item.id?if_exists}.do">${item.realName?if_exists}</a></td</tr>
      </#list>
    </table> 
</body>
</html>  


