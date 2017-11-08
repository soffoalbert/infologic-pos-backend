package com.sofo.sale;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by sofo on 2017/09/24.
 */
public interface SaleRepository extends MongoRepository<Sale, ObjectId> {

    List<Sale> findSaleByBusiness(ObjectId businessId);
}
