package com.sofo.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Created by sofo on 2017/09/24.
 */
@Document
public class Product {
    @Id
    private ObjectId id;

    private String name;
    private BigDecimal sellingPrice;
    private BigDecimal purchasePrice;
    private int quantityOnHand;

    private ObjectId businessId;
    public Product() {}

    public Product(ObjectId id, String name, BigDecimal sellingPrice, BigDecimal purchasePrice, int quantityOnHand, ObjectId businessId) {
        this.id = id;
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.quantityOnHand = quantityOnHand;
        this.businessId = businessId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public ObjectId getBusinessId() {
        return businessId;
    }

    public void setBusinessId(ObjectId businessId) {
        this.businessId = businessId;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Product)) {
            return false;
        }

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(id, product.id)
                .append(name, product.name)
                .append(sellingPrice, product.sellingPrice)
                .append(purchasePrice, product.purchasePrice)
                .append(businessId, product.businessId)
                .append(quantityOnHand, product.quantityOnHand)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(sellingPrice)
                .append(purchasePrice)
                .append(businessId)
                .toHashCode();
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }
}
