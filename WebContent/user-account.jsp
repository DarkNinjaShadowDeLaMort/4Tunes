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
		return "unknown";
	}
}

public String getAlbum(Chanson c) {
	if(c.getAlbum() != null){
		return c.getAlbum().getNom();
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

<h1>Connecté</h1>

<a href="<%=request.getContextPath()%>/servlet/logout">Logout</a> <br/>

<a href="<%=request.getContextPath()%>/servlet/addArtiste">Add new Singer</a> <br/>
<a href="<%=request.getContextPath()%>/servlet/addAlbum">Add new Album</a> <br/>
<a href="<%=request.getContextPath()%>/servlet/addChanson">Add new Song</a> <br/>

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
	</thead>

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
					"	<td><a title=\"Download\" href=\""+request.getContextPath()+"/servlet/download?id="+c.getId()+"\"><span class=\"glyphicon glyphicon-cloud-download\"></span></a></td>\n"+
					"	<td><audio src=\""+request.getContextPath()+"/servlet/download?id="+c.getId()+"\"  controls=\"controls\" preload=\"none\"></audio></td>\n"+
					"	<td><a title=\"Convert\" href=\""+request.getContextPath()+"/servlet/encode?id="+c.getId()+"\"><span class=\"glyphicon glyphicon-sound-stereo\"></span></a></td>\n"+
					"</tr>\n");
			}
		}
	
	
	%>
</table>

<jsp:include page="tpl-bottom.jsp"></jsp:include>