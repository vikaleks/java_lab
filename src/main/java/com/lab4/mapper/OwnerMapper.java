package com.lab4.mapper;

import com.lab4.dto.OwnerDto;
import com.lab4.model.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerMapper {
    @Mapping(target = "petId", expression = "java(owner.getPets().stream().map(com.lab4.model.Pet::getId).collect(java.util.stream.Collectors.toSet()))")
    OwnerDto toDto(Owner owner);

    @Mapping(target = "pets", ignore = true)
    Owner toEntity(OwnerDto ownerDto);
}