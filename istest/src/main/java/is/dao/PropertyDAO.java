package is.dao;

import is.model.Property;

public interface PropertyDAO {
	
	public Property getPropertyDAO(int id);
	
	public void setPropertyDAO(Property property);
	
	public void removePropertyDAO(Property property);
	
	public void updatePropertyDAO(Property property);

}
