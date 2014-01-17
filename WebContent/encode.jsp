<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	int id = (Integer) request.getAttribute("id");
%>
l'id en attribut est : <%=id %>
	<form method="post" action="<%=request.getContextPath()%>/servlet/encode" enctype="multipart/form-data">
		<input type="hidden" name="id" value="<%=id %>"/>
		<table>
			<tr><td>Bitrate : </td><td>
			<select name="bitrate">
				<option>64 kbit/s</option>
				<option>96 kbit/s</option>
				<option>128 kbit/s</option>
				<option>160 kbit/s</option>
				<option selected="selected">192 kbit/s</option>
				<option>224 kbit/s</option>
				<option>256 kbit/s</option>
				<option>288 kbit/s</option>
				<option>320 kbit/s</option>
			</select></td></tr>
		
			 <tr><td>SamplingRate :</td><td>
			 <select name="samplingRate">
			 	<option>44 100 Hz</option>
			 </select></td></tr>
			 
			 <tr><td>Nombre de channels : </td><td>
			 <select name="channel">
			 	<option>1 (Mono)</option>
			 	<option selected="selected">2 (Stéréo)</option>
			 </select></td></tr>
			 
			 <tr><td>Formats :</td><td>
			 <select name="format">
			 	<option>ac3</option>
			 	<option>aac</option>
			 	<option>flac</option>
			 	<option selected="selected">mp3</option>
			 	<option>ogg</option>
			 	<option>wav</option>
			 </select></td></tr>
			 
			 <tr><td colspan="2" align="center"><input type="submit" value="Encoder" /></td></tr>
		 </table>
	</form>
	
</body>
</html>