package bo.custom.impl;

import bo.custom.ReservationBO;
import config.SessionFactoryConfig;
import dao.custom.ReservationDAO;
import dao.custom.RoomDAO;
import dao.custom.StudentDAO;
import dao.custom.impl.ReservationDAOImpl;
import dao.custom.impl.RoomDAOImpl;
import dao.custom.impl.StudentDAOImpl;
import dto.ReservationDTO;
import dto.RoomDTO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    RoomDAO roomDAO = new RoomDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    ReservationDAO reservationDAO = new ReservationDAOImpl();
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


            RoomDTO roomDTO = new RoomDTO(roomss.getRoomId(),roomss.getKeyMoney(),roomss.getRoomType(),roomss.getQty(),roomss.getAvaliable_room());
            roomDTO.setAvaliable_room(roomss.getQty()-count1);
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


            RoomDTO roomDTO = new RoomDTO(roomss.getRoomId(),roomss.getKeyMoney(),roomss.getRoomType(),roomss.getQty(),roomss.getAvaliable_room());
            roomDTO.setAvaliable_room(roomss.getQty()-count1);
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
