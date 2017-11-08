package com.sofo.business;

import com.sofo.domain.Business;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by sofo on 2017/09/24.
 */
public interface BusinessRepository extends MongoRepository<Business, ObjectId>{
    List<Business> findAllByUserId(ObjectId userId);
}
