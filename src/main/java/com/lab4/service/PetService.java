package com.lab4.service;

import com.lab4.dto.PetDto;
import com.lab4.mapper.PetMapper;
import com.lab4.model.Pet;
import com.lab4.model.Role;
import com.lab4.model.User;
import com.lab4.repository.PetRepository;
import com.lab4.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.*;

@Service
public class PetService {

    private final PetRepository pets;
    private final PetMapper mapper;
    private final UserRepository users;

    public PetService(PetRepository pets, PetMapper mapper, UserRepository users) {
        this.pets = pets;
        this.mapper = mapper;
        this.users = users;
    }

    public PetDto findById(Long id) {
        Pet pet = pets.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pet not found"));
        return mapper.toDto(pet);
    }

    public Page<PetDto> findByColour(Pet.Colour colour, Pageable page) {
        return pets.findByColour(colour, page).map(mapper::toDto);
    }

    public Page<PetDto> findByColourAndNameAndBirthdayAndBreedAndOwnerId(
            Pet.Colour colour, String name, LocalDate birthday,
            String breed, Long ownerId, Pageable page) {
        return pets
                .findByColourAndNameAndBirthdayAndBreedAndOwnerId(colour, name, birthday, breed, ownerId, page)
                .map(mapper::toDto);
    }

    public PetDto createPet(PetDto dto) {
        User current = currentUser();
        Pet pet = mapper.toEntity(dto);
        pet.setOwner(current.getOwner());
        return mapper.toDto(pets.save(pet));
    }

    public PetDto updatePet(Long id, PetDto dto) {
        User current = currentUser();
        Pet pet = pets.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pet not found"));
        boolean owner = pet.getOwner().getId().equals(current.getOwner().getId());
        boolean admin = current.getRole() == Role.ROLE_ADMIN;
        if (!owner && !admin) {
            throw new ResponseStatusException(FORBIDDEN, "Access denied");
        }
        pet.setName(dto.getName());
        pet.setBirthday(dto.getBirthday());
        pet.setBreed(dto.getBreed());
        pet.setColour(dto.getColour());
        return mapper.toDto(pets.save(pet));
    }

    public void deletePet(Long id) {
        if (!pets.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Pet not found");
        }
        pets.deleteById(id);
    }

    private User currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return users.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
    }
}
