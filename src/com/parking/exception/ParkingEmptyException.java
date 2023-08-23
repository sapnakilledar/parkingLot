package com.parking.exception;

/**
 * Exception class to handle empty parking
 */
public class ParkingEmptyException extends Exception {
    public ParkingEmptyException(String message) {
        super(message);
    }
}
