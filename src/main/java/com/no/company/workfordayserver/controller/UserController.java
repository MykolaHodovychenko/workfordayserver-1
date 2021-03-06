package com.no.company.workfordayserver.controller;

import com.no.company.workfordayserver.entities.User;
import com.no.company.workfordayserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void setUser(@RequestBody User newUser) throws DataFormatException {
        userService.saveUser(newUser);
    }

    @RequestMapping(value = "/get")
    public User getUser(Principal principal){
      User user = userService.getUserByEmail(principal.getName());
      user.setPassword(null);
      return user;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editUser(Principal principal, @RequestBody User user){
        user.setIsUser(null);
        userService.editUser(user, principal.getName());
    }

    @RequestMapping(value = "/delete")
    public void delete(Principal principal){
        userService.removeUser(principal.getName());
    }

    @RequestMapping(value = "/addadmin")
    public void setPermisionToUser(@RequestParam(name = "email") String email){
        User user = new User();
        user.setIsUser(false);
        userService.editUser(user, email);
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}


