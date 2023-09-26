package com.dmdev.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

//@Data
//@ToString(exclude = "company")
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "users", schema = "public")
//@TypeDef(name = "dmdev", typeClass = JsonBinaryType.class)
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

//    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
//    private PersonalInfo personalInfo;
//
//    @Column(unique = true)
//    private String username;
//
////    @Type(type = "dmdev")
////    private String info;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    //    @ManyToOne(optional = false, fetch = FetchType.LAZY)
////    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    @JoinColumn(name = "company_id")// not required
//    private Company company;

}
