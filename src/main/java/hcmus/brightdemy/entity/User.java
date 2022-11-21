package hcmus.brightdemy.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(schema = "users")
public class User {
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name ="token")
    private String token;

    @Column(name ="status")
    private int status;

    @Column(name ="created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name ="updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name ="end_block_time")
    private Date endBlockTime;

}
