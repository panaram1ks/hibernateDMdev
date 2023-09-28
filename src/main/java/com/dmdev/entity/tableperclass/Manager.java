package com.dmdev.entity.tableperclass;

import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.Role;
import com.dmdev.entity.manytomany.UserChat;
import com.dmdev.entity.onetomany.Company;
import com.dmdev.entity.onetomany.User;
import com.dmdev.entity.onetoone.Profile;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Manager extends User {

    private String projectName;

    @Builder
    public Manager(Long id, PersonalInfo personalInfo, String username, String info, Role role, Company company, Profile profile, List<UserChat> userChats, String projectName) {
        super(id, personalInfo, username, info, role, company, profile, userChats);
        this.projectName = projectName;
    }
}
