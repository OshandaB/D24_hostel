package lk.ijse.D24_hostel.dao.custom;

import lk.ijse.D24_hostel.dao.CrudDAO;
import lk.ijse.D24_hostel.entity.Reservation;
import lk.ijse.D24_hostel.entity.Room;
import org.hibernate.query.Query;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    int countReservation(String id);

}
