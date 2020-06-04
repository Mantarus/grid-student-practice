package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.RoleDto;

public interface RoleRepository {

    void createRole(RoleDto roleDto);
    RoleDto getRoleById(int id);
    void updateRole(RoleDto roleDto);
    void deleteRoleById(int id);

}
