package com.lab4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private String breed;

    public enum Colour {
        BLACK, WHITE, GREY, ORANGE, BROWN
    }

    @Enumerated(EnumType.STRING)
    private Colour colour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "pet_friends",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )

    private Set<Pet> friends = new HashSet<>();

    public Pet() {
    }

    public Pet(Long id, String name, LocalDate birthday, String breed, Colour colour, Owner owner, Set<Pet> friends) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.colour = colour;
        this.owner = owner;
        this.friends = friends;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<Pet> getFriends() {
        return friends;
    }

    public void setFriends(Set<Pet> friends) {
        this.friends = friends;
    }

    public void addFriend(Pet friend) {
        if (friend != null && friend != this) {
            friends.add(friend);
            friend.getFriends().add(this);
        }
    }

    public void removeFriend(Pet friend) {
        if (friend != null) {
            friends.remove(friend);
            friend.getFriends().remove(this);
        }
    }

}