<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, boardgame.Meet, boardgame.User"%>
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
<title>Board Game Meet</title>


<%
	List<Meet> list = (ArrayList<Meet>) request.getAttribute("meetupArray");
	User user = (User) session.getAttribute("userObject");

	String username = null;
	if (session != null) {
		username = (String) session.getAttribute("username");
	}
%>
<style>
#title {
	font-size: 60px;
}
</style>
</head>
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
	<div class="text-center mt-20">
		<h1 id="title">BOARD GAME MEET</h1>
		<p>Make new friends with people who enjoy playing board games!</p>
		<p>Learn to play a new game with a welcoming community of friends</p>
		<%
			if (username != null && !username.isEmpty()) {
		%>
		<div class="mt-5 mb-5">
			<a class="btn btn-primary btn-20 btn-solid"
				href="meetup-creation.jsp" role="button">CREATE</a>
		</div>
		<h1>ALL JOINABLE MEETUPS</h1>
		<%
			} else {
		%>
		<h1>ALL MEETUPS</h1>
		<%
			}
		%>
		<script type="text/javascript">
			function meetupJoin(meetupID) {
				var meetName = meetupID;
				meetupID = meetupID.replace("meetup", "");
				$.ajax({
					method : "GET",
					url : "JoinMeetup",
					data : {
						id : meetupID
					}
				}).done(function(data) {
					var meetNameID = "#" + meetName;
					console.log(meetNameID);
					$(meetNameID).off('click').on('click', function() {
						meetupRemove(meetName);
					})
					$(meetNameID).html("REMOVE");
				});
			}

			function meetupRemove(meetupID) {
				var meetName = meetupID;
				meetupID = meetupID.replace("meetup", "");
				$.ajax({
					method : "GET",
					url : "removeServlet",
					data : {
						id : meetupID
					}
				}).done(function(data) {
					var meetNameID = "#" + meetName;
					console.log(meetNameID);
					$(meetNameID).off('click').on('click', function() {
						meetupJoin(meetName);
					})
					$(meetNameID).html("JOIN");
				});
			}
		</script>

		<%
			if (list != null) {
				int rows = list.size();
				System.out.println("num of meetups: " + rows);
				if (rows > 10) {
					rows = 10;
				}
				for (int i = 0; i < rows; i++) {
		%>


		<div class="meetupBlock" align="center" style="text-align: center;">
			<%
				Meet meet = list.get(i);
						String id = "meetupID" + i;
			%>
			<p><%=meet.getGameName()%></p>
			<p>
				<i class="fas fa-user"></i><%=meet.getCreatorUsername()%></p>
			<%
				if (user != null) {
			%>
			<%
				if (user.checkMeet(meet)) {
			%>
			<a id=meetup<%=meet.getMeetupID()%> class="btn btn-secondary btn-20"
				role="button" onClick="meetupRemove(this.id)">REMOVE</a>
			<%
				} else {
			%>
			<a id=meetup<%=meet.getMeetupID()%> class="btn btn-secondary btn-20"
				role="button" onClick="meetupJoin(this.id)">JOIN</a>

			<%
				}
			%>
			<%
				}
			%>

			<%
				if (i != rows - 1) {
			%>
			<hr>
			<%
				}
			%>
		</div>

		<%
			}
			} else {
				System.out.println("null");
			}
		%>

	</div>
	<!-- HTML here  -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="libraries/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>