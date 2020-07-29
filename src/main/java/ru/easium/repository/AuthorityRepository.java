package ru.easium.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.easium.domain.Authority;

import java.util.List;

public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Integer> {

    List<Authority> findAllByUsername(String username);

    Authority findByUsername(String username);

}