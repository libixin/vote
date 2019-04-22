<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!doctype html>
<html>
 <head>
 	<link href="/vote/css/1.css" rel="stylesheet" type="text/css">
 	<meta charset="utf-8"> 
 	<script type="text/javascript"> 
 		function getOnePage(type){
    	  	var url1;
    	  	var page=document.getElementById("page");
    	  	var pageSize=document.getElementById("pageSize");
    	  	var totalPageCount=document.getElementById("totalPageCount");
			
    	  	pageValue=parseInt(page.value);
    	  	if(type=="pre"){
    	  		pageValue-=1;
    	  		page.value=pageValue.toString();
    	  	}else if(type=="next"){
    	  		pageValue+=1;
    	  		page.value=pageValue.toString();
    	  	}
    	  	//提交
    	  	document.getElementById('myform').submit();
      	}
	</script>  
  </head>
  
  <body>
  	  		
		<div>
			<ul>
			<c:forEach items="${requestScope.votes}"  var="vote"> 
				<li><a href="/vote/servlet/voteservlet?type1=showvote&id=${vote.voteid}">${vote.caption}</a></li>
			</c:forEach>
			</ul>
		</div>
		<div>
			<div class="center" style="width:150px;">
				<c:if test="${requestScope.pageInformation.page > 1}">
					<td><a href="javascript:void(0);" onclick="getOnePage('pre');">上一页</a></td>  
				</c:if>					
				<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPageCount}">
					&nbsp; &nbsp;<a href="javascript:void(0);" onclick="getOnePage('next');">下一页</a>
				</c:if>	
			</div>			
		</div>
	<form action="/vote/servlet/voteservlet?type1=showvotes" id="myform" method="post">
 		<input type="hidden" name="page" id="page" value="${requestScope.pageInformation.page}">
		<input type="hidden" name="pageSize" id="pageSize" value="${requestScope.pageInformation.pageSize}">
 		<input type="hidden" name="totalPageCount" id="totalPageCount" value="${requestScope.pageInformation.totalPageCount}">
		<input type="hidden" name="allRecordCount" id="allRecordCount" value="${requestScope.pageInformation.allRecordCount}">
 	</form>
  </body>
</html>
