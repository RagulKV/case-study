package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {

public void testGetMenuItemListAdmin() throws ParseException{
		
		MenuItemDao menuItemDao=(MenuItemDao)new MenuItemDaoSqlImpl();
		List<MenuItem> menuItemAdmin = menuItemDao.getMenuItemListAdmin();
		
		for(MenuItem menuItemIterator:menuItemAdmin)
		{
			System.out.println(
				menuItemIterator.getId()+" "+menuItemIterator.getName()+" "+
				menuItemIterator.getPrice()+" "+menuItemIterator.isActive()+" "+
				DateUtil.convertToStringDateFromUtilFormat(menuItemIterator.getDateOfLaunch())+" "+menuItemIterator.getCategory()+" "+
				menuItemIterator.isFreeDelivery());
		}
	}
	public void testGetMenuItemListCustomer() throws ParseException{
		
		MenuItemDao menuItemDao=(MenuItemDao)new MenuItemDaoSqlImpl();
		List<MenuItem> menuItemCustomer=menuItemDao.getMenuItemListCustomer();
		for(MenuItem menuItemIterator:menuItemCustomer)
		{
			System.out.println(
					menuItemIterator.getName()+" "+
					menuItemIterator.isFreeDelivery()+" "+
					menuItemIterator.getPrice()+" "+
					menuItemIterator.getCategory()+" ");
		}
	}
	public void testModifyMenuItem() throws ParseException{
		
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		
		MenuItem menuItem=new MenuItem(1,"Biriyani",(float) 120.0,true,DateUtil.convertToUtilDateFromStringFormat("28/11/2017"),"Main Course",true);
		MenuItemDao menuItemDao=(MenuItemDao)new MenuItemDaoSqlImpl();
		menuItemDao.modifyMenuItem(menuItem);
		MenuItem menuItemTemp=menuItemDao.getMenuItem(1);
		if(menuItemTemp!=null)System.out.println("Menu Item "+menuItemTemp.getName()+" is modified");
		testGetMenuItemListAdmin();
		
	}
	public void testGetMenuItem(){
		
	}
	public static void main(String[] args) throws ParseException{
		
		MenuItemDaoSqlImplTest menuItemDaoSqlImplTest=new MenuItemDaoSqlImplTest();
		
		//menuItemDaoSqlImplTest.testGetMenuItemListAdmin();
		
		//menuItemDaoSqlImplTest.testGetMenuItemListCustomer();
		
		menuItemDaoSqlImplTest.testModifyMenuItem();
		
		
	}
}
