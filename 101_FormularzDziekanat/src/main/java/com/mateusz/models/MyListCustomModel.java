package com.mateusz.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

public class MyListCustomModel extends AbstractListModel<String>{

	List<String> item = new ArrayList<>();
	
	public MyListCustomModel(List<String> item) {
		this.item = item;
		Collections.sort(item);
	}
	
	public void update(List<String> item) {
		this.item = item;
		Collections.sort(item);
	}
	
	@Override
	public String getElementAt(int idx) {
		return item.get(idx);
	}

	@Override
	public int getSize() {
		return item.size();
	}
	
}
