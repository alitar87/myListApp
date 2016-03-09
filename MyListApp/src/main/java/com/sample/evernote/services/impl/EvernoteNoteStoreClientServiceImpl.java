package com.sample.evernote.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.thrift.TException;
import com.sample.evermote.services.EvernoteAuthService;
import com.sample.evermote.services.EvernoteNoteStoreClientService;

@Service
public class EvernoteNoteStoreClientServiceImpl implements EvernoteNoteStoreClientService {

	@Autowired
	private EvernoteAuthService capOneEvernoteAuthService;
	
	private static final Logger logger = LoggerFactory.getLogger(EvernoteNotebookServiceImpl.class);

	@Override
	public NoteStoreClient getNoteStoreClient() {
		ClientFactory factory = new ClientFactory(capOneEvernoteAuthService.getEvernoteAuthToken());
		try {
			NoteStoreClient noteStoreClient = factory.createNoteStoreClient();
			return noteStoreClient;
		} catch (EDAMUserException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		} catch (EDAMSystemException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		} catch (TException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

}
