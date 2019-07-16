package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
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

    @Test
    void should_throw_NoAvailableParkingPositionException_when_park_a_car_into_parkingLot_given_a_parkingLot_has_not_capacity() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);

        int position = parkingLot.getAvailableParkingPosition();

        assertEquals(0, position);
        assertThrows(NoAvailableParkingPositionException.class, () -> parkingLot.park(new Car()));
    }

    @Test
    void should_return_correspond_car_when_fetch_a_car_from_a_parking_lot_with_right_ticket_given_a_car_parking_in_a_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket ticket = parkingLot.park(car);

        Car returnedCar = parkingLot.fetch(ticket);

        assertSame(car, returnedCar);
    }


    @Test
    void should_throw_NoSuchCarException_when_fetch_a_car_from_a_parking_lot_with_invalid_ticket_given_a_car_parking_in_a_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        Car targetCar = new Car();
        ParkingTicket ticket = parkingLot.park(targetCar);
        parkingLot.fetch(ticket);

        assertThrows(NoSuchCarException.class, () -> parkingLot.fetch(ticket));
        assertThrows(NoSuchCarException.class, () -> parkingLot.fetch(null));
    }
}
