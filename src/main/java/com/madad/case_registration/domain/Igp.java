package com.madad.case_registration.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "igp")
public class Igp {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "igp_generator")
    @SequenceGenerator(name="igp_generator", sequenceName = "igp_sequence")
    @Column(name = "id",nullable = false, updatable = false, insertable = false)
    private long id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "province", nullable = false)
    public String province;

    //Mapping
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "igp")
    @JsonBackReference
    private List<MissingChildCase> casess;
}