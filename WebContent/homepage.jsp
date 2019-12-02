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
<title>Board Game Tinder</title>

<script>

</script>

<%
	List<Meet> list = (ArrayList<Meet>) request.getAttribute("meetupArray");

	String username = null;
	if(session != null) {
		username = (String)session.getAttribute("username");
	}
%>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light">
		<a href="MeetupServlet">
			<img src=" assets/bgt_64.png" alt="">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
			aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="homepage.jsp">Home </a></li>
			</ul>
			<ul class="navbar-nav mr-auto" style="margin-right: 0 !important;">
				<li class="nav-item"><a class="nav-link" href="games.jsp"><i class="fas fa-search"></i></a></li>
				<li class="nav-item"><a class="nav-link" href="Profile"><i class="far fa-user"></i></a></li>
			</ul>
		</div>
	</nav>
	<div class="text-center mt-20">
	<h1>BOARD GAME TINDER</h1>
	<p>Make new friends with people who enjoy playing board games!</p>
	<p>Learn to play a new game with a welcoming community of friends</p>
	<%
		if(username != null && !username.isEmpty()) {
	%>
	<div class = "mt-5 mb-5">
		<a class="btn btn-primary btn-20 btn-solid" href="meetup-creation.jsp" role="button">CREATE</a>
	</div>
	<%
		}
	%>
	<h1>ALL EXISTING MEETUPS</h1>

<script type="text/javascript">
	function meetupJoin(meetupID) {
		meetupID = meetupID.replace("meetup", "");
		$.ajax({
			method: "GET",
			url: "JoinMeetup",
			data: { id: meetupID}
		})
		.success(function(data){
			console.log("hi");
			});
		}
</script>

	<% 
		if(list != null) {
		int rows = list.size();
		System.out.println("num of meetups: " + rows);
		if(rows > 10) { rows =10; }
		for(int i = 0; i < rows; i++) {
		%>	 

		
	<div class="meetupBlock" align = "center" style="text-align: center;">
		<% 	Meet meet = list.get(i); 
			String id = "meetupID" + i; %>
		<p><%=meet.getGameName()%></p>
		<p><i class="fas fa-user"></i><%=meet.getCreatorUsername()%></p>
		<a id=meetup<%=meet.getMeetupID()%> class="btn btn-secondary btn-20" role="button" onClick="meetupJoin(this.id)">JOIN</a>
		<% if(i!=rows-1) { %>
		<hr>
		<% } %>
	</div>

	<% 		}
		} else {
		System.out.println("null");
		}
			%>
		
	</div>
	<!-- HTML here  -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="libraries/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>