package com.gridstudentpractice.chatservice.repository.jdbc;

import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.model.RoleDto;
import com.gridstudentpractice.chatservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Profile("jdbc")
@Repository
public class JDBCRoleRepositoryImpl implements RoleRepository {

    @Autowired
    private Connection connection;

    private static final String selectRoleQuery = "SELECT r.* FROM roles r WHERE r.id = ?;";
    private static final String createRoleQuery = "INSERT INTO roles (name) VALUES (?);";
    private static final String updateRoleQuery = "UPDATE roles r SET name = ? WHERE r.id = ?;";
    private static final String deleteRoleQuery = "DELETE FROM roles r WHERE r.id = ?;";

    @Override
    public void createRole(RoleDto roleDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(createRoleQuery)) {
            preparedStatement.setString(1,roleDto.getName());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RepositoryException("RoleDto creation error", e);
        }
    }

    @Override
    public RoleDto getRoleById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectRoleQuery)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return RoleDto.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .build();
                } else throw new RepositoryException("No such role");
            }
            catch (SQLException e) {
                throw new RepositoryException("ResultSet error", e);
            }
        }
        catch (SQLException e) {
            throw new RepositoryException("RoleDto selection error", e);
        }
    }

    @Override
    public void updateRole(RoleDto roleDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateRoleQuery)) {
            preparedStatement.setString(1, roleDto.getName());
            preparedStatement.setInt(2, roleDto.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RepositoryException("RoleDto update error", e);
        }
    }

    @Override
    public void deleteRoleById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteRoleQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RepositoryException("RoleDto delete error", e);
        }
    }

}
