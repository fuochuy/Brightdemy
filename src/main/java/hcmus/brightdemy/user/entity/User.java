package hcmus.brightdemy.user.entity;

import hcmus.brightdemy.common.entity.BaseEntity;
import hcmus.brightdemy.role.entity.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private static final long serialVersionUID = 4766770617680553069L;
    @Id
    @Column(name = "user_id")
    private int id;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Column(name = "full_name")
    @NotBlank
    private String fullName;

    @Column(name="email")
    @NotBlank
    @Email
    @Size(max =50)
    private String email;

    @Column(name="password")
    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name ="token")
    private String token;

    @Column(name ="status")
    private int status;


//    @Column(name ="end_block_time")
//    private Date endBlockTime;

}