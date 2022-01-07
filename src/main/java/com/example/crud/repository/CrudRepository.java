package com.example.crud.repository;

import com.example.crud.model.Crud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudRepository extends JpaRepository<Crud, Long> {
//    List<Crud> findByPublished(boolean published);
//
    List<Crud> findByEmailContaining(String email);
}
