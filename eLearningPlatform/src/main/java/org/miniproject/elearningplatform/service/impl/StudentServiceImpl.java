package org.miniproject.elearningplatform.service.impl;

import org.miniproject.elearningplatform.model.Student;
import org.miniproject.elearningplatform.model.Teacher;
import org.miniproject.elearningplatform.model.User;
import org.miniproject.elearningplatform.service.IUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentServiceImpl implements IUserService {

    private final List<Student> students = new ArrayList<>();

    @Override
    public void addUser(User user) {
        if (user instanceof Student) {
            students.add((Student) user);
        } else {
            throw new IllegalArgumentException("User must be an instance of Student");
        }
    }

    @Override
    public List<Teacher> getAllUsers() {
        // Cast each Student back to User for compatibility with the interface
        return new ArrayList<>(students);
    }

    @Override
    public Optional<Teacher> getUserById(String userId) {
        return students.stream()
                .filter(student -> student.getID().equals(userId))
                .findFirst()
                .map(student -> (User) student);
    }

    @Override
    public void updateUser(User updatedUser) {
        if (!(updatedUser instanceof Student)) {
            throw new IllegalArgumentException("User must be an instance of Student");
        }
        Student updatedStudent = (Student) updatedUser;
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getID().equals(updatedStudent.getID())) {
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    @Override
    public void deleteUser(String userId) {
        students.removeIf(student -> student.getID().equals(userId));
    }

    @Override
    public List<User> searchUsers(String keyword) {
        // Filter students by keyword match against their information
        List<Student> searchResults = students.stream()
                .filter(student -> student.getFname().contains(keyword) || student.getLname().contains(keyword) || student.getEmail().contains(keyword))
                .collect(Collectors.toList());
        return new ArrayList<>(searchResults);
    }
}
