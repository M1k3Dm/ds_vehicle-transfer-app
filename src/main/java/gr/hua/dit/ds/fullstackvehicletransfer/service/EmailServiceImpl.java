package gr.hua.dit.ds.fullstackvehicletransfer.service;

import gr.hua.dit.ds.fullstackvehicletransfer.entity.Transfer;
import gr.hua.dit.ds.fullstackvehicletransfer.entity.User;
import gr.hua.dit.ds.fullstackvehicletransfer.repository.TransferRepository;
import gr.hua.dit.ds.fullstackvehicletransfer.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private final JavaMailSender mailSender;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private UserRepository userRepository;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Long id,String subject, String body){
        Optional<Transfer> transfer = transferRepository.findById(id);
        User owner = userRepository.findByVat(transfer.get().getTransferTo());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(owner.getEmail());
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
