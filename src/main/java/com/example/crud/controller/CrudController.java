package com.example.crud.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.crud.model.Crud;
import com.example.crud.repository.CrudRepository;
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

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/ems")
public class CrudController {
    @Autowired
    CrudRepository crudRepository;

    @GetMapping("/employeedetails")
    public ResponseEntity<List<Crud>> getAllCruds(@RequestParam(required = false) String email) {
        try {
            List<Crud> cruds = new ArrayList<Crud>();

            if (email == null)
                crudRepository.findAll().forEach(cruds::add);
            else
                crudRepository.findByEmailContaining(email).forEach(cruds::add);

            if (cruds.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cruds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employeedetails/{id}")
    public ResponseEntity<Crud> getCrudById(@PathVariable("id") long id) {
        Optional<Crud> crudData = crudRepository.findById(id);

        if (crudData.isPresent()) {
            return new ResponseEntity<>(crudData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employeedetails")
    public ResponseEntity<Crud> createCrud(@Valid @RequestBody Crud crud) {
        try {
            Crud _crud = crudRepository
                    .save(new Crud(crud.getFirstname(), crud.getLastname(), crud.getMobile(),crud.getEmail(),crud.getSalary()));
            return new ResponseEntity<>(_crud, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employeedetails/{id}")
    public ResponseEntity<Crud> updateCrud(@Valid @PathVariable("id") long id,@Valid @RequestBody Crud crud) {
        Optional<Crud> crudData = crudRepository.findById(id);

        if (crudData.isPresent()) {
            Crud _crud = crudData.get();
            _crud.setFirstname(crud.getFirstname());
            _crud.setLastname(crud.getLastname());
            _crud.setMobile(crud.getMobile());
            _crud.setEmail(crud.getEmail());
            _crud.setSalary(crud.getSalary());
            return new ResponseEntity<>(crudRepository.save(_crud), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employeedetails/{id}")
    public ResponseEntity<HttpStatus> deleteCrud(@PathVariable("id") long id) {
        try {
            crudRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/employeedetails")
    public ResponseEntity<HttpStatus> deleteAllCruds() {
        try {
            crudRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
