package com.tek.certification.util;

import com.tek.certification.model.Subject;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ApiResponseStructure {

    private final static ResponseStructure responseStructure=new ResponseStructure();

    public  static ResponseEntity<ResponseStructure>createResponse(Object obj)
    {
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setHttpStatus(HttpStatus.CREATED);
        responseStructure.setData(obj);
        return new ResponseEntity<ResponseStructure>(responseStructure,HttpStatus.CREATED);
    }

    public static ResponseEntity<ResponseStructure> foundResponse(Object obj) {
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setHttpStatus(HttpStatus.FOUND);
        responseStructure.setData(obj);
        return new ResponseEntity<ResponseStructure>(responseStructure,HttpStatus.FOUND);

    }

    public static ResponseEntity<ResponseStructure> badRequest(Object obj) {
        responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
        responseStructure.setHttpStatus(HttpStatus.BAD_REQUEST);
        responseStructure.setData(obj);
        return new ResponseEntity<ResponseStructure>(responseStructure,HttpStatus.BAD_REQUEST);

    }
}
