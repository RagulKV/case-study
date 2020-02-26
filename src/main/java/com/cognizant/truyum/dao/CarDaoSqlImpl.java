package com.cognizant.truyum.dao;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CarDaoSqlImpl implements CartDao{

	static int flagForAddCart=0;
	//static int flagForRemoveCart=0;
	@Override
	public void addCartItem(long userId, long menuItemId) {
		// TODO Auto-generated method stub
		try {
			Connection conn=ConnectionHandler.getConnection();
			if(flagForAddCart==0){
				String sql="insert into cart_table values(?,?)";
				PreparedStatement stmt=conn.prepareStatement(sql);
				stmt.setLong(1, userId);
				stmt.setLong(2, menuItemId);
				stmt.execute();
				flagForAddCart++;
			}
			else{
				PreparedStatement stmt;
				ResultSet rs=null;
				String existingMenuItemId=null;
				String sql1="select menuItemsId from cart_table where userId=?";
				stmt=conn.prepareStatement(sql1);
				stmt.setLong(1, userId);
				rs=stmt.executeQuery();
				while(rs.next()){
					 existingMenuItemId=rs.getString(1);
				}
				String concat=existingMenuItemId+","+String.valueOf(menuItemId);
				String sql2="update cart_table set menuItemsId=? where userId=?";
				stmt=conn.prepareStatement(sql2);
				stmt.setString(1, concat);
				stmt.setLong(2, userId);
				int rows=stmt.executeUpdate();
				System.out.println("Rows Updated!!!");
			}
			
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		// TODO Auto-generated method stub
		try {
			Connection conn=ConnectionHandler.getConnection();
			List<MenuItem>menuItemList=new ArrayList<>();
			double total=0.0;
			Cart cart=new Cart(menuItemList,total);
			
			String sqlGetMenuItemsFromCart="select menuItemsId from cart_table where cart_table.userId =?";
			
			PreparedStatement stmt=conn.prepareStatement(sqlGetMenuItemsFromCart);
			
			stmt.setLong(1, userId);
			
			ResultSet rs=stmt.executeQuery();
			String[] menuItemIds=null;
			while(rs.next()){
				 menuItemIds=rs.getString(1).trim().split(",");
				//menuItemList.add(menuItem);
			}
				MenuItemDao menuItemObj=null;
				try {
					menuItemObj = new MenuItemDaoCollectionImpl();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i=0;i<menuItemIds.length;i++)
				{
					long menuItemId=Long.parseLong(menuItemIds[i]);
					MenuItem menuItem=menuItemObj.getMenuItem(menuItemId);
					menuItemList.add(menuItem);
					total+=menuItem.getPrice();
				}
			cart.setTotal(total);
			cart.setMenuItemList(menuItemList);
		
			return menuItemList;
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=ConnectionHandler.getConnection();
			
			String sql1="select menuItemsId from cart_table where userId=?";
			stmt=conn.prepareStatement(sql1);
			stmt.setLong(1, userId);
			rs=stmt.executeQuery();
			String menuItems=null;
			while(rs.next()){
				menuItems=rs.getString(1).trim();
			}
			
			if(menuItems.length()==1)
			{
				String getMenuItemIdToBeDeleted="delete from cart_table where userId=? and menuItemsId=?";
				stmt=conn.prepareStatement(getMenuItemIdToBeDeleted);
				stmt.setLong(1, userId);
				stmt.setString(2, String.valueOf(menuItemId));
				
				int deletedRows=stmt.executeUpdate();
				
				System.out.println("Deleted!");
			}
			else{
				String menuItemIdStr=String.valueOf(menuItemId);
				String updatedMenuItemId="";
				String[] menuItemsStr=menuItems.split(",");
				for(int i=0;i<menuItemsStr.length;i++){
					if(menuItemIdStr.equals(menuItemsStr[i]))continue;
					updatedMenuItemId+=menuItemsStr[i]+",";
				}
			
				updatedMenuItemId=updatedMenuItemId.substring(0, updatedMenuItemId.length()-1);
				System.out.println(updatedMenuItemId);
				String sql2="update cart_table set menuItemsId=? where userId=?";
				stmt=conn.prepareStatement(sql2);
				stmt.setString(1, updatedMenuItemId);
				stmt.setLong(2, userId);
				int i=stmt.executeUpdate();
				System.out.println("Item Removed From Cart");
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
