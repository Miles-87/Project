package com.mateusz.classes;

public class Connected {

		private int id;
		private String name;
		private String surname;
		private int age;
		private int year;
		private String schoolName;
		private String city;
		private String deanName;
		private int yearOfBuild;
		
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
		public void setSchoolName(String schoolName)
		{
			this.schoolName = schoolName;
		}
		public void setCity(String city)
		{
			this.city = city;
		}
		public void setDeanName(String deanName)
		{
			this.deanName = deanName;
		}
		public void setYearOfBuild(int yearOfBuild)
		{
			this.yearOfBuild = yearOfBuild;
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
		public int getAge()
		{
			return age;
		}
		public int getYear()
		{
			return year;
		}
		public int getYearOfBuild()
		{
			return yearOfBuild;
		}
		public String getSchoolName()
		{
			return schoolName;
		}
		public String getDeanName()
		{
			return deanName;
		}
		public String getCity()
		{
			return city;
		}
		public Connected(int id,String name,String surname, int age, int year, int yearOfBuild, String schoolName, String deanName, String city)
		{
			setId(id);
			setName(name);
			setSurname(surname);
			setAge(age);
			setYear(year);
			setYearOfBuild(yearOfBuild);
			setSchoolName(schoolName);
			setCity(city);
			setDeanName(deanName);
		}
		
		public Connected()
		{
			
		}
		
		public String toString()
		{
			return getId() + " " + getName() + " " + getSurname() + " " + getAge() +" " + getYear() + " " + getYearOfBuild() +  " " +getCity() + " " + getDeanName()+ " " + getSchoolName();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((city == null) ? 0 : city.hashCode());
			result = prime * result + ((schoolName == null) ? 0 : schoolName.hashCode());
			result = prime * result + ((surname == null) ? 0 : surname.hashCode());
			result = prime * result + ((deanName == null) ? 0 : deanName.hashCode());
			result = prime * result + year;
			result = prime * result + yearOfBuild;
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
			Connected other = (Connected) obj;
			if (id != other.id)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
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
			if (surname == null) {
				if (other.surname != null)
					return false;
			} else if (!surname.equals(other.surname))
				return false;
			if (deanName == null) {
				if (other.deanName != null)
					return false;
			} else if (!deanName.equals(other.deanName))
				return false;
			if (year != other.year)
				return false;
			if (yearOfBuild != other.yearOfBuild)
				return false;
			if (age != other.age)
				return false;
			return true;
		}
		
		
		
}
