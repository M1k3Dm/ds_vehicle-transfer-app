package gr.hua.dit.ds.fullstackvehicletransfer.service;

public interface EmailService {

    void sendEmail(Long id, String subject, String body);

}
