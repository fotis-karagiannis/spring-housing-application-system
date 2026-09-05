package es.hua.exercise.backend.dao;

import java.util.List;
import es.hua.exercise.backend.entity.ApplicationForm;

public interface ApplicationFormDAO
{
	public List<ApplicationForm> getApplicationForms();
	public void saveApplicationForm(ApplicationForm applicationForm);
	public ApplicationForm getApplicationForm(int id);
	public void deleteApplicationForm(int id);
}
