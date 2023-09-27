package com.dmdev.entity.onetomany;

import com.dmdev.entity.LocaleInfo;
import lombok.*;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.*;

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

//    //    @OneToMany
////    @JoinColumn(name = "company_id")
////    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
//    @Builder.Default
//    @OneToMany(mappedBy = "company", orphanRemoval = true)
////    @org.hibernate.annotations.OrderBy(clause = "username DESC, lastname ASC") // usually use SQL
//    @OrderBy(value = "username DESC, personalInfo.lastname ASC") // use HQL
////    @OrderColumn // only with collection no Set, value should be INT
//    private Set<User> users = new HashSet<>();

//    @Builder.Default
//    @OneToMany(mappedBy = "company", orphanRemoval = true)
//    @OrderColumn(name = "id")
//    private List<User> users = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "company", orphanRemoval = true)
    @SortNatural
    private Set<User> users = new TreeSet<>();

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
