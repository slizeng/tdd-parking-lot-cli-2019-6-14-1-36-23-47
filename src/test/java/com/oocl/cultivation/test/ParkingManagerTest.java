package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingManagerTest {
    @Test
    void should_any_type_of_parking_boys_be_added_into_management_list() {
        ParkingManager parkingManager = new ParkingManager(singletonList(new ParkingLot()));
        ParkingBoy standardParkingBoy = new StandardParkingBoy(singletonList(new ParkingLot()));
        ParkingBoy smartParkingBoy = new SmartParkingBoy(singletonList(new ParkingLot()));
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(singletonList(new ParkingLot()));

        parkingManager.addParkingBoy(standardParkingBoy);
        parkingManager.addParkingBoy(smartParkingBoy, superSmartParkingBoy);
        List<ParkingBoy> parkingBoyList = parkingManager.getParkingBoys();

        assertEquals(asList(standardParkingBoy, smartParkingBoy, superSmartParkingBoy), parkingBoyList);
    }


}
