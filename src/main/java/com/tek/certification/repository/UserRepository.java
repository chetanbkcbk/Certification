package com.tek.certification.repository;

import com.tek.certification.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {


}
