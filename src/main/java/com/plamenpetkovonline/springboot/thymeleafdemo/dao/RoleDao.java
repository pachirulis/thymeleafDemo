package com.plamenpetkovonline.springboot.thymeleafdemo.dao;

import com.plamenpetkovonline.springboot.thymeleafdemo.entity.Role;

import java.util.List;

public interface RoleDao {

    Role findRoleByName(String theRoleName);

    List<Role> findRoles();
}
