package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.mapper.RoleMapper;
import com.gridstudentpractice.chatservice.mapper.UserMapper;
import com.gridstudentpractice.chatservice.model.Role;
import com.gridstudentpractice.chatservice.model.RoleDto;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Profile("orm")
@Repository
public class ORMUserRepositoryImpl implements UserRepository {

    @Lazy
    @Autowired
    private ORMUserRepository ormUserRepository;

    @Lazy@Autowired
    private ORMRoleRepository ormRoleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        Role role = entityManager.find(Role.class, 1);
        user.setRoleEntities(Collections.singleton(role));
        ormUserRepository.save(user);
    }

    @Override
    public UserDto getUserByLogin(String userLogin) {
        return userMapper.toDTO(ormUserRepository.findByLogin(userLogin) );
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

    @Override
    public List<RoleDto> getUserRoles(String userLogin) {
        List<Role> roles = ormRoleRepository.getUserRoles(userLogin);
        return roles.stream()
                .map(role -> roleMapper.toDTO(role))
                .collect(Collectors.toList());
    }
}
