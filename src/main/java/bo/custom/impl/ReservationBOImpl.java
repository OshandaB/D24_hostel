package bo.custom.impl;

import bo.custom.ReservationBO;
import config.SessionFactoryConfig;
import dao.custom.RoomDAO;
import dao.custom.impl.RoomDAOImpl;
import dto.ReservationDTO;
import entity.Room;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    RoomDAO roomDAO = new RoomDAOImpl();
    @Override
    public boolean saveReservation(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public ArrayList<ReservationDTO> getAllReservation() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> loadRoomIds() throws SQLException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        roomDAO.setSession(session);

        ArrayList<String> list = roomDAO.loadIds();
        session.close();
        return list;

    }
}
