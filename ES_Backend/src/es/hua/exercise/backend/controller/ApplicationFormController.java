package es.hua.exercise.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.hua.exercise.backend.entity.ApplicationForm;
import es.hua.exercise.backend.entity.User;
import es.hua.exercise.backend.service.ApplicationFormService;
import es.hua.exercise.backend.service.UserService;

@Controller
public class ApplicationFormController
{
	@Autowired
	private ApplicationFormService applicationFormService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/submit")
	public String showForm()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		int userId = findUserIdByUsername(username);
		User user = userService.getUser(userId);
		
		if(user.getCanApply() == 1)
		{
			return "submit";
		}
		else
		{
			return "submit-failed";
		}
	}
	
	@GetMapping("/submit-application")
	public String submitForm(@RequestParam("personal_income")int personalIncome, @RequestParam("family_income")int familyIncome, 
			@RequestParam("siblings_studying")int siblingsStudying, @RequestParam("home_city")String homeCity, @RequestParam("year_studying")int yearStudying,
			@RequestParam("year_staying")int yearStaying, @RequestParam("unemployed_parents")int unemployedParents)
	{
		// Get username
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

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
		
		if(formId == -1 ) // if it doesn't exist, save directly. 
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
				
		return "submitted";
	}
	
	@GetMapping("/list-applications")
	public String listApplications(Model model) 
	{
		// get forms from service
		List<ApplicationForm> applicationForms = applicationFormService.getApplicationForms();

		// add the forms to the model
		model.addAttribute("applicationForms", applicationForms);

		return "list-applications";
	}
	
	@GetMapping("/show-user-application")
	public String showUserApplication(Model model)
	{
		// Get username
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		// Find application form
		int id = findFormIdByUsername(username);
		if(id != -1)
		{
			// get application form from service
			ApplicationForm applicationForm = applicationFormService.getApplicationForm(id);
			// add form to the model
			model.addAttribute("applicationForm", applicationForm);
			
			return "show-user-application";
		}
		
		return "show-user-application-failed";
	}
	
	
	/**
	 * Not only finds form id by username, but also checks if the user with the given username has already submitted a form.
	 * 
	 * @param username
	 * @return id on success, -1 on failure.
	 */
	private int findFormIdByUsername(String username)
	{
		List<ApplicationForm> applicationForms = applicationFormService.getApplicationForms();
		
		for(ApplicationForm applicationForm : applicationForms)
		{
			if(applicationForm.getUsername().equals(username))
			{
				return applicationForm.getId();
			}
		}
		
		return -1;
	}
	
	/**
	 * Not only finds id by username, but also checks if a user with the given username exists.
	 * 
	 * @param username
	 * @return id on success, -1 on failure.
	 */
	private int findUserIdByUsername(String username)
	{
		List<User> users = userService.getUsers();
		
		for(User user : users)
		{
			if( user.getUserName().equals(username) )
			{
				return user.getId();
			}
		}
		
		return -1;
	}
}
