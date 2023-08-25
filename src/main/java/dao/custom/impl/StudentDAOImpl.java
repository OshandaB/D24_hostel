package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Student;
import org.hibernate.Session;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {

    private Session session;

    @Override
    public void save(Student entity) throws SQLException {
       session.save(entity);

    }

    @Override
    public ArrayList<Student> getAll() throws SQLException {
        return null;
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
        return null;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
