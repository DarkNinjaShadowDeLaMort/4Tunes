<jsp:include page="tpl-top.jsp"></jsp:include>

<%
	String erreur = request.getParameter("erreur");

	String msg = "";
	if(erreur != null){
		if(erreur.equals("identification")){
			msg = "Erreur de nom d'utilisateur ou de mot de passe";
		}
		else if(erreur.equals("username")){
			msg = "Vous n'avez pas donné de nom d'utilisateur";
		}
		else if(erreur.equals("password")){
			msg = "Vous n'avez pas donné de mot de passe";
		}
		else if(erreur.equals("eMail")){
			msg = "Vous n'avez pas donné d'adresse eMail";
		}
		else if(erreur.equals("usernameExist")){
			msg = "Le nom d'utilisateur est déjà utilisé";
		}

	}
	
	if(!msg.isEmpty()) {
%>
<div class="alert alert-danger"><%=msg %></div>
<% } %>


<form class="form-signin" role="form" method="post" action="servlet/subscribe">
  <h2 class="form-signin-heading">Please sign on</h2>
  <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
  <input type="password" name="password" class="form-control" placeholder="Password" required>
  <input type="text" name="eMail" class="form-control" placeholder="Email address" required>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign on</button>
</form>

<jsp:include page="tpl-bottom.jsp"></jsp:include>