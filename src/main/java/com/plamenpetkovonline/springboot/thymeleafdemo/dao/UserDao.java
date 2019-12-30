package com.plamenpetkovonline.springboot.thymeleafdemo.dao;

import com.plamenpetkovonline.springboot.thymeleafdemo.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User user);

}
