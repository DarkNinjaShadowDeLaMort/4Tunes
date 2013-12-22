<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*, java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	List <Genre> listeGenre = (List <Genre>) request.getAttribute("listeGenre");
	String erreur = request.getParameter("erreur");
	if(erreur != null && ! erreur.equals("")){
		if(erreur.equals("nom")){
			out.write("Vous n'avez pas donné de nom");
		}
	}
%>

<form method="post" action="<%=request.getContextPath()%>/servlet/addGenre">
	<input type="text" name="genre" /> <br/>
	<select name="pere">
		<option>Aucun</option>
		<%
			if(listeGenre != null && listeGenre.size() > 0){
				for(int i=0; i<listeGenre.size(); i++){
					out.write("<option>"+listeGenre.get(i).getNom()+"</option>");
				}
			}
		%>
	</select> 
	<input type="submit" value="OK" />
</form>
</body>
</html>