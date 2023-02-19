package gr.hua.dit.ds.fullstackvehicletransfer.controller;

import gr.hua.dit.ds.fullstackvehicletransfer.config.CustomUserDetails;
import gr.hua.dit.ds.fullstackvehicletransfer.config.LoginForm;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.User;
import gr.hua.dit.ds.fullstackvehicletransfer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @GetMapping("/userlist")
    public String showUserList(Model model) {
        List<User> users = userService.getUsers();
        users.remove(0); // Remove admin from the list
        model.addAttribute("users", users);
        return "user_pages/list-users";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        User user = new User();
        return new ModelAndView("security/registration", "user", user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegister(@ModelAttribute("user") @Valid User user, BindingResult result) {
        //Check if the user exists by username, email, or vat
        User auser = userService.findByUsername(user.getUsername());
        User buser = userService.findByEmail(user.getEmail());
        User cuser = userService.findByVat(user.getVat());
        if (result.hasErrors() || auser != null || buser != null || cuser != null) {
            if (auser != null)
                result.rejectValue("username", "error.user", "Username already exists.");
            if (buser != null)
                result.rejectValue("email", "error.user", "Email already exists.");
            if (cuser != null)
                result.rejectValue("vat", "error.user", "Vat IN already exists.");
            return new ModelAndView("security/registration");
        }
        CustomUserDetails userDetails = new CustomUserDetails(user);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        jdbcUserDetailsManager.createUser(userDetails);

        //Find the new User and save email, firstname, lastname and vat to database
        User newUser = userService.findByUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setVat(user.getVat());
        userService.saveUser(newUser);

        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginForm() {
        LoginForm loginForm = new LoginForm();
        return new ModelAndView("security/login-form", "loginForm", loginForm);
    }

}
