<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!doctype html>
<html>
 <head>
 	<meta charset="utf-8">
  	<link href="/vote/css/1.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
      	
      	 function vote(optionid,voteid){
      		document.getElementById('myform').action="/vote/servlet/voteservlet?type1=vote&"
      			+"optionid="+optionid+"&voteid="+voteid;
      		document.getElementById('myform').submit();
      	}
      	
	</script>  
  </head>
  
  <body>
	<form action="/vote/servlet/voteservlet?type1=vote" id="myform" method="post">
		<div class="center" style="width:600px">
		  <div class="commentsHead">投票选项</div>
		  <c:forEach items="${requestScope.options}"  var="option">	
						<div class="commentContent">
							${option.content}
							<a href="javascript:void(0);" onclick="vote(${option.getOptionid()},${param.id});">投票</a>	
						</div>	
		  </c:forEach>
		</div> 
	</form>   
  </body>

  
</html>
