package com.tek.certification.service.serviceimpl;

import com.tek.certification.dao.SubjectDAO;
import com.tek.certification.dao.UserDAO;
import com.tek.certification.enums.Level;
import com.tek.certification.model.Subject;
import com.tek.certification.model.User;
import com.tek.certification.repository.SubjectRepository;
import com.tek.certification.service.UserService;
import com.tek.certification.util.ApiResponseStructure;
import com.tek.certification.util.CommonConstants;
import com.tek.certification.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final SubjectRepository subjectRepository;
    private final SubjectDAO subjectDAO;
    private final MongoTemplate mongoTemplate;
    private static int passdays = 365;

    @Override
    public ResponseEntity<ResponseStructure> addUser(User user) {

        User user1 = userDAO.createUser(user);
        return ApiResponseStructure.createResponse(user1);
    }

    @Override
    public ResponseEntity<ResponseStructure> findDisabledSubjectsByUser(String userId) {
        User user = userDAO.findDisabledSubByUser(userId);

        LocalDate currentdate = LocalDate.now();
        Map<String, String> subjectIdStatus = user.getSubjectIdStatus();

        Set<Map.Entry<String, String>> entries = subjectIdStatus.entrySet();


//        List<String> subjectIds = entries.stream().
//                filter(entry -> {
//                                return entry.getValue().equals("True");
//                                 }).map(entry -> entry.getKey()).filter(Objects::nonNull).collect(Collectors.toList());
//
//        Optional<Optional<Subject>> subject = subjectIds.stream().map(subjectRepository::findById).filter(Objects::nonNull).findAny();
//
//        Level subjectLevel = subject.get().get().getSubjectLevel();


        List<String> disabledlistsubjects = new ArrayList<>();//create a empty list to store subjectids which shud be disabled
        for (Map.Entry<String, String> entry : entries) {
            Subject subject = subjectDAO.findById(entry.getKey());//sub based on id
            String status = entry.getValue();//Pass or Fail
            Level subjectLevel = subject.getSubjectLevel();//
            int priority = subjectLevel.getPriority();
            LocalDate testdate = subject.getTestdate();

            if ("Fail".equals(status)) {
                if (currentdate.isAfter(testdate) && currentdate.isBefore(testdate.plusDays(disableddaysbasedonlevel(subjectLevel))))
                    disabledlistsubjects.add(entry.getKey());//adding into the list the subid's which must be disabled
            } else if ("Pass".equals(status)) {
                if (currentdate.isAfter(testdate) && currentdate.isBefore(testdate.plusDays(passdays))) {
                    String subjectName = subject.getSubjectName();
                    List<Subject> subjectNameList = subjectDAO.findBySubjectName(subjectName);

                    for (Subject s : subjectNameList) {
                        if (s.getSubjectLevel().getPriority() <= priority)
                            disabledlistsubjects.add(s.getSubjectId());
                    }
                }
            }
        }

        Query q = new Query();
        q.addCriteria(Criteria.where("subjectId").in(disabledlistsubjects));
        List<Subject> subjectnottobeshown = mongoTemplate.find(q, Subject.class);
        return ApiResponseStructure.foundResponse(subjectnottobeshown);

    }


    static int disableddaysbasedonlevel(Level level) {
        if (Level.BEGINNER.toString().equals("BEGINNER"))
            return 15;
        else if (Level.INTERMEDIATE.toString().equals("INTERMEDIATE"))
            return 30;
        else if (Level.ADVANCE.toString().equals("ADVANCE"))
            return 45;
        return 0;
    }


    @Override
    public ResponseEntity<ResponseStructure> checkIfUserCanBookSlotOrNot(String userId, LocalDate toBookDate, String toBookSlotId) {

        User user = userDAO.findUserById(userId);
        Map<LocalDate, String> slotStatus = user.getSlotStatus();

        for (Map.Entry<LocalDate, String> localDateSlotIdEntry : slotStatus.entrySet()) {

            LocalDate bookeddate = localDateSlotIdEntry.getKey();
            String bookedslotid = localDateSlotIdEntry.getValue();

            if( toBookDate.isBefore(LocalDate.now()))
             return    ApiResponseStructure.badRequest(CommonConstants.DATE_INVALID);

            else if ( (bookeddate.isEqual(toBookDate) && bookedslotid.equals(toBookSlotId) )) {
                return   ApiResponseStructure.badRequest(CommonConstants.INVALID_SLOTBOOKING);
            }
        }

 return ApiResponseStructure.createResponse(CommonConstants.VALID_SLOTBOOKING);

    }

}


