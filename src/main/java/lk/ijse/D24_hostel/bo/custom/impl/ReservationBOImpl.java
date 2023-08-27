package lk.ijse.D24_hostel.bo.custom.impl;

import lk.ijse.D24_hostel.bo.custom.ReservationBO;
import lk.ijse.D24_hostel.config.SessionFactoryConfig;
import lk.ijse.D24_hostel.dao.DAOFactory;
import lk.ijse.D24_hostel.dao.custom.ReservationDAO;
import lk.ijse.D24_hostel.dao.custom.RoomDAO;
import lk.ijse.D24_hostel.dao.custom.StudentDAO;
import lk.ijse.D24_hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.D24_hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.D24_hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.D24_hostel.dto.ReservationDTO;
import lk.ijse.D24_hostel.dto.RoomDTO;
import lk.ijse.D24_hostel.entity.Reservation;
import lk.ijse.D24_hostel.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    RoomDAO roomDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RoomDAO);
    StudentDAO studentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.StudentDAO);
    ReservationDAO reservationDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ReservationDAO);

    @Override
    public boolean saveReservation(ReservationDTO reservationDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            roomDAO.setSession(session);
            reservationDAO.save(reservationDTO.toEntity());
            System.out.println(reservationDTO.getRoomTypeId());
            //Room rooms = roomDAO.getCustomerById(reservationDTO.getRoomTypeId());
            //System.out.println(rooms.getQty());
            Room roomss = roomDAO.getCustomerById(reservationDTO.getRoomTypeId());
            int count = reservationDAO.countReservation(reservationDTO.getRoomTypeId());
            int count1 = count / 2;
            System.out.println(count1);


            RoomDTO roomDTO = new RoomDTO(roomss.getRoomId(), roomss.getKeyMoney(), roomss.getRoomType(), roomss.getQty(), roomss.getAvaliable_room());
            roomDTO.setAvaliable_room(roomss.getQty() - count1);
            System.out.println(roomDTO.getQty());
//            roomDAO.update(new Room(roomDTO.getRoomTypeId(),roomDTO.getRoomType(),roomDTO.getKeyMoney(),roomDTO.getQty()));
            session.merge(roomDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (SQLException e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            reservationDAO.update(reservationDTO.toEntity());
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
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            roomDAO.setSession(session);
            reservationDAO.delete(reservationDTO.toEntity());
//            Room roomss = roomDAO.getCustomerById(reservationDTO.getRoomTypeId());
//            int count = reservationDAO.countReservation(reservationDTO.getRoomTypeId());
//            int count1 = count / 3;
//            System.out.println(count1);
//
//
//            RoomDTO roomDTO = new RoomDTO(roomss.getRoomId(),roomss.getKeyMoney(),roomss.getRoomType(),roomss.getQty());
//            roomDTO.setQty(roomss.getQty()+count1);
//            System.out.println(roomDTO.getQty());
////            roomDAO.update(new Room(roomDTO.getRoomTypeId(),roomDTO.getRoomType(),roomDTO.getKeyMoney(),roomDTO.getQty()));
//            session.merge(roomDTO.toEntity());
            Room roomss = roomDAO.getCustomerById(reservationDTO.getRoomTypeId());
            int count = reservationDAO.countReservation(reservationDTO.getRoomTypeId());
            int count1 = count / 2;
            System.out.println(count1);


            RoomDTO roomDTO = new RoomDTO(roomss.getRoomId(), roomss.getKeyMoney(), roomss.getRoomType(), roomss.getQty(), roomss.getAvaliable_room());
            roomDTO.setAvaliable_room(roomss.getQty() - count1);
            System.out.println(roomDTO.getQty());
//            roomDAO.update(new Room(roomDTO.getRoomTypeId(),roomDTO.getRoomType(),roomDTO.getKeyMoney(),roomDTO.getQty()));
            session.merge(roomDTO.toEntity());
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
    public ArrayList<ReservationDTO> getAllReservation() throws SQLException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        reservationDAO.setSession(session);
        ArrayList<Reservation> reservations = reservationDAO.getAll();
        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(
                    reservation.getReservationDetailsPK().getRoomId(),
                    reservation.getReservationDetailsPK().getStudentId(),
                    reservation.getResId(),
                    reservation.getDate(),
                    reservation.getStatus()
            ));

        }
        session.close();
        return reservationDTOS;
    }

    @Override
    public ArrayList<String> loadRoomIds() throws SQLException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        roomDAO.setSession(session);

        ArrayList<String> list = roomDAO.loadIds();
        session.close();
        return list;

    }

    @Override
    public ArrayList<String> loadStudentIds() throws SQLException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        studentDAO.setSession(session);

        ArrayList<String> list = studentDAO.loadIds();
        session.close();
        return list;
    }

//    @Override
//    public int countReservation(String id) {
//        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
//        reservationDAO.setSession(session);
//
//        int count = reservationDAO.countReservation(id);
//        session.close();
//        return count;
//    }
}
