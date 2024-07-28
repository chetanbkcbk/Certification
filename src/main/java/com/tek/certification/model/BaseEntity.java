package com.tek.certification.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    private String createdOn;
    private String modifiedOn;

    public void createBaseEntity()
    {
        createdOn= LocalDateTime.now().toString();
    }
}
