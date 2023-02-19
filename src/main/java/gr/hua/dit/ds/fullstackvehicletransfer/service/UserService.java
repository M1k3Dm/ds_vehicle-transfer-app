package gr.hua.dit.ds.fullstackvehicletransfer.service;

import gr.hua.dit.ds.fullstackvehicletransfer.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User findByUsername(String username);

    User findByVat(String vat);

    User findByEmail(String email);

    void saveUser(User user);
}
