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

import com.mateusz.classes.Student;
import com.mateusz.databases.Database;

public class FormStudent extends JPanel	{
	private JButton bLeft = new JButton("<<<");
	private JButton bRight = new JButton(">>>");
	private JLabel lName = new JLabel("Name");
	private JLabel lSurname = new JLabel("Surname");
	private JLabel lYear = new JLabel("Year");
	private JLabel lAge = new JLabel("Age");
	private JTextField tfId = new JTextField(10);
	private JTextField tfName = new JTextField(10);
	private JTextField tfSurname = new JTextField(10);
	private JTextField tfYear = new JTextField(10);
	private JTextField tfAge = new JTextField(10);
	private JButton bInsert = new JButton("Insert");
	private JButton bUpdate = new JButton("Update");
	private JButton bDelete = new JButton("Delete");
	
	JPanel panelUp;
	JPanel panelDown;
	JPanel panelButton;
	
	private List<Student> list;
	private int idx; 
	
	public FormStudent()
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
			fillFormStudent();}
		);
		
		bRight.addActionListener(e -> {
			idx++;
			if(idx >= list.size())
			{
				idx=0;
			}
			fillFormStudent();}
		);
		
		bInsert = new JButton("Insert");
		bInsert.addActionListener(e -> {
			String name = tfName.getText();
			String surname = tfSurname.getText();
			int year = Integer.parseInt(tfYear.getText());
			int age = Integer.parseInt(tfAge.getText());
			Database.insertStudent(name, surname, age, year);
			list = Database.selectStudent();
			idx = 0;
			fillFormStudent();
		});
		
		bUpdate = new JButton("Uptade");
		bUpdate.addActionListener(e -> {
			int id = Integer.parseInt(tfId.getText());
			String name = tfName.getText();
			String surname = tfSurname.getText();
			int year = Integer.parseInt(tfYear.getText());
			int age = Integer.parseInt(tfAge.getText());
			Database.updateStudent(id, name, surname, age, year);
			list = Database.selectStudent();
			idx =0;
			fillFormStudent();
		});
		
		bDelete = new JButton("Delete");
		bDelete.addActionListener(e -> {
			int id = Integer.parseInt(tfId.getText());
			Database.deleteStudent(id);
			list = Database.selectStudent();
			idx =0;
			fillFormStudent();
		});
		
		gbcPanelUp.gridx = 0;
		gbcPanelUp.gridy = 0;
		gbcPanelUp.ipadx = 20;
		gbcPanelUp.ipady = 20;
		gbcPanelUp.insets = new Insets(35,35,35,35);
		panelUp.add(bLeft, gbcPanelUp);
		
		gbcPanelUp.gridx = 1;
		gbcPanelUp.gridy = 0;
		panelUp.add(tfId, gbcPanelUp);
		
		gbcPanelUp.gridx = 2;
		gbcPanelUp.gridy = 0;
		panelUp.add(bRight, gbcPanelUp);
		
		gbcPanelDown.gridx = 0;
		gbcPanelDown.gridy = 0;
		gbcPanelDown.ipadx = 20;
		gbcPanelDown.ipady = 20;
		gbcPanelDown.insets = new Insets(35, 35, 35, 35);
		panelDown.add(lName, gbcPanelDown);
		
		gbcPanelDown.gridx = 1;
		gbcPanelDown.gridy = 0;
		panelDown.add(tfName, gbcPanelDown);
		
		gbcPanelDown.gridx = 0;
		gbcPanelDown.gridy = 1;
		panelDown.add(lSurname, gbcPanelDown);
		
		gbcPanelDown.gridx = 1;
		gbcPanelDown.gridy = 1;
		panelDown.add(tfSurname, gbcPanelDown);
		
		gbcPanelDown.gridx = 0;
		gbcPanelDown.gridy = 2;
		
		panelDown.add(lYear, gbcPanelDown);
		
		gbcPanelDown.gridx = 1;
		gbcPanelDown.gridy = 2;
		panelDown.add(tfYear, gbcPanelDown);
		
		gbcPanelDown.gridx = 0;
		gbcPanelDown.gridy = 3;
		panelDown.add(lAge, gbcPanelDown);
		
		gbcPanelDown.gridx = 1;
		gbcPanelDown.gridy = 3;
		panelDown.add(tfAge, gbcPanelDown);
		
		gbcPanelButtons.gridx = 0;
		gbcPanelButtons.gridy = 0;
		gbcPanelButtons.ipadx = 20;
		gbcPanelButtons.ipady = 20;
		gbcPanelButtons.insets = new Insets(35,35, 35, 35);
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
		
		list = Database.selectStudent();
		if (list != null) //cokolwiek pobralo
		{
			idx = 0; 
			fillFormStudent(); 
		}
	}
	
	public void fillFormStudent()
	{
		if (list.isEmpty() == false)
		{
			tfId.setText(list.get(idx).getId() + "");
			tfName.setText(list.get(idx).getName());
			tfSurname.setText(list.get(idx).getSurname());
			tfYear.setText(list.get(idx).getYear()+"");
			tfAge.setText(list.get(idx).getAge()+"");
		}
		else
		{
			tfId.setText("");
			tfName.setText("");
			tfSurname.setText("");
			tfYear.setText("");
			tfAge.setText("");
		}
	}

		
}

