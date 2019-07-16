package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoSuchCarException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Objects.isNull;

public abstract class ParkingBoy {
    private static final String ERROR_MESSAGE_UNRECOGNIZED_TICKET = "Unrecognized parking ticket.";
    private static final String ERROR_MESSAGE_FULL_PARKING_LOT = "The parking lot is full.";
    private static final String ERROR_MESSAGE_PROVIDE_TICKET = "Please provide your parking ticket.";

    private final List<ParkingLot> parkingLots;
    private String lastErrorMessage;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return selectParkingLot()
                .map(parkingLot -> {
                    clearErrorMessage();
                    return parkingLot.park(car);
                })
                .orElseGet(() -> {
                    lastErrorMessage = ERROR_MESSAGE_FULL_PARKING_LOT;
                    return null;
                });
    }

    public Car fetch(ParkingTicket ticket) {
        if (isNull(ticket)) {
            lastErrorMessage = ERROR_MESSAGE_PROVIDE_TICKET;
            return null;
        }

        return parkingLots.stream()
                .map(fetchFromSingleParkingLot(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .map(car -> {
                    clearErrorMessage();
                    return car;
                })
                .orElseGet(() -> {
                    lastErrorMessage = ERROR_MESSAGE_UNRECOGNIZED_TICKET;
                    return null;
                });
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    protected abstract Optional<ParkingLot> selectParkingLot();

    void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

    List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    private Function<ParkingLot, Car> fetchFromSingleParkingLot(ParkingTicket ticket) {
        return parkingLot -> {
            try {
                return parkingLot.fetch(ticket);
            } catch (NoSuchCarException ignored) {
                return null;
            }
        };
    }

    private void clearErrorMessage() {
        lastErrorMessage = null;
    }
}
