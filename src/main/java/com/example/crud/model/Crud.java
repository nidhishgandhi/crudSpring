package com.example.crud.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "employeedetails")
public class Crud {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "firstname")
    @NotBlank(message = "Firstname is mandatory")
    @Size(min=3,max=20,message = "Enter between 3-20")
    private String firstname;

    @Column(name = "lastname")
    @NotBlank(message = "Lastname is mandatory")
    @Size(min=3,max=20,message = "Enter between 3-20")
    private String lastname;

    @Column(name = "mobile")
    @NotBlank(message = "Mobile is mandatory")
    @Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number")
    private String mobile;

    @Column(name = "email")
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @Column(name = "salary")
    @NotBlank(message = "Salary is mandatory")
    @Pattern(regexp = "([0-9]+[.])?[0-9]+",message = "Invalid salary")
    private String salary;

    public Crud() {
    }

    public Crud(String firstname, String lastname,String mobile,String email,String salary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.email = email;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Crud [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ",mobile=" + mobile + ",email=" + email + ",salary="+ salary+ "]";
    }
}


