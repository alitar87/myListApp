package com.sample.models;

import java.io.Serializable;

public class ListTitle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String listName;

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

}
