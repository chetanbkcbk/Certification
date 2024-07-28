package com.tek.certification.service;

import com.tek.certification.model.User;
import com.tek.certification.util.ResponseStructure;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface UserService {

    public ResponseEntity<ResponseStructure> addUser(User user);

    ResponseEntity<ResponseStructure> findDisabledSubjectsByUser(String userId);

    ResponseEntity<ResponseStructure> checkIfUserCanBookSlotOrNot(String userId, LocalDate toBookDate, String toBookSlotId);
}
