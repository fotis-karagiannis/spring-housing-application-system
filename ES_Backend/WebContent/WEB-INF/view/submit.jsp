<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h3>Housing Application Form</h3>
	
<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/submit-application" method="GET">
			<label class="form-label">Personal Income</label>
			<input type="number" name="personal_income"/>
			
			<label class="form-label">Family income</label>
			<input type="number" name="family_income"/>
			
			<label class="form-label">Siblings Studying</label>
			<input type="number" name="siblings_studying"/>
			
			<label class="form-label">Home City</label>
			<input type="text" name="home_city"/>
			
			<label class="form-label">Year of study</label>
			<input type="number" name="year_studying"/>
			
			<label class="form-label">Year of stay</label>
			<input type="number" name="year_staying"/>
			
			<label class="form-label">Unemployed Parents</label>
			<input type="number" name="unemployed_parents"/>
			
			<h1></h1> <button style="width:90px" class="btn" type="submit">Submit</button>
	</form:form>
	
</div>