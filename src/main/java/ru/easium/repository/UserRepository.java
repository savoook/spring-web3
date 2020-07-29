package ru.easium.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.easium.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

}