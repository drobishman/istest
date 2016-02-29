package is.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import is.dao.SearchDAO;
import is.model.Search;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDAO searchDAO;
	
	public Search getSearch(int id) {

		return searchDAO.getSearch(id);
	}

	public void setSearch(Search search) {

		searchDAO.setSearch(search);
		
	}

	public void removeSearch(Search search) {
		
		searchDAO.removeSearch(search);
		
	}

	public void updateSearch(Search search) {
		
		searchDAO.updateSearch(search);
		
	}

	public Search doSearch(Search search) {
		
		return searchDAO.doSearch(search);
		
	}

}
