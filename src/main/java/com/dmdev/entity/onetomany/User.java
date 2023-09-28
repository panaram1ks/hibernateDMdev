package com.dmdev.entity.onetomany;

import com.dmdev.entity.BaseEntityInterface;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.Role;
import com.dmdev.entity.manytomany.UserChat;
import com.dmdev.entity.onetoone.Profile;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")// not required
    private Company company;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL, // CascadeType.ALL -> profile will automatic save if user save!
            fetch = FetchType.LAZY // doesn't work because Hibernate should do request to know id of profile
//            , optional = false
    ) // optional = false make LazyInitialization not fetchType
    private Profile profile;


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

//    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.getUsername());
    }
}
