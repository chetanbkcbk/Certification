package com.tek.certification.repository;

import com.tek.certification.model.Slot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SlotRepository extends MongoRepository<Slot,String> {
}
