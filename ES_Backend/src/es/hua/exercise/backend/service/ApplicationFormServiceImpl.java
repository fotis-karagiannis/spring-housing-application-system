package es.hua.exercise.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.hua.exercise.backend.dao.ApplicationFormDAO;
import es.hua.exercise.backend.entity.ApplicationForm;

@Service
public class ApplicationFormServiceImpl implements ApplicationFormService
{
	@Autowired
	private ApplicationFormDAO applicationFormDAO;

	@Override
	@Transactional
	public List<ApplicationForm> getApplicationForms()
	{
		return applicationFormDAO.getApplicationForms();
	}

	@Override
	@Transactional
	public void saveApplicationForm(ApplicationForm applicationForm)
	{
		applicationFormDAO.saveApplicationForm(applicationForm);
	}

	@Override
	@Transactional
	public ApplicationForm getApplicationForm(int id)
	{
		return applicationFormDAO.getApplicationForm(id);
	}

	@Override
	@Transactional
	public void deleteApplicationForm(int id)
	{
		applicationFormDAO.deleteApplicationForm(id);
	}
}
