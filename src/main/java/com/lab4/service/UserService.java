package com.lab4.service;

import com.lab4.dto.OwnerDto;
import com.lab4.dto.RegisterRequest;
import com.lab4.dto.UserDto;
import com.lab4.mapper.UserMapper;
import com.lab4.model.Owner;
import com.lab4.model.Role;
import com.lab4.model.User;
import com.lab4.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    private final UserRepository users;
    private final UserMapper mapper;
    private final OwnerService owners;
    private final PasswordEncoder encoder;

    public UserService(UserRepository users, UserMapper userMapper,
                       OwnerService owners, PasswordEncoder encoder) {
        this.users = users;
        this.mapper = userMapper;
        this.owners = owners;
        this.encoder = encoder;
    }

    public UserDto register(RegisterRequest r) {
        if (users.findByUsername(r.getUsername()).isPresent())
            throw new RuntimeException("Username already exists");

        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setName(r.getOwnerName());
        OwnerDto saved = owners.createOwner(ownerDto);

        User u = new User();
        u.setUsername(r.getUsername());
        u.setPassword(encoder.encode(r.getPassword()));
        u.setRole(Role.ROLE_USER);
        u.setOwner(new Owner(saved.getId(), saved.getName()));

        return mapper.toDto(users.save(u));
    }
}
