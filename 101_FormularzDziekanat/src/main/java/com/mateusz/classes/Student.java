package com.mateusz.classes;

public class Student {

	private int id;
	private String name;
	private String surname;
	private int age;
	private int year;
	
	
	public void setId(int id)
	{
		this.id = id;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getSurname()
	{
		return surname;
	}
	public int getYear()
	{
		return year;
	}
	public int getAge()
	{
		return age;
	}
	public Student(int id,String name,String surname, int age, int year)
	{
		setId(id);
		setName(name);
		setSurname(surname);
		setAge(age);
		setYear(year);
	}
	public String toString()
	{
		return getId()+ " " + getName()+ " " + getSurname() + " " + getAge() + " " + getYear();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + year;
		result = prime * result + age;
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
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (year != other.year)
			return false;
		if (age != other.age)
			return false;
		return true;
	}
	
	
	
}
