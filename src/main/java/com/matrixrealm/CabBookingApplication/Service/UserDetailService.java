package com.matrixrealm.CabBookingApplication.Service;

import com.matrixrealm.CabBookingApplication.Model.User;
import com.matrixrealm.CabBookingApplication.Repositry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findByEmail(username);
        if(user.isPresent())
        {
            User userobj = user.get();
            return User.builder().email(userobj.getEmail()).password(userobj.getPassword()).role(userobj.getRole()).build();

        }else {
            throw new UsernameNotFoundException("UserNotFound");
        }
    }
}
