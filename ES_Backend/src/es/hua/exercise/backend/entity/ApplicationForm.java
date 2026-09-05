package es.hua.exercise.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application")
public class ApplicationForm implements Comparable<ApplicationForm>
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "personal_income")
	private int personalIncome;
	
	@Column(name = "family_income")
	private int familyIncome;
	
	@Column(name = "siblings_studying")
	private int siblingsStudying;
	
	@Column(name = "home_city")
	private String homeCity;
	
	@Column(name = "year_studying")
	private int yearStudying;
	
	@Column(name = "year_staying")
	private int yearStaying;
	
	@Column(name = "unemployed_parents")
	private int unemployedParents;

	@Column(name = "points") 
	private int points;
	
	@Column(name = "status") //Possible Values: Qualified, Aborted or Queued
	private String status;
	
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
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the personalIncome
	 */
	public int getPersonalIncome()
	{
		return personalIncome;
	}

	/**
	 * @param personalIncome the personalIncome to set
	 */
	public void setPersonalIncome(int personalIncome)
	{
		this.personalIncome = personalIncome;
	}

	/**
	 * @return the familyIncome
	 */
	public int getFamilyIncome()
	{
		return familyIncome;
	}

	/**
	 * @param familyIncome the familyIncome to set
	 */
	public void setFamilyIncome(int familyIncome)
	{
		this.familyIncome = familyIncome;
	}

	/**
	 * @return the siblingsStudying
	 */
	public int getSiblingsStudying()
	{
		return siblingsStudying;
	}

	/**
	 * @param siblingsStudying the siblingsStudying to set
	 */
	public void setSiblingsStudying(int siblingsStudying)
	{
		this.siblingsStudying = siblingsStudying;
	}

	/**
	 * @return the homeCity
	 */
	public String getHomeCity()
	{
		return homeCity;
	}

	/**
	 * @param homeCity the homeCity to set
	 */
	public void setHomeCity(String homeCity)
	{
		this.homeCity = homeCity;
	}

	/**
	 * @return the yearStudying
	 */
	public int getYearStudying()
	{
		return yearStudying;
	}

	/**
	 * @param yearStudying the yearStudying to set
	 */
	public void setYearStudying(int yearStudying)
	{
		this.yearStudying = yearStudying;
	}

	/**
	 * @return the yearStaying
	 */
	public int getYearStaying()
	{
		return yearStaying;
	}

	/**
	 * @param yearStaying the yearStaying to set
	 */
	public void setYearStaying(int yearStaying)
	{
		this.yearStaying = yearStaying;
	}

	/**
	 * @return the unemployedParents
	 */
	public int getUnemployedParents()
	{
		return unemployedParents;
	}

	/**
	 * @param unemployedParents the unemployedParents to set
	 */
	public void setUnemployedParents(int unemployedParents)
	{
		this.unemployedParents = unemployedParents;
	}

	/**
	 * @return the points
	 */
	public int getPoints()
	{
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points)
	{
		this.points = points;
	}

	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	public ApplicationForm() {}

	@Override
	public String toString()
	{
		return "ApplicationForm [id=" + id + ", username=" + username + ", personalIncome=" + personalIncome
				+ ", familyIncome=" + familyIncome + ", siblingsStudying=" + siblingsStudying + ", homeCity=" + homeCity
				+ ", yearStudying=" + yearStudying + ", yearStaying=" + yearStaying + ", unemployedParents="
				+ unemployedParents + ", points=" + points + ", status=" + status + "]";
	}
	
	//Method that is a toString but in JSON style
	public String jsonFormat() 
	{
		return "{\"id\":"+id+",\"username\":\""+username+"\",\"personalIncome\":"+personalIncome+",\"familyIncome\":"+familyIncome+",\"siblingsStudying\":"+siblingsStudying+",\"homeCity\":\""+homeCity+"\",\"yearStudying\":"+yearStudying+",\"yearStaying\":"+yearStaying+",\"unemployedParents\":"+unemployedParents+",\"points\":"+points+",\"status\":\""+status+"\"}";
	}	
	
	/**
	 * Calculates the form status, and if the form is to be queued, also calculates the form points.
	 * 
	 * @param universityCity : Location of the university, to be compared with student's home city
	 * @return
	 */
	public String calculateStatus(String universityCity)
	{
		if(this.personalIncome == 0 && this.unemployedParents == 2)
		{
			this.status = "Qualified";
			this.points = 0;
			
			return this.status;
		}
		
		if(this.yearStudying > 4  || this.yearStaying >= 4)
		{
			this.status = "Aborted";
			this.points = 0;
			
			return this.status;
		}
		
		// If none of the above, calculate points and mark it for the queue 	
		if(this.familyIncome < 10000)
		{
			this.points += 100;
		}
		else if(this.familyIncome < 15000)
		{
			this.points += 30;
		}
		
		if(this.siblingsStudying != 0)
		{
			this.points += this.siblingsStudying * 20;
		}
		
		if(!this.homeCity.equals(universityCity))
		{
			this.points += 50;
		}
		
		if(this.yearStaying != 0)
		{
			this.points -= 10 * this.yearStaying;
		}
		
		if(this.points < 0)
		{
			this.points = 0;
		}

		this.status = "Queued";
		
		return this.status;
	}

	@Override
	public int compareTo(ApplicationForm o)
	{
		if(this.points == o.points)
		{
			return 0;
		}
		else if(this.points > o.points) 
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
}
