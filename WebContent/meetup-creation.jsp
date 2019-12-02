<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.List, java.util.ArrayList, boardgame.Meet"%>
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
<title>Create Meetup</title>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light">
		<a class="navbar-brand" href="MeetupServlet">LOGO</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="MeetupServlet">Home </a></li>
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

	<h1 class="orange">Create Meetup</h1>

	<form action="createMeetup" method="GET">
		<div class="form-group">
			<label for="gameName">Game Name</label> <input type="text" name="gameName" class="form-control"
				id="gameName" placeholder="Enter a game name">
		</div>
		<div class="form-group">
			<label for="meetTime">Meet Time</label> <input
				type="text" name ="meetTime" class="form-control" id="meetTime"
				placeholder="Enter a time to meet">
		</div>
		<div class="form-group">
			<label for="frequency">Frequency</label> <input
				type="text" name ="frequency" class="form-control" id="frequency"
				placeholder="Enter how often you'd like to meet">
		</div>
		<div class="form-group">
			<label for="description">Capacity</label> <input
				type="number" name ="capacity" class="form-control" id="description"
				placeholder="Enter a description for this meetup">
		</div>
		<div class="form-group">
			<label for="location">Location</label> <input
				type="text" name ="location" class="form-control" id="location"
				placeholder="Enter where you'd like to meet">
		</div>
		<button type="submit" class="btn btn-primary form-btn">Create</button>
		<button type="reset" class="btn btn-secondary form-btn">Reset</button>
	</form>

	<!-- HTML here  -->
	<script src="libraries/jquery-3.4.1.slim.min.js"></script>
	<script src="libraries/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>