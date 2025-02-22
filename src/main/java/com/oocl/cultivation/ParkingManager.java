package com.oocl.cultivation;

import com.oocl.cultivation.exception.CannotAssignTaskToParkingBoy;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ParkingManager extends StandardParkingBoy {
    private List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
        parkingBoys = new ArrayList<>();
    }

    public void addParkingBoy(ParkingBoy... parkingBoys) {
        this.parkingBoys.addAll(asList(parkingBoys));
    }

    public ParkingTicket parkByBoy(StandardParkingBoy parkingBoy, Car car) {
        if (isManaged(parkingBoy)) {
            ParkingTicket ticket = parkingBoy.park(car);
            setLastErrorMessage(parkingBoy.getLastErrorMessage());
            return ticket;
        }

        throw new CannotAssignTaskToParkingBoy();
    }

    public Car fetchByBoy(StandardParkingBoy parkingBoy, ParkingTicket ticket) {
        if (isManaged(parkingBoy)) {
            Car car = parkingBoy.fetch(ticket);
            setLastErrorMessage(parkingBoy.getLastErrorMessage());
            return car;
        }

        throw new CannotAssignTaskToParkingBoy();
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    private boolean isManaged(StandardParkingBoy parkingBoy) {
        return parkingBoys.contains(parkingBoy);
    }
}
