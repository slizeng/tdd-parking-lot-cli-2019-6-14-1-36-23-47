package com.oocl.cultivation.test;

import com.oocl.cultivation.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParkingLotTest {
    @Test
    void should_return_a_number_when_getAvailableParkingPosition_given_a_parking_lot_with_capacity() {
        ParkingLot parkingLot = new ParkingLot(1);

        int parkingPosition = parkingLot.getAvailableParkingPosition();

        assertEquals(1, parkingPosition);
    }
}
