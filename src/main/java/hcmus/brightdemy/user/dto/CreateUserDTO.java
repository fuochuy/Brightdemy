package hcmus.brightdemy.user.dto;

import hcmus.brightdemy.role.entity.Role;
import hcmus.brightdemy.role.validation.annotation.ExistedRoleId;
import hcmus.brightdemy.user.validation.annotation.UniqueEmail;
import hcmus.brightdemy.user.validation.annotation.UniqueUsername;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class CreateUserDTO {
    private static final long serialVersionUID = 4766770617680553069L;

    @NotBlank(message = "{user.username.not-blank}")
    @Size(min = 3, max = 30, message = "{user.username.size}")
    @UniqueUsername
    private String username;

    @NotBlank
    @Size(min = 3, max = 30, message = "{user.full_name.size}")
    private String fullName;

    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;
    @NotBlank(message = "{user.roleId.not_blank}")
    @ExistedRoleId
    private int roleId;

}
