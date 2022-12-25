package hcmus.brightdemy.course.service;

import hcmus.brightdemy.course.dto.CourseDTO;
import hcmus.brightdemy.course.dto.CourseMapper;
import hcmus.brightdemy.course.dto.CreateCourseDTO;
import hcmus.brightdemy.course.entity.Course;
import hcmus.brightdemy.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseRepository courseRepository;
    @Override
    public CourseDTO create(CreateCourseDTO dto) {
        Course course = CourseMapper.INSTANCE.fromCreateCourseDTOtoEntity(dto);
        Course createCourse = courseRepository.save(course);
        return CourseMapper.INSTANCE.fromEntityToCourseDTO(createCourse);
    }

    @Override
    public List<CourseDTO> findAll() {
        List<Course> courses = courseRepository.findAll();

        return courses.stream().map(o -> CourseMapper.INSTANCE.fromEntityToCourseDTO(o))
                .collect(Collectors.toList());
    }
}
