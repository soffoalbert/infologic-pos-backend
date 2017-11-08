package com.sofo.sale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sofo on 2017/09/24.
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class SaleRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testRepositoryMethods(){

    }
}



