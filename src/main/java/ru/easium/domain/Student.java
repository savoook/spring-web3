package ru.easium.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "birth")
    private Date birthDate;
    @Column(name = "score")
    private long score;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Course> courses;

    public Student() {
    }

    public Student(String fio, Date birthDate, long score, List<Course> courses) {
        this.fio = fio;
        this.birthDate = birthDate;
        this.score = score;
        this.courses = courses;
    }
}
