<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*, java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	List <Artiste> listeArtiste = (List <Artiste>) request.getAttribute("listeArtiste");
	List <Album> listeAlbum = (List <Album>) request.getAttribute("listeAlbum");
	List <Genre> listeGenre = (List <Genre>) request.getAttribute("listeGenre");

	String erreur = request.getParameter("erreur");
	if(erreur != null && ! erreur.equals("")){
		if(erreur.equals("titre")){
			out.write("Vous n'avez pas donné de titre");
		}
		else if(erreur.equals("fichier")){
			out.write("Vous n'avez pas donné de fichier");
		}
	}
%>

<form method="post" action="<%=request.getContextPath()%>/servlet/addChanson" enctype="multipart/form-data">
	Titre <br/>
	<input type="text" name="titre" /> <br/>
	Fichier <br/>
	<input type="file" name="fichier" /> <br/>
	Artiste <br/>
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
	Album <br/>
	<select name="album">
		<option>Aucun</option>
		<%
			if(listeAlbum != null){
				for(int i=0; i<listeArtiste.size(); i++){
					out.write("<option>"+listeAlbum.get(i).getNom()+"</option>\n");
				}
			}
		%>
	</select> <br/>
	Genre <br/>
	<select name="genre">
		<option>Aucun</option>
		<%
			if(listeGenre != null){
				for(int i=0; i<listeArtiste.size(); i++){
					out.write("<option>"+listeGenre.get(i).getNom()+"</option>\n");
				}
			}
		%>
	</select><br/>
	<input type="submit" value="Ok" />
</form>
</body>
</html>