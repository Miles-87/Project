package com.mateusz.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.mateusz.classes.Connected;

public class MyCustomTableModel extends AbstractTableModel{

	private List<Connected> rows; //lista przechowujaca kolejne wiersze
	private List<String> columnNames = new ArrayList<>(Arrays.asList(
			new String[]{"Name", "Surname", "Age", "Year", "School name", "City", "Dean name", "Year of build"}
	));
	
	public MyCustomTableModel(List<Connected> rows) {
		this.rows = rows;
	}
	
	public void update(List<Connected> rows)
	{
		this.rows = rows;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}


	@Override
	public Object getValueAt(int row, int col) {
		Connected c = rows.get(row); //pobieramy wiersz o indekei row
		//i w zleznosci od kolumny zwracamy wartosci
		if (col == 0)
		{
			return c.getName();
		}
		else if (col == 1)
		{
			return c.getSurname();
		}
		else if(col == 2){
			return c.getAge();
		}
		else if(col == 3){
			return c.getYear();
		}
		else if(col == 4){
			return c.getSchoolName();
		}
		else if(col == 5){
			return c.getCity();
		}
		else if (col == 6){
			return c.getDeanName();
		}
		else{
			return c.getYearOfBuild();
		}
	}
	
	//samodzielnie dodaje jeszcze jedna metode do przeladowania ktorej byc nie musi
	//ale pozwoli mi ona dodawac nazwy kolumn do tabeli
	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

}
