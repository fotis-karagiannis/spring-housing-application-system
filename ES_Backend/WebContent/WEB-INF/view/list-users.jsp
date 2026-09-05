<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Registered Users</title>
<!-- reference our style sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Users currently registered in the system:</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<!--  add our html table here -->
			<table border="1" style="text-align:center;background-color:Lavender;padding:4px" >
				<tr>
					<th>Username</th>
					<th>Enabled</th>
					<th>Application Rights</th>
				</tr>
				<!-- loop over and print our users -->
				<c:forEach var="tempUser" items="${users}">

					<tr>
						<td>${tempUser.userName}</td>
						<td>${tempUser.enabled}</td>
						<td>${tempUser.canApply}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>
