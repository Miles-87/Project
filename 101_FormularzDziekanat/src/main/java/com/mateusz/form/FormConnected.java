package com.mateusz.form;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mateusz.classes.Connected;
import com.mateusz.databases.Database;
import com.mateusz.models.MyComboBoxModel;
import com.mateusz.models.MyCustomTableModel;
import com.mateusz.models.MyListCustomModel;

public class FormConnected extends JPanel {
	private JTextField tfId = new JTextField(10);
	private JTextField tfName = new JTextField(10);
	private JTextField tfSurname = new JTextField(10);
	private JTextField tfYear = new JTextField(10);
	private JTextField tfAge = new JTextField(10);
	private JTextField tfSchoolName = new JTextField(10);
	private JTextField tfCity = new JTextField(10);
	private JTextField tfDeanName = new JTextField(10);
	private JTextField tfYearOfBuild = new JTextField(10);
	private JLabel lFrom = new JLabel("From");
	private JLabel lTo = new JLabel("To");
	private JCheckBox jcbStudentName = new JCheckBox("Student name");
	private JCheckBox jcbStudentSurname = new JCheckBox("Student surname");
	private JCheckBox jcbSchoolCity = new JCheckBox("School city");
	private JCheckBox jcbSchoolName = new JCheckBox("School name");
	private JCheckBox jcbStudentAge = new JCheckBox("Student age");
	private JList<String> jlStudentName;
	private JList<String> jlStudentSurname;
	private JList<String> jlSchoolName;
	private JList<String> jlSchoolCity;
	private MyListCustomModel modelListStudentNames;
	private MyListCustomModel modelListStudentSurname;
	private MyListCustomModel modelListSchoolName;
	private MyListCustomModel modelListSchoolCity;
	private JButton bFiltrate = new JButton("Filtrate");
	private JButton bReset = new JButton("Reset");
	private JComboBox<Integer> jcbFrom;
	private JComboBox<Integer> jcbTo;
	private MyComboBoxModel comboBoxFrom;
	private MyComboBoxModel comboBoxTo;
	
	private JTable table;
	private MyCustomTableModel myCustomTableModel;
	
	private JPanel panelUp;
	private JPanel panelMedium;
	private JPanel panelDown;
	
	public void updateTable()
	{
		myCustomTableModel.update(Database.selectInnerJoin());
		table.updateUI();
		modelListStudentNames.update(Database.selectStudentNames());
		jlStudentName.updateUI();
		modelListStudentSurname.update(Database.selectStudentSurnames());
		jlStudentSurname.updateUI();
		modelListSchoolName.update(Database.selectSchoolName());
		jlSchoolName.updateUI();
		modelListSchoolCity.update(Database.selectSchoolCity());
		jlSchoolCity.updateUI();
		comboBoxFrom.update(Database.selectStudentAge());
		jcbFrom.updateUI();
		comboBoxTo.update(Database.selectStudentAge());
		jcbTo.updateUI();
	}
	
