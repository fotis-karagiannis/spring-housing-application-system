package es.hua.exercise.backend.service;

import java.util.List;

import es.hua.exercise.backend.entity.ApplicationFormRating;

public interface ApplicationFormRatingService
{
	public List<ApplicationFormRating> getApplicationFormRatings();
	public void saveApplicationFormRating(ApplicationFormRating applicationFormRating);
	public ApplicationFormRating getApplicationFormRating(int id);
	public void deleteApplicationFormRating(int id);
}
