package com.example.crud.repository;

import com.example.crud.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    //    List<Crud> findByPublished(boolean published);
//
    List<Admin> findByEmailidContaining(String emailid);
}