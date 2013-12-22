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
	List <Artiste> listeArtiste = (List <Artiste>) request.getAttribute("listeArtiste");

	String erreur = request.getParameter("erreur");
	if(erreur != null && ! erreur.equals("")){
		if(erreur.equals("nom")){
			out.write("Vous n'avez pas donné de nom");
		}
		else if(erreur.equals("date")){
			out.write("L'année n'est pas sous la bonne forme");
		}
		else if(erreur.equals("artiste")){
			out.write("L'artiste doit être défini");
		}
	}
%>

<form method="post" action="<%=request.getContextPath()%>/servlet/addAlbum" enctype="multipart/form-data">
	Nom : <br/>
	<input type="text" name="nom" /> <br/>
	Pochette : <br/>
	<input type="file" name="pochette" /> <br/>
	Date : <br/>
	<input type="text" name="date" /> <br/>
	Artiste : <br/>
	<select name="artiste">
		<option>Aucun</option>
		<%
			if(listeArtiste != null){
				for(int i=0; i<listeArtiste.size(); i++){
					out.write("<option>"+listeArtiste.get(i).getNom()+"</option>\n");
				}
			}
		
		%>
	</select> <br/>
	<input type="submit" value="Ok" />
</form>

</body>
</html>