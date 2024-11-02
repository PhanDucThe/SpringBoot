package com.javaweb.controller;

import com.javaweb.configuration.Translator;
import com.javaweb.dto.request.UserRequestDTO;
import com.javaweb.dto.request.UserUpdateDTO;
import com.javaweb.dto.response.ResponseData;

import com.javaweb.dto.response.UserDetailResponse;
import com.javaweb.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/")
    public ResponseData<Long> AddUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {

        Long id = userService.saveUser(userRequestDTO);

        return new ResponseData<>(HttpStatus.CREATED.value(), "created user successfully", id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseData<?> DeleteUser(@Min(1) @PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "deleted user successfully");
    }

    @GetMapping(value = "/{id}")
    public ResponseData<UserDetailResponse> getUserById(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "get user successfully", userService.getUserById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseData<?> UpdateUser(@Min(1) @PathVariable Long id ,@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.updateUser(id, userUpdateDTO);
        return new ResponseData<>(HttpStatus.CREATED.value(), "update user successfully");
    }

    @GetMapping(value = "/list/")
    public ResponseData<?> getAllUser(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                                             @RequestParam(defaultValue = "20",required = false) int pageSize,
                                                             @RequestParam(required = false) String sortField) {
        return new ResponseData<>(HttpStatus.OK.value(), "user", userService.getAllUsers(pageNo, pageSize, sortField));
    }

    @GetMapping(value = "/list-with-sort-by-one-column-search")
    public ResponseData<?> getAllUserWithSortAndSearch(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                                       @RequestParam(defaultValue = "20", required = false) int pageSize,
                                                       @RequestParam(defaultValue = "", required = false) String search,
                                                       @RequestParam(required = false) String sortField) {
        return new ResponseData<>(HttpStatus.OK.value(), "user", userService.getAllUserWithSortAnhSearchService(pageNo, pageSize, search, sortField));
    }
}
