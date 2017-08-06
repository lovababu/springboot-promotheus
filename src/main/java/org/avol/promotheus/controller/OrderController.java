package org.avol.promotheus.controller;

import io.prometheus.client.Counter;
import io.prometheus.client.Summary;
import org.avol.promotheus.api.OrderResponse;
import org.avol.promotheus.model.Order;
import org.avol.promotheus.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Durga, Padala on 06/08/17.
 */
@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    static final Counter requests = Counter.build()
            .name("requests_total").help("Total Order requests.").register();


    static final Summary requestLatency = Summary.build()
            .quantile(0.5, 0.05)   // Add 50th percentile (= median) with 5% tolerated error
            .quantile(0.9, 0.01)   // Add 90th percentile with 1% tolerated error
            .name("requests_latency_seconds").help("Order Request latency in seconds.").register();

    /*static final Histogram requestLatency = Histogram.build()
            .name("requests_latency_seconds").help("Request latency in seconds.").register();*/

    @Autowired
    private OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> post(@RequestBody Order order) {
        requests.inc();
        requestLatency.time(() -> {
            orderService.post(order);
        });
        return ResponseEntity.ok(new OrderResponse(HttpStatus.OK.value(), "Order placed successfully."));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> get(@PathVariable("id") String id) {
        requests.inc();
        return ResponseEntity.ok(orderService.get(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<OrderResponse> delete(@PathVariable("id") String id) {
        requests.inc();
        requestLatency.time(() -> {
            orderService.delete(id);
        });
        return ResponseEntity.ok(new OrderResponse(HttpStatus.OK.value(), "Order deleted successfully."));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> update(@RequestBody Order order) {
        requests.inc();
        requestLatency.time(() -> {
            orderService.put(order);
        });
        return ResponseEntity.ok(order);
    }
}
