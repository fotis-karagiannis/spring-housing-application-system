package es.hua.exercise.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import es.hua.exercise.backend.dao.UserDAO;
import es.hua.exercise.backend.entity.User;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public List<User> getUsers()
	{
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User user)
	{
		userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public User getUser(int id)
	{
		return userDAO.getUser(id);
	}

	@Override
	@Transactional
	public void deleteUser(int id)
	{
		userDAO.deleteUser(id);
	}

}
