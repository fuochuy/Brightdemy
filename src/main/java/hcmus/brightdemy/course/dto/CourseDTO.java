package hcmus.brightdemy.course.dto;

import hcmus.brightdemy.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CourseDTO extends BaseDTO {
    private static final long serialVersionUID = 5557389516229161143L;
    private int id;
    private String name;
    private String description;

    private int ownerId;

    private int status;

    private LocalDateTime openTime;

    private LocalDateTime trainingTime;
}
