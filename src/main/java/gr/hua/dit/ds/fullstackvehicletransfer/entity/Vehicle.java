package gr.hua.dit.ds.fullstackvehicletransfer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @NotBlank(message = "Please enter the license plate of the vehicle.")
    @Size(max = 9, message = "Please enter a valid size (max 9 characters).")
    @Column(name = "license_plate")
    private String licensePlate;

    @NotBlank(message = "Please enter the model of the vehicle.")
    @Size(max = 10, message = "Please enter a valid size (max 10 characters).")
    @Column(name = "model")
    private String model;

    @NotBlank(message = "Please enter the brand of the vehicle.")
    @Size(max = 10, message = "Please enter a valid size (max 10 characters).")
    @Column(name = "brand")
    private String brand;

    @NotBlank(message = "Please enter the type of the vehicle.")
    @Size(max = 10, message = "Please enter a valid size (max 10 characters).")
    @Column(name = "type")
    private String type;

    @Column(name = "mot")
    private boolean mot;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "owner")
    @JsonBackReference
    private User owner;

    @OneToOne(mappedBy = "vehicle", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private Transfer transfer;

    public Vehicle() {}

    public Vehicle(String licensePlate, String model, String brand, String type, boolean mot, User owner) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.type = type;
        this.mot = mot;
        this.owner = owner;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMot() {
        return mot;
    }

    public void setMot(boolean mot) {
        this.mot = mot;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }
}
