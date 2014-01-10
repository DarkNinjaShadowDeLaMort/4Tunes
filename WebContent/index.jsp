<jsp:include page="tpl-top.jsp"></jsp:include>

<%
	String subscribe = request.getParameter("subscribe");
	if(subscribe != null){
		if(subscribe.equals("ok")){
			out.write("Inscription ok");
		}
	}
	
	
%>
	<form class="form-signin" role="form" method="post" action="servlet/login">
		  <h2 class="form-signin-heading">Please sign in</h2>
		  <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
		  <input type="password" name="password" class="form-control" placeholder="Password" required>
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		  <a href="<%=request.getContextPath()%>/subscribe.jsp">Subscribe</a>
	</form>



<jsp:include page="tpl-bottom.jsp"></jsp:include>