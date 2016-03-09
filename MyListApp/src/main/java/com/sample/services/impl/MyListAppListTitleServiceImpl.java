package com.sample.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.evernote.edam.type.Notebook;
import com.sample.evermote.services.EvernoteNotebookService;
import com.sample.mapper.service.MyListAppNotebookMapperService;
import com.sample.models.ListTitle;
import com.sample.services.MyListAppListTitleService;

public class MyListAppListTitleServiceImpl implements MyListAppListTitleService{

	@Autowired
	private EvernoteNotebookService notebookServiceImpl;

	@Autowired
	private MyListAppNotebookMapperService myListNotebookMapperServiceImpl;

	@Override
	public void addListTitle(String listTitle) {
		//TODO: create item list implementation
	}

	@Override
	public List<ListTitle> getAllTheLists() {
		List<ListTitle> itemList = new ArrayList<ListTitle>();
		List<Notebook> listOfNotebooks = notebookServiceImpl.getAllNotebooks();
		itemList = myListNotebookMapperServiceImpl.mapNotebookToListTitle(listOfNotebooks);
		return itemList;
	}

	@Override
	public void deleteListTitle(String listTitle) {
		// TODO: Create delete list title implemention
	}
}
