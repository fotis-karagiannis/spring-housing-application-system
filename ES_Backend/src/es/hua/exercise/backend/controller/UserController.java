package es.hua.exercise.backend.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.hua.exercise.backend.entity.ApplicationForm;
import es.hua.exercise.backend.entity.Authority;
import es.hua.exercise.backend.entity.User;
import es.hua.exercise.backend.service.ApplicationFormService;
import es.hua.exercise.backend.service.AuthorityService;
import es.hua.exercise.backend.service.UserService;

@Controller
public class UserController
{	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private ApplicationFormService applicationFormService;
	
	@GetMapping("/register")
	public String showRegister() 
	{
		return "register";
	}
	
	@GetMapping("/register-user")
	public String registerUser(@RequestParam("username")String username, @RequestParam("password")String password)
	{
		if( findUserIdByUsername(username) == -1 ) // if username doesn't already exist in the database
		{
			User user = new User();
			user.setUserName(username);
			user.setPassword(new BCryptPasswordEncoder().encode(password));
			user.setCanApply(-1);
			user.setEnabled(1);
			
			Authority authorities = new Authority();
			authorities.setUserName(username);
			authorities.setAuthority("ROLE_USER");
			
			userService.saveUser(user);
			authorityService.saveAuthority(authorities);
			
			return "registered";
		}
		
		return "register-failed"; // TODO: failure management
	}
	
	@GetMapping("/delete")
	public String showDelete()
	{
		return "delete";
	}
	
	@GetMapping("/delete-user")
	public String deleteUser(@RequestParam("username")String username)
	{
		int userId = findUserIdByUsername(username);
		
		if(authorityService.getAuthority(userId).getAuthority().equals("ROLE_ADMIN"))
		{
			return "failed-admin";
		}
		
		if(userId != -1)
		{
			// If the user has submitted a form, delete it as well.
			int formId = findFormIdByUsername(username);
			
			if(formId != -1)
			{
				applicationFormService.deleteApplicationForm(formId);
			}
			
			authorityService.deleteAuthority(userId);
			userService.deleteUser(userId);
		
			return "deleted";
		}
		
		return "delete-failed"; // TODO: failure management
	}
	
	@GetMapping("/edit")
	public String showEdit()
	{
		return "edit";
	}
	
	@GetMapping("/edit-user")
	public String editUser(@RequestParam("oldUsername")String oldUsername, @RequestParam("username")String username, @RequestParam("password")String password)
	{
		int id = findUserIdByUsername(oldUsername);
		
		if(authorityService.getAuthority(id).getAuthority().equals("ROLE_ADMIN"))
		{
			return "failed-admin";
		}
		
		if( id != -1 )
		{
			User user = userService.getUser(id); // Find the user in the database
			Authority authorities = authorityService.getAuthority(id);
			
			// If params are not empty, set them to the user's fields.
			if(!username.isEmpty())
			{
				if( findUserIdByUsername(username) == -1 ) // If username doesn't already exist in the database
				{
					user.setUserName(username);
					authorities.setUserName(username);
				}
				else
				{
					return "edit-failed-1"; // TODO: failure management
				}
			}
			if(!password.isEmpty())
			{
				user.setPassword(new BCryptPasswordEncoder().encode(password));
			}
			
			// Delete existing user
			authorityService.deleteAuthority(id);
			userService.deleteUser(id);
			// Insert edited user
			userService.saveUser(user);
			authorityService.saveAuthority(authorities);	
			
			return "edited";
		}
		
		return "edit-failed-2"; // TODO: failure management
	}
	
	@GetMapping("/allow")
	public String showAllow()
	{
		return "allow";
	}
	
	@GetMapping("/allow-application")
	public String allowApplication(@RequestParam("username")String username)
	{	
		int id = findUserIdByUsername(username);
		
		if(authorityService.getAuthority(id).getAuthority().equals("ROLE_ADMIN"))
		{
			return "failed-admin";
		}
		
		if( id != -1 )
		{
			User user = userService.getUser(id); // Find the user in the database
			Authority authorities = authorityService.getAuthority(id);
			
			user.setCanApply(1);
			
			// Delete existing user
			authorityService.deleteAuthority(id);
			userService.deleteUser(id);
			// Insert edited user
			userService.saveUser(user);
			authorityService.saveAuthority(authorities);	
			
			return "allowed";
		}	
		return "allow-failed";
	}
	
	@GetMapping("/list-users")
	public String listUsers(Model model)
	{
		// get users from service
		List<User> users = userService.getUsers();

		// add the users to the model
		model.addAttribute("users", users);

		return "list-users";
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
}
