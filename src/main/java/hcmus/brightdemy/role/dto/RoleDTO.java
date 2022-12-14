package hcmus.brightdemy.role.dto;

import hcmus.brightdemy.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO extends BaseDTO {
    private static final long serialVersionUID = 5557389516229161143L;

    private int roleId;
    private String name;
    private String description;
}

