package is.service;

import org.springframework.transaction.annotation.Transactional;

import is.dao.UserDAO;
import is.model.Property;
import is.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
public User getUser(String login) {
		
		return userDAO.getUser(login);
	}

	public User getUser(String login, String password) {
		
		return userDAO.getUser(login,password);
	}

	public User getUser(int id, String password) {
		return userDAO.getUser(id, password);
	}

	public void setUser(User user){

		userDAO.setUser(user);

	}

	public void removeUser(User user){

		userDAO.removeUser(user);

	}

	public void updateUser(User user){

		userDAO.updateUser(user);
	}

	public User getBookmark(User user) {

		return userDAO.getBookmark(user);
	}

	public void addBookmark(int user_id, Property property) {
		
		userDAO.addBookmark(user_id, property);
		
	}
	
public void removeBookmark(int user_id, Property property) {
		
		userDAO.removeBookmark(user_id, property);
		
	}
}
