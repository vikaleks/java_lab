package com.lab4.mapper;

import com.lab4.dto.UserDto;
import com.lab4.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(
            target = "roles",
            expression = "java(user.getRole() != null " +
                    "? java.util.Set.of(user.getRole().name()) " +
                    ": java.util.Set.of())"
    )
    UserDto toDto(User user);
}
