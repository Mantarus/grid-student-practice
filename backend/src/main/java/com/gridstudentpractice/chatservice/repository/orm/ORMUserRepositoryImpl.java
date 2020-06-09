package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.mapper.UserMapper;
import com.gridstudentpractice.chatservice.model.Role;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Profile("orm")
@Repository
public class ORMUserRepositoryImpl implements UserRepository {

    @Lazy
    @Autowired
    private ORMUserRepository ormUserRepository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createUser(UserDto userDto) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("user-entity-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        User user = mapper.toEntity(userDto);
        Role role = entityManager.find(Role.class, 1, properties);
        user.getRoleEntities().add(role);
        ormUserRepository.save(user);
    }

    @Override
    public UserDto getUserByLogin(String userLogin) {
        return mapper.toDTO(ormUserRepository.findByLogin(userLogin) );
    }

    @Override
    public void updateUserLoginAndPassword(UserDto userDto) {
        User user = ormUserRepository.findById(userDto.getId())
                .orElseThrow(() -> new RepositoryException("No such user found with id " + userDto.getId()));
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        ormUserRepository.save(user);
    }

    @Override
    public void addRoleToUser(int rId, int uId) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("user-entity-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        Role role = entityManager.find(Role.class, rId, properties);
        User user = entityManager.find(User.class, uId, properties);

        if(!user.getRoleEntities().contains(role)) {
            user.getRoleEntities().add(role);
            ormUserRepository.save(user);
        }
    }

    @Override
    public void deleteUserById(int id) {
        ormUserRepository.deleteById(id);
    }
}
