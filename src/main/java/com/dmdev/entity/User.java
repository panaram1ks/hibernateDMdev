package com.dmdev.entity;


import com.dmdev.converter.BirthdayConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
@TypeDef(name = "dmdev", typeClass = JsonBinaryType.class)
//@Access(AccessType.PROPERTY)
public class User {

    //    @Id
//    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
////    @SequenceGenerator(name = "user_gen", sequenceName = "users_id_seq", allocationSize = 1)
//    @TableGenerator(name = "user_gen",
//            table = "all_sequence",
//            allocationSize = 1,
//            pkColumnName = "table_name",
//            valueColumnName = "pk_value")
//    private Long id;

    @EmbeddedId
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Column(unique = true)
    private String username;


    //    JsonBinaryType
//    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonBinaryType")
//    @Type(type = "jsonb")
    @Type(type = "dmdev")
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date date;
    // Java 8 datetimeAPI
//    private LocalDateTime localDateTime;
//    private LocalDate localDate;
//    private LocalTime localTime;






//    public User() {
//    }
//
//    public User(String username, String firstname, String lastname, LocalDate birthDate, Integer age) {
//        this.username = username;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.birthDate = birthDate;
//        this.age = age;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "username='" + username + '\'' +
//                ", firstname='" + firstname + '\'' +
//                ", lastname='" + lastname + '\'' +
//                ", birthDate=" + birthDate +
//                ", age=" + age +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(username, user.username) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(birthDate, user.birthDate) && Objects.equals(age, user.age);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(username, firstname, lastname, birthDate, age);
//    }
}
