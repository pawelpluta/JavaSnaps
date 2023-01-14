package com.pawelpluta.day013

import spock.lang.Specification

import static com.pawelpluta.day013.FuelType.DIESEL
import static com.pawelpluta.day013.FuelType.PETROL

class CarTest extends Specification {

    Integer NO_PASSENGERS = 0
    Integer FOUR_SEATS = 4
    Integer TEN_LITERS = 10
    Integer FIFTY_LITERS = 50

    def "car should inform about its mileage in kilometers"() {
        given:
            Integer mileage = 10000
            Car car = new Car(NO_PASSENGERS, FOUR_SEATS, mileage, TEN_LITERS, FIFTY_LITERS, PETROL)

        expect:
            car.mileageInKm() == mileage
    }

}
