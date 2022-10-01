package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.models.TShirtModel;
import com.inventory.services.DisplaySortService;
import com.inventory.services.TshirtSearchService;

@Controller
public class RequiredTShirtController {
  @Autowired
	private TshirtSearchService tshirtSearchService;
	
	@Autowired
	private DisplaySortService displaySortService; 
	
	@RequestMapping("/search")
	public ModelAndView searchTshirts(@RequestParam String tshirtSize, @RequestParam String tshirtColour,
			@RequestParam String tshirtGender,
			@RequestParam String sortTshirt) {
		ModelAndView modelView = new ModelAndView();
		
		int sortBy = Integer.parseInt(sortTshirt);
		List<TShirtModel> searchResult = tshirtSearchService.getSearchResult(tshirtSize,
											tshirtColour, 
											tshirtGender);
		displaySortService.sortTshirt(searchResult, sortBy);
		
		modelView.addObject("result", searchResult);
		modelView.setViewName("result");
		return modelView;
	}
}
