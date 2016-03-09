package com.sample.mapper.map;

import org.modelmapper.PropertyMap;
import com.evernote.edam.type.Note;
import com.sample.models.ListItem;

public class NoteListItemMap extends PropertyMap<Note, ListItem>{

	@Override
	protected void configure() {
		map().setTitle(source.getTitle());
		map().setDetails(source.getContent());
	}
}