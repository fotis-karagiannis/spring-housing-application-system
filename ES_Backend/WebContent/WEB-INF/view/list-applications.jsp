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
			<h2>Application forms currently submitted in the system:</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<!--  add our html table here -->
			<table border="1" style="text-align:center;background-color:Lavender;padding:4px">
				<tr>
					<th>Username</th>
					<th>Personal Income</th>
					<th>Family Income</th>
					<th>Siblings Studying</th>
					<th>Home City</th>
					<th>Year of Study</th>
					<th>Year of Stay</th>
					<th>Unemployed Parents</th>
				</tr>
				<!-- loop over and print our users -->
				<c:forEach var="tempApplication" items="${applicationForms}">
					<tr>
						<td>${tempApplication.username}</td>
						<td>${tempApplication.personalIncome}</td>
						<td>${tempApplication.familyIncome}</td>
						<td>${tempApplication.siblingsStudying}</td>
						<td>${tempApplication.homeCity}</td>
						<td>${tempApplication.yearStudying}</td>
						<td>${tempApplication.yearStaying}</td>
						<td>${tempApplication.unemployedParents}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>
