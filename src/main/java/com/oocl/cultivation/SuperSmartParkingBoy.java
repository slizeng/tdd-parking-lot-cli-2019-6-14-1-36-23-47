package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Optional<ParkingLot> selectParkingLot() {
        return getParkingLots().stream()
                .max(Comparator.comparingDouble(parkingLot ->
                        (double) parkingLot.getAvailableParkingPosition() / parkingLot.getCapacity()
                ));
    }
}
