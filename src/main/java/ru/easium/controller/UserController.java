package ru.easium.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.easium.MyUserPrincipal;
import ru.easium.domain.Authority;
import ru.easium.domain.User;
import ru.easium.repository.AuthorityRepository;
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
    @Autowired
    AuthorityRepository authorityRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String allUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                           @RequestParam(defaultValue = "5") Integer pageSize,
                           Model model) {
        if (logger.isInfoEnabled()) {
            logger.info("Page number = " + pageNo + " page size = " + pageSize);
        }
        Long total = repository.count();
        List<User> users = service.getPage(pageNo, pageSize);
        model.addAttribute("users", users);
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", principal.getUsername());
        model.addAttribute("pages", new Integer[(int) Math.ceil((double) total / 5)]);
        return "users";
    }

    @GetMapping(path = "/user/add")
    public String addUser(Model model) {
        //TODO fill model with roles from db
        return "userAdd";
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @PostMapping(path = "/user/save")
    public String saveUser(@RequestParam String login, @RequestParam String password, @RequestParam String confirmedPassword,
                           @RequestParam String role, @RequestParam Boolean enabled) {
        if (!password.equals(confirmedPassword)) {
            return "redirect:/user/add?confirmedError=true";
        }
        Authority authority = new Authority();
        authority.setAuthority(role);
        authority.setUsername(login);
        User user = new User();
        user.setUsername(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(enabled);
        service.saveUser(user, authority);
        return "redirect:/users";
    }

    @PostMapping(path = "/user/delete")
    public String deleteUser(@RequestParam String username) {
        User user = repository.findByUsername(username);
        Authority authority = authorityRepository.findByUsername(username);
        service.deleteUser(user, authority);
        return "redirect:/users";
    }

}
