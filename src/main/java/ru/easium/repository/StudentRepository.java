package ru.easium.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.easium.domain.Student;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

    List<Student> findAllByFio(String fio);

    @Query("select s from Student s where s.fio like %?1%")
    List<Student> findByAboutPartialMatch(String fio);

}
