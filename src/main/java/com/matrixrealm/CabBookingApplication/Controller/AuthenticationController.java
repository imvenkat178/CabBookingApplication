package com.matrixrealm.CabBookingApplication.Controller;

import com.matrixrealm.CabBookingApplication.JWTAuthentication.JwtUtil;
import com.matrixrealm.CabBookingApplication.Model.AuthenticationRequest;
import com.matrixrealm.CabBookingApplication.Model.User;
import com.matrixrealm.CabBookingApplication.Service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {
    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    public UserDetailService userDetailService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authreq)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authreq.getUsername(),authreq.getPassword()));
        User user = (User) userDetailService.loadUserByUsername(authreq.getUsername());
        if(user!=null)
        {
            return ResponseEntity.ok(jwtUtil.generateToken(user));
        }

        return ResponseEntity.status(400).body("Error occured");
    }
}
