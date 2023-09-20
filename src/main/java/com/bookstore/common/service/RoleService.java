package com.bookstore.common.service;

import com.bookstore.common.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    public Optional<Role> retrieveByName(String name);
    public List<Role> retrieveRoleByUserName(String username);
    public List<Role> retrieveRolesByUserId(Integer userId);
}
