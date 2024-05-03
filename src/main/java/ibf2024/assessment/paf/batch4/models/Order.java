package ibf2024.assessment.paf.batch4.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Order {

    private String orderId;
    private Date date;
    private int brewryId;
    private List<Orders> orders = new ArrayList<>();

    public String getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBrewryId() {
        return brewryId;
    }

    public void setBrewryId(int brewryId) {
        this.brewryId = brewryId;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
