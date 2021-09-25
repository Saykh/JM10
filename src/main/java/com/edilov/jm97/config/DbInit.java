package com.edilov.jm97.config;

import com.edilov.jm97.entity.Role;
import com.edilov.jm97.entity.User;
import com.edilov.jm97.service.RoleService;
import com.edilov.jm97.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DbInit {

    private final UserService userService;
    private final RoleService roleService;

    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
    Role role1 = new Role("ROLE_ADMIN");
    Role role2 = new Role("ROLE_USER");

    roleService.saveRole(role1);
    roleService.saveRole(role2);

    User user1 = new User();
    user1.setUsername("Dopa");
    user1.setPassword("12345");
    user1.setName("Muslim");
    user1.setLastName("Isaev");
    user1.setAge(26);
    user1.setEmail("isaev_mv@mail.ru");
    user1.setRoles(Set.of(roleService.getRoleByName("ROLE_ADMIN"), roleService.getRoleByName("ROLE_USER")));
    userService.save(user1);

    User user2 = new User();
    user2.setUsername("Faker");
    user2.setPassword("1234");
    user2.setName("Saykhan");
    user2.setLastName("Edilov");
    user2.setAge(27);
    user2.setEmail("edilov_st@mail.ru");
    user2.setRoles(Set.of( roleService.getRoleByName("ROLE_USER")));
    userService.save(user2);

    }
}
