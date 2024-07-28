package com.tek.certification.service;

import com.tek.certification.model.Slot;
import com.tek.certification.util.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface SlotService {

    public ResponseEntity<ResponseStructure>addSlot(Slot slot);

    ResponseEntity<ResponseStructure> findAllSlots();

    ResponseEntity<ResponseStructure> displaySlotsByDateAndTime(String entereddate);
}
