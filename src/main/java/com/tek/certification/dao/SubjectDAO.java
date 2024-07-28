package com.tek.certification.dao;

import com.tek.certification.model.Subject;

import java.util.List;

public interface SubjectDAO {

    public Subject createSubject(Subject subject);

    List<Subject> findBylevel(String level);//using predefined methods

    List<Subject>  findAllSubjects(String level);//using Streams Api

    Subject findById(String subjectId);

    List<Subject> findBySubjectName(String subjectName);
}
