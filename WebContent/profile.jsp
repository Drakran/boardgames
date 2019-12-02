<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, boardgame.Meet, boardgame.Game"%>
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
<style>
img {
	height: 250px;
}

.list {
	border-bottom: 1px solid #89aee2;
	padding: 50px;
}

h3 {
	color: #89aee2;
}

span {
	visibility: hidden;
}
</style>
</head>
<%String username = (String) session.getAttribute("username"); %>


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
				<li class="nav-item active"><a class="nav-link" href="Profile">Profile
				</a></li>
			</ul>
			<ul class="navbar-nav mr-auto" style="margin-right: 0 !important;">
				<li class="nav-item"><a class="nav-link"
					href="https://www.google.com/" target="_blank"><i
						class="fas fa-search icon"></i></a></li>
				<li class="nav-item"><a class="nav-link" href="Profile"><i
						class="far fa-user icon"></i></a></li>

				<%
					if (username != null && !username.isEmpty()) {
				%>
				<li class="nav-item"><a class="nav-link" href="Signout"><i
						class="fas fa-sign-out-alt icon"></i></a></li>
				<%
					}
				%>
			</ul>
		</div>
	</nav>
	<%
		if (username == null) {
	%>
	<div class="mt-5 mb-5">
		<a class="btn btn-primary btn-20 btn-solid" href="register.jsp"
			role="button">Sign up</a> <a class="btn btn-secondary btn-20"
			href="login.jsp" role="button">Log in</a>
	</div>
	<%
		} else {
	%>

	<h1 class="orange"><%=username%>'s Profile
	</h1>

	<h2 class="orange">My Meetups</h2>
	<div class="container">
		<%
			List<Meet> meetList = (ArrayList<Meet>) request.getAttribute("meetList");
				for (int i = 0; i < meetList.size(); i++) {
		%>
		<div class="row list">
			<div class="col-6">
				<div class="row">
					<h3>
						<%=meetList.get(i).getGameName()%></h3>
				</div>
				<div class="row">
					<h5>
						Location: <span>llll</span>
						<%=meetList.get(i).getLocation()%></h5>
				</div>
				<div class="row">
					<h5>
						Meet Time: <span>l</span>
						<%=meetList.get(i).getMeetTime()%></h5>
				</div>
				<div class="row">
					<h5>
						Frequency: <span>l</span>
						<%=meetList.get(i).getFrequency()%></h5>
				</div>
				<div class="row">
					<h5>
						Creator: <span>lllllll</span>
						<%=meetList.get(i).getCreatorUsername()%></h5>
				</div>
				<div class="row">
					<h5>
						Players: <span>lllllll</span>
						<%=meetList.get(i).getCurrPlayers()%>
						out of
						<%=meetList.get(i).getCapacity()%></h5>
				</div>
			</div>

			<div class="col">
				<img src="<%=meetList.get(i).getImage()%>" />
			</div>
		</div>
		<%
			}
		%>
	</div>

	<h2 class="orange">My Games</h2>
	<table>
		<tbody class="tableBody">
			<%
				List<Game> ownList = (ArrayList<Game>) request.getAttribute("ownList");
					for (int i = 0; i < ownList.size(); i++) {
			%>
			<tr>
				<td><%=ownList.get(i).getGameName()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

	<h2 class="orange">My Wishlist</h2>
	<table>
		<tbody class="tableBody">
			<%
				List<Game> wishList = (ArrayList<Game>) request.getAttribute("wishList");
					for (int i = 0; i < wishList.size(); i++) {
			%>
			<tr>
				<td><%=wishList.get(i).getGameName()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<%
		}
	%>

	<!-- HTML here  -->
	<script src="libraries/jquery-3.4.1.slim.min.js"></script>
	<script src="libraries/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>