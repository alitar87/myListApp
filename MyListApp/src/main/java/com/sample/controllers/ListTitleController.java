package com.sample.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.models.ListTitle;
import com.sample.services.MyListAppListTitleService;

@Controller
public class ListTitleController {
	@Autowired 
	private MyListAppListTitleService capOneListTitleService;
	
	private static final Logger logger = LoggerFactory.getLogger(ListTitleController.class);

	@RequestMapping(value = "/allListTitles", method = RequestMethod.GET)
	public ResponseEntity<List<ListTitle>> getAllListTitles(){
		logger.debug(">> Getting all list titles");
		List<ListTitle> listTitles = new ArrayList<ListTitle>();
		listTitles = capOneListTitleService.getAllTheLists();
		logger.debug("<<.. end");
		return new ResponseEntity<List<ListTitle>>(listTitles,HttpStatus.OK);
	}
}
