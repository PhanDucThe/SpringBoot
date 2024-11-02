package com.javaweb.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.util.Gmail;
import com.javaweb.util.PhoneNumber;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class UserRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "firstname don't not null")
    private String firstName;

    @NotNull(message = "lastname don't not null")
    private String lastName;

    @Gmail(message = "email alidate format")
    private String email;

    @PhoneNumber(message = "phonenumber alidate format")
    private String phoneNumber;

    @NotNull(message = "dateOfBrith must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBrith;

    @NotEmpty(message = "Collection don't not EMPTY")
    private List<AddressRequestDTO> address;

    private String userStatus;

    @NotNull(message = "gender don't not null")
    private String gender;

    @NotNull(message = "username don't not null")
    private String username;

    @NotNull(message = "password don't not null")
    private String password;

    @NotNull(message = "type don't not null")
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

    public LocalDate getDateOfBrith() {
        return dateOfBrith;
    }

    public void setDateOfBrith(LocalDate dateOfBrith) {
        this.dateOfBrith = dateOfBrith;
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
