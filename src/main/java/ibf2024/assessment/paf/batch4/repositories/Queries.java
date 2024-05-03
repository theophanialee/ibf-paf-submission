package ibf2024.assessment.paf.batch4.repositories;

public interface Queries {

    public static final String GET_ALL_STYLES = """
            SELECT s.style_name, s.id AS style_id, COUNT(b.id) AS beer_count
            FROM styles s
            LEFT JOIN beers b ON s.id = b.style_id
            GROUP BY s.style_name, s.id
            ORDER BY beer_count DESC, s.style_name ASC;
                        """;
    public static final String GET_BREWERIES_BY_BEER = """
            SELECT beers.name AS beer_name,
                   beers.descript AS descript,
                   breweries.name AS brewery_name,
                   beers.id AS beer_id,
                   beers.brewery_id AS brewery_id
            FROM beers
            LEFT JOIN breweries ON beers.brewery_id = breweries.id
            WHERE beers.style_id = ?
            ORDER BY beer_name ASC;
                           """;
    public static final String GET_BEERS_BY_BREWERY = """
            SELECT breweries.name AS brewery_name,
            breweries.id AS brewery_id,
            breweries.descript AS brew_descript,
            breweries.address1 AS address1,
            breweries.address2 AS address2,
            breweries.city AS city,
            breweries.phone AS phone,
            breweries.website AS website,
            beers.id AS beer_id,
            beers.name AS beer_name,
            beers.descript AS beer_descript
            from breweries
            LEFT JOIN beers ON breweries.id=beers.brewery_id
            WHERE beers.brewery_id=?
            ORDER BY beers.name ASC;
                                   """;

    // public static final String SQL_ADD_ORDER = """
    // INSERT INTO orders(order_date,customer_name,ship_address,notes,tax)
    // VALUES (?,?,?,?,?)
    // """;
    // public static final String SQL_ADD_ORDER_DETAIL = """
    // INSERT INTO orders_details(product,unit_price,discount,quantity,order_id)
    // VALUES (?,?,?,?,?)
    // """;
    // public static final String SQL_GET_ID = """
    // SELECT LAST_INSERT_ID()
    // """;
}
