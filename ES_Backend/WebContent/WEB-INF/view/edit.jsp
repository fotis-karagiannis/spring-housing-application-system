<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h3>Edit User</h3>
	
<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/edit-user" method="GET">
			<h1> Enter old username and new user details. Empty fields will be left unchanged. </h1>	
			<label class="form-label">Old User Name</label> 
			<input type="text" name="oldUsername"/>
	
			<label class="form-label">New User Name</label> 
			<input type="text" name="username"/>
		
			<label class="form-label">New Password</label> 
			<input type="password" name="password"/>
			
			<h1></h1> <button style="width:90px" class="btn" type="submit">Edit</button>
	</form:form>
	
</div>

