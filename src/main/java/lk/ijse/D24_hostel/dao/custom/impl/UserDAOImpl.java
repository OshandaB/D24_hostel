package lk.ijse.D24_hostel.dao.custom.impl;

import lk.ijse.D24_hostel.dao.custom.UserDAO;
import lk.ijse.D24_hostel.entity.Student;
import lk.ijse.D24_hostel.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Session session;

    @Override
    public void save(User entity) throws SQLException {
        session.save(entity);

    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        try {
            List<User> users = session.createNativeQuery("Select * from User", User.class).getResultList();
            return (ArrayList<User>) users;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(User entity) throws SQLException {

    }

    @Override
    public void update(User entity) throws SQLException {
        session.update(entity);
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException {
        String sql = "SELECT U.userId FROM User AS U";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return (ArrayList<String>) list;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }


    @Override
    public User getUserById(String id) {
        String sql = "SELECT U FROM User AS U WHERE U.userId = :user_id";
        Query namedQuery = session.createQuery(sql);
        namedQuery.setParameter("user_id", id);
        User user = (User) namedQuery.getSingleResult();
        session.close();
        return user;
    }

    @Override
    public String generateNextId() {
        String sql = "SELECT U.userId FROM User AS U ORDER BY  U.userId desc ";
        Query namedQuery = session.createQuery(sql);
        String userId = (String) namedQuery.setMaxResults(1).uniqueResult();
        return userId;
    }
}
