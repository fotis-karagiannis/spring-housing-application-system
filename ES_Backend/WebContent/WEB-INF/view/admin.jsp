<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h3>Admin</h3>
	
<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/list-users" method="GET">
	<h1></h1> <button style="width:140px" class="btn" type="submit">List Users</button>
	</form:form>

</div>

<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/list-applications" method="GET">
	<h1></h1> <button style="width:140px" class="btn" type="submit">List Applications</button>
	</form:form>

</div>
	
<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/register" method="GET">
	<h1></h1> <button style="width:140px" class="btn" type="submit">Register User</button>
	</form:form>

</div>

<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/delete" method="GET">	
	<h1></h1> <button style="width:140px" class="btn" type="submit">Delete User</button>
	</form:form>
	
</div>

<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/edit" method="GET">	
	<h1></h1> <button style="width:140px" class="btn" type="submit">Edit User</button>
	</form:form>
	
</div>

<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/allow" method="GET">	
	<h1></h1> <button style="width:140px" class="btn" type="submit">Allow Application</button>
	</form:form>
	
</div>

<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/create-rating" method="GET">	
	<h1></h1> <button style="width:140px" class="btn" type="submit">Create Ranking</button>
	</form:form>
	
</div>

<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/show-rating" method="GET">	
	<h1></h1> <button style="width:140px" class="btn" type="submit">Show Ranking</button>
	</form:form>
	
</div>