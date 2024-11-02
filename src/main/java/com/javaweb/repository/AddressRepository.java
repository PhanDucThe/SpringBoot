package com.javaweb.repository;

import com.javaweb.model.tbl_address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<tbl_address, Long> {
    tbl_address findByUser_Id(Long id);
}
