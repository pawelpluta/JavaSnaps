package com.pawelpluta.day013;

record Car(Integer passengers, Integer seats, Integer mileageInKm, Integer fuelInTank, Integer tankCapacity, FuelType fuelType) {
    private static final Double KILOMETER_AS_MILE = 0.621371192;
    Car addPassenger() {
        if (passengers < seats) {
            return new Car(passengers + 1, seats, mileageInKm, fuelInTank, tankCapacity, fuelType);
        }
        return this;
    }

    Integer mileageInMiles() {
        return (int) (KILOMETER_AS_MILE * mileageInKm);
    }

    Car refuel(FuelType type, Integer volume) {
        if (unsupportedFuelType(type)) {
            return this;
        } else if (willOverflowTank(volume)) {
            return this;
        }
        return new Car(passengers, seats, mileageInKm, fuelInTank + volume, tankCapacity, fuelType);
    }

    private boolean unsupportedFuelType(FuelType type) {
        return type != fuelType;
    }

    private boolean willOverflowTank(Integer volume) {
        return fuelInTank + volume > tankCapacity;
    }

}