	public FormConnected()
	{
		super(new GridBagLayout());
		myCustomTableModel = new MyCustomTableModel(Database.selectInnerJoin());
		table = new JTable(myCustomTableModel);
		add(new JScrollPane(table));
		JPanel panelUp = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelUp = new GridBagConstraints();
		
		JPanel panelMedium = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelMedium = new GridBagConstraints();
		
		JPanel panelDown = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelDown = new GridBagConstraints();
		
		
		
		gbcPanelUp.gridx = 0;
		gbcPanelUp.gridy = 0;
		gbcPanelUp.ipadx = 5;
		gbcPanelUp.ipady = 5;
		gbcPanelUp.insets = new Insets(5, 5, 5, 5);
		panelUp.add(jcbStudentName, gbcPanelUp);
		
		gbcPanelUp.gridx = 1;
		gbcPanelUp.gridy = 0;
		panelUp.add(jcbStudentSurname, gbcPanelUp);
		
		gbcPanelUp.gridx = 2;
		gbcPanelUp.gridy = 0;
		panelUp.add(jcbSchoolCity, gbcPanelUp);
		
		gbcPanelUp.gridx = 3;
		gbcPanelUp.gridy = 0;
		panelUp.add(jcbSchoolName, gbcPanelUp);
		
		gbcPanelUp.gridx = 0;
		gbcPanelUp.gridy = 1;
		modelListStudentNames = new MyListCustomModel(Database.selectStudentNames());
		jlStudentName = new JList<>(modelListStudentNames);
		panelUp.add(jlStudentName, gbcPanelUp);
		
		gbcPanelUp.gridx = 1;
		gbcPanelUp.gridy = 1;
		modelListStudentSurname = new MyListCustomModel(Database.selectStudentSurnames());
		jlStudentSurname = new JList<>(modelListStudentSurname);
		panelUp.add(jlStudentSurname, gbcPanelUp);
		
		gbcPanelUp.gridx = 2;
		gbcPanelUp.gridy = 1;
		modelListSchoolCity = new MyListCustomModel(Database.selectSchoolCity());
		jlSchoolCity = new JList<>(modelListSchoolCity);
		panelUp.add(jlSchoolCity, gbcPanelUp);
		
		gbcPanelUp.gridx = 3;
		gbcPanelUp.gridy = 1;
		modelListSchoolName = new MyListCustomModel(Database.selectSchoolName());
		jlSchoolName = new JList<>(modelListSchoolName);
		panelUp.add(jlSchoolName, gbcPanelUp);
		
		
		gbcPanelUp.gridx = 0;
		gbcPanelUp.gridy = 2;
		panelUp.add(jcbStudentAge, gbcPanelUp);
		
		gbcPanelUp.gridx = 0;
		gbcPanelUp.gridy = 3;
		panelUp.add(lFrom, gbcPanelUp);
		
		gbcPanelUp.gridx = 1;
		gbcPanelUp.gridy = 3;
		comboBoxFrom = new MyComboBoxModel(Database.selectStudentAge());
		jcbFrom = new JComboBox<>(comboBoxFrom);
		panelUp.add(jcbFrom, gbcPanelUp);
		
		gbcPanelUp.gridx = 2;
		gbcPanelUp.gridy = 3;
		panelUp.add(lTo, gbcPanelUp);
		
		gbcPanelUp.gridx = 3;
		gbcPanelUp.gridy = 3;
		comboBoxTo = new MyComboBoxModel(Database.selectStudentAge());
		jcbTo = new JComboBox<>(comboBoxTo);
		panelUp.add(jcbTo, gbcPanelUp);
		
		
		gbcPanelDown.gridx = 0;
		gbcPanelDown.gridy = 0;
		gbcPanelDown.ipadx = 10;
		gbcPanelDown.ipady = 10;
		gbcPanelDown.insets = new Insets(50, 50, 5, 50);
		bFiltrate.addActionListener(e -> {
			tableFiltering();
		});
		panelDown.add(bFiltrate, gbcPanelDown);
		
		gbcPanelDown.gridx = 1;
		gbcPanelDown.gridy = 0;
		gbcPanelDown.insets = new Insets(50, 50, 5, 50);
		bReset.addActionListener(e -> {
			resetFilter();
		});
		panelDown.add(bReset, gbcPanelDown);
		
		
		GridBagConstraints gbcMain = new GridBagConstraints();
		gbcMain.gridx = 0;
		gbcMain.gridy = 1;
		add(panelUp, gbcMain);
		
		gbcMain.gridx = 0;
		gbcMain.gridy = 2;
		add(panelDown, gbcMain);
	}
	
	public void tableFiltering()
	{
		//get filter params
		boolean isFilterName = jcbStudentName.isSelected();
		boolean isFilterSurname = jcbStudentSurname.isSelected();
		boolean isFilterSchoolName = jcbSchoolName.isSelected();
		boolean isFilterSchoolCity = jcbSchoolCity.isSelected();
		boolean isFilterAge = jcbStudentAge.isSelected();
		
		
		List<String> filterNames = jlStudentName.getSelectedValuesList();		
		List<String> filterSurnames = jlStudentSurname.getSelectedValuesList();
		List<String> filterSchoolName = jlSchoolName.getSelectedValuesList();
		List<String> filterSchoolCity = jlSchoolCity.getSelectedValuesList();		
		int ageFrom = (int)jcbFrom.getSelectedItem();
		int ageTo = (int)jcbTo.getSelectedItem();

	
		myCustomTableModel.update(Database.filter(
			isFilterName, 
			isFilterSurname, 
			isFilterSchoolCity,
			isFilterAge,
			isFilterSchoolName,					
			filterNames, 
			filterSurnames, 
			filterSchoolCity,
			filterSchoolName,
			ageTo,
			ageFrom)			
		);
		table.updateUI();
		
	}
	
	void resetFilter()
	{
		myCustomTableModel.update(Database.selectInnerJoin());
		table.updateUI();
	}
}
