package com.tek.certification.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Slot extends BaseEntity{

    private String slotId;
    private String startTime;
    private String endTime;
}
