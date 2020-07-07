package ru.easium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.easium.domain.Authority;
import ru.easium.domain.User;
import ru.easium.repository.AuthorityRepository;
import ru.easium.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    private AuthorityRepository authorityRepository;

    public User findUser(long id) {
        return repository.findById(id).get();
    }

    public List<User> findAllUsers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void saveUser(User user, Authority authority) {
        repository.save(user);
        authorityRepository.save(authority);
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