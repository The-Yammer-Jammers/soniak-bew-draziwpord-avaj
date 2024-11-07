package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    String username;
    String password;

    @JsonCreator
    public RegisterRequest(
            @JsonProperty("username") final String username,
            @JsonProperty("password") final String password
    ) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public static class OrderProduct {
        private int orderId;
        private int productId;
        private String name;
        private int quantity;

        public OrderProduct(final int orderId,
                            final int productId,
                            final String name,
                            final int quantity) {
            this.orderId = orderId;
            this.productId = productId;
            this.name = name;
            this.quantity = quantity;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(final int orderId) {
            this.orderId = orderId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(final int productId) {
            this.productId = productId;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(final int quantity) {
            this.quantity = quantity;
        }
    }
}
