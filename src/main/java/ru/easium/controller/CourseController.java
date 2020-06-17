package ru.easium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.easium.MyUserPrincipal;
import ru.easium.domain.Course;
import ru.easium.domain.Teacher;
import ru.easium.repository.CourseRepository;
import ru.easium.service.CourseService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CourseController {

    @Autowired
    CourseService service;
    @Autowired
    CourseRepository repository;

    @RequestMapping(path = "/courses", method = RequestMethod.GET)
    public String allCourses(@RequestParam(defaultValue = "0") Integer pageNo,
                             @RequestParam(defaultValue = "5") Integer pageSize,
                             Model model) {
        Long total = repository.count();
        List<Course> courses = service.getPage(pageNo, pageSize);
        model.addAttribute("courses", courses);
        MyUserPrincipal principal= (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", principal.getUsername());
        int size = (int) Math.ceil((double) total / 5);
        model.addAttribute("pages", new Integer[size]);
        return "courses";
    }

    @GetMapping("/course/add")
    public String addCourse(Model model) {
        return "courseAdd";
    }

    @PostAuthorize("#username=authentication.principal.username")
//    @PreAuthorize("#username=authentication.principal.username")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
//    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @PostMapping("/course/save")
    public String saveCourse(@RequestParam String name, @RequestParam int duration) {
        service.saveCourse(new Course(name, duration));
        return "redirect:/courses";
    }
}
