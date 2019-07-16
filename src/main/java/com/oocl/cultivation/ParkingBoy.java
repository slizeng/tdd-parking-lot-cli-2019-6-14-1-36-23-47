package com.oocl.cultivation;

public class ParkingBoy {

    private static final String ERROR_MESSAGE_UNRECOGNIZED_TICKET = "Unrecognized parking ticket.";
    private static final String ERROR_MESSAGE_FULL_PARKING_LOT = "The parking lot is full.";
    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        try {
            ParkingTicket ticket = parkingLot.park(car);
            clearErrorMessage();
            return ticket;
        } catch (NoAvailableParkingPositionException ignored) {
            lastErrorMessage = ERROR_MESSAGE_FULL_PARKING_LOT;
            return null;
        }
    }

    public Car fetch(ParkingTicket ticket) {
        try {
            Car car = parkingLot.fetch(ticket);
            clearErrorMessage();
            return car;
        } catch (NoSuchCarException ignored) {
            lastErrorMessage = ERROR_MESSAGE_UNRECOGNIZED_TICKET;
            return null;
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    private void clearErrorMessage() {
        lastErrorMessage = null;
    }
}
