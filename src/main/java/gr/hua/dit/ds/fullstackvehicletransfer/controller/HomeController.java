package gr.hua.dit.ds.fullstackvehicletransfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {return "home_page/index";}

    // No-one can access the restController urls from frontend
    @GetMapping("/users")
    public String errorUsers() {
        return "error/405";
    }

    @GetMapping("/vehicles")
    public String errorVehicles() {
        return "error/405";
    }

    @GetMapping("/transfers")
    public String errorTransfers() {
        return "error/405";
    }
}
