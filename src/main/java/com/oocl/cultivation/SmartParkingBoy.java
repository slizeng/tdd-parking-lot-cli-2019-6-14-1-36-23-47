package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingInt;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Optional<ParkingLot> selectParkingLot() {
        return getParkingLots().stream()
                .max(comparingInt(ParkingLot::getAvailableParkingPosition));
    }
}
