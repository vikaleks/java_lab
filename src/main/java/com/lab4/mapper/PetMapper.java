package com.lab4.mapper;

import com.lab4.dto.PetDto;
import com.lab4.model.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetMapper {
    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(target = "friendId", expression = "java(pet.getFriends().stream().map(com.lab4.model.Pet::getId).collect(java.util.stream.Collectors.toSet()))")
    PetDto toDto(Pet pet);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "friends", ignore = true)
    Pet toEntity(PetDto petDto);
}