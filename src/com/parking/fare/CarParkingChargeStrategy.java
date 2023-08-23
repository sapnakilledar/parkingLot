package com.parking.fare;

import com.parking.util.ParkingUtility;

/**
 * Class used for calculating car parking fare
 */
public class CarParkingChargeStrategy implements ParkingChargeStrategy{

    /**
     * This method used to calculate fare required for parking
     * @param parkHours
     * @return parking fare
     */
    @Override
    public double calculateParkingCharge(double parkHours) {
        if (parkHours < 1) {
            return ParkingUtility.PARKING_CHARGE;
        }
        return parkHours * ParkingUtility.PARKING_CHARGE;
    }
}
