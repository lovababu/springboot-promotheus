package org.avol.promotheus.service;

import org.avol.promotheus.model.Order;

/**
 * @author Durga, Padala on 06/08/17.
 */
public interface OrderService {

    boolean post(Order order);

    Order get(String id);

    Order put(Order order);

    boolean delete(String id);
}
