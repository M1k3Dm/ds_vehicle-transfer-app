package gr.hua.dit.ds.fullstackvehicletransfer.restController;

import gr.hua.dit.ds.fullstackvehicletransfer.config.CustomUserDetails;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.User;
import gr.hua.dit.ds.fullstackvehicletransfer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
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
