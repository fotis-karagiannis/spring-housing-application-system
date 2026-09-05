package es.hua.exercise.backend.dao;

import java.util.List;

import es.hua.exercise.backend.entity.User;

public interface UserDAO
{
	public List<User> getUsers();
	public void saveUser(User user);
	public User getUser(int id);
	public void deleteUser(int id);
}
