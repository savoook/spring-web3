package ru.easium.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.easium.domain.Teacher;

import java.util.List;

public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Integer> {

    List<Teacher> findAllByFio(String fio);

    @Query("select t from Teacher t where t.about like %?1%")
    List<Teacher> findByAboutPartialMatch(String about);

}
