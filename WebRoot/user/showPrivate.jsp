<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
 <head>
 	<meta charset="utf-8"></head>
  <body>
    
	<table width="600" border="0" align="center">
		<tbody>
			<tr><td colspan="2"  align="center">个人信息：</td></tr>
			<tr><td  align="right" width="150">用户类型：</td>
				<td>${ sessionScope.user.type}</td>
			</tr>			
			<tr><td  align="right" >用户名：</td>
				<td>${ sessionScope.user.name}</td>
			</tr>			
			<tr><td align="right">头像：</td>	
				<td><img src="${ sessionScope.user.headIconUrl}" height="200"/></td></tr>
				<tr><td align="right">性别：</td>	
					<td>${ requestScope.userinformation.sex}</td></tr>
				<tr><td align="right">简介：</td>
					<td>${ requestScope.userinformation.intro}</td>
				</tr> 			
		</tbody>
	</table>
	<table width="600" border="0" align="center">
	<tr><td><a href="/vote/servlet/userservlet?type1=changePrivate1">修改信息</a></td></tr>
	<tr><td><a href="/vote/user/manage/changePassword.jsp">修改密码</a></td></tr>
	</table>				   
  </body>
</html>
