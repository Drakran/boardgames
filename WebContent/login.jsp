<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="libraries/fontawesome/css/all.css">
<link rel="stylesheet" type="text/css"
	href="libraries/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/form.css">
<title>Board Games</title>
</head>

<% 
	String errorMessage = (String)request.getAttribute("loginError");
%>

<body>
	<nav class="navbar navbar-expand-lg navbar-light">
		<a class="navbar-brand" href="homepage.jsp">LOGO</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="homepage.jsp">Home
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">BLAH</a></li>
			</ul>
			<ul class="navbar-nav mr-auto" style="margin-right: 0!important;">
				<li class="nav-item"><a class="nav-link" href="games.jsp"><i
						class="fas fa-search"></i></a></li>
				<li class="nav-item"><a class="nav-link" href="Profile"><i
						class="far fa-user"></i></a></li>
			</ul>
		</div>
	</nav>
	<br><br>
	<h1 class="orange">Login</h1>	

	<form action="Login" method="GET">
	  <div class="form-group">
	    <label for="username">Username</label>
	    <input type="username" name="username" class="form-control" id="username" aria-describedby="usernameHelp" placeholder="Enter username">
	  </div>
	  <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" name="password" class="form-control" id="password" placeholder="Enter password">
	  </div>
	  <button type="submit" class="btn btn-primary form-btn">Submit</button>
	  <%= errorMessage!=null ? errorMessage : "" %>
	</form>

	<!-- HTML here  -->
	<script src="libraries/jquery-3.4.1.slim.min.js"></script>
	<script src="libraries/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>