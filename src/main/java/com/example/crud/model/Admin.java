package com.example.crud.model;

import javax.persistence.*;


@Entity
@Table(name = "admindetails")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "emailid")
    private String emailid;

    @Column(name = "password")
    private String password;

    public Admin() {
    }

    public Admin(String emailid, String password) {
        this.emailid = emailid;
        this.password = password;

    }

    public long getId() {
        return id;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Admin [id=" + id + ", emailid=" + emailid + ", password=" + password +"]";
    }
}

