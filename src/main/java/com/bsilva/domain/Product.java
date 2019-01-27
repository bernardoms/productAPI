package com.bsilva.domain;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Table(name = "product")
public class Product {
    @PartitionKey
    @Column(name = "id")
    @NotNull
    private String productId;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 ]*$")
    private String name;
    @NotNull
    @NumberFormat
    private double price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
