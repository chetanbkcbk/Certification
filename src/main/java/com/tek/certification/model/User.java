package com.tek.certification.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Map;

@Data
@Document
public class User extends BaseEntity{

    @Id
    private String userId;
    private String username;

    private Map<String,String> subjectIdStatus; //subjectId,status
    private Map<LocalDate,String>slotStatus; //EnteredDate,slotId
}
