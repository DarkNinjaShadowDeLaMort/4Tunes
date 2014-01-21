<%@ page import="model.*, java.util.List, java.text.DecimalFormat, java.util.concurrent.TimeUnit" %>
<jsp:include page="tpl-top.jsp"></jsp:include>

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

public String getDuration(Chanson c){
	double duration = c.getDuree();
	int seconds = (int) duration;
	
	long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
	long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);
	return minute+":"+second; 
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
		<th>Durée</th>
		<th>Nom du fichier</th>
		<th>Télécharger</th>
		<th>Écouter</th>
		<th>Convertir</th>
	</tr>

	<%
		if(listeChanson != null){
			for(int i=0; i<listeChanson.size(); i++){
				Chanson c = listeChanson.get(i);
					out.write("\n<tr>\n"+
					"	<td>"+getArtiste(c)+"</td>\n"+
					"	<td>"+c.getTitre()+"</td>\n"+
					"	<td>"+getAlbum(c)+"</td>\n"+
					"	<td>"+getDuration(c)+"</td>\n"+
					"	<td>"+c.getUrl()+"</td>\n"+
					"	<td><a href=\""+request.getContextPath()+"/servlet/download?id="+c.getId()+"\">Télécharger</a></td>\n"+
					"	<td><audio src=\""+request.getContextPath()+"/servlet/download?id="+c.getId()+"\"  controls=\"controls\" preload=\"none\"></audio></td>\n"+
					"	<td><a href=\""+request.getContextPath()+"/servlet/encode?id="+c.getId()+"\">Convertir</a></td>\n"+
					"</tr>\n");
			}
		}
	
	
	%>
</table>

<jsp:include page="tpl-bottom.jsp"></jsp:include>