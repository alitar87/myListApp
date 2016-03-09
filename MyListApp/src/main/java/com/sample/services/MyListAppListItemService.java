package com.sample.services;

import java.util.List;

import com.sample.models.ListItem;

public interface MyListAppListItemService {

	List<ListItem> getAllListItems();

	String addListItem(String listItem, String listItemDescription);
	
}
