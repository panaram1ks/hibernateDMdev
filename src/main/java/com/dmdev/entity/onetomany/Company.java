package com.dmdev.entity.onetomany;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //    @OneToMany
//    @JoinColumn(name = "company_id")
    @OneToMany(mappedBy = "company")
    private List<User> users;

}
