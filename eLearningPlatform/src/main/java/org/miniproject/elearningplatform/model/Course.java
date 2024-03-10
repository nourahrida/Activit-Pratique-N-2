package org.miniproject.elearningplatform.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.miniproject.elearningplatform.util.AutoIdGenerator;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Course {
    // Constant
    protected final String TeacherUser_Violation_Exception_Message = "The user you are trying to add is not a user with type 'Teacher'. Please verify and try again.";
    @Getter
    @Column
    @Id
    protected String ID;
    @Column()
    protected String name;
    @Column()
    protected LocalDate startDate;
    @Column()
    @Getter
    protected LocalDate endDate;
    @Column()
    protected int durationInWeeks;
    @Column()
    protected String description;

    // One to Many -> A Course can be taught by many teachers
    @Column
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    protected List<Teacher> teachers;

    @Column
    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments = new HashSet<>();


    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        // Calculating the week count between the start date and the end date
        Period period = Period.between(startDate, endDate);
        this.durationInWeeks = (int) (period.toTotalMonths() * 4 + period.getDays() / 7);
    }

    public Course(String name, LocalDate startDate, LocalDate endDate, int durationInWeeks, String description) {
        this.ID = AutoIdGenerator.generateAutoId(name);
        this.setName(name);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setDurationInWeeks(durationInWeeks);
        this.setDescription(description);
    }

}
