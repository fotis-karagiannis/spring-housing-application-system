package es.hua.exercise.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.hua.exercise.backend.dao.ApplicationFormRatingDAO;
import es.hua.exercise.backend.entity.ApplicationFormRating;

@Service
public class ApplicationFormRatingServiceImpl implements ApplicationFormRatingService
{
	@Autowired
	private ApplicationFormRatingDAO applicationFormRatingDAO;	
	
	@Override
	@Transactional
	public List<ApplicationFormRating> getApplicationFormRatings()
	{
		return applicationFormRatingDAO.getApplicationFormRatings();
	}	
	
	@Override
	@Transactional
	public void saveApplicationFormRating(ApplicationFormRating applicationFormRating)
	{
		applicationFormRatingDAO.saveApplicationFormRating(applicationFormRating);
	}	
	
	@Override
	@Transactional
	public ApplicationFormRating getApplicationFormRating(int id)
	{
		return applicationFormRatingDAO.getApplicationFormRating(id);
	}	
	
	@Override
	@Transactional
	public void deleteApplicationFormRating(int id)
	{
		applicationFormRatingDAO.deleteApplicationFormRating(id);
	}	
}
