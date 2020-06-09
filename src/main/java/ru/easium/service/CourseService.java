package ru.easium.service;

import javafx.print.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.easium.domain.Course;
import ru.easium.domain.Teacher;
import ru.easium.repository.CourseRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;

    public Course findById(int id) {
        return repository.findById(id).get();
    }

    public List<Course> findAllCourses() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Course saveCourse(Course course) {
        return repository.save(course);
    }

    public List<Teacher> getTeacherByCourseName(String courseName) {
        List<Course> courses = repository.findAllByName(courseName);
        return courses.stream().map(c -> c.getTeacher()).collect(Collectors.toList());
    }

    public List<Course> getPage(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Course> coursePage = repository.findAll(paging);
        if (coursePage.hasContent()) {
            return coursePage.getContent();
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}

