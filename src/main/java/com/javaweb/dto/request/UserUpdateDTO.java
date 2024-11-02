package com.javaweb.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.util.Gmail;
import com.javaweb.util.PhoneNumber;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class UserUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    @Gmail(message = "email avidate format")
    private String email;

    @PhoneNumber(message = "Phone Number avilidate format")
    private String phoneNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBrith;

    private List<AddressRequestDTO> address;

    private String userStatus;

    private String gender;

    private String username;

    private String password;

    private String type;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<AddressRequestDTO> getAddress() {
        return address;
    }

    public void setAddress(List<AddressRequestDTO> address) {
        this.address = address;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public @NotNull(message = "dateOfBrith must be not null") LocalDate getDateOfBrith() {
        return dateOfBrith;
    }

    public void setDateOfBrith(@NotNull(message = "dateOfBrith must be not null") LocalDate dateOfBrith) {
        this.dateOfBrith = dateOfBrith;
    }
}
