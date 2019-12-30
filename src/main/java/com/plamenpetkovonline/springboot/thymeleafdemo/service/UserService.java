package com.plamenpetkovonline.springboot.thymeleafdemo.service;

import com.plamenpetkovonline.springboot.thymeleafdemo.entity.User;
import com.plamenpetkovonline.springboot.thymeleafdemo.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);
}
