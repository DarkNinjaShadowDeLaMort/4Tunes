<%@ page import="model.*, java.util.List, java.text.DecimalFormat, java.util.concurrent.TimeUnit" %>
<jsp:include page="tpl-top.jsp"></jsp:include>

<%
	List<Chanson> listeChanson = (List <Chanson>) request.getAttribute("listeChanson");
	List<Artiste> listeArtiste = (List <Artiste>) request.getAttribute("listeArtiste");
	List<Album> listeAlbum = (List <Album>) request.getAttribute("listeAlbum");

	int idArtSel = ((Integer) request.getAttribute("selectedArtiste")).intValue();
	int idAlbSel = ((Integer) request.getAttribute("selectedAlbum")).intValue();
	
%>

<%!
public String getArtiste(Chanson c) {
	if(c.getArtiste() != null){
		return c.getArtiste().getNom();
	}
	else{
		return "unknown";
	}
}

public String getAlbum(Chanson c, String contextPath) {

	if(c.getAlbum() != null){
		return "<a href="+contextPath+"/servlet/image?id="+c.getAlbum().getId()+">"+c.getAlbum().getNom()+"</a>";
	}
	else{
		return "unknown";
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


<div class="btn-group">
	<a class="btn btn-danger" href="<%=request.getContextPath()%>/servlet/logout"><span class="glyphicon glyphicon-off"></span> Logout</a>       
	<a class="btn btn-primary" href="<%=request.getContextPath()%>/servlet/addArtiste"><span class="glyphicon glyphicon-plus"></span> Add new Singer</a>  
	<a class="btn btn-primary" href="<%=request.getContextPath()%>/servlet/addAlbum"><span class="glyphicon glyphicon-plus"></span> Add new Album</a>  
	<a class="btn btn-primary" href="<%=request.getContextPath()%>/servlet/addChanson"><span class="glyphicon glyphicon-plus"></span> Add new Song</a>  
</div>

<div>
	<form method="post" action="<%=request.getContextPath()%>/servlet/userAccount">
		<select name="artiste" class="form-control" onchange="this.form.submit()">
			<option value="-1">Tous les Artistes</option>
		<% for(Artiste artiste : listeArtiste) { %>
			<option value="<%=artiste.getId() %>" <%if(artiste.getId()==idArtSel) {%>selected="selected"<%}%>><%=artiste.getNom() %></option>
		<% } %>
		</select>
		<select name="album" class="form-control" onchange="this.form.submit()">
		  <option value="-1">Tous les Album</option>
		<% for(Album album : listeAlbum) { %>
			<option value="<%=album.getId() %>" <%if(album.getId()==idAlbSel) {%>selected="selected"<%}%>><%=album.getNom() %></option>
		<% } %>
		</select>
	</form>
</div>

<table class="table table-striped">
	<thead>
		<th>Artist</th>
		<th>Title</th>
		<th>Album</th>
		<th>Duration</th>
		<th>File name</th>
		<th>Download</th>
		<th>Listen</th>
		<th>Convert</th>
		<th>Delete</th>
	</thead>

	<%
		if(listeChanson != null){
			for(int i=0; i<listeChanson.size(); i++){
				Chanson c = listeChanson.get(i);
					out.write("\n<tr>\n"+
					"	<td>"+getArtiste(c)+"</td>\n"+
					"	<td>"+c.getTitre()+"</td>\n"+
					"	<td>"+getAlbum(c, request.getContextPath())+"</td>\n"+
					"	<td>"+getDuration(c)+"</td>\n"+
					"	<td>"+c.getUrl()+"</td>\n"+
					"	<td><a title=\"Download\" href=\""+request.getContextPath()+"/servlet/download?id="+c.getId()+"\"><span class=\"glyphicon glyphicon-cloud-download\"></span></a></td>\n"+
					"	<td><audio src=\""+request.getContextPath()+"/servlet/download?id="+c.getId()+"\"  controls=\"controls\" preload=\"none\"></audio></td>\n"+
					"	<td><a title=\"Convert\" href=\""+request.getContextPath()+"/servlet/encode?id="+c.getId()+"\"><span class=\"glyphicon glyphicon-sound-stereo\"></span></a></td>\n"+
					"	<td><a title=\"Delete\" href=\""+request.getContextPath()+"/servlet/delete?id="+c.getId()+"\"><span class=\"glyphicon glyphicon-remove\"></span></a></td>\n"+
					"</tr>\n");
			}
		}
	
	
	%>
</table>

<jsp:include page="tpl-bottom.jsp"></jsp:include>