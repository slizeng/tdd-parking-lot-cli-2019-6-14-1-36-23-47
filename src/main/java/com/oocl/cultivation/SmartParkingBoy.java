package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {

        return getParkingLots().stream()
                .max(Comparator.comparingInt(ParkingLot::getAvailableParkingPosition))
                .map(parkingLot -> parkingLot.park(car))
                .orElse(null);
    }
}
