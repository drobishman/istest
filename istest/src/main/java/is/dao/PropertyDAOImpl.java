package is.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import is.model.Property;

@Repository
public class PropertyDAOImpl implements PropertyDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public Property getPropertyDAO(int id) {
		List<Property> propertyList = new ArrayList<Property>();
		Query query = openSession().createQuery("from Property p where p.id = :id");
		query.setParameter("id", id);
		propertyList = query.list();
		if (propertyList.size() > 0)
			return propertyList.get(0);
		else
			return null;	
	}

	public void setPropertyDAO(Property property) {
		openSession().clear();
		openSession().save(property);
		
	}

	public void removePropertyDAO(Property property) {
		openSession().clear();
		openSession().delete(property);
		
	}

	public void updatePropertyDAO(Property property) {
		openSession().clear();
		openSession().update(property);
	}
}