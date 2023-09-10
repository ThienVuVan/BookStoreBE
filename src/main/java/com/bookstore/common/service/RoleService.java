package com.bookstore.common.service;

import com.bookstore.common.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    public Role saveRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Role role);
    public Optional<Role> retrieveByName(String name);
    public List<Role> retrieveRoleByUserName(String username);
    public List<Role> retrieveRolesByUserId(Integer userId);
}
