package gr.hua.dit.ds.fullstackvehicletransfer.repository;

import gr.hua.dit.ds.fullstackvehicletransfer.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Vehicle findVehicleByLicensePlate(String licensePlate);

}
