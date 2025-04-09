package com.technova.msvendor.entity;

import com.technova.user.Address;
import com.technova.user.PhoneNumber;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "vendor")
public class VendorEntity {
    private ObjectId id;
    
    private String companyName;
    
    private String companyType;
    
    private String companyRegistrationNumber;

    private String name;

    private String username;

    private String email;
    
    private String password;

    private String role;
    
    private Address address;
    
    private PhoneNumber phoneNumber;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public VendorEntity(ObjectId id, String companyName, String companyType, String companyRegistrationNumber, String name, String username, String email, String password, String role, Address address, PhoneNumber phoneNumber) {
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
}
