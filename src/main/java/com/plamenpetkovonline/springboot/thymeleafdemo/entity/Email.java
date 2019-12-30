package com.plamenpetkovonline.springboot.thymeleafdemo.entity;


import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Email {

    // define fields


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "department")
    private String department;

    @Column(name = "email")
    private String email;


    // define constructors

    public Email() {

    }

    public Email(int id, String name, String surname, String email, String password, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.surname = surname;
        this.department = department;
        this.password = password;
    }


    public Email(String name, String surname, String department) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.password = generateRandomPass(10);
    }

    // define getter/setter

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    // define tostring

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + name + ", email=" + email + "]";
    }

    public String generateRandomPass(int length) {
        String randomPass = RandomStringUtils.randomAscii(length);
        return randomPass;
    }

    public String generateMail(String firstname, String surname, String department, String domain) {
        String generatedMail = firstname.replaceAll("\\s", "") + "." + surname.replaceAll("\\s", "") + "@" + department.replaceAll("\\s", "") + "." + domain;
        return generatedMail.toLowerCase();
    }

}











