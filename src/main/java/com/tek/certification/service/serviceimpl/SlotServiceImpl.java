package com.tek.certification.service.serviceimpl;

import com.tek.certification.dao.SlotDAO;
import com.tek.certification.model.Slot;
import com.tek.certification.repository.SlotRepository;
import com.tek.certification.service.SlotService;
import com.tek.certification.util.ApiResponseStructure;
import com.tek.certification.util.CommonConstants;
import com.tek.certification.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {

    private final SlotDAO slotDAO;

    @Override
    public ResponseEntity<ResponseStructure> addSlot(Slot slot) {
        slot.createBaseEntity();
        Slot slot1 = slotDAO.createSlot(slot);
        return ApiResponseStructure.createResponse(slot1);
    }

    @Override
    public ResponseEntity<ResponseStructure> findAllSlots() {
        List<Slot> slots = slotDAO.findSlots();
        return ApiResponseStructure.foundResponse(slots);
    }

    @Override
    public ResponseEntity<ResponseStructure> displaySlotsByDateAndTime(String entereddate) {
        List<Slot> slots = slotDAO.findSlots();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate enteredLocalDate = LocalDate.parse(entereddate, dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String currenttime = LocalTime.now().format(timeFormatter);
        LocalTime current = LocalTime.parse(currenttime, timeFormatter);

        System.out.println(currenttime);
        if(enteredLocalDate.isBefore(LocalDate.now()))    //validating the user entered Date
           // throw new NoSuchElementException("Invalid Date"); //try not to throw exception in service class
       return ApiResponseStructure.badRequest(CommonConstants.DATE_INVALID);

//to validate the user entered time and display the slots that are remaining
       List<Slot> slotstobedisplayed=new ArrayList<>();
        for(Slot slot:slots)
        {
            String startTime = slot.getStartTime();

            LocalTime stinlocalformat = LocalTime.parse(startTime,timeFormatter);//start time in Local Format
            System.out.println(stinlocalformat);
            if( (enteredLocalDate.isEqual(LocalDate.now() )&&stinlocalformat.isAfter(current) )  ||enteredLocalDate.isAfter(LocalDate.now()))
            {
                slotstobedisplayed.add(slot);
            }
       /*     else if (enteredLocalDate.isAfter(LocalDate.now()))
            {
                slotstobedisplayed.add(slot);
            }
       */
        }
        return ApiResponseStructure.foundResponse(slotstobedisplayed);
    }
}
