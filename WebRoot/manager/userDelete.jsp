<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
 <head><meta charset="utf-8">
 	<link href="/news1/css/1.css" rel="stylesheet" type="text/css">
 	<script type="text/javascript">
	  function checkAll1(obj){
	  	var checkboxs= document.getElementsByName("checkbox1");
	  	for (var i = 0; i < checkboxs.length; i++) 
	  		checkboxs[i].checked =obj.checked;	  
	  }
	  
	  function deleteUsers(){
	  	var checkboxs= document.getElementsByName("checkbox1"); 
	  	var ids="";
	  	////拼接id为 ：1,2,
	   	for(i=0;i<checkboxs.length;i++){
        	if(checkboxs[i].checked == true)
            	ids+=checkboxs[i].value+",";
        }
		if(ids.length<1){
			alert("请选择至少一个需删除的元素！");
			return false;//阻止提交
		}
		ids=ids.substring(0,ids.length-1);//删除最后的逗号
		ids1=document.getElementById("ids"); 
	  	ids1.value=ids;
	  	//提交
    	document.getElementById('myform').submit();
	  }

      function getOnePage(type,orderFieldName){
    	  	var url1;
    	  	var page=document.getElementById("page");
    	  	var pageSize=document.getElementById("pageSize");
    	  	var totalPageCount=document.getElementById("totalPageCount");
    	  	
  			var order=document.getElementById("order");
  			var orderField=document.getElementById("orderField");
			
			if(orderFieldName!=""){//切换排序
				orderField.value=orderFieldName;//设置排序字段名
				if(order.value == "asc")//切换排序
					order.value="desc";
				else
					order.value="asc";		
				
				page.value=1;//排序后从第一页开始显示					
			}
			
    	  	pageValue=parseInt(page.value);
    	  	if(type=="first")
    	  		page.value="1";
    	  	else if(type=="pre"){
    	  		pageValue-=1;
    	  		page.value=pageValue.toString();
    	  	}else if(type=="next"){
    	  		pageValue+=1;
    	  		page.value=pageValue.toString();
    	  	}else if(type=="last"){
    	  		page.value=totalPageCount.value;
    	  	}
    	  	//提交
    	  	document.getElementById('myform').submit();
      	}
	</script>  
  </head>
  
  <body>
  	<form action="/vote/servlet/userservlet?type1=delete" id="myform" method="post">
  	 <table align="center" class="tableDefault"  style="width:500px;">
	    <tr style="background-color:#FFACAC;">
	      <td><input id="checkboxAll" type='checkbox' onchange="checkAll1(this);"></td>
	      <td><a href='javascript:void(0);' onclick="getOnePage('','userId');">Id</a></td>
	      <td>用户类型</td><td>用户名</td><td>帐号可用性</td>
	    </tr>	    
	    <c:forEach items="${requestScope.users}"  var="user">      
	   		<tr class="trDefault">
	   			<td><input name="checkbox1"  type="checkbox" value="${user.userId}"></td>
		   		<td>${user.userId}</td>     
		   		<td>${user.type}</td>	
		   		<td>${user.name}</td>     
		   		
		   		<td>${user.enable}</td>  
		   	</tr>
		</c:forEach> 	    
	</table>
	 <table align="center" class="tableDefault">  
	   	<tr>	
	   		<td width="200"><input type="button"  value="  删 除 所 选 项  " onclick="deleteUsers();"></td>		
			<c:if test="${requestScope.pageInformation.page > 1}">
				<td><a href="javascript:void(0);" onclick="getOnePage('first','');">首页</a></td>  
			</c:if>
			
			<c:if test="${requestScope.pageInformation.page > 1}">
				<td><a href="javascript:void(0);" onclick="getOnePage('pre','');">上一页</a></td>  
			</c:if>			 
			
			<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPageCount}">
				<td><a href="javascript:void(0);" onclick="getOnePage('next','');">下一页</a></td>
			</c:if>	  			
			<c:if test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPageCount}">
				<td><a href="javascript:void(0);" onclick="getOnePage('last','');">尾页</a></td>
			</c:if>
			<td>共${requestScope.pageInformation.totalPageCount}页</td>	   		
		</tr>    
	 </table>
	 	<input type="hidden" name="page" id="page" value="${requestScope.pageInformation.page}">
		<input type="hidden" name="pageSize" id="pageSize" value="${requestScope.pageInformation.pageSize}">
	 	<input type="hidden" name="totalPageCount" id="totalPageCount" value="${requestScope.pageInformation.totalPageCount}">
		<input type="hidden" name="allRecordCount" id="allRecordCount" value="${requestScope.pageInformation.allRecordCount}">
	 	<input type="hidden" name="orderField" id="orderField" value="${requestScope.pageInformation.orderField}">
		<input type="hidden" name="order" id="order" value="${requestScope.pageInformation.order}">
	 	<input type="hidden" name="ids" id="ids" value="${requestScope.pageInformation.ids}">
		<input type="hidden" name="searchSql" id="searchSql" value="${requestScope.pageInformation.searchSql}">
	 	<input type="hidden" name="result" id="result" value="${requestScope.pageInformation.result}">
   </form>
  </body>
</html>