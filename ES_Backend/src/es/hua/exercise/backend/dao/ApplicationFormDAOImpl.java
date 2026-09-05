package es.hua.exercise.backend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import es.hua.exercise.backend.entity.ApplicationForm;

@Repository
public class ApplicationFormDAOImpl implements ApplicationFormDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ApplicationForm> getApplicationForms()
	{
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<ApplicationForm> query = currentSession.createQuery("from ApplicationForm", ApplicationForm.class);
		
		List<ApplicationForm> applications = query.getResultList();
		
		return applications;
	}

	@Override
	public void saveApplicationForm(ApplicationForm applicationForm)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(applicationForm);
	}

	@Override
	public ApplicationForm getApplicationForm(int id)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		ApplicationForm applicationForm = currentSession.get(ApplicationForm.class, id);
		
		return applicationForm;
	}

	@Override
	public void deleteApplicationForm(int id)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		ApplicationForm applicationForm = currentSession.get(ApplicationForm.class, id);
		
		currentSession.delete(applicationForm);
	}

}
