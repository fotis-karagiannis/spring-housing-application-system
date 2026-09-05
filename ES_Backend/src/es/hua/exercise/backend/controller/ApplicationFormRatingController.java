package es.hua.exercise.backend.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.hua.exercise.backend.entity.ApplicationForm;
import es.hua.exercise.backend.entity.ApplicationFormRating;
import es.hua.exercise.backend.service.ApplicationFormRatingService;
import es.hua.exercise.backend.service.ApplicationFormService;

@Controller
public class ApplicationFormRatingController
{
	@Autowired
	private ApplicationFormService applicationFormService;
	@Autowired
	private ApplicationFormRatingService applicationFormRatingService;
	@Autowired
	
	@GetMapping("/create-rating")
	public String createRating()
	{
		return "create-rating";
	}
	
	@GetMapping("/calculate-rating")
	public String calculateRating(@RequestParam("university_city")String universityCity, @RequestParam("housing_spaces")int housingSpaces)
	{
		// check if a rating already exists, and if yes, cleanup.
		List<ApplicationFormRating> oldRatings = applicationFormRatingService.getApplicationFormRatings();
		if(!oldRatings.isEmpty())
		{
			for( ApplicationFormRating oldRating : oldRatings )
			{
				applicationFormRatingService.deleteApplicationFormRating(oldRating.getId());
			}
		}
		
		// get forms from service
		List<ApplicationForm> applicationForms = applicationFormService.getApplicationForms();
		List<ApplicationForm> queue = new ArrayList<>();
		List<ApplicationFormRating> rating = new ArrayList<>();
		List<ApplicationFormRating> aborted = new ArrayList<>();

		int position = 1;
		
		for(ApplicationForm applicationForm : applicationForms)
		{
			applicationForm.calculateStatus(universityCity);
	
			// Aborted or qualified are removed from the list and placed in the right lists
			if(applicationForm.getStatus().equals("Qualified"))
			{
				ApplicationFormRating currentRating;
				
				if( position-1 < housingSpaces )
				{
					currentRating = new ApplicationFormRating();
					
					currentRating.setUsername(applicationForm.getUsername());
					currentRating.setPosition(position);
					currentRating.setStatus(applicationForm.getStatus());	
				}
				else // If it is by default qualified but there are no slots, add in the "top" of the queue.
				{
					currentRating = new ApplicationFormRating();
					
					currentRating.setUsername(applicationForm.getUsername());
					currentRating.setPosition(position);
					currentRating.setStatus("In Queue");						
				}
				rating.add(currentRating);
				position++;
			}
			else if(applicationForm.getStatus().equals("Aborted"))
			{
				ApplicationFormRating currentRating = new ApplicationFormRating();
				
				currentRating.setUsername(applicationForm.getUsername());
				currentRating.setPosition(0);
				currentRating.setStatus(applicationForm.getStatus());		
				
				aborted.add(currentRating);
			}
			else
			{
				queue.add(applicationForm);
			}
		}	
		// Sort queue descending, based on points ( sorting defined in compareTo )
		Collections.sort(queue, Collections.reverseOrder());
		
		for(ApplicationForm applicationForm : queue)
		{	
			if( position-1 < housingSpaces )
			{
				ApplicationFormRating currentRating = new ApplicationFormRating();
				
				currentRating.setUsername(applicationForm.getUsername());
				currentRating.setPosition(position);
				currentRating.setStatus("Qualified");	
				
				rating.add(currentRating);
				position++;				
			}
			else
			{
				ApplicationFormRating currentRating = new ApplicationFormRating();
				
				currentRating.setUsername(applicationForm.getUsername());
				currentRating.setPosition(position);
				currentRating.setStatus("In Queue");	
				
				rating.add(currentRating);
				position++;				
			}					
		}
	
		for(ApplicationFormRating applicationFormRating : rating)
		{
			applicationFormRatingService.saveApplicationFormRating(applicationFormRating);
		}
		
		// Add the aborted forms in the end of the rating
		for(ApplicationFormRating applicationFormRating : aborted)
		{

			applicationFormRatingService.saveApplicationFormRating(applicationFormRating);
		}
		
		return "sorted";
	}
	
	@GetMapping("/show-rating")
	public String showRating(Model model)
	{
		//get ratings from service
		List<ApplicationFormRating> applicationFormRatings = applicationFormRatingService.getApplicationFormRatings();
		
		//add the ratings to the model
		model.addAttribute("applicationFormRatings", applicationFormRatings);
		
		return "show-rating";
	}
}
