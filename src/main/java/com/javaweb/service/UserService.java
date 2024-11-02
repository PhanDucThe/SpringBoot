package com.javaweb.service;

import com.javaweb.dto.request.UserRequestDTO;
import com.javaweb.dto.request.UserUpdateDTO;
import com.javaweb.dto.response.PageResponse;
import com.javaweb.dto.response.UserDetailResponse;


public interface UserService {
    Long saveUser(UserRequestDTO userRequestDTO);

    void updateUser(Long id , UserUpdateDTO userUpdateDTO);

    void deleteUser(Long userId);

    UserDetailResponse getUserById(Long userId);

    PageResponse<?> getAllUsers(int pageNo, int pageSize, String sortField);

    PageResponse<?> getAllUserWithSortAnhSearchService(int pageNo, int pageSize, String search, String sortField);
}
