package hcmus.brightdemy.role.dto;

import hcmus.brightdemy.role.validation.annotation.UniqueRoleId;
import hcmus.brightdemy.role.validation.annotation.UniqueRoleName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateRoleDTO {
    private static final long serialVersionUID = 4766770617680553069L;
    @NotBlank(message = "{loai_nguoi_dung.ten.not_blank}")
    @Size(min = 3, max = 30, message = "{role.name.size}")
    @UniqueRoleName
    private String name;

    @NotBlank(message = "{loai_nguoi_dung.ten.not_blank}")
    @Size(min = 3, max = 30, message = "{role.name.size}")
    @UniqueRoleName
    private String description;
}
