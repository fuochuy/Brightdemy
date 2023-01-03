package hcmus.brightdemy.course.controller;

import hcmus.brightdemy.common.ResponseHandler;
import hcmus.brightdemy.constant.ContextPath;
import hcmus.brightdemy.course.dto.CourseDTO;
import hcmus.brightdemy.course.dto.CreateCourseDTO;
import hcmus.brightdemy.course.service.CourseService;
import hcmus.brightdemy.role.dto.CreateRoleDTO;
import hcmus.brightdemy.role.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = ContextPath.Course.PATH)
@CrossOrigin
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping(ContextPath.Course.LIST)
    public Object getCourses(@RequestHeader String authorization) {
        List<CourseDTO> courseDTOS = courseService.findAll();
        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
    }

    @PostMapping(ContextPath.Course.CREATE)
    public Object createCourse(@RequestHeader String authorization,@Valid @RequestBody CreateCourseDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseHandler.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        CourseDTO createdCourse = courseService.create(dto);

        return new ResponseEntity<>(createdCourse, HttpStatus.OK);
    }

    @PostMapping(ContextPath.Course.JOIN)
    public Object joinCourse(@RequestHeader String authorization,@Valid @RequestBody CreateCourseDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseHandler.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        CourseDTO createdCourse = courseService.create(dto);

        return new ResponseEntity<>(createdCourse, HttpStatus.OK);
    }

}
