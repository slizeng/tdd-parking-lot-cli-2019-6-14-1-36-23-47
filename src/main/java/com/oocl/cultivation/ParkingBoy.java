package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoAvailableParkingPositionException;
import com.oocl.cultivation.exception.NoSuchCarException;

import static java.util.Objects.isNull;

public class ParkingBoy {

    private static final String ERROR_MESSAGE_UNRECOGNIZED_TICKET = "Unrecognized parking ticket.";
    private static final String ERROR_MESSAGE_FULL_PARKING_LOT = "The parking lot is full.";
    private static final String ERROR_MESSAGE_PROVIDE_TICKET = "Please provide your parking ticket.";
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
        if (isNull(ticket)) {
            lastErrorMessage = ERROR_MESSAGE_PROVIDE_TICKET;
            return null;
        }

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
