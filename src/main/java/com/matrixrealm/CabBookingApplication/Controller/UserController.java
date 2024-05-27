package com.matrixrealm.CabBookingApplication.Controller;

import com.matrixrealm.CabBookingApplication.Model.User;
import com.matrixrealm.CabBookingApplication.Repositry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello";
    }

    @PostMapping("/register/user")
    public User register(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
