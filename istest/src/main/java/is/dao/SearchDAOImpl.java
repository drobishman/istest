package is.dao;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import is.model.Property;
import is.model.Search;

@Repository
public class SearchDAOImpl implements SearchDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Search getSearch(int id) {
		List<Search> searchList = new ArrayList<Search>();
		Query query = openSession().createQuery("from Search u where u.id = :id");
		query.setParameter("id", id);
		searchList = query.list();
		if (searchList.size() > 0)
			return searchList.get(0);
		else
			return null;
	}

	@Override
	public void setSearch(Search search) {
		openSession().clear();
		openSession().save(search);
		
	}

	@Override
	public void removeSearch(Search search) {
		openSession().clear();
		openSession().delete(search);
		
	}

	@Override
	public void updateSearch(Search search) {
		openSession().clear();
		openSession().update(search);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Search doSearch(Search search) {
		
		openSession().clear();
		
		List<Property> propertyList = new ArrayList<Property>();
		Query query = openSession().createQuery("from Property p where"
				+ " p.province = :province and"
				+ "	p.city = :city and"
				+ " p.zone = :zone and"
				+ " p.rooms = :rooms and"
				+ " p.bath = :bath and"
				+ " p.type = :type and"
				+ " p.subtype = :subtype and"
				+ " p.price > :price_min and"
				+ " p.price < :price_max and"
				+ " p.sqm > :sqm_min and"
				+ " p.sqm < :sqm_max");
		
		query.setParameter("province", search.getProvince());
		query.setParameter("city", search.getCity());
		query.setParameter("zone", search.getZone());
		query.setParameter("rooms", search.getRooms());
		query.setParameter("bath", search.getBath());
		query.setParameter("type", search.getType());
		query.setParameter("subtype", search.getSubtype());
		query.setParameter("price_min", search.getPrice_min());
		query.setParameter("price_max", search.getPrice_max());
		query.setParameter("sqm_min", search.getSqm_min());
		query.setParameter("sqm_max", search.getSqm_max());
		
		propertyList = query.list();
		
		Set<Property> result =  new HashSet<Property>();
		 for(int i=0;i<propertyList.size();i++){
			 result.add(propertyList.get(i));
	      }
		 search.setResults(result);
		 
		 openSession().save(search);	
		 
		 return search;
	}

}
