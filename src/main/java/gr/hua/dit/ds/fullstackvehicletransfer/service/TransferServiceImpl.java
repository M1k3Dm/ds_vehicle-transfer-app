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
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService{

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Transfer> getTransfers() {
        return transferRepository.findAll();
    }

    @Override
    public void cancelTransfer(Long id) {
        Optional<Transfer> transfer = transferRepository.findById(id);
        transfer.get().setTransferStatus("Cancelled");
        Vehicle vehicle = vehicleRepository.findVehicleByLicensePlate(transfer.get().getVehicle().getLicensePlate());
        vehicle.setTransfer(null);
        transfer.get().setVehicle(null);

        transferRepository.save(transfer.get());
        vehicleRepository.save(vehicle);
    }

    @Override
    public void acceptTransfer(Long id) {
        Optional<Transfer> transfer = transferRepository.findById(id);
        transfer.get().setTransferStatus("Accepted");
        Vehicle vehicle = vehicleRepository.findVehicleByLicensePlate(transfer.get().getVehicle().getLicensePlate());
        User newOwner = userRepository.findByVat(transfer.get().getTransferTo());
        vehicle.setOwner(newOwner);
        transfer.get().setVehicle(null);
        vehicle.setTransfer(null);

        transferRepository.save(transfer.get());
        vehicleRepository.save(vehicle);
    }

    @Override
    public void rejectTransfer(Long id) {
        Optional<Transfer> transfer = transferRepository.findById(id);
        transfer.get().setTransferStatus("Rejected");
        Vehicle vehicle = vehicleRepository.findVehicleByLicensePlate(transfer.get().getVehicle().getLicensePlate());
        vehicle.setTransfer(null);
        transfer.get().setVehicle(null);

        transferRepository.save(transfer.get());
        vehicleRepository.save(vehicle);
    }

    @Override
    public void saveTransfer(Transfer transfer) {
        transferRepository.save(transfer);
    }
}
