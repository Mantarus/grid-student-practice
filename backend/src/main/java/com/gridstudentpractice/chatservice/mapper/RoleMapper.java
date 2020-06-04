package com.gridstudentpractice.chatservice.mapper;

import com.gridstudentpractice.chatservice.model.Role;
import com.gridstudentpractice.chatservice.model.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.context.annotation.Profile;

@Profile("orm")
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleMapper {

    @Mappings({
            @Mapping(target = "id", source = "role.id"),
            @Mapping(target = "name", source = "role.name")
    })
    RoleDto toDto(Role role);

    @Mappings({
            @Mapping(target = "id", source = "roleDto.id"),
            @Mapping(target = "name", source = "roleDto.name"),
            @Mapping(target = "userEntities", ignore = true)
    })
    Role toEntity(RoleDto roleDto);

}
