package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket ticket) {
        try {
            return parkingLot.fetch(ticket);
        } catch (NoSuchCarException ignored) {
            return null;
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
