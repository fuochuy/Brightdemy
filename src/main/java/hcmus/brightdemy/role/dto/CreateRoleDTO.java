package hcmus.brightdemy.role.dto;

import hcmus.brightdemy.role.validation.annotation.UniqueRoleId;
import hcmus.brightdemy.role.validation.annotation.UniqueRoleName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateRoleDTO {
    @UniqueRoleId
    private String roleId;

    @NotBlank(message = "{loai_nguoi_dung.ten.not_blank}")
    @Size(min = 3, max = 30, message = "{role.name.size}")
    @UniqueRoleName
    private String name;
}
