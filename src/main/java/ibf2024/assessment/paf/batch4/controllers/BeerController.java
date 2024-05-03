package ibf2024.assessment.paf.batch4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.services.BeerService;
import jakarta.websocket.server.PathParam;

@Controller
public class BeerController {

	@Autowired
	BeerService beerSvc;

	//TODO Task 2 - view 0
	@GetMapping("/styles")
	public String getStyles(Model model) {

		List<Style> stylesList = beerSvc.getStyles();
		model.addAttribute("styles", stylesList);

		return "view0";
	}
	
	
	//TODO Task 3 - view 1
	@GetMapping("/beer/style/{styleId}")
	public String getBeerById(@PathVariable int styleId,
			@RequestParam String styleName,
			Model model) {
		System.out.println(styleId + styleName);

		List<Beer> beerList = beerSvc.getBreweriesByBeer(styleId);
		model.addAttribute("beerList", beerList);

		return "view1";
	}
	

	//TODO Task 4 - view 2

	
	//TODO Task 5 - view 2, place order

}
