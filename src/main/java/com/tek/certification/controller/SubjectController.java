package com.tek.certification.controller;

import com.tek.certification.enums.Level;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.tek.certification.model.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tek.certification.service.SubjectService;
import com.tek.certification.util.ResponseStructure;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {

   private final SubjectService subjectService;

   @PostMapping("/")
   public ResponseEntity<ResponseStructure> addSubjects(@RequestBody Subject subject)
   {

      return subjectService.addSubjects(subject);

   }

   @GetMapping("/")           //finding using predefined methods repository
   public ResponseEntity<ResponseStructure> findBySubjectLevel(@RequestParam String level)
   {

      return subjectService.findBySubjectLevel(level);

   }

   @GetMapping("/subs")       //finding using STREAMS API
   public ResponseEntity<ResponseStructure> findAllSubjects(@RequestParam String level)
   {

      return subjectService.findAllSubjects(level);

   }

}
