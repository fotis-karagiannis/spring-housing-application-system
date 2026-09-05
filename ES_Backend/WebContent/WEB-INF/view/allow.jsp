<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h3>Allow Application</h3>
	
<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/allow-application" method="GET">	
			<label class="form-label">User Name</label> 
			<input type="text" name="username"/>
			
			<h1></h1> <button style="width:250px" class="btn" type="submit">Allow User Application</button>
	</form:form>
	
</div>

