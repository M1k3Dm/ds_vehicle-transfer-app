package gr.hua.dit.ds.fullstackvehicletransfer.controller;

import gr.hua.dit.ds.fullstackvehicletransfer.entity.Transfer;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.User;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.Vehicle;
import gr.hua.dit.ds.fullstackvehicletransfer.service.TransferService;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class TransferController {

    @Autowired
    TransferService transferService;

    @Autowired
    UserService userService;

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/transferlist")
    public String showTransferList(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Transfer> transferFromUser = new ArrayList<>();
        List<Transfer> transfersForUser = new ArrayList<>();
        for (Transfer tr : transferService.getTransfers()) {
            if (tr.getTransferStatus().equals("Pending")) {
                if (tr.getOwner() == null) {
                    continue;
                }
                if (tr.getTransferTo().equals(user.getVat()))
                    transfersForUser.add(tr);
                if (tr.getOwner().equals(user))
                    transferFromUser.add(tr);
            }
        }
        model.addAttribute("transfersForUser", transfersForUser);
        model.addAttribute("transfersFromUser", transferFromUser);
        return "transfer_pages/list-transfers";
    }

    @GetMapping("/transferhistory")
    public String showTransferHistory(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Transfer> transfers = new ArrayList<>();
        for (Transfer tr : transferService.getTransfers()) {
            if (user.equals(tr.getOwner()) || user.getVat().equals(tr.getTransferTo())) {
                if (!tr.getTransferStatus().equals("Pending"))
                    transfers.add(tr);
            }
        }
        Collections.reverse(transfers);
        model.addAttribute("transfers", transfers);
        return "transfer_pages/transfer-history";
    }

    @GetMapping("/transferform")
    public String showTransferForm(Model model, Principal principal) {
        Transfer transfer = new Transfer();
        List<Vehicle> vehicles = vehicleService.getUserVehicles(principal.getName());
        model.addAttribute("transfer", transfer);
        model.addAttribute("vehicles", vehicles);
        return "transfer_pages/create-transfer";
    }

    @PostMapping("/transferform")
    public String createTransfer(@ModelAttribute("transfer") @Valid Transfer transfer, BindingResult result, Principal principal, Model model) {
        //Check transfer request
        User owner = userService.findByUsername(principal.getName());
        User buyer = userService.findByVat(transfer.getTransferTo());
        Vehicle vehicle;
        if (transfer.getVehicle() == null) {
            result.rejectValue("vehicle", "error.transfer", "Please choose a vehicle.");
            vehicle = null;
        } else {
            vehicle = vehicleService.findByLicensePlate(transfer.getVehicle().getLicensePlate());
        }
        if (vehicle != null) {
           if (!vehicle.isMot())
               result.rejectValue("vehicle", "error.transfer", "Sorry, the vehicle cannot be transferred due to the MOT being false.");
           if (vehicle.getTransfer() != null)
               result.rejectValue("vehicle", "error.transfer", "Pending transfer in this vehicle.");
        }
        if (buyer != null && buyer.getVat().equals(owner.getVat()))
            result.rejectValue("transferTo", "error.transfer", "Sorry, you can't make a transfer to yourself.");
        if (buyer == null && transfer.getTransferTo().length() == 9)
            result.rejectValue("transferTo", "error.transfer", "Sorry, user does not exist in our database.");
        if (result.hasErrors()) {
            //Show transfer form again with the vehicle list
            Transfer atransfer = new Transfer();
            List<Vehicle> vehicles = vehicleService.getUserVehicles(principal.getName());
            model.addAttribute("atransfer", atransfer);
            model.addAttribute("vehicles", vehicles);
            return "transfer_pages/create-transfer";
        }
        transfer.setOwner(owner);
        transfer.setTransferStatus("Pending");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);
        transfer.setTransferDateTime(formattedDateTime);

        transferService.saveTransfer(transfer);
        return "redirect:/transferlist";
    }
}
