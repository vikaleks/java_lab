package com.lab4.controller;

import com.lab4.dto.PetDto;
import com.lab4.model.Pet;
import com.lab4.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/pets")
@Tag(name = "PetController")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить питомца по айди")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public PetDto getPetById(@PathVariable Long id) {
        return petService.findById(id);
    }

    @GetMapping
    @Operation(summary = "Получить питомцев по цвету")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<PetDto> getPetsByColour(@RequestParam Pet.Colour colour, Pageable pageable) {
        return petService.findByColour(colour, pageable);
    }

    @GetMapping("/filter")
    @Operation(summary = "Фильтр питомцев по цвету, имени, дате рождения, породе и айди хозяина")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<PetDto> findByColourAndNameAndBirthdayAndBreedAndOwnerId(@RequestParam(required = false) Pet.Colour colour,
                                                                         @RequestParam(required = false) String name,
                                                                         @RequestParam(required = false) LocalDate birthday,
                                                                         @RequestParam(required = false) String breed,
                                                                         @RequestParam(required = false) Long ownerId,
                                                                         Pageable pageable) {
        return petService.findByColourAndNameAndBirthdayAndBreedAndOwnerId(colour, name, birthday, breed, ownerId, pageable);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить питомца")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @securityService.isPetOwner(#id, principal.username))")
    public PetDto updatePet(@PathVariable Long id, @RequestBody PetDto petDto) {
        PetDto updatedPet = petService.updatePet(id, petDto);
        return ResponseEntity.ok(updatedPet).getBody();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить питомца по айди")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @securityService.isPetOwner(#id, principal.username))")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Создать нового питомца")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @securityService.isOwnerOwner(#petDto.ownerId, principal.username))")
    public ResponseEntity<PetDto> createPet(@RequestBody @Valid PetDto petDto) {
        PetDto createdPet = petService.createPet(petDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }
}