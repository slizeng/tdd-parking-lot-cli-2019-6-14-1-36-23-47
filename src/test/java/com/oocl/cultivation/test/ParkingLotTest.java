package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_10_when_getAvailableParkingPosition_given_a_default_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();

        int parkingPosition = parkingLot.getAvailableParkingPosition();

        assertEquals(10, parkingPosition);
    }

    @Test
    void should_return_a_number_when_getAvailableParkingPosition_given_a_parking_lot_with_capacity() {
        ParkingLot parkingLot = new ParkingLot(1);

        int parkingPosition = parkingLot.getAvailableParkingPosition();

        assertEquals(1, parkingPosition);
    }

    @Test
    void should_return_a_ticket_when_park_a_car_into_parkingLot_given_a_parkingLot_has_capacity() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.park(car);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_available_parking_position_reduces_one_when_park_a_car_into_parkingLot_given_a_parkingLot_has_capacity() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        int positionBeforePark = parkingLot.getAvailableParkingPosition();

        parkingLot.park(car);
        int positionAfterPark = parkingLot.getAvailableParkingPosition();

        assertEquals(positionBeforePark - 1, positionAfterPark);
    }
}
