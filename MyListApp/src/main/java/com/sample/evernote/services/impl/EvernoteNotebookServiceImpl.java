package com.sample.evernote.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.evernote.clients.NoteStoreClient;
import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteList;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;
import com.evernote.thrift.TException;
import com.sample.evermote.services.EvernoteNoteStoreClientService;
import com.sample.evermote.services.EvernoteNotebookService;

public class EvernoteNotebookServiceImpl implements EvernoteNotebookService {

	@Autowired
	private EvernoteNoteStoreClientService noteStoreClientServiceImpl;
	private static final Logger logger = LoggerFactory.getLogger(EvernoteNotebookServiceImpl.class);

	public List<Notebook> getAllNotebooks(){
		List<Notebook> listOfNotebooks= new ArrayList<Notebook>();
		NoteStoreClient noteStoreClient = noteStoreClientServiceImpl.getNoteStoreClient();
		try {
			listOfNotebooks  = noteStoreClient.listNotebooks();
		} catch (EDAMUserException e) {
			logger.error(e.getMessage(),e);
		} catch (EDAMSystemException e) {
			logger.error(e.getMessage(),e);
		} catch (TException e) {
			logger.error(e.getMessage(),e);
		}

		return listOfNotebooks;
	}
	
	public List<Note> getAllNotes(){
		NoteList tempList= new NoteList();
		List<Note> listOfNotes= new ArrayList<Note>();
		NoteStoreClient noteStoreClient = noteStoreClientServiceImpl.getNoteStoreClient();
		try {
			tempList= noteStoreClient.findNotes(new NoteFilter(), 0, 100);
			Iterator<Note> notesItr = tempList.getNotesIterator();
			while(notesItr.hasNext()){
				Note note = notesItr.next();
				note = noteStoreClient.getNote(note.getGuid(), true,false,false,false);
				listOfNotes.add(note);
			}
			
		} catch (EDAMUserException e) {
			logger.error(e.getMessage(),e);
		} catch (EDAMSystemException e) {
			logger.error(e.getMessage(),e);
		} catch (TException e) {
			logger.error(e.getMessage(),e);
		} catch (EDAMNotFoundException e) {
			logger.error(e.getMessage(),e);
		}
		return listOfNotes;
	}
}
