package com.dmdev.entity.manytomany;

import com.dmdev.entity.BaseEntity;
import com.dmdev.entity.onetomany.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_chat")
public class UserChat extends BaseEntity<Long> {

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "added_by")
    private String addedBy;

    @ManyToOne
//    @JoinColumn(name ="user_id") // NOT REQUIRED
    private User user;

    @ManyToOne
//    @JoinColumn(name = "chat_id") // NOT REQUIRED
    private Chat chat;

    public void setUser(User user){
        this.user = user;
        this.user.getUserChats().add(this);
    }

    public void setChat(Chat chat){
        this.chat = chat;
        this.chat.getUserChats().add(this);
    }

}
