package com.mateusz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import com.mateusz.databases.Database;
import com.mateusz.form.PrimaryPanel;

/**
 * Hello world!
 *
 */
public class App {
	public static void createGUI() {
		JFrame frame = new JFrame("WINDOW");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PrimaryPanel p = new PrimaryPanel();
		p.setVisible(true);
		frame.setContentPane(p);
		frame.setJMenuBar(p.createMenuBar());

		frame.setVisible(true);
		frame.pack();
		frame.setResizable(false);
	}

	public static void main(String[] args) {
		Database.connect();
		Database.createTables();
		
		System.out.println("-----------------------------");
		Database.selectInnerJoin().forEach(System.out::println);
		List<String> nameList = new ArrayList<>();
		Collections.addAll(nameList, "Jan");
		List<String> surnameList = new ArrayList<>();
		Collections.addAll(surnameList, "Makuch");
		List<String> schoolCityList = new ArrayList<>();
		Collections.addAll(schoolCityList, "Sopot", "Krk");
		int studentYearFrom = 1;
		int studentYearTo = 3;
		List<String> schoolNameList = new ArrayList<>();
		Collections.addAll(schoolNameList, "SWPS", "UJ");
		System.out.println("-----------------------------");
		
		boolean studentNameActive = true; 
		boolean studentSurnameActive = true; 
		boolean schoolCityActive = true; 
		boolean studentYearActive = true; 
		boolean schoolNameActive = true;
		
		Database.filter(studentNameActive, studentSurnameActive, schoolCityActive, studentYearActive, schoolNameActive, nameList, surnameList, schoolCityList, schoolNameList, studentYearFrom, studentYearTo);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});
	}
}