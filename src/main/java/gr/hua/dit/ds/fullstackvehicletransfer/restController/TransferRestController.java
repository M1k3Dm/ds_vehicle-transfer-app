package gr.hua.dit.ds.fullstackvehicletransfer.restController;

import gr.hua.dit.ds.fullstackvehicletransfer.service.EmailService;
import gr.hua.dit.ds.fullstackvehicletransfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
public class TransferRestController {

    @Autowired
    TransferService transferService;
    @Autowired
    EmailService emailService;

    @PostMapping("{id}/cancel")
    public void cancelTransfer(@PathVariable Long id) {
        transferService.cancelTransfer(id);
    }

    @PostMapping("{id}/accept")
    public void acceptTransfer(@PathVariable Long id) {
        transferService.acceptTransfer(id);
        emailService.sendEmail(id,
                "Vehicle Transfer Notification", "There is a pending vehicle transfer on your name");
    }

    @PostMapping("{id}/reject")
    public void rejectTransfer(@PathVariable Long id) {
        transferService.rejectTransfer(id);
    }
}
