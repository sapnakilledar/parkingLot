package com.parking.car;

import java.time.LocalDateTime;

/**
 * Car class extending vehicle interface
 */
public class Car implements Vehicle {
    private String carNumber;
    private LocalDateTime carInTime;
    private LocalDateTime carOutTime;
    private double parkingFare;
  

	public Car(String carNumber, LocalDateTime carInTime){
        this.carNumber = carNumber;
        this.carInTime = carInTime;
    }

    @Override
    public String getVehicleNumber() {
        return carNumber;
    }

    @Override
    public LocalDateTime getVehicleInTime() {
        return carInTime;
    }
    
    public LocalDateTime getCarOutTime() {
        return carOutTime;
    }

    public void setCarOutTime(LocalDateTime carOutTime) {
        this.carOutTime = carOutTime;
    }

    public double getParkingFare() {
        return parkingFare;
    }

    public void setParkingFare(double parkingFare) {
        this.parkingFare = parkingFare;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNumber='" + carNumber + '\'' +
                ", carInTime=" + carInTime +
                ", carOutTime=" + carOutTime +
                ", parkingFare=" + parkingFare +
                '}';
    }


}
