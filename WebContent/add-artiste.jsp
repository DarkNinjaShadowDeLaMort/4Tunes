<jsp:include page="tpl-top.jsp"></jsp:include>

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

<jsp:include page="tpl-bottom.jsp"></jsp:include>