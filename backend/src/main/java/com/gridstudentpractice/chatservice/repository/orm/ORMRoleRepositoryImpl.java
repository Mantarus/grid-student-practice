package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.exception.NoEntityException;
import com.gridstudentpractice.chatservice.mapper.RoleMapper;
import com.gridstudentpractice.chatservice.model.RoleDto;
import com.gridstudentpractice.chatservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("orm")
@Repository
public class ORMRoleRepositoryImpl implements RoleRepository {

    @Lazy
    @Autowired
    private ORMRoleRepository ormRoleRepository;

    @Autowired
    private RoleMapper mapper;

    @Override
    public void createRole(RoleDto roleDto) {
        ormRoleRepository.save(mapper.toEntity(roleDto));
    }

    @Override
    public RoleDto getRoleById(int id) {
        return mapper.toDto(ormRoleRepository.findById(id).orElseThrow(() ->
                new NoEntityException("No role with id " + id)));
    }

    @Override
    public void updateRole(RoleDto roleDto) {
        ormRoleRepository.save(mapper.toEntity(roleDto));
    }

    @Override
    public void deleteRoleById(int id) {
        ormRoleRepository.deleteById(id);
    }
}
