package gr.hua.dit.ds.fullstackvehicletransfer.restController;

import gr.hua.dit.ds.fullstackvehicletransfer.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleRestController {

    @Autowired
    private VehicleService vehicleService;

    @DeleteMapping("{licensePlate}")
    void deleteVehicle(@PathVariable String licensePlate) {
        vehicleService.deleteVehicle(licensePlate);
    }
}
