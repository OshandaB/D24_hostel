package lk.ijse.D24_hostel.bo.custom.impl;

import lk.ijse.D24_hostel.bo.custom.HomeBO;
import lk.ijse.D24_hostel.config.SessionFactoryConfig;
import lk.ijse.D24_hostel.dao.DAOFactory;
import lk.ijse.D24_hostel.dao.custom.RoomDAO;
import lk.ijse.D24_hostel.dao.custom.StudentDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HomeBOImpl implements HomeBO {
    StudentDAO studentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.StudentDAO);
    RoomDAO roomDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RoomDAO);
    @Override
    public int studentCount() {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
       
        try {
            studentDAO.setSession(session);
            int count = studentDAO.StudentCount();

            session.close();
            return count;
        } catch (Exception e) {
           
            session.close();
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int roomCount() {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();

        try {
            roomDAO.setSession(session);
            int count = roomDAO.RoomsCount();

            session.close();
            return count;
        } catch (Exception e) {

            session.close();
            e.printStackTrace();
            return -1;
        }
    }
}
