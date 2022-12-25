package hcmus.brightdemy.course.service;

import hcmus.brightdemy.course.dto.CourseDTO;
import hcmus.brightdemy.course.dto.CreateCourseDTO;

import java.util.List;

public interface CourseService {
    CourseDTO create(CreateCourseDTO dto);
    List<CourseDTO> findAll();
}
