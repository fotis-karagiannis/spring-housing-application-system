<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h3>User</h3>
	
<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/submit" method="GET">
	<h1></h1> <button style="width:170px" class="btn" type="submit">Submit Application</button>
	</form:form>

</div>	

<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/show-user-application" method="GET">
	<h1></h1> <button style="width:170px" class="btn" type="submit">Show my form</button>
	</form:form>

</div>