package gr.hua.dit.ds.fullstackvehicletransfer.service;

import gr.hua.dit.ds.fullstackvehicletransfer.entity.Transfer;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.User;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.Vehicle;
import gr.hua.dit.ds.fullstackvehicletransfer.repository.TransferRepository;
import gr.hua.dit.ds.fullstackvehicletransfer.repository.UserRepository;
import gr.hua.dit.ds.fullstackvehicletransfer.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransferRepository transferRepository;

    @Override
    public List<Vehicle> getUserVehicles(String username) {
        User user = userRepository.findByUsername(username);
        return user.getVehicles();
    }

    @Override
    public Vehicle findByLicensePlate(String licensePlate) {
        return vehicleRepository.findVehicleByLicensePlate(licensePlate);
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(String licensePlate) {
        Vehicle vehicle = vehicleRepository.findVehicleByLicensePlate(licensePlate);
        if (vehicle.getTransfer() != null) {
            Transfer transfer = vehicle.getTransfer();
            transfer.setVehicle(null);
            transfer.setTransferStatus("Cancelled");
            transferRepository.save(transfer);
        }
        vehicleRepository.delete(vehicle);
    }
}
