package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

public class StandardParkingBoy extends ParkingBoy {
    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Optional<ParkingLot> selectParkingLot() {
        return getParkingLots().stream()
                .filter(parkingLot -> parkingLot.getAvailableParkingPosition() > 0)
                .findFirst();
    }
}
