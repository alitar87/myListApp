package com.sample.mapper.map;

import org.modelmapper.PropertyMap;

import com.evernote.edam.type.Notebook;
import com.sample.models.ListTitle;

public class NotebookListTitleMap extends PropertyMap<Notebook, ListTitle>{

	@Override
	protected void configure() {
		map().setListName(source.getName());

	}
}
