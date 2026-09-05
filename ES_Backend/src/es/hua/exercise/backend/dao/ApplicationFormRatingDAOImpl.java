package es.hua.exercise.backend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.hua.exercise.backend.entity.ApplicationFormRating;

@Repository
public class ApplicationFormRatingDAOImpl implements ApplicationFormRatingDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ApplicationFormRating> getApplicationFormRatings()
	{
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<ApplicationFormRating> query = currentSession.createQuery("from ApplicationFormRating", ApplicationFormRating.class);
		
		List<ApplicationFormRating> applicationFormRatings = query.getResultList();
		
		return applicationFormRatings;
	}

	@Override
	public void saveApplicationFormRating(ApplicationFormRating applicationFormRating)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(applicationFormRating);
	}

	@Override
	public ApplicationFormRating getApplicationFormRating(int id)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		ApplicationFormRating applicationFormRating = currentSession.get(ApplicationFormRating.class, id);
		
		return applicationFormRating;
	}

	@Override
	public void deleteApplicationFormRating(int id)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		ApplicationFormRating applicationFormRating = currentSession.get(ApplicationFormRating.class, id);
		
		currentSession.delete(applicationFormRating);
	}	
}
