package come.sample.mapper.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;
import com.sample.mapper.map.NoteListItemMap;
import com.sample.mapper.map.NotebookListTitleMap;
import com.sample.mapper.service.MyListAppNotebookMapperService;
import com.sample.models.ListItem;
import com.sample.models.ListTitle;

@Service
public class MyListAppNotebookMapperServiceImpl implements MyListAppNotebookMapperService {

	public List<ListTitle> mapNotebookToListTitle(List<Notebook> listOfNotebooks) {
		List<ListTitle> listOfListTitles = new ArrayList<ListTitle>();
		for (Notebook notebook : listOfNotebooks) {
			listOfListTitles.add(mapNotebookToListGroup(notebook));
		}
		return listOfListTitles;
	}
	
	public List<ListItem> mapNotesToListItems(List<Note> listOfNotes) {
		List<ListItem> listOfItems = new ArrayList<ListItem>();
		for (Note note : listOfNotes) {
			listOfItems.add(mapNoteToListItem(note));
		}
		return listOfItems;
	}

	private ListTitle mapNotebookToListGroup(Notebook notebook) {
		ListTitle listGroup = new ListTitle();
		ModelMapper modelMapper = new ModelMapper();
		NotebookListTitleMap notebookListTitleMap = new NotebookListTitleMap();
		modelMapper.addMappings(notebookListTitleMap);
		listGroup = modelMapper.map(notebook, ListTitle.class);
		return listGroup;
	}

	private ListItem mapNoteToListItem(Note note) {
		ListItem listItem = new ListItem();
		ModelMapper modelMapper = new ModelMapper();
		NoteListItemMap noteListItemMap = new NoteListItemMap();
		modelMapper.addMappings(noteListItemMap);
		listItem = modelMapper.map(note, ListItem.class);
		return listItem;
	}

}
