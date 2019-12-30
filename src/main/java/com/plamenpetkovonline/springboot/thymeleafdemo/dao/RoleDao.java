package com.plamenpetkovonline.springboot.thymeleafdemo.dao;

import com.plamenpetkovonline.springboot.thymeleafdemo.entity.Role;

public interface RoleDao {

    Role findRoleByName(String theRoleName);

}
