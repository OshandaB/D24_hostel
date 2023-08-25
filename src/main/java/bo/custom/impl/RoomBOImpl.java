package bo.custom.impl;

import bo.custom.RoomBO;
import config.SessionFactoryConfig;
import dao.custom.RoomDAO;
import dao.custom.impl.RoomDAOImpl;
import dto.RoomDTO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = new RoomDAOImpl();

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
