package org.miniproject.elearningplatform.service.impl;

import org.miniproject.elearningplatform.model.Teacher;
import org.miniproject.elearningplatform.model.User;
import org.miniproject.elearningplatform.repository.ITeacherRepository;
import org.miniproject.elearningplatform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements IUserService {

    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public void addUser(User user) {
        if (user instanceof Teacher) {
            teacherRepository.save((Teacher) user);
        } else {
            throw new IllegalArgumentException("User must be an instance of Teacher");
        }
    }

    @Override
    public List<Teacher> getAllUsers() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getUserById(String userId) {
        return teacherRepository.findById(userId);
    }

    @Override
    public void updateUser(User updatedUser) {
        if (!(updatedUser instanceof Teacher)) {
            throw new IllegalArgumentException("User must be an instance of Teacher");
        }
        Teacher updatedTeacher = (Teacher) updatedUser;

        // Check if the teacher exists before updating
        if (teacherRepository.existsById(updatedTeacher.getID())) {
            teacherRepository.save(updatedTeacher);
        } else {
            throw new IllegalArgumentException("Teacher with ID " + updatedTeacher.getID() + " does not exist.");
        }
    }


    @Override
    public void deleteUser(String userId) {
        teacherRepository.deleteById(userId);
    }

}
