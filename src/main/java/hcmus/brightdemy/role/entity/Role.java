package hcmus.brightdemy.role.entity;

import hcmus.brightdemy.common.entity.BaseEntity;
import hcmus.brightdemy.user.entity.User;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    private static final long serialVersionUID = 4766770617680553069L;

    @Id
    @Column(name = "role_id")
    @GeneratedValue
    private int role_id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "role", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<User> users = new HashSet<User>();
}
