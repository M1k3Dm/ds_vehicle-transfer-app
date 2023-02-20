package gr.hua.dit.ds.fullstackvehicletransfer.restController;

import gr.hua.dit.ds.fullstackvehicletransfer.config.CustomUserDetails;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.Transfer;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.User;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.Vehicle;
import gr.hua.dit.ds.fullstackvehicletransfer.service.TransferService;
import gr.hua.dit.ds.fullstackvehicletransfer.service.UserService;
import gr.hua.dit.ds.fullstackvehicletransfer.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TransferService transferService;

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        for (Vehicle v : user.getVehicles()) {
            v.setOwner(null);
            vehicleService.saveVehicle(v);
        }
        for (Transfer t : user.getTransfers()) {
            t.setOwner(null);
            transferService.saveTransfer(t);
        }
        jdbcUserDetailsManager.deleteUser(username);
    }

    @PostMapping("{username}/activate")
    public void activateAccount(@PathVariable String username) {
        User user = userService.findByUsername(username);
        user.setEnabled(true);
        CustomUserDetails userDetails = new CustomUserDetails(user);
        jdbcUserDetailsManager.updateUser(userDetails);
    }

    @PostMapping("{username}/deactivate")
    public void deactivateAccount(@PathVariable String username) {
        User user = userService.findByUsername(username);
        user.setEnabled(false);
        userService.saveUser(user);
    }
}
