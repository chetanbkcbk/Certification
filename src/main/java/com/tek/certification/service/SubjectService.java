package com.tek.certification.service;

import com.tek.certification.model.Subject;
import com.tek.certification.util.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface SubjectService {

    public ResponseEntity<ResponseStructure>addSubjects(Subject subject);

    ResponseEntity<ResponseStructure> findBySubjectLevel(String level);

    ResponseEntity<ResponseStructure> findAllSubjects(String level);
}
