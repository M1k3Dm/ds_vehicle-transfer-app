package gr.hua.dit.ds.fullstackvehicletransfer.controller;

import gr.hua.dit.ds.fullstackvehicletransfer.entity.User;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.Vehicle;
import gr.hua.dit.ds.fullstackvehicletransfer.service.UserService;
import gr.hua.dit.ds.fullstackvehicletransfer.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    UserService userService;

    @GetMapping("/vehiclelist")
    public String showVehicleList(Model model, Principal principal) {
        model.addAttribute("vehicles", vehicleService.getUserVehicles(principal.getName()));
        return "vehicle_pages/list-vehicles";
    }

    @GetMapping("/vehicleform")
    public String showVehicleForm(Model model) {
        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
        return "vehicle_pages/create-vehicle";
    }

    @PostMapping("/vehicleform")
    public String createVehicle(@ModelAttribute("vehicle") @Valid Vehicle vehicle, BindingResult result, Principal principal) {
        //Check if the vehicle exists by licensePlate
        Vehicle avehicle = vehicleService.findByLicensePlate(vehicle.getLicensePlate());
        if (result.hasErrors() || avehicle != null) {
            if (avehicle != null)
                result.rejectValue("licensePlate", "error.vehicle", "A vehicle with this license plate already exists.");
            return "vehicle_pages/create-vehicle";
        }
        User owner = userService.findByUsername(principal.getName());
        vehicle.setOwner(owner);
        vehicleService.saveVehicle(vehicle);
        return "redirect:/vehiclelist";
    }

}
