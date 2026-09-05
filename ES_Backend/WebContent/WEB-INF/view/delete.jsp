<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h3>Delete</h3>
	
<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/delete-user" method="GET">	
			<label class="form-label">User Name</label> 
			<input type="text" name="username"/>
			
			<h1></h1> <button style="width:90px" class="btn" type="submit">Delete</button>
	</form:form>
	
</div>

