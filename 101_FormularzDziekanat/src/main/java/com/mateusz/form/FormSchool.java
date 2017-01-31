package com.mateusz.form;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mateusz.classes.School;
import com.mateusz.classes.Student;
import com.mateusz.databases.Database;

public class FormSchool extends JPanel{
	private JButton bLeft = new JButton("<<<");
	private JButton bRight = new JButton(">>>");
	private JLabel lSchoolName = new JLabel("Name");
	private JLabel lCity = new JLabel("City");
	private JLabel lYearOfBuild = new JLabel("Year of build");
	private JLabel lDeanName = new JLabel("Dean name");
	private JTextField tfSchoolName = new JTextField(10);
	private JTextField tfCity = new JTextField(10);
	private JTextField tfYearOfBuild= new JTextField(10);
	private JTextField tfDeanName = new JTextField(10);
	private JTextField tfId = new JTextField(10);
	private JButton bInsert = new JButton("Insert");
	private JButton bUpdate = new JButton("Update");
	private JButton bDelete = new JButton("Delete");
	
	private List<School> list;
	private int idx;
	
	public FormSchool()
	{
		super(new GridBagLayout());
		
		JPanel panelUp = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelUp = new GridBagConstraints();
		JPanel panelDown = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelDown = new GridBagConstraints();
		JPanel panelButtons = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelButtons = new GridBagConstraints();
		
		bLeft.addActionListener(e -> {
			idx--;
			if(idx<0)
			{
				idx = list.size()-1;
			}
			fillFormSchool();}
		);
		
		bRight.addActionListener(e -> {
			idx++;
			if(idx >= list.size())
			{
				idx=0;
			}
			fillFormSchool();}
		);
		
		bInsert = new JButton("Insert");
		bInsert.addActionListener(e -> {
			String schoolName = tfSchoolName.getText();
			String city = tfCity.getText();
			int yearsOfBuild = Integer.parseInt(tfYearOfBuild.getText());
			String deanName = tfDeanName.getText();
			Database.insertSchool(yearsOfBuild, city, deanName, schoolName);
			list = Database.selectSchool();
			idx = 0;
			fillFormSchool();
		});
		
		bUpdate = new JButton("Uptade");
		bUpdate.addActionListener(e -> {
			int id = Integer.parseInt(tfId.getText());
			String SchoolName = tfSchoolName.getText();
			String City = tfCity.getText();
			int YearOfBuild = Integer.parseInt(tfYearOfBuild.getText());
			String DeanName = tfDeanName.getText();
			Database.updateSchool(id, SchoolName, YearOfBuild, City, DeanName);
			list = Database.selectSchool();
			idx =0;
			fillFormSchool();
		});
		
		bDelete = new JButton("Delete");
		bDelete.addActionListener(e -> {
			int id = Integer.parseInt(tfId.getText());
			Database.deleteSchool(id);
			list = Database.selectSchool();
			idx =0;
			fillFormSchool();
		});
		
		gbcPanelUp.gridx = 0;
		gbcPanelUp.gridy = 0;
		gbcPanelUp.ipadx = 20;
		gbcPanelUp.ipady = 20;
		gbcPanelUp.insets = new Insets(35, 35, 35, 35);
		panelUp.add(bLeft, gbcPanelUp);
		
		gbcPanelUp.gridx = 1;
		gbcPanelUp.gridy = 0;
		panelUp.add(tfId, gbcPanelUp);
		
		gbcPanelUp.gridx = 2;
		gbcPanelUp.gridy = 0;
		panelUp.add(bRight, gbcPanelUp);
		
		//-------------------------------------
		gbcPanelDown.gridx = 0;
		gbcPanelDown.gridy = 0;
		gbcPanelDown.ipadx = 20;
		gbcPanelDown.ipady = 20;
		gbcPanelDown.insets = new Insets(35, 35, 35, 35);
		panelDown.add(lSchoolName, gbcPanelDown);
		
		gbcPanelDown.gridx = 1;
		gbcPanelDown.gridy = 0;
		panelDown.add(tfSchoolName, gbcPanelDown);
		
		gbcPanelDown.gridx = 0;
		gbcPanelDown.gridy = 1;
		panelDown.add(lCity, gbcPanelDown);
		
		gbcPanelDown.gridx = 1;
		gbcPanelDown.gridy = 1;
		panelDown.add(tfCity, gbcPanelDown);
		
		gbcPanelDown.gridx = 0;
		gbcPanelDown.gridy = 2;
		panelDown.add(lYearOfBuild, gbcPanelDown);
		
		gbcPanelDown.gridx = 1;
		gbcPanelDown.gridy = 2;
		panelDown.add(tfYearOfBuild, gbcPanelDown);
		
		gbcPanelDown.gridx = 0;
		gbcPanelDown.gridy = 3;
		panelDown.add(lDeanName, gbcPanelDown);
		gbcPanelDown.gridx = 1;
		gbcPanelDown.gridy = 3;
		panelDown.add(tfDeanName, gbcPanelDown);
		
		//----------------------------------------
		gbcPanelButtons.gridx = 0;
		gbcPanelButtons.gridy = 0;
		gbcPanelButtons.ipadx = 20;
		gbcPanelButtons.ipady = 20;
		gbcPanelButtons.insets = new Insets(35, 35, 35, 35);
		panelButtons.add(bInsert, gbcPanelButtons);
		
		gbcPanelButtons.gridx = 1;
		gbcPanelButtons.gridy = 0;
		panelButtons.add(bUpdate, gbcPanelButtons);
		
		gbcPanelButtons.gridx = 2;
		gbcPanelButtons.gridy = 0;
		panelButtons.add(bDelete, gbcPanelButtons);
		
		GridBagConstraints gbcMain = new GridBagConstraints();
		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		add(panelUp, gbcMain);
		
		gbcMain.gridx = 0;
		gbcMain.gridy = 1;
		add(panelDown, gbcMain);
		
		gbcMain.gridx = 0;
		gbcMain.gridy = 2;
		add(panelButtons, gbcMain);
		
		list = Database.selectSchool();
		if(list != null)
		{
			idx = 0;
			fillFormSchool();
		}
	}
	
	/*
	public Student getStudentFromaFields()
	{
		try
		{
		String schoolName = tfSchoolName.getText();
		String city = tfCity.getText();
		int yearsOfBuild = Integer.parseInt(tfYearOfBuild.getText());
		String deanName = tfDeanName.getText();
		}
		catch(Exception e)
		{
			System.out.println("Nieprawidlowa wartosc pola");
		}
	}
	*/
	
	public void fillFormSchool()
	{
		if (list.isEmpty() == false)
		{
			tfId.setText(list.get(idx).getId()+"");
			tfSchoolName.setText(list.get(idx).getSchoolName());
			//tfMiejscowosc to obiekt przechowujacy tekst
			//ma on metode setText ktora zapisuje do niego tekst
			//jaki to tekst dostaje w argumencie, tym argumentem jest
			//lista.get(idx).getMiejscowosc()
			///wiec lista.get(idx) to pobranie elementu o numerze idx
			//a potem jeszcze odwolanie sie za pomoca getMiejscowosc do 
			//nazwy miejscowosci tego obiektu
			tfCity.setText(list.get(idx).getCity());
			tfYearOfBuild.setText(list.get(idx).getYearOfBuild()+"");
			tfDeanName.setText(list.get(idx).getDeanName());
		}
	}
	
}
