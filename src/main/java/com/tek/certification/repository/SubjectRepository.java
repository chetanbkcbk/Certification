package com.tek.certification.repository;

import com.tek.certification.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubjectRepository extends MongoRepository<Subject,String> {
    List<Subject> findBySubjectLevel(String level);
}
