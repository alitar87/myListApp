package com.sample.mapper.service;

import java.util.List;

import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;
import com.sample.models.ListItem;
import com.sample.models.ListTitle;

public interface MyListAppNotebookMapperService {
	public List<ListTitle> mapNotebookToListTitle(List<Notebook> listOfNotebooks);
	public List<ListItem> mapNotesToListItems(List<Note> listOfNotes);

}
