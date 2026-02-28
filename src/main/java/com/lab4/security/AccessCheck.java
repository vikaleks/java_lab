//package com.lab4.security;
//
//import com.lab4.repository.PetRepository;
//import com.lab4.repository.UserRepository;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//@Component("accessCheck")
//public class AccessCheck {
//
//    private final PetRepository pets;
//    private final UserRepository users;
//
//    public AccessCheck(PetRepository pets, UserRepository users) {
//        this.pets = pets;
//        this.users = users;
//    }
//
//    public boolean isPetOwner(Authentication a, Long petId) {
//        return pets.findById(petId).map(p ->
//                users.findByUsername(a.getName())
//                        .map(u -> u.getOwner() != null &&
//                                p.getOwner() != null &&
//                                p.getOwner().getId().equals(u.getOwner().getId()))
//                        .orElse(false)
//        ).orElse(false);
//    }
//
//    public boolean isOwner(Long ownerId) {
//        Authentication a = SecurityContextHolder.getContext().getAuthentication();
//        boolean admin = a.getAuthorities().stream()
//                .anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"));
//        if (admin) return true;
//        return users.findByUsername(a.getName())
//                .map(u -> u.getOwner() != null && u.getOwner().getId().equals(ownerId))
//                .orElse(false);
//    }
//}
