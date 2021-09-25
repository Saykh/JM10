package com.edilov.jm97.service;

import com.edilov.jm97.entity.User;
import com.edilov.jm97.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByEmail(username);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void save(User user) {
        User userDB = getUserById(user.getId());
        if (user.getPassword().equals("")) {
            user.setPassword(userDB.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }


    public void saveUser(User user, Long[] roles) {
        user.setRoles(Arrays.stream(roles).map(roleService::getRoleById).collect(Collectors.toSet()));
        save(user);
    }


    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }


    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
