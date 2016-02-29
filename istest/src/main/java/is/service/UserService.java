package is.service;

import is.model.Property;
import is.model.User;

public interface UserService {
	
	public User getUser(String login);
	
	public User getUser(String login, String password);
	
	public User getUser(int id, String password);
	
	public void setUser(User user);
	
	public void removeUser(User user);
	
	public void updateUser(User user);
	
	public User getBookmark(User user);
	
	public void addBookmark(int user_id, Property property);
	
	public void removeBookmark(int user_id, Property property);

}
