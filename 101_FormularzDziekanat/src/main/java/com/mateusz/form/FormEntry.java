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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mateusz.classes.Entry;
import com.mateusz.classes.School;
import com.mateusz.classes.Student;
import com.mateusz.databases.Database;
import com.mateusz.models.MyComboBoxModel;
import com.mateusz.models.MyCustomTableModel;

public class FormEntry extends JPanel implements ActionListener {
	private JLabel lName = new JLabel("Name"); 
	private JLabel lSurname = new JLabel("Surname");
	private JLabel lAge = new JLabel("Age");
	private JLabel lYear = new JLabel("Year");
	private JLabel lSchoolName = new JLabel("School name");
	private JLabel lCity = new JLabel("City");
	private JLabel lYearOfBuild = new JLabel("Year of build");
	private JLabel lDeanName = new JLabel("Dean name");
	private JLabel lIdStudent = new JLabel("Id student");
	private JLabel lIdSchool = new JLabel("Id school");
	private JButton bInsert = new JButton("Insert");
	private JButton bDelete = new JButton("Delete");
	private JTextField tfName = new JTextField(10);
	private JTextField tfSurname = new JTextField(10);
	private JTextField tfYear = new JTextField(10);
	private JTextField tfAge = new JTextField(10);
	private JTextField tfSchoolName = new JTextField(10);
	private JTextField tfCity = new JTextField(10);
	private JTextField tfYearOfBuild = new JTextField(10);
	private JTextField tfDeanName = new JTextField(10);
	private JComboBox<Integer> cbIdStudent;
	private JComboBox<Integer> cbIdSchool;
	private MyComboBoxModel modelCbStudent;
	private MyComboBoxModel modelCbSchool;
	
	JPanel panelUp;
	JPanel panelMiddle;
	JPanel panelDown;
	
	private List<Entry> list;
	private int idx;
	
	private FormConnected formConnected;
	
