package is.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import is.dao.PropertyDAO;
import is.model.Property;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyDAO propertyDAO;
	
	public Property getProperty(int id) {
		
		return propertyDAO.getPropertyDAO(id); 
	}

	public void setProperty(Property property) {
		
		propertyDAO.setPropertyDAO(property);
		
	}

	public void removeProperty(Property property) {
		
		propertyDAO.removePropertyDAO(property);
		
	}

	public void updateProperty(Property property) {
		
		propertyDAO.updatePropertyDAO(property);
		
	}

}
