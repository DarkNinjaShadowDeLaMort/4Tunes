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
	String erreur = request.getParameter("erreur");


	if(erreur != null){
		if(erreur.equals("identification")){
			out.write("Erreur de nom d'utilisateur ou de mot de passe");
		}
		else if(erreur.equals("username")){
			out.write("Vous n'avez pas donné de nom d'utilisateur");
		}
		else if(erreur.equals("password")){
			out.write("Vous n'avez pas donné de mot de passe");
		}
		else if(erreur.equals("eMail")){
			out.write("Vous n'avez pas donné d'adresse eMail");
		}
		else if(erreur.equals("usernameExist")){
			out.write("Le nom d'utilisateur est déjà utilisé");
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

</body>
</html>