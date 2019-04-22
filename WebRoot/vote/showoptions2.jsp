<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!doctype html>
<html>
 <head>
 	<meta charset="utf-8">
  	<link href="/vote/css/1.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
	<form action="/vote/servlet/voteservlet?type1=votes" id="myform" method="post">
		<div class="center" style="width:600px">
		  <div class="commentsHead">投票选项</div>
		  <c:forEach items="${requestScope.options}"  var="option">	
						<div class="commentContent">
							${option.content}
							<input type="checkbox"	name="${option.getOptionid()}" value="${option.getOptionid()}"/>
						</div>	
		  </c:forEach>
		  <input type="hidden" name="voteid" value="${param.id}" />
		  <input type="submit" value="投票" />
		</div> 
	</form>   
  </body>

  
</html>