	public FormEntry(FormConnected formConnected)
	{
		
		super(new GridBagLayout());
		
		this.formConnected = formConnected;
		
		panelUp = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelUp = new GridBagConstraints();
		panelMiddle = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelMiddle = new GridBagConstraints();
		panelDown = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelDown = new GridBagConstraints();
		
		//dwa obiekty reprezentuja model - jeden model jest dla combobox od id studenta
		//drugi model jest od combobox id uczelnia
		
		modelCbStudent = new MyComboBoxModel(Database.selectStudentIds()); 
		modelCbSchool = new MyComboBoxModel(Database.selectSchoolIds());
		cbIdStudent = new JComboBox<>(modelCbStudent);//jak chcesz ustawic wlasny model to go podajesz jako argument konstruktora comboboxa
		cbIdStudent.addActionListener(this);
		cbIdSchool = new JComboBox<>(modelCbSchool);
		cbIdSchool.addActionListener(this);
	
		bInsert = new JButton("Insert");
		bDelete = new JButton("Delete");
		bInsert.addActionListener(this);
		bDelete.addActionListener(this);
		
		
		gbcPanelUp.gridx = 0;
		gbcPanelUp.gridy = 0;
		gbcPanelUp.ipadx = 20;
		gbcPanelUp.ipady = 20;
		gbcPanelUp.insets = new Insets(25, 25, 5, 50);
		panelUp.add(lIdStudent, gbcPanelUp);
		
		gbcPanelUp.gridx = 1;
		gbcPanelUp.gridy = 0;
		panelUp.add(cbIdStudent, gbcPanelUp);
		
		gbcPanelUp.gridx = 2;
		gbcPanelUp.gridy = 0;
		panelUp.add(lIdSchool, gbcPanelUp);
		
		gbcPanelUp.gridx = 3;
		gbcPanelUp.gridy = 0;
		panelUp.add(cbIdSchool,gbcPanelUp);
		
		gbcPanelMiddle.gridx = 0;
		gbcPanelMiddle.gridy = 0;
		gbcPanelMiddle.ipadx = 20;
		gbcPanelMiddle.ipady = 20;
		gbcPanelMiddle.insets = new Insets(35, 35, 35, 35);
		panelMiddle.add(lName, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 1;
		gbcPanelMiddle.gridy = 0;
		panelMiddle.add(tfName, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 2;
		gbcPanelMiddle.gridy = 0;
		panelMiddle.add(lSchoolName, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 3;
		gbcPanelMiddle.gridy = 0;
		panelMiddle.add(tfSchoolName, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 0;
		gbcPanelMiddle.gridy = 1;
		panelMiddle.add(lSurname, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 1;
		gbcPanelMiddle.gridy = 1;
		panelMiddle.add(tfSurname, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 2;
		gbcPanelMiddle.gridy = 1;
		panelMiddle.add(lCity, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 3;
		gbcPanelMiddle.gridy = 1;
		panelMiddle.add(tfCity, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 0;
		gbcPanelMiddle.gridy = 2;
		panelMiddle.add(lAge, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 1;
		gbcPanelMiddle.gridy = 2;
		panelMiddle.add(tfAge, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 2;
		gbcPanelMiddle.gridy = 2;
		panelMiddle.add(lYearOfBuild, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 3;
		gbcPanelMiddle.gridy = 2;
		panelMiddle.add(tfYearOfBuild, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 0;
		gbcPanelMiddle.gridy = 3;
		panelMiddle.add(lYear, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 1;
		gbcPanelMiddle.gridy = 3;
		panelMiddle.add(tfYear, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 2;
		gbcPanelMiddle.gridy = 3;
		panelMiddle.add(lDeanName, gbcPanelMiddle);
		
		gbcPanelMiddle.gridx = 3;
		gbcPanelMiddle.gridy = 3;
		panelMiddle.add(tfDeanName, gbcPanelMiddle);
		
		gbcPanelDown.gridx = 0;
		gbcPanelDown.gridy = 0;
		gbcPanelDown.ipadx = 20;
		gbcPanelDown.ipady = 20;
		gbcPanelDown.insets = new Insets(25, 25, 5, 25);
		panelDown.add(bInsert, gbcPanelDown);
		
		gbcPanelDown.gridx = 1;
		gbcPanelDown.gridy = 0;
		panelDown.add(bDelete, gbcPanelDown);
		
		
		
		
		
		GridBagConstraints gbcMain = new GridBagConstraints();
		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		add(panelUp, gbcMain);
		
		gbcMain.gridx = 0;
		gbcMain.gridy = 1;
		add(panelMiddle, gbcMain);
		
		gbcMain.gridx = 0;
		gbcMain.gridy = 2;
		add(panelDown, gbcMain);
		
		/*
		add(panelUp, BorderLayout.PAGE_START); 
		add(panelDown, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.PAGE_END);
		*/
		list = Database.selectEntry();
		if(list != null)
		{
			idx = 0;
			fillFormEntry();
		}
	}
	
	public void fillFormEntry()
	{
//		if (lista.isEmpty() == false)
//		{
//			tfId.setText(lista.get(idx).getIdWpis()+"");
//			/*
//			tf.setText(lista.get(idx).getIdStudenta()+"");
//			tf2.setText(lista.get(idx).getIdUczelni()+"");
//			*/
//		}
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		if(bInsert == e.getSource())
		{
			int idStudent = (int)modelCbStudent.getSelectedItem();
			int idSchool= (int)modelCbSchool.getSelectedItem();
			Database.insertEntry(idStudent, idSchool);
			list = Database.selectEntry();
			idx =0;
			
			//sprawdzamy co jest w bazie - test
			//System.out.println("AFTER INSERT");
			//for (Entry w : list)
			//{
			//	System.out.println(w);
			//}
			//list = Database.selectEntry();
			formConnected.updateTable();
			
		}
		else if(bDelete == e.getSource())
		{
			int idStudent = (int)modelCbStudent.getSelectedItem();
			int idSchool = (int)modelCbSchool.getSelectedItem();
			Database.dleteEntry(idStudent, idSchool);
			list = Database.selectEntry();
			idx = 0;
			
			//sprawdzamy co jest w bazie - test
			System.out.println("After DELETE");
			for (Entry w : list)
			{
				System.out.println(w);
			}
		}
		else if (cbIdStudent == e.getSource())
		{
			//System.out.println("AAA = " + modelCbStudent.getSelectedItem());
			int whichStudent = (int)modelCbStudent.getSelectedItem();
			//w zmiennej ktoryStudent masz Studenta, ktorego dane chcemy wypisac
			List<Student> listS = Database.selectStudent();
			for (Student s : listS)
			{
				//przegladasz kolejnych studentow w bazie i jezeli trafisz na studenta ktory ma takie id jak znaznaczyles
				//combo box to wypelniasz pola
				if (s.getId() == whichStudent)
				{
					tfName.setText(s.getName());
					tfSurname.setText(s.getSurname());
					tfYear.setText(s.getYear() + ""); //najszybsza konwersja na String - dodac ""
					tfAge.setText(s.getAge() + "");
				}
			}
		}
		else if (cbIdSchool == e.getSource())
		{
			int whichSchool = (int)modelCbSchool.getSelectedItem();
			List<School> listU = Database.selectSchool();
			for(School u : listU)
			{
				if(u.getId() == whichSchool)
				{
					tfSchoolName.setText(u.getSchoolName());
					tfCity.setText(u.getCity());
					tfYearOfBuild.setText(u.getYearOfBuild() + "");
					tfDeanName.setText(u.getDeanName());
				}
			}
		}
		
	}

	public MyComboBoxModel getModelCbStudent() {
		return modelCbStudent;
	}

	public MyComboBoxModel getModelCbSchool() {
		return modelCbSchool;
	}


	public JComboBox<Integer> getCbIdStudent() {
		return cbIdStudent;
	}

	public JComboBox<Integer> getCbIdSchool() {
		return cbIdSchool;
	}
	
}
