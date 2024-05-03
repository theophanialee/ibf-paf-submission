package ibf2024.assessment.paf.batch4.repositories;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;

@Repository
public class BeerRepository implements Queries {
	@Autowired
	JdbcTemplate jdbcTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2
		List<Style> result = new LinkedList<>();
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_ALL_STYLES);
		while (rs.next()) {
			Style s = new Style();
			s.setStyleId(rs.getInt("id"));
			s.setName(rs.getString("style_name"));
			s.setBeerCount(rs.getInt("beer_count"));
			result.add(s);
		}

		return Collections.unmodifiableList(result);
	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(int styleId) {
		// TODO: Task 3
		List<Beer> result = new LinkedList<>();
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_BREWERIES_BY_BEER, styleId);

		while (rs.next()) {
			Beer b = new Beer();
			b.setBeerName(rs.getString("beer_name"));
			b.setBreweryName(rs.getString("brewery_name"));
			b.setBeerId(rs.getInt("beer_id"));
			b.setBreweryId(rs.getInt("brewery_id"));
			if (rs.getString("descript").isEmpty()) {
				b.setBeerDescription("N/A");
			} else {
				b.setBeerDescription(rs.getString("descript"));
			}
			result.add(b);
		}

		return Collections.unmodifiableList(result);

	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(int brewryId) {
		// TODO: Task 4

		final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_BEERS_BY_BREWERY, brewryId);

		if (!rs.next()) {
			return Optional.empty();
		}

		Brewery rBrew = new Brewery();
		rBrew.setBreweryId(brewryId);

		rBrew.setName(rs.getString("brewery_name"));
		rBrew.setAddress1(rs.getString("address1"));
		rBrew.setAddress2(rs.getString("address2"));
		rBrew.setCity(rs.getString("city"));
		rBrew.setPhone(rs.getString("phone"));
		rBrew.setWebsite(rs.getString("website"));
		rBrew.setDescription(rs.getString("brew_descript"));

		List<Beer> rBeers = new LinkedList<>();

		while (rs.next()) {
			Beer b = new Beer();
			b.setBeerName(rs.getString("beer_name"));
			b.setBreweryName(rs.getString("brewery_name"));
			b.setBeerId(rs.getInt("beer_id"));
			b.setBreweryId(rs.getInt("brewery_id"));
			if (rs.getString("beer_descript").isEmpty()) {
				b.setBeerDescription("N/A");
			} else {
				b.setBeerDescription(rs.getString("beer_descript"));
			}
			rBeers.add(b);
		}

		rBrew.setBeers(rBeers);

		return Optional.of(rBrew);

	}
}
