/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.ceau.chart.BarChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.BarData;
import be.ceau.chart.dataset.BarDataset;
import be.ceau.chart.options.BarOptions;
import be.ceau.chart.options.Legend;
import be.ceau.chart.options.Title;
import be.ceau.chart.options.scales.BarScale;
import be.ceau.chart.options.scales.YAxis;
import be.ceau.chart.options.ticks.LinearTicks;
import services.ShoutService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	
	@Autowired
	private ShoutService shoutService;
	
	
	
	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Map<String, Double> statistics;
		
		statistics = this.shoutService.computeStatistics();	
		
		result = new ModelAndView("administrator/dashboard");
		result.addObject("statistics", statistics);

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping(value = "/chart", method = RequestMethod.GET)
	public ModelAndView action2() {
		ModelAndView result;
		
		int[] values = this.shoutService.getValues();

		BarOptions options = new BarOptions();
		
		options.setResponsive(false)
			   .setTitle(new Title().setText("Administrator Chart")
					   				.setDisplay(true))
			   .setScales(new BarScale()
					   .addyAxes(new YAxis<LinearTicks>().setTicks(new LinearTicks().setBeginAtZero(true))))
			   .setLegend(new Legend().setDisplay(false));
		
		BarDataset dataSet = new BarDataset()
				.setLabel("shouts")
				.setData(values)
				.addBackgroundColors(Color.RED, Color.BLUE, Color.GREEN)
				.setBorderWidth(4)
				.setBorderColor(Color.BLACK);

		BarData data = new BarData()
				.addLabels("All Shouts", "Short Shouts", "Long Shouts")
				.addDataset(dataSet);
		
		
		String barChart = new BarChart(data, options).toJson();

		result = new ModelAndView("administrator/chart");
		result.addObject("barChart", barChart);
		return result;
	}

}
