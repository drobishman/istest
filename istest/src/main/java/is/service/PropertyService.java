package is.service;

import is.model.Property;

public interface PropertyService {
	
	public Property getProperty(int id);
	
	public void setProperty(Property property);
	
	public void removeProperty(Property property);
	
	public void updateProperty(Property property);

}
