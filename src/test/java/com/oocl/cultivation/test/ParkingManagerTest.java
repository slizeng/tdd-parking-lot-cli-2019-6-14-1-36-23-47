package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    void should_a_car_be_parked_into_parking_manager_itself_parking_lot_when_parking_manager_park_a_car_given_a_parking_manager_without_managing_parking_boy() {
        ParkingLot parkingLotOfManager = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(singletonList(parkingLotOfManager));

        ParkingTicket ticket = parkingManager.park(new Car());

        assertNotNull(ticket);
        assertEquals(0, parkingLotOfManager.getAvailableParkingPosition());
    }

    @Test
    void should_a_car_be_parked_into_parking_boys_lot_when_park_a_car_given_a_parking_manager_without_available_position_and_managing_a_parking_boy() {
        ParkingManager parkingManager = new ParkingManager(singletonList(new ParkingLot(0)));
        ParkingLot parkingLotOfBoy = new ParkingLot(1);
        StandardParkingBoy managedBoy = new StandardParkingBoy(singletonList(parkingLotOfBoy));
        parkingManager.addParkingBoy(managedBoy);

        ParkingTicket ticket = parkingManager.parkByBoy(managedBoy, new Car());

        assertNotNull(ticket);
        assertEquals(0, parkingLotOfBoy.getAvailableParkingPosition());
    }
}
