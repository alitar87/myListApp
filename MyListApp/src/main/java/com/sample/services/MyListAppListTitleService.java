package com.sample.services;

import java.util.List;

import com.sample.models.ListTitle;

public interface MyListAppListTitleService {
	
	public void addListTitle(String listTitle);
	
	public List<ListTitle> getAllTheLists();
	
	public void deleteListTitle(String listTitle);
}
