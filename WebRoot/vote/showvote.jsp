<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!doctype html>
<html>

  <body>
  	<div class="center" style="width:800px;margin-top:30px;">
		<table width="800" border="0">
			<tbody>
				<tr><td align="center"  class="newsCaption">${requestScope.vote.caption}</td>
				<tr><td align="center" height="50">作者：${requestScope.vote.author}&nbsp;	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						</td>
				</tr><tr><td height="30"><hr></td></tr>
				<tr>
					<td>${requestScope.vote.content}</td>				
				</tr>
				<tr><td height="30"><hr></td></tr>
			</tbody>
		</table>
	</div>

	<jsp:include page='<%="/servlet/voteservlet?type1=showoptions"%>' flush="true" >
		<jsp:param name="id" value="${requestScope.vote.voteid}" />
	</jsp:include>	
  </body>
</html>
