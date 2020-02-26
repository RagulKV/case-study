package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.*;
import java.util.*;

public interface CartDao {

	public void addCartItem(long userId,long menuItemId);
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException;
	public void removeCartItem(long userId,long menuItemId);
}
