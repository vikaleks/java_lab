package com.lab4.dto;
import com.lab4.model.Pet;

import java.time.LocalDate;
import java.util.Set;

public class PetDto {
    private Long id;
    private String name;
    private LocalDate birthday;
    private String breed;
    private Pet.Colour colour;
    private Long ownerId;
    private Set<Long> friendId;

    public PetDto(Long id, String name, LocalDate birthday, String breed, Pet.Colour colour, Long ownerId, Set<Long> friendId) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.colour = Pet.Colour.valueOf(String.valueOf(colour));
        this.ownerId = ownerId;
        this.friendId = friendId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Pet.Colour getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = Pet.Colour.valueOf(colour);
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Set<Long> getFriendId() {
        return friendId;
    }

    public void setFriendId(Set<Long> friendId) {
        this.friendId = friendId;
    }
}