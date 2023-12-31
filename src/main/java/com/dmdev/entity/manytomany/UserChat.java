package com.dmdev.entity.manytomany;

import com.dmdev.entity.AuditableEntity;
import com.dmdev.entity.BaseEntity;
import com.dmdev.entity.onetomany.User;
import com.dmdev.listener.UserChatListener;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "users_chat")
@EntityListeners(UserChatListener.class)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserChat extends AuditableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name ="user_id") // NOT REQUIRED
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "chat_id") // NOT REQUIRED
    private Chat chat;

    public void setUser(User user) {
        this.user = user;
        this.user.getUserChats().add(this);
    }

    public void setChat(Chat chat) {
        this.chat = chat;
        this.chat.getUserChats().add(this);
    }

}
