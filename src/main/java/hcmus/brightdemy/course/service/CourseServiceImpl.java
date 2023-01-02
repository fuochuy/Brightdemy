package hcmus.brightdemy.course.service;

import hcmus.brightdemy.common.exception.InvalidDataException;
import hcmus.brightdemy.course.dto.CourseDTO;
import hcmus.brightdemy.course.dto.CourseMapper;
import hcmus.brightdemy.course.dto.CreateCourseDTO;
import hcmus.brightdemy.course.entity.Course;
import hcmus.brightdemy.course.repository.CourseRepository;
import hcmus.brightdemy.user.entity.User;
import hcmus.brightdemy.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public CourseDTO create(CreateCourseDTO dto) {
        Course course = CourseMapper.INSTANCE.fromCreateCourseDTOtoEntity(dto);
        Course createCourse = courseRepository.save(course);
        return CourseMapper.INSTANCE.fromEntityToCourseDTO(createCourse);
    }

    @Override
    public List<CourseDTO> findAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for(Course c:courses){
            Optional<User> userOpt =  userRepository.findById(c.getOwnerId());
            if(!userOpt.isPresent()){
                throw new InvalidDataException("User is not existed. ");
            }
            CourseDTO courseDTO = CourseMapper.INSTANCE.fromEntityToCourseDTO(c);
            courseDTO.setOwnerName(userOpt.get().getFullName());
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }
}
