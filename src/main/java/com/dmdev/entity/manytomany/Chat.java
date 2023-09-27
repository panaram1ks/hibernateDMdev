package com.dmdev.entity.manytomany;

import com.dmdev.entity.onetomany.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "name")
@ToString(exclude = "userChats" /*"users"*/)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // First var
//    @Builder.Default
//    @ManyToMany(mappedBy = "chats") // User is  more important entity in our logic
//    private Set<User> users = new HashSet<>();
//
//
//    public void addUser(User user) {
//        users.add(user);
//        user.addChat(this);
//    }

    @Builder.Default
    @OneToMany(mappedBy = "chat")
    private Set<UserChat> userChats = new HashSet<>();

}
