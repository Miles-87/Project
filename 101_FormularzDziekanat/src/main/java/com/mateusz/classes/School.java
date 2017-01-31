package com.mateusz.classes;

public class School {
	private int id;
	private int yearOfBuild;
	private String city;
	private String deanName;
	private String schoolName;
	
	public void setId(int id)
	{
		this.id = id;
	}
	public void setYearOfBuild(int yearOfBuild)
	{
		this.yearOfBuild = yearOfBuild;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public void setDeanName(String deanName)
	{
		this.deanName = deanName;
	}
	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}
	public int getId()
	{
		return id;
	}
	public String getSchoolName()
	{
		return schoolName;
	}
	public int getYearOfBuild()
	{
		return yearOfBuild;
	}
	public String getCity()
	{
		return city;
	}
	public String getDeanName()
	{
		return deanName;
	}
	public School(int id,String schoolName, int yearOfBuild, String city, String deanName)
	{
		setId(id);
		setSchoolName(schoolName);
		setYearOfBuild(yearOfBuild);
		setCity(city);
		setDeanName(deanName);
	}
	public String toString()
	{
		return getId()+ " " + getSchoolName()+ " " + getYearOfBuild() + " " + getCity() + " " + getDeanName();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((schoolName == null) ? 0 : schoolName.hashCode());
		result = prime
				* result
				+ ((deanName == null) ? 0 : deanName.hashCode());
		result = prime * result + yearOfBuild;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		School other = (School) obj;
		if (id != other.id)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (schoolName == null) {
			if (other.schoolName != null)
				return false;
		} else if (!schoolName.equals(other.schoolName))
			return false;
		if (deanName == null) {
			if (other.deanName != null)
				return false;
		} else if (!deanName.equals(other.deanName))
			return false;
		if (yearOfBuild != other.yearOfBuild)
			return false;
		return true;
	}
	
	
}
