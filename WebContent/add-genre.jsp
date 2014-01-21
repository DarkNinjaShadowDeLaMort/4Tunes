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
<jsp:include page="tpl-bottom.jsp"></jsp:include>