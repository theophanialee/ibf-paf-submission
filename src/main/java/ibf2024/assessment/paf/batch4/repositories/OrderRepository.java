package ibf2024.assessment.paf.batch4.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.Order;
import ibf2024.assessment.paf.batch4.models.Orders;

@Repository
public class OrderRepository {

	MongoTemplate template;

	// TODO: Task 5
	public String placeOrder(List<Orders> orderList, int breweryId) {
		// TODO: Task 5
		String orderId = UUID.randomUUID().toString().substring(0, 8);

		Order o = new Order();

		o.setOrderId(orderId);
		o.setBrewryId(breweryId);
		o.setOrders(orderList);
		o.setDate(new Date());

		template.save(o);

		return o.getOrderId();
	}
}
