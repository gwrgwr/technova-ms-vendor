package com.technova.msvendor.repository;

import com.technova.msvendor.entity.VendorEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends MongoRepository<VendorEntity, ObjectId> {
    Optional<VendorEntity> findByEmail(String email);
}
