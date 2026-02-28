package com.lab4.controller;

import com.lab4.model.Owner;
import com.lab4.model.Role;
import com.lab4.model.User;
import com.lab4.repository.OwnerRepository;
import com.lab4.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository users;
    private final OwnerRepository owners;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository users, OwnerRepository owners,
                          PasswordEncoder encoder) {
        this.users = users;
        this.owners = owners;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> req) {
        User u = new User();
        u.setUsername(req.get("username"));
        u.setPassword(encoder.encode(req.get("password")));
        u.setRole(Role.ROLE_USER);

        Owner o = new Owner();
        o.setName("User " + req.get("username"));
        o = owners.save(o);

        u.setOwner(o);
        users.save(u);

        return ResponseEntity.ok("Registered");
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(org.springframework.security.core.Authentication a) {
        return ResponseEntity.ok(a.getName());
    }
}
