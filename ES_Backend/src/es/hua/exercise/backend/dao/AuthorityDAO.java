package es.hua.exercise.backend.dao;

import java.util.List;

import es.hua.exercise.backend.entity.Authority;

public interface AuthorityDAO
{
	public List<Authority> getAuthorities();
	public void saveAuthority(Authority authorities);
	public Authority getAuthority(int id);
	public void deleteAuthority(int id);
}
