package com.madad.case_registration.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "user_sequence")
    @Column(name = "id",nullable = false, updatable = false, insertable = false)
    private long id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "cnic", nullable = false)
    private String cnic;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "province", nullable = false)
    private String province;
    @Column(name = "district", nullable = false)
    private String district;

      //Mapping
       @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
       @JsonBackReference
       private List<MissingChildCase> cases;

}
