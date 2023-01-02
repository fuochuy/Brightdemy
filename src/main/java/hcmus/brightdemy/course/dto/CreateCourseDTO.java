package hcmus.brightdemy.course.dto;

import hcmus.brightdemy.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateCourseDTO extends BaseDTO {
    private String name;

    private String description;

    private int ownerId;

    private int status;

    private LocalDateTime openTime;

    private LocalDateTime trainingTime;

    private String language;

    private String framework;

    private String position;
}
