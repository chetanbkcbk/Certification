package com.tek.certification.enums;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
 @RequiredArgsConstructor   //1
public enum Level {

    //2nd
    BEGINNER(1),
    INTERMEDIATE(2),
    ADVANCE(3);

    //3rd
    public  final int priority;

}
