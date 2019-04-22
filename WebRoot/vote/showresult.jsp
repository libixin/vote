<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!doctype html>
<html>
 <head>
 	<meta charset="utf-8">
  	<link href="/vote/css/1.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
		<div class="center" style="width:600px">
		  <div class="commentsHead">投票选项</div>
		  <c:forEach items="${requestScope.options}"  var="option">	
						<div class="commentContent">
							${option.content}  
							<c:forEach items="${requestScope.showresults}"  var="showresult">
								<c:if test="${(showresult.optionid==option.getOptionid())}">
									共${showresult.votes}票
								</c:if>
							</c:forEach>
						</div>	
		  </c:forEach>
		</div> 
  </body>

  
</html>
