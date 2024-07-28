package com.tek.certification.dao.daoimpl;

import com.tek.certification.dao.SubjectDAO;
import com.tek.certification.model.Subject;
import com.tek.certification.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectDAOImpl implements SubjectDAO {

    private final SubjectRepository subjectRepository;
private final MongoTemplate mongoTemplate;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.insert(subject); //returns the inserted entity
    }

    @Override                   //here using predefined methods from repository
    public List<Subject> findBylevel(String level) {
        return subjectRepository.findBySubjectLevel(level);
    }

    @Override   //Using STREAMS API
    public List<Subject> findAllSubjects(String level) {

        List<Subject> findingSubjectsbylevelusingstreams = subjectRepository.findAll().stream().filter(subject -> subject.getSubjectLevel().name().equals(level)).collect(Collectors.toList());
        return findingSubjectsbylevelusingstreams;
    }

    @Override
    public Subject findById(String subjectId) {
        Subject subject= subjectRepository.findById(subjectId).orElseThrow(()-> new NoSuchElementException("No subject found"));
        return subject;
    }

    @Override
    public List<Subject> findBySubjectName(String subjectName) {
        Query query=new Query();
        query.addCriteria(Criteria.where("subjectName").is(subjectName));
        return mongoTemplate.find(query,Subject.class);

    }
}
