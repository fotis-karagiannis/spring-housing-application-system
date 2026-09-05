package es.hua.exercise.backend.service;

import java.util.List;

import es.hua.exercise.backend.entity.User;

public interface UserService
{
	public List<User> getUsers();
	public void saveUser(User user);
	public User getUser(int id);
	public void deleteUser(int id);
}
