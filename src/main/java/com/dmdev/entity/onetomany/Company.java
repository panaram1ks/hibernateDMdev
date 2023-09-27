package com.dmdev.entity.onetomany;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = "users")
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    //    @OneToMany
//    @JoinColumn(name = "company_id")
//    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @Builder.Default
    @OneToMany(mappedBy = "company", orphanRemoval = true)
    private Set<User> users = new HashSet<>();

    public void addUser(User user){
        users.add(user);
        user.setCompany(this);
    }

}
