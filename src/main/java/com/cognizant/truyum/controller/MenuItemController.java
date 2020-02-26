package com.cognizant.truyum.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;

@Controller
public class MenuItemController {

	@Autowired
	MenuItemService service;
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping(value = "/show-menu-list-admin")
	public String showMenuItemListAdmin(ModelMap model) throws SystemException {
		List<MenuItem> menuItemListAdmin = service.getMenuItemListAdmin();
		model.addAttribute("menuItemListAdmin", menuItemListAdmin);
		return "menu-item-list-admin";
	}
	
	@GetMapping(value = "/show-edit-menu-item")
	public String showEditMenuItem(ModelMap model,@RequestParam long id) throws SystemException {
		MenuItem menuItem = service.getMenuItem(id);
		model.addAttribute("menuItem", menuItem);
		return "edit-menu-item";
		
	}
	
	@PostMapping(value="/edit-menu-item")
	public String editMenuItem(@Valid MenuItem menuItem, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
		{
			return "edit-menu-item";
		}
		System.out.println(menuItem.getDateOfLaunch());
		service.modifyMenuItem(menuItem);
		return "redirect:/show-menu-list-admin";
	}
	
	@GetMapping(value = "/show-menu-list-customer")
	public String showMenuItemListCustomer(ModelMap model) throws SystemException {

		List<MenuItem> menuItemListCustomer = service.getMenuItemListCustomer();
		model.addAttribute("menuItemListCustomer", menuItemListCustomer);
		return "menu-item-list-customer";
	}

}
