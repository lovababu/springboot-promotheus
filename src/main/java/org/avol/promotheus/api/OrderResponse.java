package org.avol.promotheus.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Durga, Padala on 06/08/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {

    private String message;
    private int statusCode;

    public OrderResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
