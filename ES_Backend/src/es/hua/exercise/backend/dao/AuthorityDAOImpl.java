package es.hua.exercise.backend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.hua.exercise.backend.entity.Authority;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Authority> getAuthorities()
	{
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Authority> query = currentSession.createQuery("from Authorities", Authority.class);
		
		List<Authority> authorities = query.getResultList();
		
		return authorities;
	}

	@Override
	public void saveAuthority(Authority authorities)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(authorities);
	}

	@Override
	public Authority getAuthority(int id)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		Authority authority = currentSession.get(Authority.class, id);
		
		return authority;
	}

	@Override
	public void deleteAuthority(int id)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		Authority authority = currentSession.get(Authority.class, id);
		
		currentSession.delete(authority);
	}

}


