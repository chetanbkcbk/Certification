package com.tek.certification.dao;

import com.tek.certification.model.Slot;

import java.util.List;

public interface SlotDAO {

   public  Slot createSlot(Slot slot);

   List<Slot> findSlots();

   List<Slot> displaySlotsBasedOnDate(String entereddate);

}
