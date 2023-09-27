package com.dmdev.entity.onetomany;

import com.dmdev.entity.LocaleInfo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
//    @org.hibernate.annotations.OrderBy(clause = "username DESC, lastname ASC") // usually use SQL
    @OrderBy(value = "username DESC, personalInfo.lastname ASC") // use HQL
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        users.add(user);
        user.setCompany(this);
    }

    @Builder.Default
    @ElementCollection
//    @CollectionTable(name = "company_locale") // like @Table but for collections
    @CollectionTable(name = "company_locale", joinColumns = @JoinColumn(name = "company_id"))
    // if id in Company has different name
//    @AttributeOverride(name= "lang", @Column(name = "language")) // if names of field in embedded class is different than in table db
    private List<LocaleInfo> locales = new ArrayList<>();
//    @Column(name = "description") // only for справочник, and only for read!!!
//    private List<String> descriptions;

}
