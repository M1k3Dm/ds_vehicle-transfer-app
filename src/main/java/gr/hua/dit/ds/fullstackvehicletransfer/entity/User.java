package gr.hua.dit.ds.fullstackvehicletransfer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Size(min = 4, max = 20, message = "Username should be greater than 4 characters.")
    @Column
    private String username;

    @Size(min = 5, message = "Password should be greater than 5 characters.")
    @Column
    private String password;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Please enter a valid email address.")
    @Column(name = "email", unique = true)
    @Size(max = 40, message = "Email should not be greater than 40 characters.")
    private String email;

    @NotBlank(message="Please enter your first name.")
    @Size(max = 20, message = "First name should not be greater than 20 characters.")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message="Please enter your last name.")
    @Size(max = 20, message = "Last name should not be greater than 20 characters.")
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 9,max = 9, message = "VAT identification number should be 9 digits.")
    @Column(name = "vat", unique = true)
    private String vat;

    @Column
    private Boolean enabled;

    @OneToMany(mappedBy = "owner", cascade= {CascadeType.ALL})
    @JsonManagedReference
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "owner", cascade= {CascadeType.ALL})
    @JsonManagedReference
    private List<Transfer> transfers;

    public User() {}

    public User(String username, String password, String email, String firstName, String lastName, String vat) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.vat = vat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }
}