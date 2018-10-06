/*
 * ProfileController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import forms.Calculator;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	// Action-1 ---------------------------------------------------------------		

	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		List<String> quotes;	
		
		quotes = new ArrayList<String>();
		quotes.add("Make it simple, not simpler -- Albert Einstein");
		quotes.add("I have a dream -- Martin L King");
		quotes.add("It seems impossible until it's done -- Nelson Mandela");
		quotes.add("Cogito ergo sum -- Ren� Descartes");
		Collections.shuffle(quotes);
		quotes = quotes.subList(0, 3);
		
		result = new ModelAndView("profile/quotes");
		result.addObject("quotes", quotes);

		return result;
	}

	// Action-2 ---------------------------------------------------------------		

	@RequestMapping(value = "/calculator", method = RequestMethod.GET)
	public ModelAndView action2Get() {
		ModelAndView result;
		Calculator calculator = new Calculator();

		result = new ModelAndView("profile/calculator");
		result.addObject("calculator", calculator);

		return result;
	}
	
	
	@RequestMapping(value = "/calculator", method = RequestMethod.POST)
	public ModelAndView action2Post(@Valid final Calculator calculator, final BindingResult binding) {
		ModelAndView result;
		calculator.compute();

		result = new ModelAndView("profile/calculator");
		result.addObject("calculator", calculator);

		return result;
		
	}

	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/oops")
	public ModelAndView action3() {
		throw new RuntimeException("Oops! An *expected* exception was thrown. This is normal behaviour.");
	}

}
