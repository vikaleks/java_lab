package com.lab4.security;

import com.lab4.model.Owner;
import com.lab4.model.Pet;
import com.lab4.model.User;
import com.lab4.repository.PetRepository;
import com.lab4.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityService {
    private final PetRepository petRepo;
    private final UserRepository userRepo;

    public SecurityService(PetRepository petRepo, UserRepository userRepo) {
        this.petRepo = petRepo;
        this.userRepo = userRepo;
    }

    public boolean isOwner(Authentication auth, Long petId) {
        String username = auth.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Pet pet = petRepo.findById(petId).orElse(null);
        return pet != null && pet.getOwner().getId().equals(user.getOwner().getId());
    }
}

