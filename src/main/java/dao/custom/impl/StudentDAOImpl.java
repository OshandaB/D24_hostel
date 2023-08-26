package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Session session;

    @Override
    public void save(Student entity) throws SQLException {
       session.save(entity);

    }

    @Override
    public ArrayList<Student> getAll() throws SQLException {
        try {
            List<Student> studentList = session.createNativeQuery("Select * from Student", Student.class).getResultList();
            return (ArrayList<Student>) studentList;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Student entity) throws SQLException {
session.delete(entity);
    }

    @Override
    public void update(Student entity) throws SQLException {
        session.update(entity);
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException {
        String sql = "SELECT S.studentId FROM Student AS S";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return (ArrayList<String>) list;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
