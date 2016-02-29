package is.dao;

import is.model.Search;

public interface SearchDAO {

	public Search getSearch(int id);
	
	public void setSearch(Search search);
	
	public void removeSearch(Search search);
	
	public void updateSearch(Search search);
	
	public Search doSearch (Search search);
	
}
