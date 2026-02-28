package com.lab4.repository;

import com.lab4.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> findByColour(Pet.Colour colour, Pageable pageable);

    Page<Pet> findByColourAndNameAndBirthdayAndBreedAndOwnerId(Pet.Colour colour, String name, LocalDate birthday, String breed, Long ownerId, Pageable pageable);
}