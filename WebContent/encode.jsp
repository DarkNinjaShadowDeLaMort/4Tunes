<jsp:include page="tpl-top.jsp"></jsp:include>
<%
	int id = (Integer) request.getAttribute("id");
%>

<div class="jumbotron">
	<a href="<%=request.getContextPath()%>/servlet/userAccount">Revenir au panneau principal</a>
</div>

<form class="form-signin" role="form" method="post" action="<%=request.getContextPath()%>/servlet/encode" enctype="multipart/form-data">
  <h2 class="form-signin-heading">Convert song</h2>
  <input type="hidden" name="id" value="<%=id %>"/>
  <select name="bitrate" class="form-control">
		<option>64 kbit/s</option>
		<option>96 kbit/s</option>
		<option>128 kbit/s</option>
		<option>160 kbit/s</option>
		<option selected="selected">192 kbit/s</option>
		<option>224 kbit/s</option>
		<option>256 kbit/s</option>
		<option>288 kbit/s</option>
		<option>320 kbit/s</option>
	</select>
	<select name="samplingRate" class="form-control">
	 	<option>44 100 Hz</option>
	 </select>
	 <select name="channel" class="form-control">
	 	<option>1 (Mono)</option>
	 	<option selected="selected">2 (Stéréo)</option>
	 </select>
	 <select name="format" class="form-control">
	 	<option>ac3</option>
	 	<option>aac</option>
	 	<option>flac</option>
	 	<option selected="selected">mp3</option>
	 	<option>ogg</option>
	 	<option>wav</option>
	 </select>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Convert Song</button>
</form>
	
<jsp:include page="tpl-bottom.jsp"></jsp:include>