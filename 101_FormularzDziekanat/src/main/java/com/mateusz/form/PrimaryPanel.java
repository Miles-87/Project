package com.mateusz.form;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.mateusz.databases.Database;

public class PrimaryPanel extends JPanel implements ActionListener{

	private JMenuItem itemFormStudent;
	private JMenuItem itemFormSchool;
	private JMenuItem itemFormEntry;
	private JMenuItem itemConnected;
	
	
	
	FormStudent fs;
	FormSchool fu;
	FormEntry fw;
	FormConnected fl; 
	
	
	public PrimaryPanel()
	{
		super(new CardLayout()); 
		fs = new FormStudent();
		fu = new FormSchool();
		fl = new FormConnected();
		fw = new FormEntry(fl);
		
		
		
		
		add(fs, "fs");
		add(fu, "fu");
		add(fw, "fw");
		add(fl, "fl");
		
	}
	
	public JMenuBar createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu menuForm = new JMenu("FORM");
		itemFormStudent = new JMenuItem("FORM STUDENT");
		itemFormStudent.addActionListener(this);
		itemFormSchool = new JMenuItem("FORM SCHOOL");
		itemFormSchool.addActionListener(this);
		itemFormEntry = new JMenuItem("FORM ENTRY");
		itemFormEntry.addActionListener(this);
		itemConnected = new JMenuItem("FORM CONNECTED");
		itemConnected.addActionListener(this);
		
		
		
		menuForm.add(itemFormStudent);
		menuForm.add(itemFormSchool);
		menuForm.add(itemFormEntry);
		menuForm.add(itemConnected);
		
		
		menuBar.add(menuForm);
		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		CardLayout cL = (CardLayout)getLayout(); 
		if (e.getSource() == itemFormStudent)
		{
			cL.show(this, "fs");
		}
		else if (e.getSource() == itemFormSchool)
		{
			cL.show(this, "fu");
		}
		else if (e.getSource() == itemFormEntry)
		{
			fw.getModelCbStudent().update(Database.selectStudentIds());
			fw.getModelCbSchool().update(Database.selectSchoolIds());
			fw.getCbIdStudent().updateUI();
			fw.getCbIdSchool().updateUI();
			cL.show(this, "fw");
		}
		else if (e.getSource() == itemConnected)
			
		{
			fl.updateTable();			
			cL.show(this, "fl");
		}
		
	}
}

