package com.sofo.sale;

import com.sofo.domain.Business;
import com.sofo.domain.Product;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sofo on 2017/09/24.
 */
@Document
public class Sale {

    @Id
    private ObjectId id;
    private ZonedDateTime timeStamp = ZonedDateTime.now();

    private Set<Product> products = new HashSet<>();

    private Business business;

    enum SaleStatus {
        OMPLETED, VOIDED
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Product)) {
            return false;
        }

        Sale sale = (Sale) o;

        return new EqualsBuilder()
                .append(id, sale.id)
                .append(business, sale.business)
                .append(products, sale.products)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(business)
                .append(products)
                .toHashCode();
    }
}
