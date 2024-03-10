package org.miniproject.elearningplatform.service;

import org.miniproject.elearningplatform.model.Teacher;
import org.miniproject.elearningplatform.model.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    void addUser(User user);
    List<Teacher> getAllUsers();
    Optional<Teacher> getUserById(String userId);
    void updateUser(User updatedUser);
    void deleteUser(String userId);
    List<User> searchUsers(String keyword);
}
