package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.util.Objects.nonNull;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return capacity - cars.size();
    }

    public ParkingTicket park(Car car) throws RuntimeException {
        if (hasNoCapacity()) {
            throw new NoAvailableParkingPositionException();
        }

        ParkingTicket parkingTicket = new ParkingTicket();
        cars.put(parkingTicket, car);

        return parkingTicket;
    }

    private boolean hasNoCapacity() {
        return getAvailableParkingPosition() <= 0;
    }

    public Car fetch(ParkingTicket ticket) throws RuntimeException {
        try {
            Car car = cars.remove(ticket);
            if (nonNull(car)) {
                return car;
            }

            throw new NoSuchElementException();
        } catch (Exception ignored) {
            throw new NoSuchCarException();
        }
    }
}
