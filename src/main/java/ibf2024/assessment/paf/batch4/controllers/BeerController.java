package ibf2024.assessment.paf.batch4.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Order;
import ibf2024.assessment.paf.batch4.models.Orders;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.services.BeerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

		model.addAttribute("styleName", styleName);
		model.addAttribute("styleId", styleId);
		model.addAttribute("beerList", beerList);

		return "view1";
	}
	

	//TODO Task 4 - view 2

	@GetMapping("/beer/brewery/{breweryId}")
	public String getMethodName(@PathVariable int breweryId,
			@RequestParam String breweryName,
			@RequestParam String sid,
			Model model) {

		Brewery brewery = beerSvc.getBeersFromBrewery(breweryId).get();

		model.addAttribute("brewery", brewery);
		model.addAttribute("beerList", brewery.getBeers());
		model.addAttribute("styleId", sid);
		return "view2";
	}
	
	//TODO Task 5 - view 2, place order
	@PostMapping("/brewery/{breweryId}/order")
	public String submitOrder(@PathVariable int breweryId, @RequestBody String order, Model model) {
		String[] orderArr = order.split("&");

		// Find the midpoint to split between quantity and beerId
		int midpoint = orderArr.length / 2;

		LinkedList<Orders> orderList = new LinkedList<>();
		for (int i = 0; i < midpoint; i++) {
			Orders detail = new Orders();

			// Get the quantity and beerId corresponding to the current index
			String quantity = orderArr[i];
			String beerId = orderArr[midpoint + i];

			// Extract the value part from the key-value pair
			String[] quantityPair = quantity.split("=");
			String[] beerIdPair = beerId.split("=");

			// Check if both quantity and beerId are not empty
			if (quantityPair.length == 2 && beerIdPair.length == 2) {
				int quantityValue = Integer.parseInt(quantityPair[1]);
				int beerIdValue = Integer.parseInt(beerIdPair[1]);
				detail.setQuantity(quantityValue);
				detail.setBeerId(beerIdValue);
				orderList.add(detail);
			}
		}

		String orderId = beerSvc.placeOrder(orderList, breweryId);
		model.addAttribute("orderId", orderId);
		return "view3";
	}

}
