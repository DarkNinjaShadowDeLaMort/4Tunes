<%@ page import="model.*, java.util.List"%>
<jsp:include page="tpl-top.jsp"></jsp:include>

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

<div class="jumbotron">
	<a href="<%=request.getContextPath()%>/servlet/userAccount">Revenir au panneau principal</a>
</div>

<form class="form-signin" role="form" method="post" action="addAlbum" enctype="multipart/form-data">
  <h2 class="form-signin-heading">Add Album</h2>
  <input type="text" name="nom" class="form-control" placeholder="Name" required autofocus>
  <input type="file" name="pochette" class="form-control" placeholder="Pochette">
  <input type="number" name="date" class="form-control" placeholder="Date">
  <select name="artiste" class="form-control">
  	<option>Aucun</option>
		<%
			if(listeArtiste != null){
				for(int i=0; i<listeArtiste.size(); i++){
					out.write("<option>"+listeArtiste.get(i).getNom()+"</option>\n");
				}
			}
		%>
  
  </select>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Add album</button>
</form>


<!--<form method="post" action="<%=request.getContextPath()%>/servlet/addAlbum" enctype="multipart/form-data">
	Nom : <br/>
	<input type="text" name="nom" /> <br/>
	Pochette : <br/>te)){
					response.sendRedirect(request.getContextPath()+"/servlet/addAlbum?erreur=date");
					return;
				}
				else{
					iDate = Integer.valueOf(date);
				}
			}
			if(artiste == null || artiste
	<input type="file" name="pochette" /> <br/>
	Date : <br/>
	<input type="text" name="date" /> <br/>
	Artiste : <br/>
	<select name="artiste" class="form-control">
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
</form>-->

<jsp:include page="tpl-bottom.jsp"></jsp:include>