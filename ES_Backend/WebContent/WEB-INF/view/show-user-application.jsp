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
			<h2>My submitted application form:</h2>
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
				<c:set var="applicationForm" value="${applicationForm}"/>
					<tr>
						<td>${applicationForm.username}</td>
						<td>${applicationForm.personalIncome}</td>
						<td>${applicationForm.familyIncome}</td>
						<td>${applicationForm.siblingsStudying}</td>
						<td>${applicationForm.homeCity}</td>
						<td>${applicationForm.yearStudying}</td>
						<td>${applicationForm.yearStaying}</td>
						<td>${applicationForm.unemployedParents}</td>
					</tr>
			</table>
		</div>
	</div>

</body>
</html>
