package ru.easium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.easium.domain.Course;
import ru.easium.domain.User;
import ru.easium.domain.Teacher;
import ru.easium.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User findUser(long id) {
        return repository.findById(id).get();
    }

    public List<User> findAllUsers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public User saveUser(User user) {
        return repository.save(user);
    }
    public User findUserByUserName(String userName) {
        return repository.findByUsername(userName);

    }

    public List<User> getPage(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<User> userPage = repository.findAll(paging);
        if (userPage.hasContent()) {
            return userPage.getContent();
        } else {
            return new ArrayList<User>();
        }
    }
}