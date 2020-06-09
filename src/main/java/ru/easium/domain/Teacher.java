package ru.easium.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "about")
    private String about;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(String fio, String about, List<Course> courses) {
        this.fio = fio;
        this.about = about;
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", about='" + about + '\'' +
                ", courses=" + courses +
                '}';
    }
}

