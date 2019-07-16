package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class SuperSmartParkingBoyTest {
    @Test
    void should_a_car_be_parked_into_the_parking_lot_with_highest_available_position_rate_when_super_smart_parking_boy_park_a_car() {
        ParkingLot parkingLotWithLowerRate = buildParkingLotWithTwoEmptyPositionsAndThreeAvailablePositions();
        ParkingLot parkingLotWithHigherRate = buildParkingLotWithOneEmptyPositionAndOneAvailablePosition();
        SuperSmartParkingBoy superSmartParkingBoy =
                new SuperSmartParkingBoy(asList(parkingLotWithLowerRate, parkingLotWithHigherRate));
        int availablePositionsOfLowerRateLot = parkingLotWithLowerRate.getAvailableParkingPosition();
        int availablePositionsOfHigherRateLot = parkingLotWithHigherRate.getAvailableParkingPosition();
        assertTrue(availablePositionsOfHigherRateLot < availablePositionsOfLowerRateLot);

        ParkingTicket ticket = superSmartParkingBoy.park(new Car());

        assertNotNull(ticket);
        assertEquals(availablePositionsOfHigherRateLot - 1, parkingLotWithHigherRate.getAvailableParkingPosition());
        assertEquals(availablePositionsOfLowerRateLot, parkingLotWithLowerRate.getAvailableParkingPosition());
    }

    private ParkingLot buildParkingLotWithOneEmptyPositionAndOneAvailablePosition() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());

        return parkingLot;
    }

    private ParkingLot buildParkingLotWithTwoEmptyPositionsAndThreeAvailablePositions() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());

        return parkingLot;
    }
}
