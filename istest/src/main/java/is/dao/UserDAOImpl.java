package is.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import is.model.Property;
import is.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public User getUser(String login, String password) {
		List<User> userList = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.login = :login"
				+ " and u.password = :password");
		query.setParameter("login", login);
		query.setParameter("password", password);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;	
	}

	@SuppressWarnings("unchecked")
	public User getUser(String login) {
		List<User> userList = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.login = :login");
		query.setParameter("login", login);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;	
	}

	@SuppressWarnings("unchecked")
	public User getUser(int id, String password) {
		List<User> userList = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.id = :id"
				+ " and u.password = :password");
		query.setParameter("id", id);
		query.setParameter("password", password);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;	
	}

	public void setUser(User user){
		openSession().clear();
		openSession().save(user);
	}

	public void removeUser(User user){
		openSession().clear();
		openSession().delete(user);
	}

	public void updateUser(User user){
		openSession().clear();
		openSession().update(user);
	}

	@SuppressWarnings("unchecked")
	public User getBookmark (User user){
		openSession().clear();

		List<Property> propertyList = new ArrayList<Property>();
		Query query = openSession().createQuery("select p"
				+ " from Property p JOIN p.users u where"
				+ " u.id=:id");

		query.setParameter("id", user.getId());


		propertyList = query.list();

		Set<Property> properties =  new HashSet<Property>();
		for(int i=0;i<propertyList.size();i++){
			properties.add(propertyList.get(i));
		}
		user.setProperties(properties);


		return user;
	}

	@SuppressWarnings("unchecked")
	public void addBookmark (int user_id, Property property){

		openSession().clear();

		User user = new User();

		List<User> userList = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.id = :user_id");
		query.setParameter("user_id", user_id);
		userList = query.list();

		user = userList.get(0);

		Set<Property> properties =  new HashSet<Property>();

		properties = user.getProperties();

		for(Property temp: properties)
			if(temp.getId()==property.getId()){
				return;
			}
		properties.add(property);
		user.setProperties(properties);
		openSession().update(user);
	}

	@SuppressWarnings("unchecked")
	public void removeBookmark(int user_id, Property property) {

		openSession().clear();

		User user = new User();

		List<User> userList = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.id = :user_id");
		query.setParameter("user_id", user_id);
		userList = query.list();

		user = userList.get(0);

		Set<Property> properties =  new HashSet<Property>();

		properties = user.getProperties();

		Iterator<Property> iterator = properties.iterator();

		while(iterator.hasNext()){
			Property element = iterator.next();
			if(element.getId()==property.getId()){
				iterator.remove();
				user.setProperties(properties);
				openSession().update(user);
			}
		}
	}
}
