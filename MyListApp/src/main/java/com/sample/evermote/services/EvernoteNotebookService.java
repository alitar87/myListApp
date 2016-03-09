package com.sample.evermote.services;

import java.util.List;

import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;

public interface EvernoteNotebookService {
	List<Notebook> getAllNotebooks();
	List<Note> getAllNotes();
}
