package com.plamenpetkovonline.springboot.thymeleafdemo.service;


import com.plamenpetkovonline.springboot.thymeleafdemo.dao.EmailDao;
import com.plamenpetkovonline.springboot.thymeleafdemo.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {

    private EmailDao employeeRepository;

    @Autowired
    public EmailServiceImpl(EmailDao theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Email> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Email findById(int theId) {
        Optional<Email> result = employeeRepository.findById(theId);

        Email theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public void save(Email theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public List<Email> searchBy(String theName) {

        List<Email> results = null;

        if (theName != null && (theName.trim().length() > 0)) {
            results = employeeRepository.findByNameContainsOrSurnameContainsAllIgnoreCase(theName, theName);
        } else {
            results = findAll();
        }

        return results;

    }

    @Override
    public List<Email> findUsersContaining(String contains) {
        List<Email> resultsList = null;
        String currentUsername;
        try {
            if (contains != null && (contains.trim().length() > 0)) {

                //get current user
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (principal instanceof UserDetails) {
                    currentUsername = ((UserDetails) principal).getUsername();
                } else {
                    currentUsername = principal.toString();
                }
                //now check if current user is  in contains
                if (contains == currentUsername) {
                    resultsList = employeeRepository.findAll().stream().filter(u -> u.getEmail().contains(currentUsername)).collect(Collectors.toList());

                }
            }
        } catch (Exception e) {
            System.out.println("error in findUsersContaining");
            e.printStackTrace();
        }
        return resultsList;

    }
}






