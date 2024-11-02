package com.javaweb.service.impl;

import com.javaweb.convertor.ConvertorToTblUser;
import com.javaweb.dto.request.UserRequestDTO;
import com.javaweb.dto.request.UserUpdateDTO;
import com.javaweb.dto.response.PageResponse;
import com.javaweb.dto.response.UserDetailResponse;
import com.javaweb.model.tbl_address;
import com.javaweb.model.tbl_user;
import com.javaweb.repository.AddressRepository;
import com.javaweb.repository.SearchRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConvertorToTblUser convertorToTblUser;

    @Autowired
    private SearchRepository searchRepository;

    @Transactional
    @Override
    public Long saveUser(UserRequestDTO userRequestDTO) {
        tbl_user tbl_user = new tbl_user();
        modelMapper.map(userRequestDTO, tbl_user);
        userRepository.save(tbl_user);

        List<tbl_address> tbl_address = new ArrayList<>();
        for (int i = 0; i < userRequestDTO.getAddress().size(); i++) {
            tbl_address address = new tbl_address();
            modelMapper.map(userRequestDTO.getAddress().get(i), address);
            address.setUser(tbl_user);
            tbl_address.add(address);
        }
        addressRepository.saveAll(tbl_address);
        return tbl_user.getId();
    }

    @Override
    public void updateUser(Long id , UserUpdateDTO updateDTO) {
        tbl_user tbl_user = userRepository.findById(id).get();
        tbl_user = convertorToTblUser.ToTblUser(updateDTO);
        tbl_user.setId(id);
        userRepository.save(tbl_user); // THAY DOI BEN USER TRUOC.

        for (int i = 0; i < updateDTO.getAddress().size(); i++) {
            tbl_address address = new tbl_address();
            tbl_address address1 = addressRepository.findByUser_Id(id);
            address.setId(address1.getId());
            modelMapper.map(updateDTO.getAddress().get(i), address);
            address.setUser(tbl_user);
            addressRepository.save(address);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        tbl_user user = new tbl_user();
        user = userRepository.findById(userId).get();
        List<tbl_address> addressList = user.getAddressList();
        for (tbl_address address : addressList) {
            addressRepository.delete(address);
        }
        userRepository.delete(user);
    }

    @Override
    public UserDetailResponse getUserById(Long userId) {
        tbl_user user = userRepository.findById(userId).get();
        UserDetailResponse userDetailResponse = new UserDetailResponse();
        modelMapper.map(user, userDetailResponse);
        return userDetailResponse;
    }

    @Override
    public PageResponse<List<UserDetailResponse>> getAllUsers(int pageNo, int pageSize, String sortField) {

        List<Sort.Order> orders = new ArrayList<>();

        // Kiem tra xem sortField co gia tri khong da
        if (StringUtils.hasLength(sortField)) {
            // Kiem tra xem co giong voi bieu thuc chinh quy khong
            // firstName:asc|desc
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sortField);
            if (matcher.find()) {
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    orders.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                }
                else {
                    orders.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }

        }

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orders));
        Page<tbl_user> user = userRepository.findAll(pageable);

        PageResponse<List<UserDetailResponse>> pageResponse = new PageResponse<>();
        pageResponse.setPageNo(pageNo);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotalPage(user.getTotalPages());

        List<UserDetailResponse> userList = new ArrayList<>();
        for (tbl_user item : user.getContent()) {
            UserDetailResponse userDetailResponse = new UserDetailResponse();
            modelMapper.map(item, userDetailResponse);
            userList.add(userDetailResponse);
        }
        pageResponse.setItem(userList);
        return pageResponse;
    }

    @Override
    public PageResponse<List<UserDetailResponse>> getAllUserWithSortAnhSearchService(int pageNo, int pageSize, String search, String sortField) {
        PageResponse<List<UserDetailResponse>> pageResponse = new PageResponse<>();

        pageResponse = searchRepository.getUserAllWithSortAndSearchRepository(pageNo, pageSize, search, sortField);

        return pageResponse;
    }
}
