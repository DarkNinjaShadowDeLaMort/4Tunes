<%@ page import="model.*, java.util.List" %>
<jsp:include page="tpl-top.jsp"></jsp:include>

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
<form class="form-signin" role="form" method="post" action="<%=request.getContextPath()%>/servlet/addChanson" enctype="multipart/form-data">
  <h2 class="form-signin-heading">Add Song</h2>
  <input type="text" name="titre" class="form-control" placeholder="Title" required autofocus>
  <input type="file" name="fichier" class="form-control">
  <select name="artiste" class="form-control">
  	<option>Aucun</option>
		<%
			if(listeArtiste != null){
				for(int i=0; i<listeArtiste.size(); i++){
					out.write("<option value=\""+listeArtiste.get(i).getId()+"\">"+listeArtiste.get(i).getNom()+"</option>\n");
				}
			}
		%>
  
  </select>
  <select name="album" class="form-control">
		<option>Aucun</option>
		<%
			if(listeAlbum != null){
				for(int i=0; i<listeAlbum.size(); i++){
					out.write("<option value=\""+listeAlbum.get(i).getId()+"\">"+listeAlbum.get(i).getNom()+"</option>\n");
				}
			}
		%>
	</select>
  <select name="genre" class="form-control">
  	<option>Aucun</option>
		<%
			if(listeGenre != null){
				for(int i=0; i<listeGenre.size(); i++){
					out.write("<option value=\""+listeGenre.get(i).getId()+"\">"+listeGenre.get(i).getNom()+"</option>\n");
				}
			}
		%>
  </select>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Add Song</button>
</form>






<!--
<form method="post" action="<%=request.getContextPath()%>/servlet/addChanson" enctype="multipart/form-data">
	Titre <br/>
	<input type="text" name="titre" /> <br/>
	Fichier <br/>
	<input type="file" name="fichier" accept="audio/*"/> <br/>
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
				for(int i=0; i<listeGenre.size(); i++){
					out.write("<option>"+listeGenre.get(i).getNom()+"</option>\n");
				}
			}
		%>
	</select><br/>
	<input type="submit" value="Ok" />
</form>-->





<jsp:include page="tpl-bottom.jsp"></jsp:include>