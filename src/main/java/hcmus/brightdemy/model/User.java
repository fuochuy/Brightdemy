package hcmus.brightdemy.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;;
import java.util.Date;
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
    @Column(name = "id")
    private int id;

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

    @Column(name = "role")
    @NotBlank
    private String role;

    @Column(name ="token")
    private String token;

    @Column(name ="is_block")
    private boolean isBlock;

    @Column(name ="created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name ="updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name ="end_block_time")
    private Date endBlockTime;

}
