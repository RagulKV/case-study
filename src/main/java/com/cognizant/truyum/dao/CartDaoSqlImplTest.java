package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImplTest {

public void testAddCartItem() throws ParseException{
		
		CartDao cartDao=new CarDaoSqlImpl();
		cartDao.addCartItem(1, 2);
		cartDao.addCartItem(1, 3);
		try {
			List<MenuItem>cartMenuItemList=cartDao.getAllCartItems(1);
			
			
			for(MenuItem menuItem:cartMenuItemList)
			{
				System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+
									menuItem.isFreeDelivery()+" "+
									menuItem.getPrice());
			}
			MenuItemDao menuItemDaoObj=new MenuItemDaoCollectionImpl();
			MenuItem menuItem=menuItemDaoObj.getMenuItem(2);
			if(cartMenuItemList.contains(menuItem))
			{
				System.out.println("The Item with Item Id "+menuItem.getId()+" is added to the cart");
			}
			else {
				System.out.println("Error in adding the item");
			}
		
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testGetAllCartItems(){
		
		CartDao cartDao=new CarDaoSqlImpl();
		try {
			List<MenuItem>menuItem=cartDao.getAllCartItems(1);
			for(MenuItem menuItemIterator:menuItem)
			{
				System.out.println(menuItemIterator.getName()+" "+
									menuItemIterator.getPrice());
			}
			//Print total?
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testRemoveCartItem() throws ParseException {
		
		CartDao cartDao=new CarDaoSqlImpl();
		
		cartDao.addCartItem(1, 2);
		cartDao.addCartItem(1, 3);
		cartDao.removeCartItem(1, 3);
		
		try {
			List<MenuItem>cartItemMenuList=cartDao.getAllCartItems(1);
			
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			System.out.println("This catch block is executed because the cart item is removed and the cart is nil");
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws ParseException
	{
		CartDaoSqlImplTest cartDaoSqlImplTest=new CartDaoSqlImplTest();
		
		//cartDaoSqlImplTest.testAddCartItem();
		//cartDaoSqlImplTest.testGetAllCartItems();
		cartDaoSqlImplTest.testRemoveCartItem();
	}
}
