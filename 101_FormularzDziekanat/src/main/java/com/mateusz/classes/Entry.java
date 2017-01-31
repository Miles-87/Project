package com.mateusz.classes;

public class Entry {
	
	private int idEntry;
	private int idSchool;
	private int idStudent;
	
	public void setIdEntry(int idEntry)
	{
		this.idEntry =idEntry;
	}
	public void setIdSchool(int idSchool)
	{
		this.idSchool = idSchool;
	}
	public void setIdStudent(int idStudent)
	{
		this.idStudent = idStudent;
	}
	public int getIdEntry()
	{
		return idEntry;
	}
	public int getIdSchool()
	{
		return idSchool;
	}
	public int getIdStudent()
	{
		return idStudent;
	}
	public Entry(int idEntry, int idSchool, int idStudent)
	{
		setIdEntry(idEntry);
		setIdSchool(idSchool);
		setIdStudent(idStudent);
	}
	public String toString()
	{
		return getIdEntry()+ "" + getIdSchool() + " " + getIdStudent();
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idStudent;
		result = prime * result + idSchool;
		result = prime * result + idEntry;
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
		Entry other = (Entry) obj;
		if (idStudent != other.idStudent)
			return false;
		if (idSchool != other.idSchool)
			return false;
		if (idEntry != other.idEntry)
			return false;
		return true;
	}
}
