package es.hua.exercise.backend.service;

import java.util.List;

import es.hua.exercise.backend.entity.Authority;

public interface AuthorityService
{
	public List<Authority> getAuthorities();
	public void saveAuthority(Authority authorities);
	public Authority getAuthority(int id);
	public void deleteAuthority(int id);
}
