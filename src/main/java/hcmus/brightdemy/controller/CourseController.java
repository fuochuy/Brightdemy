package hcmus.brightdemy.controller;

import hcmus.brightdemy.constant.ContextPath;
import hcmus.brightdemy.entity.Response;
import hcmus.brightdemy.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(ContextPath.Course.PATH)
public class CourseController {

    @Autowired
    CourseService service;

    @GetMapping(value = ContextPath.Course.LIST)
    public ResponseEntity<?> getListCourse() {
        Response response = Response.builder()
                .code(200)
                .message("success")
                .data(service.getList())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
