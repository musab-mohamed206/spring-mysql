package com.example.springmysql.security;

import org.springframework.stereotype.Service;

import java.util.List;

import com.example.springmysql.error.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return new User("musab" , passwordEncoder().encode("password") ,AuthorityUtils.NO_AUTHORITIES);
        AppUser user = userRepository.findByEmail(username);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    public void save(AppUser user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        this.userRepository.save(user);
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }
}
