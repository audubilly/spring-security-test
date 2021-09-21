package com.billy.testdemo.controller;


import com.billy.testdemo.config.CustomUserDetailsService;
import com.billy.testdemo.config.JwtUtil;
import com.billy.testdemo.model.AuthenticationRequests;
import com.billy.testdemo.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequests authenticationRequests) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequests.getUsername(),authenticationRequests.getPassword()
            ));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED",e);
        }
        catch (BadCredentialsException e){
            throw new Exception("IVALID_CREDENTIALS" , e );
        }
       UserDetails userDetails =  customUserDetailsService.loadUserByUsername(authenticationRequests.getUsername())
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
