package com.edilov.jm97.service;

import com.edilov.jm97.entity.Role;
import com.edilov.jm97.repository.RoleRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role getRoleByName(String name) {
       return roleRepository.findRoleByName(name);
    }

    public Role getRoleById(long id) {
        return roleRepository.findRoleById(id);
    }


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
