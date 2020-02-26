package com.cognizant.truyum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.model.MenuItem;

@Service
public class MenuItemService {

	@Autowired
	MenuItemDao menuItemDao;

	public List<MenuItem> getMenuItemListAdmin() {
		List<MenuItem> menuItemList = menuItemDao.getMenuItemListAdmin();
		return menuItemList;
	}

	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> menuItemList = menuItemDao.getMenuItemListCustomer();
		return menuItemList;
	}
	public MenuItem getMenuItem(long menuItemId) {
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		return menuItem;
	}
	public void modifyMenuItem(MenuItem menuItem) {
		menuItemDao.modifyMenuItem(menuItem);
	}

}
