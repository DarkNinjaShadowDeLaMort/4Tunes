<%@ page import="model.*, java.util.List"%>
<jsp:include page="tpl-top.jsp"></jsp:include>

<%
	List <Genre> listeGenre = (List <Genre>) request.getAttribute("listeGenre");
	String erreur = request.getParameter("erreur");
	if(erreur != null && ! erreur.equals("")){
		if(erreur.equals("nom")){
			out.write("Vous n'avez pas donné de nom");
		}
	}
%>

<form class="form-signin" role="form" method="post" action="<%=request.getContextPath()%>/servlet/addGenre">
  <h2 class="form-signin-heading">Add Genre</h2>
  <input type="text" name="genre" class="form-control" placeholder="Name" required autofocus>
  <select name="pere" class="form-control">
  	<option>Aucun</option>
		<%
			List <Artiste> listeArtiste = (List <Artiste>) request.getAttribute("listeArtiste");
			if(listeArtiste != null){
				for(int i=0; i<listeArtiste.size(); i++){
					out.write("<option>"+listeArtiste.get(i).getNom()+"</option>\n");
				}
			}
		%>
  
  </select>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Add Genre</button>
</form>


<!-- <form method="post" action="<%=request.getContextPath()%>/servlet/addGenre">
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
</form> -->



<jsp:include page="tpl-bottom.jsp"></jsp:include>