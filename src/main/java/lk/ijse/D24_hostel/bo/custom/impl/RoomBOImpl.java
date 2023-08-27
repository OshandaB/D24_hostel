package lk.ijse.D24_hostel.bo.custom.impl;

import lk.ijse.D24_hostel.bo.custom.RoomBO;
import lk.ijse.D24_hostel.config.SessionFactoryConfig;
import lk.ijse.D24_hostel.dao.DAOFactory;
import lk.ijse.D24_hostel.dao.custom.RoomDAO;
import lk.ijse.D24_hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.D24_hostel.dto.RoomDTO;
import lk.ijse.D24_hostel.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RoomDAO);

    @Override
    public boolean saveRooms(RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomDAO.setSession(session);
            roomDAO.save(roomDTO.toEntity());
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
    public boolean updateRooms(RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomDAO.setSession(session);
            roomDAO.update(roomDTO.toEntity());
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
    public boolean deleteRooms(RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomDAO.setSession(session);
            roomDAO.delete(roomDTO.toEntity());
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
    public ArrayList<RoomDTO> getAllRooms() throws SQLException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
          roomDAO.setSession(session);
        ArrayList<Room> rooms = roomDAO.getAll();
        ArrayList<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room : rooms) {
            roomDTOS.add(new RoomDTO(
                    room.getRoomId(),
                    room.getKeyMoney(),
                    room.getRoomType(),
                    room.getQty()
            ));

        }
        session.close();
        return roomDTOS;
    }
}
