package ru.easium.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.easium.domain.Course;

import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

    List<Course> findAllByName(String name);

    @Query("select c from Course c where c.name like %?1%")
    List<Course> findByAboutPartialMatch(String name);


}
