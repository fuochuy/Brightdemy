package hcmus.brightdemy.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    private static final long serialVersionUID = 4766770617680553069L;


    private String username;


    private String fullName;


    private String email;


    private String password;

}
