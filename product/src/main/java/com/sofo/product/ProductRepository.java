package com.sofo.product;

import com.sofo.domain.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by sofo on 2017/09/24.
 */
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    List<Product> findAllByBusinessId(ObjectId businessId);

    Product findById(ObjectId productId);
}
