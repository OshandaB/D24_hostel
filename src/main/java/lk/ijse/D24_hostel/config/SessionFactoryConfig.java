package lk.ijse.D24_hostel.config;

import lk.ijse.D24_hostel.entity.Reservation;
import lk.ijse.D24_hostel.entity.Room;
import lk.ijse.D24_hostel.entity.Student;
import lk.ijse.D24_hostel.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig sessionFactoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {

        Properties properties = new Properties();
        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties");
            if (inputStream == null) {
                System.out.println("Resource not found!");
            } else {
                properties.load(inputStream);
                System.out.println("Resource loaded successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        sessionFactory = new Configuration()
                .setProperties(properties).
                        addAnnotatedClass(Student.class).
                        addAnnotatedClass(Room.class).
                        addAnnotatedClass(Reservation.class).
                        addAnnotatedClass(User.class).
                        buildSessionFactory();

    }

    public static SessionFactoryConfig getSessionFactoryConfig() {
        return (sessionFactoryConfig == null) ? sessionFactoryConfig = new SessionFactoryConfig() : sessionFactoryConfig;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
