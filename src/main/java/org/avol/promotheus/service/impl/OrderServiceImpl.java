package org.avol.promotheus.service.impl;

import org.avol.promotheus.model.Order;
import org.avol.promotheus.service.OrderService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Durga, Padala on 06/08/17.
 */
public class OrderServiceImpl implements OrderService {

    private final Map<String, Order> orderStore;

    public OrderServiceImpl() {
        this.orderStore = new HashMap<>();
    }

    @Override
    public boolean post(Order order) {
        orderStore.put(order.getId(), order);
        return true;
    }

    @Override
    public Order get(String id) {
        return orderStore.get(id);
    }

    @Override
    public Order put(Order order) {
        orderStore.put(order.getId(), order);
        return order;
    }

    @Override
    public boolean delete(String id) {
        orderStore.remove(id);
        return true;
    }
}
