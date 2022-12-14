package hcmus.brightdemy.user.dto;

import hcmus.brightdemy.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class UserDTO extends BaseDTO {
    private static final long serialVersionUID = 4766770617680553069L;

    private int id;

    private String username;

    private String fullName;

    private String email;

    private String password;

    private int roleId;

    private String token;
    private int status;

    private Date endBlockTime;
}
