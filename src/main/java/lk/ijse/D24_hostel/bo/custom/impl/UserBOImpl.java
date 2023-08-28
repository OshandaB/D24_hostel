package lk.ijse.D24_hostel.bo.custom.impl;

import lk.ijse.D24_hostel.bo.custom.UserBO;
import lk.ijse.D24_hostel.config.SessionFactoryConfig;
import lk.ijse.D24_hostel.dao.custom.UserDAO;
import lk.ijse.D24_hostel.dao.custom.impl.UserDAOImpl;
import lk.ijse.D24_hostel.dto.UserDTO;
import lk.ijse.D24_hostel.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
 UserDAO userDAO = new UserDAOImpl();
    @Override
    public boolean saveUsers(UserDTO userDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userDAO.setSession(session);
            userDAO.save(userDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUsers(UserDTO userDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userDAO.setSession(session);
            userDAO.update(userDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDTO searchUsers(String id) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        try {
            userDAO.setSession(session);
            User user = userDAO.getUserById(id);
            UserDTO userDTO = new UserDTO(user.getUserId(),user.getUserName(),user.getPassword());
            session.close();
            return userDTO;
        } catch (Exception e) {

            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<String> loadUserIds() throws SQLException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        userDAO.setSession(session);

        ArrayList<String> list = userDAO.loadIds();
        session.close();
        return list;
    }

    @Override
    public String generateNextUserId() {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        userDAO.setSession(session);
        String nextId = userDAO.generateNextId();
        return nextId;
    }
}
