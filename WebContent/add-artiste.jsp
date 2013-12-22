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
	if(erreur != null && ! erreur.equals("")){
		if(erreur.equals("nom")){
			out.write("Vous n'avez pas donné de nom");
		}
		else if(erreur.equals("nomExist")){
			out.write("L'Artiste existe déjà");
		}
	}
%>

<form method="post" action="<%=request.getContextPath()%>/servlet/addArtiste">
	Nom : <br/>
	<input type="text" name="nom" /> <br/>
	<input type="submit" value="Ok" />
</form>
</body>
</html>