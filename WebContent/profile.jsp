<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<a class="navbar-brand" href="homepage.jsp">
		<img src = "assets/bgt_64.png" alt = "">
	</a>
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
			<li class="nav-item"><a class="nav-link" href="profile.jsp"><i class="far fa-user"></i></a></li>
		</ul>
	</div>
</nav>
<%
	String username = (String)session.getAttribute("username");
	if(username==null) {
%>
		<div class="mt-5 mb-5">
			<a class="btn btn-primary btn-20 btn-solid" href="register.jsp" role="button">Sign up</a>
			<a class="btn btn-secondary btn-20 btn-hollow" href="login.jsp" role="button">Log in</a>
		</div>
<% } else { %>
	

	<h1 class="orange"><%= username %>'s Profile</h1>	
	<h2 class="orange">Email</h2>

<% } %>

	<!-- HTML here  -->
	<script src="libraries/jquery-3.4.1.slim.min.js"></script>
	<script src="libraries/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>