<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Connecté</h1>

<a href="<%=request.getContextPath()%>/servlet/logout">Logout</a> <br/>

<a href="<%=request.getContextPath()%>/servlet/addGenre">Créer Genre</a> <br/>
<a href="<%=request.getContextPath()%>/servlet/addArtiste">Créer Artiste</a> <br/>
<a href="<%=request.getContextPath()%>/servlet/addAlbum">Créer Album</a> <br/>
<a href="<%=request.getContextPath()%>/servlet/addChanson">Créer chanson</a> <br/>

</body>
</html>