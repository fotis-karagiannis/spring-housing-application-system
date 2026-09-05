package es.hua.exercise.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.hua.exercise.backend.dao.AuthorityDAO;
import es.hua.exercise.backend.entity.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService
{
	@Autowired
	private AuthorityDAO authorityDAO;

	@Override
	@Transactional
	public List<Authority> getAuthorities()
	{
		return authorityDAO.getAuthorities();
	}

	@Override
	@Transactional
	public void saveAuthority(Authority authorities)
	{
		authorityDAO.saveAuthority(authorities);
	}

	@Override
	@Transactional
	public Authority getAuthority(int id)
	{
		return authorityDAO.getAuthority(id);
	}

	@Override
	@Transactional
	public void deleteAuthority(int id)
	{
		authorityDAO.deleteAuthority(id);
	}

}
