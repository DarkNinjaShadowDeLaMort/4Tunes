<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*, java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	List <Chanson> listeChanson = (List <Chanson>) request.getAttribute("listeChanson");
%>

<%!
public String getArtiste(Chanson c) {
	if(c.getArtiste() != null){
		return c.getArtiste().getNom();
	}
	else{
		return "inconnu";
	}
}

public String getAlbum(Chanson c) {
	if(c.getAlbum() != null){
		return c.getAlbum().getNom();
	}
	else{
		return "inconnu";
	}
}
%>

<h1>Connecté</h1>

<a href="<%=request.getContextPath()%>/servlet/logout">Logout</a> <br/>

<a href="<%=request.getContextPath()%>/servlet/addArtiste">Créer Artiste</a> <br/>
<a href="<%=request.getContextPath()%>/servlet/addAlbum">Créer Album</a> <br/>
<a href="<%=request.getContextPath()%>/servlet/addChanson">Créer chanson</a> <br/>

<table width="70%" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<th>Artiste</th>
		<th>Titre</th>
		<th>Album</th>
		<th>Télécharger</th>
		<th>Écouter</th>
	</tr>
	
	<%
		if(listeChanson != null){
			for(int i=0; i<listeChanson.size(); i++){
				Chanson c = listeChanson.get(i);
					out.write("<tr><td>"+getArtiste(c)+"</td><td>"+c.getTitre()+"</td><td>"+getAlbum(c)+"</td><td><a href=\""+request.getContextPath()+"/servlet/download?id="+c.getId()+"\">Télécharger</a></td><td><audio src=\""+request.getContextPath()+"/servlet/download?id="+c.getId()+"\"  controls=\"controls\"></td></tr>\n");
			}
		}
	
	
	%>
</table>

</body>
</html>