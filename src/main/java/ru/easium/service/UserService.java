package ru.easium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.easium.domain.Authority;
import ru.easium.repository.AuthorityRepository;
import ru.easium.repository.UserRepository;
import ru.easium.domain.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthorityRepository authorityRepository;

    public User findUser(Long id) {
        return repository.findById(id).get();
    }

    public List<User> findAllUsers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveUser(User user, Authority authority) {
        repository.save(user);
        authorityRepository.save(authority);
    }

    @Transactional
    public void deleteUser(User user, Authority authority) {
        authorityRepository.delete(authority);
        repository.delete(user);
    }

    public User findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<User> getPage(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<User> pageCourses = repository.findAll(paging);
        if(pageCourses.hasContent()) {
            return pageCourses.getContent();
        } else {
            return new ArrayList<User>();
        }
    }
}