package ru.easium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.easium.domain.Student;
import ru.easium.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public Student findStudent(int id) {
        return repository.findById(id).get();
    }

    public List<Student> findAllStudent() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void saveStudent(Student student) {
        repository.save(student);
    }
}
