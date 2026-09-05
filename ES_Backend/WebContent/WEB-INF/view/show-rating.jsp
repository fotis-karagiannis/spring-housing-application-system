<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Application Rating</title>
<!-- reference our style sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Application Form Ranking</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<!--  add our html table here -->
			<table border="1" style="text-align:center;background-color:Lavender;padding:4px">
				<tr>
					<th>Username</th>
					<th>Position</th>
					<th>Status</th>
				</tr>
				<!-- loop over and print our users -->
				<c:forEach var="tempApplication" items="${applicationFormRatings}">
					<tr>
						<td>${tempApplication.username}</td>
						<td>${tempApplication.position}</td>
						<td>${tempApplication.status}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>
