package com.parking.car;



import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.parking.exception.ParkingEmptyException;
import com.parking.exception.ParkingFullException;
import com.parking.fare.ParkingChargeStrategy;

/**
 * Concrete class to implement car park/unpark logic
 */
public class ParkingLot implements Parking {

    BlockingQueue<Vehicle> parkingSlots;
    private int parkingCapacity;

    public ParkingLot(int parkingCapacity){
        this.parkingCapacity = parkingCapacity;
        parkingSlots = new ArrayBlockingQueue<>(parkingCapacity);
    }

    public int getAvailableParkingLot(){
        return parkingCapacity - parkingSlots.size();
    }

    public int getNumberOfCarParked(){
        return parkingSlots.size();
    }


    /**
     * Car parking implementation
     * @param vehicle
     * @return Vehicle object
     * @throws ParkingFullException
     */
    @Override
    public Vehicle park(Vehicle vehicle) throws ParkingFullException {
        if (getAvailableParkingLot()<=0) {
            throw new ParkingFullException("Parking is full.. please wait for available space!");
        }
        parkingSlots.add(vehicle);
        
        if(vehicle instanceof Car) {
        	Car car = (Car)vehicle;
        }
        
        return vehicle;
    }

    /**
     * Car unparking implementation
     * @param vehicle object
     * @param parkingCostStrategy
     * @return Vehicle object
     * @throws ParkingEmptyException
     */
    @Override
    public Vehicle unPark(Vehicle vehicle, ParkingChargeStrategy parkingCostStrategy) throws ParkingEmptyException {
        Car car = null;
        if (getNumberOfCarParked() == 0) {
            throw new ParkingEmptyException("Parking is empty and nothing to be un-parked");
        }
        if (vehicle instanceof Car) {
            car = (Car) vehicle;
            car.setCarOutTime(LocalDateTime.now());
            car.setParkingFare(parkingCostStrategy.calculateParkingCharge((LocalDateTime.now().getHour() - vehicle.getVehicleInTime().getHour())));
        }
        parkingSlots.remove(vehicle);
        return car;
    }

}
