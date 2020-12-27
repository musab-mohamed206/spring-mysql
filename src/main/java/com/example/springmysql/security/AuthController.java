package com.example.springmysql.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager  authenticationManager;

    @PostMapping(value = {"" , "/"})
    public JwtResponse signIn(@RequestBody SigninRequest signinRequest) {

        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userService.loadUserByUsername(signinRequest.getUsername());
        String token = tokenUtil.generateToken(userDetails);
        JwtResponse response = new JwtResponse(token);
        return response;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
