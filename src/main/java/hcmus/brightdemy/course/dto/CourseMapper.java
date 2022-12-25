package hcmus.brightdemy.course.dto;

import hcmus.brightdemy.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    CourseDTO fromEntityToCourseDTO(Course course);

    Course fromCourseDTOToEntity (CourseDTO dto);

    Course fromCreateCourseDTOtoEntity (CreateCourseDTO dto);
}
