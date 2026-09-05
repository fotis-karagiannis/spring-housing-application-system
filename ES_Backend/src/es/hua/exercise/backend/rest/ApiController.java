package es.hua.exercise.backend.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import es.hua.exercise.backend.entity.ApplicationForm;
import es.hua.exercise.backend.entity.User;
import es.hua.exercise.backend.entity.ApplicationFormRating;
import es.hua.exercise.backend.service.ApplicationFormRatingService;
import es.hua.exercise.backend.service.ApplicationFormService;
import es.hua.exercise.backend.service.UserService;

@RestController
@RequestMapping("/api/user")
public class ApiController
{
	@Autowired
	private UserService userService;
	@Autowired
	private ApplicationFormService applicationFormService;
	@Autowired
	private ApplicationFormRatingService applicationFormRatingService;

	// Method for login function to the frontend
	@RequestMapping(value = "/getUser", method = RequestMethod.POST, produces =
	{ "application/json", "application/xml" })
	public String getUser(@RequestParam("username") String username, @RequestParam("password") String password)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		int userId = findUserIdByUsername(username);
		
		if (userService.getUser(userId) != null)
		{
			User user = userService.getUser(userId);
			if (passwordEncoder.matches(password, user.getPassword()))
			{
				return "{\"user\" : \"FOUND\"}";
			} 
			else
			{
				return null;
			}
		} 
		else
		{
			return "{\"user\" : \"NOT_FOUND\"}";
		}

	}

	// Method to check user if can apply
	@RequestMapping(value = "/checkCanApply/{username}", method = RequestMethod.GET, produces =	{ "application/json", "application/xml" })
	public String checkCanApply(@PathVariable("username") String username)
	{
		int userId = findUserIdByUsername(username);
		
		if (userService.getUser(userId) != null)
		{
			User user = userService.getUser(userId);
			if (user.getCanApply() == -1)
			{
				return "{\"user\" : \"CANT_APPLY\"}";
			} 
			else
			{
				return "{\"user\" : \"CAN_APPLY\"}";
			}
		} 
		else
		{
			return "{\"user\" : \"NOT_FOUND\"}";
		}
	}

	// Method to submitform
	@RequestMapping(value = "/submitForm", method = RequestMethod.POST, produces =	{ "application/json", "application/xml" })
	public String addForm(@RequestParam("username") String username, @RequestParam("personal_income") int personalIncome, 
			@RequestParam("family_income") int familyIncome,
			@RequestParam("siblings_studying") int siblingsStudying, @RequestParam("home_city") String homeCity,
			@RequestParam("year_studying") int yearStudying, @RequestParam("year_staying") int yearStaying,
			@RequestParam("unemployed_parents") int unemployedParents)
	{

		ApplicationForm applicationForm = new ApplicationForm();
		applicationForm.setUsername(username);
		applicationForm.setPersonalIncome(personalIncome);
		applicationForm.setFamilyIncome(familyIncome);
		applicationForm.setSiblingsStudying(siblingsStudying);
		applicationForm.setHomeCity(homeCity);
		applicationForm.setYearStudying(yearStudying);
		applicationForm.setYearStaying(yearStaying);
		applicationForm.setUnemployedParents(unemployedParents);
		applicationForm.setPoints(0);
		applicationForm.setStatus("Undecided");

		int formId = findFormIdByUsername(username);

		if (formId == -1) // if it doesn't exist, save directly.
		{
			applicationFormService.saveApplicationForm(applicationForm);
		} 
		else
		{
			// Delete previous application
			applicationFormService.deleteApplicationForm(formId);
			// Insert new application
			applicationFormService.saveApplicationForm(applicationForm);
		}

		return "{\"form\" : \"SUBMITED\"}";

	}

	// Method to show form by username
	@RequestMapping(value = "/getForm/{username}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	public String getForm(@PathVariable("username") String username)
	{
		int id = findFormIdByUsername(username);

		if (id != -1)
		{
			ApplicationForm form = applicationFormService.getApplicationForm(id);
			return form.jsonFormat();
		} 
		else
		{
			return "{\"form\" : \"NOT_FOUND\"}";
		}
	}

	// Method to show form rating by username
	@RequestMapping(value = "/getFormRating/{username}", method = RequestMethod.GET, produces =	{ "application/json", "application/xml" })
	public String getFormRating(@PathVariable("username") String username)
	{
		int id = findFormRatingIdByUsername(username);

		if (id != -1)
		{
			ApplicationFormRating formRating = applicationFormRatingService.getApplicationFormRating(id);
			return formRating.jsonFormat();
		} 
		else
		{
			return "{\"formRating\" : \"NOT_FOUND\"}";
		}
	}

	private int findFormRatingIdByUsername(String username)
	{
		List<ApplicationFormRating> applicationFormRatings = applicationFormRatingService.getApplicationFormRatings();
		
		for (ApplicationFormRating applicationFormRating : applicationFormRatings)
		{
			if (applicationFormRating.getUsername().equals(username))
			{
				return applicationFormRating.getId();
			}
		}

		return -1;
	}

	private int findFormIdByUsername(String username)
	{
		List<ApplicationForm> applicationForms = applicationFormService.getApplicationForms();

		for (ApplicationForm applicationForm : applicationForms)
		{
			if (applicationForm.getUsername().equals(username))
			{
				return applicationForm.getId();
			}
		}

		return -1;
	}

	private int findUserIdByUsername(String username)
	{
		List<User> users = userService.getUsers();

		for (User user : users)
		{
			if (user.getUserName().equals(username))
			{
				return user.getId();
			}
		}

		return -1;
	}

}
