package com.dmdev.entity.onetomany;

import com.dmdev.entity.BaseEntityInterface;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.Role;
import com.dmdev.entity.manytomany.UserChat;
import com.dmdev.entity.onetoone.Profile;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.dmdev.util.StringUtils.SPACE;

@NamedQuery(name = "findUserByNameAndCompanyName", query = "SELECT u FROM User u " +
        "JOIN u.company c " +
        "WHERE u.personalInfo.firstname = :firstname AND c.name = :companyName " +
        "ORDER BY u.personalInfo.lastname ASC")

@Data
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "profile", "userChats" /*"chats"*/})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
@TypeDef(name = "dmdev", typeClass = JsonBinaryType.class)
//@Inheritance(strategy = InheritanceType.JOINED)
@FetchProfile(name = "withCompany", fetchOverrides = {
        @FetchProfile.FetchOverride(entity = User.class, association = "company", mode = FetchMode.JOIN)
})
@FetchProfile(name = "withCompanyAndPayment", fetchOverrides = {
        @FetchProfile.FetchOverride(entity = User.class, association = "company", mode = FetchMode.JOIN),
        @FetchProfile.FetchOverride(entity = User.class, association = "payments", mode = FetchMode.JOIN)
})
@NamedEntityGraph(name = "WithCompanyAndChat", attributeNodes = {@NamedAttributeNode("company"),@NamedAttributeNode(value = "userChats", subgraph = "chats")},
        subgraphs = {@NamedSubgraph(name = "chats", attributeNodes = @NamedAttributeNode("chat"))}
)
@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Comparable<User>, BaseEntityInterface<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Column(unique = true)
    private String username;

    @Type(type = "dmdev")
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Bidirectional relation(when we already have ManyToOne relation) if we don't have company here it's Onedirectional relation
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")// not required
    private Company company;

//    @OneToOne(mappedBy = "user",
//            cascade = CascadeType.ALL, // CascadeType.ALL -> profile will automatic save if user save!
//            fetch = FetchType.LAZY // doesn't work because Hibernate should do request to know id of profile
////            , optional = false
//    ) // optional = false make LazyInitialization not fetchType
//    private Profile profile;


    // First var
//    @Builder.Default
//    @ManyToMany
//    @JoinTable(name = "users_chat",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "chat_id")
//    )
//    private Set<Chat> chats = new HashSet<>();
//
//    public void addChat(Chat chat){
//        chats.add(chat);
//        chat.getUsers().add(this);
//    }

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Users")
    @NotAudited
    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserChat> userChats = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.getUsername());
    }


    @NotAudited
    @Builder.Default
//    @BatchSize(size = 3)
//    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private Set<Payment> payments = new HashSet<>();

    public String fullName() {
        return getPersonalInfo().getFirstname() + SPACE + getPersonalInfo().getLastname();
    }
}
