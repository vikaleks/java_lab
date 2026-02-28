package com.lab4.dto;

import java.time.LocalDate;
import java.util.Set;

public class OwnerDto {
    private Long id;
    private String name;
    private LocalDate birthday;
    private Set<Long> petId;

    public OwnerDto() {}

    public OwnerDto(Long id, String name, LocalDate birthday, Set<Long> petId) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.petId = petId;
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

    public Set<Long> getPetId() {
        return petId;
    }

    public void setPetId(Set<Long> petId) {
        this.petId = petId;
    }
}