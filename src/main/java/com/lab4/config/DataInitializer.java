package com.lab4.config;

import com.lab4.model.Role;
import com.lab4.model.User;
import com.lab4.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository users;
    private final PasswordEncoder encoder;

    public DataInitializer(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (users.findByUsername("admin").isEmpty()) {
            User a = new User();
            a.setUsername("admin");
            a.setPassword(encoder.encode("admin"));
            a.setRole(Role.ROLE_ADMIN);
            users.save(a);
        }
    }
}
