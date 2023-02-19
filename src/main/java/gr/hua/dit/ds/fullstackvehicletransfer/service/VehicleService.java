package gr.hua.dit.ds.fullstackvehicletransfer.service;

import gr.hua.dit.ds.fullstackvehicletransfer.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getUserVehicles(String username);

    Vehicle findByLicensePlate(String licensePlate);

    void saveVehicle(Vehicle vehicle);

    void deleteVehicle(String licensePlate);
}
