<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Connect�</h1>

<a href="<%=request.getContextPath()%>/servlet/logout">Logout</a> <br/>

<a href="<%=request.getContextPath()%>/servlet/addGenre">Cr�er Genre</a> <br/>
<a href="<%=request.getContextPath()%>/servlet/addArtiste">Cr�er Artiste</a> <br/>
<a href="<%=request.getContextPath()%>/servlet/addAlbum">Cr�er Album</a> <br/>
<a href="<%=request.getContextPath()%>/servlet/addChanson">Cr�er chanson</a> <br/>

</body>
</html>