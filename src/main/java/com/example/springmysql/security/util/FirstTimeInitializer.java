package com.example.springmysql.security.util;

import com.example.springmysql.security.AppUser;
import com.example.springmysql.security.UserService;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class FirstTimeInitializer implements CommandLineRunner{

    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // chake if no user exists , create some users
        if (userService.findAll().isEmpty()) {
            logger.info("No user account fond. Create some users");

            AppUser user = new AppUser("admin@admin.com" , "admin" , "Admin");
            userService.save(user);
        }

    }
    
}
