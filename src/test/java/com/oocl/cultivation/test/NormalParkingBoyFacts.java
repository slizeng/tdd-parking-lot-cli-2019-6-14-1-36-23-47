package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.NormalParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class NormalParkingBoyFacts {
    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        Car fetched = parkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot = new ParkingLot();
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);

        Car fetchedByFirstTicket = parkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = parkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(wrongTicket));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));
        ParkingTicket wrongTicket = new ParkingTicket();

        parkingBoy.fetch(wrongTicket);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_clear_the_message_once_the_operation_is_succeeded() {
        ParkingLot parkingLot = new ParkingLot();
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));
        ParkingTicket wrongTicket = new ParkingTicket();

        parkingBoy.fetch(wrongTicket);
        assertNotNull(parkingBoy.getLastErrorMessage());

        ParkingTicket ticket = parkingBoy.park(new Car());
        assertNotNull(ticket);
        assertNull(parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(null));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));

        parkingBoy.fetch(null);

        assertEquals(
                "Please provide your parking ticket.",
                parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        ParkingLot parkingLot = new ParkingLot();
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        assertNull(parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_error_message_for_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        parkingBoy.fetch(ticket);

        assertEquals(
                "Unrecognized parking ticket.",
                parkingBoy.getLastErrorMessage()
        );
    }

    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));

        parkingBoy.park(new Car());

        assertNull(parkingBoy.park(new Car()));
    }

    @Test
    void should_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        NormalParkingBoy parkingBoy = new NormalParkingBoy(singletonList(parkingLot));

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_park_cars_per_natual_order_when_park_cars_into_multiple_parking_lots() {
        final int capacity = 1;
        ParkingLot firstParkingLot = new ParkingLot(capacity);
        ParkingLot secondParkingLot = new ParkingLot(capacity);
        NormalParkingBoy parkingBoy = new NormalParkingBoy(asList(firstParkingLot, secondParkingLot));

        parkingBoy.park(new Car());

        assertEquals(0, firstParkingLot.getAvailableParkingPosition());
        assertEquals(1, secondParkingLot.getAvailableParkingPosition());
    }

    @Test
    void should_park_car_into_second_parking_lot_when_park_a_car_given_two_parking_lots_and_first_one_is_full() {
        final int capacity = 1;
        ParkingLot firstParkingLot = new ParkingLot(capacity);
        ParkingLot secondParkingLot = new ParkingLot(capacity);
        NormalParkingBoy parkingBoy = new NormalParkingBoy(asList(firstParkingLot, secondParkingLot));
        parkingBoy.park(new Car());

        parkingBoy.park(new Car());

        assertEquals(0, firstParkingLot.getAvailableParkingPosition());
        assertEquals(0, secondParkingLot.getAvailableParkingPosition());
    }
}