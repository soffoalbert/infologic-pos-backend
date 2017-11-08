package com.sofo.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sofo on 2017/09/24.
 */
@Document
public class Business {

    @Id
    private ObjectId id;
    private String name;
    private String address;
    private String telephone;
    private String website;
    private ObjectId userId;

    public  Business(){}

    public Business(ObjectId id, String name, String address, String telephone, String website, ObjectId userId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.website = website;
        this.userId = userId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Business)) {
            return false;
        }

        Business business = (Business) o;

        return new EqualsBuilder()
                .append(id, business.id)
                .append(name, business.name)
                .append(address, business.address)
                .append(telephone, business.telephone)
                .append(website, business.website)
                .append(userId, business.userId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(address)
                .append(telephone)
                .append(website)
                .append(userId)
                .toHashCode();
    }


}
