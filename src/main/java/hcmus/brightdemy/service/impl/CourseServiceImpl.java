package hcmus.brightdemy.service.impl;

import hcmus.brightdemy.model.Course;
import hcmus.brightdemy.repository.CourseRepository;
import hcmus.brightdemy.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository repository;

    @Override
    public List<Course> getList() {
        return repository.findAll();
    }
}
