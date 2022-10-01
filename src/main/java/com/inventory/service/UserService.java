package com.inventory.service;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.dao.UserDao;
import com.inventory.models.UserModel;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public boolean validateUser(String userName, String password) {	
		UserModel userModel = userDao.getUser(userName);
		if(userModel == null) return false;
		if(userModel.getPassword().equals(password)) return true;
		return false;
	}

	public void addUser(String userName, String password) {
		UserModel userModel = new UserModel();
		userModel.setUserName(userName);
		userModel.setPassword(password);
		userDao.addUser(userModel);
	}
}

}
