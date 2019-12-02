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
<title>Register</title>
</head>

<% 
	String errorMessage = (String)request.getAttribute("registerError");
%>

<body>
		<nav class="navbar navbar-expand-lg navbar-light">
		<a href="MeetupServlet"> <img id="logo" src=" assets/bgt_64.png"
			alt="">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="MeetupServlet">Home
				</a></li>
				<li class="nav-item"><a class="nav-link" href="Profile">Profile
				</a></li>
			</ul>
			<ul class="navbar-nav mr-auto" style="margin-right: 0 !important;">
				<li class="nav-item"><a class="nav-link"
					href="https://www.google.com/" target="_blank"><i
						class="fas fa-search icon"></i></a></li>
				<li class="nav-item"><a class="nav-link" href="Profile"><i
						class="far fa-user icon"></i></a></li>
			</ul>
		</div>
	</nav>

	<h1 class="orange">Register</h1>

	<form action="Register" method="POST">
		<div class="form-group">
			<label for="username">Username</label> <input type="text" name="username" class="form-control"
				id="username" placeholder="Enter username">
		</div>
		<div class="form-group">
			<label for="pass">Password</label> <input
				type="password" name ="password" class="form-control" id="pass"
				placeholder="Enter password">
		</div>
		<div class="form-group">
			<label for="confirm">Confirm Password</label> <input
				type="password" name ="cPassword" class="form-control" id="confirm"
				placeholder="Confirm password">
		</div>
		<button type="submit" class="btn btn-primary form-btn">Register</button>
		<button type="reset" class="btn btn-secondary form-btn">Reset</button>
		<%= errorMessage!=null ? errorMessage : "" %>
	</form>

	<!-- HTML here  -->
	<script src="libraries/jquery-3.4.1.slim.min.js"></script>
	<script src="libraries/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>