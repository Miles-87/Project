package com.mateusz.models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

//z combo boxem mozna powiazac np array liste ktora bedzie dynamiczne
//przechowywac kolejne id ktore pobierze sobie z bazy danych ze studenta oraz z uczelni
public class MyComboBoxModel extends AbstractListModel<Integer> implements ComboBoxModel<Integer>{
	private List<Integer> lista; //lista - zauwaz ze dzieki lisciece bedziesz mial mozliwosc 
	//dynamicznie dodawac dane do comboboxa
	private int selected; //bedzie to zmienna ktora przechowuje aktualnie wybrany,zaznaczony element
	
	//konstruktor pozwala zainicjalizowac liste
	public MyComboBoxModel(List<Integer> lista)
	{
		this.lista = lista;
		if (lista != null && !lista.isEmpty())
		{
			Collections.sort(lista);
			selected = lista.get(0);
		}
	}
	
	public void update(List<Integer> lista)
	{
		this.lista = lista;
		if (lista != null && !lista.isEmpty())
		{
			Collections.sort(lista);
			selected = lista.get(0);
		}
	}

	//szereg metod ktore dostajesz od klasy abstrakcyjnej oraz interfejsu
	@Override
	public Integer getElementAt(int arg0) {
		return lista.get(arg0); //zwrac i-ty element z listy, czyli tak naprawde i-ty element z comboboxa bo z nim powiazana bedzie lista
	}

	//pobiera ile masz elementow w combobox - rowniez u nas to jest rozmiar lista
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	//metoda zwraca wartosc zaznaczonego elementu, zaznaczony element przechowywany jest w zmiennej selected i wlasnie jej wartosc zwracam
	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return selected;
	}

	//metoda jako argument przyjmuje zmienna anItem i jest to zmienna
	//ktora przechowuje wartosc ta na ktora kliknales w comboboxie
	//metoda setSelected automatycznie pobiera wartosc zaznaczonego elementu i przekazuje go w postaci argumentu anItem
	@Override
	public void setSelectedItem(Object anItem) {
		// TODO Auto-generated method stub
		selected = (int)anItem; //anItem jest typu Object to go musze przerzutowac na int, bo my mamy eleemnty typu int
	}
	
	
}
