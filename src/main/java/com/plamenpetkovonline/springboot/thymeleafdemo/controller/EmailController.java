package com.plamenpetkovonline.springboot.thymeleafdemo.controller;


import com.plamenpetkovonline.springboot.thymeleafdemo.entity.Email;
import com.plamenpetkovonline.springboot.thymeleafdemo.service.EmailService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class EmailController {

    private EmailService employeeService;

    @SuppressWarnings("unused")
    private static EmailController myInstanceController;

    public EmailController(EmailService theEmployeeService) {
        myInstanceController = this;
        employeeService = theEmployeeService;
    }

    @GetMapping("/error")
    public String redirect() {
        return "redirect:/users/list";
    }
    // add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        String currentUser = getCurrentUser();
        // get employees from db
        List<Email> theEmployees;
        try {
            theEmployees = employeeService.findUsersContaining(currentUser);
            // add to the spring model

            theModel.addAttribute("employees", theEmployees);
        } catch (Exception e) {
            System.out.println("error enn el controoller");
            e.printStackTrace();
        }
        return "users/list-employees";
    }

    // add mapping for show form
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        //create model  attribute  to bind form data
        Email theUser = new Email();
        theModel.addAttribute("user", theUser);
        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") Email theUser) {
        //assign email and pass
        theUser.setEmail(theUser.generateMail(theUser.getName(), theUser.getSurname(), theUser.getDepartment(), getCurrentUser()));
        theUser.setPassword(theUser.generateRandomPass(14));
        //save the employee
        employeeService.save(theUser);
        //use a redirect to prevent duplicate submissions
        return "redirect:/users/list";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
        //get user from the service
        Email theUser = employeeService.findById(theId);


        //set employee  as model attribute to  prepopulate the form
        theModel
                .addAttribute("user", theUser);

        //send  over to our form
        return "users/user-form";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId, Model theModel) {
        //delete the user
        employeeService.deleteById(theId);
        //redirect to list

        return "redirect:/users/list";

    }

    @GetMapping("/search")
    public String delete(@RequestParam("employeeName") String theName,
                         Model theModel) {

        // delete the employee
        List<Email> theEmployees = employeeService.searchBy(theName).stream().filter(u -> u.getEmail().contains(getCurrentUser())).collect(Collectors.toList());

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        // send to /employees/list
        return "/users/list-employees";

    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }


}




