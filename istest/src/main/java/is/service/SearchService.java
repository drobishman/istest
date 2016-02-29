package is.service;

import is.model.Search;

public interface SearchService {
	
	public Search getSearch(int id);
	
	public void setSearch(Search search);
	
	public void removeSearch(Search search);
	
	public void updateSearch(Search search);
	
	public Search doSearch(Search search);

}
