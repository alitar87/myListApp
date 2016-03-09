package com.sample.controllers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sample.models.ListItem;
import com.sample.services.MyListAppListItemService;

@Controller
public class ListItemController {
	private static final Logger logger = LoggerFactory.getLogger(ListItemController.class);

	@Autowired 
	private MyListAppListItemService capOneListItemService;
	
	@RequestMapping(value = "/allListItems", method = RequestMethod.GET)
	public ResponseEntity<List<ListItem>> getAllListItems(){
		logger.debug(">> Getting all list items");
		List<ListItem> listItems = new ArrayList<ListItem>();
		listItems = capOneListItemService.getAllListItems();
		logger.debug("<<.. end");
		return new ResponseEntity<List<ListItem>>(listItems,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/addListItem", method = RequestMethod.POST)
	public ResponseEntity<String> addListItem(@ModelAttribute("demo")ListItem listItem, ModelMap model){
		logger.debug(">> Adding new list item");
		model.addAttribute("listItem",listItem.getTitle());
		model.addAttribute("listItemDetails",listItem.getDetails());
		String noteGUID = capOneListItemService.addListItem(listItem.getTitle(), listItem.getDetails());
		
		logger.debug("<<.. end");
		
		return new ResponseEntity<String>("New note added " + noteGUID,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listItem", method = RequestMethod.GET)
	public ModelAndView listItem(){
		return new ModelAndView("listItem","command",new ListItem());
	}
}
