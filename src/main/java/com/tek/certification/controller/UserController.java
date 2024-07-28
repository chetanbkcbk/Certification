package com.tek.certification.controller;

import com.tek.certification.model.User;
import com.tek.certification.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tek.certification.service.UserService;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<ResponseStructure> addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @GetMapping("/{userId}")                 //based on userid im finding the disabled subjects
    public ResponseEntity<ResponseStructure>  findDisabledSubjectsByUser(@PathVariable String userId)
    {
        return userService.findDisabledSubjectsByUser(userId);
    }

    @GetMapping("/{userId}/{toBookSlotId}")
    public ResponseEntity<ResponseStructure>checkIfUserCanBookSlotOrNot(@PathVariable String userId,@RequestParam LocalDate toBookDate,@PathVariable String toBookSlotId)
    {
        return userService.checkIfUserCanBookSlotOrNot(userId,toBookDate,toBookSlotId);
    }


}
