package org.miniproject.elearningplatform.repository;

import org.miniproject.elearningplatform.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnrollmentRepository extends JpaRepository<Enrollment, String> {
}
