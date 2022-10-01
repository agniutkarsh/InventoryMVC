
package com.inventory.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.dao.TShirtDao;
import com.inventory.models.TShirtModel;

public class TShirtSearchService {
@Autowired
	private TShirtDao tshirtDao;

	public List<TShirtModel> getSearchResult(String tshirtSize, String tshirtColour, String tshirtGender) {
		
		List<TShirtModel> searchResult;
		searchResult = tshirtDao.getTshirts(tshirtSize, tshirtColour, tshirtGender);
		return searchResult;
	}
}
