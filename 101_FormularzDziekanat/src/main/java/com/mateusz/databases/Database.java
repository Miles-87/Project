package com.mateusz.databases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteConfig;

import com.mateusz.classes.Connected;
import com.mateusz.classes.Entry;
import com.mateusz.classes.School;
import com.mateusz.classes.Student;


public class Database {
	
	private static String DRV = "org.sqlite.JDBC"; 
	
	private static String DB = "jdbc:sqlite:Dziekanat.db"; 
	
	private static Connection conn; 
	
	private static Statement stat; 
	
	
	public static void connect() 
	{
		
		try {
			Class.forName(DRV);
			SQLiteConfig conf = new SQLiteConfig();
			conf.enforceForeignKeys(true);
			conn = DriverManager.getConnection(DB, conf.toProperties());
			stat = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createTables()
	{
		String createStudent = "create table if not exists Student (id integer primary key autoincrement, name varchar(50), surname varchar(50), age integer, year integer);";
		String createSchool = "create table if not exists School(id integer primary key autoincrement, yearOfBuild integer, city varchar(50), deanName varchar(50), schoolName varchar(50));";
		String createEntry = "create table if not exists Entry(id integer primary key autoincrement,idStudent integer, idSchool integer, foreign key (idStudent) references Student(id) on delete cascade on update cascade,foreign key (idSchool) references School(id) on delete cascade on update cascade);";
		
		try {
			stat.execute(createStudent);
			stat.execute(createSchool);
			stat.execute(createEntry);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void insertStudent(String name, String surname, int age, int year)
	{
		String insertS = "insert into Student(name, surname, age,  year) values (?,?,?,?);"; 
		try {
			PreparedStatement ps = conn.prepareStatement(insertS);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setInt(3,  age);
			ps.setInt(4, year);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();     
		}
	}
	
	public static void insertSchool(int yearOfBuild, String city, String deanName, String schoolName)
	{
		String insertU = "insert into School(yearOfBuild, city,deanName, schoolName) values (?,?,?,?);";
		try {
			PreparedStatement ps = conn.prepareStatement(insertU);
			ps.setInt(1, yearOfBuild);
			ps.setString(2, city);
			ps.setString(3, deanName);
			ps.setString(4, schoolName);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void insertEntry(int idStudent,int idSchool)
	{
		String insertE = "insert into Entry(idStudent,idSchool) values(?,?);";
		try {
			PreparedStatement ps = conn.prepareStatement(insertE);
			ps.setInt(1, idStudent);
			ps.setInt(2, idSchool);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Student> selectStudent()
	{
		List<Student> list = new ArrayList<Student>(); 
		String selectString = "SELECT * FROM Student"; 
		try {
			ResultSet result = stat.executeQuery(selectString);
			int id;
			String name;
			String surname;
			int year;
			int age;
			
			while(result.next())
			{
				id = result.getInt("id"); 
				name = result.getString("name");
				surname = result.getString("surname");
				age = result.getInt("age");
				year = result.getInt("year");
				
				list.add(new Student(id, name, surname, age, year));
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static List<Integer> selectStudentIds()
	{
		List<Integer> l = new ArrayList<>();
		String selectId = "SELECT id FROM Student";
		try {
			ResultSet result = stat.executeQuery(selectId);
			int StudentId;
			while(result.next())
			{
				StudentId = result.getInt("id");
				l.add(StudentId);
			}
			return l;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Integer> selectSchoolIds()
	{
		List<Integer> l = new ArrayList<>();
		String SelectId = "SELECT id FROM School";
		try {
			ResultSet result = stat.executeQuery(SelectId);
			int SchoolId;
			while(result.next())
			{
				SchoolId = result.getInt("id");
				l.add(SchoolId);
			}
			return l;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static List<School> selectSchool()
	{
		 List<School> list2 = new ArrayList<School>();
		 String selectString = "SELECT * FROM School";
		 try {
		 ResultSet result = stat.executeQuery(selectString);
		 int id;
		 int yearOfBuild;
		 String city;
		 String deanName;
		 String schoolName;
		 while(result.next())
		 {
			 id = result.getInt("id");
			 yearOfBuild = result.getInt("yearOfBuild");
			 city = result.getString("city");
			 deanName = result.getString("deanName");
			 schoolName = result.getString("schoolName");
			 list2.add(new School (id, schoolName, yearOfBuild, city, deanName));
		 }
		 	return list2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public static List<Entry> selectEntry()
	{
		List<Entry> list3 = new ArrayList<Entry>();	
		String selectString = "SELECT * FROM Entry";
		try {
			ResultSet result = stat.executeQuery(selectString);
			int idEntry;
			int idStudent;
			int idSchool;
			while(result.next())
			{
				idEntry = result.getInt("id");
				idStudent = result.getInt("idStudent");
				idSchool = result.getInt("idSchool");
				list3.add(new Entry(idEntry,idStudent,idSchool));
			}
			return list3;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	

	public static List<Connected> selectInnerJoin()
	{
		List<Connected> list3 = new ArrayList<Connected>();
		String innerJoin = "select Entry.id, Student.name, Student.surname, Student.age, Student.year, School.yearOfBuild, School.city, School.deanName, School.schoolName from Student inner join Entry on Student.id = Entry.idStudent inner join School on Entry.idSchool = School.id";
		try {
			ResultSet result = stat.executeQuery(innerJoin);
			int id;
			String name;
			String surname;
			int age;
			int year;
			int yearOfBuild;
			String city;
			String deanName;
			String schoolName;
			while(result.next())
			{
				id = result.getInt(1); 
				name = result.getString(2);
				surname = result.getString(3);
				age = result.getInt(4);
				year = result.getInt(5);
				yearOfBuild = result.getInt(6);
				city = result.getString(7);
				deanName = result.getString(8);
				schoolName = result.getString(9);
				list3.add(new Connected(id, name, surname, age, year, yearOfBuild, city, deanName, schoolName));		
			}
			return list3;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	
	}
	
	public static void updateStudent(int id, String name, String surname,int age, int year)
	{
		String update = "UPDATE Student SET name = ?, surname = ?,age = ?, year = ? WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(update);
			ps.setString(1, name);
			ps.setString(2,  surname);
			ps.setInt(3, age);
			ps.setInt(4,  year);
			ps.setInt(5, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateSchool(int id,String schoolName,int yearOfBuild,String city,String deanName)
	{
		String update = "UPDATE School SET schoolName = ?, yearOfBuild =?, city =?, deanName =? WHERE id =?";
		try {
			PreparedStatement ps = conn.prepareStatement(update);
			ps.setString(1, schoolName);
			ps.setInt(2, yearOfBuild);
			ps.setString(3, city);
			ps.setString(4, deanName);
			ps.setInt(5, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void updateWpis(int id,int idSchool, int idStudent)
	{
		String update = "UPDATE Entry SET idSchool =?, idStudent = ? WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(update);
			ps.setInt(1, idSchool);
			ps.setInt(2, idStudent);
			ps.setInt(3, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  static void deleteStudent(int id)
	{
		String delete = "DELETE FROM Student WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(delete);
			ps.setInt(1, id);
			ps.execute(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void deleteSchool(int id)
	{
		String delete = "DELETE FROM School WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(delete);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void dleteEntry(int idStudent, int idSchool)
	{
		String delete = "DELETE FROM Entry WHERE idStudent = ? AND idSchool = ?";
		   try {
			PreparedStatement ps = conn.prepareStatement(delete);
			ps.setInt(1, idStudent);
			ps.setInt(2, idSchool);
			ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public static List<Connected> filter(
			boolean studentNameActive, 
			boolean studentSurnameActive, 
			boolean schoolCityActive, 
			boolean studentAgeActive, 
			boolean schoolNameActive,
			List<String> nameList, 
			List<String> surnameList,
			List<String> schoolCityList,
			List<String> schoolNameList,
			int studentYearFrom,
			int studentYearTo)
	{
		List<Connected> list3 = new ArrayList<Connected>();
		String innerJoin = "select Entry.id, Student.name, Student.surname, Student.age, Student.year, "
				+ "School.yearOfBuild, School.city, School.deanName, School.schoolName "
				+ "from Student inner join Entry on Student.id = Entry.idStudent "
				+ "inner join School on Entry.idSchool = School.id WHERE 1 = 1 ";
		
		if (studentNameActive)
		{
			String stringStudentName = " AND Student.name IN ('" + String.join("','", nameList) + "')";
			innerJoin += stringStudentName;
		}		
		if(studentSurnameActive)
		{
			String stringStudentSurname = " AND Student.surname IN ('" + String.join("','", surnameList) + "')";
			innerJoin += stringStudentSurname;
		}
		if (schoolCityActive)
		{			
			String stringSchoolCity = " AND School.city IN ('" + String.join("','", schoolCityList) + "')";
			innerJoin += stringSchoolCity;
		}
		if(studentAgeActive)
		{
			String strinStudentYear = " AND Student.age BETWEEN " + studentYearFrom + " AND " + studentYearTo;
			innerJoin += strinStudentYear;
		}
		if(schoolNameActive)
		{
			String stringSchoolNameList = " AND School.schoolName IN ('" + String.join("','", schoolNameList) + "')";
			innerJoin += stringSchoolNameList;
		}
		try {
			ResultSet result = stat.executeQuery(innerJoin);
			int id;
			String name;
			String surname;
			int age;
			int year;
			int yearOfBuild;
			String city;
			String deanName;
			String schoolName;
			while(result.next())
			{
				id = result.getInt(1); 
				//zamiast nazw
				name = result.getString(2);
				surname = result.getString(3);
				age = result.getInt(4);
				year = result.getInt(5);
				yearOfBuild = result.getInt(6);
				city = result.getString(7);
				deanName = result.getString(8);
				schoolName = result.getString(9);
				list3.add(new Connected(id, name, surname, age, year, yearOfBuild, city, deanName, schoolName));		
			}
			return list3;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	
	}
	
	public static List<String> selectStudentNames()
	{
		String innerJoin = "select distinct Student.name "
				+ "from Student inner join Entry on Student.id = Entry.idStudent "
				+ "inner join School on Entry.idSchool = School.id";
		List<String> nameList = new ArrayList<>();
		
		try {
			ResultSet result = stat.executeQuery(innerJoin);
			
			while(result.next())
			{
				nameList.add(result.getString(1));		
			}
			return nameList;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static List<String> selectStudentSurnames()
	{
		String innerJoin = "select distinct Student.surname "
				+ "from Student inner join Entry on Student.id = Entry.idStudent "
				+ "inner join School on Entry.idSchool = School.id";
		List<String> surnameList = new ArrayList<>();
		
		try {
			ResultSet result = stat.executeQuery(innerJoin);
					
			while(result.next())
			{
				surnameList.add(result.getString(1));		
			}
			return surnameList;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}	
	}
	
	public static List<String> selectSchoolName()
	{
		String innerJoin = "select distinct School.schoolName "
				+ "from Student inner join Entry on Student.id = Entry.idStudent "
				+ "inner join School on Entry.idSchool = School.id";
		List<String> schoolName = new ArrayList<>();
		
		try {
			ResultSet result = stat.executeQuery(innerJoin);
			
			while(result.next())
			{
				schoolName.add(result.getString(1));		
			}
			return schoolName;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static List<String> selectSchoolCity()
	{
		String innerJoin = "select distinct School.city "
				+ "from Student inner join Entry on Student.id = Entry.idStudent "
				+ "inner join School on Entry.idSchool = School.id";
		List<String> schoolCity = new ArrayList<>();
		
		try {
			ResultSet result = stat.executeQuery(innerJoin);
			
			while(result.next())
			{
				schoolCity.add(result.getString(1));		
			}
			return schoolCity;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}		
	}
	
	public static List<Integer> selectStudentAge()
	{
		String innerJoin = "select distinct Student.age "
				+ "from Student inner join Entry on Student.id = Entry.idStudent "
				+ "inner join School on Entry.idSchool = School.id";
		List<Integer> studentAge = new ArrayList<>();
		
		try {
			ResultSet result = stat.executeQuery(innerJoin);
			
			while(result.next())
			{
				studentAge.add(Integer.parseInt(result.getString(1)));		
			}
			return studentAge;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}		
	}
}
                                                                                                                                                                                                                                                                                                                                       