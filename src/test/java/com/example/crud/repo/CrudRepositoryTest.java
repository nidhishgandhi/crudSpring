package com.example.crud.repo;

import com.example.crud.model.Crud;
import com.example.crud.repository.CrudRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CrudRepositoryTest {

    @Autowired
    private CrudRepository crudRepo;
    @Test
    void findByEmailContaining() {
        Crud crud = new Crud("nidg","gandhiii","8888888888","dcs@gmail.com","555");
        crudRepo.save(crud);

        List<Crud> actualResult= crudRepo.findByEmailContaining("dcs@gmail.com");
        assertThat(actualResult).asList();
    }
    @BeforeEach
    void setUp(){
        System.out.println("Setting Up");
    }

    @AfterEach
    void tearDown(){
      //  Crud crud = new Crud("nidg","gandhiii","8888888888","dcs@gmail.com","555");
        System.out.println("Tearing Down");
      //  crudRepo.delete(crud);
        System.out.println("Deleted");
    }



}