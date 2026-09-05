<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h3>Login</h3>
	
<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/authUser" method="POST">
	<c:if test="${param.error != null}">
		<i>Sorry! Invalid username/password!</i>
	</c:if>
	
			<label class="form-label">User Name</label> 
			<input type="text" name="username"/>
		
			<label class="form-label">Password</label> 
			<input type="password" name="password"/>
			<h1></h1> <button style="width:90px" class="btn" type="submit">Login</button>
	</form:form>
	
</div>
