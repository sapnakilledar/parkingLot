package com.parking.exception;
/**
 * Exception class to handle parking full
 */
public class ParkingFullException extends Exception {
    public ParkingFullException(String message) {
        super(message);
    }
}
