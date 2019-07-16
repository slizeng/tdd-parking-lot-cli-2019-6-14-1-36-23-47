package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.SmartParkingBoy;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmartParkingBoyTest {
    @Test
    void should_car_be_parked_into_the_parking_lot_with_most_available_position_when_park_a_car_given_two_parking_lots() {
        ParkingLot parkingLotWithLessAvailablePositions = new ParkingLot(1);
        ParkingLot parkingLotWithMoreAvailablePositions = new ParkingLot(2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(asList(
                parkingLotWithLessAvailablePositions,
                parkingLotWithMoreAvailablePositions
        ));

        ParkingTicket ticket = smartParkingBoy.park(new Car());

        assertNotNull(ticket);
        assertEquals(1, parkingLotWithLessAvailablePositions.getAvailableParkingPosition());
        assertEquals(1, parkingLotWithMoreAvailablePositions.getAvailableParkingPosition());
    }
}
