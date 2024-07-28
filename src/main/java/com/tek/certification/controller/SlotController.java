package com.tek.certification.controller;

import com.tek.certification.model.Slot;
import com.tek.certification.service.SlotService;
import com.tek.certification.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slots")
@RequiredArgsConstructor
public class SlotController {

    private final SlotService slotService;

    @PostMapping("/")
    public ResponseEntity<ResponseStructure> addSlot(@RequestBody Slot slot)
    {
        return slotService.addSlot(slot);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseStructure> findAllSlots(){
        return slotService.findAllSlots();
    }

    @GetMapping("/sl")
    public ResponseEntity<ResponseStructure>displaySlotsByDateAndTime(@RequestParam String entereddate)
    {
        return slotService.displaySlotsByDateAndTime(entereddate);
    }

}
