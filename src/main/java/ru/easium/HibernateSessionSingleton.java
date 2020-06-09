package ru.easium;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.easium.domain.Course;
import ru.easium.domain.Student;

public class HibernateSessionSingleton {
    private static SessionFactory sessionFactory;

    private HibernateSessionSingleton() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.configure();
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Initialization exception " + e.getMessage());
            }
        }
        return sessionFactory;
    }
}
