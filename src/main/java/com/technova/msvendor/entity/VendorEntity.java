package com.technova.msvendor.entity;

import com.technova.user.dto.Address;
import com.technova.user.dto.PhoneNumber;
import com.technova.vendor.enums.VendorStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String companyName;
    
    private String companyType;

    @Column(unique = true, nullable = false)
    private String companyRegistrationNumber;

    private String name;

    @Enumerated(EnumType.STRING)
    private VendorStatus status = VendorStatus.ACTIVE;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;
    
    private String password;

    private String role;

    @Embedded
    @Column(unique = true, nullable = false)
    private Address address;

    @Column(unique = true, nullable = false)
    @Embedded
    private PhoneNumber phoneNumber;

    @ElementCollection
    private List<String> productIds = List.of();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyRegistrationNumber() {
        return companyRegistrationNumber;
    }

    public void setCompanyRegistrationNumber(String companyRegistrationNumber) {
        this.companyRegistrationNumber = companyRegistrationNumber;
    }

    public VendorStatus getStatus() {
        return status;
    }

    public void setStatus(VendorStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public VendorEntity() {
    }

    public VendorEntity(String id, String companyName, String companyType, String companyRegistrationNumber, String name, String username, String email, String password, String role, Address address, PhoneNumber phoneNumber) {
        this.id = id;
        this.companyName = companyName;
        this.companyType = companyType;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public VendorEntity(String companyName, String companyType, String companyRegistrationNumber, String name, String username, String email, String password, String role, Address address, PhoneNumber phoneNumber) {
        this.companyName = companyName;
        this.companyType = companyType;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }
}
