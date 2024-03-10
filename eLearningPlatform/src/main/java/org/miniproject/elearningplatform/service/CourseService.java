package org.miniproject.elearningplatform.service;

import org.miniproject.elearningplatform.model.Course;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final List<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> getCourseById(String courseId) {
        return courses.stream()
                .filter(course -> course.getID().equals(courseId))
                .findFirst();
    }

    public void updateCourse(Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (course.getID().equals(updatedCourse.getID())) {
                courses.set(i, updatedCourse);
                break;
            }
        }
    }

    public void deleteCourse(String courseId) {
        courses.removeIf(course -> course.getID().equals(courseId));
    }


    public List<Course> searchCourses(String keyword) {
        List<Course> searchResults = new ArrayList<>();
        for (Course course : courses) {
            if (course.getName().contains(keyword) || course.getDescription().contains(keyword)) {
                searchResults.add(course);
            }
        }
        return searchResults;
    }
}
