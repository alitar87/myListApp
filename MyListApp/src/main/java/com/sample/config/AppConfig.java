package com.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.evermote.services.EvernoteAuthService;
import com.sample.evermote.services.EvernoteNoteStoreClientService;
import com.sample.evermote.services.EvernoteNotebookService;
import com.sample.evernote.services.impl.EvernoteAuthServiceImpl;
import com.sample.evernote.services.impl.EvernoteNoteStoreClientServiceImpl;
import com.sample.evernote.services.impl.EvernoteNotebookServiceImpl;
import com.sample.mapper.service.MyListAppNotebookMapperService;
import com.sample.services.MyListAppListItemService;
import com.sample.services.MyListAppListTitleService;
import com.sample.services.impl.MyListAppListItemServiceImpl;
import com.sample.services.impl.MyListAppListTitleServiceImpl;

import come.sample.mapper.service.impl.MyListAppNotebookMapperServiceImpl;

@Configuration
public class AppConfig {

	@Bean(name="evernoteAuthService")
	public EvernoteAuthService myListAppEvernoteAuthService() {
		return new EvernoteAuthServiceImpl();
	}
	
	@Bean(name="evernoteNotebookService")
	public EvernoteNotebookService evernoteNotebookServiceImpl() {
		return new EvernoteNotebookServiceImpl();
	}
	
	@Bean(name="evernoteNoteStoreClientService")
	public EvernoteNoteStoreClientService noteStoreClientService() {
		return new EvernoteNoteStoreClientServiceImpl();
	}

	@Bean(name="myListAppListTitleService")
	public MyListAppListTitleService myListAppListTitleService() {
		return new MyListAppListTitleServiceImpl();
	}
	
	@Bean(name="myListAppListItemService")
	public MyListAppListItemService myListAppListItemService() {
		return new MyListAppListItemServiceImpl();
	}

	@Bean(name="myListAppNotebookMapperService")
	public MyListAppNotebookMapperService myListAppNotebookMapperService() {
		return new MyListAppNotebookMapperServiceImpl();
	}

	
}
