package is.dao;

import is.model.Property;
import is.model.User;

public interface UserDAO {
	
	public User getUser(String login, String password);
	
	public User getUser(int id, String password);
	
	public User getUser(String login);
	
	public void setUser(User user);
	
	public void removeUser(User user);
	
	public void updateUser(User user);

	public User getBookmark(User user);
	
	public void addBookmark(int user_id, Property property);
	
	public void removeBookmark(int user_id, Property property);
}
