<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.List, java.util.ArrayList, boardgame.Meet, boardgame.Game"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Montserrat&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="libraries/fontawesome/css/all.css">
<link rel="stylesheet" type="text/css"
	href="libraries/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/form.css">
<title>Profile</title>
</head>



<body>
<nav class="navbar navbar-expand-lg navbar-light">
	<a class="navbar-brand" href="homepage.jsp">LOGO</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
		aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarText">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="homepage.jsp">Home </a></li>
			<li class="nav-item"><a class="nav-link" href="#">BLAH</a></li>
		</ul>
		<ul class="navbar-nav mr-auto" style="margin-right: 0 !important;">
			<li class="nav-item"><a class="nav-link" href="games.jsp"><i class="fas fa-search"></i></a></li>
			<li class="nav-item"><a class="nav-link" href="Profile"><i class="far fa-user"></i></a></li>
		</ul>
	</div>
</nav>
<%
	String username = (String)session.getAttribute("username");
	if(username==null) {
%>
		<div class="mt-5 mb-5">
			<a class="btn btn-primary btn-20 btn-solid" href="register.jsp" role="button">Sign up</a>
			<a class="btn btn-secondary btn-20" href="login.jsp" role="button">Log in</a>
		</div>
<% } else { %>
	
	<h1 class="orange"><%= username %>'s Profile</h1>	

	<h2 class="orange">My Meetups</h2>
	<table>
		<thead class="tableHead">
			<tr>
				<th>Name</th>
				<th>Location</th>
				<th>Meet Time</th>
				<th>Frequency</th>
			</tr>
		</thead>
		<tbody class="tableBody">
		<%
		List<Meet> meetList = (ArrayList<Meet>) request.getAttribute("meetList");
		for(int i=0; i<meetList.size(); i++){ %>	
			<tr>
				<td><%= meetList.get(i).getGameName()%></td>
				<td><%= meetList.get(i).getLocation()%></td>
				<td><%= meetList.get(i).getMeetTime()%></td>
				<td><%= meetList.get(i).getFrequency()%></td>
			</tr>
		<%}%>
		</tbody>
	</table>

	<h2 class="orange">My Games</h2>
	<table>
		<tbody class="tableBody">
		<%
		List<Game> ownList = (ArrayList<Game>)request.getAttribute("ownList");
		for(int i=0; i<ownList.size(); i++){ %>	
			<tr><td><%= ownList.get(i).getGameName()%></td></tr>
		<%}%>
		</tbody>
	</table>

	<h2 class="orange">My Wishlist</h2>
	<table>
		<tbody class="tableBody">
		<%
		List<Game> wishList = (ArrayList<Game>)request.getAttribute("wishList");
		for(int i=0; i<wishList.size(); i++){ %>	
			<tr><td><%= wishList.get(i).getGameName()%></td></tr>
		<%}%>
		</tbody>
	</table>
<% } %>

	<!-- HTML here  -->
	<script src="libraries/jquery-3.4.1.slim.min.js"></script>
	<script src="libraries/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>