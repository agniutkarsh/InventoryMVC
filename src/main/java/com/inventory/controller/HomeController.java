package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.services.GetTshirtService;
import com.inventory.services.UserService;
@Controller
public class HomeController {
@Autowired
	private UserService userService;
	
	@Autowired
	private GetTshirtService getTshirt;
	
	@RequestMapping("/Login")
	public ModelAndView login(@RequestParam String userName, @RequestParam String password) {
		ModelAndView modelView = new ModelAndView();	
		if(!userService.validateUser(userName, password)) {
			modelView.setViewName("index");
			return modelView;
		}
		
		List<String> getTshirtName = getTshirt.getTshirtName();
		List<String> getTshirtColour = getTshirt.getTshirtColour();
		List<String> getTshirtSize = getTshirt.getTshirtSize();
		
		modelView.addObject("tshirtName", getTshirtName);
		modelView.addObject("tshirtColour", getTshirtColour);
		modelView.addObject("tshirtSize", getTshirtSize);
		modelView.addObject("userName", userName);
		modelView.setViewName("home");
		return modelView;
	}
	
	
	@RequestMapping("/Signup")
	public ModelAndView signUp(@RequestParam String userName, @RequestParam String password) {
		ModelAndView modelView = new ModelAndView();
		userService.addUser(userName, password);	
		
		List<String> getTshirtName = getTshirt.getTshirtName();
		List<String> getTshirtColour = getTshirt.getTshirtColour();
		List<String> getTshirtSize = getTshirt.getTshirtSize();
		modelView.addObject("tshirtName", getTshirtName);
		modelView.addObject("tshirtColour", getTshirtColour);
		modelView.addObject("tshirtSize", getTshirtSize);
		modelView.addObject("userName", userName);
		modelView.setViewName("home");
		return modelView;
	}
	
	@RequestMapping("/Logout")
	public ModelAndView logOut() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("userName", null);
		modelView.setViewName("index");
		return modelView;
	}
}
