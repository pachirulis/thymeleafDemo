package com.plamenpetkovonline.springboot.thymeleafdemo.dao;

import com.plamenpetkovonline.springboot.thymeleafdemo.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailDao extends JpaRepository<Email, Integer> {

    // search by name
    List<Email> findByNameContainsOrSurnameContainsAllIgnoreCase(String name, String surname);

    // search emails containing currentuser
    List<Email> findByEmailContaining(String currentUser);
}
