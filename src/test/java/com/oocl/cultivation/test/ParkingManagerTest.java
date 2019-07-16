package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import com.oocl.cultivation.exception.CannotAssignTaskToParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

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
    void should_return_correspond_car_when_parking_manager_fetch_a_car_by_himself_given_a_car_parked_into_parking_managers_lot() {
        ParkingLot parkingLotOfManager = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(singletonList(parkingLotOfManager));
        Car targetCar = new Car();
        ParkingTicket ticket = parkingManager.park(targetCar);
        assertNotNull(ticket);

        Car fetchedCar = parkingManager.fetch(ticket);

        assertSame(targetCar, fetchedCar);
    }

    @Test
    void should_a_car_be_parked_into_parking_boys_lot_when_park_a_car_by_a_managed_parking_boy_given_a_parking_boy_have_available_parking_position() {
        ParkingManager parkingManager = new ParkingManager(singletonList(new ParkingLot(0)));
        ParkingLot parkingLotOfBoy = new ParkingLot(1);
        StandardParkingBoy managedBoy = new StandardParkingBoy(singletonList(parkingLotOfBoy));
        parkingManager.addParkingBoy(managedBoy);

        ParkingTicket ticket = parkingManager.parkByBoy(managedBoy, new Car());

        assertNotNull(ticket);
        assertEquals(0, parkingLotOfBoy.getAvailableParkingPosition());
    }


    @Test
    void should_return_correspond_car_when_manger_ask_parking_boy_fetch_a_car_with_valid_ticket_given_a_car_parked_in_the_parking_lot_of_the_boy() {
        ParkingManager parkingManager = new ParkingManager(singletonList(new ParkingLot(0)));
        ParkingLot parkingLotOfBoy = new ParkingLot(1);
        StandardParkingBoy managedBoy = new StandardParkingBoy(singletonList(parkingLotOfBoy));
        parkingManager.addParkingBoy(managedBoy);
        Car targetCar = new Car();
        ParkingTicket ticket = parkingManager.parkByBoy(managedBoy, targetCar);

        Car fetchedCar = parkingManager.fetchByBoy(managedBoy, ticket);

        assertNotNull(ticket);
        assertEquals(0, parkingLotOfBoy.getAvailableParkingPosition());
        assertSame(targetCar, fetchedCar);
    }

    @Test
    void should_throws_CannotAssignTaskToParkingBoy_exception_when_park_a_car_by_nonmanaged_parking_boy() {
        ParkingManager parkingManager = new ParkingManager(singletonList(new ParkingLot(0)));
        ParkingLot parkingLotOfBoy = new ParkingLot(1);
        StandardParkingBoy nonmanagedBoy = new StandardParkingBoy(singletonList(parkingLotOfBoy));

        assertThrows(CannotAssignTaskToParkingBoy.class, () -> parkingManager.parkByBoy(nonmanagedBoy, new Car()));
    }

    @Test
    void should_throws_CannotAssignTaskToParkingBoy_exception_when_fetch_a_car_by_nonmanaged_parking_boy() {
        ParkingManager parkingManager = new ParkingManager(singletonList(new ParkingLot(0)));
        ParkingLot parkingLotOfBoy = new ParkingLot(1);
        ParkingTicket ticket = parkingLotOfBoy.park(new Car());
        StandardParkingBoy nonmanagedBoy = new StandardParkingBoy(singletonList(parkingLotOfBoy));

        assertThrows(CannotAssignTaskToParkingBoy.class, () -> parkingManager.fetchByBoy(nonmanagedBoy, ticket));
    }

    @Test
    void should_return_error_message_when_park_a_car_by_parking_boy_and_parking_boy_failed() {
        ParkingManager parkingManager = new ParkingManager(singletonList(new ParkingLot(0)));
        ParkingLot parkingLotOfBoy = new ParkingLot(1);
        parkingLotOfBoy.park(new Car());
        StandardParkingBoy managedBoy = new StandardParkingBoy(singletonList(parkingLotOfBoy));
        parkingManager.addParkingBoy(managedBoy);
        assertEquals(0, parkingLotOfBoy.getAvailableParkingPosition());
        assertNull(parkingManager.getLastErrorMessage());

        ParkingTicket ticket = parkingManager.parkByBoy(managedBoy, new Car());

        assertNull(ticket);
        assertEquals("The parking lot is full.", parkingManager.getLastErrorMessage());
    }

    @Test
    void should_return_error_message_when_fetch_a_car_by_parking_boy_and_parking_boy_failed() {
        ParkingManager parkingManager = new ParkingManager(singletonList(new ParkingLot(0)));

        ParkingLot parkingLotOfBoy = new ParkingLot(1);
        StandardParkingBoy managedBoy = new StandardParkingBoy(singletonList(parkingLotOfBoy));
        managedBoy.park(new Car());
        parkingManager.addParkingBoy(managedBoy);

        Car car = parkingManager.fetchByBoy(managedBoy, new ParkingTicket());

        assertNull(car);
        assertEquals("Unrecognized parking ticket.", parkingManager.getLastErrorMessage());
    }
}
