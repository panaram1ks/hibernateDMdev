package com.dmdev.entity.onetomany;

import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.Role;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "username")
@ToString(exclude = "company")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
@TypeDef(name = "dmdev", typeClass = JsonBinaryType.class)
public class User {

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

}
