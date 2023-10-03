package com.dmdev.listener;

import com.dmdev.entity.manytomany.Chat;
import com.dmdev.entity.manytomany.UserChat;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;

public class UserChatListener {

    @PostPersist
    public void postPersist(UserChat uc) {
        Chat chat = uc.getChat();
        chat.setCount(chat.getCount() + 1);
    }
    @PostRemove
    public void postRemove(UserChat uc) {
        Chat chat = uc.getChat();
        chat.setCount(chat.getCount() - 1);
    }
}
