package com.edilov.jm97.controller;

import com.edilov.jm97.entity.User;
import com.edilov.jm97.service.RoleService;
import com.edilov.jm97.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String logIn(){
        return "login";
    }


    @GetMapping("/admin")
    public String admin(Principal principal, Model model, @ModelAttribute("addNewUser") User user) {
        model.addAttribute("uPrincipal", userService.getUserByUsername(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping("/admin/save")
    public String save(@ModelAttribute("user") User user,
                       @RequestParam(value = "slcroles") Long[] roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }


    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "slcroles") Long[] roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        model.addAttribute("uPrincipal", userService.getUserByUsername(principal.getName()));
        return "user";
    }

}


