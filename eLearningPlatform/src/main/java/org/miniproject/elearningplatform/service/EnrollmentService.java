package org.miniproject.elearningplatform.service;


import org.miniproject.elearningplatform.model.Enrollment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final List<Enrollment> enrollments;

    public EnrollmentService() {
        this.enrollments = new ArrayList<>();
    }

    public void enrollStudent(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }

    public Optional<Enrollment> getEnrollmentById(String enrollmentId) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getID().equals(enrollmentId))
                .findFirst();
    }

    public void updateEnrollment(Enrollment updatedEnrollment) {
        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment enrollment = enrollments.get(i);
            if (enrollment.getID().equals(updatedEnrollment.getID())) {
                enrollments.set(i, updatedEnrollment);
                break;
            }
        }
    }

    public void deleteEnrollment(String enrollmentId) {
        enrollments.removeIf(enrollment -> enrollment.getID().equals(enrollmentId));
    }

}
