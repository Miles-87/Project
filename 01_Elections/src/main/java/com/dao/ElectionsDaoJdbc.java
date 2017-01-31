package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.sqlite.SQLiteConfig;

import com.elections.classes.Candidate;
import com.elections.classes.Constituency;
import com.elections.classes.Voter;

public class ElectionsDaoJdbc implements ElectionsDao {

	// singleton
	private static ElectionsDaoJdbc electionsDaoJdbc;

	// database vars
	private final static String DB_URL = "org.sqlite.JDBC";
	private final static String DB_NAME = "jdbc:sqlite:elections.db";
	private static Connection conn;
	private static Statement stat;

	private ElectionsDaoJdbc() {
		try {
			Class.forName(DB_URL);
			SQLiteConfig conf = new SQLiteConfig();
			conf.enforceForeignKeys(true);
			conn = DriverManager.getConnection(DB_NAME, conf.toProperties());
			stat = conn.createStatement();

			createAndFillTableConstituency();
			createAndFillTableCandidate();
			createAndFillTableVoter();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ElectionsDaoJdbc getInstance() {
		if (electionsDaoJdbc == null) {
			electionsDaoJdbc = new ElectionsDaoJdbc();
		}
		return electionsDaoJdbc;
	}

	@Override
	public Candidate getCandidate() {
		List<Candidate> candidatesList = new ArrayList<>();
		String selectCandidates = "SELECT C1.firstName, C1.lastName, C1.votes, C2.name "
				+ "FROM Candidate C1 INNER JOIN Constituency C2 " + "ON C2.id = C1.constituencyId";
		try {
			ResultSet result = stat.executeQuery(selectCandidates);
			String firstName;
			String lastName;
			int votes;
			String name;
			while (result.next()) {
				firstName = result.getString(1);
				lastName = result.getString(2);
				votes = result.getInt(3);
				name = result.getString(4);
				candidatesList.add(new Candidate(firstName, lastName, votes, Constituency.valueOf(name)));
			}
			Random rnd = new Random();
			return candidatesList.get(rnd.nextInt(candidatesList.size()));
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Voter getVoter() {
		List<Voter> voterList = new ArrayList<>();
		String selectVoter = "SELECT V1.id, V2.name " + "FROM Voter V1 INNER JOIN Constituency V2 "
				+ "ON V2.id = V1.constituencyId";
		try {
			ResultSet result = stat.executeQuery(selectVoter);
			int id;
			String name;
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				voterList.add(new Voter(id, Constituency.valueOf(name)));
			}
			Random rnd = new Random();
			return voterList.get(rnd.nextInt(voterList.size()));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// databse methods
	private void createAndFillTableCandidate() throws SQLException {
		String sqlCreateTableCandidate = "CREATE TABLE IF NOT EXISTS Candidate ( "
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "firstName VARCHAR(50) NOT NULL, "
				+ "lastName VARCHAR(50) NOT NULL, " + "votes INTEGER NOT NULL, " + "constituencyId INTEGER NOT NULL, "
				+ "FOREIGN KEY (constituencyId) REFERENCES Constituency(id) ON DELETE CASCADE ON UPDATE CASCADE"
				+ " );";

		stat.execute(sqlCreateTableCandidate);

		String sqlInsertCandidate = "";
	
	}

	private void createAndFillTableVoter() throws SQLException {
		String sqlCreateTableVoter = "CREATE TABLE IF NOT EXISTS Voter ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "constituencyId VARCHAR(50) NOT NULL, "
				+ "FOREIGN KEY (constituencyId) REFERENCES Constituency(id) ON DELETE CASCADE ON UPDATE CASCADE"
				+ " );";
		stat.execute(sqlCreateTableVoter);

		String sqlInsertVoter = "";

	}

	private void createAndFillTableConstituency() throws SQLException {
		String sqlCreateTableConstituency = "CREATE TABLE IF NOT EXISTS CONSTITUENCY ( "
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name VARCHAR(50) NOT NULL);";
		stat.execute(sqlCreateTableConstituency);

		String sqlInsertConstituency = "";
		sqlInsertConstituency = "INSERT INTO CONSTITUENCY VALUES ( null, 'WEST_DISTRICT')";
		stat.execute(sqlInsertConstituency);
		sqlInsertConstituency = "INSERT INTO CONSTITUENCY VALUES ( null, 'EAST_DISTRICT')";
		stat.execute(sqlInsertConstituency);
		sqlInsertConstituency = "INSERT INTO CONSTITUENCY VALUES ( null, 'SOUTH_DISTRICT')";
		stat.execute(sqlInsertConstituency);
		sqlInsertConstituency = "INSERT INTO CONSTITUENCY VALUES ( null, 'NORTH_DISTRICT')";
		stat.execute(sqlInsertConstituency);
	}

}
