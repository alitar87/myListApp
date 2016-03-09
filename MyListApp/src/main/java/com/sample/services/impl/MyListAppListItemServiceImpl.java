package com.sample.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.evernote.clients.NoteStoreClient;
import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;
import com.sample.evermote.services.EvernoteNoteStoreClientService;
import com.sample.evermote.services.EvernoteNotebookService;
import com.sample.mapper.service.MyListAppNotebookMapperService;
import com.sample.models.ListItem;
import com.sample.services.MyListAppListItemService;

public class MyListAppListItemServiceImpl implements MyListAppListItemService {

	@Autowired
	private EvernoteNoteStoreClientService noteStoreClientServiceImpl;
	
	@Autowired
	private EvernoteNotebookService evernoteNotebookServiceImpl;
	
	@Autowired
	private MyListAppNotebookMapperService myListAppNotebookMapperServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(MyListAppListItemServiceImpl.class);

	@Override
	public List<ListItem> getAllListItems() {
		List<ListItem> listItems = new ArrayList<ListItem>();
		List<Note> noteList = evernoteNotebookServiceImpl.getAllNotes();
		listItems = myListAppNotebookMapperServiceImpl.mapNotesToListItems(noteList);
		return listItems;
	}
	
	@Override
	public String addListItem(String listItem, String listItemDescription) {
		Note note1 = makeNote(noteStoreClientServiceImpl.getNoteStoreClient(), listItem, listItemDescription, null);
		return note1.getGuid();
	}
	
	private Note makeNote(NoteStoreClient noteStore, String noteTitle, String noteBody, Notebook parentNotebook) {

		  String nBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		  nBody += "<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">";
		  nBody += "<en-note>" + noteBody + "</en-note>";

		  Note ourNote = new Note();
		  ourNote.setTitle(noteTitle);
		  ourNote.setContent(nBody);

		  if (parentNotebook != null && parentNotebook.isSetGuid()) {
		    ourNote.setNotebookGuid(parentNotebook.getGuid());
		  }

		  Note note = null;
		  try {
		    note = noteStore.createNote(ourNote);
		  } catch (EDAMUserException edue) {
			  logger.error("EDAMUserException: ", edue);
		  } catch (EDAMNotFoundException ednfe) {
			  logger.error("EDAMNotFoundException: Invalid parent notebook GUID");
		  } catch (Exception e) {
			  logger.error(e.getMessage(),e);
		  }
		  return note;
		 
		}

}
