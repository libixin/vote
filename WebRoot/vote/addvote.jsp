<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html> 

  <body>
  
	   <form action="/vote/servlet/voteservlet?type1=add" method="post" onsubmit="return submit1()">
	   <div class="center" style="width:600px;margin-top:40px">
		<table  border="0" align="center">
			<tbody>
				<tr height="30">
					<td></td><td>添加投票</td>
				</tr>
						
				<tr height="30">
					<td  align="right">作者：</td>
					<td align="left"><input type="text" name="author" id="author">
				</tr>
				<tr height="30">
					<td  align="right">标题：</td>
					<td align="left"><input type="text" name="caption" id="caption">
				</tr>
				<tr height="30">
					<td  align="right">内容：</td>
					<td align="left"><input type="text" name="content" id="content">
				</tr>
				
				<tr height="30">
					<td  align="right">发布时间：</td>
					<td align="left"><input type="datetime-local" name="publishtime" id=publishtime>
				</tr>
				
				<tr height="30">
					<td  align="right">截至时间：</td>
					<td align="left"><input type="datetime-local" name="finaltime" id="finaltime">
				</tr>
				
				<tr height="30">
					<td align="right">投票类型：</td>
					<td><select name="votetype">
							<option value="all">全部</option>
							<option value="com">社会</option>
							<option value="music">音乐</option>
					</select></td>
				</tr>		
				
				<tr height="30">
					<td align="right">选择类型：</td>
					<td><select name="selecttype">
							<option value="single">单选</option>
							<option value="multi">多选</option>
					</select></td>
				</tr>	
				
				<tr height="30">
					<td align="right">发布：</td>
					<td><select name="enable">
							<option value="yes">是</option>
							<option value="no">否</option>
						</select></td>
				</tr>

				<tr height="30">
					<td  align="right">1：</td>
					<td align="left"><input type="text" name="option1" id="option1">
				</tr>
				
				<tr height="30">
					<td  align="right">2：</td>
					<td align="left"><input type="text" name="option2" id="option2">
				</tr>

				<tr height="30">
					<td  align="right">3：</td>
					<td align="left"><input type="text" name="option3" id="option3">
				</tr>
				
				<tr height="30">
					<td  align="right">4：</td>
					<td align="left"><input type="text" name="option4" id="option4">
				</tr>

				<tr height="30">
					<td  align="right">5：</td>
					<td align="left"><input type="text" name="option5" id="option5">
				</tr>

				<tr height="30">
					<td></td><td><input type="submit" value=" 发    布   "/></td>
				</tr>
			</tbody>
		</table>
	  </div>
	</form>
  </body>
  
</html>
