package org.miniproject.elearningplatform.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.miniproject.elearningplatform.exception.EmailValidationException;
import org.miniproject.elearningplatform.exception.PhoneNumberValidationException;
import org.miniproject.elearningplatform.model.submodel.UserAddress;
import org.miniproject.elearningplatform.model.submodel.UserPhoneNumber;
import org.miniproject.elearningplatform.util.AgeCalculator;
import org.miniproject.elearningplatform.util.AutoIdGenerator;
import org.miniproject.elearningplatform.util.EmailValidator;
import org.miniproject.elearningplatform.util.PhoneNumberValidator;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    // Constants
    @Transient
    protected final String Email_Validation_Exception_Message = "The email that has been entered is not valid, Please verify it!";
    @Transient
    protected final String PhoneNumber_Validation_Exception_Message = "The phone number that has been entered is not valid, Please verify it!";
    @Getter
    @Column
    @Id
    protected String ID;
    @Column()
    @Getter
    @Setter
    protected String fname;
    @Column()
    @Getter
    @Setter
    protected String lname;
    @Column()
    @Getter
    protected String email;
    @Column()
    @Getter
    protected LocalDate birthdate;
    @Column()
    @Getter
    protected int age;
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userAddress")
    protected UserAddress address;
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userPhoneNumber")
    protected UserPhoneNumber phoneNumber;

    public void setEmail(String email) throws EmailValidationException {
        if(EmailValidator.isValidEmail(email)){
            this.email = email;
        }else{
            throw new EmailValidationException(Email_Validation_Exception_Message);
        }
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        // calculting the age
        this.age = AgeCalculator.calculateAge(this.getBirthdate());
    }

    public void setPhoneNumber(UserPhoneNumber phoneNumber) throws PhoneNumberValidationException {
        if(PhoneNumberValidator.isValidPhoneNumber(phoneNumber)){
            this.phoneNumber = phoneNumber;
        }else{
            throw new PhoneNumberValidationException(PhoneNumber_Validation_Exception_Message);
        }
    }

    public User(String fname, String lname, String email, LocalDate birthdate, UserAddress address, UserPhoneNumber phoneNumber) throws EmailValidationException, PhoneNumberValidationException {
        this.ID = AutoIdGenerator.generateAutoId(fname, lname);
        this.setFname(fname);
        this.setLname(lname);
        this.setEmail(email);
        this.setBirthdate(birthdate);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
    }
    @PrePersist
    protected void onPrePersist() {
        if (this.ID == null || this.ID.isEmpty()) {
            this.ID = AutoIdGenerator.generateAutoId(this.fname, this.lname);
        }
    }
}