package com.parking.car;

import com.parking.exception.ParkingEmptyException;
import com.parking.exception.ParkingFullException;
import com.parking.fare.ParkingChargeStrategy;

/**
 * Generic interface to handle park/unpark feature
 */
public interface Parking {
    public Vehicle park(Vehicle vehicle) throws ParkingFullException;


    public Vehicle unPark(Vehicle vehicle, ParkingChargeStrategy parkingCostStrategy) throws ParkingEmptyException;

}
