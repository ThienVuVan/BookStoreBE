package com.bookstore.common.service;

import com.bookstore.common.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> retrieveAllRole();
    public Role saveRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Role role);
    public Role retrieveByName(String name);
    public List<Role> retrieveRoleByUserName(String username);
}
