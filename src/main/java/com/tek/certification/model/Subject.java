package com.tek.certification.model;

import com.tek.certification.enums.Level;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Subject extends BaseEntity {
    @Id
    private String subjectId;
    private String subjectName;
    private Level subjectLevel;
    private LocalDate testdate;

}
