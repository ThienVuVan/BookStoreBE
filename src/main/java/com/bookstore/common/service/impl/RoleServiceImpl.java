package com.bookstore.common.service.impl;

import com.bookstore.common.entity.Role;
import com.bookstore.common.repository.RoleRepository;
import com.bookstore.common.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Optional<Role> retrieveByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> retrieveRoleByUserName(String username) {
        return roleRepository.findRoleByUserName(username);
    }

    @Override
    public List<Role> retrieveRolesByUserId(Integer userId) {
        return roleRepository.findRolesByUserId(userId);
    }
}
