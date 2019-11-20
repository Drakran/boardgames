<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css"
	href="libraries/fontawesome/css/all.css">
<link rel="stylesheet" type="text/css"
	href="libraries/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="styles/main.css">
<title>Insert title here</title>
</head>
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
				<li class="nav-item active"><a class="nav-link"
					href="homepage.jsp">Home </a></li>
				<li class="nav-item"><a class="nav-link" href="#">BLAH</a></li>
			</ul>
			<ul class="navbar-nav mr-auto" style="margin-right: 0 !important;">
				<li class="nav-item"><a class="nav-link" href="games.jsp"><i
						class="fas fa-search"></i></a></li>
				<li class="nav-item"><a class="nav-link" href="profile.jsp"><i
						class="far fa-user"></i></a></li>
			</ul>
		</div>
	</nav>

	<h1 class="orange">Register</h1>

	<form action="Register" method="POST">
		<div class="form-group">
			<label for="exampleInputEmail1" style="color: white">Email address</label> <input
				type="text" class="form-control" id="exampleInputEmail1"
				placeholder="Enter email">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Password</label> <input
				type="password" class="form-control" id="exampleInputPassword1"
				placeholder="Password">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Password</label> <input
				type="password" class="form-control" id="exampleInputPassword1"
				placeholder="Password">
		</div>
		<button type="submit" class="btn btn-primary">Register</button>
		<button type="reset" class="btn btn-secondary">Reset</button>
	</form>

	<!-- HTML here  -->
	<script src="libraries/jquery-3.4.1.slim.min.js"></script>
	<script src="libraries/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>