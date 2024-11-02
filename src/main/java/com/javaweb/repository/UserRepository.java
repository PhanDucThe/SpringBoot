package com.javaweb.repository;

import com.javaweb.model.tbl_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<tbl_user, Long> {

}
