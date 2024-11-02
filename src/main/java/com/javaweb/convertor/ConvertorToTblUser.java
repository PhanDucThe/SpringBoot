package com.javaweb.convertor;

import com.javaweb.dto.request.AddressRequestDTO;
import com.javaweb.dto.request.UserUpdateDTO;
import com.javaweb.model.tbl_user;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertorToTblUser {

    public tbl_user ToTblUser(UserUpdateDTO userUpdateDTO) {
        tbl_user tbl_user = new tbl_user();
        if (userUpdateDTO.getFirstName() != null) {
            tbl_user.setFirstName(userUpdateDTO.getFirstName());
        }
        if (userUpdateDTO.getLastName() != null) {
            tbl_user.setLastName(userUpdateDTO.getLastName());
        }
        if (userUpdateDTO.getEmail() != null) {
            tbl_user.setEmail(userUpdateDTO.getEmail());
        }
        if (userUpdateDTO.getPhoneNumber() != null) {
            tbl_user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        }
        if (userUpdateDTO.getDateOfBrith() != null) {
            tbl_user.setDateOfBrith(userUpdateDTO.getDateOfBrith());
        }
        if (userUpdateDTO.getUserStatus() != null) {
            tbl_user.setUserStatus(userUpdateDTO.getUserStatus());
        }
        if (userUpdateDTO.getGender() != null) {
            tbl_user.setGender(userUpdateDTO.getGender());
        }
        if (userUpdateDTO.getUsername() != null) {
            tbl_user.setUsername(userUpdateDTO.getUsername());
        }
        if (userUpdateDTO.getPassword() != null) {
            tbl_user.setPassword(userUpdateDTO.getPassword());
        }
        if (userUpdateDTO.getType() != null) {
            tbl_user.setType(userUpdateDTO.getType());
        }
        if (userUpdateDTO.getAddress() != null) {
            AddressRequestDTO address = new AddressRequestDTO();
            List<AddressRequestDTO> addressList = new ArrayList<>();
            if (userUpdateDTO.getAddress().get(0).getApartmentNumber() != null) {
                address.setApartmentNumber(userUpdateDTO.getAddress().get(0).getApartmentNumber());
            }
            if (userUpdateDTO.getAddress().get(0).getFloor() != null) {
                address.setFloor(userUpdateDTO.getAddress().get(0).getFloor());
            }
            if (userUpdateDTO.getAddress().get(0).getBuilding() != null) {
                address.setBuilding(userUpdateDTO.getAddress().get(0).getBuilding());
            }
            if (userUpdateDTO.getAddress().get(0).getStreetNumber() != null) {
                address.setStreetNumber(userUpdateDTO.getAddress().get(0).getStreetNumber());
            }
            if (userUpdateDTO.getAddress().get(0).getStreet() != null) {
                address.setStreet(userUpdateDTO.getAddress().get(0).getStreet());
            }
            if (userUpdateDTO.getAddress().get(0).getCity() != null) {
                address.setCity(userUpdateDTO.getAddress().get(0).getCity());
            }
            if (userUpdateDTO.getAddress().get(0).getCountry() != null) {
                address.setCountry(userUpdateDTO.getAddress().get(0).getCountry());
            }
            if (userUpdateDTO.getAddress().get(0).getAddressType() != null) {
                address.setAddressType(userUpdateDTO.getAddress().get(0).getAddressType());
            }
            addressList.add(address);
            userUpdateDTO.setAddress(addressList);
        }
        return tbl_user;
    }
}
