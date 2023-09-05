package lk.ijse.D24_hostel.dao.custom.impl;

import lk.ijse.D24_hostel.dao.custom.ReservationDAO;
import lk.ijse.D24_hostel.entity.Reservation;
import lk.ijse.D24_hostel.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private Session session;

    @Override
    public void save(Reservation entity) throws SQLException {
        System.out.println(entity);
     session.save(entity);
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException {
        try {
            List<Reservation> reservationList = session.createNativeQuery("Select * from Reservation", Reservation.class).getResultList();
            return (ArrayList<Reservation>) reservationList;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Reservation entity) throws SQLException {
        session.delete(entity);
    }

    @Override
    public void update(Reservation entity) throws SQLException {
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

    @Override
    public int countReservation(String id) {
        //session.createNativeQuery("SELECT COUNT(res_id) from Reservation where room_type_id=id group by res_id", Reservation.class).getResultList();
        String sql = "SELECT COUNT(Re.resId) from Reservation AS Re where Re.reservationDetailsPK.roomId=:room_type_id ";
        Query query = session.createQuery(sql);
        query.setParameter("room_type_id",id);
        Long count = (Long) query.uniqueResult();


//        session.close();
        return Math.toIntExact(count);
        
    }


}
