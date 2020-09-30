package com.madad.case_registration.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "MissingChildCase")
@Data
public class MissingChildCase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_generator")
    @SequenceGenerator(name="case_generator", sequenceName = "case_sequence")
    @Column(name = "id",nullable = false, updatable = false, insertable = false)
    private long id;
    @Column(name = "regDateTime")
    private LocalDateTime regDateTime;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name="dateOfBirth")
    private LocalDate dateOfBirth;
    @Column(name = "language", nullable = false)
    private String language;
    @Column(name = "height", nullable = false)
    private double height;
    @Column(name = "weight", nullable = false)
    private float weight;
    @Column(name = "eyecolor", nullable = false)
    private String eyeColor;
    @Column(name = "haircolor", nullable = false)
    private String hairColor;
    @Column(name = "lastSeenProvince", nullable = false)
    private String lastSeenProvince;
    @Column(name = "lastSeenTime")
    private LocalTime lastSeenTime;
    @Column(name = "lastSeenDate")
    private LocalDate lastSeenDate;
    @Column(name = "lastSeenClothes", nullable = false)
    private String lastSeenClothes;
    @Transient
    private String dateOfBirthInString;
    @Transient
    private String lastSeenDateInString;

//  @Column(name = "childImage", nullable = false)
////    @Lob
////    @Basic(fetch = FetchType.LAZY)
////    private byte[] childImage;

    //Mapping with User Table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")

    public User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //Mapping with Igp Table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "igpId")

    public Igp igp;

    public Igp getIgp() {
        return igp;
    }

    public void setIgp(Igp igp) {
        this.igp = igp;
    }
}
