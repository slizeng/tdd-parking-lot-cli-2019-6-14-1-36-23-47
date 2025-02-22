package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingDouble;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Optional<ParkingLot> selectParkingLot() {
        return getParkingLots().stream()
                .max(comparingDouble(parkingLot ->
                        (double) parkingLot.getAvailableParkingPosition() / parkingLot.getCapacity()
                ));
    }
}
