<jsp:include page="tpl-top.jsp"></jsp:include>

<%
	String erreur = request.getParameter("erreur");


	if(erreur != null){
		if(erreur.equals("identification")){
			out.write("Erreur de nom d'utilisateur ou de mot de passe");
		}
		else if(erreur.equals("username")){
			out.write("Vous n'avez pas donn� de nom d'utilisateur");
		}
		else if(erreur.equals("password")){
			out.write("Vous n'avez pas donn� de mot de passe");
		}
		else if(erreur.equals("eMail")){
			out.write("Vous n'avez pas donn� d'adresse eMail");
		}
		else if(erreur.equals("usernameExist")){
			out.write("Le nom d'utilisateur est d�j� utilis�");
		}

	}
%>
<h1>Subscribe</h1>
<form method="post" action="servlet/subscribe">
	Username <br/>
	<input type="text" name="username" /> <br/>
	Password <br/>
	<input type="text" name="password" /> <br/>
	eMail Adress : <br/>
	<input type="text" name="eMail" /> <br/>
	<input type="submit" value="OK" />
</form>

<jsp:include page="tpl-bottom.jsp"></jsp:include>