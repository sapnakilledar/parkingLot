package com.parking.client;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.parking.car.Car;
import com.parking.car.Parking;
import com.parking.car.ParkingLot;
import com.parking.car.Vehicle;
import com.parking.exception.ParkingEmptyException;
import com.parking.exception.ParkingFullException;
import com.parking.fare.CarParkingChargeStrategy;
import com.parking.util.ParkingUtility;

/**
 * Parking Client class to test/run solution
 */
public class ParkingClient {
    public static void main(String[] args) {
    	List<Vehicle> tasks = new ArrayList<>();
    	Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Parking parking = new ParkingLot(ParkingUtility.PARKING_CAPACITY);
        for(int i=0; i<ParkingUtility.PARKING_CAPACITY_EXCEED;i++){
            tasks.add(new Car(""+i, LocalDateTime.now().minusHours(random.nextInt(5))));
        }

        //Submit park task using completable future
        tasks.stream().map(task -> CompletableFuture.runAsync(()-> {
            try {
                Car parked = (Car) parking.park(task);
                System.out.println("Car " + parked.getVehicleNumber() + " parked at " +parked.getVehicleInTime());
            } catch (ParkingFullException ex) {
                System.out.println("Parking full exception :: " + ex.getMessage());
            }
        }, executorService)).collect(Collectors.toList());

        //Submit unpark task using completable future
        tasks.stream().map(task -> CompletableFuture.runAsync(()-> {
            try {
                Car unparked =(Car) parking.unPark(task, new CarParkingChargeStrategy());
                int parkedHour = unparked.getCarOutTime().getHour() - unparked.getVehicleInTime().getHour();
                if(parkedHour ==0){
                    System.out.println("Car "+ unparked.getVehicleNumber() + " parked for less than an hour and total fare charged :: "+"£"+unparked.getParkingFare());
                }else{
                    System.out.println("Car "+ unparked.getVehicleNumber() + " parked for "+ parkedHour +" hour and total fare charged :: "+"£"+unparked.getParkingFare());
                }

            }  catch (ParkingEmptyException ex){
                System.out.println("Parking empty exception :: " + ex.getMessage());
            }
        }, executorService)).collect(Collectors.toList());

        executorService.shutdown();
    }
}
