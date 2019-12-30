package com.plamenpetkovonline.springboot.thymeleafdemo.service;

import com.plamenpetkovonline.springboot.thymeleafdemo.entity.Email;

import java.util.List;

public interface EmailService {

    List<Email> findAll();

    Email findById(int theId);

    void save(Email theEmployee);

    List<Email> searchBy(String theName);

    void deleteById(int theId);

    List<Email> findUsersContaining(String contains);

}
