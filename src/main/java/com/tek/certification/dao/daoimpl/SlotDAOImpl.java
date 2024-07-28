package com.tek.certification.dao.daoimpl;

import com.tek.certification.dao.SlotDAO;
import com.tek.certification.model.Slot;
import com.tek.certification.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SlotDAOImpl implements SlotDAO {

    private final SlotRepository slotRepository;

    @Override
    public Slot createSlot(Slot slot) {

        Slot slot1 = slotRepository.insert(slot);
        return slot1;
    }

    @Override
    public List<Slot> findSlots() {
        List<Slot> slots = slotRepository.findAll();
       if(!CollectionUtils.isEmpty(slots))
        return slots;
           throw new NoSuchElementException("Empty Slots");
    }

    @Override
    public List<Slot> displaySlotsBasedOnDate(String entereddate) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate.parse(entereddate, dateTimeFormatter);
        return List.of();
    }


}
