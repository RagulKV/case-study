package com.cognizant.truyum.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

@Component("menuItemDao")
public  class MenuItemDaoSqlImpl implements MenuItemDao {

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		List<MenuItem> menuItemList = new ArrayList();
		try {
			Connection con = ConnectionHandler.getConnection();
			System.out.println("Connection successfull!");
			PreparedStatement stmt = con.prepareStatement("select * from menu_item");
			System.out.println("After prepared statement");
			ResultSet rs = stmt.executeQuery();
			/* DateUtil.convertToUtilDateFromSqlFormat(rs.getString(5)) */
			while (rs.next()) {

				MenuItem menuItem = new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4),
						DateUtil.convertToUtilDateFromSqlFormat(rs.getString(5)), rs.getString(6), rs.getBoolean(7));
				menuItemList.add(menuItem);
			}
		} /*catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Exiting getMenuItemListAdmin");
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> menuItemList = new ArrayList<>();
		try {
			Connection con = ConnectionHandler.getConnection();

			PreparedStatement stmt = con.prepareStatement(
					"select * from menu_item where active=? && date(now()) > dateOflaunch");
			stmt.setBoolean(1, true);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				MenuItem menuItem = new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4),
						DateUtil.convertToUtilDateFromSqlFormat(rs.getString(5)), rs.getString(6), rs.getBoolean(7));
				menuItemList.add(menuItem);

			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItemList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		try {
			Connection con = ConnectionHandler.getConnection();

			String sql = "update menu_item set name=?,price=?,active=?,category=?,freeDelivery=?,dateOfLaunch=? where id=?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, menuItem.getName());
			stmt.setFloat(2, menuItem.getPrice());
			stmt.setBoolean(3, menuItem.isActive());
			stmt.setString(4, menuItem.getCategory());
			stmt.setBoolean(5, menuItem.isFreeDelivery());
			String dateStr = DateUtil.convertToSqlDateFromUtilDate(menuItem.getDateOfLaunch());
			stmt.setString(6, dateStr);
			stmt.setLong(7, menuItem.getId());
			int rowsAffected = stmt.executeUpdate();
			System.out.println("The number of rows updated:" + rowsAffected);

		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		try {
			Connection con = ConnectionHandler.getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from menu_item where id=" + menuItemId);

			ResultSet rs = stmt.executeQuery();
			MenuItem menuItem = null;
			while (rs.next()) {

				menuItem = new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4),
						DateUtil.convertToUtilDateFromSqlFormat(rs.getString(5)), rs.getString(6), rs.getBoolean(7));

			}
			return menuItem;

		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*@Override
	public List<MenuItem> getMenuItemListCustomer() {
		try {
			Connection con = ConnectionHandler.getConnection();

			String sql = "update menu_item set menuItemName=?,menuItemPrice=?,menuItemActive=?,menuItemCategory=?,menuItemFreeDelivery=?,menuItemDateOfLaunch=? where menuItemId=?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, menuItem.getName());
			stmt.setFloat(2, menuItem.getPrice());
			stmt.setBoolean(3, menuItem.isActive());
			stmt.setString(4, menuItem.getCategory());
			stmt.setBoolean(5, menuItem.isFreeDelivery());
			String dateStr = DateUtil.convertToSqlDateFromUtilDate(menuItem.getDateOfLaunch());
			stmt.setString(6, dateStr);
			stmt.setLong(7, menuItem.getId());
			int rowsAffected = stmt.executeUpdate();
			System.out.println("The number of rows updated:" + rowsAffected);

		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		try {
			Connection con = ConnectionHandler.getConnection();

			String sql = "update menu_item set menuItemName=?,menuItemPrice=?,menuItemActive=?,menuItemCategory=?,menuItemFreeDelivery=?,menuItemDateOfLaunch=? where menuItemId=?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, menuItem.getName());
			stmt.setFloat(2, menuItem.getPrice());
			stmt.setBoolean(3, menuItem.isActive());
			stmt.setString(4, menuItem.getCategory());
			stmt.setBoolean(5, menuItem.isFreeDelivery());
			String dateStr = DateUtil.convertToSqlDateFromUtilDate(menuItem.getDateOfLaunch());
			stmt.setString(6, dateStr);
			stmt.setLong(7, menuItem.getId());
			int rowsAffected = stmt.executeUpdate();
			System.out.println("The number of rows updated:" + rowsAffected);

		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		// TODO Auto-generated method stub

		try {
			Connection con = ConnectionHandler.getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from menu_item where menuitemId=" + menuItemId);

			ResultSet rs = stmt.executeQuery();
			MenuItem menuItem = null;
			while (rs.next()) {

				menuItem = new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4),
						DateUtil.convertToUtilDateFromSqlFormat(rs.getString(5)), rs.getString(6), rs.getBoolean(7));

			}
			return menuItem;

		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
*/
}
