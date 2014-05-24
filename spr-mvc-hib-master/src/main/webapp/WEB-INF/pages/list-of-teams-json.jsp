<!DOCTYPE html>
<html>
	<head>
		<!-- jQuery from CDN -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	</head>
	<body>
		<p>This page will illustrate adding team, getting all team by using JSON request</p>
		<p>Using Developer Tool to see the result.</p>
		<br>
		<input type="text" id="name" value="Manchester United">
		<input type="number" id="rating" value="5">
		<button id="add-user">Add user</button>
		<br>
		<button id="list-users">List users</button>
				
		<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
		<script type="text/javascript">
			//var context = ${pageContext.request.contextPath};
			$("#add-user").click(function() {
			
				var teamDTO = {name: $("#name").val(), rating: $("#rating").val()};
								
				$.ajax({
					method: 'POST',
					url: "save",
					contentType: 'application/json',
					data: JSON.stringify(teamDTO)
				})
				.done(function(id) {
					
					console.log("<-- POST successful, received: created team id: " + id);
										
					$.get("get/" + id)
					.done(function(teamDTO) {
						console.log("<-- GET successful, received: " + JSON.stringify(teamDTO));
					});
				});
			});
		
			$("#list-users").click(function() {
				console.log("--> GET test: get from URL commands/teamrest/get-all-users");
				
				$.get("get-all-teams")
				.done(function(teamDTO) {
					console.log("<-- GET successful, received: " + JSON.stringify(teamDTO));
				});
			});
		
		</script>	
	</body>
</html>