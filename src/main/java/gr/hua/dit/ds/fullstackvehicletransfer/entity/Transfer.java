package gr.hua.dit.ds.fullstackvehicletransfer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "transfer_date_time")
    private String transferDateTime;

    @Column(name = "region")
    private String region;

    @Column(name = "tranfser_to")
    @Size(min = 9, max = 9, message = "VAT identification number should be 9 digits")
    private String transferTo;

    @Column(name = "status")
    private String transferStatus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "owner")
    @JsonBackReference
    private User owner;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "vehicle_license_plate")
    private Vehicle vehicle;

    public Transfer() {}

    public Transfer(String region, String transferTo, Vehicle vehicle) {
        this.region = region;
        this.transferTo = transferTo;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransferDateTime() {
        return transferDateTime;
    }

    public void setTransferDateTime(String transferDateTime) {
        this.transferDateTime = transferDateTime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTransferTo() {
        return transferTo;
    }

    public void setTransferTo(String transferTo) {
        this.transferTo = transferTo;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
