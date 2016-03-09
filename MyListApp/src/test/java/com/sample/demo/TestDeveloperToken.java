package com.sample.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;
import com.evernote.thrift.TException;
import com.sample.config.AppConfig;
import com.sample.evermote.services.EvernoteAuthService;
import com.sample.evermote.services.EvernoteNotebookService;
import com.sample.models.ListTitle;
import com.sample.services.MyListAppListTitleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestDeveloperToken {

	@Autowired
	private EvernoteAuthService capOneEvernoteAuthService;
	
	@Autowired
	private EvernoteNotebookService evernoteNotebookServiceImpl;
	
	@Autowired
	private MyListAppListTitleService capOneListServiceImpl;
	
	public void testDeveloperToken(){
		ClientFactory factory = new ClientFactory(capOneEvernoteAuthService.getEvernoteAuthToken());
		NoteStoreClient noteStore = null;
		List<Notebook> notebooks = null;

		try {
			noteStore = factory.createNoteStoreClient();
			notebooks = noteStore.listNotebooks();
			//makeNote(noteStore,"This is a test notebook", "This is a test notebody essage",null);

		} catch (EDAMUserException e1) {
			e1.printStackTrace();
		} catch (EDAMSystemException e1) {
			e1.printStackTrace();
		} catch (TException e1) {
			e1.printStackTrace();
		}
		
	}
	@Test
	public void testAllListGroups(){
		List<ListTitle> listOfItemGroups = capOneListServiceImpl.getAllTheLists();
		for(ListTitle listGroup:listOfItemGroups){
			System.out.println(listGroup.getListName());
		}
	}
	
	@Test
	public void testAllListItems(){
		List<Note> noteList = new ArrayList<Note>();
		noteList  = evernoteNotebookServiceImpl.getAllNotes();
		
	}

	public Note makeNote(NoteStoreClient noteStore, String noteTitle, String noteBody, Notebook parentNotebook) {

		String nBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		nBody += "<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">";
		nBody += "<en-note>" + noteBody + "</en-note>";

		// Create note object
		Note ourNote = new Note();
		ourNote.setTitle(noteTitle);
		ourNote.setContent(nBody);

		// parentNotebook is optional; if omitted, default notebook is used
		if (parentNotebook != null && parentNotebook.isSetGuid()) {
			ourNote.setNotebookGuid(parentNotebook.getGuid());
		}

		// Attempt to create note in Evernote account
		Note note = null;
		try {
			note = noteStore.createNote(ourNote);
		} catch (EDAMUserException edue) {
			// Something was wrong with the note data
			// See EDAMErrorCode enumeration for error code explanation
			// http://dev.evernote.com/documentation/reference/Errors.html#Enum_EDAMErrorCode
			System.out.println("EDAMUserException: " + edue);
		} catch (EDAMNotFoundException ednfe) {
			// Parent Notebook GUID doesn't correspond to an actual notebook
			System.out.println("EDAMNotFoundException: Invalid parent notebook GUID");
		} catch (Exception e) {
			// Other unexpected exceptions
			e.printStackTrace();
		}

		// Return created note object
		return note;

	}
}
