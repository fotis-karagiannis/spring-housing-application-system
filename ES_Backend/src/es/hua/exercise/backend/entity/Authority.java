package es.hua.exercise.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "authority")
	private String authority;
	
	public Authority() {}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * @return the authority
	 */
	public String getAuthority()
	{
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority)
	{
		this.authority = authority;
	}

	@Override
	public String toString()
	{
		return "Authority [id=" + id + ", userName=" + userName + ", authority=" + authority + "]";
	}
}
