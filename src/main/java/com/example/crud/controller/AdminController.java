package com.example.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.crud.model.Admin;
//import com.example.crud.model.Crud;
import com.example.crud.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/ems")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/admindetails")
    public ResponseEntity<List<Admin>> getAllAdmins(@RequestParam(required = false) String emailid) {
        try {
            List<Admin> admins = new ArrayList<Admin>();

            if (emailid == null)
                adminRepository.findAll().forEach(admins::add);
            else
                adminRepository.findByEmailidContaining(emailid).forEach(admins::add);

            if (admins.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/admindetails/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") long id) {
        Optional<Admin> adminData = adminRepository.findById(id);

        if (adminData.isPresent()) {
            return new ResponseEntity<>(adminData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admindetails")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        try {
            Admin _admin = adminRepository
                    .save(new Admin(admin.getEmailid(), admin.getPassword()));
            return new ResponseEntity<>(_admin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/admindetails/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") long id, @RequestBody Admin admin) {
        Optional<Admin> adminData = adminRepository.findById(id);

        if (adminData.isPresent()) {
            Admin _admin = adminData.get();
            _admin.setEmailid(admin.getEmailid());
            _admin.setPassword(admin.getPassword());

            return new ResponseEntity<>(adminRepository.save(_admin), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admindetails/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") long id) {
        try {
            adminRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/admindetails")
    public ResponseEntity<HttpStatus> deleteAllAdmins() {
        try {
            adminRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @GetMapping("/employeedetails/published")
//    public ResponseEntity<List<Crud>> findByPublished() {
//        try {
//            List<Crud> cruds = crudRepository.findByPublished(true);
//
//            if (cruds.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(cruds, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
