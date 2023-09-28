package com.dmdev.entity.tableperclass;


import com.dmdev.entity.BaseEntity;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.Role;
import com.dmdev.entity.manytomany.UserChat;
import com.dmdev.entity.onetomany.Company;
import com.dmdev.entity.onetomany.User;
import com.dmdev.entity.onetoone.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@PrimaryKeyJoinColumn(name = "id")
public class Programmer extends BaseEntity<Long> {

//    @Enumerated(EnumType.STRING)
    private Language language;

//    @Builder
//    public Programmer(Long id, PersonalInfo personalInfo, String username, String info, Role role, Company company, Profile profile, List<UserChat> userChats, Language language) {
//        super(id, personalInfo, username, info, role, company, profile, userChats);
//        this.language = language;
//    }
}
