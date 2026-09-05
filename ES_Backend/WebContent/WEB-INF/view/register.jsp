<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h3>Register</h3>
	
<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/register-user" method="GET">	
			<label class="form-label">New User's Name</label> 
			<input type="text" name="username"/>
		
			<label class="form-label">New User's Password</label> 
			<input type="password" name="password"/>
			
			<h1></h1> <button style="width:90px" class="btn" type="submit">Register</button>
	</form:form>
	
</div>

