package ru.easium.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.easium.MyUserPrincipal;
import ru.easium.domain.User;
import ru.easium.repository.UserRepository;
import ru.easium.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService service;
    @Autowired
    UserRepository repository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String allUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                           @RequestParam(defaultValue = "5") Integer pageSize,
                           Model model) {
        if (logger.isInfoEnabled()) {
            logger.info("Page number=" + pageNo + "page size=" + pageSize);
        }
        Long total = repository.count();
        List<User> users = service.getPage(pageNo, pageSize);
        model.addAttribute("Users", users);
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", principal.getUsername());
        int size = (int) Math.ceil((double) total / 5);
        model.addAttribute("pages", new Integer[size]);
        return "Users";
    }

    @GetMapping("/user/add")
    public String addUser(Model model) {
        return "UserAdd";
    }

//    @PostAuthorize("#username=authentication.principal.username")
//    @PreAuthorize("#username=authentication.principal.username")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @PostMapping(path = "/user/save")
    public String saveUser(@RequestParam String login, @RequestParam String password) {
        service.saveUser(new User(login, passwordEncoder.encode(password)));
        return "redirect:/users";
    }
}
