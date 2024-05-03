package ibf2024.assessment.paf.batch4.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Orders;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.repositories.BeerRepository;
import ibf2024.assessment.paf.batch4.repositories.OrderRepository;

@Service
public class BeerService {
	@Autowired
	BeerRepository beerRepo;

	@Autowired
	OrderRepository orderRepo;

	public List<Style> getStyles() {

		return beerRepo.getStyles();
	}

	public List<Beer> getBreweriesByBeer(int styleId) {

		return beerRepo.getBreweriesByBeer(styleId);
	}

	public Optional<Brewery> getBeersFromBrewery(int breweryId) {
		return beerRepo.getBeersFromBrewery(breweryId);
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(List<Orders> orderList, int breweryId) {
		// TODO: Task 5 
		
		return orderRepo.placeOrder(orderList, breweryId);
	}

}
