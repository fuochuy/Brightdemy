package hcmus.brightdemy.course.dto;

import hcmus.brightdemy.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JoinCourseDTO extends BaseDTO {
    private int id;
    private int courseId;
    private int userId;
    private String role;
    private int status;
    private LocalDateTime trainingTime;
}
