package org.miniproject.elearningplatform.service;

import org.miniproject.elearningplatform.model.Student;
import org.miniproject.elearningplatform.repository.IStudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public List<Student> getAllStudents() {
        // Cast each Student back to User for compatibility with the interface
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String userId) {
        return studentRepository.findById(userId);
    }

    public void updateStudent(Student updatedUser) {
        if (studentRepository.findById(updatedUser.getID()).isPresent()) {
            studentRepository.save(updatedUser);
        }
    }

    public void deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
    }

}
