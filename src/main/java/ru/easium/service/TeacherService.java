package ru.easium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.easium.domain.Teacher;
import ru.easium.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository repository;

    public Teacher findTeacher(int id) {
        return repository.findById(id).get();
    }

    public List<Teacher> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void saveTeacher(Teacher teacher) {
        repository.save(teacher);
    }

    public List<Teacher> findTeacherByFio(String fio) {
        return repository.findAllByFio(fio);
    }

    public List<Teacher> findTeacherByAbout(String about) {
        return repository.findByAboutPartialMatch(about);
    }
}
