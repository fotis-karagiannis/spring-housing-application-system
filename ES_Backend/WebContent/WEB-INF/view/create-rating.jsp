<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h3>Calculate Application Form Ranking</h3>
	
<div class="form-group">

	<form:form action="${pageContext.request.contextPath}/calculate-rating" method="GET">
			<h1> Enter univesity city and housing spaces available. </h1>	
			<label class="form-label">University City</label> 
			<input type="text" name="university_city"/>
	
			<label class="form-label">Housing Spaces</label> 
			<input type="number" name="housing_spaces"/>

			<h1></h1> <button style="width:90px" class="btn" type="submit">Calculate</button>
	</form:form>
	
</div>

