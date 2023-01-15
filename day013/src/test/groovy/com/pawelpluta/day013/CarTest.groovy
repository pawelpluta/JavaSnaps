package com.pawelpluta.day013

import spock.lang.Specification

import static com.pawelpluta.day013.FuelType.DIESEL
import static com.pawelpluta.day013.FuelType.PETROL

class CarTest extends Specification {

    Integer NO_PASSENGERS = 0
    Integer FOUR_SEATS = 4
    Integer SOME_MILEAGE = 123456
    Integer TEN_LITERS = 10
    Integer FIFTY_LITERS = 50

    def "car should inform about its mileage in kilometers"() {
        given:
            Integer mileage = 10000
            Car car = new Car(NO_PASSENGERS, FOUR_SEATS, mileage, TEN_LITERS, FIFTY_LITERS, PETROL)

        expect:
            car.mileageInKm() == mileage
    }

    def "car should inform about its mileage in miles"() {
        given:
            Integer mileage = 10000
            Car car = new Car(NO_PASSENGERS, FOUR_SEATS, mileage, TEN_LITERS, FIFTY_LITERS, PETROL)

        expect:
            car.mileageInMiles() == 6213
    }

    def "car can fit as much passengers as it has seats"() {
        given:
            Integer seatsCount = 4
            Car car = new Car(NO_PASSENGERS, seatsCount, SOME_MILEAGE, TEN_LITERS, FIFTY_LITERS, PETROL)
        and: "take all seats"
            (1..seatsCount).each { car = car.addPassenger() }

        when:
            car = car.addPassenger()

        then:
            car.passengers() == seatsCount
    }

    def "cannot refuel with unsupported fuel type"() {
        given:
            Car car = new Car(NO_PASSENGERS, FOUR_SEATS, SOME_MILEAGE, TEN_LITERS, FIFTY_LITERS, PETROL)

        when:
            car = car.refuel(DIESEL, 20)

        then:
            car.fuelInTank() == TEN_LITERS
    }

    def "cannot refuel too much fuel"() {
        given:
            Car car = new Car(NO_PASSENGERS, FOUR_SEATS, SOME_MILEAGE, TEN_LITERS, FIFTY_LITERS, PETROL)

        when:
            car = car.refuel(PETROL, 41)

        then:
            car.fuelInTank() == TEN_LITERS
    }

    def "can refuel if fuel will fit in tank"() {
        given:
            Car car = new Car(NO_PASSENGERS, FOUR_SEATS, SOME_MILEAGE, TEN_LITERS, FIFTY_LITERS, PETROL)

        when:
            car = car.refuel(PETROL, 20)

        then:
            car.fuelInTank() == 30
    }

}
