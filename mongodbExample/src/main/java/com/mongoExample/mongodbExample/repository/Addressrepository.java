package com.mongoExample.mongodbExample.repository;

import com.mongoExample.mongodbExample.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Addressrepository extends MongoRepository<Address,String> {
    Address findByCityName(String cityName);
}
