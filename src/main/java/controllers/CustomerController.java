/*
 * CustomerController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Shout;
import services.ShoutService;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	
	@Autowired
	private ShoutService shoutService;
	
	
	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// shouts ---------------------------------------------------------------		

	@RequestMapping(value = "/shouts", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Collection<Shout> shouts;
		
		shouts = this.shoutService.findAll();

		result = new ModelAndView("customer/shouts");
		result.addObject("shouts", shouts);

		return result;
	}

	// enterashout ---------------------------------------------------------------		

	@RequestMapping(value = "/enterashout", method = RequestMethod.GET)
	public ModelAndView action2Get() {
		ModelAndView result;
		Shout shout;
		shout= this.shoutService.create();

		result = new ModelAndView("customer/enterashout");
		result.addObject("shout", shout);

		return result;
	}
	
	@RequestMapping(value = "/enterashout", method = RequestMethod.POST)
	public ModelAndView action2Post(@Valid final Shout shout, final BindingResult binding) {
		ModelAndView result;
		
		if(!binding.hasErrors()) {
			this.shoutService.save(shout);
			result = new ModelAndView("redirect:shouts.do");
		} else {
			result = new ModelAndView("customer/enterashout");
			result.addObject("shout", shout);
		}
		
		return result;
	}
}
