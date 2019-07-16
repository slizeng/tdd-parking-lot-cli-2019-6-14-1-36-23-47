package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ParkingManager extends StandardParkingBoy{
    private List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
        parkingBoys = new ArrayList<>();
    }

    public void addParkingBoy(ParkingBoy... parkingBoys) {
        this.parkingBoys.addAll(asList(parkingBoys));
    }

    public ParkingTicket parkByBoy(StandardParkingBoy parkingBoy, Car car) {
        return parkingBoy.park(car);
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }
}
