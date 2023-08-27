package lk.ijse.D24_hostel.dao.custom;

import lk.ijse.D24_hostel.dao.CrudDAO;
import lk.ijse.D24_hostel.entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    int countReservation(String id);
}
