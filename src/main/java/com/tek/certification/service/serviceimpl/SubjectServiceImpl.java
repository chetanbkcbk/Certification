package com.tek.certification.service.serviceimpl;

import com.tek.certification.dao.SubjectDAO;
import com.tek.certification.util.CommonConstants;
import lombok.RequiredArgsConstructor;
import com.tek.certification.model.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.tek.certification.service.SubjectService;
import com.tek.certification.util.ApiResponseStructure;
import com.tek.certification.util.ResponseStructure;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectDAO subjectDAO;


    @Override
    public ResponseEntity<ResponseStructure> addSubjects(Subject subject) {
                            subject.createBaseEntity();
        Subject subject1 = subjectDAO.createSubject(subject);
        return ApiResponseStructure.createResponse(subject1);
    }

    @Override                      //using predefined methods in repository
    public ResponseEntity<ResponseStructure> findBySubjectLevel(String level) {

        List<Subject> subjectlistByLevel = subjectDAO.findBylevel(level);
        if(CollectionUtils.isEmpty(subjectlistByLevel))
            return  ApiResponseStructure.badRequest(CommonConstants.SUBJECT_NOTFOUND);
        else
            return ApiResponseStructure.foundResponse(subjectlistByLevel);
    }

    @Override                   //using STREAMS API
    public ResponseEntity<ResponseStructure> findAllSubjects(String level) {
        List<Subject> allSubjects = subjectDAO.findAllSubjects(level);
        return ApiResponseStructure.foundResponse(allSubjects);
    }
}
